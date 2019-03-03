package com.stock.mvc.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class CommandeClient implements Serializable{
	@Id
	@GeneratedValue()
	private Long id;
	
	private String code;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCommande; 
	
	@ManyToOne
	private Client client;
	
	@OneToMany(mappedBy = "commandeClient")
	private List<LigneCommandeClient> ligneCommandeclients;
	
	
	public CommandeClient() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getDateCommande() {
		return dateCommande;
	}

	public void setDateCommande(Date dateCommande) {
		this.dateCommande = dateCommande;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<LigneCommandeClient> getLigneCommandeclients() {
		return ligneCommandeclients;
	}

	public void setLigneCommandeclients(List<LigneCommandeClient> ligneCommandeclients) {
		this.ligneCommandeclients = ligneCommandeclients;
	}
	
	
	

}
