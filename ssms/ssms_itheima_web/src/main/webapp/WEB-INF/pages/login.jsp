<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>管理员登录</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <script type="text/javascript">
    </script>
  </head>
  <body>


  <%--<%
	  String username="";
	  String pwd="";
	  String checked="";
	  Cookie[] cookies = request.getCookies();
	  for (int i = 1; i < cookies.length; i++) {
		 if ("username".equals(cookies[i].getName())){
		      username=cookies[i].getValue();
		      checked="checked";
		 }
		 if ("pwd".equals(cookies[i].getName())){
		      pwd= cookies[i].getValue();
		 }

	  }
	  request.setAttribute("username",username);
	  request.setAttribute("pwd",pwd);
	  request.setAttribute("checked",checked);
  %>
--%>
 <%
 /*编写java代码*/

	 Cookie[] cookies = request.getCookies();
	 String username=null;
	 String pwd=null;
	 String checked= null;
	 for (int i = 0; i < cookies.length; i++) {
		  if ("username".equals(cookies[i].getName())){
		       username= cookies[i].getValue();
		       checked="checked";
		  }
		  if ("pwd".equals(cookies[i].getName())){
		       pwd= cookies[i].getValue();
		  }
	 }
	 request.setAttribute("username",username);
	 request.setAttribute("pwd",pwd);
	 request.setAttribute("checked",checked);
 %>
  	<div class="container" style="width: 400px;">
  		<h3 style="text-align: center;">管理员登录</h3>
        <form action="user/login" method="post">
	      <div class="form-group">
	        <label for="user">用户名：</label>
	        <input type="text" name="username" class="form-control" value="${username}" id="user" placeholder="请输入用户名"/>
	      </div>
	      
	      <div class="form-group">
	        <label for="password">密码：</label>
	        <input type="password" name="pwd" class="form-control" value="${pwd}" id="password" placeholder="请输入密码"/>
	      </div>

			<div class="form-group">
				<label for="password">记住我：</label>
				<input type="checkbox" name="ck" class="form-control" value="1"  ${checked} id="ck"/>
			</div>
	      

	      <hr/>
	      <div class="form-group" style="text-align: center;">
	        <input class="btn btn btn-primary" type="submit" value="登录">
		  </div>
	  	</form>
		
		<!-- 出错显示的信息框 -->
	  	<div class="alert alert-warning alert-dismissible" role="alert">
		  <button type="button" class="close" data-dismiss="alert" >
		  	<span>&times;</span>
		  </button>
		   <strong>登录失败!</strong>
		</div>
  	</div>
  </body>
</html>