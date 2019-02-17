package blog.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import blog.entity.Article;
import blog.server.ArticleServer;
import blog.server.impl.ArticleServerImpl;

/**
 * 专门用于修改文章
 */
@WebServlet("/EditArticle")
public class EditArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ArticleServer articleServer = new ArticleServerImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Article article= getFileObject(request, response);
		
		articleServer.updateArticle(article);
		
		List<Article> articleList=articleServer.getNewArticleList();	
		request.setAttribute("articleList", articleList);
		request.getRequestDispatcher("writearticle.jsp").forward(request, response);
		
	}

	/**
	 * 获取文件对象，并获取文件的图片
	 */
	protected Article getFileObject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 先判断表单中是否设置了enctype属性，
		 * enctype属性规定在发送到服务器之前应该如何对表单数据进行编码
		 * 对字符编码，在使用包含文件上传控件的表单时候，必须要包含找个属性
		 */
		boolean isMultipart=ServletFileUpload.isMultipartContent(request);
		//判断是否包含属性
		if(!isMultipart){
			throw new RuntimeException("请检查表单中是否包含enctype属性");
		}
		//获取上传的文件名
		String name=null;
		
		//创建一个文件保存文件保存对象FileObject
		Article fileObj=null;
		
		//创建一个工厂对象
		//字段分析的工厂对象
		DiskFileItemFactory dfif=new DiskFileItemFactory();
		
		//创建一个文件上传解析器
		ServletFileUpload parser=new ServletFileUpload(dfif);
		
		/*
		 * 使用 ServletFileUpload解析器解析上传的数据，
		 * 解析的结果是返回一个list<FileItem>集合
		 * 每一个FileItem对应的都是表单中每一个输入项
		 */
		
		try {
			//把请求转换为list对象
			List<FileItem> items=parser.parseRequest(request);
			//对象不为空才执行
			if(items!=null){
				for (FileItem fileItem : items) {
					/*
					 * fileItem.isFormField()判断当前是否为普通的表单字段
					 * 如果是普通的表单字段，返回true 
					 * 是文件字段，返回false
					 */
					if(!fileItem.isFormField()){
						//getName() 返回文件名
						String fileName=fileItem.getName();
						
						//用户选择文件上传的时候
						if(fileName != null && !fileName.equals("")){
							/*
							 * 包中的FilenameUtils是工具类
							 * FilenameUtils.getName(str)
							 * 表示从输入的路径中找到文件名（包括其后缀）
							 */
							fileName=FilenameUtils.getName(fileName);
							
							/*
							 * 把文件路径确定在服务器中
							 * getServletContext().getRealPath("/upload")
							 * 获得当前运行文件在服务器上的绝对路径
							 */
							String directoryPath= getServletContext().getRealPath("/upload");
							//创建文件目录
							File fileDirectory=new File(directoryPath);
							/*
							 * 如果文件目录不存在，则创建
							 */
							if(!fileDirectory.exists()){
								//创建文件目录
								fileDirectory.mkdirs();
							}
							//写入文件中，将fileItem中上传的文件写入file中
							/*
							 * fileDirectory:文件的目录
							 * File.separator ：表示文件的分隔符“/”
							 * fileName ：文件名
							 */
							fileItem.write(new File(fileDirectory+File.separator+fileName));
							//删除处理文件上传中生成的临时文件夹
							fileItem.delete();
							//获取文件的相对访问路径
							name=request.getContextPath()+"/upload/"+fileName;
						}
					}
				}
				
				//new一个fileObject对象
				fileObj=new Article();
				//为了每个单独属性设置值
				/*
				 * getString(utf-8)获取普通表单中的value，并以utf-8的编码来获取值
				 */
				//request.getParamter();替代了
				//System.out.println(items.get(0).getString("utf-8"));
				//System.out.println(items.get(1).getString("utf-8"));
				//System.out.println(items.get(3).getString("utf-8"));
				//System.out.println(name);
				fileObj.setTitle( items.get(0).getString("utf-8") );
				fileObj.setId( items.get(1).getString("utf-8")  );
				
				String oldImage=items.get(2).getString("utf-8");
				if(name!=null){
					fileObj.setImage( name );
				}else{
					fileObj.setImage( oldImage );
				}
						
				fileObj.setBody(items.get(4).getString("utf-8")  );
				
				//设置图片
				//fileObj.setFilePath(name);
			}
		
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//返回值
		return fileObj;
		
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
