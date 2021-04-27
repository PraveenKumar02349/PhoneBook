package io.javabrains.springbootstarter.topic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringRunner.class)
@WebMvcTest(value = EmployeeController.class, secure = false)
public class EmployeeControllertest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EmployeeService employeeService;


	// GET METHOD
	
	@Test
	public void GET_ALL_DETAILS() throws Exception {
		mockMvc.perform(get("/topics/").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id", Matchers.is(1)))
		.andExpect(jsonPath("$.name",Matchers.is("ABC")))
		.andExpect(jsonPath("$.gender",Matchers.is("male")));
		
	}

	
	// POST METHOD

	@Test
	public void createEmployee() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		Employee employee = new Employee(1, "ABC", "male");
		String jsonInString = mapper.writeValueAsString(employee);
		mockMvc.perform(post("/employees")	
			.contentType(MediaType.APPLICATION_JSON)
			.content(jsonInString))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id").exists())
			.andExpect(jsonPath("$.id",Matchers.is(1)))
			.andExpect(jsonPath("$.name",Matchers.is("ABC")))
			.andExpect(jsonPath("$.gender",Matchers.is("male")))
			.andExpect(jsonPath("$.*", Matchers.hasSize(3)));
		
	}
	
	//To Check create_employee accepts Post Method Not Get Method

	// BAD REQUEST 405
	@Test
	public void CREATEusingGET()throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		Employee employee = new Employee(1, "ABC", "male");
		String jsonInString = mapper.writeValueAsString(employee);
		mockMvc.perform(get("/employees")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonInString))
				.andExpect(status().isMethodNotAllowed());
			
	}
	// SUCCESS 200
	@Test
	public void CREATEusingPOST() throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		Employee employee = new Employee(1, "ABC", "male");
		String jsonInString = mapper.writeValueAsString(employee);
		mockMvc.perform(post("/employees")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonInString))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.*", Matchers.hasSize(3)));
		
			
	}

	//EMPTY VALIDATION_FAILURE
	@Test
    public void EMPTY_VALIDATION_FAILURE() throws Exception {
        String jsonTaskWithBlankName = String.format("{\"id\":1,\"name\":\"\",\"gender\":\"male\"}");
        this.mockMvc.perform(post("/employees")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonTaskWithBlankName))
            .andDo(print())
            .andExpect(status().isBadRequest());
                   
    }	
	
	//EMPTY VALIDATION_SUCCESS
	@Test
	public void EMPTY_VALIDATION_SUCCESS() throws Exception {
		String jsonTask = String.format("{\"id\":1,\"name\":\"ABC\",\"gender\":\"male\"}");

		this.mockMvc.perform(post("/employees")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonTask))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json(jsonTask)); 
																					
	} 

	//KEY_VALIDATION_FAILURE
	@Test
	public void KEY_VALIDATION_FAILURE() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		Employee employee = new Employee();
		employee.setId(1);
		employee.setgender("male");
		String jsonInString = mapper.writeValueAsString(employee);
		mockMvc.perform(post("/employees")
			.contentType(MediaType.APPLICATION_JSON)
			.content(jsonInString))
			.andExpect(status().isBadRequest());
	}

	
	//KEY_VALIDATION_SUCCESS
		@Test
		public void KEY_VALIDATION_SUCCESS() throws Exception {
			ObjectMapper mapper = new ObjectMapper();
			Employee employee = new Employee();
			employee.setId(1);
			employee.setName("ABC");
			employee.setgender("male");
			String jsonInString = mapper.writeValueAsString(employee);
			mockMvc.perform(post("/employees")
					
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonInString))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").exists())
				.andExpect(jsonPath("$.name").exists())
				.andExpect(jsonPath("$.gender").exists())
				.andExpect(jsonPath("$.*", Matchers.hasSize(3)));
	}

	
}


