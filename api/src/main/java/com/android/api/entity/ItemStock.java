package com.android.api.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;

/**
 * The persistent class for the item_stocks database table.
 * 
 */
@Entity
@Table(name = "item_stocks")
@NamedQuery(name = "ItemStock.findAll", query = "SELECT i FROM ItemStock i")
public class ItemStock implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "item_stock_id")
	private long itemStockId;

	private int count;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_at")
	private Date createAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_at")
	private Date updateAt;

	// bi-directional many-to-one association to Color
	@ManyToOne
	@JoinColumn(name = "color_id")
	private Color color;

	// bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	// bi-directional many-to-one association to Size
	@ManyToOne
	@JoinColumn(name = "size_id")
	private Size size;

	public ItemStock() {
	}

	public long getItemStockId() {
		return this.itemStockId;
	}

	public void setItemStockId(long itemStockId) {
		this.itemStockId = itemStockId;
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

	public Date getUpdateAt() {
		return this.updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
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