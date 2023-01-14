<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<html>
<head>
	<title>Home</title>
	<link rel="stylesheet" href="/resources/css/board/sub3/answer_content_page.css">
	<link rel="stylesheet" href="/resources/css/header.css">
</head>
<body>

<div class="header">
	<jsp:include page="header.jsp"></jsp:include>
</div>

<P> ${serverTime}. </P>

<sec:authorize access="isAuthenticated()">
		<sec:authentication property="principal.dto.user_name"/>님 반갑습니다.<br>
		당신은 <sec:authentication property="principal.dto.authList"/> 권한입니다. <br>
</sec:authorize> 
</body>
</html>
