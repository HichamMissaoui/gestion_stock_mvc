package com.stock.mvc.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class CommandeFournisseur implements Serializable{

	@Id
	@GeneratedValue()
	private Long id;
	
	private String code;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	@ManyToOne
	private Fournisseur fournisseur;
	
	@OneToMany(mappedBy = "commandeFournisseur")
	private List<LigneCommandeFournisseur> lignesCommandeFournisseurs;
	
	public CommandeFournisseur() {
		
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Fournisseur getFournisseur() {
		return fournisseur;
	}

	public void setFournisseur(Fournisseur fournisseur) {
		this.fournisseur = fournisseur;
	}

	public List<LigneCommandeFournisseur> getLignesCommandeFournisseurs() {
		return lignesCommandeFournisseurs;
	}

	public void setLignesCommandeFournisseurs(List<LigneCommandeFournisseur> lignesCommandeFournisseurs) {
		this.lignesCommandeFournisseurs = lignesCommandeFournisseurs;
	}
	
	
	
}
