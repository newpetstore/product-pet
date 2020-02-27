package petstore.pet.spring.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import petstore.pet.adapter.model.CategoryResponse;
import petstore.pet.adapter.model.PetRequest;
import petstore.pet.adapter.model.PetResponse;
import petstore.pet.spring.Entrypoint;
import petstore.pet.usecase.model.Category;
import petstore.pet.usecase.port.Categories;
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
	Categories categories;
	
	@MockBean
	PetIdGenerator idGenerator;

	@Autowired
	WebTestClient http;
	
	@Test
	public void should_res_400_when_there_is_no_body() {
		
		http.post()
		.uri("/pets")
		.accept(MediaType.APPLICATION_JSON)
		.contentType(MediaType.APPLICATION_JSON)
		.exchange()
			.expectStatus().isBadRequest();
		
	}
	
	@Test
	public void should_res_400_when_name_is_null() {
		
		String categoryId = "cat889";
		String categoryName = "Cat 889";
		String caregoryDescription = "Cat 889 Description";
		Category category = new Category();
		category.setId(categoryId);
		category.setName(categoryName);
		category.setDescription(caregoryDescription);
		
		Mockito.when(categories.getById(categoryId))
			.thenReturn(Optional.of(category));

		LocalDate petBirthdate = LocalDate.now();
		String petBio = "Pet 889 Bio";
		
		PetRequest req = new PetRequest();
		req.setName(null);
		req.setBirthdate(petBirthdate);
		req.setBio(petBio);
		req.setCategory(categoryId);
		
		Map<String, Object> expected = new HashMap<>();
		expected.put("path", "/pets");
		expected.put("status", 400);
		expected.put("error", "Bad Request");
		expected.put("message", "name.should.not.be.null");
		
		http.post()
		.uri("/pets")
		.accept(MediaType.APPLICATION_JSON)
		.contentType(MediaType.APPLICATION_JSON)
		.bodyValue(req)
		.exchange()
			.expectStatus().isBadRequest()
			.expectBody(Map.class)
			.isEqualTo(expected);
		
	}
	
	@Test
	public void should_res_400_when_name_is_empty() {
		
		String categoryId = "cat889";
		String categoryName = "Cat 889";
		String caregoryDescription = "Cat 889 Description";
		Category category = new Category();
		category.setId(categoryId);
		category.setName(categoryName);
		category.setDescription(caregoryDescription);
		
		Mockito.when(categories.getById(categoryId))
			.thenReturn(Optional.of(category));

		LocalDate petBirthdate = LocalDate.now();
		String petBio = "Pet 889 Bio";
		
		PetRequest req = new PetRequest();
		req.setName("  ");
		req.setBirthdate(petBirthdate);
		req.setBio(petBio);
		req.setCategory(categoryId);
		
		Map<String, Object> expected = new HashMap<>();
		expected.put("path", "/pets");
		expected.put("status", 400);
		expected.put("error", "Bad Request");
		expected.put("message", "name.should.not.be.empty");
		
		http.post()
		.uri("/pets")
		.accept(MediaType.APPLICATION_JSON)
		.contentType(MediaType.APPLICATION_JSON)
		.bodyValue(req)
		.exchange()
			.expectStatus().isBadRequest()
			.expectBody(Map.class)
			.isEqualTo(expected);
	}
	
	@Test
	public void should_res_400_when_category_id_is_null() {
		
		String categoryId = "cat889";
		String categoryName = "Cat 889";
		String caregoryDescription = "Cat 889 Description";
		Category category = new Category();
		category.setId(categoryId);
		category.setName(categoryName);
		category.setDescription(caregoryDescription);
		
		Mockito.when(categories.getById(categoryId))
			.thenReturn(Optional.of(category));

		LocalDate petBirthdate = LocalDate.now();
		String petBio = "Pet 889 Bio";
		
		PetRequest req = new PetRequest();
		req.setName("Pet name");
		req.setBirthdate(petBirthdate);
		req.setBio(petBio);
		req.setCategory(null);
		
		Map<String, Object> expected = new HashMap<>();
		expected.put("path", "/pets");
		expected.put("status", 400);
		expected.put("error", "Bad Request");
		expected.put("message", "category.should.not.be.null");
		
		http.post()
		.uri("/pets")
		.accept(MediaType.APPLICATION_JSON)
		.contentType(MediaType.APPLICATION_JSON)
		.bodyValue(req)
		.exchange()
			.expectStatus().isBadRequest()
			.expectBody(Map.class)
			.isEqualTo(expected);
	}
	
	@Test
	public void should_res_400_when_category_not_found() {
		
		String categoryId = "cat889";
		
		Mockito.when(categories.getById(categoryId))
			.thenReturn(Optional.empty());

		LocalDate petBirthdate = LocalDate.now();
		String petBio = "Pet 889 Bio";
		
		PetRequest req = new PetRequest();
		req.setName("Pet name");
		req.setBirthdate(petBirthdate);
		req.setBio(petBio);
		req.setCategory(categoryId);
		
		Map<String, Object> expected = new HashMap<>();
		expected.put("path", "/pets");
		expected.put("status", 400);
		expected.put("error", "Bad Request");
		expected.put("message", "category.not.found");
		
		http.post()
		.uri("/pets")
		.accept(MediaType.APPLICATION_JSON)
		.contentType(MediaType.APPLICATION_JSON)
		.bodyValue(req)
		.exchange()
			.expectStatus().isBadRequest()
			.expectBody(Map.class)
			.isEqualTo(expected);
	}
	
	@Test
	public void should_res_400_when_birthdate_is_null() {
		
		String categoryId = "cat889";
		String categoryName = "Cat 889";
		String caregoryDescription = "Cat 889 Description";
		Category category = new Category();
		category.setId(categoryId);
		category.setName(categoryName);
		category.setDescription(caregoryDescription);
		
		Mockito.when(categories.getById(categoryId))
			.thenReturn(Optional.of(category));

		String petBio = "Pet 889 Bio";
		
		PetRequest req = new PetRequest();
		req.setName("Pet name");
		req.setBirthdate(null);
		req.setBio(petBio);
		req.setCategory(categoryId);
		
		Map<String, Object> expected = new HashMap<>();
		expected.put("path", "/pets");
		expected.put("status", 400);
		expected.put("error", "Bad Request");
		expected.put("message", "birthdate.should.not.be.null");
		
		http.post()
		.uri("/pets")
		.accept(MediaType.APPLICATION_JSON)
		.contentType(MediaType.APPLICATION_JSON)
		.bodyValue(req)
		.exchange()
			.expectStatus().isBadRequest()
			.expectBody(Map.class)
			.isEqualTo(expected);
	}
	
	@Test
	public void should_res_400_when_bio_is_null() {
		
		String categoryId = "cat889";
		String categoryName = "Cat 889";
		String caregoryDescription = "Cat 889 Description";
		Category category = new Category();
		category.setId(categoryId);
		category.setName(categoryName);
		category.setDescription(caregoryDescription);
		
		Mockito.when(categories.getById(categoryId))
			.thenReturn(Optional.of(category));

		PetRequest req = new PetRequest();
		req.setName("Pet name");
		req.setBirthdate(LocalDate.now());
		req.setBio(null);
		req.setCategory(categoryId);
		
		Map<String, Object> expected = new HashMap<>();
		expected.put("path", "/pets");
		expected.put("status", 400);
		expected.put("error", "Bad Request");
		expected.put("message", "biography.should.not.be.null");
		
		http.post()
		.uri("/pets")
		.accept(MediaType.APPLICATION_JSON)
		.contentType(MediaType.APPLICATION_JSON)
		.bodyValue(req)
		.exchange()
			.expectStatus().isBadRequest()
			.expectBody(Map.class)
			.isEqualTo(expected);
	}
	
	@Test
	public void should_res_400_when_bio_is_empty() {
		
		String categoryId = "cat889";
		String categoryName = "Cat 889";
		String caregoryDescription = "Cat 889 Description";
		Category category = new Category();
		category.setId(categoryId);
		category.setName(categoryName);
		category.setDescription(caregoryDescription);
		
		Mockito.when(categories.getById(categoryId))
			.thenReturn(Optional.of(category));

		PetRequest req = new PetRequest();
		req.setName("Pet name");
		req.setBirthdate(LocalDate.now());
		req.setBio("  	");
		req.setCategory(categoryId);
		
		Map<String, Object> expected = new HashMap<>();
		expected.put("path", "/pets");
		expected.put("status", 400);
		expected.put("error", "Bad Request");
		expected.put("message", "biography.should.not.be.empty");
		
		http.post()
		.uri("/pets")
		.accept(MediaType.APPLICATION_JSON)
		.contentType(MediaType.APPLICATION_JSON)
		.bodyValue(req)
		.exchange()
			.expectStatus().isBadRequest()
			.expectBody(Map.class)
			.isEqualTo(expected);
	}
	
	@Test
	public void should_res_405_when_method_is_not_post() {
		
		http.head()
		.uri("/pets")
		.accept(MediaType.APPLICATION_JSON)
		.exchange()
			.expectStatus()
				.isEqualTo(HttpStatus.METHOD_NOT_ALLOWED);
		
	}
	
	@Test
	public void should_save_new_pets_and_res_200() {
		
		String categoryId = "cat889";
		String categoryName = "Cat 889";
		String caregoryDescription = "Cat 889 Description";
		Category category = new Category();
		category.setId(categoryId);
		category.setName(categoryName);
		category.setDescription(caregoryDescription);
		
		Mockito.when(categories.getById(categoryId))
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
			.contentType(MediaType.APPLICATION_JSON)
			.bodyValue(req)
			.exchange()
				.expectStatus().isOk()
				.expectBody(PetResponse.class)
				.isEqualTo(expected);
	}
}
