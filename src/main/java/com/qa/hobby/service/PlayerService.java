package com.qa.hobby.service;

import java.util.List;
import java.util.ArrayList;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.qa.hobby.DTO.PlayerDTO;
import com.qa.hobby.domain.Player;
import com.qa.hobby.repo.PlayerRepo;

@Service
public class PlayerService {

	private PlayerRepo repo;
	
	private ModelMapper mapper;

	public PlayerService(PlayerRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}
	
	public PlayerDTO createPlayer(PlayerDTO player) {
		return this.mapper.map(this.repo.save(this.mapper.map(player, Player.class)), PlayerDTO.class);
	}
	
	public List<PlayerDTO> getPlayer(){
		List<Player> players = this.repo.findAll();
		List<PlayerDTO> dtos = new ArrayList<>();
		
		for (Player p : players) {
			PlayerDTO dto = this.mapper.map(p, PlayerDTO.class);
			dtos.add(dto);
		}
		return dtos;
	}
	
	public PlayerDTO getPlayerById(Long id) {
		Player exist = this.repo.findById(id).orElseThrow(() -> new EntityNotFoundException());

		return this.mapper.map(exist, PlayerDTO.class);	
	}
	
	public PlayerDTO updatePlayer(Long id, Player player) {
		Player exist = this.repo.findById(id).orElseThrow(() -> new EntityNotFoundException());
		
		exist.setName(player.getName());
		
		Player saved = this.repo.save(exist);
		
		return this.mapper.map(saved, PlayerDTO.class);
	}
	
	public boolean removePlayer(Long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}
}
