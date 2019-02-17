package blog.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import blog.dao.MyinfoDao;
import blog.entity.Me;
import blog.utils.DBUtil;

public class MyinfoDaoImpl implements MyinfoDao{

	private DBUtil dbutil=DBUtil.newInstance();
	
	@Override
	public Me getMeinfo() {
		String sql="select * from myinfo";
		ResultSet rs=dbutil.dataQuery(sql);
		
		Me me=new Me();
		try {
			if(rs.next()){
				me.setId(rs.getString("id"));
				me.setInfo(rs.getString("info"));
				me.setHobby(rs.getString("hobby"));
				me.setQq(rs.getString("qq"));
				me.setEmail(rs.getString("email"));
				me.setDescription(rs.getString("description"));
				me.setUserId(rs.getString("userid"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return me;
	}

	@Override
	public boolean updateMeinfo(Me my) {
		String updateSql="update myinfo set info=?,hobby=?,qq=?,email=?,description=?,userid=?"
				+ "where id=?";
		
		String emailChangeSql="update user set email_address=? where id=?";
		boolean b1=dbutil.dataUpdate(updateSql, my.getInfo(),my.getHobby(),my.getQq(),my.getEmail(),my.getDescription(),
				my.getUserId(),my.getId());
		
		boolean b2=dbutil.dataUpdate(emailChangeSql, my.getEmail(),my.getUserId());
		
		return b1&&b2;
	}

}
