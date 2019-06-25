package service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Service
public class ValidationUidService {
	//Validationの結果
	private BindingResult result;
	//モデルオブジェクト
	private Model model;

	public ValidationUidService(BindingResult re, Model mo) {
		result = re;
		model = mo;
	}
	public void validation() {
		String[] data = null;
		List<FieldError> uidErr = result.getFieldErrors("uid");
		data = new String[uidErr.size()];
		for(int j = 0; j < uidErr.size(); j++) {
			data[j] = uidErr.get(j).getDefaultMessage();
		}
		List<String> List_uidErr = Arrays.asList(data);
		model.addAttribute("uiderr",List_uidErr);
	}

}
