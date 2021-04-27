package com.angler.phonebook.Utils;

import com.google.gson.annotations.SerializedName;

public class MasterRequest extends PojoUtils {

	@SerializedName("page")
	int page = 0;

	@SerializedName("count")
	int count = 0;

	@SerializedName("USER_ID")
	long userId = 0;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

}
