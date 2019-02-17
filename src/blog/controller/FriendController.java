package blog.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blog.entity.User;
import blog.server.FriendServer;
import blog.server.impl.FriendServerImpl;

/**
 *联系人管理
 */
@WebServlet("/FriendController")
public class FriendController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private HttpSession session=null;
	
	private FriendServer friendServer=new FriendServerImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置中文编码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		
		session=request.getSession();
		
		String action=request.getParameter("action");
		if("getallfriendslist".equals(action)){
			getAllFriendsList(request, response);
		}else if("markfriend".equals(action)){
			markFriend(request, response);
		}else if("shieldfriend".equals(action)){
			shieldFriend(request, response);
		}else if("delfriend".equals(action)){
			delFriend(request, response);
		}
		
	}

	protected void delFriend(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid=request.getParameter("userid");
		friendServer.delFriend(userid);
		getAllFriendsList(request, response);
	}
	
	protected void shieldFriend(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid=request.getParameter("userid");
		int flag=Integer.parseInt(request.getParameter("flag"));
		
		friendServer.shieldFriend(userid, flag);
		getAllFriendsList(request, response);
	}
	
	protected void markFriend(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid=request.getParameter("userid");
		int flag=Integer.parseInt(request.getParameter("flag"));
		
		friendServer.markFriend(userid, flag);
		getAllFriendsList(request, response);
		
	}
	
	protected void getAllFriendsList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<User> friendList=new ArrayList<>();
		friendList=friendServer.getAllUserList();
		request.setAttribute("friendList", friendList);
		request.getRequestDispatcher("friends.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
