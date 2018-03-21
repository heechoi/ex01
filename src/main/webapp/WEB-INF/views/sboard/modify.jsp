<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../include/header.jsp" %>
<style>
	.img_bg, .newImg{
		position: relative;
		float: left;
		margin: 5px;
		margin-bottom: 10px;
	}
	.img_bg button,.newImg button{
		position: absolute;
		right:0px;
		top:0px;
	}
	.newImg img.resize{
		height:100px;
		width:auto;
	}
</style>
<script>
	$(function(){
		var index=1;
		$("#files").change(function(){
			
			var file = document.getElementById("files");
			var reader = new FileReader();
			
			reader.onload = function(e){
					var newImg = $("<div class='newImg'>");
					var imgObj = $("<img>").attr("src",e.target.result);
					var btnObj = $("<button class='deleteBtn'>").text("X").attr("type","button");
					var total  = newImg.append(imgObj).append(btnObj);
					 $("#image_wrap").append(total); 
				}
			
			reader.readAsDataURL(file.files[0]);
			reader.onloadend = function(e){
				if(index>=file.files.length){
					index==1;
					return;
				}
				reader.readAsDataURL(file.files[index]);
				index++;
			}

			$("#image_wrap").append();
		})
		
		$(document).on("click",".deleteBtn",function(){
			$(this).parent().remove();
			if($(this).val() !=null){
				$("#modi").append("<input type='hidden' name='delfiles' value='"+$(this).val()+"'>");
			}
			
		})
	})
</script>
	<section class="content">
		<div class="row">
			<div class="col-md-12">
				<div class="box">
					<div class="box-header with-border">
						<h3 class="box-title">MODIFY</h3>
					</div>
					<div class="box-body">
						<form method="post" action="modify" enctype="multipart/form-data">
							<input type="hidden" name="bno" value="${boardVO.bno }">
							<input type="hidden" name="page" value="${cri.page }">
							<input type="hidden" name="perPageNum" value="${cri.perPageNum }">
							<input type="hidden" name="searchType" value="${cri.searchType }">
							<input type="hidden" name="keyword" value="${cri.keyword }">
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
							<div class="form-group" id="image_wrap">
								<c:forEach var="file" items="${boardVO.files }">
								<div class="img_bg">
									<img src="${pageContext.request.contextPath }/displayFile?filename=${file }">
									<button class="deleteBtn" type="button" value="${file }">X</button>
								</div>
									
								</c:forEach>
							</div>
							<input type="file" class="form-control" multiple="multiple" id="files" name="imageFile">
							<div class="form-group">
								<input type="submit" value="Modify" class="btn btn-warning" id="modi">
								<a href="listPage?page=${cri.page }&perPageNum=${cri.perPageNum}&searchType=${cri.searchType}&keyword=${cri.keyword}"><input type="button" value="Go List" class="btn btn-primary"></a>
							</div>
							</form>
					</div>
				</div>
			</div>
		</div>
	</section>
<%@ include file="../include/footer.jsp" %>