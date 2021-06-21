package com.qa.hobby.rest;

import static org.assertj.core.api.Assertions.assertThat;

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
		Player player = new Player("Red");
		PlayerDTO newdata = new PlayerDTO(1L, "Red");
		
		Mockito.when(this.serv.createPlayer(player)).thenReturn(newdata);
		
		assertThat(this.contr.createPlayer(player)).isEqualTo(newdata);
		
		Mockito.verify(this.serv, Mockito.times(1)).createPlayer(player);
	}
}
