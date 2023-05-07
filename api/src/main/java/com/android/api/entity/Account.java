package com.android.api.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 * The persistent class for the accounts database table.
 * 
 */
@Entity
@Table(name = "accounts")
@NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a")
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "account_id")
	private long accountId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_at")
	private Date createAt;

	@Column(name = "hash_code")
	@JsonIgnore
	private String hashCode;

	@Column(name = "is_active")
	private byte isActive;

	@Column(name = "is_deleted")
	private byte isDeleted;

	private String role;

	private String salt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_at")
	private Date updateAt;

	private String username;

	private static final long OTP_VALID_DURATION = 5 * 60 * 1000; // 5 minutes

	@Column(name = "one_time_password")
	@JsonIgnore
	private String oneTimePassword;

	@Column(name = "otp_requested_time")
	@JsonIgnore
	private Date otpRequestedTime;

	public String getOneTimePassword() {
		return oneTimePassword;
	}

	public void setOneTimePassword(String oneTimePassword) {
		this.oneTimePassword = oneTimePassword;
	}

	public Date getOtpRequestedTime() {
		return otpRequestedTime;
	}

	public void setOtpRequestedTime(Date otpRequestedTime) {
		this.otpRequestedTime = otpRequestedTime;
	}

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

	public boolean isOTPExpired() {
		if (this.getOneTimePassword() == null) {
			return false;
		}

		long currentTimeInMillis = System.currentTimeMillis();
		long otpRequestedTimeInMillis = this.otpRequestedTime.getTime();

		if (otpRequestedTimeInMillis + OTP_VALID_DURATION < currentTimeInMillis) {
			// OTP expires
			return false;
		}

		return true;
	}

	@PrePersist
	void createdAt() {
		this.createAt = this.updateAt = new Date();
	}

	@PreUpdate
	void updatedAt() {
		this.updateAt = new Date();
	}

}