package com.android.api.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The persistent class for the cart_items database table.
 * 
 */
@Entity
@Table(name = "cart_items")
@NamedQuery(name = "CartItem.findAll", query = "SELECT c FROM CartItem c")
public class CartItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "cart_item_id")
	private long cartItemId;

	private int count;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_at")
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Date createAt;

	@Column(name = "is_deleted")
	private byte isDeleted;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_at")
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Date updateAt;

	// bi-directional many-to-one association to Cart
	@ManyToOne
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@JoinColumn(name = "cart_id")
	private Cart cart;

	// bi-directional many-to-one association to Color
	@ManyToOne
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@JoinColumn(name = "color_id")
	private Color color;

	// bi-directional many-to-one association to Product
	@ManyToOne
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@JoinColumn(name = "product_id")
	private Product product;

	// bi-directional many-to-one association to Size
	@ManyToOne
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@JoinColumn(name = "size_id")
	private Size size;

	public CartItem() {
	}

	public long getCartItemId() {
		return this.cartItemId;
	}

	public void setCartItemId(long cartItemId) {
		this.cartItemId = cartItemId;
	}

	public int getCount() {
		return this.count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Date getCreateAt() {
		return this.createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public byte getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(byte isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Date getUpdateAt() {
		return this.updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	public Cart getCart() {
		return this.cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Color getColor() {
		return this.color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Size getSize() {
		return this.size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

}