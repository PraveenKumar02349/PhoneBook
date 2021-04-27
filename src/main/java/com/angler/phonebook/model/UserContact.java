package com.angler.phonebook.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.angler.phonebook.Utils.PojoUtils;
import com.google.gson.annotations.SerializedName;

@Entity
@Table(name = "USER_CONTACT")
public class UserContact extends PojoUtils implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SerializedName("id")
	@Column(name = "ID")
	private long id;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private long userId;

	@NotEmpty(message = "Phone Number is required")
	@SerializedName("phone_number")
	@Column(name = "PhoneNumber")
	private String phoneNumber;

	@NotEmpty(message = "Alternate Phone Number is required")
	@SerializedName("alt_phone_number")
	@Column(name = "AlternatePhoneNumber")
	private String alternatePhoneNumber;

	@OneToOne
	@PrimaryKeyJoinColumn(name = "USER_ID")
	UserInfo userInfo;

	@Column(name = "total_count")
	private long totalCount;

	@Column(name = "CREATED_BY")
	private BigDecimal createdBy;

	@Column(name = "CREATED_DATE")
	private String createdDate;

	@SerializedName("delete_flag")
	@Column(name = "DELETE_FLAG")
	private BigDecimal deleteFlag;

	@Column(name = "UPDATED_BY")
	private BigDecimal updatedBy;

	@Column(name = "UPDATED_DATE")
	private Timestamp updatedDate;

	public long getId() {
		return checkResultAsLong(id);
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAlternatePhoneNumber() {
		return alternatePhoneNumber;
	}

	public void setAlternatePhoneNumber(String alternatePhoneNumber) {
		this.alternatePhoneNumber = alternatePhoneNumber;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public BigDecimal getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(BigDecimal createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public BigDecimal getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(BigDecimal deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public BigDecimal getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(BigDecimal updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Override
	public String toString() {
		return "UserContact [id=" + id + ", userId=" + userId + ", phoneNumber=" + phoneNumber
				+ ", alternatePhoneNumber=" + alternatePhoneNumber + ", userInfo=" + userInfo + ", totalCount="
				+ totalCount + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", deleteFlag="
				+ deleteFlag + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + "]";
	}

}
