package com.angler.phonebook.controller;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.angler.phonebook.Utils.CommonListResponse;
import com.angler.phonebook.Utils.MasterRequest;
import com.angler.phonebook.Utils.SuccessResponse;
import com.angler.phonebook.model.UserInfo;
import com.angler.phonebook.service.phonebookServiceInterface;

@Transactional
@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:4200")
public class phonebookController {

	@Autowired
	private phonebookServiceInterface phoneBookService;

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public SuccessResponse<CommonListResponse<UserInfo>> userList(HttpServletRequest request,
			@RequestBody MasterRequest masterRequest) throws ParseException {

		SuccessResponse<CommonListResponse<UserInfo>> commonResult = new SuccessResponse<CommonListResponse<UserInfo>>();

		if (masterRequest.getPage() == 0)
			masterRequest.setPage(1);

		if (masterRequest.getPage() >= 1) {
			masterRequest.setPage(masterRequest.getPage() - 1);
		}

		Pageable pageable = null;
		if (masterRequest.getCount() != 0)
			pageable = PageRequest.of(masterRequest.getPage(), masterRequest.getCount());

		List<UserInfo> Userlist = phoneBookService.getUserDetails(pageable, masterRequest);

		CommonListResponse<UserInfo> CommonListResponse = new CommonListResponse<>();
		CommonListResponse.setList(Userlist);
		if (Userlist.size() > 0)
			CommonListResponse.setTotal(Userlist.get(0).getTotalCount());
		commonResult.setStatus(true);
		commonResult.setMessage("User List");
		commonResult.setData(CommonListResponse);
		return commonResult;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public SuccessResponse<CommonListResponse<UserInfo>> createUser(HttpServletRequest request,
			@RequestBody UserInfo userList) {
		SuccessResponse<CommonListResponse<UserInfo>> commonResult = new SuccessResponse<CommonListResponse<UserInfo>>();

		try {
			long userchk = 0;

			long count = phoneBookService.addUser(userList);
			if (count > 0)
				userchk++;

			if (userchk > 0) {
				commonResult.setStatus(true);
				commonResult.setMessage("User Created Successfully");
			} else {
				commonResult.setStatus(false);
				commonResult.setMessage("Invalid Data");
			}
		} catch (Exception e) {
			commonResult.setMessage(e.getMessage());
		}

		return commonResult;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public SuccessResponse<CommonListResponse<UserInfo>> updateUser(HttpServletRequest request,
			@RequestBody UserInfo userList) {
		SuccessResponse<CommonListResponse<UserInfo>> commonResult = new SuccessResponse<CommonListResponse<UserInfo>>();

		try {
			long userchk = 0;
			boolean status = phoneBookService.updateUser(userList);
			if (status)
				userchk++;

			if (userchk > 0) {
				commonResult.setStatus(true);
				commonResult.setMessage("User Updated Successfully");
			} else {
				commonResult.setStatus(false);
				commonResult.setMessage("Invalid Data");
			}
		} catch (Exception e) {
			commonResult.setMessage(e.getMessage());
		}

		return commonResult;
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("delete/{student_id}")
	public SuccessResponse<CommonListResponse<UserInfo>> deleteUser(HttpServletRequest request,
			@PathVariable("student_id") int student_id) throws ParseException {

		SuccessResponse<CommonListResponse<UserInfo>> commonResult = new SuccessResponse<CommonListResponse<UserInfo>>();
		boolean status = phoneBookService.deleteUser(student_id);
		if (status) {
			commonResult.setStatus(true);
			commonResult.setMessage("User Deleted Successfully");
		}
		return commonResult;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("student/{student_id}")
	public SuccessResponse<CommonListResponse<UserInfo>> getUserbyId(HttpServletRequest request,
			@PathVariable("student_id") int student_id) throws ParseException {

		SuccessResponse<CommonListResponse<UserInfo>> commonResult = new SuccessResponse<CommonListResponse<UserInfo>>();
		CommonListResponse<UserInfo> CommonListResponse = new CommonListResponse<>();

		List<UserInfo> list = phoneBookService.getUser(String.valueOf(student_id));
		if (!list.isEmpty()) {
			commonResult.setStatus(true);
			commonResult.setMessage("User List");
			CommonListResponse.setList(list);
			commonResult.setData(CommonListResponse);
		}
		return commonResult;
	}

}
