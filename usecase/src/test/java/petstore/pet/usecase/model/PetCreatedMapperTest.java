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

import petstore.pet.domain.entity.Category;
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
		
		Category category = Category.builder()
				.id("cat 99")
				.name("Cat 99")
				.description("Cat 99 Desc")
				.build();
		
		String petId = "pet99";
		Pet pet = Pet.builder()
				.id(petId)
				.name("Pet 99")
				.birth(LocalDate.now())
				.bio("Cat 99 Bio")
				.category(category)
				.build();
		
		PetCreatedMapper mapper = Mappers.getMapper(PetCreatedMapper.class);
		
		PetCreated actual = mapper.map(pet);
		
		assertEquals(petId, actual.getId());
	}
	
	@Test
	public void should_map_the_name() {
		Category category = Category.builder()
				.id("cat 99")
				.name("Cat 99")
				.description("Cat 99 Desc")
				.build();
		
		String petName = "Pet 99";
		Pet pet = Pet.builder()
				.id("pet99")
				.name(petName)
				.birth(LocalDate.now())
				.bio("Cat 99 Bio")
				.category(category)
				.build();
		
		PetCreatedMapper mapper = Mappers.getMapper(PetCreatedMapper.class);
		
		PetCreated actual = mapper.map(pet);
		
		assertEquals(petName, actual.getName());
	}
	
	@Test
	public void should_map_the_birthdate() {
		Category category = Category.builder()
				.id("cat 99")
				.name("Cat 99")
				.description("Cat 99 Desc")
				.build();
		
		LocalDate petBirth = LocalDate.now();
		Pet pet = Pet.builder()
				.id("pet99")
				.name("Pet 99")
				.birth(petBirth)
				.bio("Cat 99 Bio")
				.category(category)
				.build();
		
		PetCreatedMapper mapper = Mappers.getMapper(PetCreatedMapper.class);
		
		PetCreated actual = mapper.map(pet);
		
		assertEquals(petBirth, actual.getBirthdate());
	}
	
	@Test
	public void should_map_the_biography() {
		Category category = Category.builder()
				.id("cat 99")
				.name("Cat 99")
				.description("Cat 99 Desc")
				.build();
		
		String petBio = "Cat 99 Bio";
		Pet pet = Pet.builder()
				.id("pet99")
				.name("Pet 99")
				.birth(LocalDate.now())
				.bio(petBio)
				.category(category)
				.build();
		
		PetCreatedMapper mapper = Mappers.getMapper(PetCreatedMapper.class);
		
		PetCreated actual = mapper.map(pet);
		
		assertEquals(petBio, actual.getBiography());
	}
	
	@Test
	public void should_map_the_category_id() {
		
		String categoryId = "cat99";
		Category category = Category.builder()
				.id(categoryId)
				.name("Cat 99")
				.description("Cat 99 Desc")
				.build();
	
		Pet pet = Pet.builder()
				.id("pet99")
				.name("Pet 99")
				.birth(LocalDate.now())
				.bio("Cat 99 Bio")
				.category(category)
				.build();
		
		PetCreatedMapper mapper = Mappers.getMapper(PetCreatedMapper.class);
		
		PetCreated actual = mapper.map(pet);
		
		assertEquals(categoryId, actual.getCategory().getId());
	}
}
