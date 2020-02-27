/*
 * Java (TM) Pet Store Modernized Edition - 2019
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package petstore.pet.datastore.mongodb.spring;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import petstore.pet.datastore.mongodb.spring.model.PetModel;
import petstore.pet.datastore.mongodb.spring.model.PetModelMapper;
import petstore.pet.domain.entity.Pet;
import petstore.pet.domain.exception.PetValidationException;

/**
 * 
 * @author fabiojose
 *
 */
@Tag("integration")
@SpringBootTest
public class MongoPetDatastoreTest {
	
	@Autowired
	PetRepository repository;
	
	@Autowired
	MongoPetDatastore datastore;
	
	@Autowired
	PetModelMapper mapper;
	
	@Test
	public void should_throw_when_id_is_null_on_read() {
		
		assertThrows(IllegalArgumentException.class, () -> 
				datastore.get(null));
		
	}
	
	@Test
	public void should_throw_when_id_is_null_on_delete() {
		
		assertThrows(IllegalArgumentException.class, () ->
				datastore.del(null));
		
	}
	
	@Test
	public void should_throw_when_read_inconsistent_birth() {
		
		String categoryId = "cat008";
				
		String petId = "pet008";
		PetModel create = new PetModel();
		create.setId(petId);
		create.setName("Pet 8");
		create.setBirthdate(null);
		create.setBiography("Bio 8");
		create.setCategoryId(categoryId);
		repository.save(create);
		
		assertThrows(PetValidationException.class, () -> 
				datastore.get(petId));
	}
	
	@Test
	public void should_throw_when_read_inconsistent_bio() {
		
		String categoryId = "cat002";
				
		String petId = "pet002";
		PetModel create = new PetModel();
		create.setId(petId);
		create.setName("Pet 2");
		create.setBirthdate(LocalDate.now());
		create.setBiography("  ");
		create.setCategoryId(categoryId);
		repository.save(create);
		
		assertThrows(PetValidationException.class, () -> 
				datastore.get(petId));
	}
	
	@Test
	public void should_throw_when_read_inconsistent_name() {
		
		String categoryId = "cat00c4";
				
		String petId = "pet00c4";
		PetModel create = new PetModel();
		create.setId(petId);
		create.setName(null);
		create.setBirthdate(LocalDate.now());
		create.setBiography("Bio c4");
		create.setCategoryId(categoryId);
		repository.save(create);
		
		assertThrows(PetValidationException.class, () -> 
				datastore.get(petId));
	}
	
	@Test
	public void should_thrown_when_read_and_not_found_category() {
		
		String categoryId = "not-found";
		
		String petId = "pet00d4";
		PetModel create = new PetModel();
		create.setId(petId);
		create.setName(null);
		create.setBirthdate(LocalDate.now());
		create.setBiography("Bio d4");
		create.setCategoryId(categoryId);
		repository.save(create);
		
		assertThrows(PetValidationException.class, () -> 
				datastore.get(petId));
		
	}
	
	@Test
	public void should_not_throw_when_delete_not_found_pet() {
		
		datastore.del("notfound");
		
	}
	
	@Test
	public void should_create_pet() {
		
		String petId = "pet003";
		Pet expected = Pet.builder()
			.id(petId)
			.name("Pet 3")
			.birth(LocalDate.now())
			.bio("Pet bio")
			.idOfCategory("cat 99")
			.build();
		
		datastore.put(expected);
		
		Optional<PetModel> found = repository.findById(petId);
		assertTrue(found.isPresent());
		
		Pet actual =  mapper.map(found.get());
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void should_update_existing_pet() {
		
		String petId = "pet003";
		Pet create = Pet.builder()
			.id(petId)
			.name("Pet 3")
			.birth(LocalDate.now())
			.bio("Pet bio")
			.idOfCategory("cat 99")
			.build();
		
		datastore.put(create);
		
		Pet expected = create.toBuilder()
			.name("Timtim")
			.build();
		
		datastore.put(expected);
		
		Optional<PetModel> found = repository.findById(petId);
		assertTrue(found.isPresent());
		
		Pet actual =  mapper.map(found.get());
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void should_delete_existing_pet() {
		
		String petId = "pet003";
		Pet create = Pet.builder()
			.id(petId)
			.name("Pet 3")
			.birth(LocalDate.now())
			.bio("Pet bio")
			.idOfCategory("cat 99")
			.build();
		
		datastore.put(create);
		
		datastore.del(petId);
		
		assertFalse(repository.findById(petId).isPresent());
	}
	
	@Test
	public void should_read_a_pet_by_id() {
		
		String categoryId = "cate3";
		
		String petId = "pet00e3";
		PetModel create = new PetModel();
		create.setId(petId);
		create.setName("Pet e3");
		create.setBirthdate(LocalDate.now());
		create.setBiography("Bio e3");
		create.setCategoryId(categoryId);
		repository.save(create);
		
		Pet expected = mapper.map(create);
		
		Optional<Pet> found = datastore.get(petId);
		assertTrue(found.isPresent());
		
		assertEquals(expected, found.get());
	}
	
	@Test
	public void should_result_an_empty_when_pet_not_found() {
	
		assertFalse(datastore.get("not-found").isPresent());
		
	}
}
