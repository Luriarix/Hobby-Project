package com.qa.hobby.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.qa.hobby.DTO.PlayerDTO;
import com.qa.hobby.domain.Player;
import com.qa.hobby.repo.PlayerRepo;
import com.qa.hobby.utils.PlayerMapper;

@Service
public class PlayerService {

	private PlayerRepo repo;
	
	private PlayerMapper map;

	public PlayerService(PlayerRepo repo, PlayerMapper map) {
		super();
		this.repo = repo;
		this.map = map;
	}
	
	public PlayerDTO createPlayer(Player player) {
		return this.map.mapToDTO(this.repo.save(player));
	}
	
	public List<PlayerDTO> getPlayer(){
		return this.repo.findAll().stream().map(p -> this.map.mapToDTO(p)).collect(Collectors.toList());
	}
	
	public PlayerDTO getPlayerById(Long id) {
		Player exist = this.repo.findById(id).orElseThrow(() -> new EntityNotFoundException());

		return this.map.mapToDTO(exist);	
	}
	
	public PlayerDTO updatePlayer(Long id, Player player) {
		Player exist = this.repo.findById(id).orElseThrow(() -> new EntityNotFoundException());
		
		exist.setName(player.getName());
		
		Player saved = this.repo.save(exist);
		
		return this.map.mapToDTO(saved);
	}
	
	public boolean removePlayer(Long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}
}
