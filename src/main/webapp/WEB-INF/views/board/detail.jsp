<%@ page language="java" contentType="text/html; charset-UTF-8" pageEncoding="utf-8" %>

<%@ include file="../layout/header.jsp"%>

<div class="container">
  <button class="btn btn-secondary" onclick="history.back();">돌아가기</button>

  <c:if test="${board.user.id== principal.user.id}">
    <a href="/board/${board.id}/updateForm" class="btn btn-warning">수정</a>
    <button id="btn-delete" class="btn btn-danger">삭제</button>
  </c:if>
  <br /><br />

    <div>
      글번호 : <span id="id"><i>${board.id} </i></span>
      작성자 : <span id="username"><i>${board.user.username}</i> </span>
    </div>
    <div class="form-group">
     <h3>${board.title}</h3>
    </div>
    <hr />
    <div class="form-group">
      <div>${board.content}</div>
    </div>
    <hr />

    <div class="card">
      <div class="card-body"><textarea class="form-control" rows="1"></textarea></div>
      <div class="card-footer"><button class="btn btn-primary">등록</button></div>
    </div>
    <br />
    <div class="card">
      <div class="card-header">댓글리스트</div>
      <ul id="reply--box" class="list-group">
        <c:forEach var="reply" items="${board.replys}">
          <li id="reply--1" class="list-group-item d-flex justify-content-between">
            <div>${reply.content}</div>
            <div class="d-flex"> <!-- 한줄처리 : d-flex -->
              <div class="font-italic">작성자 : ${reply.user.username} &nbsp;</div>
              <button class="badge">삭제</button>
<%--              <div>${reply.board}</div> 무한참조하기 때문에 다운된다. --%>
            </div>
          </c:forEach>
        </li>
      </ul>
    </div>
</div>

<script>
  $('.summernote').summernote({
    // placeholder: 'Hello stand alone ui',
    tabsize: 2,
    height: 300
    // ,toolbar: [
    //   ['style', ['style']],
    //   ['font', ['bold', 'underline', 'clear']],
    //   ['color', ['color']],
    //   ['para', ['ul', 'ol', 'paragraph']],
    //   ['table', ['table']],
    //   ['insert', ['link', 'picture', 'video']],
    //   ['view', ['fullscreen', 'codeview', 'help']]
    // ]
  });
</script>
<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>

