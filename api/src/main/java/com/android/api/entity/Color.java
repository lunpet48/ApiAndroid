package com.android.api.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 * The persistent class for the color database table.
 * 
 */
@Entity
@NamedQuery(name = "Color.findAll", query = "SELECT c FROM Color c")
public class Color implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "color_id")
	private long colorId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_at")
	private Date createAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_at")
	private Date updateAt;

	private String value;

	// bi-directional many-to-many association to Product
	@ManyToMany
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	@JoinTable(name = "color_product", joinColumns = {
			@JoinColumn(name = "color_id")
	}, inverseJoinColumns = {
			@JoinColumn(name = "product_id")
	})
	private List<Product> products;

	public Color() {
	}

	public long getColorId() {
		return this.colorId;
	}

	public void setColorId(long colorId) {
		this.colorId = colorId;
	}

	public Date getCreateAt() {
		return this.createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Date getUpdateAt() {
		return this.updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}