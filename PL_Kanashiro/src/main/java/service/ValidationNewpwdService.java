package service;

import java.util.Arrays;
import java.util.List;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class ValidationNewpwdService {
	//Validationの結果
	private BindingResult result;
	//モデルオブジェクト
	private Model model;

	public ValidationNewpwdService(BindingResult re, Model mo) {
		result = re;
		model = mo;
	}
	public void validation() {
		String[] data = null;
		List<FieldError> newpwdErr = result.getFieldErrors("newpwd");
		data = new String[newpwdErr.size()];
		for(int j = 0; j < newpwdErr.size(); j++) {
			data[j] = newpwdErr.get(j).getDefaultMessage();
		}
		List<String>List_newpwdErr = Arrays.asList(data);
		model.addAttribute("newpwderr",List_newpwdErr);
	}
}
