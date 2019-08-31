package com.clientui.beans;

public class ExpeditionBean {
	private int id;

	private int idCommande;

	private String etat;

	public ExpeditionBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdCommande() {
		return idCommande;
	}

	public void setIdCommande(int idCommande) {
		this.idCommande = idCommande;
	}

	public String getEtat() {
		
		return etat.equals("0")?"En préparation":etat.equals("1")?"expédiée":"livrée";	}

	public void setEtat(String etat) {
		this.etat = etat;
	}
}
