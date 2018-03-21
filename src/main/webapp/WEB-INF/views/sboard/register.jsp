<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp" %>

<style>
	#previewBoard{
		width:100%;
		height:600px;
		border:1px solid #ccc;
		overflow: auto;
		margin-top: 10px;
	}
	#previewBoard img{
		max-width:100%;
		max-height:100%;
	}
</style>
<script>
	$(function(){
		var index =1;
		$("#files").change(function(){
		
			$("#previewBoard").empty();
			
			var file = document.getElementById("files");
			var reader = new FileReader();
			
			reader.onload = function(e){
					var imgObj = $("<img>").attr("src",e.target.result);
					$("#previewBoard").append(imgObj);
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

		})
	})
</script>
	<section class="content">
		<div class="row">
			<div class="col-md-12">
				<div class="box">
					<div class="box-header with-border">
						<h3 class="box-title">REGISTER</h3>
					</div>
					<div class="box-body">
						<form method="post" action="register" enctype="multipart/form-data">
							<div class="form-group">
								<label>Title</label>
								<input type="text" name="title" class="form-control" placeholder="title">
							</div>
							<div class="form-group">
								<label>Content</label>
								<textarea rows="5" class="form-control" placeholder="content" name="content"></textarea>
							</div>
							<div class="form-group">
								<label>Writer</label>
								<input type="text" name="writer" class="form-control" placeholder="writer" value="${login.userid }" readonly="readonly">
							</div>
							<div class="form-group">
								<label>Image File</label>
								<input type="file" name="imageFile" class="form-control" multiple="multiple" id="files">
								<div id="previewBoard">
								
								</div>
							</div>
							<div class="form-group">
								<input type="submit" value="submit" class="btn btn-primary">
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</section>
<%@ include file="../include/footer.jsp" %>