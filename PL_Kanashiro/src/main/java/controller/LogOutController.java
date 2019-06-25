package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LogOutController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	LoginCheckForm lcf2;


	@RequestMapping(path = "/EnterInputPath", method = RequestMethod.GET)
	public String index(Model model) {

		return "LogOut";
	}

	@RequestMapping(path = "/Logout", method = RequestMethod.POST)
	public String update(Model model, @ModelAttribute LoginCheckForm lcf1) {
		lcf2.clear();
			return "login";




	}




}