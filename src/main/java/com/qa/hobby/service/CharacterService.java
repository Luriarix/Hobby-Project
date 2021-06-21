package com.qa.hobby.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.qa.hobby.DTO.CharacterDTO;
import com.qa.hobby.domain.Characters;
import com.qa.hobby.repo.CharacterRepo;


@Service
public class CharacterService {
	
	private CharacterRepo repo;
	
	private ModelMapper map;

	public CharacterService(CharacterRepo repo, ModelMapper map) {
		super();
		this.repo = repo;
		this.map = map;
	}

	public CharacterDTO createCharacter(Characters character) {
		return this.map.map(this.repo.save(character), CharacterDTO.class);
	}

	public List<CharacterDTO> getCharacter() {
		return this.repo.findAll().stream().map(charac -> this.map.map(charac, CharacterDTO.class)).collect(Collectors.toList());
	}

	public CharacterDTO getCharacterById(Long id) {
		Characters exist = this.repo.findById(id).orElseThrow(() -> new EntityNotFoundException());

		return this.map.map(exist, CharacterDTO.class);	
	}

	public CharacterDTO updateCharacter(Long id, Characters character) {
		Characters exist = this.repo.findById(id).orElseThrow(() -> new EntityNotFoundException());

		exist.setName(character.getName());
		exist.setPlayer(character.getPlayer());
		
		return this.map.map(this.repo.save(exist), CharacterDTO.class);
	}

	public boolean removeCharacter(Long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}

}
