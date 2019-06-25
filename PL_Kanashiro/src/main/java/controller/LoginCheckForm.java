package controller;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LoginCheckForm implements Serializable{
	private static final long serialVersionUID = 1L;

	@Length(min=4, max=8,message = "4文字以上8文字以下で入力して下さい")
	private String uid;

	@Length(min=4, max=20,message = "4文字以上20文字以下で入力して下さい")
	private String pwd;


	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}


//uidとpwdにnullを代入して、ログアウトを表現
	public void clear() {
		uid = null;
		pwd = null;
	}
}
