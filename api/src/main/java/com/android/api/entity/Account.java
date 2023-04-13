package com.android.api.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the accounts database table.
 * 
 */
@Entity
@Table(name="accounts")
@NamedQuery(name="Account.findAll", query="SELECT a FROM Account a")
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="account_id")
	private long accountId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_at")
	private Date createAt;

	@Column(name="hash_code")
	private String hashCode;

	@Column(name="is_active")
	private byte isActive;

	@Column(name="is_deleted")
	private byte isDeleted;

	private String role;

	private String salt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_at")
	private Date updateAt;

	private String username;

	//bi-directional many-to-one association to Customer
	@OneToMany(mappedBy="account")
	private List<Customer> customers;

	public Account() {
	}

	public long getAccountId() {
		return this.accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public Date getCreateAt() {
		return this.createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public String getHashCode() {
		return this.hashCode;
	}

	public void setHashCode(String hashCode) {
		this.hashCode = hashCode;
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

	public String getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role.name();
	}

	public String getSalt() {
		return this.salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Date getUpdateAt() {
		return this.updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Customer> getCustomers() {
		return this.customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public Customer addCustomer(Customer customer) {
		getCustomers().add(customer);
		customer.setAccount(this);

		return customer;
	}

	public Customer removeCustomer(Customer customer) {
		getCustomers().remove(customer);
		customer.setAccount(null);

		return customer;
	}

}