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
package petstore.pet.adapter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import petstore.pet.adapter.model.PetRequest;
import petstore.pet.adapter.model.PetRequestMapper;
import petstore.pet.adapter.model.PetResponse;
import petstore.pet.adapter.model.PetResponseMapper;
import petstore.pet.usecase.RegisterNewPet;
import petstore.pet.usecase.model.CategoryCreated;
import petstore.pet.usecase.model.NewPet;
import petstore.pet.usecase.model.PetCreated;

/**
 * 
 * @author fabiojose
 *
 */
@ExtendWith(MockitoExtension.class)
public class PetRegisterControllerTest {
	
	@Mock
	RegisterNewPet usecase;
	
	@InjectMocks
	PetRegisterController controller;

	@Test
	public void should_throw_when_usecase_is_null() {
		
		assertThrows(NullPointerException.class, () -> 
			new PetRegisterController(null));
		
	}
	
	@Test
	public void should_throw_when_request_supplier_is_null() {
		
		assertThrows(NullPointerException.class, () ->
				controller.register(null));
		
	}
	
	@Test
	public void should_throw_when_supplier_results_null() {
	
		assertThrows(NullPointerException.class, () ->
			controller.register(() -> null));
		
	}
	
	@Test
	public void should_register_new_pets() throws Exception {
		
		// setup
		String categoryId = "cat35";
		String categoryName = "Cat 35";
		String categoryDescription = "Cat 35 Description";
		
		CategoryCreated category = new CategoryCreated();
		category.setId(categoryId);
		category.setName(categoryName);
		category.setDescription(categoryDescription);
		
		String petId = "pet35";
		String petName = "Pet 35";
		LocalDate petBirthdate = LocalDate.now();
		String petBiography = "Pet 35 Bio";
		PetCreated created = new PetCreated();
		created.setId(petId);
		created.setName(petName);
		created.setBirthdate(petBirthdate);
		created.setBiography(petBiography);
		created.setCategory(category);
		
		PetResponse expected = PetResponseMapper.INSTANCE.map(created);
		
		PetRequest req = new PetRequest();
		req.setName(petName);
		req.setBirthdate(petBirthdate);
		req.setBio(petBiography);
		req.setCategory(categoryId);
		
		NewPet newPet = PetRequestMapper.INSTANCE.map(req);
		
		when(usecase.create(newPet))
			.thenReturn(created);
		
		// act
		CompletableFuture<PetResponse> future =
			controller.register(() -> req);
		
		
		// assert
		assertNotNull(future);
		
		PetResponse actual = future.get();
		
		assertNotNull(actual);
		
		assertEquals(expected, actual);
	}
}
