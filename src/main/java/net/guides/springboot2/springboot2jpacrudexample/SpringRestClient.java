package net.guides.springboot2.springboot2jpacrudexample;

import org.springframework.web.client.RestTemplate;

import net.guides.springboot2.springboot2jpacrudexample.model.Task;

public class SpringRestClient {

	private static final String CREATE_TASK_ENDPOINT_URL = "http://localhost:8080/task";
	private static RestTemplate restTemplate = new RestTemplate();

	public static void main(String[] args) {
		SpringRestClient springRestClient = new SpringRestClient();
		
		springRestClient.createTask();
		
	}


	private void createTask() {

		Task t = new Task("new task", 200);

		RestTemplate restTemplate = new RestTemplate();
		Task result = restTemplate.postForObject(CREATE_TASK_ENDPOINT_URL, t, Task.class);

		System.out.println(result);
	}

}
