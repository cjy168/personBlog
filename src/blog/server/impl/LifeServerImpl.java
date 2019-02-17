package blog.server.impl;

import java.util.List;

import blog.dao.LifeDao;
import blog.dao.impl.LifeDaoImpl;
import blog.entity.Life;
import blog.server.LifeServer;
import blog.utils.Util;

public class LifeServerImpl implements LifeServer {

	private LifeDao lifeDao=new LifeDaoImpl();
	
	@Override
	public List<Life> getNewLifeList() {
	
		return lifeDao.getNewLifeList();
	}

	@Override
	public boolean addNewLife(Life life) {
		life.setId(Util.createID());
		life.setDate(Util.createNowDate());
		
		return lifeDao.addNewLife(life);
	}

	@Override
	public boolean delLifeById(String id) {
		
		return lifeDao.delLifeById(id);
	}

}
