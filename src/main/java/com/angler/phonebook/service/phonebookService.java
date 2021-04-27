package com.angler.phonebook.service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.angler.phonebook.DAO.phonebookDAOInterface;
import com.angler.phonebook.Utils.MasterRequest;
import com.angler.phonebook.Utils.Utils;
import com.angler.phonebook.model.UserInfo;


@Service
public class phonebookService implements phonebookServiceInterface {

	@Autowired
	private phonebookDAOInterface phoneBookDao;

	@Override
	public List<UserInfo> getUserDetails(Pageable pageable, MasterRequest commonRequest) {
		// TODO Auto-generated method stub
		return phoneBookDao.getUserDetails(pageable, commonRequest);
	}

	@Override
	public long addUser(UserInfo userInfo) throws Exception {
		// TODO Auto-generated method stub
		return phoneBookDao.addUser(userInfo);
	}

	@Override
	public boolean updateUser(UserInfo userInfo) {
		// TODO Auto-generated method stub
		Utils.log(""+userInfo.getId());
		Calendar cal = Calendar.getInstance();
		Timestamp timestamp = new Timestamp(cal.getTimeInMillis());
		boolean status = phoneBookDao.updateUserInfo(new String[] { "FIRSTNAME", "LASTNAME", "EMAILID","UPDATED_DATE" },
				new String[] { "USER_ID" }, userInfo.getFirstName(), userInfo.getLastName(), userInfo.getEmailId(),timestamp,
				userInfo.getId());
		if (status)
			status = phoneBookDao.updateUserContact(new String[] { "PHONENUMBER", "ALTERNATEPHONENUMBER","UPDATED_DATE" },
					new String[] { "USER_ID" }, userInfo.getPhoneNumber(), userInfo.getAlternatePhoneNumber(),timestamp,
					userInfo.getId());
		return status;
	}

	@Override
	public boolean deleteUser(long userId) {
		// TODO Auto-generated method stub
		return phoneBookDao.updateUserInfo(new String[] { "DELETE_FLAG" }, new String[] { "USER_ID" }, 0,
				userId);
	}

	@Override
	public List<UserInfo> getUser(String id) {
		// TODO Auto-generated method stub
		return phoneBookDao.getUserDetails(id);
	}

}
