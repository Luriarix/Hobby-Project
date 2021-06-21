package com.qa.hobby.rest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.qa.hobby.DTO.CharacterDTO;
import com.qa.hobby.domain.Characters;
import com.qa.hobby.service.CharacterService;


@SpringBootTest
@ActiveProfiles("test")
public class CharacterControllerTest {

	@Autowired
	private CharacterController contr;
	
	@MockBean
	private CharacterService serv;

	@Test
	void testCreate() throws Exception {
		Characters character = new Characters("Azarot");
		CharacterDTO newCharacter = new CharacterDTO(1L, "Azarot");
		
		Mockito.when(this.serv.createCharacter(character)).thenReturn(newCharacter);
		
		assertThat(this.contr.createCharacter(character)).isEqualTo(newCharacter);
		
		Mockito.verify(this.serv, Mockito.times(1)).createCharacter(character);
	}
	
	@Test
	void testRead() {
		CharacterDTO character = new CharacterDTO(1L, "Azarot");
		List<CharacterDTO> characters = List.of(character);
		
		Mockito.when(this.serv.getCharacter()).thenReturn(characters);
		
		assertThat(this.contr.getCharacter()).isEqualTo(characters);
		
		Mockito.verify(this.serv, Mockito.times(1)).getCharacter();		
	}
	
	@Test
	void testUpdate() {
		Characters character = new Characters("Bob");
		CharacterDTO update = new CharacterDTO(1L, "Bob");
		
		Mockito.when(this.serv.updateCharacter(1L, character)).thenReturn(update);
		
		assertThat(this.serv.updateCharacter(1L, character)).isEqualTo(update);
		
		Mockito.verify(this.serv, Mockito.times(1)).updateCharacter(1L, character);	
	}
	
	@Test
	void testDelete() {
		Mockito.when(this.serv.removeCharacter(1L)).thenReturn(true);

		assertThat(this.serv.removeCharacter(1L)).isEqualTo(true);
		
		Mockito.verify(this.serv, Mockito.times(1)).removeCharacter(1L);			
	}
}
