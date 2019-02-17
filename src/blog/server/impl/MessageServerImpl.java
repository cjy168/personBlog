package blog.server.impl;

import java.util.List;

import blog.dao.MessageDao;
import blog.dao.impl.MessageDaoImpl;
import blog.entity.Message;
import blog.server.MessageServer;

public class MessageServerImpl implements MessageServer {

	private MessageDao messageDao=new MessageDaoImpl();
	
	@Override
	public List<Message> getAllMessageList() {
		
		return messageDao.getAllMessageList();
	}

	@Override
	public boolean addNewMessageByAdmin(String messageid, String text) {
		
		return messageDao.addNewMessageByAdmin(messageid, text);
	}

	@Override
	public boolean addNewMessage(Message message) {
		return messageDao.addNewMessage(message);
	}

}
