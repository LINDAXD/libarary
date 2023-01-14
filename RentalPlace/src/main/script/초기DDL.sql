create database library;

use library;

-- 회원 테이블
CREATE TABLE `member` (
  `user_id` varchar(20) NOT NULL, -- 아이디
  `user_pw` varchar(1024) NOT NULL, -- 비밀번호
  `user_name` varchar(50) NOT NULL, -- 이름
  `user_birth` date DEFAULT NULL, -- 생년월일
  `user_tel` varchar(30) NOT NULL, -- 전화번호
  `user_email` varchar(40) NOT NULL, -- 이메일
  `user_zip` varchar(10) NOT NULL, -- 우편번호
  `user_address` varchar(255) NOT NULL, -- 주소
  `user_address_detail` varchar(255) NOT NULL, -- 상세주소
  `user_able_loan` int(11) NOT NULL DEFAULT '10', -- 대출 가능 도서 수
  `user_book_count` int(11) NOT NULL DEFAULT '0', -- 대출 중 도서 수
  `user_overdue_date` int(11) DEFAULT '0', -- 대출 불가 일 수
  `enabled` varchar(1) DEFAULT '1', -- 권한
  `user_reg_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP, -- 회원 가입 일
  PRIMARY KEY (`user_id`)
);

-- 회원 권한 테이블
CREATE TABLE `member_auth` (
  `user_id` varchar(20) NOT NULL, -- 아이디
  `auth` varchar(100) NOT NULL, -- 권한
  KEY `fk_member_auth_user_id` (`user_id`),
  CONSTRAINT `fk_member_auth_userid` FOREIGN KEY (`user_id`) REFERENCES `member` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
);

-- 탈퇴 회원 테이블
CREATE TABLE `secession_member` (
  `user_id` varchar(20) NOT NULL,
  `user_email` varchar(40) NOT NULL,
  PRIMARY KEY (`user_id`)
);

-- 대출 내역 테이블 
CREATE TABLE `loan_history` (
  `loan_no` int(11) NOT NULL AUTO_INCREMENT, -- 대출 도서 번호
  `user_id` varchar(20) NOT NULL, -- 대출 회원 아이디
  `user_email` varchar(40) not NULL, -- 대출 회원 이메일
  `book_title` varchar(100) NOT NULL, -- 대출 도서 제목
  `book_author` varchar(200) NOT NULL, -- 대출 도서 저자
  `book_isbn` varchar(20) NOT NULL, -- 대출 도서 ISBN
  `book_cover` varchar(2000) DEFAULT NULL, -- 대출 도서 표지 주소
  `book_pubdate` varchar(20) NOT NULL, -- 대출 도서 출간일
  `book_publisher` varchar(50) NOT NULL, -- 대출 도서 출판사
  `loan_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP, -- 대출 일자
  `return_date` timestamp NULL DEFAULT NULL, -- 반납 일자
  `return_period` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP, -- 도서 반납 기한
  `return_status` tinyint(1) NOT NULL DEFAULT '0', -- 도서 반납 상태
  PRIMARY KEY (`loan_no`),
  KEY `loan_history_FK` (`user_id`),
  CONSTRAINT `loan_history_FK` FOREIGN KEY (`user_id`) REFERENCES `member` (`user_id`)
);

-- 희망 도서 테이블
CREATE TABLE `hope` (
  `hope_no` int(11) NOT NULL AUTO_INCREMENT, -- 희망 도서 번호
  `user_id` varchar(20) NOT NULL, -- 신청 회원 아이디
  `book_title` varchar(100) NOT NULL, -- 희망 도서 제목
  `book_author` varchar(200) NOT NULL, -- 희망 도서 저자
  `book_publisher` varchar(200) NOT NULL, -- 희망 도서 출판사
  `book_pubdate` varchar(200) NOT NULL, -- 희망 도서 출간일
  `book_isbn` varchar(20) DEFAULT NULL, -- 희망 도서 ISBN
  `note` varchar(1024) DEFAULT NULL, -- 비고
  `book_price` varchar(20) DEFAULT NULL, -- 희망 도서 가격
  `hope_status` int(11) DEFAULT '0', -- 희망 도서 처리 상태
  `cancel_reason` varchar(100) DEFAULT NULL, -- 희망 도서 취소 사유
  `hope_reg_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP, -- 희망 도서 신청일
  PRIMARY KEY (`hope_no`),
  KEY `hope_FK` (`user_id`),
  CONSTRAINT `hope_FK` FOREIGN KEY (`user_id`) REFERENCES `member` (`user_id`)
);

-- 추천 도서 테이블
CREATE TABLE `recommend_book` (
  `rec_no` int(11) NOT NULL AUTO_INCREMENT, -- 추천 도서 번호
  `user_id` varchar(20) NOT NULL, -- 추천자 아이디
  `book_title` varchar(100) NOT NULL, -- 추천 도서 제목
  `book_author` varchar(200) NOT NULL, -- 추천 도서 저자
  `book_isbn` varchar(20) NOT NULL, -- 추천 도서 ISBN
  `book_cover` varchar(2000) NOT NULL, -- 추천 도서 표지
  `book_pubdate` varchar(20) NOT NULL, -- 추천 도서 출간일
  `book_publisher` varchar(50) NOT NULL, -- 추천 도서 출판사
  `recommend_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP, -- 추천 도서 등록일
  PRIMARY KEY (`rec_no`),
  KEY `recommend_book_FK` (`user_id`),
  CONSTRAINT `recommend_book_FK` FOREIGN KEY (`user_id`) REFERENCES `member` (`user_id`)
);

-- 열람실 테이블
CREATE TABLE `reading_room` (
  `seat_no` int(11) NOT NULL, -- 좌석 번호
  `user_id` varchar(20) DEFAULT NULL, -- 예약자 아이디
  `checkin_time` timestamp NULL DEFAULT NULL, -- 좌석 입실 시간
  `checkout_time` timestamp NULL DEFAULT NULL, -- 좌석 퇴실 시간
  PRIMARY KEY (`seat_no`),
  KEY `reading_room_FK` (`user_id`),
  CONSTRAINT `reading_room_FK` FOREIGN KEY (`user_id`) REFERENCES `member` (`user_id`) ON DELETE SET NULL
);

-- 도서관 일정 테이블
CREATE TABLE `calendar` (
  `cal_no` int(11) NOT NULL AUTO_INCREMENT, -- 일정 번호
  `groupId` int(11) NOT NULL, -- 그룹 아이디
  `user_id` varchar(20) NOT NULL, -- 일정 작성자 아이디
  `title` varchar(1024) NOT NULL, -- 일정 제목
  `start` date NOT NULL, -- 일정 시작 시간
  `end` date NOT NULL, -- 일정 종료 시간
  `allDay` int(11) NOT NULL, -- 하루종일인지
  `textColor` varchar(50) NOT NULL, -- 글자 색상
  `backgroundColor` varchar(50) NOT NULL, -- 배경 색상
  `borderColor` varchar(50) NOT NULL, -- 외곽선 색상
  `reg_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP, -- 일정 등록 일자
  PRIMARY KEY (`cal_no`),
  KEY `calendar_FK` (`user_id`),
  CONSTRAINT `calendar_FK` FOREIGN KEY (`user_id`) REFERENCES `member` (`user_id`)
);

-- 공지사항 테이블
CREATE TABLE `notice` (
  `notice_no` int(11) NOT NULL AUTO_INCREMENT, -- 공지사항 번호
  `notice_title` varchar(50) NOT NULL, -- 공지사항 제목
  `notice_content` varchar(8196) NOT NULL, -- 공지사항 내용
  `writer_id` varchar(20) NOT NULL, -- 공지사항 작성자 아이디
  `writer_name` varchar(50) NOT NULL, -- 공지사항 작성자 이름
  `notice_reg_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP, -- 공지사항 작성일
  `notice_views` int(11) NOT NULL DEFAULT '0', -- 공지사항 조회수
  `notice_img` mediumblob, -- 공지사항 첨부파일 경로 
  PRIMARY KEY (`notice_no`),
  KEY `notice_FK` (`writer_id`),
  CONSTRAINT `notice_FK` FOREIGN KEY (`writer_id`) REFERENCES `member` (`user_id`)
);

-- 공지사항 첨부파일 테이블
CREATE TABLE `notice_attach_file` (
  `uuid` varchar(500) NOT NULL, -- uuid
  `upload_path` varchar(4096) NOT NULL, -- 업로드 경로
  `file_name` varchar(100) NOT NULL, -- 파일명
  `file_type` char(1) DEFAULT '1', -- 파일 타입
  `notice_no` int(11) NOT NULL, -- 공지사항 번호
  PRIMARY KEY (`uuid`),
  KEY `notice_no` (`notice_no`),
  CONSTRAINT `notice_attach_file_ibfk_1` FOREIGN KEY (`notice_no`) REFERENCES `notice` (`notice_no`) ON DELETE CASCADE ON UPDATE CASCADE
);

-- 분실물 테이블
CREATE TABLE `article` (
  `article_no` int(11) NOT NULL AUTO_INCREMENT, -- 분실물 찾기 게시글 번호
  `article_title` varchar(50) NOT NULL, -- 분실물찾기 제목
  `article_content` varchar(8196) NOT NULL, -- 분실물 찾기 내용
  `writer_id` varchar(20) NOT NULL, -- 작성자 아이디
  `writer_name` varchar(50) NOT NULL, -- 작성자 이름
  `article_reg_date` timestamp DEFAULT CURRENT_TIMESTAMP, -- 게시글 등록일
  `article_views` int(11) NOT NULL DEFAULT '0', -- 게시글 조회수
  PRIMARY KEY (`article_no`),
  KEY `article_FK` (`writer_id`),
  CONSTRAINT `article_FK` FOREIGN KEY (`writer_id`) REFERENCES `member` (`user_id`)
);

-- 분실물 첨부파일 테이블
CREATE TABLE `attach_file` (
  `uuid` varchar(500) NOT NULL, -- uuid
  `upload_path` varchar(4096) NOT NULL, -- 업로드 경로
  `file_name` varchar(100) NOT NULL, -- 파일명
  `file_type` char(1) DEFAULT '1', -- 파일 타입
  `article_no` int(11) NOT NULL, -- 분실물 찾기 게시글 번호
  PRIMARY KEY (`uuid`),
  KEY `article_no` (`article_no`),
  CONSTRAINT `attach_file_ibfk_1` FOREIGN KEY (`article_no`) REFERENCES `article` (`article_no`) ON DELETE CASCADE ON UPDATE CASCADE
);

-- 문의사항 테이블
CREATE TABLE `enquiry` (
  `enquiry_no` int(11) NOT NULL AUTO_INCREMENT, -- 문의사항 게시글 번호
  `enquiry_title` varchar(1024) NOT NULL, -- 문의사항 제목
  `enquiry_content` varchar(8196) NOT NULL, -- 문의사항 내용
  `writer_id` varchar(20) NOT NULL, -- 문의사항 작성자 아이디
  `writer_name` varchar(50) NOT NULL, -- 문의사항 작성자 이름
  `enquiry_hits` int(11) NOT NULL DEFAULT '0', -- 문의사항 조회수
  `enquiry_reg_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP, -- 문의사항 등록일
  PRIMARY KEY (`enquiry_no`),
  KEY `enquiry_FK` (`writer_id`),
  CONSTRAINT `enquiry_FK` FOREIGN KEY (`writer_id`) REFERENCES `member` (`user_id`)
);

-- 답변 테이블
CREATE TABLE `answer` (
  `answer_no` int(11) NOT NULL AUTO_INCREMENT, -- 답변 번호
  `enquiry_no` int(11) NOT NULL, -- 문의사항 번호
  `answer_title` varchar(1024) NOT NULL, -- 답변 제목
  `answer_content` varchar(8196) NOT NULL, -- 답변 내용
  `a_writer_id` varchar(20) NOT NULL, -- 답변 작성자 아이디
  `a_writer_name` varchar(50) NOT NULL, -- 답변 작성자 이름
  `answer_hits` int(11) NOT NULL DEFAULT '0', -- 답변 조회수
  `answer_reg_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP, -- 답변 등록일
  PRIMARY KEY (`answer_no`),
  KEY `fk_answer_enquiry_no` (`enquiry_no`),
  KEY `answer_FK` (`a_writer_id`),
  CONSTRAINT `answer_FK` FOREIGN KEY (`a_writer_id`) REFERENCES `member` (`user_id`),
  CONSTRAINT `fk_answer_enquiry_no` FOREIGN KEY (`enquiry_no`) REFERENCES `enquiry` (`enquiry_no`) ON DELETE CASCADE ON UPDATE CASCADE
);

-- 배너 테이블
CREATE TABLE `banner` (
  `user_id` varchar(20) NOT NULL, -- 등록자 아이디
  `path` varchar(4096) NOT NULL, -- 경로
  `reg_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP, -- 등록일
  KEY `banner_FK` (`user_id`),
  CONSTRAINT `banner_FK` FOREIGN KEY (`user_id`) REFERENCES `member` (`user_id`)
);


-- 이벤트 출력
show variables like 'event%';

-- 이벤트 활성화
set global event_scheduler = on;

-- 등록 된 이벤트 출력
select * from information_schema.EVENTS;


-- 대출 불가 일수 -1
DELIMITER //
CREATE PROCEDURE overdue_decrease()
begin
   update member 
     set user_overdue_date = user_overdue_date - 1
   where user_overdue_date > 0;
END //

-- 자정마다 대출 불가 일수 -1 하는 이벤트
create event overdue_decrease
on schedule every 1 day
starts '2021-12-04 00:00:00'
comment '대출 불가 일 수'
do
call overdue_decrease(); 




-- 열람실 퇴실 프로시저
DELIMITER //
CREATE PROCEDURE seat_check()
begin
   update reading_room
      set user_id = null, checkin_time = null, checkout_time = null
    where checkout_time <= current_time;
END //

-- 열람실 실시간 검사 후 퇴실 처리하는 이벤트
create event check_seat
on schedule every 1 second
comment '열람실 실시간 검사'
do
call seat_check(); 



-- 공지사항 삭제 시 공지사항 번호 정렬
DELIMITER //
CREATE PROCEDURE notice_reset()
begin
   SET @CNT = 0;
   UPDATE notice SET notice.notice_no = @CNT:=@CNT+1;
   set @max = (SELECT MAX(notice_no)+ 1 FROM notice); 
   set @str = CONCAT('ALTER TABLE notice AUTO_INCREMENT = ', @max);
   PREPARE qry FROM @str;
   EXECUTE qry;
   DEALLOCATE PREPARE qry;
END //



-- 문의사항 삭제 시 문의사항 번호 정렬
DELIMITER //
CREATE PROCEDURE enquiry_reset()
begin
   SET @CNT = 0;
   UPDATE enquiry SET enquiry.enquiry_no = @CNT:=@CNT+1;
   set @max = (SELECT MAX(enquiry_no)+ 1 FROM enquiry); 
   set @str = CONCAT('ALTER TABLE enquiry AUTO_INCREMENT = ', @max);
   PREPARE qry FROM @str;
   EXECUTE qry;
   DEALLOCATE PREPARE qry;
END //


-- 답변 삭제 시 답변 번호 정렬
DELIMITER //
CREATE PROCEDURE answer_reset()
begin
   SET @CNT = 0;
   UPDATE answer SET answer.answer_no = @CNT:=@CNT+1;
   set @max = (SELECT MAX(enquiry_no)+ 1 FROM enquiry); 
    set @str = CONCAT('ALTER TABLE answer AUTO_INCREMENT = ', @max);
    PREPARE qry FROM @str;
    EXECUTE qry;
    DEALLOCATE PREPARE qry;
END //

-- 분실물 찾기 삭제 시 분실물 찾기 번호 정렬
DELIMITER //
CREATE PROCEDURE article_reset()
begin
   SET @CNT = 0;
   UPDATE article SET article.article_no = @CNT:=@CNT+1;
   set @max = (SELECT MAX(article_no)+ 1 FROM article); 
    set @str = CONCAT('ALTER TABLE article AUTO_INCREMENT = ', @max);
    PREPARE qry FROM @str;
    EXECUTE qry;
    DEALLOCATE PREPARE qry;
END //

-- 초기 관리자 계정 설정
-- id : admin
-- pw : zxcvzxcv

insert into member values
("admin", "$2a$10$oyw6645fwRPh9BOpgsVzZuqkSQr1N/b8UGE25hiU0ww7kEQ/e.YPW", "관리자", "2021-12-06", "01000000000",
"library.raon@gmail.com", "63309", "제주특별자치도 제주시 첨단로 242 (영평동)", "1", 10, 0, 0, 1, current_timestamp);
insert into member_auth values("admin", "ROLE_MEMBER");
insert into member_auth values("admin", "ROLE_ADMIN");
insert into member_auth values("admin", "ROLE_MASTER");

-- 기본 배너 추가
insert into banner values("admin", "/resources/banner/banner1.png", current_timestamp);
insert into banner values("admin", "/resources/banner/banner2.png", current_timestamp);

-- 열람실 좌석 insert
DELIMITER //
CREATE PROCEDURE insert_seat()
begin
    DECLARE i INT DEFAULT 1;
    WHILE (i <= 124) DO
        INSERT INTO reading_room(seat_no) VALUE (i); -- ⓓ 테이블에 i값 넣어주기
        SET i = i + 1; -- ⓔ i값에 1더해주고 WHILE문 처음으로 이동
    END WHILE;
END //

call insert_seat();


-- 기준일 기점으로 일주일 씩 휴관일 지정 (2021-11-29 기준)
DELIMITER //
CREATE PROCEDURE insert_day()
begin
    DECLARE i INT DEFAULT 1;
	DECLARE j int default 7;
    WHILE (i <= 54) DO
        insert into calendar values(null, 1, 'admin', '휴관일', DATE_ADD('2021-11-29', interval j day), 
		DATE_ADD('2021-11-29', interval j day), 1, "red", "none", "none", current_timestamp);	
		SET i = i + 1;
		SET j = j + 7;
    END WHILE;
END //

call insert_day();
