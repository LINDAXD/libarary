package com.library.controller.buy;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.library.model.search.BookDTO;
import com.library.model.search.DateDTO;
import com.library.page.Criteria;
import com.library.page.ViewPage;
import com.library.service.search.AladinApi;
import com.library.service.search.BookService;
import com.library.util.DateUtil;

@Controller
@RequestMapping("/search")
public class CancelController {

	@Autowired
	private AladinApi api;

	@Autowired
	private BookService bookService;

	

}
