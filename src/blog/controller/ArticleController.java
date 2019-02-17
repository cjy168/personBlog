package blog.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import blog.entity.Article;
import blog.entity.ArticleComment;
import blog.server.ArticleServer;
import blog.server.impl.ArticleServerImpl;


/**
 * Servlet implementation class ArticleController
 */
@WebServlet("/ArticleController")
public class ArticleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ArticleServer articleServer=new ArticleServerImpl();
	
	private HttpSession  session=null;
	
	private Gson gson=new Gson();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//中文设置
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		session=request.getSession();
		
		String action=request.getParameter("action");
		
		if("getarticlebytitle".equals(action)){
			getArticleByTitle(request, response);
		}else if("getnewarticle".equals(action)){
			getNewArticle(request, response);
		}else if("gethotarticle".equals(action)){
			getHotArticle(request, response);
		}else if("getarticlebyid".equals(action)){
			getArticleById(request, response);
		}else if("addnewcomment".equals(action)){
			addNewComment(request, response);
		}else if("getarticlelist".equals(action)){
			getArticleList(request, response);
		}else if("writearticle".equals(action)){
			writeArticle(request, response);
		}else if("addnewarticle".equals(action)){
			//addnewarticle(request, response);
		}else if("delarticle".equals(action)){
			delArticle(request, response);
		}else if("editarticle".equals(action)){
			editArticleToPage(request, response);
		}else if("editoldarticle".equals(action)){
			//editOldArticle(request, response);
		}
		
	}
	
	/*protected void editOldArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Article article =getArticleByFrom(request, response);
		
		articleServer.updateArticle(article);
		
		getArticleList(request, response);
	}*/

	
	/**
	 * 获取修改文章的内容
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void editArticleToPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String articleid=request.getParameter("articleid");
		
		//获取文章
		Article article=articleServer.getArticleById(articleid);
		request.setAttribute("article", article);
		request.setAttribute("edit", 0);
		request.getRequestDispatcher("articledetail.jsp").forward(request, response);
	}

	
	
	/**
	 * 删除文章
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void delArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String articleid=request.getParameter("articleid");
		//删除
		articleServer.delArticleById(articleid);
		
		//重新执行
		getArticleList(request, response);
	}
	
	
	/**
	 * 添加一篇新文章的内容
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	/*protected void addnewarticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取作者
		User admin= (User) session.getAttribute("user");
		String author=admin.getName();
		
		Article article=getArticleByFrom(request, response);
		article.setAuthor(author);
		
		articleServer.addNewArticle(article);
		//重新执行
		getArticleList(request, response);
	}*/
	
	/**
	 * 对于带有图片上传的表单进行数据获取封装
	 */
	/*protected Article getArticleByFrom(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//获取上传的文件名
		String name=null;
		
		//创建一个文件保存文件保存对象FileObject
		Article article =null;
		
		//创建一个工厂对象
		//字段分析的工厂对象
		DiskFileItemFactory dfif=new DiskFileItemFactory();		
		//创建一个文件上传解析器
		ServletFileUpload parser=new ServletFileUpload(dfif);	
		
		 * 使用 ServletFileUpload解析器解析上传的数据，
		 * 解析的结果是返回一个list<FileItem>集合
		 * 每一个FileItem对应的都是表单中每一个输入项
		 
		
		try {
			//把请求转换为list对象
			List<FileItem> items=parser.parseRequest(request);
		
			//对象不为空才执行
			if(items!=null){
				for (FileItem fileItem : items) {
					
					 * fileItem.isFormField()判断当前是否为普通的表单字段
					 * 如果是普通的表单字段，返回true 
					 * 是文件字段，返回false
					 
					if(!fileItem.isFormField()){
						//getName() 返回文件名
						String fileName=fileItem.getName();
						
						//用户选择文件上传的时候
						if(fileName != null && !fileName.equals("")){
							
							 * 包中的FilenameUtils是工具类
							 * FilenameUtils.getName(str)
							 * 表示从输入的路径中找到文件名（包括其后缀）
							 
							fileName=FilenameUtils.getName(fileName);
							
							
							 * 把文件路径确定在服务器中
							 * getServletContext().getRealPath("/upload")
							 * 获得当前运行文件在服务器上的绝对路径
							 
							String directoryPath= getServletContext().getRealPath("/upload");
							//创建文件目录
							File fileDirectory=new File(directoryPath);
							
							 * 如果文件目录不存在，则创建
							 
							if(!fileDirectory.exists()){
								//创建文件目录
								fileDirectory.mkdirs();
							}
							//写入文件中，将fileItem中上传的文件写入file中
							
							 * fileDirectory:文件的目录
							 * File.separator ：表示文件的分隔符“/”
							 * fileName ：文件名
							 
							fileItem.write(new File(fileDirectory+File.separator+fileName));
							//删除处理文件上传中生成的临时文件夹
							fileItem.delete();
							//获取文件的相对访问路径
							name=request.getContextPath()+"/upload/"+fileName;
						}
					}
				}
				
				article=new Article();
				//为了每个单独属性设置值
				
				 * getString(utf-8)获取普通表单中的value，并以utf-8的编码来获取值
				 
				//request.getParamter();替代了			
				article.setTitle(items.get(0).getString("utf-8"));
				article.setId(items.get(1).getString("utf-8"));
				article.setBody(items.get(3).getString("utf-8"));
				//设置图片
				article.setImage(name);
			}
		
		} catch (FileUploadException e) {			
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//返回值
		return article;
	}*/
	
	/**
	 * 写文章页面
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void writeArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//edit=1 表示写文章，edit=0表示修改文章
		request.setAttribute("edit", 1);
		request.getRequestDispatcher("articledetail.jsp").forward(request, response);
		
	}
	
	/**
	 * 获取全部的文章列表
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void getArticleList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取文章列表
		List<Article> articleList=articleServer.getNewArticleList();	
		request.setAttribute("articleList", articleList);
		request.getRequestDispatcher("writearticle.jsp").forward(request, response);
	}
	
	/**
	 * ajax效果，添加评论
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void addNewComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String body=request.getParameter("text");
		String articleId=request.getParameter("articleid");
		String userName=request.getParameter("username");
		
		//封装一个article 
		ArticleComment articleComment=new ArticleComment();
		articleComment.setBody(body);
		articleComment.setUserName(userName);
		articleComment.setArticleId(articleId);
		
		//添加comment
		articleServer.addNewArticleComment(articleComment);
	
		//获取全部的comment
		List<ArticleComment> commentlist=articleServer.getNewArticleCommentList(articleId);
		String listJson=gson.toJson(commentlist);
		response.getWriter().print(listJson);
		
		
	}
	
	
	protected void getArticleById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String articleid=request.getParameter("articleid");
		
		Article article=articleServer.getArticleById(articleid);
		request.setAttribute("article", article);
		
		//通过articleid获取文章评论
		List<ArticleComment> commentList=articleServer.getNewArticleCommentList(articleid);
		request.setAttribute("commentList", commentList);
		
		request.getRequestDispatcher("article.jsp").forward(request, response);
		
	}
	
	
	/**
	 * Ajax效果 获取最热文章
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void getHotArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Article> articleList=articleServer.getLimitHotArticleList();
		
		String listJson=gson.toJson(articleList);	
		//传输JSON字符串
		response.getWriter().print(listJson);
	}

	/**
	 * Ajax效果 获取最新文章
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void getNewArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Article> articleList=articleServer.getNewArticleList();
		String listJson=gson.toJson(articleList);	
		//传输JSON字符串
		response.getWriter().print(listJson);
	
	}
	
	/**
	 * Ajax效果
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void getArticleByTitle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String title=request.getParameter("title");
		
		List<Article> articleList=articleServer.getArticleListByTitle(title);
		String listJson=gson.toJson(articleList);	
		//传输JSON字符串
		response.getWriter().print(listJson);
		
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
