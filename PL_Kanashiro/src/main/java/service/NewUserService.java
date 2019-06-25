package service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import controller.NewUserCheckForm;

@Service
public class NewUserService {

	//コントローラーのフォームオブジェクトでなければ、値は拾えない！！
	private NewUserCheckForm newNucf;
	//コントローラーのjdbcでなければ、SQLが使えない！！
	private JdbcTemplate jdbc2;


	public NewUserService(NewUserCheckForm nucfo, JdbcTemplate newJdbc) {
		newNucf = nucfo;
		jdbc2 = newJdbc;
	}
	public void newUser() {
		//現在の時間を調べる
		Date now = new Date();

		//SimpleDateFormatクラスを型にして、オブジェクトを作成
		SimpleDateFormat registerNow = new SimpleDateFormat("yyyy/MM/dd/HH/mm/ss");

		//NewUserCheckFormのオブジェクトを作成する
//		NewUserCheckForm nucf1 = new NewUserCheckForm();

		jdbc2.update("INSERT INTO user (USER_ID, PASSWORD, CREATE_DATE)VALUES(?, ?, ?)",
				newNucf.getNewuid(), newNucf.getNewpwd(), registerNow.format(now.getTime()));
	}
}
