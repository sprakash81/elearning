package com.comp.elearning.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.comp.elearning.entity.Account;
import com.comp.elearning.service.AccountService;

@Controller
@RequestMapping("/accounts")
public class AccountController extends BaseController {

	@Autowired
	private AccountService accountSerivce;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "account";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Account> listRegisteredAccounts() {
		return accountSerivce.list();
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	public String registerUser(@ModelAttribute Account account) {
		try {
			account = accountSerivce.create(account);
		} catch (final Exception e) {
			handleUnknownExceptions(e);
		}
		final Map<String, Object> returnValues = new HashMap<String, Object>();
		returnValues.put("account", account);

		return "account";
	}

}
