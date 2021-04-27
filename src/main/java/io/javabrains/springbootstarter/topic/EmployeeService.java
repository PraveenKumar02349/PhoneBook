package io.javabrains.springbootstarter.topic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	private List<Employee> emp=new ArrayList<>(Arrays.asList(
			new Employee(2,"DEF","male"),
			new Employee(3,"GHI","male"),
			new Employee(4,"IJK","female")				
				));

public List<Employee> getAllTopics(){
	List<Employee> emp=new ArrayList<>();
	employeeRepository.findAll().forEach(emp::add);
	return emp;
}
public Employee getTopic(Integer id) {
	return emp.stream().filter(t -> t.getId().equals(id)).findFirst().get();
}

public void addTopic(Employee employee) {
	employeeRepository.save(employee);

}
public void updateTopic(Integer id,Employee employee)
{
	for(int i=0;i<emp.size();i++)
	{
		Employee e=emp.get(i);
		if(e.getId().equals(id)) {
			emp.set(i, employee);
			
		}
	}
	//topicRepository.save(topic);
}
public void deleteTopic(String id)
{
	
	emp.removeIf(t ->t.getId().equals(id));
	
}

}