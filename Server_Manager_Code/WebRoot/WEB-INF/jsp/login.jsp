<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>登录页面</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<c:url value='/js/jquery-3.2.1.min.js'></c:url>"></script>
	
	<script type="text/javascript">
	 $(function(){
	   
	 
	 });
	 
	 function login(){
	  var username = $("input[name='userid']").val();
	  var password =$("input[name='password']").val();
  
	 if(username=="" || password==""){
	 alert("用户名或密码不能为空!");
	 return ;
	 }
	 $.ajax({
	  type:"post",
      data:'{"userid":"'+username+'"'+',"password":"'+password+'"'+'}',	 
      contentType:"application/json;charset=utf-8",
      url:"http://localhost:8080/Server_Manager_Code/ssd_user/userLogin.action",
      success:function(data){
      $("h1").text("状态码:"+data.retcode+"描述："+data.description+"Token:"+data.Token);
     //    alert(data);
      }
	 
	 });
	 
	 
	 }
	 
	
	</script>

  </head>
  
  <body>
      
            <h1></h1>
            <br/>
            <h2>用户登录</h2>
        <%--     <form action='<c:url value="/ssd_user/quick_register.action"></c:url>' method="post" enctype="application/json;charset=UTF-8"> --%>
               用户名:<input type="text" name="userid" ><br/>
               密&nbsp;&nbsp;码:<input type="password" name="password"><br/>
               
               
                           
          <input type="submit"  value="登录" onclick="login()">-------------<button>退出</button>           
         <!--       </form> -->
               
                     
     
   
  </body>
</html>
