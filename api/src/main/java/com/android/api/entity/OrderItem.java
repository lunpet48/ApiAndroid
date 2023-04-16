package com.android.api.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the order_items database table.
 * 
 */
@Entity
@Table(name="order_items")
@NamedQuery(name="OrderItem.findAll", query="SELECT o FROM OrderItem o")
public class OrderItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="order_item_id")
	private long orderItemId;

	private int count;

	private BigDecimal discount;

	private BigDecimal subtotal;

	//bi-directional many-to-one association to Color
	@ManyToOne
	@JoinColumn(name="color_id")
	private Color color;

	//bi-directional many-to-one association to Size
	@ManyToOne
	@JoinColumn(name="size_id")
	private Size size;

	//bi-directional many-to-one association to Order
	@ManyToOne
	@JoinColumn(name="order_id")
	private Order order;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;

	public OrderItem() {
	}

	public long getOrderItemId() {
		return this.orderItemId;
	}

	public void setOrderItemId(long orderItemId) {
		this.orderItemId = orderItemId;
	}

	public int getCount() {
		return this.count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public BigDecimal getDiscount() {
		return this.discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public BigDecimal getSubtotal() {
		return this.subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public Color getColor() {
		return this.color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Size getSize() {
		return this.size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}