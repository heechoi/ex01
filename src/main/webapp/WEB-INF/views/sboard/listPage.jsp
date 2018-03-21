<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <script src="${pageContext.request.contextPath }/resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../include/header.jsp" %>
	<section class="content">
		<div class="row">
			<div class="col-md-12">
				<div class="box">
					<div class="box-header with-border">
						<h3 class="box-title">LIST ALL</h3>
					</div>
					<div class="box-body">
						<select name="searchType">
							<option value="n">-------------------------------</option>
							<option value="t" ${cri.searchType == 't'? 'selected':'' }>Title</option>
							<option value="c" ${cri.searchType == 'c'? 'selected':'' }>Content</option>
							<option value="w" ${cri.searchType == 'w'? 'selected':'' }>Writer</option>
							<option value="tc" ${cri.searchType == 'tc'? 'selected':'' }>Title OR Content</option>
							<option value="cw" ${cri.searchType == 'cw'? 'selected':'' }>Content OR Writer</option>
							<option value="tcw" ${cri.searchType == 'tcw'? 'selected':'' }>Title OR Content OR Writer</option>
						</select>
						<input type="text" name="keyword" id="keywordInput" value="${cri.keyword }">
						<button id="searchBtn">Search</button>
						<a href="register"><button id="newBoard">New Board</button></a>
					</div>
					<div class="box-body">
						<table class="table table-bordered">
							<thead>
								<tr>
									<th>BNO</th>
									<th>TITLE</th>
									<th>WRITER</th>
									<th>REGDATE</th>
									<th>VIEWCNT</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="item" items="${list }">
									<tr>
										<td style="width:10px;">${item.bno }</td>
										<td><a href="readPage${pageMaker.makeSearch(pageMaker.cri.page)}&bno=${item.bno }">${item.title }</a> [${item.replycnt}]</td>
										<td>${item.writer }</td>
										<fmt:formatDate value="${item.regdate }" pattern="yyyy-MM-dd a HH:mm" var="regdate"/>
										<td>${regdate}</td>
										<td style="width:40px;"><span class="badge bg-red">${item.viewcnt }</span></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					
					<div class="box-footer">
						<div class="text-center">
							<ul class="pagination">
							<c:if test="${pageMaker.prev }">
								<li><a href="listPage${pageMaker.makeSearch(pageMaker.startPage-1)}">&laquo;</a></li>
							</c:if>
							
							<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="idx">
								<li ${pageMaker.cri.page == idx? 'class=active' : '' }><a href="listPage${pageMaker.makeSearch(idx)}">${idx }</a></li>
							</c:forEach>
							
							<c:if test="${pageMaker.next }">
								<li><a href="listPage${pageMaker.makeSearch(pageMaker.endPage+1)}">&raquo;</a></li>
							</c:if>
							</ul>
						</div>
						
					</div>
				</div>
			</div>
		</div>
	</section>
	
	<script>
		$(function(){
			$("#searchBtn").click(function(){
				var searchType=$("select[name='searchType']").val();
				var keyword = $("input[name='keyword']").val();
				location.href="listPage${pageMaker.makeQuery(1)}&searchType="+searchType+"&keyword="+keyword;
			})
		})
		
	</script>
<%@ include file="../include/footer.jsp" %>