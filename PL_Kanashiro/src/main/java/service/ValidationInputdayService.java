package service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Service
public class ValidationInputdayService {
	//Validationの結果
	private BindingResult result;
	//モデルオブジェクト
	private Model model;
	public ValidationInputdayService(BindingResult re, Model mo) {

	}
	public void validation() {
		String[] data = null;
		List<FieldError> inputdayErr = result.getFieldErrors("inputday");
		data = new String[inputdayErr.size()];
		for(int i = 0; i < inputdayErr.size(); i++) {
			data[i] = inputdayErr.get(i).getDefaultMessage();
		}
		List<String> List_inputdayErr = Arrays.asList(data);
		model.addAttribute("inputdayerr",List_inputdayErr);
	}
}
