package com.android.api.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the products database table.
 * 
 */
@Entity
@Table(name = "products")
@NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "product_id")
	private long productId;

	private String brand;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_at")
	private Date createAt;

	private String description;

	private String image;

	@Column(name = "is_active")
	private byte isActive;

	@Column(name = "is_deleted")
	private byte isDeleted;

	private BigDecimal price;

	@Column(name = "product_name")
	private String productName;

	private BigDecimal rating;

	private int sold;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_at")
	private Date updateAt;

	// bi-directional many-to-one association to CartItem
	@OneToMany(mappedBy = "product")
	private List<CartItem> cartItems;

	// bi-directional many-to-many association to Color
	@ManyToMany(mappedBy = "products")
	private List<Color> colors;

	// bi-directional many-to-one association to Discount
	@OneToMany(mappedBy = "product")
	private List<Discount> discounts;

	// bi-directional many-to-one association to ItemStock
	@OneToMany(mappedBy = "product")
	private List<ItemStock> itemStocks;

	// bi-directional many-to-one association to OrderItem
	@OneToMany(mappedBy = "product")
	private List<OrderItem> orderItems;

	// bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	// bi-directional many-to-one association to Review
	@OneToMany(mappedBy = "product")
	private List<Review> reviews;

	// bi-directional many-to-many association to Size
	@ManyToMany(mappedBy = "products")
	private List<Size> sizes;

	public Product() {
	}

	public long getProductId() {
		return this.productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Date getCreateAt() {
		return this.createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public byte getIsActive() {
		return this.isActive;
	}

	public void setIsActive(byte isActive) {
		this.isActive = isActive;
	}

	public byte getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(byte isDeleted) {
		this.isDeleted = isDeleted;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getRating() {
		return this.rating;
	}

	public void setRating(BigDecimal rating) {
		this.rating = rating;
	}

	public int getSold() {
		return this.sold;
	}

	public void setSold(int sold) {
		this.sold = sold;
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
		cartItem.setProduct(this);

		return cartItem;
	}

	public CartItem removeCartItem(CartItem cartItem) {
		getCartItems().remove(cartItem);
		cartItem.setProduct(null);

		return cartItem;
	}

	public List<Color> getColors() {
		return this.colors;
	}

	public void setColors(List<Color> colors) {
		this.colors = colors;
	}

	public List<Discount> getDiscounts() {
		return this.discounts;
	}

	public void setDiscounts(List<Discount> discounts) {
		this.discounts = discounts;
	}

	public Discount addDiscount(Discount discount) {
		getDiscounts().add(discount);
		discount.setProduct(this);

		return discount;
	}

	public Discount removeDiscount(Discount discount) {
		getDiscounts().remove(discount);
		discount.setProduct(null);

		return discount;
	}

	public List<ItemStock> getItemStocks() {
		return this.itemStocks;
	}

	public void setItemStocks(List<ItemStock> itemStocks) {
		this.itemStocks = itemStocks;
	}

	public ItemStock addItemStock(ItemStock itemStock) {
		getItemStocks().add(itemStock);
		itemStock.setProduct(this);

		return itemStock;
	}

	public ItemStock removeItemStock(ItemStock itemStock) {
		getItemStocks().remove(itemStock);
		itemStock.setProduct(null);

		return itemStock;
	}

	public List<OrderItem> getOrderItems() {
		return this.orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public OrderItem addOrderItem(OrderItem orderItem) {
		getOrderItems().add(orderItem);
		orderItem.setProduct(this);

		return orderItem;
	}

	public OrderItem removeOrderItem(OrderItem orderItem) {
		getOrderItems().remove(orderItem);
		orderItem.setProduct(null);

		return orderItem;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Review> getReviews() {
		return this.reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public Review addReview(Review review) {
		getReviews().add(review);
		review.setProduct(this);

		return review;
	}

	public Review removeReview(Review review) {
		getReviews().remove(review);
		review.setProduct(null);

		return review;
	}

	public List<Size> getSizes() {
		return this.sizes;
	}

	public void setSizes(List<Size> sizes) {
		this.sizes = sizes;
	}

}