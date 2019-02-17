package blog.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import blog.dao.UserDao;
import blog.entity.User;
import blog.utils.DBUtil;

public class UserDaoImpl implements UserDao {

	private DBUtil dbUtil=DBUtil.newInstance();
	
	@Override
	public User adminLogin(String email, String password) {
		String sql="select * from user where email_address =? and password =? ";
		ResultSet rs=dbUtil.dataQuery(sql, email,password);
		User admin=new User();
		try {
			if(rs.next()){
				admin.setId(rs.getString("id"));
				admin.setName(rs.getString("name"));
				admin.setBlogAddress(rs.getString("blog_address"));
				admin.setEmailAddress(rs.getString("email_address"));
				admin.setPassword(rs.getString("password"));
				admin.setType(Integer.parseInt(rs.getString("type")));
				admin.setStranger(Integer.parseInt(rs.getString("stranger")));
			}
		} catch (SQLException e) {		
			e.printStackTrace();
		}
		return admin;
	}

	@Override
	public User userLogin(String email) {
		String sql="select * from user where email_address =? ";
		ResultSet rs=dbUtil.dataQuery(sql, email);
		
		User user=new User();
		try {
			if(rs.next()){
				user.setId(rs.getString("id"));
				user.setName(rs.getString("name"));
				user.setBlogAddress(rs.getString("blog_address"));
				user.setEmailAddress(rs.getString("email_address"));
				user.setPassword(rs.getString("password"));
				user.setType(Integer.parseInt(rs.getString("type")));
				user.setStranger(Integer.parseInt(rs.getString("stranger")));
			}
		} catch (SQLException e) {		
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public List<User> getFriendsList() {
		String sql="select * from user where stranger =7;";
		ResultSet rs=dbUtil.dataQuery(sql);
		
		List<User> userList=new ArrayList<>();
		try {
			while(rs.next()){
				User user=new User();
				user.setId(rs.getString("id"));
				user.setName(rs.getString("name"));
				user.setBlogAddress(rs.getString("blog_address"));
				user.setEmailAddress(rs.getString("email_address"));
				user.setPassword(rs.getString("password"));
				user.setType(Integer.parseInt(rs.getString("type")));
				user.setStranger(Integer.parseInt(rs.getString("stranger")));
				
				userList.add(user);
			}
		} catch (SQLException e) {		
			e.printStackTrace();
		}
		return userList;
	}

	@Override
	public boolean userRegister(User user) {
		String findSql="select * from user where name=? or email_address=?";
		ResultSet rs=null;
		rs=dbUtil.dataQuery(findSql, user.getName(),user.getEmailAddress());
		
		try {
			if(rs.next()){
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String insertsql="insert into user(id,name,blog_address,email_address,type,stranger)"
				+ "values(?,?,?,?,?,?)";
		return dbUtil.dataUpdate(insertsql, user.getId(),user.getName(),user.getBlogAddress(),
				user.getEmailAddress(),user.getType(),user.getStranger());
	}

}
