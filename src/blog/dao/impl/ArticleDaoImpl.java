package blog.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import blog.dao.ArticleDao;
import blog.entity.Article;
import blog.entity.ArticleComment;
import blog.utils.DBUtil;

public class ArticleDaoImpl implements ArticleDao {

	private DBUtil dbUtil=new DBUtil();
	
	@Override
	public List<Article> getArticleListByTitle(String title) {
		String sql="select * from article  where title like ?";
		ResultSet rs=dbUtil.dataQuery(sql, "%"+title+"%");
		
		List<Article> articleList=new ArrayList<>();
		
		try {
			while(rs.next()){
				
				Article article =new Article();
				article.setId(rs.getString("id"));
				article.setTitle(rs.getString("title"));
				article.setBody(rs.getString("body"));
				article.setNum(Integer.parseInt(rs.getString("num")));
				article.setImage(rs.getString("image"));
				article.setDate(rs.getDate("date"));
				article.setAuthor(rs.getString("author"));
				
				articleList.add(article);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return articleList;
	}

	@Override
	public List<Article> getNewArticleList() {
		String sql="select * from article ORDER BY date DESC";
		ResultSet rs=dbUtil.dataQuery(sql);
		
		List<Article> articleList=new ArrayList<>();
		try {
			while(rs.next()){
				Article article =new Article();
				article.setId(rs.getString("id"));
				article.setTitle(rs.getString("title"));
				article.setBody(rs.getString("body"));
				article.setNum(Integer.parseInt(rs.getString("num")));
				article.setImage(rs.getString("image"));
				article.setDate(rs.getDate("date"));
				article.setAuthor(rs.getString("author"));
				
				articleList.add(article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return articleList;
	}

	@Override
	public List<Article> getLimitHotArticleList() {
		String sql="select * from article ORDER BY num DESC LIMIT 5";
		ResultSet oneRs=dbUtil.dataQuery(sql);
		
		List<Article> articleList=new ArrayList<>();
		try {
			while(oneRs.next()){
				Article article =new Article();
				article.setId(oneRs.getString("id"));
				article.setTitle(oneRs.getString("title"));
				article.setBody(oneRs.getString("body"));
				article.setNum(Integer.parseInt(oneRs.getString("num")));
				article.setImage(oneRs.getString("image"));
				article.setDate(oneRs.getDate("date"));
				article.setAuthor(oneRs.getString("author"));
				
				articleList.add(article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return articleList;
	}

	@Override
	public Article getArticleById(String id) {
		String sql="select * from article where id=? ";
		ResultSet rs=dbUtil.dataQuery(sql, id);
		Article article=null;
		try {
			if(rs.next()){
				 article =new Article();
				article.setId(rs.getString("id"));
				article.setTitle(rs.getString("title"));
				article.setBody(rs.getString("body"));
				article.setNum(Integer.parseInt(rs.getString("num")));
				article.setImage(rs.getString("image"));
				article.setDate(rs.getDate("date"));
				article.setAuthor(rs.getString("author"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return article;

	}
	
	/**
	 * 从rs结果集中获取article数据结果
	 * @param rs
	 * @return
	 * @throws SQLException 
	 */

		


	@Override
	public List<ArticleComment> getNewArticleCommentList(String articleId) {
		String sql="select * from article_comment where article_id =? ORDER BY date DESC";
		ResultSet rs=dbUtil.dataQuery(sql,articleId);
		
		List<ArticleComment> commentList=new ArrayList<>();
		try {
			while(rs.next()){
				ArticleComment comment=new ArticleComment();
				comment.setId(rs.getString("id"));
				comment.setArticleId(rs.getString("article_id"));
				comment.setBody(rs.getString("body"));
				comment.setDate(rs.getDate("date"));
				comment.setUserName(rs.getString("user_name"));
				
				commentList.add(comment);
			}
		} catch (SQLException e) {
	
			e.printStackTrace();
		}
		return commentList;
	}

	@Override
	public boolean addNewArticleComment(ArticleComment comment) {
		
		String sql="insert into article_comment(id,article_id,body,date,user_name)values(?,?,?,?,?)";
				
	    boolean b1=	dbUtil.dataUpdate(sql, comment.getId(),comment.getArticleId(),comment.getBody(),
				comment.getDate(),comment.getUserName());
		
	    boolean b2=addArticleCommentNum(comment.getArticleId());
		return  b1&&b2;	
		
	}

	@Override
	public boolean addNewArticle(Article article) {
		String sql="insert into article(id,title,body,num,image,date,author)"
				+ "values(?,?,?,?,?,?,?)";
		return dbUtil.dataUpdate(sql, article.getId(),article.getTitle(),article.getBody(),
				article.getNum(),article.getImage(),article.getDate(),article.getAuthor());
	}

	@Override
	public boolean addArticleCommentNum(String articleid) {
		String querysql="select num from article where id= ?";
		ResultSet rs=dbUtil.dataQuery(querysql, articleid);
		int count=0;
		try {
			if(rs.next()){
				count=rs.getInt("num");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		count+=1;
		String updateSQL="update article set num=? where id=?";
		
		return dbUtil.dataUpdate(updateSQL, count,articleid);
	}

	@Override
	public boolean delArticleById(String articleid) {
		String sql="delete from article where id=?";
		return dbUtil.dataUpdate(sql, articleid);
	}

	@Override
	public boolean updateArticle(Article article) {
		String sql="update article set title =? , body=?,image=? where id =? ";
		return dbUtil.dataUpdate(sql, article.getTitle(),article.getBody(),article.getImage(),article.getId());
	}

	
	
	
}
