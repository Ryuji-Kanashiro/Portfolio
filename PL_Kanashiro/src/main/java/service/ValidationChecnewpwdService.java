package service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Service
public class ValidationChecnewpwdService {
	//Validationの結果
	private BindingResult result;
	//モデルオブジェクト
	private Model model;

	public ValidationChecnewpwdService(BindingResult re, Model mo) {
		result = re;
		model = mo;
	}
	public void validation() {
		String[] data = null;
		List<FieldError> newchecknewpwdErr = result.getFieldErrors("checknewpwd");
		data = new String[newchecknewpwdErr.size()];
		for(int k = 0; k < newchecknewpwdErr.size(); k++) {
			data[k] = newchecknewpwdErr.get(k).getDefaultMessage();
		}
		List<String>List_checknewpwdErr = Arrays.asList(data);
		model.addAttribute("checknewpwderr", List_checknewpwdErr);
	}
}
