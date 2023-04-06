package com.android.api.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the size database table.
 * 
 */
@Entity
@NamedQuery(name="Size.findAll", query="SELECT s FROM Size s")
public class Size implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="size_id")
	private long sizeId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_at")
	private Date createAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_at")
	private Date updateAt;

	private String value;

	//bi-directional many-to-one association to CartItem
	@OneToMany(mappedBy="size")
	private List<CartItem> cartItems;

	//bi-directional many-to-one association to ItemStock
	@OneToMany(mappedBy="size")
	private List<ItemStock> itemStocks;

	//bi-directional many-to-many association to Product
	@ManyToMany
	@JoinTable(
		name="size_product"
		, joinColumns={
			@JoinColumn(name="size_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="product_id")
			}
		)
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

	public List<CartItem> getCartItems() {
		return this.cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public CartItem addCartItem(CartItem cartItem) {
		getCartItems().add(cartItem);
		cartItem.setSize(this);

		return cartItem;
	}

	public CartItem removeCartItem(CartItem cartItem) {
		getCartItems().remove(cartItem);
		cartItem.setSize(null);

		return cartItem;
	}

	public List<ItemStock> getItemStocks() {
		return this.itemStocks;
	}

	public void setItemStocks(List<ItemStock> itemStocks) {
		this.itemStocks = itemStocks;
	}

	public ItemStock addItemStock(ItemStock itemStock) {
		getItemStocks().add(itemStock);
		itemStock.setSize(this);

		return itemStock;
	}

	public ItemStock removeItemStock(ItemStock itemStock) {
		getItemStocks().remove(itemStock);
		itemStock.setSize(null);

		return itemStock;
	}

	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}