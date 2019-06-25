package service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class AjaxService {
	private List<Map<String,Object>>list;
	private ObjectMapper mapper;
	private ObjectNode pnode;
	private ArrayNode monthly;
	private ObjectNode node;

	public AjaxService(List<Map<String,Object>>li, ObjectMapper ob1, ObjectNode obn, ArrayNode an, ObjectNode no) {
		list = li;
		mapper = ob1;
		pnode = obn;
		monthly = an;
		node = no;
	}
	@SuppressWarnings("deprecation")
	public void ajax() {
		int count = 0;
		for(Map<String,Object> d : list){
			count++;
			//jsonのオブジェクトnodeにキーと値を格納する
			node.put("id", count);
			node.put("name", String.valueOf(d.get("WEIGHT"))+"kg");
			node.put("startdate", String.valueOf(d.get("MEASURE_DATE")));
			node.put("enddate", String.valueOf(d.get("MEASURE_DATE")));
			node.put("starttime", "");
			node.put("endtime", "");
			node.put("color", "#666699");
			node.put("url", "");
			//配列のjsonオブジェクトmonthlyに作成したjsonのオブジェクトnodeを格納する
			monthly.add(node);
			//次の体重のデータをjsonにするため、新たに空のjsonのオブジェクトnodeを作成する
			node = mapper.createObjectNode();
		}
		//jsonオブジェクトpnodeにキーと配列のjsonオブジェクトmonthlyを格納する
		pnode.put("monthly", monthly);


	}
}
