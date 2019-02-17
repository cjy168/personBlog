package blog.dao;

import java.util.List;

import blog.entity.Message;

public interface MessageDao {

	/**
	 * 获取所有的留言信息
	 */
	public List<Message> getAllMessageList();
	
	/**
	 * 添加新的信息
	 * @param messageid
	 * @param text
	 * @return
	 */
	public boolean addNewMessageByAdmin(String messageid,String text);
	
	/**
	 * 添加新的留言信息
	 * @param message
	 * @return
	 */
	public boolean addNewMessage(Message message);
}
