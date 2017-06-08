<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<title>添加用户页面</title>

<!-- <meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page"> -->
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<script type="text/javascript">
$(function(){

 
  var zhi = ${user.sex};

 
 );

</script>
</head>

<body>
	<form action='<c:url value="/user/add_user.action"></c:url>' method="post" enctype="multipart/form-data">
		<table width="500" border="1" cellpadding="0" cellspacing="0">
			<tbody>
				<tr>
					<th scope="row">用户名称</th>
					<td><input type="text" name="username" value="${user.username }"></td>
				</tr>
				<tr>
					<th scope="row">用户密码</th>
					<td><input type="password" name="password" value="${user.password }"></td>
				</tr>
				<tr>
					<th scope="row">用户住址</th>
					<td><input type="text" name="address" value="${user.address }"></td>
				</tr>
				<tr>
					<th scope="row">手机号码</th>
					 <td><input type="text" name="phone" value="${user.phone }"></td>
				</tr>
				<tr>
					<th scope="row">固定号码</th>
					<td><input type="text" name="telephone" value="${user.telephone }"></td>
				</tr>
				<tr>
					<th scope="row">身份证号</th>
					<td><input type="text" name="identity_card" value="${user.identity_card }"></td>
				</tr>
				<tr>
					<th scope="row">用户头像</th>
					<td><input type="file" name="usericonfile" accept="image/jpeg"></td>
				</tr>
				<tr>
					<th scope="row">用户性别</th>
					<td><input type="radio" name="sex" value="男">男&nbsp;&nbsp;
						<input type="radio" name="sex" value="女">女
						</td>
				</tr>
				<tr>
					<th scope="row">用户生日</th>
					<td><input type="date" name="birthday" value="${user.birthday }"></td>
				</tr>
				<tr>
					<th scope="row">用户邮箱</th>
					<td><input type="email" name="email" value="${user.email }"></td>
				</tr>


				<tr>
					<td><input type="submit" value="注册"></td>
					<td><input type="reset" value="清除"></td>
				</tr>
			</tbody>
		</table>

	</form>
	
	<h1>错误信息显示</h1>
	 <c:forEach  items="${errors }" var="error">
    ${error.defaultMessage}<br/>
    
    
    
    
    
    </c:forEach>
    
    ${iconerror }
</body>
</html>
