package com.example.project_spring.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
        @NamedQuery(name = "magazyn.all", query = "SELECT m FROM Magazyn m"),
        @NamedQuery(name = "magazyn.byName", query = "Select m from Magazyn m where m.name = :name")
})

public class Magazyn {
	
	private Long id;
	
	private String name;
	
	private int amount;
	
	private int margin;

	private List<ToOrder> orders = new ArrayList<ToOrder>();
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getMargin() {
		return margin;
	}

	public void setMargin(int margin) {
		this.margin = margin;
	}
	
	// Be careful here, both with lazy and eager fetch type
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<ToOrder> getOrders() {
		return orders;
	}
	public void setOrders(List<ToOrder> orders) {
		this.orders = orders;
	}
}
