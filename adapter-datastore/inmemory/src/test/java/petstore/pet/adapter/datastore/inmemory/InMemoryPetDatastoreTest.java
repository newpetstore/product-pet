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
package petstore.pet.adapter.datastore.inmemory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import petstore.pet.adapter.datastore.inmemory.InMemoryPetDatastore;
import petstore.pet.domain.entity.Category;
import petstore.pet.domain.entity.Pet;
import petstore.pet.usecase.port.PetDatastore;

/**
 * 
 * @author fabiojose
 *
 */
public class InMemoryPetDatastoreTest {

	@Test
	public void should_throw_when_put_null_pet() {
		
		PetDatastore datastore = new InMemoryPetDatastore();
		
		assertThrows(NullPointerException.class, () -> 
				datastore.put(null));
	}
	
	@Test
	public void should_create() {
		
		Category category = Category.builder()
				.id("an id")
				.name("cat")
				.description("cat desc")
				.build();
		
		Pet pet = Pet.builder()
				.id("an id")
				.name("a name")
				.birth(LocalDate.now())
				.bio("a bio")
				.category(category)
				.build();
		
		PetDatastore datastore = new InMemoryPetDatastore();
		
		datastore.put(pet);
		
		assertNotNull(datastore.get(pet.getId()));
		
	}
	
	@Test
	public void should_delete() {
		
		Category category = Category.builder()
				.id("an id")
				.name("cat")
				.description("cat desc")
				.build();
		
		Pet pet = Pet.builder()
				.id("an id")
				.name("a name")
				.birth(LocalDate.now())
				.bio("a bio")
				.category(category)
				.build();
		
		PetDatastore datastore = new InMemoryPetDatastore();
		
		datastore.put(pet);
		datastore.del(pet.getId());
		
		assertFalse(datastore.get(pet.getId()).isPresent());
	}
	
	@Test
	public void should_update() {
		
		Category category = Category.builder()
				.id("an id")
				.name("cat")
				.description("cat desc")
				.build();
		
		Pet pet = Pet.builder()
				.id("an id")
				.name("a name")
				.birth(LocalDate.now())
				.bio("a bio")
				.category(category)
				.build();
		
		PetDatastore datastore = new InMemoryPetDatastore();
		
		datastore.put(pet);
		
		Pet updatedPet = pet.toBuilder()
				.name("johnx")
				.build();
		
		datastore.put(updatedPet);
		
		Optional<Pet> actual = datastore.get(updatedPet.getId());
		assertTrue(actual.isPresent());
		assertEquals("johnx", actual.get().getName());
	}
}
