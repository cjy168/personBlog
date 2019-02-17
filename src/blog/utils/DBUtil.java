package blog.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 自定义数据库连接工具类
 * @author ZHM
 *
 */
public class DBUtil {
	//用户名
	private  String user="root";
	//数据库连接密码
	private  String password="123456";
	//数据库连接url
	private  String url="jdbc:mysql://localhost:3306/personblog?"
	+ "useUnicode=true&characterEncoding=utf8";
	
	//数据库连接对象
	private Connection con=null;
	
	//动态的SQL语句执行对象
	private PreparedStatement preparedStatement;
	
	//查询返回的结果集
	private ResultSet rs=null;
	
	//单例模式
	private static DBUtil dbUtil=null;
	
	/**
	 * 在初始化的时候进行驱动加载
	 */
	public DBUtil(){
		//驱动加载
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("驱动加载完成...");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 获取连接
	 * @return
	 */
	public Connection getConnection(){
		 try {
			 //获取连接
			con= DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return con;
	}
//	public ResultSet query(String sql) {
//		con = getConnection();
//		try {
//			preparedStatement= con.prepareStatement(sql);
//			rs = preparedStatement.executeQuery();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return rs;
//	}
	/**
	 * 数据的查询
	 */
	public ResultSet dataQuery(String sql,Object...args){
		//获取连接
		con=getConnection();

		try {
			//预编译sql语句
			preparedStatement= con.prepareStatement(sql);
			//插入参数
			int len=0;
			while(args!=null && len<args.length){
				preparedStatement.setObject(len+1, args[len]);
				len++;
			}
			//执行SQL
			rs=preparedStatement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	/**
	 * 数据的修改，删除，添加
	 */
	public boolean dataUpdate(String sql,Object...args){
		//获取连接：
		con=getConnection();
		//表示受影响的行数
		int resultNum=0;
		try {
			//预编译SQL
			preparedStatement=con.prepareStatement(sql);
		
			//插入参数
			int len=0;
			while(args!=null && len<args.length){
				preparedStatement.setObject(len+1, args[len]);
				len++;
			}
			//执行SQL
			resultNum=preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//关闭
			close();
		}
		
		//只要有1条受到影响，就算执行成功
		return resultNum >0 ? true :false;
	}
	
	/**
	 * 关闭
	 */
	public void close(){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(preparedStatement!=null){
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(con!=null){
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	/**
	 * 单例模式，一个系统下只有一个实例对象
	 * @return
	 */
	public static DBUtil newInstance(){
		if(dbUtil==null){
			dbUtil=new DBUtil();
		}
		
		return dbUtil;
	}
	
}
