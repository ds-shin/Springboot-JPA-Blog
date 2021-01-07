<%@ page language="java" contentType="text/html; charset-UTF-8" pageEncoding="utf-8" %>

<%@ include file="../layout/header.jsp"%>

<div class="container">
  <form><!-- form 태그를 이용하지 않고 ajax를 이용해서 처리-->
    <div class="form-group">
      <label for="title">title</label>
      <input type="text" name="title" class="form-control" placeholder="Enter title" id="title">
    </div>

    <div class="form-group">
      <label for="content">Comment:</label>
      <textarea class="form-control summernote" rows="5" id="content"></textarea>
    </div>
  </form>

  <button id="btn-save" class="btn btn-primary">글쓰기완료</button>

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

