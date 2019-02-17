package blog.server.impl;

import java.util.List;

import blog.dao.FriendDao;
import blog.dao.impl.FriendDaoImpl;
import blog.entity.User;
import blog.server.FriendServer;
/**
 *  stranger 
 * 好友标记：1
 * 非好友标记：2
 * 屏蔽标记：3
 * 非屏蔽标记：6
 * 
 * 好友屏蔽：4
 * 好友非屏蔽：7
 * 非好友屏蔽：5
 * 非好友非屏蔽：8 默认值
 * @author ZHM
 *
 */
public class FriendServerImpl implements FriendServer{

	private FriendDao friendDao=new FriendDaoImpl();
	
	@Override
	public List<User> getAllUserList() {
	
		return friendDao.getAllUserList();
	}

	@Override
	public boolean markFriend(String userid, int shield) {
		int flag=0;
		if(shield==4||shield==7){
			if(shield==4){
				flag=5;
			}else{
				flag=8;
			}
		}else {
			if(shield==5){
				flag=4;
			}else{
				flag=7;
			}
		}
		return friendDao.markFriend(userid, flag);
	}

	@Override
	public boolean delFriend(String userid) {
		
		return friendDao.delFriend(userid);
	}

	@Override
	public boolean shieldFriend(String userid, int friend) {
		int flag=0;
		if(friend==4||friend==5){
			if(friend==4){
				flag=7;
			}else{
				flag=8;
			}
		}else {
			if(friend==7){
				flag=4;
			}else{
				flag=5;
			}
		}
		return friendDao.shieldFriend(userid, flag);
	
	}

}
