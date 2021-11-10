package io.ubun.personpm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.ubun.personpm.entities.Project;
import io.ubun.personpm.exception.ProjectIdException;
import io.ubun.personpm.repositories.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	public Project saveOrUpdateProject(Project project) {
		try {
			return projectRepository.save(project);
		} catch (Exception e) {
			throw new ProjectIdException("Project ID : "+ project.getProjectIdentifier() + "already exists.");
		}
	}

	
}
