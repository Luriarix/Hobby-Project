package com.qa.hobby.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.hobby.DTO.CharacterDTO;
import com.qa.hobby.domain.Characters;
import com.qa.hobby.service.CharacterService;

@RestController
@RequestMapping("/characters")
public class CharacterController {

	private CharacterService service;
	
	@Autowired
	public CharacterController(CharacterService service) {
		super();
		this.service = service;
	}
	
	@PostMapping("/create")
	public CharacterDTO createCharacter(@RequestBody Characters character) {
		return this.service.createCharacter(character);
	}
	
	@GetMapping("/getAll")
	public List<CharacterDTO> getCharacter() {
		return this.service.getCharacter();
	}
	
	@GetMapping("/getCharacter/{id}")
	public CharacterDTO getCharacterById(@PathVariable Long id) {
		return this.service.getCharacterById(id);
	}

	@PutMapping("/update/{id}")
	public CharacterDTO Character(@PathVariable Long id, @RequestBody Character character) {
		return this.service.updateCharacter(id, character);
	}
	
	@DeleteMapping("/delete/{id}")
	public boolean removeCharacter(@PathVariable Long id) {
		return this.service.removeCharacter(id);
	}
}