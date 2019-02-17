package blog.server;

import java.util.List;

import blog.entity.Article;
import blog.entity.ArticleComment;

public interface ArticleServer {
	/**
	 * 通过文章标题来获取文章列表
	 * @param title
	 * @return
	 */
	public List<Article>  getArticleListByTitle(String title);
	
	/**
	 * 获取最近时间获取文章
	 * @return
	 */
	public List<Article> getNewArticleList();
	
	/**
	 * 获取前五评论最多的文章
	 * @return
	 */
	public List<Article> getLimitHotArticleList();
	
	/**
	 * 通过ID来获取文章内容
	 * @param id
	 * @return
	 */
	public Article getArticleById(String id);
	
	/**
	 * 获取新的文章评论
	 * @return
	 */
	public   List<ArticleComment>  getNewArticleCommentList(String articleId);
	
	/**
	 * 添加一条新的评论
	 * @return
	 */
	public boolean addNewArticleComment(ArticleComment comment);
	
	
	/**
	 * 添加一条新的记录
	 * @param article
	 * @return
	 */
	public boolean addNewArticle(Article article);
	
	/**
	 * 删除文章通过id
	 * @param articleid
	 * @return
	 */
	public boolean delArticleById(String articleid);
	
	/**
	 * 修改文章内容
	 * @param article
	 * @return
	 */
	public boolean updateArticle(Article article);
	
	
}
