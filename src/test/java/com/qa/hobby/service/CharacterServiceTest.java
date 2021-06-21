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

import com.qa.hobby.DTO.CharacterDTO;
import com.qa.hobby.domain.Characters;
import com.qa.hobby.repo.CharacterRepo;

@SpringBootTest
@ActiveProfiles("test")
public class CharacterServiceTest {

	@Autowired
	private CharacterService serv;
	
	@MockBean
	private CharacterRepo repo;
	
	@MockBean
	private ModelMapper map;
	
	@Test
	private void testCreate() {
		Characters initial = new Characters(1L, "Azarot");
		CharacterDTO rtn = new CharacterDTO(1L, "Azarot");
		
		Mockito.when(this.repo.save(initial)).thenReturn(initial);
		Mockito.when(this.map.map(initial, CharacterDTO.class)).thenReturn(rtn);
		
		assertThat(this.serv.createCharacter(initial)).isEqualTo(rtn);
		Mockito.verify(this.repo, Mockito.times(1)).save(initial);
		Mockito.verify(this.map, Mockito.times(1)).map(initial, CharacterDTO.class);
	}
	
	@Test
	private void testRead() {
		CharacterDTO rtn = new CharacterDTO(1L, "Azarot");
		List<Characters> initial = List.of(new Characters(1L, "Azarot"));
		
		Mockito.when(this.repo.findAll()).thenReturn(initial);
		Mockito.when(this.map.map(initial, CharacterDTO.class)).thenReturn(rtn);
		
		assertThat(this.serv.getCharacter()).isEqualTo(List.of(rtn));
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
		Mockito.verify(this.map, Mockito.times(1)).map(initial, CharacterDTO.class);
	}
	
	@Test
	private void testUpdate() {
		Characters initial = new Characters("Bob");
		Characters initialFound = new Characters(1L, "Azarot");
		Characters initialSave = new Characters(1L, "Bob");
		CharacterDTO rtn = new CharacterDTO(1L, "Azarot");
		
		Mockito.when(this.repo.findById(1L)).thenReturn(Optional.of(initialFound));
		Mockito.when(this.repo.save(initialSave)).thenReturn(initialSave);
		Mockito.when(this.map.map(initialSave, CharacterDTO.class)).thenReturn(rtn);
		
		assertThat(this.serv.updateCharacter(1L, initial)).isEqualTo(rtn);
		Mockito.verify(this.repo, Mockito.times(1)).findById(1L);
		Mockito.verify(this.repo, Mockito.times(1)).save(initialSave);
		Mockito.verify(this.map, Mockito.times(1)).map(initialSave, CharacterDTO.class);
	}
	
	@Test
	private void testDelete() {
		Long id = 1L;
		
		Mockito.when(this.repo.existsById(id)).thenReturn(false);
		
		assertFalse(this.serv.removeCharacter(id));
		Mockito.verify(this.repo, Mockito.times(1)).existsById(id);

	}
}
