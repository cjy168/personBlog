package blog.dao;

import java.util.List;

import blog.entity.User;

public interface FriendDao {
	/**
	 * 获取全部的联系人
	 * @return
	 */
	public List<User> getAllUserList();
	
	/**
	 * 标记联系人为好友
	 */
	public boolean markFriend(String userid,int shield);
	
	/**
	 * 删除联系人
	 * @return
	 */
	public boolean delFriend(String userid);
	
	/**
	 * 屏蔽联系人
	 * @param userid
	 * @return
	 */
	public boolean shieldFriend(String userid,int friend);
	
}
