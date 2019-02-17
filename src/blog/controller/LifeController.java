package blog.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.entity.Life;
import blog.server.LifeServer;
import blog.server.impl.LifeServerImpl;

/**
 * 生活随笔
 */
@WebServlet("/LifeController")
public class LifeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private LifeServer lifeServer=new LifeServerImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置中文字符编码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		
		String action=request.getParameter("action");
		
		if("getalllife".equals(action)){
			getAllLife(request, response);
		}else if("addnewlife".equals(action)){
			addNewLife(request, response);
		}else if("dellife".equals(action)){
			delFife(request, response);
		}
		
		
		
	}
	
	protected void delFife(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String lifeid=request.getParameter("lifeid");
		
		lifeServer.delLifeById(lifeid);
		
		getAllLife(request, response);
	}

	protected void addNewLife(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fifeBody=request.getParameter("lifetext");
		
		Life life=new Life();
		life.setBody(fifeBody);
		
		lifeServer.addNewLife(life);
		
		getAllLife(request, response);
		
	}
	
	protected void getAllLife(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Life> lifeList=lifeServer.getNewLifeList();
		request.setAttribute("lifeList", lifeList);
		request.getRequestDispatcher("life.jsp").forward(request, response);

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
