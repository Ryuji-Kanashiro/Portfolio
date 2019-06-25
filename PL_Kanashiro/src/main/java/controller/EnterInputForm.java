package controller;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class EnterInputForm implements Serializable{
	private static final long serialVersionUID = 1L;

	@NotNull(message = "日付をカレンダーから選択して下さい")
	private String inputday;

	@Length(min=4,message = "4桁で入力して下さい")
	private String inputweight;

	private String uid;

	public String getInputday() {
		return inputday;
	}

	public void setInputday(String inputday) {
		this.inputday = inputday;
	}

	public String getInputweight() {
		return inputweight;
	}

	public void setInputweight(String inputweight) {
		this.inputweight = inputweight;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUid() {
		return uid;
	}
}
