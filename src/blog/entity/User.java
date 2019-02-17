package blog.entity;
/**
 * 用户信息表
 * @author ZHM
 *
 */
public class User {
	private String id;
	private String name;
	private String blogAddress;
	private String emailAddress;
	private String password;
	private int type; // 1表示博主 0表示联系人
	private int stranger ;//1表示陌生人 0表示好友
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBlogAddress() {
		return blogAddress;
	}
	public void setBlogAddress(String blogAddress) {
		this.blogAddress = blogAddress;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getStranger() {
		return stranger;
	}
	public void setStranger(int stranger) {
		this.stranger = stranger;
	}
	
}
