<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="main-container">
	<!-- 左メニューエリア -->
	<div class="menu-wrapper">
		<%@ include file="menu.jsp"%>
	</div>

	<!-- 右コンテンツエリア -->
	<div class="content-container">
		<h2>科目管理</h2>
		<a href="#">新規登録</a>
		<table>
			<tr>
				<th>科目コード</th>
				<th>科目名</th>
				<td>${subject.cd}</td>
				<td>${subject.name}</td>
			</tr>

		</table>
	</div>
</div>