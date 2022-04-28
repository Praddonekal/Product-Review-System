
package com.spring.boot.micro;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReviewsController {
	@Autowired
	private ReviewsService reviewsService;
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	@GetMapping("/reviews")
	public String getAll(Model model, HttpServletRequest request) 
	{
		String error_message = getErrorMessage(request);
		String info_message = getInfoMessage(request);
		List<Reviews> items = this.reviewsService.getAll();
		model.addAttribute("items", items);
		model.addAttribute("error_message", (error_message == "null" ? "" : error_message));
		model.addAttribute("info_message", (info_message == "null" ? "" : info_message));
		return "reviews/list";
	}
	
	@GetMapping("/reviews/{id}")
	public String getItemById(@PathVariable String id, Model model, HttpServletRequest request)
	{
		String error_message = getErrorMessage(request);
		String info_message = getInfoMessage(request);
		List<Reviews> items = this.reviewsService.getItemByProductId(id);
		model.addAttribute("items", items);
		model.addAttribute("error_message", (error_message == "null" ? "" : error_message));
		model.addAttribute("info_message", (info_message == "null" ? "" : info_message));
		return "reviews/list";
	}
	
	@GetMapping("/reviews/update/{id}")
	public String updateItem(@PathVariable int id, Model model)
	{
		Reviews products = this.reviewsService.getItem(id);
		model.addAttribute("reviews", products);
		return "reviews/update";
	}
	
	@RequestMapping(value = "/reviews/update", method = RequestMethod.POST)
    public String updateItem(@ModelAttribute @Valid Reviews item, BindingResult bindingResult, Model model, HttpServletRequest request)
	{
		if (bindingResult.hasErrors()) {
            System.out.println("BINDING RESULT ERROR");
            return "reviews/update";
        } 
		else {
        	this.reviewsService.updateItem(item.getId(), item);
    		boolean isValid = item != null;
    		
    		if(isValid)
    		{
    			request.getSession().setAttribute("info_message", "Information Saved Correctly!");
    		}
    		else
    		{
    			request.getSession().setAttribute("error_message", "There were some errors trying to save your information");
    		}
    		
    		return "redirect:/reviews";	
        }
	}
	
	@GetMapping("/reviews/create")
	public String createItem(Model model)
	{
		model.addAttribute("reviews", new Reviews());
		return "reviews/create";
	}

	@RequestMapping(value = "/reviews/create", method = RequestMethod.POST)
    public String createItem(@ModelAttribute @Valid Reviews item, BindingResult bindingResult, Model model, HttpServletRequest request)
	{
	
		if (bindingResult.hasErrors()) {
            System.out.println("BINDING RESULT ERROR");
            return "reviews/create";
        } 
		else {
			this.reviewsService.createItem(item);
			boolean isValid = item != null;
			
			if(isValid)
			{
				request.getSession().setAttribute("info_message", "Information Saved Correctly!");
			}
			else
			{
				request.getSession().setAttribute("error_message", "There were some errors trying to save your information");
			}
			return "redirect:/reviews";	

		}
		
	}
	
	@GetMapping("/reviews/details/{id}")
	public String detailsItem(@PathVariable int id, Model model)
	{
		Reviews products = this.reviewsService.getItem(id);
		model.addAttribute("reviews", products);
		return "reviews/details";
	}
		
	@RequestMapping(value = "reviews/delete/{id}", method = RequestMethod.POST)
	public ModelAndView deleteItem(@PathVariable int id, HttpServletRequest request)
	{

		this.reviewsService.deleteItem(id);
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
