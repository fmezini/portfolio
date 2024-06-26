package com.example.bdd.integration;

import com.example.bdd.dto.AuthorDTO;
import com.example.bdd.entity.Author;
import com.example.bdd.repository.AuthorRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
public class CucumberIntegrationEndToEndTest extends BddIntegrationTests {

    @Autowired
    private AuthorRepository repository;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    private ResponseEntity<AuthorDTO> result;

    private HttpStatusCodeException exception;

    @Given("the following users exist")
    public void setUp(io.cucumber.datatable.DataTable dataTable) {

        List<List<String>> lists = dataTable.asLists();

        lists.stream().forEach(
                l -> {
                     Author author = new Author();
                     author.setId(Long.valueOf(l.get(0)));
                     author.setName(l.get(1));
                     repository.save(author);
                }
        );

    }


    @When("The client retrieves the author by {int}")
    public void clientRetrievesAuthorById(Integer id) throws Exception {
        try {
            result = restTemplateBuilder.build().getForEntity("http://localhost:" + port + "/author/id/{id}", AuthorDTO.class, Map.of("id", id));
        } catch (HttpStatusCodeException e) {
            exception = e;
        }
    }

    @Then("The client receives status code of 200")
    public void clientReceivesAStatusCode200() {
        assertThat(result.getStatusCode().value()).isEqualTo(200);
    }

    @And("The client receives author info with {int} and {string}")
    public void clientReceiveTheAuthorInfo(Integer id, String name) throws Exception {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(Long.valueOf(id));
        authorDTO.setName(name);
        authorDTO.setPhones(new ArrayList<>());
        authorDTO.setBookList(new ArrayList<>());
        assertThat(authorDTO).isEqualTo(result.getBody());
    }


    @Then("The client receives status code of 404")
    public void clientReceivesAStatusCode404() {
        assertThat(exception.getStatusCode().value()).isEqualTo(404);
    }
}
