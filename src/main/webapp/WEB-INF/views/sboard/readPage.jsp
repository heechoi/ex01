<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <script src="${pageContext.request.contextPath }/resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
 <script src="${pageContext.request.contextPath }/resources/board/js/read.js?v=3" type="text/javascript"></script>
<script>
	$(function(){
		getPage(1);
	})
</script>
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
							<input type="hidden" name="searchType" value="${cri.searchType }">
							<input type="hidden" name="keyword" value="${cri.keyword }">
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
							<div class="form-group" id="image_wrap">
								<c:forEach var="file" items="${boardVO.files }">
									<img src="${pageContext.request.contextPath }/displayFile?filename=${file }">
								</c:forEach>
							</div>

							<div class="form-group">
							<c:if test="${login.userid == boardVO.writer }">
								<input type="button" value="Modify" class="btn btn-warning" id="modify">
								<input type="button" value="Remove" class="btn btn-danger" id="remove">
							</c:if>
								
								<a href="listPage?page=${cri.page }&perPageNum=${cri.perPageNum}&searchType=${cri.searchType}&keyword=${cri.keyword}"><input type="button" value="Go List" class="btn btn-primary"></a>
							</div>
						
					</div>
				</div>
			</div>
		</div>
		
		<!-- replies[  -->
		<div class="row">
			<div class="col-md-12">
				<div class="box box-success">
					<div class="box-header">
						<h3 class="box-title">Add Reply</h3>
					</div>
					<div class="box-body">
						<label for="writer">Writer</label>
						<input type="text" id="writer" class="form-control" value="${login.userid }">
						<label for="replytext">Reply Text</label>
						<input type="text" id="replytext" class="form-control">
					</div>
					<div class="box-footer">
						<button class="btn btn-primary" id="replyAddBtn">Add Reply</button>
					</div>
				</div>
				<ul class="timeline">
					<li class="time-label" id="replysDiv">
						<span class="bg-green">Replies List [${boardVO.replycnt }]</span>
					</li>
				</ul>
				<div class="text-center">
					<ul id="pagination" class="pagination pagination-sm no-margin">
					
					</ul>
				</div>
			</div>
		</div>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.0.11/handlebars.js"></script>
		<script id="template" type="text/x-handlebars-template">
		{{#each.}}
     		 <li class="replyLi" data-rno={{rno}}>
         <i class="fa fa-comments bg-blue"></i>
         <div class="timeline-item">
            <span class="time"> <i class="fa fa-clock-o"></i>
               {{prettifyDate regdate}}
            </span>
            <h3 class="timeline-header">
               <strong>{{rno}}</strong> -{{replyer}}
            </h3>
            <div class="timeline-body">{{replytext}}</div>
			
			{{#if replyer}}
            <div class="timeline-footer">
               <a class="btn btn-primary btn-xs" data-toggle="modal"
                  data-target="#modifyModal" id="modifyModalBtn">Modify</a>
            </div>
			{{/if}}
         
		</div>
      </li>
      {{/each}}
		</script>
		<script>
			//options는 <div>~~내용들
			Handlebars.registerHelper("if",function(replyer,options){
				if(replyer =="${login.userid}"){
					return options.fn(this);
				}else{
					return "";
				}
			})
			
			Handlebars.registerHelper("prettifyDate",function(value){
				var dateObj = new Date(value);
				var year = dateObj.getFullYear();
				var month = dateObj.getMonth()+1;
				var date =  dateObj.getDate();
				
				return year+"/"+month+"/"+date;
			})	
			var bno = ${boardVO.bno}
			var relyPage = 1;
			var templateFunc = Handlebars.compile($("#template").html());
			
			$("#replyAddBtn").click(function(){
				
				var replyer = $("#writer").val();
				var replytext = $("#replytext").val();
				
				var sendData = {bno:bno,replyer:replyer,replytext:replytext};
				
				$.ajax({
					url:"${pageContext.request.contextPath}/replies/",
					type:"post",
					headers:{"Content-Type":"application/json"},
					dataType:"text",
					data:JSON.stringify(sendData),//json객체를 json string으로 변경해줌
					success:function(result){
						console.log(result);
						location.reload();
						getPage(1);
						alert(result);
					
		
						
					}
				})
			})
			
			function getPage(page){
				$.ajax({
					url:"${pageContext.request.contextPath}/replies/"+bno+"/"+page,
					type:"get",
					dataType:"json",
					success:function(result){
						console.log(result);
						$(".replyLi").remove();
						$(".timeline").append(templateFunc(result.list));
						printPaging(result.pageMaker);
				
					}
				})
			}
			

			function printPaging(pageMaker){
				var str = "";
				if(pageMaker.prev){
					str+="<li><a href='"+pageMaker.startPage-1+"'> << </a></li>";
				}
				for(var i=pageMaker.startPage;i<=pageMaker.endPage;i++){
					if(pageMaker.cri.page == i){
						str+="<li class='active'><a href='"+i+"'>"+i+"</a></li>";
					}else{
						str+="<li><a href='"+i+"'>"+i+"</a></li>";
					}
					
				}
				if(pageMaker.next){
					str+="<li><a href='"+pageMaker.startPage+1+"'> >> </a></li>";
				}
				
				$("#pagination").html(str);
			}
			
			$("#replysDiv").click(function(){
				getPage(1);
			})
			
			
			
			$(document).on("click","#modifyModalBtn",function(){
				var rno = $(this).parents(".timeline-item").find(".timeline-header").find("strong").text();
				var replytext = $(this).parents(".timeline-item").find(".timeline-body").text();
				$("#modalRno").html(rno);
				$("#modalReplytext").val(replytext);
			})
			
			
			
		$("#pagination").on("click"," li a",function(e){
			e.preventDefault();
			page = $(this).attr("href");
			$.ajax({
				url:"${pageContext.request.contextPath}/replies/"+bno+"/"+page,
				type:"get",
				dataType:"json",
				success:function(result){
					console.log(result);
					$(".replyLi").remove();
					$(".timeline").append(templateFunc(result.list));
					printPaging(result.pageMaker);
					
				}
			})
			
		})
		</script>
		<!-- ] replies  -->
	</section>
	 
	 <!-- Modal -->
 		 <div class="modal fade modal-primary" id="modifyModal" role="dialog">
  			  <div class="modal-dialog">
    
    		  <!-- Modal content-->
  			    <div class="modal-content">
       				 <div class="modal-header">
         				 <button type="button" class="close" data-dismiss="modal">&times;</button>
      				 		<h4 class="modal-title" id="modalRno"></h4>
      				 </div>
       			 <div class="modal-body">
          			<input type="text" id="modalReplytext" class="form-control">
        		</div>
       			 <div class="modal-footer">
       			 	<button type="button" class="btn btn-info" id="replyModifyBtn" data-dismiss="modal">Modify</button>
       			 	<button type="button" class="btn btn-danger" id="replyDeleteBtn" data-dismiss="modal">Delete</button>
          			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
       			 </div>
     		 </div>
      
    	</div>
 	 </div>
  <script>
  	$(document).on("click","#replyModifyBtn",function(){
  		var rno = $("#modalRno").text();
  		var replytext = $("#modalReplytext").val();
  		
  		var sendData = {replytext:replytext};
		$.ajax({
			url:"${pageContext.request.contextPath}/replies/"+rno,
			type:"put",
			dataType:"text",
			data:JSON.stringify(sendData),
			headers:{"Content-Type":"application/json"},
			success:function(result){
				console.log(result);
				getPage(1);
				alert("수정되었습니다");
			}
		})
  	})
  	
  	$(document).on("click","#replyDeleteBtn",function(){
  		var rno = $("#modalRno").text();
  		var sendData = {rno:rno};

			$.ajax({
				url:"${pageContext.request.contextPath}/replies/"+rno,
				type:"delete",
				dataType:"text",
				headers:{"Content-Type":"application/json"},
				data:JSON.stringify(sendData),
				success:function(result){
					console.log(result);
					location.reload();
					getPage(1);
					alert("삭제되었습니다");
				}
			})
		
			
  		
  	})
  </script>

	
	
<%@ include file="../include/footer.jsp" %>