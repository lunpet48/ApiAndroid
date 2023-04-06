package com.android.api.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;


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

	@Column(name="color_id")
	private BigInteger colorId;

	private int count;

	private BigDecimal discount;

	@Column(name="order_id")
	private BigInteger orderId;

	@Column(name="product_id")
	private BigInteger productId;

	@Column(name="size_id")
	private BigInteger sizeId;

	private BigDecimal subtotal;

	public OrderItem() {
	}

	public long getOrderItemId() {
		return this.orderItemId;
	}

	public void setOrderItemId(long orderItemId) {
		this.orderItemId = orderItemId;
	}

	public BigInteger getColorId() {
		return this.colorId;
	}

	public void setColorId(BigInteger colorId) {
		this.colorId = colorId;
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

	public BigInteger getOrderId() {
		return this.orderId;
	}

	public void setOrderId(BigInteger orderId) {
		this.orderId = orderId;
	}

	public BigInteger getProductId() {
		return this.productId;
	}

	public void setProductId(BigInteger productId) {
		this.productId = productId;
	}

	public BigInteger getSizeId() {
		return this.sizeId;
	}

	public void setSizeId(BigInteger sizeId) {
		this.sizeId = sizeId;
	}

	public BigDecimal getSubtotal() {
		return this.subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

}