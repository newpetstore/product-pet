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
import petstore.pet.usecase.model.NewPet.NewPetMapper;
import petstore.pet.usecase.port.IdGenerator;

/**
 * 
 * @author fabiojose
 *
 */
public class NewPetMapperTest {

	@Test
	public void should_map_the_id() {
		
		String categoryId = "catx99";
		Category category = Category.builder()
				.id(categoryId)
				.name("Cat x99")
				.description("Cat x99 Desc")
				.build();
		
		NewPet newPet = NewPet.builder()
				.name("New Pet 1")
				.birthdate(LocalDate.now())
				.biography("New Pet Bio")
				.idOfCategory(categoryId)
				.build();
		
		final String petId = "petx99";
		
		IdGenerator<String> generator = new IdGenerator<String>() {
			public String nextId() {
				return petId;
			}
		};
		
		NewPetMapper mapper = Mappers.getMapper(NewPetMapper.class);
		mapper.setGenerator(generator);
		
		Pet actual = mapper.map(newPet, category);
		
		assertEquals(petId, actual.getId());
	}
	
	@Test
	public void should_map_the_name() {
		
		String categoryId = "catx99";
		Category category = Category.builder()
				.id(categoryId)
				.name("Cat x99")
				.description("Cat x99 Desc")
				.build();
		
		String petName = "New Pet";
		NewPet newPet = NewPet.builder()
				.name(petName)
				.birthdate(LocalDate.now())
				.biography("New Pet Bio")
				.idOfCategory(categoryId)
				.build();
		
		final String petId = "petx99";
		
		IdGenerator<String> generator = new IdGenerator<String>() {
			public String nextId() {
				return petId;
			}
		};
		
		NewPetMapper mapper = Mappers.getMapper(NewPetMapper.class);
		mapper.setGenerator(generator);
		
		Pet actual = mapper.map(newPet, category);
		
		assertEquals(petName, actual.getName());
	}
	
	@Test
	public void should_map_the_birth() {
		String categoryId = "catx99";
		Category category = Category.builder()
				.id(categoryId)
				.name("Cat x99")
				.description("Cat x99 Desc")
				.build();
		
		LocalDate petBith = LocalDate.now();
		NewPet newPet = NewPet.builder()
				.name("New Pet")
				.birthdate(petBith)
				.biography("New Pet Bio")
				.idOfCategory(categoryId)
				.build();
		
		final String petId = "petx99";
		
		IdGenerator<String> generator = new IdGenerator<String>() {
			public String nextId() {
				return petId;
			}
		};
		
		NewPetMapper mapper = Mappers.getMapper(NewPetMapper.class);
		mapper.setGenerator(generator);
		
		Pet actual = mapper.map(newPet, category);
		
		assertEquals(petBith, actual.getBirth());
	}
	
	@Test
	public void should_map_the_bio() {
		String categoryId = "catx99";
		Category category = Category.builder()
				.id(categoryId)
				.name("Cat x99")
				.description("Cat x99 Desc")
				.build();
		
		String petBio = "New pet Bio";
		NewPet newPet = NewPet.builder()
				.name("New Pet")
				.birthdate(LocalDate.now())
				.biography(petBio)
				.idOfCategory(categoryId)
				.build();
		
		final String petId = "petx99";
		
		IdGenerator<String> generator = new IdGenerator<String>() {
			public String nextId() {
				return petId;
			}
		};
		
		NewPetMapper mapper = Mappers.getMapper(NewPetMapper.class);
		mapper.setGenerator(generator);
		
		Pet actual = mapper.map(newPet, category);
		
		assertEquals(petBio, actual.getBio());
	}
	
	@Test
	public void should_map_the_category() {
		String categoryId = "catx99";
		Category category = Category.builder()
				.id(categoryId)
				.name("Cat x99")
				.description("Cat x99 Desc")
				.build();
		
		NewPet newPet = NewPet.builder()
				.name("New Pet")
				.birthdate(LocalDate.now())
				.biography("New pet Bio")
				.idOfCategory(categoryId)
				.build();
		
		final String petId = "petx99";
		
		IdGenerator<String> generator = new IdGenerator<String>() {
			public String nextId() {
				return petId;
			}
		};
		
		NewPetMapper mapper = Mappers.getMapper(NewPetMapper.class);
		mapper.setGenerator(generator);
		
		Pet actual = mapper.map(newPet, category);
		
		assertEquals(categoryId, actual.getCategory().getId());
	}
}
