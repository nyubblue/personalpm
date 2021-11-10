package io.ubun.personpm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.ubun.personpm.domain.ProjectDTO;
import io.ubun.personpm.services.ProjectService;
import io.ubun.personpm.utils.MyProject;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@PostMapping("/add")
	public ResponseEntity<?> createNewProject( @MyProject(ProjectDTO.class) ProjectDTO project ) {
		return new ResponseEntity<ProjectDTO>(project, HttpStatus.CREATED);
	}
}
