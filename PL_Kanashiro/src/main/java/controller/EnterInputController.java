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
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import service.AjaxService;
import service.ValidationInputdayService;
import service.ValidationInputweightService;

@Controller
public class EnterInputController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	LoginCheckForm lcf2;

	@RequestMapping(path = "/LoginAfterPath", method = RequestMethod.GET)
	public String index(Model model) {

		return "EnterInput";
	}



	@RequestMapping(path = "/EnterInputPath", method = RequestMethod.POST)
	public String update(Model model,@Valid @ModelAttribute("eif1") EnterInputForm eif1, BindingResult result) {
		boolean ferr =false;
		//要素のnameがinputdayのvalidationをする
		if(result.hasFieldErrors("inputday")) {
			ferr = true;
			ValidationInputdayService valinputday = new ValidationInputdayService(result, model);
			valinputday.validation();
//			List<FieldError> inputdayErr = result.getFieldErrors("inputday");
//			data = new String[inputdayErr.size()];
//			for(int i = 0; i < inputdayErr.size(); i++) {
//				data[i] = inputdayErr.get(i).getDefaultMessage();
//			}
//			List<String> List_inputdayErr = Arrays.asList(data);
//			model.addAttribute("inputdayerr",List_inputdayErr);
//			//要素のnameがinputweightのvalidationも行い2つともエラーが発生しているかを検証する
//			if(result.hasFieldErrors("inputweight")) {
//				List<FieldError> inputweightErr = result.getFieldErrors("inputweight");
//				data = new String[inputweightErr.size()];
//				for(int k =0;k < inputweightErr.size(); k++) {
//					data[k] = inputweightErr.get(k).getDefaultMessage();
//				}
//				List<String>List_inputweightErr = Arrays.asList(data);
//				model.addAttribute("inputweightErr",List_inputweightErr);
//			}
//			return "EnterInput";
		}
		//要素のnameがinputweightのvalidationをする
		if(result.hasFieldErrors("inputweight")) {
			ferr = true;
			ValidationInputweightService valinputweight = new ValidationInputweightService(result, model);
			valinputweight.validation();
//			List<FieldError> inputweightErr = result.getFieldErrors("inputweight");
//			data = new String[inputweightErr.size()];
//			for(int k =0;k < inputweightErr.size(); k++) {
//				data[k] = inputweightErr.get(k).getDefaultMessage();
//			}
//			List<String>List_inputweightErr = Arrays.asList(data);
//			model.addAttribute("inputweightErr",List_inputweightErr);

		}
		if(ferr == true) {
			return "EnterInput";
		}
		else {
			//1日1回しか体重を計測できないようにする
			List<Map<String,Object>>list;
			//取得した各カラムの情報をリストに格納する
			list = jdbcTemplate.queryForList("SELECT ID, USER_ID, WEIGHT, MEASURE_DATE FROM weight "
					+ "WHERE  USER_ID = ? AND MEASURE_DATE = ?",lcf2.getUid(),eif1.getInputday());
			if(list.size()==0) {
			jdbcTemplate.update("INSERT INTO weight (USER_ID, WEIGHT, MEASURE_DATE)VALUES(?, ?, ?)",
					lcf2.getUid(), eif1.getInputweight(), eif1.getInputday());

			// ユーザIDを画面に渡す
			eif1.setUid(lcf2.getUid());
			return "LogOut";
			//まだ出来ていない(カレンダー画面で確認する)
			}else {
				String message = "その日は計測済みです。";
				model.addAttribute("MS", message);
				return "EnterInput";
			}
		}

	}






//@SuppressWarnings("deprecation")
@ResponseBody
@RequestMapping(path = "/getWeight", method = RequestMethod.POST)
public ObjectNode getWeight(Model model,@Valid @ModelAttribute EnterInputForm eif1, BindingResult result) {
	List<Map<String,Object>>list;
	list = jdbcTemplate.queryForList("SELECT WEIGHT, MEASURE_DATE FROM weight WHERE USER_ID=?",lcf2.getUid());

	// Jacksonによるjsonオブジェクトの作成
	ObjectMapper mapper = new ObjectMapper();
	ObjectNode pnode = mapper.createObjectNode();
	ArrayNode monthly = mapper.createArrayNode();
	ObjectNode node = mapper.createObjectNode();

	// 親ノードの作成
//	int count = 0;
//	for(Map<String,Object> d : list){
//		count++;
//		//jsonのオブジェクトnodeにキーと値を格納する
//		node.put("id", count);
//		node.put("name", String.valueOf(d.get("WEIGHT"))+"kg");
//		node.put("startdate", String.valueOf(d.get("MEASURE_DATE")));
//		node.put("enddate", String.valueOf(d.get("MEASURE_DATE")));
//		node.put("starttime", "");
//		node.put("endtime", "");
//		node.put("color", "#666699");
//		node.put("url", "");
//		//配列のjsonオブジェクトmonthlyに作成したjsonのオブジェクトnodeを格納する
//		monthly.add(node);
//		//次の体重のデータをjsonにするため、新たに空のjsonのオブジェクトnodeを作成する
//		node = mapper.createObjectNode();
//	}
//	//jsonオブジェクトpnodeにキーと配列のjsonオブジェクトmonthlyを格納する
//	pnode.put("monthly", monthly);

	AjaxService as = new AjaxService(list, mapper, pnode, monthly, node);
	as.ajax();
	return pnode;
}

}