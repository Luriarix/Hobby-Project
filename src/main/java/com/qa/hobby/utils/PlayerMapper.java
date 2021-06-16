package com.qa.hobby.utils;

import org.springframework.stereotype.Service;

import com.qa.hobby.DTO.PlayerDTO;
import com.qa.hobby.domain.Player;

@Service
public class PlayerMapper implements Mapper<Player, PlayerDTO> {

	@Override
	public PlayerDTO mapToDTO(Player player) {
		PlayerDTO dto = new PlayerDTO();
		
		dto.setId(player.getId());
		dto.setName(player.getName());
		
		return dto;
	}

	@Override
	public Player mapFromDTO(PlayerDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}


	
	
}
