<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src='<c:url value="/js/jquery-3.2.1.min.js"></c:url>'></script>
	
	
	
	<script type="text/javascript">
	 $(function(){alert("1");});
	 
	 
	 function check(){
	 $.ajax({
	 url:"<%=path%>/ssd_user/modifyUserDetail.action",
	  type:"post",
	  dataType:"json",
	  async:true,
	  contentType:"application/json;charset=utf-8",
	 // data:'{"user":[{"userid": null,"username": "唐衡","password": "123456789", "address": "湖南永州","phone": "18566403223","telephone": null,"createdate": null,"lastlogindate": null,"lastmodifydate": null,"identity_card": "431126200003198478","icon": "/WEB-INF/icons/qeqw.img","sex": "男","birthday": "2000-03-19","email": "2605253179@qq.com","usertype": null,"rank": null,"integral": 10}]}',
	  data:'{"userid":"18536251420","username":"唐衡","password":"123456789","address1":"湖南永州","phone":"18566403223","telephone":null,"createdate":null,"lastlogindate":null,"lastmodifydate":null,"identity_card":"431126200003198478","icon":"/WEB-INF/icons/qeqw.img","sex":"男","birthday":"2000-03-19","email":"2605253179@qq.com","usertype":null,"rank":null,"integral":10.0,"token":"3f45609d-bad7-4338-b1bd-4984c4859801"}',
	 success:function(data){
	
	alert(data);
	 }
	 
	 }
	 
	 
	 );
	 
	 }
	 
	 
	 
	 function ResetPassword(){
	 
	  $.ajax({
	 url:"<%=path%>/ssd_user/resetPassword.action",
	  type:"post",
	  dataType:"json",
	  async:true,
	  contentType:"application/json;charset=utf-8",
	 // data:'{"user":[{"userid": null,"username": "唐衡","password": "123456789", "address": "湖南永州","phone": "18566403223","telephone": null,"createdate": null,"lastlogindate": null,"lastmodifydate": null,"identity_card": "431126200003198478","icon": "/WEB-INF/icons/qeqw.img","sex": "男","birthday": "2000-03-19","email": "2605253179@qq.com","usertype": null,"rank": null,"integral": 10}]}',
	  data:'{"userid":"18536251420","password":"123456789","confirmpassword":"123456789" }',
	 success:function(data){
	
	alert(data);
	 }
	 });
	 }
	
	
	function sjb(){
	alert(1);
	}
	</script>
  </head>
  
  <body>
    This is my JSP page. <br>
    
    <h1 onclick="check()">唐衡66666666666</h1>
    
    <br/>
     <h1 onclick="ResetPassword()">唐衡222222222222</h1>
    <c:forEach  items="${errors }" var="error">
    ${error.defaultMessage}<br/>
    
    
    </c:forEach>
    
    <input type="file" name="filename" onchange="sjb()" /> 
    
  </body>
</html>
