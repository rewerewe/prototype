package com.example.demo.rest;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class RestTemplateController {

	@Autowired
	RestTemplateService restTemplateService;

	@GetMapping(value = "/search/{param}")
	@ResponseBody
	public HashMap<String, String> weather(@PathVariable String param) {
		String responseBody = restTemplateService.get(param);

		HashMap<String, String> result = new HashMap<>();
		result.put("responseBody", responseBody);

		return result;
	}

}
