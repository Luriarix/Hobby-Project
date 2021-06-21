package com.qa.hobby.rest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.qa.hobby.DTO.PlayerDTO;
import com.qa.hobby.domain.Player;
import com.qa.hobby.service.PlayerService;

@SpringBootTest
@ActiveProfiles("test")
class PlayerControllerTest {

	@Autowired
	private PlayerController contr;
	
	@MockBean
	private PlayerService serv;

	@Test
	void testCreate() throws Exception {
		PlayerDTO player = new PlayerDTO(1L, "Red");
		
		Mockito.when(this.serv.createPlayer(player)).thenReturn(player);
		
		assertThat(this.contr.createPlayer(player)).isEqualTo(player);
		
		Mockito.verify(this.serv, Mockito.times(1)).createPlayer(player);
	}
	
	@Test
	void testRead() {
		PlayerDTO player = new PlayerDTO(1L, "Red");
		List<PlayerDTO> players = List.of(player);
		
		Mockito.when(this.serv.getPlayer()).thenReturn(players);
		
		assertThat(this.contr.getPlayer()).isEqualTo(players);
		
		Mockito.verify(this.serv, Mockito.times(1)).getPlayer();		
	}
	
	@Test
	void testUpdate() {
		Player player = new Player("Bob");
		PlayerDTO update = new PlayerDTO(1L, "Bob");
		
		Mockito.when(this.serv.updatePlayer(1L, player)).thenReturn(update);
		
		assertThat(this.serv.updatePlayer(1L, player)).isEqualTo(update);
		
		Mockito.verify(this.serv, Mockito.times(1)).updatePlayer(1L, player);	
	}
	
	@Test
	void testDelete() {
		Mockito.when(this.serv.removePlayer(1L)).thenReturn(true);

		assertThat(this.serv.removePlayer(1L)).isEqualTo(true);
		
		Mockito.verify(this.serv, Mockito.times(1)).removePlayer(1L);			
	}
}
