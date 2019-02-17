<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
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
		<script type="text/javascript">
			
			$(function(){
				
				$("#div01").css("display","none");
			
				$("#button01").click(function(){
					$("#div02").css("display","none");
					$("#div01").show();
				});
				
				$("#button02").click(function(){
					$("#div01").css("display","none");
					$("#div02").show();
				});
			})
			
			
			
		</script>
	</head>
	<body>
		<div class="container">
			
			<div class="row">
				<div class="col-lg-4 col-lg-offset-4 diy_m">	
					<div id="div01">
						 <form class="form-signin" method="post" action="UserController?action=adminlogin">
						        <h2 align="center" class="form-signin-heading">博主登录</h2>
						        <div class="form-group">
						        	 <label for="inputEmail" ><font size="4">邮箱:</font></label>
						     		  <input type="text" id="inputEmail" class="form-control" placeholder="邮箱" name="email" >
						        </div>	        
						        <div class="form-group">
						        	  <label for="inputPassword" ><font size="4">密码:</font></label>
						       		<input type="password" id="inputPassword" class="form-control" placeholder="密码" name="password" >
						        
						        </div>
						        
						        <div class="form-group">
						        	<button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
						        </div>				        
					      </form>		
					</div>
					<div id="div02">
						 <form class="form-signin" method="post" action="UserController?action=userlogin">
						        <h2 align="center" class="form-signin-heading">朋友登录</h2>
						        <div class="form-group">
						        	 <label for="inputEmail1" ><font size="4">邮箱:</font></label>
						     		  <input type="text" id="inputEmail1" class="form-control" placeholder="邮箱" name="email">
						        </div>	        
						        
						        <div class="form-group">
						        	<button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
						        </div>				        
					      </form>		
					</div>
					<button id="button01" type="button" class="btn btn-primary">博主登录</button>	
					<button id="button02" type="button" class="btn btn-primary">朋友登录</button>	
				</div>			
			</div>
    </div>
	</body>
</html>
