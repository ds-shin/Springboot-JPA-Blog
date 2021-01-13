<%@ page language="java" contentType="text/html; charset-UTF-8" pageEncoding="utf-8" %>

<%@ include file="../layout/header.jsp"%>

<%--<sec:authorize access="isAuthenticated()">--%>
<%--  <sec:authentication property="principal" var="principal"></sec:authentication>--%>
<%--</sec:authorize>--%>

<div class="container">
  <form action="">
    <input type="hidden" id="id" value="${principal.user.id}" />
    <div class="form-group">
      <label for="username">username</label>
      <input type="text" value="${principal.user.username}"  class="form-control" placeholder="Enter username" id="username" readonly>
    </div>
    <!-- 카카오 로그인이면 비밀번호를 변경못하게 처리-->
<c:choose>
    <c:when test="${not empty principal.user.oauth}">
    <div class="form-group">
      <label for="email">Email</label>
      <input value="${principal.user.email}" type="email" class="form-control" placeholder="Enter email" id="email" readonly>
    </div>
    </c:when>
    <c:otherwise>
      <div class="form-group">
        <label for="password">Password</label>
        <input type="password" class="form-control" placeholder="Enter password" id="password">
      </div>
      <div class="form-group">
        <label for="email">Email</label>
        <input value="${principal.user.email}" type="email" class="form-control" placeholder="Enter email" id="email">
      </div>
      <button id="btn-update" class="btn btn-primary">회원수정완료</button>
    </c:otherwise>
</c:choose>
  </form>
</div>

<script src="/js/user.js"></script>

<%@ include file="../layout/footer.jsp"%>