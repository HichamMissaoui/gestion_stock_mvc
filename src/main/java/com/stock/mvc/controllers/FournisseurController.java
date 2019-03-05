package com.stock.mvc.controllers;

import java.io.File;
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
import org.springframework.web.servlet.ModelAndView;

import com.stock.mvc.entities.Fournisseur;
import com.stock.mvc.services.IFlickrService;
import com.stock.mvc.services.IFournisseurService;


@Controller
@RequestMapping(value="/fournisseur")
public class FournisseurController {
	
	@Autowired
	private IFournisseurService fournisseurService;
	
	@Autowired
	private IFlickrService flickrService;
	
	@RequestMapping(value="/")
	public String fournisseur(Model model) {
		List<Fournisseur> fournisseurs = fournisseurService.selectAll();
		if(fournisseurs == null) {
			fournisseurs = new ArrayList<Fournisseur>();
		}
		model.addAttribute("fournisseurs", fournisseurs);
		return "fournisseur/fournisseur";
	}
	
	@RequestMapping(value="/nouveau")
	public ModelAndView ajouter() {
		return new ModelAndView("fournisseur/ajouterFournisseur","fournisseur",new Fournisseur());
	}
	
	@RequestMapping(value="/enregistrer")
	public String enregistrer(Model model,Fournisseur fournisseur,MultipartFile file) {
		String urlPhoto = null;
		if(fournisseur != null) {
			if(file != null && !file.isEmpty()) {
				InputStream stream = null;
				try {
					stream = file.getInputStream();
					urlPhoto = flickrService.savePhoto(stream, fournisseur.getNom());
					fournisseur.setPhoto(urlPhoto);
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
			if(fournisseur.getId() == null) {
				fournisseurService.save(fournisseur);
			}else {
				fournisseurService.update(fournisseur);
			}
			
		
		}
		return "redirect:/fournisseur/";
	}
	
	@RequestMapping(value="/supprimer/{id}", method = RequestMethod.GET)
	public String supprimer(@PathVariable Long id){
		Fournisseur fournisseur = fournisseurService.getById(id);
		if(fournisseur!= null) {
			fournisseurService.remove(id);
			return "redirect:/fournisseur/";
		}
		return "erreur/erreur";
	}
	
	@RequestMapping(value="/modifier/{id}")
	public String modifier(@PathVariable Long id, Model model) {
		Fournisseur fournisseur = fournisseurService.getById(id);
		if(fournisseur != null ) {
			model.addAttribute("fournisseur", fournisseur);
			return "/fournisseur/modifierFournisseur";
		}
		return "erreur/erreur";
		
	}

}
