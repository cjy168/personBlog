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

import blog.entity.Message;
import blog.server.MessageServer;
import blog.server.impl.MessageServerImpl;

/**
 * 留言信息管理
 */
@WebServlet("/MessageController")
public class MessageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private HttpSession session=null;
	
	private MessageServer messageServer=new MessageServerImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置字符编码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		
		session=request.getSession();
		
		String action=request.getParameter("action");
		if("getmessagelist".equals(action)){
			getMessageList(request, response);
		}else if("addnewmessage".equals(action)){
			addNewMessage(request, response);
		}else if("addnewreply".equals(action)){
			addNewReply(request, response);
		}
		
		
		
	}
	
	protected void addNewReply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String messageid=request.getParameter("messageid");
		String reply=request.getParameter("reply");
		
		messageServer.addNewMessageByAdmin(messageid, reply);
		
		getMessageList(request, response);
	}
	
	protected void addNewMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid=request.getParameter("userid");
		String username=request.getParameter("username");
		String usermessage=request.getParameter("messagetext");
		
		Message message=new Message();
		message.setUserId(userid);
		message.setUsername(username);
		message.setMessage(usermessage);

		if(messageServer.addNewMessage(message)){
			getMessageList(request, response);
		}else{
			response.getWriter().print("<script>"
					+ "alert('由于你的不当言论被禁止留言！');"
					+ "window.history.go(-1);"
					+ "</script>");
		}
		
	
	}
	
	protected void getMessageList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Message> messageList=new ArrayList<>();
		messageList=messageServer.getAllMessageList();
		request.setAttribute("messageList", messageList);
		request.getRequestDispatcher("message.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
