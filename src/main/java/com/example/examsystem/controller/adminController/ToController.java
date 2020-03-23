package com.example.examsystem.controller.adminController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/go/")
public class ToController
{
	@RequestMapping("to/{id}/{id2}")
	public String to(@PathVariable("id") String id, @PathVariable("id2") String id2)
	{
		return id+"/"+id2;
	}
}