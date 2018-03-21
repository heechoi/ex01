<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
										<td><a href="read?bno=${item.bno }">${item.title }</a></td>
										<td>${item.writer }</td>
										<fmt:formatDate value="${item.regdate }" pattern="yyyy-MM-dd a HH:mm" var="regdate"/>
										<td>${regdate}</td>
										<td style="width:40px;"><span class="badge bg-red">${item.viewcnt }</span></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</section>
<%@ include file="../include/footer.jsp" %>