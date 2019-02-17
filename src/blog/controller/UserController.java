package blog.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.realm.UserDatabaseRealm;

import com.google.gson.Gson;

import blog.entity.User;
import blog.server.UserServer;
import blog.server.impl.UserServerImpl;

/**
 * 用户信息管理
 */
@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private HttpSession session=null;
	
	private UserServer userServer=new UserServerImpl();
	
	private Gson gson=new Gson();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置中文编码
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//获取一个session
		session =request.getSession();
		
		//获取action行动操作
		String action=request.getParameter("action");
		//用户登录action=adminlogin
		if("adminlogin".equals(action)){
			adminLogin(request, response);
		}else if("userlogin".equals(action)){
			userLogin(request, response);
		}else if("getgoodfriendlist".equals(action)){
			getGoodFriendList(request, response);
		}else if("userregister".equals(action)){
			userRegister(request, response);
		}
	}
	
	protected void userRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("username");
		String email=request.getParameter("email");
		String blog=request.getParameter("blog");
		
		User user=new User();
		user.setName(username);
		user.setEmailAddress(email);
		user.setBlogAddress(blog);
		
		if(userServer.userRegister(user)){
			response.sendRedirect("login.jsp");
		}else{
			response.getWriter().print("<script>"
					+ "alert('你输入的昵称或者是邮箱已经被人占用了，请重新输入一个');"
					+ "window.history.go(-1);"
					+ "</script>");
		}
		
	}
	
	/**
	 * ajax效果，获取好友列表
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void getGoodFriendList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取列表
		List<User> userList=userServer.getFriendsList();
		
		String listJson=gson.toJson(userList);
		response.getWriter().print(listJson);
		
	}
	
	
	
	/**
	 * 博主登录
	 */
	protected void adminLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		
		//获取
		User admin=userServer.adminLogin(email, password);
		session.setAttribute("user", admin);
		
		response.sendRedirect("index.jsp");
	}
	
	/**
	 * 普通登录
	 */
	protected void userLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email");
		
		//获取
		User user=userServer.userLogin(email);
		session.setAttribute("user", user);
		
		response.sendRedirect("index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
