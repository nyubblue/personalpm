package io.ubun.personpm.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.ubun.personpm.entities.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

}
