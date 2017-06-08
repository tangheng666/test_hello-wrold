<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户详细页面</title>

<script type="text/javascript" src="<c:url value='/js/jquery-3.2.1.min.js'></c:url>"></script>
	
	<script type="text/javascript">
 
	 
	 function login(){
	  var username = "18566403223";
	  var token = "ca6b9806-9a0b-444f-8fc0-79148e4f65e7" ;
   
	 $.ajax({
	  type:"post",
      data: JSON.stringify({"userid":username,"token":token}),	 
      contentType:"application/json;charset=utf-8",
      url:"http://localhost:8080/Server_Manager_Code/ssd_user/showUserDetail.action",
      success:function(data){
      $("h1").text("状态码:"+data.retcode+"描述："+data.description+"Token:"+data.Token);
     //    alert(data);
      }
	 
	 });
	 
	 
	 }
	 
	
	</script>
</head>
<body>
<h1 onclick="login()">很厉害</h1>
</body>
</html>