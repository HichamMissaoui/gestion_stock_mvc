package com.stock.mvc.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.stock.mvc.entities.Category;
import com.stock.mvc.services.ICategoryService;

@Controller
@RequestMapping(value="/category")
public class CategoryController {
	
	@Autowired
	private ICategoryService categoryService;
	
	@RequestMapping(value="/")
	public String category(Model model) {
		List<Category> categories = categoryService.selectAll();
		if(categories == null) {
			categories = new ArrayList<Category>();
		}
		model.addAttribute("categories",categories);
		return "category/category";
	}
	
    @RequestMapping("/nouveau")
    public ModelAndView nouveau() {
    	return new ModelAndView("category/ajouterCategory","category",new Category());
    }
    
    @RequestMapping(value="/enregistrer")
    public String enregistrer(Model model,Category category) {
    	if(category != null) {
    		if(category.getId() == null) {
    			categoryService.save(category);
    		}else {
    			categoryService.update(category);
    		}
    		
    	}
    	return "redirect:/category/";
    }
    
    @RequestMapping(value="/supprimer/{id}", method = RequestMethod.GET)
	public String supprimer(@PathVariable Long id){
    	Category category = categoryService.getById(id);
		if(category!= null) {
			categoryService.remove(id);
			return "redirect:/category/";
		}
		return "erreur/erreur";
	}
	
	@RequestMapping(value="/modifier/{id}")
	public String modifier(@PathVariable Long id, Model model) {
		Category category = categoryService.getById(id);
		if(category != null ) {
			model.addAttribute("category", category);
			return "/category/modifierCategory";
		}
		return "erreur/erreur";
		
	}

}
