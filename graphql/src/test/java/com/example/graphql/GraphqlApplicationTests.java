package com.example.graphql;

import com.example.graphql.configuration.Beans;
import com.example.graphql.dto.AuthorDTO;
import com.example.graphql.rest.AuthorRest;
import com.example.graphql.service.AuthorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.AutoConfigureGraphQl;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.graphql.test.tester.HttpGraphQlTester;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureGraphQl
class GraphqlApplicationTests {

	@MockBean
	private AuthorService service;

	@Autowired
	private WebApplicationContext applicationContext;

	@BeforeEach
	void contextLoads() {
		AuthorDTO authorDTO = new AuthorDTO();
		authorDTO.setId(1L);
		authorDTO.setName("Test user");
		when(service.findById(1L)).thenReturn(authorDTO);
	}

	@Test
	void testGetAuthor() {


		WebTestClient client =
				MockMvcWebTestClient.bindToApplicationContext(applicationContext)
						.configureClient()
						.baseUrl("http://localhost:8080/graphql")
						.build();

		HttpGraphQlTester tester = HttpGraphQlTester.create(client);

		tester.document("{\n" +
						"  findPersonById(id: 1) {\n" +
						"    id,\n" +
						"    name,\n" +

						"  }\n" +
						"}")
				.execute()
				.path("")
			  .matchesJson(
					  "{\"findPersonById\":{\"id\": 1, \"name\": \"Test user\"}}" +
					  "");

	}

}
