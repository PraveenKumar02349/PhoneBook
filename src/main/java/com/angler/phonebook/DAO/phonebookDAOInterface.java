package com.angler.phonebook.DAO;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.angler.phonebook.Utils.MasterRequest;
import com.angler.phonebook.model.UserContact;
import com.angler.phonebook.model.UserInfo;

public interface phonebookDAOInterface {

	List<UserInfo> getUserDetails(Pageable pageable, MasterRequest commonRequest);

	long addUser(UserInfo userInfo) throws Exception;

	boolean updateUserInfo(String[] columnName, String[] whereArg, Object... whereValue);

	boolean updateUserContact(String[] columnName, String[] whereArg, Object... whereValue);

	List<UserInfo> getUserDetails(String id);

}
