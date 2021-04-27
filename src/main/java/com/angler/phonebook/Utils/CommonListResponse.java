package com.angler.phonebook.Utils;

import java.util.List;

public class CommonListResponse<T> {
	long total;
	List<T> list;

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> List) {
		this.list = List;
	}

}
