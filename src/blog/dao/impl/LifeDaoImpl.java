package blog.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import blog.dao.LifeDao;
import blog.entity.Life;
import blog.utils.DBUtil;

/**
 * cai.jiaying 2018-11-30
 * @author ASUS
 *
 */
public class LifeDaoImpl implements LifeDao {

	private DBUtil dbUtil=DBUtil.newInstance();
	
	
	/**
	 * 蔡佳颖，2018-11-30
	 * 获取新的生活随笔列表
	 * @param
	 * return  List<Life>
	 */
	@Override
	public List<Life> getNewLifeList() {
		//使用StringBuffer的原因是因为防止sql过长导致维护困难
		StringBuffer sql = new StringBuffer();
		sql.append("select * from life order by date desc");
		//String sql="select * from life order by date desc";
		
		ResultSet rs=dbUtil.dataQuery(sql.toString());
		
		List<Life> lifeList=new ArrayList<>();
		try {
			while(rs.next()){
				Life life=new Life();
				life.setId(rs.getString("id"));
				life.setBody(rs.getString("body"));
				life.setDate(rs.getDate("date"));
				
				lifeList.add(life);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lifeList;
	}
	  

	@Override
	public boolean addNewLife(Life life) {
		String sql="insert into life(id,body,date) "
				+ "values(?,?,?)";
		return dbUtil.dataUpdate(sql.toString(),life.getId(),life.getBody(),life.getDate());
	}

	@Override
	public boolean delLifeById(String id) {
		String sql="delete from life where id=?";
		return dbUtil.dataUpdate(sql, id);
	}

}
