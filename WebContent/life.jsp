<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="utf-8" />
		<title></title>
		<link rel="stylesheet" href="css/bootstrap.min.css" />
		<script type="text/javascript" src="js/jquery.min.js" ></script>
		<script type="text/javascript" src="js/bootstrap.min.js" ></script>
		<link rel="stylesheet" href="css/my_css.css" />
		
			<script type="text/javascript">
			$(function(){
				
				//获取时间最新的文章
				$.ajax({
					type:"post",
					url:"ArticleController",
					data:{
						"action":"getnewarticle",
						"time":new Date()
					},
					dataType:"json",
					success:function(result){
						var list=eval(result);
						var str="";
						for(var i=0;i<list.length;i++){
							
							str+="<div class='row MY_divbordertop'>"+
									"<div class='col-lg-5'>"+
										"<a href='ArticleController?action=getarticlebyid&articleid="+ list[i].id+"' class='thumbnail'>"+
						      			"<img height='120px' width='180px' src='"+list[i].image+"' alt='无法加载图片'>"+
						   				" </a>"+
								  "</div>"+
								"<div class='col-lg-7'>"+
									"<h2>"+list[i].title+"</h2>"+
									"<p class='MY_fontSize3'>"+
									list[i].summary+"...<a href='ArticleController?action=getarticlebyid&articleid="+list[i].id+" '>[详情]</a>"+
									"</p>"+
								"<hr class='My_hr2' />"+
								"<div>"+
								"<span class='glyphicon glyphicon-time' aria-hidden='true'>&nbsp;"+list[i].date+
								"&nbsp;"+
								"<span class='glyphicon glyphicon-pencil' aria-hidden='true'>&nbsp;"+list[i].num+"条评论"+	
								"</div>"+			
								"</div>"+
							"</div>";
				
						}
						$("#newarticle").append(str);
						
					}
					
				});
				
				//获取评论最多的文章前5
				$.ajax({
					
					type:"post",
					url:"ArticleController",
					data:{
						"action":"gethotarticle",
						"time":new Date()
					},
					dataType:"json",
					success:function(result){
						var list=eval(result);
						var str="";
						for(var i=0;i<list.length;i++){
							str+="<tr>"+			
								"<td><span class='glyphicon glyphicon-menu-right' aria-hidden='true'></span>"+
								"<a href='ArticleController?action=getarticlebyid&articleid="+list[i].id+"'>"+list[i].title+"</a>"+
								"</td>"+
								"</tr>";	
						}
						$("#hotarticle").append(str);
						
					}
				});
				
				//获取好友联系人
				$.ajax({
					type:"post",
					url:"UserController",
					data:{
						"action":"getgoodfriendlist",
						"time":new Date()
					},
					dataType:"json",
					success:function(result){
						var list=eval(result);
						var str="";
						for(var i=0;i<list.length;i++){
							str+="<tr>"+
							"<td><span class='glyphicon glyphicon-eye-open' aria-hidden='true'></span>"+
							"<a href='https://"+list[i].blogAddress+"'>"+list[i].name+"的博客</a>"+
							"</td>"+
							"</tr>";
						}
						$("#goodfriend").append(str);
						
					}
					
				});
				
				
			});
		
		
		</script>
		
		<script type="text/javascript">
			$(function(){
				$("#button01").click(function(){
					var title=$("#input01").val();
					
					$.ajax({
						type:"post",
						url:"ArticleController",
						data:{
							"action":"getarticlebytitle",
							"title":title,
							"time":new Date()
						},
						dataType:"json",
						success:function(result){
							var list=eval(result);
							var str="";
							for(var i=0;i<list.length;i++){
								
								str+="<div class='row MY_divbordertop'>"+
										"<div class='col-lg-5'>"+
											"<a href='ArticleController?action=getarticlebyid&articleid="+ list[i].id+"' class='thumbnail'>"+
							      			"<img height='120px' width='180px' src='"+list[i].image+"' alt='无法加载图片'>"+
							   				" </a>"+
									  "</div>"+
									"<div class='col-lg-7'>"+
										"<h2>"+list[i].title+"</h2>"+
										"<p class='MY_fontSize3'>"+
										list[i].summary+"...<a href='ArticleController?action=getarticlebyid&articleid="+list[i].id+" '>[详情]</a>"+
										"</p>"+
									"<hr class='My_hr2' />"+
									"<div>"+
									"<span class='glyphicon glyphicon-time' aria-hidden='true'>&nbsp;"+list[i].date+
									"&nbsp;"+
									"<span class='glyphicon glyphicon-pencil' aria-hidden='true'>&nbsp;"+list[i].num+"条评论"+	
									"</div>"+			
									"</div>"+
								"</div>";
					
							}
							if(str==""){
								alert("没有相应的搜索内容，请重新输入搜索内容！");
								window.location.reload();
							}
							
							$("#newarticle").empty();
							$("#newarticle").append(str);
							
						}
						
					});
					
					
				});
				
				
			});
		
		
		</script>
		
	</head>
	<body>
		<nav class="navbar navbar-default MY_fontSize1 navbar-inverse">
			  <div class="container-fluid">
			    <!-- Brand and toggle get grouped for better mobile display -->
			    <div class="navbar-header">
			    	
			      <a class="navbar-brand active" href="index.jsp">老张头的博客</a>
			    </div>
			
			    <!-- Collect the nav links, forms, and other content for toggling -->
			    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			      <ul class="nav navbar-nav">
			        <li ><a href="LifeController?action=getalllife"><span class="glyphicon glyphicon-tree-deciduous" aria-hidden="true"></span>&nbsp;生活随笔 </a></li>
			       
			       <li><a href="MessageController?action=getmessagelist"><span class="glyphicon glyphicon-bullhorn" aria-hidden="true">&nbsp;留言板</a></li>
			       	
			       	<li><a href="MyinfoController?action=getmyinfo"> <span class="glyphicon glyphicon-hand-right" aria-hidden="true">&nbsp;关于我</a></li>
			      
			      	<c:if test="${user.type==1}">
			      		<!-- 博主才有的内容 -->
			      		<li><a href="ArticleController?action=getarticlelist"> <span class="glyphicon glyphicon-pencil" aria-hidden="true">&nbsp;写文章</a></li>
			       		<li><a href="FriendController?action=getallfriendslist"> <span class="glyphicon glyphicon-star-empty" aria-hidden="true">&nbsp;联系人</a></li>
			      	</c:if>
			      	
			       
			      </ul>
			       <form class="navbar-form navbar-left" >
			        <div class="form-group">
			          <input id="input01" type="text" class="form-control" placeholder="搜索文章" name="title">
			        </div>
			        
			        <button id="button01" type="button" class="btn btn-default"><span class="glyphicon glyphicon-search" aria-hidden="true"></button>
			      </form>
			      <ul class="nav navbar-nav navbar-right">
			       	
			       	<c:if test="${empty user }">
			       			<li><a href="login.jsp"> &nbsp;登录</a></li>
			       	</c:if>
			       	<c:if test="${!(empty user) }">
			       		<li> <a href="javascript:void(0);">&nbsp;欢迎！&nbsp;${user.name }</a></li>
			       	</c:if>
			       	<li><a href="register.jsp">&nbsp;注册</a></li>
			      </ul>
			    </div><!-- /.navbar-collapse -->
			  </div><!-- /.container-fluid -->
			</nav>
		<!-- 容器 -->	
		<div class="container">
			<div class="row">
				<div class="col-lg-8">
				<h1>&nbsp;生活随笔&nbsp;<small>&nbsp;&nbsp;&nbsp;用心记录生活中的点滴。</small></h1>
				<hr class="My_hr1"/>
				
				<c:if test="${user.type ==1 }">
				<!-- 是要被隐藏的 -->
				<div class="row MY_divbordertop" >
					<h2>个人随笔</h2>
					<form action="LifeController?action=addnewlife" method="post">				  
					  <div class="form-group">  
					    <textarea  name="lifetext"  class="MY_textarea"  id="exampleInputFile" cols="100" rows="3"></textarea>				    
					  </div>
					  <div class="form-group">
					    <button type="submit" class="btn btn-success">记录一下</button>
					  </div>
					</form>
				</div>
				
				</c:if>
	
				<c:forEach var="life" items="${lifeList }">
				
						<!-- 遍历的每一行 -->
						<div class="row MY_divbordertop" >
							<div class="MY_divborder">
								<h4><span class="glyphicon glyphicon-calendar" aria-hidden="true">&nbsp;${life.date }</h4>
								
								<p>
										${life.body }
								</p>
								
								<c:if test="${user.type == 1 }">
									<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
									<a href="LifeController?action=dellife&lifeid=${life.id }">删除</a>
								</c:if>
								
							</div>	
						</div>
				
				
				</c:forEach>

				</div>
				
				
				<div class="col-lg-4">
					<table class="table MY_fontSize2">
						<tbody>
							<tr>	
								<td><span class="glyphicon glyphicon-fire" aria-hidden="true">&nbsp;热门文章</td>
								
							</tr>
							<table class="table MY_fontSize3" id="hotarticle">
								
							</table>
						</tbody>
					</table>
					<br/>
					
					<table class="table MY_fontSize2">
						<tbody>
							<tr>	
								<td><span class="glyphicon glyphicon-send" aria-hidden="true">&nbsp;友情连接</td>
								
							</tr>
							<table class="table MY_fontSize3" id="goodfriend">
								
							</table>
						</tbody>
					</table>
				</div>
			</div>
			
		</div>	
	</body>
</html>