package com.library.controller.board;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.library.model.board.NoticeAttachForAjaxDTO;
import com.library.util.PathUtil;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

@Controller
@Log4j
public class NoticeAttachController {

	public String UPLOAD_PATH = PathUtil.path() + File.separator + "notice"; // 업로드 경로

	// 첨부 파일 형식이 이미지인지 아닌지 체크
	private boolean checkImageType(File file) {

		try {
			String contentType = Files.probeContentType(file.toPath());
			return contentType.startsWith("image");
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}

	// 첨부할 파일 선택 시
	@PostMapping(value = "/uploadNoticeFileAjaxAction", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<NoticeAttachForAjaxDTO>> uploadNoticeFileAjaxPost(MultipartFile[] uploadNoticeFile) {

		List<NoticeAttachForAjaxDTO> list = new ArrayList<>();
		String uploadFolder = UPLOAD_PATH;

		File uploadPath = new File(uploadFolder);

		if (uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}

		for (MultipartFile multipartFile : uploadNoticeFile) {

			NoticeAttachForAjaxDTO attachForAjaxDTO = new NoticeAttachForAjaxDTO();
			String uploadFileName = multipartFile.getOriginalFilename();

			// 파일명에 띄어쓰기 있을 시 _로 변경
			uploadFileName = uploadFileName.replace(" ", "_");

			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
			log.info("only file name: " + uploadFileName);
			attachForAjaxDTO.setFile_name(uploadFileName);

			UUID uuid = UUID.randomUUID();

			uploadFileName = uuid.toString() + "_" + uploadFileName;

			try {
				// 파일을 폴더에 저장
				File saveFile = new File(uploadPath, uploadFileName);
				multipartFile.transferTo(saveFile);

				attachForAjaxDTO.setUuid(uuid.toString());
				attachForAjaxDTO.setUpload_path(uploadFolder);

				// 해당 파일이 이미지일 경우 폴더에 썸네일 저장
				if (checkImageType(saveFile)) {
					attachForAjaxDTO.setImage(true);
					FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_" + uploadFileName));
					Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 100, 100);
					thumbnail.close();
				}

				list.add(attachForAjaxDTO);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	// 첨부할 파일 썸네일 출력
	@GetMapping("/displayFiles")
	@ResponseBody
	public ResponseEntity<byte[]> getFile(String file_name) {

		File file = new File(file_name);

		ResponseEntity<byte[]> result = null;

		try {
			HttpHeaders header = new HttpHeaders();

			header.add("Content-Type", Files.probeContentType(file.toPath()));
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

	// 첨부할 파일 선택 취소 시 폴더에 저장된 파일 삭제
	@PostMapping("/deleteNoticeFile")
	@ResponseBody
	public ResponseEntity<String> deleteNoticeFile(String file_name, String type, @RequestParam("uuid") String uuid) {

		log.info("deleteFile: " + file_name);

		fileDelete(uuid, type);

		return new ResponseEntity<String>("deleted", HttpStatus.OK);
	}

	// 폴더 내 파일 삭제 함수(이미지일 경우 썸네일까지 삭제)
	public void fileDelete(String uuid, String type) {

		String filePath = UPLOAD_PATH + File.separator;

		File deleteFileName = new File(filePath + uuid);

		if (type.equals("image")) {
			String thumb = "s_" + uuid;
			File deleteThumbFileName = new File(filePath + thumb);
			deleteFileName.delete();
			deleteThumbFileName.delete();
		} else {
			deleteFileName.delete();
		}

	}

	// 첨부 파일 다운로드
	@GetMapping(value = "/downloadNoticeFile", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public ResponseEntity<Resource> downloadNoticeFile(@RequestHeader("User-Agent") String userAgent,
			String file_name) {

		Resource resource = new FileSystemResource(file_name);

		// 파일이 존재하지 않으면 404 에러 전달
		if (resource.exists() == false) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		String resourseName = resource.getFilename();

		// remove uuid
		String resourseOriginalName = resourseName.substring(resourseName.indexOf("_") + 1);

		HttpHeaders headers = new HttpHeaders();

		try {
			String downloadName = null;

			if (userAgent.contains("Trident")) {
				log.info("IE browser");
				downloadName = URLEncoder.encode(resourseOriginalName, "UTF-8").replaceAll("\\+", " ");
			} else if (userAgent.contains("Edge")) {
				log.info("Edge browser");
				downloadName = URLEncoder.encode(resourseOriginalName, "UTF-8");
			} else {
				log.info("Chrome browser");
				downloadName = new String(resourseOriginalName.getBytes("UTF-8"), "ISO-8859-1");
			}
			log.info("downloadName: " + downloadName);

			headers.add("Content-Disposition", "attachment; filename=" + downloadName);

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}

}
