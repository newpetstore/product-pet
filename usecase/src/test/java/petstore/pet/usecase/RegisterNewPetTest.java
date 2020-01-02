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
package petstore.pet.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import petstore.pet.domain.entity.Category;
import petstore.pet.domain.entity.Pet;
import petstore.pet.domain.exception.PetValidationException;
import petstore.pet.usecase.exception.PetAlreadyExistsException;
import petstore.pet.usecase.model.NewPet;
import petstore.pet.usecase.model.PetCreated;
import petstore.pet.usecase.port.CategoryDatastore;
import petstore.pet.usecase.port.PetIdGenerator;
import petstore.pet.usecase.port.PetDatastore;

/**
 * 
 * @author fabiojose
 *
 */
@ExtendWith(MockitoExtension.class)
public class RegisterNewPetTest {
	
	@Mock
	PetDatastore pets;
	
	@Mock
	CategoryDatastore categories;
	
	@Mock
	PetIdGenerator generator;
	
	@InjectMocks
	RegisterNewPet usecase;
	
	@Test
	public void should_throw_on_null_pets_args() {
		
		assertThrows(NullPointerException.class, () -> {
			new RegisterNewPet(null, generator, categories);
		});
		
	}
	
	@Test
	public void should_throw_on_null_id_gen_args() {
		
		assertThrows(NullPointerException.class, () -> {
			new RegisterNewPet(pets, null, categories);
		});
		
	}
	
	@Test
	public void should_throw_on_null_categories_args() {
		
		assertThrows(NullPointerException.class, () -> {
			new RegisterNewPet(pets, generator, null);
		});
		
	}
	
	@Test
	public void should_throw_when_invalid_category() {
				
		// setup
		String categoryId = "catx1";
		
		when(categories.get(categoryId))
			.thenReturn(Optional.empty());
		
		NewPet newPet = NewPet.builder()
				.name("a pet")
				.birthdate(LocalDate.now())
				.biography("a bio")
				.idOfCategory(categoryId)
				.build();
		
		// assert
		PetValidationException e = 
			assertThrows(PetValidationException.class, () -> {
				
				// act
				usecase.create(newPet);
				
			});
		
		// assert
		assertEquals("category.not.found", e.getMessage());
	}
	
	@Test
	public void should_create_the_new_pet() {
		
		// setup
		String categoryId = "catx1";
		Category category = Category.builder()
				.id(categoryId)
				.name("category")
				.description("a desc")
				.build();
		
		when(categories.get(categoryId))
			.thenReturn(Optional.of(category));
		
		NewPet newPet = NewPet.builder()
				.name("a pet")
				.birthdate(LocalDate.now())
				.biography("a bio")
				.idOfCategory(categoryId)
				.build();
		
		String petId = "newpet6y";
		when(generator.nextId())
			.thenReturn(petId);
		
		// act
		PetCreated actual = usecase.create(newPet);
		assertNotNull(actual);
		
		assertEquals(petId, actual.getId());
	}
	
	@Test
	public void should_throw_when_id_already_exists() {
		
		// setup
		String petId = "newpet6y";
		
		when(generator.nextId())
			.thenReturn(petId);
		
		String categoryId = "catx1";
		
		Category category = Category.builder()
				.id(categoryId)
				.name("a cat")
				.description("cat desc")
				.build();
		
		when(categories.get(categoryId))
			.thenReturn(Optional.of(category));
		
		Pet pet = Pet.builder()
			.id(petId)
			.name("a pet")
			.birth(LocalDate.now())
			.bio("a bio")
			.category(category)
			.build();
		
		when(pets.get(petId))
			.thenReturn(Optional.of(pet));
		
		NewPet newPet = NewPet.builder()
				.name("a pet")
				.birthdate(LocalDate.now())
				.biography("a bio")
				.idOfCategory(categoryId)
				.build();
		
		// assert
		assertThrows(PetAlreadyExistsException.class, () -> {
			
			// act
			usecase.create(newPet);
			
		});
		
	}
}
