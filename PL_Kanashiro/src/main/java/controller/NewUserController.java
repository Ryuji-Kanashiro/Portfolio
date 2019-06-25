package controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import service.NewUserService;
import service.ValidationChecnewpwdService;
import service.ValidationNewpwdService;
import service.ValidationNewuidService;

@Controller
public class NewUserController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@RequestMapping(path = "/NewUser", method = RequestMethod.GET)
	public String index(Model model) {

		return "NewUser";
	}

	@RequestMapping(path = "/NewUserFirst", method = RequestMethod.POST)
	public String update(Model model,@Valid @ModelAttribute NewUserCheckForm nucf1, BindingResult result) {
		boolean ferr = false;
		//要素のnameがnewuidのvalidationをする
		if(result.hasFieldErrors("newuid")) {
			ferr = true;
			ValidationNewuidService valnewuid = new ValidationNewuidService(result, model);
			valnewuid.validation();
//			List<FieldError> newuidErr = result.getFieldErrors("newuid");
//			data = new String[newuidErr.size()];
//			for(int i = 0; i < newuidErr.size(); i++) {
//				data[i] = newuidErr.get(i).getDefaultMessage();
//			}
//			List<String> List_newuidErr = Arrays.asList(data);
//			model.addAttribute("newuiderr",List_newuidErr);

		}
		//要素のnameがnewpwdのvalidationをする
		if(result.hasFieldErrors("newpwd")) {
			ferr = true;
			ValidationNewpwdService valnewpwd = new ValidationNewpwdService(result, model);
			valnewpwd.validation();
//			List<FieldError> newpwdErr = result.getFieldErrors("newpwd");
//			data = new String[newpwdErr.size()];
//			for(int j = 0; j < newpwdErr.size(); j++) {
//				data[j] = newpwdErr.get(j).getDefaultMessage();
//			}
//			List<String>List_newpwdErr = Arrays.asList(data);
//			model.addAttribute("newpwderr",List_newpwdErr);

		}
		//要素のnameがchecknewpwdのvalidationをする
		if(result.hasFieldErrors("checknewpwd")) {
			ferr = true;
			ValidationChecnewpwdService valcheknewpwd = new ValidationChecnewpwdService(result, model);
			valcheknewpwd.validation();
//			List<FieldError> newchecknewpwdErr = result.getFieldErrors("checknewpwd");
//			data = new String[newchecknewpwdErr.size()];
//			for(int k = 0; k < newchecknewpwdErr.size(); k++) {
//				data[k] = newchecknewpwdErr.get(k).getDefaultMessage();
//			}
//			List<String>List_checknewpwdErr = Arrays.asList(data);
//			model.addAttribute("checknewpwderr", List_checknewpwdErr);

		}
		if(ferr == true) {
			return "NewUser";
		}
		//入力されたIDとPASSRORDで指定し、DBからの取得を行う
		//DBになければ、重複はないのでinsertする
		else if(nucf1.getNewpwd().equals(nucf1.getChecknewpwd())) {
			List<Map<String,Object>>list;
			//取得した各カラムの情報をリストに格納する
			list = jdbcTemplate.queryForList("SELECT ID, USER_ID, PASSWORD, CREATE_DATE FROM user "
					+ "WHERE  PASSWORD = ? AND USER_ID = ?",nucf1.getNewpwd(),nucf1.getNewuid());
			//リストの中身の数が0なら重複はないので、insertを行う
			//それ以外は新規登録画面に戻る
			if(list.size() == 0) {
				//サービスクラスのオブジェクトを作成する
				//フォームとjdbcTemplateのオブジェクトを引数として渡す
				NewUserService nus = new NewUserService(nucf1,jdbcTemplate);
				nus.newUser();
				return "login";
			}
			//リストの中身があったならば、重複しているので
			//新規登録画面に戻る


		}
		else {
			model.addAttribute("errm", "同じパスワードを入力して下さい");
			return "NewUser";
		}
		return null;
	}



}
