package com.qa.hobby.utils;

import org.springframework.stereotype.Service;

import com.qa.hobby.DTO.CharacterDTO;
import com.qa.hobby.domain.Characters;

@Service
public class CharacterMapper implements Mapper<Characters, CharacterDTO> {

	@Override
	public CharacterDTO mapToDTO(Characters entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Characters mapFromDTO(CharacterDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
