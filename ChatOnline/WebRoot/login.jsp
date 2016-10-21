<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

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
	<script type="text/javascript">
	function check(){
	var name=document.getElementById(name);
	var pass=document.getElementById(pass);
	var errStr="";
	if(name.value==""||name.value==null){
	errStr+="用户名不能为空\n";
		}
     if(pass.value==""||pass.value=="null"){
     errStr+="密码不能为空";
     }
     if(errStr==""||errStr=="null"){
     return true;
     }
     alert(errStr);
     return false;
	
	}
	document.getElementById("loginForm").onsubmit=check;
	
	</script>
  </head>
  
  <body>
    <form name="form2" id="loginForm" method="post" action="/log.do">
    请输入用户名和密码登陆<br>
    用户名：<input name="name" id="name" type="text"><br>
    密 码：<input name="pass" id="pass" type="text"><br>
    <input value="登陆" type="submit" name="submit" ><br>
     <a href="reg.jsp">注册新用户</a>
    </form>
  </body>
</html>
