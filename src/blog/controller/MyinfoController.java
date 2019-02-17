package blog.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blog.entity.Me;
import blog.server.MyinfoServer;
import blog.server.impl.MyinfoServerImpl;

/**
 * 个人信息管理
 */
@WebServlet("/MyinfoController")
public class MyinfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private HttpSession session=null;
	private MyinfoServer myinfoServer=new MyinfoServerImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置字符编码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		
		session=request.getSession();
		
		String action=request.getParameter("action");
		if("getmyinfo".equals(action)){
			getMyinfo(request, response);
		}else if("updatemyinfo".equals(action)){
			updateMyInfo(request, response);
		}
		
	}

	protected void updateMyInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Me me=myinfoServer.getMeinfo();
		
		//获取修改的信息
		String info=request.getParameter("info");
		String hobby=request.getParameter("hobby");
		String qq=request.getParameter("qq");
		String email=request.getParameter("email");
		String description=request.getParameter("description");
		String id =request.getParameter("id");
		String userId =request.getParameter("userid");
		
		me.setId(id);
		me.setUserId(userId);
		me.setInfo(info);
		me.setHobby(hobby);
		me.setQq(qq);
		me.setEmail(email);
		me.setDescription(description);
		
		myinfoServer.updateMeinfo(me);
		
		request.setAttribute("me", me);
		request.getRequestDispatcher("aboutme.jsp").forward(request, response);
	}
	
	protected void getMyinfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Me me=myinfoServer.getMeinfo();
		request.setAttribute("me", me);
		request.getRequestDispatcher("aboutme.jsp").forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
