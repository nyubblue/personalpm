package io.ubun.personpm.utils;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

public class ProjectDTOModelMapper extends RequestResponseBodyMethodProcessor {

	public ProjectDTOModelMapper(List<HttpMessageConverter<?>> converters, EntityManager entityManager) {
		super(converters);
	}

	private static final ModelMapper modelMapper = new ModelMapper();

	private EntityManager entityManager;

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(MyProject.class);
	}

	@Override
	protected void validateIfApplicable(WebDataBinder binder, MethodParameter parameter) {
		binder.validate();
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		Object dto = super.resolveArgument(parameter, mavContainer, webRequest, binderFactory);
		Object id = getEntityId(dto);
		if (id == null) {
			return modelMapper.map(dto, parameter.getParameterType());
		} else {
			 Object persistedObject = entityManager.find(parameter.getParameterType(), id);
			 modelMapper.map(dto, persistedObject);
			 return persistedObject;
		}
	}

	@Override
	protected <T> Object readWithMessageConverters(HttpInputMessage inputMessage, MethodParameter parameter,
			Type targetType) throws IOException, HttpMediaTypeNotSupportedException, HttpMessageNotReadableException {
		for (Annotation ann : parameter.getParameterAnnotations()) {
			MyProject dtoType = AnnotationUtils.getAnnotation(ann, MyProject.class);
			if (dtoType != null) {
				return super.readWithMessageConverters(inputMessage, parameter, dtoType.value());
			}
		}
		throw new RuntimeException();
	}

	private Object getEntityId(@NotNull Object dto) {
		for (Field feild : dto.getClass().getDeclaredFields()) {
			if (feild.getAnnotation(Id.class) != null) {
				try {
					feild.setAccessible(true);
					return feild.get(dto);
				} catch (IllegalAccessException e) {
					throw new RuntimeException(e);
				}
			}
		}
		return null;
	}
}
