<%@ page language="java" contentType="text/html; charset-UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authorize access="isAuthenticated()">
  <script>
    //alert("로그인된 사용자입니다.");
  </script>
  <sec:authentication property="principal" var="principal"></sec:authentication>
</sec:authorize>

<!--
  spring security : user/pwd(서버로그에 출력됨)
  //login,logout 가 내장되어 있다.
-->
<!DOCTYPE html>
<html lang="ko">
<head>
  <title>My Blog : Bootstrap</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>

<nav class="navbar navbar-expand-md bg-dark navbar-dark">
  <a class="navbar-brand" href="/">MyBlog</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="collapsibleNavbar">
  <c:choose>
<%--    <c:when test="${empty sessionScope.principal}">--%>
    <c:when test="${empty principal}">
      <ul class="navbar-nav">
        <li class="nav-item"><a class="nav-link" href="/loginForm">로그인</a></li>
        <li class="nav-item"><a class="nav-link" href="/joinForm">회원가입</a></li>
      </ul>
    </c:when>
    <c:otherwise>
      <ul class="navbar-nav">
        <li class="nav-item"><a class="nav-link" href="/board/writeFrom">글쓰기</a></li>
        <li class="nav-item"><a class="nav-link" href="/user/userForm">회원정보</a></li>
        <li class="nav-item"><a class="nav-link" href="/logout">로그아웃</a></li>
      </ul>
    </c:otherwise>
  </c:choose>
  </div>
</nav>
<br />