<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title>登录页面</title>
		<link rel="stylesheet" href="css/bootstrap.min.css" />
		<script type="text/javascript" src="js/jquery.min.js" ></script>
		<script type="text/javascript" src="js/bootstrap.min.js" ></script>
		<style type="text/css">
			.diy_m{
				margin-top: 100px;
				
			}
			
			
		</style>
	</head>
	<body>
		<div class="container">
			
			<div class="row">
				<div class="col-lg-4 col-lg-offset-4 diy_m">					
					 <form class="form-signin" method="post" action="UserController?action=userregister">
				        <h2 align="center" class="form-signin-heading">欢迎注册</h2>
				        <div class="form-group">
				        	 <label for="inputEmail" ><font size="4">昵称:</font></label>
				     		  <input name="username" type="text" id="inputEmail" class="form-control" placeholder="帐户名" required="" autofocus="">
				        </div>	        
				         <div class="form-group">
				        	  <label for="inputPassword" ><font size="4">电子邮箱:</font></label>
				       		<input name="email" type="text" id="inputPassword" class="form-control" placeholder="邮箱" required="">
				        
				        </div>
				         <div class="form-group">
				        	  <label for="inputPassword" ><font size="4">博客网址:</font></label>
				       		<input name="blog" type="text" id="inputPassword" class="form-control" placeholder="博客" required="">
				        
				        </div>
				        
				        <div class="form-group">
				        	<button class="btn btn-lg btn-primary btn-block" type="submit">注册</button>
				        </div>				        
			      </form>				
				</div>			
			</div>
    </div>
	</body>
</html>
