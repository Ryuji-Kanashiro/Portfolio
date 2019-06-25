package controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import service.ValidationPwdService;
import service.ValidationUidService;

@Controller
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	LoginCheckForm lcf2;


	@RequestMapping(path = "/LoginPath", method = RequestMethod.GET)
	public String index(Model model) {
		logger.info("index method");

//ここの段階では値は無い、いくらやってもnull
//下記はだめな例
//		List<Map<String,Object>> list1;
//		list1 = jdbcTemplate.queryForList("SELECT ID, USER_ID, PASSWORD, CREATE_DATE FROM user "
//				+ "WHERE  PASSWORD = ? AND USER_ID = ?",lcf2.getPwd(),lcf2.getUid());
//		lcf2.setUid((String) list1.get(0).get("USER_ID"));
		return "login";
	}

	@RequestMapping(path = "/LoginAfterPath", method = RequestMethod.POST)
	public String update(Model model,@Valid @ModelAttribute LoginCheckForm lcf1, BindingResult result) {

		boolean ferr = false;
		//要素のnameがuidのvalidationをする
		if(result.hasFieldErrors("uid")) {
			ferr = true;
//			List<FieldError> uidErr = result.getFieldErrors("uid");
//			data = new String[uidErr.size()];
//			for(int j = 0; j < uidErr.size(); j++) {
//				data[j] = uidErr.get(j).getDefaultMessage();
//			}
//			List<String> List_uidErr = Arrays.asList(data);
//			model.addAttribute("uiderr",List_uidErr);

			ValidationUidService valuid = new ValidationUidService(result, model);
			valuid.validation();
			//要素のnameがpwdのvalidationも行い2つともエラーが発生しているかを検証する
//			if(result.hasFieldErrors("pwd")) {
//				List<FieldError> pwdErr = result.getFieldErrors("pwd");
//				data = new String[pwdErr.size()];
//				for(int k =0;k < pwdErr.size(); k++) {
//					data[k] = pwdErr.get(k).getDefaultMessage();
//				}
//				List<String>List_pwdErr = Arrays.asList(data);
//				model.addAttribute("pwderr",List_pwdErr);
//			}
//			return "login";
			}
		if(result.hasFieldErrors("pwd")) {
			ferr = true;
//			List<FieldError> pwdErr = result.getFieldErrors("pwd");
//			data = new String[pwdErr.size()];
//			for(int k =0;k < pwdErr.size(); k++) {
//				data[k] = pwdErr.get(k).getDefaultMessage();
//			}
//			List<String>List_pwdErr = Arrays.asList(data);
//			model.addAttribute("pwderr",List_pwdErr);
			ValidationPwdService valpwd = new ValidationPwdService(result, model);
			valpwd.validation();
		}
		if(ferr == true) {

//			//要素のnameがpwdのvalidationをする
//		else if(result.hasFieldErrors("pwd")) {
//			List<FieldError> pwdErr = result.getFieldErrors("pwd");
//			data = new String[pwdErr.size()];
//			for(int k =0;k < pwdErr.size(); k++) {
//				data[k] = pwdErr.get(k).getDefaultMessage();
//			}
//			List<String>List_pwdErr = Arrays.asList(data);
//			model.addAttribute("pwderr",List_pwdErr);

		return "login";
		}
		//入力されたIDとPASSRORDで指定し、DBからの取得を行う
		if(ferr == false) {
			List<Map<String,Object>>list;
			//取得した各カラムの情報をリストに格納する
			list = jdbcTemplate.queryForList("SELECT ID, USER_ID, PASSWORD, CREATE_DATE FROM user "
					+ "WHERE  PASSWORD = ? AND USER_ID = ?",lcf1.getPwd(),lcf1.getUid());

			//リストの中身の数が0ならログイン画面へ戻る、それ以外は入力画面に進む
			if(list.size()==0) {
				return "NewUser";
			}

			//USER_IDをSQLから取得し、LoginCheckForm型のオブジェクトlcf2にセットする
			lcf2.setUid((String) list.get(0).get("USER_ID"));

			return "EnterInput";
			}
		return null;



	}




}