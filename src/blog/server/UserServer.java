package blog.server;

import java.util.List;

import blog.entity.User;

public interface UserServer {
	/**
	 * 博主登录
	 */
	public User adminLogin(String email,String password );
	
	/**
	 * 普通用户登录
	 * @param email
	 * @return
	 */
	public User userLogin(String email);
	
	/**
	 * 获取好友列表
	 * @return
	 */
	public List<User> getFriendsList();
	
	/**
	 * 注册一个帐户
	 * @param user
	 * @return
	 */
	public boolean userRegister(User user);
}
