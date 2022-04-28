package com.spring.boot.micro;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;


import com.sun.istack.NotNull;

@Entity
@Table(name="products")
public class Products {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message = "Name is mandatory")
	@NotNull()
	@Column(name="Name")
	private String Name;
	
	@NotBlank(message = "price is mandatory")
	@NotNull()
	@Column(name="Price")
	private String Price;
	
	@NotBlank(message = "quantity is mandatory")
	@NotNull()
	@Column(name="Quantity")
	private String Quantity;
	

	@NotBlank(message = "location is mandatory")
	@NotNull()
	@Column(name="Location")
	private String Location;
	


	
	public Products(String Name, String Price, String Quantity, String Location) {
		super();
		this.Name = Name;
		this.Price = Price;
		this.Quantity = Quantity;
		this.Location = Location;

	}
	
	public Products() {	
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return Name;
	}

	public void setName(String Name) {
		this.Name = Name;
	}

	public String getPrice() {
		return Price;
	}

	public void setPrice(String price) {
		this.Price = price;
	}

	public String getQuantity() {
		return Quantity;
	}

	public void setQuantity(String quantity) {
		this.Quantity = quantity;
	}

	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		this.Location = location;
	}

}
