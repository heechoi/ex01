<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
	<section class="content">
		<div class="row">
			<div class="col-md-12">
				<div class="box">
					<div class="box-header with-border">
						<h3 class="box-title">MODIFY</h3>
					</div>
					<div class="box-body">
						<form method="post" action="modify">
							<input type="hidden" name="bno" value="${boardVO.bno }">
							<input type="hidden" name="page" value="${cri.page }">
							<input type="hidden" name="perPageNum" value="${cri.perPageNum }">
						
							<div class="form-group">
								<label>Title</label>
								<input type="text" name="title" class="form-control" placeholder="title" value="${boardVO.title }">
							</div>
							<div class="form-group">
								<label>Content</label>
								<textarea rows="5" class="form-control" placeholder="content" name="content">${ boardVO.content}</textarea>
							</div>
							<div class="form-group">
								<label>Writer</label>
								<input type="text" name="writer" class="form-control" placeholder="writer" value="${boardVO.writer }">
							</div>
							<div class="form-group">
								<input type="submit" value="Modify" class="btn btn-warning">
								<a href="listPage?page=${cri.page }&perPageNum=${cri.perPageNum}"><input type="button" value="Go List" class="btn btn-primary"></a>
							</div>
							</form>
					</div>
				</div>
			</div>
		</div>
	</section>
<%@ include file="../include/footer.jsp" %>