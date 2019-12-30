package petstore.pet.spring.controller;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import petstore.pet.adapter.model.CategoryResponse;
import petstore.pet.adapter.model.PetRequest;
import petstore.pet.adapter.model.PetResponse;
import petstore.pet.domain.entity.Category;
import petstore.pet.spring.Entrypoint;
import petstore.pet.usecase.port.CategoryDatastore;
import petstore.pet.usecase.port.PetIdGenerator;

/**
 * 
 * @author fabiojose
 *
 */
@Tag("integration")
@SpringBootTest(
	webEnvironment = WebEnvironment.RANDOM_PORT,
	classes = Entrypoint.class
)
@ExtendWith(MockitoExtension.class)
public class PetRegisterControllerSpringTest {
	
	@MockBean
	//@Autowired
	CategoryDatastore categories;
	
	@MockBean
	PetIdGenerator idGenerator;

	@Autowired
	WebTestClient http;
	
	@Test
	public void should_be_ok() {
		
		String categoryId = "cat889";
		String categoryName = "Cat 889";
		String caregoryDescription = "Cat 889 Description";
		Category category = Category.builder()
				.id(categoryId)
				.name(categoryName)
				.description(caregoryDescription)
				.build();
		
		Mockito.when(categories.get(categoryId))
			.thenReturn(Optional.of(category));

		String petName = "Pet 889";
		LocalDate petBirthdate = LocalDate.now();
		String petBio = "Pet 889 Bio";
		
		PetRequest req = new PetRequest();
		req.setName(petName);
		req.setBirthdate(petBirthdate);
		req.setBio(petBio);
		req.setCategory(categoryId);
		
		CategoryResponse catRes = new CategoryResponse();
		catRes.setId(categoryId);
		catRes.setName(categoryName);
		catRes.setDescription(caregoryDescription);
		
		String petId = "x300";
		PetResponse expected = new PetResponse();
		expected.setId(petId);
		expected.setName(petName);
		expected.setBirthdate(petBirthdate);
		expected.setBiography(petBio);
		expected.setCategory(catRes);
		
		Mockito.when(idGenerator.nextId())
			.thenReturn(petId);
		
		http.post()
			.uri("/pets")
			.accept(MediaType.APPLICATION_JSON)
			.bodyValue(req)
			.exchange()
				.expectStatus().isOk()
				.expectBody(PetResponse.class)
				.isEqualTo(expected);
	}
}
