package service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
@Service
public class ValidationInputweightService {
	//Validationの結果
	private BindingResult result;
	//モデルオブジェクト
	private Model model;

	public ValidationInputweightService(BindingResult re, Model mo) {
		result = re;
		model = mo;
	}
	public void validation() {
		String[] data = null;
		List<FieldError> inputweightErr = result.getFieldErrors("inputweight");
		data = new String[inputweightErr.size()];
		for(int k =0;k < inputweightErr.size(); k++) {
			data[k] = inputweightErr.get(k).getDefaultMessage();
		}
		List<String>List_inputweightErr = Arrays.asList(data);
		model.addAttribute("inputweightErr",List_inputweightErr);
	}
}
