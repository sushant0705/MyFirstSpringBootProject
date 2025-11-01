package com.itp.MyFirstSpringBootApp.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itp.MyFirstSpringBootApp.model.Product;
@Controller
public class HelloController {
	
	@RequestMapping("/test")
	public String test()
	{
		return "Welcome";
	}
	
	@RequestMapping("/getProduct")
	public Product getProduct()
	{
		Product p=Product.builder()
				.pid(101)
				.productTitle("Laptop")
				.productDesc("16 GB Ram 1TB SSD i7 cpu Dell")
				.productCategory("Electronics")
				.price(100.0)
				.build();
		
		return p;
	}
	
	@RequestMapping("/getProduct1")
	public Product getProduct1()
	{
		Product p=Product.builder()
				.pid(102)
				.productTitle("Mobile")
				.productDesc("16 GB Ram 1TB SSD One Plus")
				.productCategory("Electronics")
				.price(100.0)
				.build();
		
		return p;
	}
	
	
	

}
