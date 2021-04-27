package io.javabrains.springbootstarter.topic;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import io.javabrains.springbootstarter.topic.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	//private EmployeeRepository employeeRepository;
	
	public String mapToJson(Object obj) throws JsonProcessingException {
		 ObjectMapper objectMapper = new ObjectMapper();
	      return objectMapper.writeValueAsString(obj);
	}
	     
	@GetMapping(value = "/topics/", produces = MediaType.APPLICATION_JSON_VALUE)
	public Employee employee() {
		return new Employee(1, "ABC", "male");
	}

//	@RequestMapping("/topics")
//	public List<Employee> getAllTopics() {
//		return employeeService.getAllTopics();
//	}

	@RequestMapping("/topics/{id}")
	public Employee getTopic(@PathVariable Integer id) {
		return employeeService.getTopic(id); 
		//return new Topic("js", "js framework", "describe js"); //Testing
		
	}
//	@RequestMapping(method = RequestMethod.POST, value = "/topics")
//	public @ResponseBody Optional<Employee> addTopic(@RequestBody Employee employee) {
//		Employee savedTopic = employeeRepository.save(employee);
//		employeeRepository.save(employee);
//		//return employeeRepository.findById(savedTopic.getId());
//		return employeeRepository.findById(savedTopic.getId());
//	}
//	
//
	@RequestMapping(method = RequestMethod.POST, value = "/employees")
	public Employee addTopic(@Valid @RequestBody Employee employee) {
		
		employeeService.addTopic(employee);
		return employee;  //Testing
		
	}
	

	@RequestMapping(method = RequestMethod.PUT, value = "/topics/{id}")
	public Employee UpdateTopic(@RequestBody Employee employee, @PathVariable Integer id) {
		employeeService.updateTopic(id, employee);
		return employee;
	}
	
	
	
//
//	@RequestMapping(method = RequestMethod.DELETE, value = "/topics/{id}")
//	public void deleteTopic(@PathVariable Integer id) {
//		employeeService.deleteTopic(id);
//	}

}
