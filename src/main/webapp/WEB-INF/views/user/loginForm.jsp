<%@ page language="java" contentType="text/html; charset-UTF-8" pageEncoding="utf-8" %>

<%@ include file="../layout/header.jsp"%>

<div class="container">
  <form action="/auth/loginProc" method="post"><!-- form 태그를 이용하지 않고 ajax를 이용해서 처리-->
    <div class="form-group">
      <label for="username">username</label>
      <input type="text" name="username" class="form-control" placeholder="Enter username" id="username">
    </div>

    <div class="form-group">
      <label for="password">Password</label>
      <input type="password" name="password" class="form-control" placeholder="Enter password" id="password">
    </div>
    <%--
    <div class="form-group form-check">
      <label class="form-check-label">
        <input name="remember" class="form-check-input" type="checkbox"> Remember me
      </label>
    </div>
    --%>
    <button id="btn-login" class="btn btn-primary">로그인</button>
    <a href="https://kauth.kakao.com/oauth/authorize?client_id=bf591b4518919bd509251f6d16ee63a4&redirect_uri=http://localhost:8000/auth/kakao/callback&response_type=code"><img src="/image/kakao_login_button.png" height="38" alt="카카오로그인" /></a>
  </form>

</div>

<!-- js 로그인처리 하지않고 form 로그인 처리로 변경(security적용) -->
<%--<script src="/js/user.js"></script>--%>
<%@ include file="../layout/footer.jsp"%>