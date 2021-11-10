package io.ubun.personpm.domain;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProjectDTO {
	@NotEmpty
	private String projectName;
	@NotEmpty
	private String projectIdentifier;
}
