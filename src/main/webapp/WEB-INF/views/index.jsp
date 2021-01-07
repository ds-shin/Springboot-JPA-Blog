<%@ page language="java" contentType="text/html; charset-UTF-8" pageEncoding="utf-8" %>

<%@ include file="layout/header.jsp"%>

<!--
참조 : https://www.w3schools.com/bootstrap4/tryit.asp?filename=trybs_card_image&stacked=h
-->
<div class="container">
  <c:forEach var="board" items="${boards}">
    <div class="card m-2"> <!-- 마진 -->
      <div class="card-body">
        <h4 class="card-title">${board.title}</h4>
        <a href="#" class="btn btn-primary">상세보기</a>
      </div>
    </div>
  </c:forEach>
</div>

<%@ include file="layout/footer.jsp"%>

