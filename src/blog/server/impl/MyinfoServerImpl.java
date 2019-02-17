package blog.server.impl;

import blog.dao.MyinfoDao;
import blog.dao.impl.MyinfoDaoImpl;
import blog.entity.Me;
import blog.server.MyinfoServer;

public class MyinfoServerImpl implements MyinfoServer{

	private MyinfoDao myinfoDao=new MyinfoDaoImpl();
	
	@Override
	public Me getMeinfo() {

		return myinfoDao.getMeinfo();
	}

	@Override
	public boolean updateMeinfo(Me my) {
	
		return myinfoDao.updateMeinfo(my);
	}

}
