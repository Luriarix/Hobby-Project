package com.qa.hobby.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.qa.hobby.DTO.PlayerDTO;
import com.qa.hobby.domain.Player;
import com.qa.hobby.repo.PlayerRepo;

@SpringBootTest
@ActiveProfiles("test")
public class PlayerServiceTest {

	@Autowired
	private PlayerService serv;
	
	@MockBean
	private PlayerRepo repo;
	
	@MockBean
	private ModelMapper map;
	
	@Test
	private void testCreate() {
		Player initial = new Player("Azarot");
		Player initialSave = new Player(1L, "Azarot");
		PlayerDTO rtn = new PlayerDTO(1L, "Azarot");
		
		Mockito.when(this.map.map(rtn, Player.class)).thenReturn(initial);
		Mockito.when(this.repo.save(initial)).thenReturn(initialSave);
		Mockito.when(this.map.map(initialSave, PlayerDTO.class)).thenReturn(rtn);
		
		assertThat(this.serv.createPlayer(rtn)).isEqualTo(rtn);
		Mockito.verify(this.repo, Mockito.times(1)).save(initial);
		Mockito.verify(this.map, Mockito.times(1)).map(rtn, Player.class);
		Mockito.verify(this.map, Mockito.times(1)).map(initialSave, PlayerDTO.class);
	}
	
	@Test
	private void testRead() {
		PlayerDTO rtn = new PlayerDTO(1L, "Azarot");
		List<Player> initial = List.of(new Player(1L, "Azarot"));
		
		Mockito.when(this.repo.findAll()).thenReturn(initial);
		Mockito.when(this.map.map(initial, PlayerDTO.class)).thenReturn(rtn);
		
		assertThat(this.serv.getPlayer()).isEqualTo(List.of(rtn));
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
		Mockito.verify(this.map, Mockito.times(1)).map(initial, PlayerDTO.class);
	}
	
	@Test
	private void testUpdate() {
		Player initial = new Player("Bob");
		Player initialFound = new Player(1L, "Azarot");
		Player initialSave = new Player(1L, "Bob");
		PlayerDTO rtn = new PlayerDTO(1L, "Azarot");
		
		Mockito.when(this.repo.findById(1L)).thenReturn(Optional.of(initialFound));
		Mockito.when(this.repo.save(initialSave)).thenReturn(initialSave);
		Mockito.when(this.map.map(initialSave, PlayerDTO.class)).thenReturn(rtn);
		
		assertThat(this.serv.updatePlayer(1L, initial)).isEqualTo(rtn);
		Mockito.verify(this.repo, Mockito.times(1)).findById(1L);
		Mockito.verify(this.repo, Mockito.times(1)).save(initialSave);
		Mockito.verify(this.map, Mockito.times(1)).map(initialSave, PlayerDTO.class);
	}
	
	@Test
	private void testDelete() {
		Long id = 1L;
		
		Mockito.when(this.repo.existsById(id)).thenReturn(false);
		
		assertFalse(this.serv.removePlayer(id));
		Mockito.verify(this.repo, Mockito.times(1)).existsById(id);

	}
}
