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
package petstore.pet.usecase.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import petstore.pet.domain.entity.Pet;
import petstore.pet.usecase.model.PetCreated.PetCreatedMapper;

/**
 * 
 * @author fabiojose
 *
 */
public class PetCreatedMapperTest {

	@Test
	public void should_map_the_id() {

		Category category = new Category();
		category.setId("cat 99");
		category.setName("Cat 99 Name");
		category.setDescription("Cat 99 Desc");
		
		String petId = "pet99";
		Pet pet = Pet.builder()
				.id(petId)
				.name("Pet 99")
				.birth(LocalDate.now())
				.bio("Cat 99 Bio")
				.idOfCategory(category.getId())
				.build();
		
		PetCreatedMapper mapper = Mappers.getMapper(PetCreatedMapper.class);
		
		PetCreated actual = mapper.map(pet, category);
		
		assertEquals(petId, actual.getId());
	}
	
	@Test
	public void should_map_the_name() {
		Category category = new Category();
		category.setId("cat 99");
		category.setName("Cat 99 Name");
		category.setDescription("Cat 99 Desc");
		
		String petName = "Pet 99";
		Pet pet = Pet.builder()
				.id("pet99")
				.name(petName)
				.birth(LocalDate.now())
				.bio("Cat 99 Bio")
				.idOfCategory(category.getId())
				.build();
		
		PetCreatedMapper mapper = Mappers.getMapper(PetCreatedMapper.class);
		
		PetCreated actual = mapper.map(pet, category);
		
		assertEquals(petName, actual.getName());
	}
	
	@Test
	public void should_map_the_birthdate() {
		Category category = new Category();
		category.setId("cat 99");
		category.setName("Cat 99 Name");
		category.setDescription("Cat 99 Desc");
		
		LocalDate petBirth = LocalDate.now();
		Pet pet = Pet.builder()
				.id("pet99")
				.name("Pet 99")
				.birth(petBirth)
				.bio("Cat 99 Bio")
				.idOfCategory(category.getId())
				.build();
		
		PetCreatedMapper mapper = Mappers.getMapper(PetCreatedMapper.class);
		
		PetCreated actual = mapper.map(pet, category);
		
		assertEquals(petBirth, actual.getBirthdate());
	}
	
	@Test
	public void should_map_the_biography() {
		Category category = new Category();
		category.setId("cat 99");
		category.setName("Cat 99 Name");
		category.setDescription("Cat 99 Desc");
		
		String petBio = "Cat 99 Bio";
		Pet pet = Pet.builder()
				.id("pet99")
				.name("Pet 99")
				.birth(LocalDate.now())
				.bio(petBio)
				.idOfCategory(category.getId())
				.build();
		
		PetCreatedMapper mapper = Mappers.getMapper(PetCreatedMapper.class);
		
		PetCreated actual = mapper.map(pet, category);
		
		assertEquals(petBio, actual.getBiography());
	}
	
	@Test
	public void should_map_the_category_id() {
		
		String categoryId = "cat99";
		Category category = new Category();
		category.setId(categoryId);
		category.setName("Cat 99 Name");
		category.setDescription("Cat 99 Desc");
	
		Pet pet = Pet.builder()
				.id("pet99")
				.name("Pet 99")
				.birth(LocalDate.now())
				.bio("Cat 99 Bio")
				.idOfCategory(categoryId)
				.build();
		
		PetCreatedMapper mapper = Mappers.getMapper(PetCreatedMapper.class);
		
		PetCreated actual = mapper.map(pet, category);
		
		assertEquals(categoryId, actual.getCategory().getId());
	}
}
