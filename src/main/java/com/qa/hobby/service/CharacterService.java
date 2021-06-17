package com.qa.hobby.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.qa.hobby.DTO.CharacterDTO;
import com.qa.hobby.domain.Characters;
import com.qa.hobby.repo.CharacterRepo;
import com.qa.hobby.utils.CharacterMapper;

@Service
public class CharacterService {
	
	private CharacterRepo repo;
	
	private CharacterMapper map;

	public CharacterService(CharacterRepo repo, CharacterMapper map) {
		super();
		this.repo = repo;
		this.map = map;
	}

	public CharacterDTO createCharacter(Characters character) {
		return this.map.mapToDTO(this.repo.save(character));
	}

	public List<CharacterDTO> getCharacter() {
		return this.repo.findAll().stream().map(charac -> this.map.mapToDTO(charac)).collect(Collectors.toList());
	}

	public CharacterDTO getCharacterById(Long id) {
		Characters exist = this.repo.findById(id).orElseThrow(() -> new EntityNotFoundException());

		return this.map.mapToDTO(exist);	
	}

	public CharacterDTO updateCharacter(Long id, Character character) {
		Characters exist = this.repo.findById(id).orElseThrow(() -> new EntityNotFoundException());

		return null;
	}

	public boolean removeCharacter(Long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}

}
