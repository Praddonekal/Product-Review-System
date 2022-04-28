
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
@Table(name="reviews")
public class Reviews {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message = "Name is mandatory")
	@NotNull()
	@Column(name="Name")
	private String Name;
	
	@NotBlank(message = "Data is mandatory")
	@NotNull()
	@Column(name="Data")
	private String Data;
	
	@NotBlank(message = "Comment is mandatory")
	@NotNull()
	@Column(name="Comment")
	private String Comment;
	

	@NotBlank(message = "Rating is mandatory")
	@NotNull()
	@Column(name="Rating")
	private String Rating;
	
	@NotBlank(message = "ProductId is mandatory")
	@NotNull()
	@Column(name="ProductId")
	private String ProductId;
	


	
	public Reviews(String Name, String Data, String Comment, String Rating, String ProductId) {
		super();
		this.Name = Name;
		this.Data = Data;
		this.Comment = Comment;
		this.Rating = Rating;
		this.ProductId = ProductId;

	}
	
	public Reviews() {	
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

	public String getData() {
		return Data;
	}

	public void setData(String Data) {
		this.Data = Data;
	}

	public String getComment() {
		return Comment;
	}

	public void setComment(String Comment) {
		this.Comment = Comment;
	}

	public String getRating() {
		return Rating;
	}

	public void setRating(String Rating) {
		this.Rating = Rating;
	}
	
	public String getProductId() {
		return ProductId;
	}

	public void setProductId(String ProductId) {
		this.ProductId = ProductId;
	}

}
