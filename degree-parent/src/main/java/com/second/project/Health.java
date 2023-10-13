package com.second.project;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class Health {

	@RequestMapping(value = "/health",method = {RequestMethod.GET,RequestMethod.POST,RequestMethod.HEAD,RequestMethod.OPTIONS,RequestMethod.PUT})
	@ResponseBody
	public String getHealth(HttpServletRequest request,HttpServletResponse response) throws Exception {
		return "OK";
	}
}

