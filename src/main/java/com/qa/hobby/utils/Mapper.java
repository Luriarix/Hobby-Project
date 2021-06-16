package com.qa.hobby.utils;

public interface Mapper<O, D> {

	D mapToDTO(O entity);
	
	O mapFromDTO(D dto);
}
