package blog.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import blog.dao.FriendDao;
import blog.entity.User;
import blog.utils.DBUtil;
/**
 * 
 * @author ZHM
 * 
 * stranger 
 * 好友标记：1
 * 非好友标记：2
 * 屏蔽标记：3
 * 非屏蔽标记：6
 * 
 * 好友屏蔽：4
 * 好友非屏蔽：7
 * 非好友屏蔽：5
 * 非好友非屏蔽：8 默认值
 * 
 *
 */
public class FriendDaoImpl implements FriendDao {

	private DBUtil dbutil = DBUtil.newInstance();

	@Override
	public List<User> getAllUserList() {
		String sql = "select * from user where type=0 order by stranger desc";
		ResultSet rs = dbutil.dataQuery(sql);
		List<User> userList = new ArrayList<>();

		try {
			while (rs.next()) {
				User user = new User();
				user.setName(rs.getString("name"));
				user.setBlogAddress(rs.getString("blog_address"));
				user.setEmailAddress(rs.getString("email_address"));
				user.setPassword(rs.getString("password"));
				user.setType(Integer.parseInt(rs.getString("type")));
				user.setStranger(Integer.parseInt(rs.getString("stranger")));
				user.setId(rs.getString("id"));
				
				userList.add(user);
			}
		} catch (SQLException e) {
		
			e.printStackTrace();
		}

		return userList;
	}

	@Override
	public boolean markFriend(String userid,int shield) {
		String sql="update user set stranger=? where id=?";
		return dbutil.dataUpdate(sql, shield,userid);
	}

	@Override
	public boolean delFriend(String userid) {
		String sql="selete from user where id=?";
		return dbutil.dataUpdate(sql, userid);
	}

	@Override
	public boolean shieldFriend(String userid, int friend) {
		String sql="update user set stranger=? where id=?";
		return dbutil.dataUpdate(sql, friend,userid);
	}

}
