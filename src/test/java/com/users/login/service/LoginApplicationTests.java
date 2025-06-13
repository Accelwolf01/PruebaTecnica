package com.users.login.service;

import com.users.login.client.AuthClient;
import com.users.login.dto.Usuario;
import com.users.login.repository.persistence.LoginLogRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class LoginApplicationTests {

	@InjectMocks
	LoginServiceImpl loginService;

	@Mock
	LoginLogRepository loginLogRepository;

	@Mock
	AuthClient authClient;

	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void obtenerUsuarioTest() {
		String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9";
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		usuario.setUsername("sophiab");
		usuario.setEmail("sophia.brown@x.dummyjson.com");
		usuario.setFirstName("Sophia");
		usuario.setLastName("Brown");
		usuario.setGender("female");
		usuario.setImage("https://dummyjson.com/icon/sophiab/128");

		when(authClient.obtenerUsuario(token)).thenReturn(usuario);
		loginService.obtenerUsuario(token);
		assertEquals(1L, usuario.getId());
	}

}
