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
package petstore.pet.adapter.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import petstore.pet.usecase.model.CategoryCreated;
import petstore.pet.usecase.model.PetCreated;

/**
 * 
 * @author fabiojose
 *
 */
public class PetResponseMapperTest {
	
	@Test
	public void should_map_usecase_id_to_id() {
		
		CategoryCreated cat = new CategoryCreated();
		cat.setId("catx88");
		cat.setName("Cat 88");
		cat.setDescription("Cat 88 Description");
		
		String petId = "petx88";
		PetCreated created = new PetCreated();
		created.setId(petId);
		created.setName("pet name");
		created.setBirthdate(LocalDate.now());
		created.setBiography("pet bio");
		created.setCategory(cat);
		
		PetResponse actual = PetResponseMapper.INSTANCE.map(created);
		
		assertEquals(petId, actual.getId());
	}

	@Test
	public void should_map_usecase_name_to_name() {
		
		CategoryCreated cat = new CategoryCreated();
		cat.setId("catx88");
		cat.setName("Cat 88");
		cat.setDescription("Cat 88 Description");
		
		String petName = "pet name";
		PetCreated created = new PetCreated();
		created.setId("petx88");
		created.setName(petName);
		created.setBirthdate(LocalDate.now());
		created.setBiography("pet bio");
		created.setCategory(cat);
		
		PetResponse actual = PetResponseMapper.INSTANCE.map(created);
		
		assertEquals(petName, actual.getName());
	}
	
	@Test
	public void should_map_usecase_bithdate_to_birthdate() {
		
		CategoryCreated cat = new CategoryCreated();
		cat.setId("catx88");
		cat.setName("Cat 88");
		cat.setDescription("Cat 88 Description");
		
		LocalDate petBirthdate = LocalDate.now();
		PetCreated created = new PetCreated();
		created.setId("petx88");
		created.setName("pet name");
		created.setBirthdate(petBirthdate);
		created.setBiography("pet bio");
		created.setCategory(cat);
		
		PetResponse actual = PetResponseMapper.INSTANCE.map(created);
		
		assertEquals(petBirthdate, actual.getBirthdate());
	}
	
	@Test
	public void should_map_usecase_biography_to_biography() {
		
		CategoryCreated cat = new CategoryCreated();
		cat.setId("catx88");
		cat.setName("Cat 88");
		cat.setDescription("Cat 88 Description");
		
		String petBiography = "pet bio";
		PetCreated created = new PetCreated();
		created.setId("petx88");
		created.setName("pet name");
		created.setBirthdate(LocalDate.now());
		created.setBiography(petBiography);
		created.setCategory(cat);
		
		PetResponse actual = PetResponseMapper.INSTANCE.map(created);
		
		assertEquals(petBiography, actual.getBiography());
	}
	
	@Test
	public void should_map_usecase_category_id_to_category_id() {
		
		String categoryId = "catx88";
		CategoryCreated cat = new CategoryCreated();
		cat.setId(categoryId);
		cat.setName("Cat 88");
		cat.setDescription("Cat 88 Description");

		PetCreated created = new PetCreated();
		created.setId("petx88");
		created.setName("pet name");
		created.setBirthdate(LocalDate.now());
		created.setBiography("pet bio");
		created.setCategory(cat);
		
		PetResponse actual = PetResponseMapper.INSTANCE.map(created);
		
		assertEquals(categoryId, actual.getCategory().getId());
	}
	
	@Test
	public void should_map_usecase_category_name_to_category_name() {
		
		String categoryName = "Cat 88";
		CategoryCreated cat = new CategoryCreated();
		cat.setId("catx88");
		cat.setName(categoryName);
		cat.setDescription("Cat 88 Description");

		PetCreated created = new PetCreated();
		created.setId("petx88");
		created.setName("pet name");
		created.setBirthdate(LocalDate.now());
		created.setBiography("pet bio");
		created.setCategory(cat);
		
		PetResponse actual = PetResponseMapper.INSTANCE.map(created);
		
		assertEquals(categoryName, actual.getCategory().getName());
	}
	
	@Test
	public void should_map_usecase_category_description_to_category_description() {
		
		String categoryDescription = "Cat 88 Description";
		CategoryCreated cat = new CategoryCreated();
		cat.setId("catx88");
		cat.setName("Cat 88");
		cat.setDescription("Cat 88 Description");

		PetCreated created = new PetCreated();
		created.setId("petx88");
		created.setName("pet name");
		created.setBirthdate(LocalDate.now());
		created.setBiography("pet bio");
		created.setCategory(cat);
		
		PetResponse actual = PetResponseMapper.INSTANCE.map(created);
		
		assertEquals(categoryDescription, 
				actual.getCategory().getDescription());
	}
}
