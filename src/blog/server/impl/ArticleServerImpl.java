package blog.server.impl;

import java.util.List;

import blog.dao.ArticleDao;
import blog.dao.impl.ArticleDaoImpl;
import blog.entity.Article;
import blog.entity.ArticleComment;
import blog.server.ArticleServer;
import blog.utils.Util;
import sun.java2d.pipe.SpanShapeRenderer.Simple;

public class ArticleServerImpl implements ArticleServer {

	private ArticleDao articleDao=new ArticleDaoImpl(); 
	
	@Override
	public List<Article> getArticleListByTitle(String title) {
		return articleDao.getArticleListByTitle(title);
	}

	@Override
	public List<Article> getNewArticleList() {
		return articleDao.getNewArticleList();
	}

	@Override
	public List<Article> getLimitHotArticleList() {
		return articleDao.getLimitHotArticleList();
	}

	@Override
	public Article getArticleById(String id) {
		return articleDao.getArticleById(id);
	}

	@Override
	public List<ArticleComment> getNewArticleCommentList(String articleId) {

		return articleDao.getNewArticleCommentList(articleId);
	}

	@Override
	public boolean addNewArticleComment(ArticleComment comment) {
		comment.setId(Util.createID());
		comment.setDate(Util.createNowDate());
		return articleDao.addNewArticleComment(comment);
	}

	@Override
	public boolean addNewArticle(Article article) {
		article.setId(Util.createID());
		article.setNum(0);
		article.setDate(Util.createNowDate());
		//System.out.println(Util.createNowDate());
		//System.out.println("获取时间："+article.getDate());
		
		return articleDao.addNewArticle(article);
	}

	@Override
	public boolean delArticleById(String articleid) {
	
		return articleDao.delArticleById(articleid);
	}

	@Override
	public boolean updateArticle(Article article) {
		
		return articleDao.updateArticle(article);
	}

}
