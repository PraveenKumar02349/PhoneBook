package io.javabrains.springbootstarter.topic;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import io.javabrains.springbootstarter.topic.Employee;
public interface EmployeeRepository extends CrudRepository<Employee,String> {

	Optional<Employee> findById(Integer id);

	

	
	
	
}
