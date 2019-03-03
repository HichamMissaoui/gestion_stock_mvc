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
public class Vente implements Serializable{
	@Id
	@GeneratedValue()
	private Long id;
	
	private String code;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date datevente;
	
	@ManyToOne
	private Client client;
	
	@OneToMany(mappedBy = "vente")
	private List<LigneVente> lignesVentes;
	
	public Vente() {
		
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

	public Date getDatevente() {
		return datevente;
	}

	public void setDatevente(Date datevente) {
		this.datevente = datevente;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<LigneVente> getLignesVentes() {
		return lignesVentes;
	}

	public void setLignesVentes(List<LigneVente> lignesVentes) {
		this.lignesVentes = lignesVentes;
	}
	
	
	

}
