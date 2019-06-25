package controller;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

public class NewUserCheckForm implements Serializable{
	private static final long serialVersionUID = 1L;;

	@Pattern(regexp = "^[a-zA-Z0-9]*$", message = "半角英数字で入力してください")
	@Length(min=4, max=8,message = "4文字以上8文字以下で入力して下さい")
	private String newuid;

	@Pattern(regexp = "^[a-zA-Z0-9]*$", message = "半角英数字で入力してください")
	@Length(min=4, max=20,message = "4文字以上20文字以下で入力して下さい")
	private String newpwd;

	@Pattern(regexp = "^[a-zA-Z0-9]*$", message = "半角英数字で入力してください")
	@Length(min=4, max=20,message = "4文字以上20文字以下で入力して下さい")
	private String checknewpwd;

	public String getNewuid() {
		return newuid;
	}

	public void setNewuid(String newuid) {
		this.newuid = newuid;
	}

	public String getNewpwd() {
		return newpwd;
	}

	public void setNewpwd(String newpwd) {
		this.newpwd = newpwd;
	}

	public String getChecknewpwd() {
		return checknewpwd;
	}

	public void setChecknewpwd(String checknewpwd) {
		this.checknewpwd = checknewpwd;
	}
}
