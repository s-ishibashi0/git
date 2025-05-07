<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
	function togglePasswordVisibility() {
		const passwordInput = document.getElementById("password");
		const checkbox = document.getElementById("showPasswordCheckbox");
		if (checkbox.checked) {
			passwordInput.type = "text";
		} else {
			passwordInput.type = "password";
		}
	}
</script>
<c:import url="../base.jsp" />
<h2>ログイン</h2>
<form action="LoginAction" method="post">
	<input type="text" id="id" name="id" value="${id}"
		placeholder="半角でご入力ください" style="ime-mode: disabled;" required
		maxlength="10"> <input type="password" id="password"
		name="password" placeholder="30文字以内の半角英数字でご入力ください" maxlength="30"
		style="ime-mode: disabled;" required> <input type="checkbox"
		id="showPasswordCheckbox" name="chk_d_ps"
		onclick="togglePasswordVisibility()"> <label
		for="showPasswordCheckbox">パスワードを表示</label> <input type="button"
		name="login" value="ログイン">
</form>