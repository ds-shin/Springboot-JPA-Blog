<%@ page language="java" contentType="text/html; charset-UTF-8" pageEncoding="utf-8" %>

<%@ include file="../layout/header.jsp"%>

<div class="container">
  <form action=""><!-- form 태그를 이용하지 않고 ajax를 이용해서 처리-->
    <div class="form-group">
      <label for="username">username</label>
      <input type="text" class="form-control" placeholder="Enter username" id="username">
    </div>
    <div class="form-group">
      <label for="password">Password</label>
      <input type="password" class="form-control" placeholder="Enter password" id="password">
    </div>
    <div class="form-group">
      <label for="email">Email</label>
      <input type="email" class="form-control" placeholder="Enter email" id="email">
    </div>
  </form>
  <button id="btn-save" class="btn btn-primary">회원가입완료</button>
</div>

<script src="/js/user.js"></script>

<%@ include file="../layout/footer.jsp"%>