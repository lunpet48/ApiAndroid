package com.android.api.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the carts database table.
 * 
 */
@Entity
@Table(name = "carts")
@NamedQuery(name = "Cart.findAll", query = "SELECT c FROM Cart c")
public class Cart implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "cart_id")
	private long cartId;

	@Column(name = "count_unique_items")
	private int countUniqueItems;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_at")
	private Date createAt;

	@Column(name = "is_deleted")
	private byte isDeleted;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_at")
	private Date updateAt;

	// bi-directional many-to-one association to CartItem
	@OneToMany(mappedBy = "cart")
	private List<CartItem> cartItems;

	// bi-directional many-to-one association to Customer
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	public Cart() {
	}

	public long getCartId() {
		return this.cartId;
	}

	public void setCartId(long cartId) {
		this.cartId = cartId;
	}

	public int getCountUniqueItems() {
		return this.countUniqueItems;
	}

	public void setCountUniqueItems(int countUniqueItems) {
		this.countUniqueItems = countUniqueItems;
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

	public List<CartItem> getCartItems() {
		return this.cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public CartItem addCartItem(CartItem cartItem) {
		getCartItems().add(cartItem);
		cartItem.setCart(this);

		return cartItem;
	}

	public CartItem removeCartItem(CartItem cartItem) {
		getCartItems().remove(cartItem);
		cartItem.setCart(null);

		return cartItem;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}