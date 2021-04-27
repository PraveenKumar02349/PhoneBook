package com.angler.phonebook.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.angler.phonebook.Utils.PojoUtils;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.google.gson.annotations.SerializedName;

@Entity
@Table(name = "USER_INFO")
public class UserInfo extends PojoUtils implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SerializedName("id")
	@JsonInclude(Include.NON_NULL)
	@Column(name = "USER_ID")
	private long id;

	@NotEmpty(message = "First name is required")
	@SerializedName("first_name")
	@Column(name = "FirstName")
	private String FirstName;

	
	@NotEmpty(message = "Last name is required")
	@SerializedName("last_name")
	@Column(name = "LastName")
	private String LastName;

	@NotEmpty(message = "Email is required")
	@SerializedName("email_id")
	@Column(name = "EMAILID")
	private String emailId;

	@JsonInclude(Include.NON_NULL)
	@SerializedName("phone_number")
	@Column(name = "PhoneNumber")
	private String phoneNumber;

	@NotEmpty(message = "Alternate Phone Number is required")
	@SerializedName("alt_phone_number")
	@Column(name = "AlternatePhoneNumber")
	private String alternatePhoneNumber;

	@JsonInclude(Include.NON_NULL)
	@Column(name = "total_count")
	private long totalCount;

	@JsonInclude(Include.NON_NULL)
	@Column(name = "CREATED_BY")
	private BigDecimal createdBy;

	@JsonInclude(Include.NON_NULL)
	@Column(name = "CREATED_DATE")
	private String createdDate;

	@JsonInclude(Include.NON_NULL)
	@SerializedName("delete_flag")
	@Column(name = "DELETE_FLAG")
	private BigDecimal deleteFlag;

	@JsonInclude(Include.NON_NULL)
	@Column(name = "UPDATED_BY")
	private BigDecimal updatedBy;

	@JsonInclude(Include.NON_NULL)
	@Column(name = "UPDATED_DATE")
	private Timestamp updatedDate;

	public long getId() {
		return checkResultAsLong(id);
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return checkResult(FirstName);
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return checkResult(LastName);
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getEmailId() {
		return checkResult(emailId);
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
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
		return "UserInfo [id=" + id + ", FirstName=" + FirstName + ", LastName=" + LastName + ", emailId=" + emailId
				+ ", phoneNumber=" + phoneNumber + ", alternatePhoneNumber=" + alternatePhoneNumber + ", totalCount="
				+ totalCount + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", deleteFlag="
				+ deleteFlag + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + "]";
	}

}
