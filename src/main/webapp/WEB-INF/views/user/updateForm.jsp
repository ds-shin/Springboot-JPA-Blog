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
    <div class="form-group">
      <label for="password">Password</label>
      <input type="password" class="form-control" placeholder="Enter password" id="password">
    </div>
    <div class="form-group">
      <label for="email">Email</label>
      <input value="${principal.user.email}" type="email" class="form-control" placeholder="Enter email" id="email">
    </div>
  </form>
  <button id="btn-update" class="btn btn-primary">수정완료</button>
</div>

<script src="/js/user.js"></script>

<%@ include file="../layout/footer.jsp"%>