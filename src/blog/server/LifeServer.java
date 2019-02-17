package blog.server;

import java.util.List;

import blog.entity.Life;

public interface LifeServer {
	/**
	 * 获取最新的生活随笔
	 * @return
	 */
	public List<Life> getNewLifeList();
	
	/**
	 * 添加新的生活随笔
	 * @param life
	 * @return
	 */
	public boolean addNewLife(Life life);
	
	/**
	 * 删除一条随笔内容
	 * @param id
	 * @return
	 */
	public boolean delLifeById(String id);
}
