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
import org.springframework.web.servlet.ModelAndView;

import com.stock.mvc.entities.Client;
import com.stock.mvc.services.IClientService;
import com.stock.mvc.services.IFlickrService;

@Controller
@RequestMapping(value = "/client")
public class ClientController {

	@Autowired
	private IClientService clientService;

	@Autowired
	private IFlickrService flickrService;

	@RequestMapping(value = "/")
	public String client(Model model) {
		List<Client> clients = clientService.selectAll();
		if (clients == null) {
			clients = new ArrayList<Client>();
		}
		model.addAttribute("clients", clients);
		return "client/client";
	}

//	@RequestMapping(value = "/nouveau", method = RequestMethod.GET)
//	public String ajouterClient(Model model) {
//		Client client = new Client();
//		model.addAttribute("client", client);
//		return "client/ajouterClient";
//	}
	
	@RequestMapping(value = "/nouveau", method = RequestMethod.GET)
	public ModelAndView ajouterClient() {
		return new ModelAndView("client/ajouterClient","client",new Client());
	}

	@RequestMapping(value = "/enregistrer", method = RequestMethod.POST)
	public String enregisterClient(Model model, Client client, MultipartFile file){
		String photoUrl = null;
		if(client != null) {
			if (file != null && !file.isEmpty()) {
				InputStream stream = null;
				try {
					stream = file.getInputStream();
					photoUrl = flickrService.savePhoto(stream, client.getNom());
					client.setPhoto(photoUrl);
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
			
			if(client.getId() != null) {
				clientService.update(client);

			}else {
				clientService.save(client);				
			}
		}
		
		return "redirect:/client/";
	}
	
	@RequestMapping(value="/modifier/{id}", method = RequestMethod.GET)
	public String modifierClient(Model model,@PathVariable Long id) {
		if(id != null) {
			Client client = clientService.getById(id);
			if(client != null) {
				model.addAttribute("client", client);
				return "client/modifierClient";
			}
		}
		return "erreur/erreur";
		
	}
	
	@RequestMapping(value="/supprimer/{id}", method = RequestMethod.GET)
	public String supprimerClient(@PathVariable Long id) {
		if(id != null) {
			Client client = clientService.getById(id);
			if(client != null) {
				clientService.remove(id);
				return "redirect:/client/";
			}
			
			}
		return "erreur/erreur";
		
	}

}
