package com.spring.boot.micro;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductsController {
	@Autowired
	private ProductsService productsService;
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	@GetMapping("/products")
	public String getAll(Model model, HttpServletRequest request) 
	{
		String error_message = getErrorMessage(request);
		String info_message = getInfoMessage(request);
		List<Products> items = this.productsService.getAll();
		model.addAttribute("items", items);
		model.addAttribute("error_message", (error_message == "null" ? "" : error_message));
		model.addAttribute("info_message", (info_message == "null" ? "" : info_message));
		return "products/list";
	}
	
	@GetMapping("/products/update/{id}")
	public String updateItem(@PathVariable int id, Model model)
	{
		Products products = this.productsService.getItem(id);
		model.addAttribute("products", products);
		return "products/update";
	}
	
	@RequestMapping(value = "/products/update", method = RequestMethod.POST)
    public String updateItem(@ModelAttribute @Valid Products item, BindingResult bindingResult, Model model, HttpServletRequest request)
	{
		if (bindingResult.hasErrors()) {
            System.out.println("BINDING RESULT ERROR");
            return "products/update";
        } 
		else {
        	this.productsService.updateItem(item.getId(), item);
    		boolean isValid = item != null;
    		
    		if(isValid)
    		{
    			request.getSession().setAttribute("info_message", "Information Saved Correctly!");
    		}
    		else
    		{
    			request.getSession().setAttribute("error_message", "There were some errors trying to save your information");
    		}
    		
    		return "redirect:/products";	
        }
		
	}
	
	@GetMapping("/products/create")
	public String createItem(Model model)
	{
		model.addAttribute("products", new Products());
		return "products/create";
	}

	@RequestMapping(value = "/products/create", method = RequestMethod.POST)
    public String createItem(@ModelAttribute @Valid Products item, BindingResult bindingResult, Model model, HttpServletRequest request)
	{
	
		if (bindingResult.hasErrors()) {
            System.out.println("BINDING RESULT ERROR");
            return "products/create";
        } 
		else {
			this.productsService.createItem(item);
			boolean isValid = item != null;
			
			if(isValid)
			{
				request.getSession().setAttribute("info_message", "Information Saved Correctly!");
			}
			else
			{
				request.getSession().setAttribute("error_message", "There were some errors trying to save your information");
			}
			return "redirect:/products";	

		}
		
	}
	
	@GetMapping("/products/details/{id}")
	public String detailsItem(@PathVariable int id, Model model)
	{
		Products products = this.productsService.getItem(id);
		model.addAttribute("products", products);
		return "products/details";
	}
		
	@RequestMapping(value = "products/delete/{id}", method = RequestMethod.POST)
	public ModelAndView deleteItem(@PathVariable int id, HttpServletRequest request)
	{

		this.productsService.deleteItem(id);
		request.getSession().setAttribute("info_message", "products Deleted Correctly!");
		return new ModelAndView("redirect:/products", new HashMap<>());
	}
	
	private String getErrorMessage(HttpServletRequest request)
	{
		Object retVal = request.getSession().getAttribute("error_message");
		request.getSession().setAttribute("error_message", "");
		return String.valueOf(retVal);
	}
	
	private String getInfoMessage(HttpServletRequest request)
	{
		Object retVal = request.getSession().getAttribute("info_message");
		request.getSession().setAttribute("info_message", "");
		return String.valueOf(retVal);
	}
}
