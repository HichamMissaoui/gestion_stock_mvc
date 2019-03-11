package com.stock.mvc.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.stock.mvc.entities.Article;
import com.stock.mvc.entities.Category;
import com.stock.mvc.entities.Client;
import com.stock.mvc.services.IArticleService;
import com.stock.mvc.services.ICategoryService;
import com.stock.mvc.services.IFlickrService;

@Controller
@RequestMapping(value = "/article")
public class ArticleController {
	@Autowired
	private IArticleService articleService;
	
	@Autowired
	private ICategoryService categoryService;
	
	@Autowired
	private IFlickrService flickrService;

	@RequestMapping(value = "/")
	public String article(Model model) {
		List<Article> articles = articleService.selectAll();	
		if(articles == null) {
			articles = new ArrayList<Article>();
		}		
		model.addAttribute("articles",articles);		
		return "article/article";
	}
	
	@RequestMapping(value = "/nouveau")
	public String nouveau(Model model) {
		List<Category> categories = categoryService.selectAll();
		if(categories == null) {
			categories = new ArrayList<Category>();
		}
		model.addAttribute("categories",categories);
		model.addAttribute("article",new Article());
		return "article/ajouterArticle";
	}
	
	@RequestMapping(value = "enregistrer")
	public String enegistrer(Model model,Article article,MultipartFile file) {
		if(article != null) {
			if(file != null && !file.isEmpty()) {
				InputStream stream = null;
				String urlPhoto = null;
				try {
					stream = file.getInputStream();
					urlPhoto = flickrService.savePhoto(stream,article.getDesignation());
					article.setPhoto(urlPhoto);
				}catch(Exception e) {
					e.printStackTrace();
				}finally {
					try {
						stream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			if(article.getId() == null) {
				articleService.save(article);
			}else {
				articleService.update(article);
			}
		}
		return "redirect:/article/";
	}
	
	@RequestMapping(value="/modifier/{id}", method = RequestMethod.GET)
	public String modifierarticle(Model model,@PathVariable Long id) {
		if(id != null) {
			Article article = articleService.getById(id);
			if(article != null) {
				List<Category> categories = categoryService.selectAll();
				if(categories == null) {
					categories = new ArrayList<Category>();
				}
				model.addAttribute("categories",categories);
				model.addAttribute("article", article);
				return "article/modifierArticle";
			}
		}
		return "erreur/erreur";
		
	}
	
	@RequestMapping(value="/supprimer/{id}", method = RequestMethod.GET)
	public String supprimerClient(@PathVariable Long id) {
		if(id != null) {
			Article article = articleService.getById(id);
			if(article != null) {
				articleService.remove(id);
				return "redirect:/article/";
			}
			
			}
		return "erreur/erreur";
		
	}
}
