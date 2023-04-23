package com.android.api.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The persistent class for the size database table.
 * 
 */
@Entity
@NamedQuery(name = "Size.findAll", query = "SELECT s FROM Size s")
public class Size implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "size_id")
	private long sizeId;

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
	@JoinTable(name = "size_product", joinColumns = {
			@JoinColumn(name = "size_id")
	}, inverseJoinColumns = {
			@JoinColumn(name = "product_id")
	})
	private List<Product> products;

	public Size() {
	}

	public long getSizeId() {
		return this.sizeId;
	}

	public void setSizeId(long sizeId) {
		this.sizeId = sizeId;
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