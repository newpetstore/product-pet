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
package petstore.pet.datastore.mongodb.spring.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import petstore.pet.datastore.mongodb.spring.MongoCategoryDatastore;
import petstore.pet.domain.entity.Category;
import petstore.pet.domain.entity.Pet;
import petstore.pet.domain.exception.PetValidationException;

/**
 * 
 * @author fabiojose
 *
 */
@Tag("unit")
@ExtendWith(MockitoExtension.class)
public class PetModelMapperTest {
	
	@Mock
	MongoCategoryDatastore categories;

	@Test
	public void model_to_pet_should_maps_the_attributes() {
		
		// setup
		String categoryId = "catx99";
		Category category = Category.builder()
				.id(categoryId)
				.name("cat name")
				.description("cat desc")
				.build();
		
		when(categories.get(categoryId))
			.thenReturn(Optional.of(category));
		
		PetModel model = new PetModel("modelId", "modelName", LocalDate.now(),
				"modelBio", categoryId);
		
		PetModelMapper mapper = Mappers.getMapper(PetModelMapper.class);
		mapper.categories = categories;
		
		// act
		Pet actual = mapper.map(model);
		
		// assert		
		assertEquals(model.getId(), actual.getId());
		assertEquals(model.getName(), actual.getName());
		assertEquals(model.getBirthdate(), actual.getBirth());
		assertEquals(model.getBiography(), actual.getBio());
		assertEquals(model.getCategoryId(), actual.getCategory().getId());
		
		assertEquals(category.getName(), actual.getCategory().getName());
		assertEquals(category.getDescription(), 
				actual.getCategory().getDescription());
	}
	
	@Test
	public void pet_to_model_should_maps_the_attributes() {
		
		Category category = Category.builder()
				.id("catId")
				.name("catName")
				.description("catDesc")
				.build();
		
		Pet pet = Pet.builder()
				.id("petId")
				.name("petName")
				.birth(LocalDate.now())
				.bio("petBio")
				.category(category)
				.build();
		
		PetModel model = PetModelMapper.INSTANCE.map(pet);
		
		assertEquals(pet.getId(), model.getId());
		assertEquals(pet.getName(), model.getName());
		assertEquals(pet.getBirth(), model.getBirthdate());
		assertEquals(pet.getBio(), model.getBiography());
		assertEquals(pet.getCategory().getId(), model.getCategoryId());
		
	}
	
	@Test
	public void should_throw_when_category_not_found() {
	
		// setup
		PetModel model = new PetModel("modelId", "modelName", LocalDate.now(),
				"modelBio", "notfoundCat");
		
		PetModelMapper mapper = Mappers.getMapper(PetModelMapper.class);
		mapper.categories = categories;
		
		assertThrows(PetValidationException.class, () ->
			mapper.map(model));
	}
}
