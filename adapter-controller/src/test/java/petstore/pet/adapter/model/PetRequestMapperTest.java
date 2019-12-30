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

import petstore.pet.usecase.model.NewPet;

/**
 * 
 * @author fabiojose
 *
 */
public class PetRequestMapperTest {

	@Test
	public void should_map_name_to_usecase_name() {
		
		String petName = "pet name";
		PetRequest req = new PetRequest();
		req.setName(petName);
		req.setBirthdate(LocalDate.now());
		req.setBio("pet bio");
		req.setCategory("cat34");
		
		NewPet actual = PetRequestMapper.INSTANCE.map(req);
		
		assertEquals(petName, actual.getName());
		
	}
	
	@Test
	public void should_map_bithdate_to_usecase_birthdate() {
		
		LocalDate petBirthdate = LocalDate.now();
		PetRequest req = new PetRequest();
		req.setName("pet name");
		req.setBirthdate(petBirthdate);
		req.setBio("pet bio");
		req.setCategory("cat34");
		
		NewPet actual = PetRequestMapper.INSTANCE.map(req);
		
		assertEquals(petBirthdate, actual.getBirthdate());
		
	}
	
	@Test
	public void should_map_bio_to_usecase_biography() {
		
		String petBio = "pet bio";
		PetRequest req = new PetRequest();
		req.setName("pet name");
		req.setBirthdate(LocalDate.now());
		req.setBio(petBio);
		req.setCategory("cat34");
		
		NewPet actual = PetRequestMapper.INSTANCE.map(req);
		
		assertEquals(petBio, actual.getBiography());
	}
	
	@Test
	public void should_map_category_to_usecase_idOfCategory() {
		
		String categoryId = "cat34";
		PetRequest req = new PetRequest();
		req.setName("pet name");
		req.setBirthdate(LocalDate.now());
		req.setBio("pet bio");
		req.setCategory(categoryId);
		
		NewPet actual = PetRequestMapper.INSTANCE.map(req);
		
		assertEquals(categoryId, actual.getIdOfCategory());
	}
}
