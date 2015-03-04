package com.comp.elearning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.comp.elearning.entity.Account;
import com.comp.elearning.service.AccountService;

@Controller
@RequestMapping("/accountsRegistration")
public class AccountController {

	@Autowired
	// @Qualifier("accountSerivce")
	private AccountService accountSerivce;

	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Account> listRegisteredAccounts() {
		return accountSerivce.list();
		// return null;
	}

}
