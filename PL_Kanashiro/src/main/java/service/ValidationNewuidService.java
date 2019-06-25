package service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Service
public class ValidationNewuidService {
	//Validationの結果
	private BindingResult result;
	//モデルオブジェクト
	private Model model;

	public ValidationNewuidService(BindingResult re, Model mo) {
		result = re;
		model = mo;
	}
	public void validation() {
		String[] data = null;
		List<FieldError> newuidErr = result.getFieldErrors("newuid");
		data = new String[newuidErr.size()];
		for(int i = 0; i < newuidErr.size(); i++) {
			data[i] = newuidErr.get(i).getDefaultMessage();
		}
		List<String> List_newuidErr = Arrays.asList(data);
		model.addAttribute("newuiderr",List_newuidErr);
	}
}
