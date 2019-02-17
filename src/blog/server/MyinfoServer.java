package blog.server;

import blog.entity.Me;

public interface MyinfoServer {
		//获取我的个人信息
		public Me getMeinfo();
		
		//修改我的个人信息此
		public boolean updateMeinfo(Me my);
}
