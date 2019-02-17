package blog.server.impl;

import java.util.List;

import blog.dao.UserDao;
import blog.dao.impl.UserDaoImpl;
import blog.entity.User;
import blog.server.UserServer;
import blog.utils.Util;

public class UserServerImpl implements UserServer{

	private UserDao userDao=new UserDaoImpl();
	
	@Override
	public User adminLogin(String email, String password) {

		return userDao.adminLogin(email, password);
	}

	@Override
	public User userLogin(String email) {

		return userDao.userLogin(email);
	}

	@Override
	public List<User> getFriendsList() {
		return userDao.getFriendsList();
	}

	@Override
	public boolean userRegister(User user) {
		user.setId(Util.createID());
		user.setType(0);
		user.setStranger(8);
		
		return userDao.userRegister(user);
	}

}
