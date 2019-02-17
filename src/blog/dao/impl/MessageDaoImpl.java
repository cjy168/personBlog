package blog.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import blog.dao.MessageDao;
import blog.entity.Message;
import blog.utils.DBUtil;
import blog.utils.Util;

public class MessageDaoImpl implements MessageDao {

	private DBUtil dbutil=DBUtil.newInstance();
	
	@Override
	public List<Message> getAllMessageList() {
		String sql="select * from messagelist order by date";
		ResultSet rs=dbutil.dataQuery(sql);
		List<Message> messagelist=new ArrayList<>();
		
		try {
			while(rs.next()){
				Message message=new Message();
				message.setId(rs.getString("id"));
				message.setUserId(rs.getString("userid"));
				message.setUsername(rs.getString("username"));
				message.setMessage(rs.getString("message"));
				message.setDate(rs.getDate("date"));
				message.setReply(rs.getString("reply"));
				
				messagelist.add(message);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return messagelist;
	}

	@Override
	public boolean addNewMessageByAdmin(String messageid, String text) {
		String sql="update messagelist set reply =? where id=?";
		return dbutil.dataUpdate(sql,text,messageid);
	}

	@Override
	public boolean addNewMessage(Message message) {
		message.setId(Util.createID());
		message.setDate(Util.createNowDate());
		
		
		String updateSql="select stranger  from user where id=?";
		ResultSet rs=dbutil.dataQuery(updateSql, message.getUserId());
		int stranger=0;
		try {
			if(rs.next()){
				stranger=rs.getInt("stranger");
			}
			
			if(stranger==4 ||stranger==5 ){
				return false;
			}else{
				String sql="insert into messagelist (id,userid,username,message,date,reply)"
						+"values(?,?,?,?,?,?)";
				
				return dbutil.dataUpdate(sql, message.getId(),message.getUserId(),message.getUsername(),
						message.getMessage(),message.getDate(),message.getReply());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
		return false;
	}

}
