package service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Service
public class ValidationPwdService {
	//Validationの結果
	private BindingResult result;
	//モデルオブジェクト
	private Model model;

	public ValidationPwdService (BindingResult re, Model mo) {
		result = re;
		model = mo;
	}
	public void validation() {
		String[] data = null;
		List<FieldError> pwdErr = result.getFieldErrors("pwd");
		data = new String[pwdErr.size()];
		for(int k =0;k < pwdErr.size(); k++) {
			data[k] = pwdErr.get(k).getDefaultMessage();
		}
		List<String>List_pwdErr = Arrays.asList(data);
		model.addAttribute("pwderr",List_pwdErr);
		//test
	}
}
