package com.android.api.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the color database table.
 * 
 */
@Entity
@NamedQuery(name="Color.findAll", query="SELECT c FROM Color c")
public class Color implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="color_id")
	private long colorId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_at")
	private Date createAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_at")
	private Date updateAt;

	private String value;

	//bi-directional many-to-one association to CartItem
	@OneToMany(mappedBy="color")
	private List<CartItem> cartItems;

	//bi-directional many-to-many association to Product
	@ManyToMany(mappedBy="colors")
	private List<Product> products;

	//bi-directional many-to-one association to ItemStock
	@OneToMany(mappedBy="color")
	private List<ItemStock> itemStocks;

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

	public List<CartItem> getCartItems() {
		return this.cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public CartItem addCartItem(CartItem cartItem) {
		getCartItems().add(cartItem);
		cartItem.setColor(this);

		return cartItem;
	}

	public CartItem removeCartItem(CartItem cartItem) {
		getCartItems().remove(cartItem);
		cartItem.setColor(null);

		return cartItem;
	}

	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public List<ItemStock> getItemStocks() {
		return this.itemStocks;
	}

	public void setItemStocks(List<ItemStock> itemStocks) {
		this.itemStocks = itemStocks;
	}

	public ItemStock addItemStock(ItemStock itemStock) {
		getItemStocks().add(itemStock);
		itemStock.setColor(this);

		return itemStock;
	}

	public ItemStock removeItemStock(ItemStock itemStock) {
		getItemStocks().remove(itemStock);
		itemStock.setColor(null);

		return itemStock;
	}

}