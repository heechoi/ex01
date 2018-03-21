<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <script src="${pageContext.request.contextPath }/resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
 <script src="${pageContext.request.contextPath }/resources/board/js/read.js?v=3" type="text/javascript"></script>
<%@ include file="../include/header.jsp" %>
	<section class="content">
		<div class="row">
			<div class="col-md-12">
				<div class="box">
					<div class="box-header with-border">
						<h3 class="box-title">READ</h3>
					</div>
					<div class="box-body">
						<form method="get" action="" id="f">
							<input type="hidden" name="bno" value="${boardVO.bno }">
							<input type="hidden" name="page" value="${cri.page }">
							<input type="hidden" name="perPageNum" value="${cri.perPageNum }">
						</form>
						
							<div class="form-group">
								<label>Title</label>
								<input type="text" name="title" class="form-control" placeholder="title" value="${boardVO.title }" readonly="readonly">
							</div>
							<div class="form-group">
								<label>Content</label>
								<textarea rows="5" class="form-control" placeholder="content" name="content" readonly="readonly">${ boardVO.content}</textarea>
							</div>
							<div class="form-group">
								<label>Writer</label>
								<input type="text" name="writer" class="form-control" placeholder="writer" value="${boardVO.writer }" readonly="readonly">
							</div>
							<div class="form-group">
								<input type="button" value="Modify" class="btn btn-warning" id="modify">
								<input type="button" value="Remove" class="btn btn-danger" id="remove">
								<a href="listPage?page=${cri.page }&perPageNum=${cri.perPageNum}"><input type="button" value="Go List" class="btn btn-primary"></a>
							</div>
						
					</div>
				</div>
			</div>
		</div>
	</section>
<%@ include file="../include/footer.jsp" %>