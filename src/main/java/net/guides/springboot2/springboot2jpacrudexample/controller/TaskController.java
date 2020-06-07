package net.guides.springboot2.springboot2jpacrudexample.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.guides.springboot2.springboot2jpacrudexample.exception.ResourceNotFoundException;
import net.guides.springboot2.springboot2jpacrudexample.model.Task;
import net.guides.springboot2.springboot2jpacrudexample.repository.TaskRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/task")
public class TaskController {
	@Autowired
	private TaskRepository taskRepository;

	@GetMapping("/")
	public List<Task> getAllTask() {
		return taskRepository.findAll();
	}

	@PostMapping("/")
	public Task createTask(@Valid @RequestBody Task t) {
		return taskRepository.save(t);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Task> updateTask(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Task ti) throws ResourceNotFoundException {
		Task t = taskRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Task not found for this id :: " + id));

		if (ti.getDescription().equals("")) {
			new ResourceNotFoundException("Required description for task ");
		}
		
		t.setId(ti.getId());
		t.setDescription(ti.getDescription());
		t.setStatus(ti.getStatus());
		final Task updatedTask = taskRepository.save(t);
		return ResponseEntity.ok(updatedTask);
	}

}
