<%@ page language="java" contentType="text/html; charset-UTF-8" pageEncoding="utf-8" %>

<%@ include file="../layout/header.jsp"%>

<div class="container">
  <button class="btn btn-secondary" onclick="history.back();">돌아가기</button>
  <button id="btn-update" class="btn btn-warning">수정</button>
  <c:if test="${board.user.id== principal.user.id}">
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

