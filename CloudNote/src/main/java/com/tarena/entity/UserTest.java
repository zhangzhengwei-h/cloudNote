package com.tarena.entity;

public class UserTest extends Entity {

	private String cn_user_id;//用户id
	private String cn_user_name;//用户名
	private String cn_user_password;//密码
	private String cn_user_token;//令牌
	private String cn_user_desc;//说明
	public String getCn_user_id() {
		return cn_user_id;
	}
	public void setCn_user_id(String cn_user_id) {
		this.cn_user_id = cn_user_id;
	}
	public String getCn_user_name() {
		return cn_user_name;
	}
	public void setCn_user_name(String cn_user_name) {
		this.cn_user_name = cn_user_name;
	}
	public String getCn_user_password() {
		return cn_user_password;
	}
	public void setCn_user_password(String cn_user_password) {
		this.cn_user_password = cn_user_password;
	}
	public String getCn_user_token() {
		return cn_user_token;
	}
	public void setCn_user_token(String cn_user_token) {
		this.cn_user_token = cn_user_token;
	}
	public String getCn_user_desc() {
		return cn_user_desc;
	}
	public void setCn_user_desc(String cn_user_desc) {
		this.cn_user_desc = cn_user_desc;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cn_user_desc == null) ? 0 : cn_user_desc.hashCode());
		result = prime * result
				+ ((cn_user_id == null) ? 0 : cn_user_id.hashCode());
		result = prime * result
				+ ((cn_user_name == null) ? 0 : cn_user_name.hashCode());
		result = prime
				* result
				+ ((cn_user_password == null) ? 0 : cn_user_password.hashCode());
		result = prime * result
				+ ((cn_user_token == null) ? 0 : cn_user_token.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserTest other = (UserTest) obj;
		if (cn_user_desc == null) {
			if (other.cn_user_desc != null)
				return false;
		} else if (!cn_user_desc.equals(other.cn_user_desc))
			return false;
		if (cn_user_id == null) {
			if (other.cn_user_id != null)
				return false;
		} else if (!cn_user_id.equals(other.cn_user_id))
			return false;
		if (cn_user_name == null) {
			if (other.cn_user_name != null)
				return false;
		} else if (!cn_user_name.equals(other.cn_user_name))
			return false;
		if (cn_user_password == null) {
			if (other.cn_user_password != null)
				return false;
		} else if (!cn_user_password.equals(other.cn_user_password))
			return false;
		if (cn_user_token == null) {
			if (other.cn_user_token != null)
				return false;
		} else if (!cn_user_token.equals(other.cn_user_token))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "UserTest [cn_user_id=" + cn_user_id + ", cn_user_name="
				+ cn_user_name + ", cn_user_password=" + cn_user_password
				+ ", cn_user_token=" + cn_user_token + ", cn_user_desc="
				+ cn_user_desc + "]";
	}
	public UserTest(String cn_user_id, String cn_user_name,
			String cn_user_password, String cn_user_token, String cn_user_desc) {
		super();
		this.cn_user_id = cn_user_id;
		this.cn_user_name = cn_user_name;
		this.cn_user_password = cn_user_password;
		this.cn_user_token = cn_user_token;
		this.cn_user_desc = cn_user_desc;
	}
	public UserTest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
