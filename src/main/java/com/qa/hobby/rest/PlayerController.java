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

import com.qa.hobby.DTO.PlayerDTO;
import com.qa.hobby.domain.Player;
import com.qa.hobby.service.PlayerService;

@RestController
@RequestMapping("/player")
public class PlayerController {
	
	@Autowired
	private PlayerService service;

	public PlayerController(PlayerService service) {
		super();
		this.service = service;
	}
	
	@PostMapping("/create")
	public PlayerDTO createPlayer(@RequestBody Player player) {
		return this.service.createPlayer(player);
	}
	
	@GetMapping("/getAll")
	public List<PlayerDTO> getPlayer() {
		return this.service.getPlayer();
	}
	
	@GetMapping("/getPlayer/{id}")
	public PlayerDTO getPlayerById(@PathVariable Long id) {
		return this.service.getPlayerById(id);
	}

	@PutMapping("/update/{id}")
	public PlayerDTO Player(@PathVariable Long id, @RequestBody Player player) {
		return this.service.updatePlayer(id, player);
	}
	
	@DeleteMapping("/delete/{id}")
	public boolean removePlayer(@PathVariable Long id) {
		return this.service.removePlayer(id);
	}
}