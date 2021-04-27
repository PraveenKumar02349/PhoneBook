package com.angler.phonebook.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.angler.phonebook.Utils.MasterRequest;
import com.angler.phonebook.model.UserContact;
import com.angler.phonebook.model.UserInfo;

public interface phonebookServiceInterface {

	List<UserInfo> getUserDetails(Pageable pageable, MasterRequest commonRequest);

	long addUser(UserInfo userInfo)throws Exception ;

	boolean updateUser(UserInfo userInfo);

	boolean deleteUser(long userId);

	List<UserInfo> getUser(String id);

}
