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
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

/**
 * 
 * @author fabiojose
 *
 */
public class NewPetTest {

	@Test
	public void should_throw_when_name_is_null() {
		
		NewPet.NewPetBuilder builder = NewPet.builder()
				.name(null)
				.birthdate(LocalDate.now())
				.biography("a Bio")
				.idOfCategory("catid");
		
		IllegalArgumentException e = 
			assertThrows(IllegalArgumentException.class, () -> 
					builder.build());
		
		assertEquals("name.should.not.be.null", e.getMessage());
	}
	
	@Test
	public void should_throw_when_name_is_empty() {
		
		NewPet.NewPetBuilder builder = NewPet.builder()
				.name(" ")
				.birthdate(LocalDate.now())
				.biography("a Bio")
				.idOfCategory("catid");
		
		IllegalArgumentException e = 
				assertThrows(IllegalArgumentException.class, () -> 
						builder.build());
			
		assertEquals("name.should.not.be.empty", e.getMessage());
	}
	
	@Test
	public void should_throw_when_birthdate_is_null() {
		
		NewPet.NewPetBuilder builder = NewPet.builder()
				.name("a name")
				.birthdate(null)
				.biography("a Bio")
				.idOfCategory("catid");
		
		IllegalArgumentException e = 
			assertThrows(IllegalArgumentException.class, () -> 
					builder.build());
			
		assertEquals("birthdate.should.not.be.null", e.getMessage());
	}
	
	@Test
	public void should_throw_when_biography_is_null() {
		
		NewPet.NewPetBuilder builder = NewPet.builder()
				.name("a name")
				.birthdate(LocalDate.now())
				.biography(null)
				.idOfCategory("catid");
		
		IllegalArgumentException e = 
			assertThrows(IllegalArgumentException.class, () -> 
					builder.build());
			
		assertEquals("biography.should.not.be.null", e.getMessage());
	}
	
	@Test
	public void should_throw_when_biography_is_empty() {
		
		NewPet.NewPetBuilder builder = NewPet.builder()
				.name("a name")
				.birthdate(LocalDate.now())
				.biography(" ")
				.idOfCategory("catid");
		
		IllegalArgumentException e = 
			assertThrows(IllegalArgumentException.class, () -> 
					builder.build());
			
		assertEquals("biography.should.not.be.empty", e.getMessage());
	}
	
	@Test
	public void should_throw_when_category_is_null() {
		
		NewPet.NewPetBuilder builder = NewPet.builder()
				.name("a name")
				.birthdate(LocalDate.now())
				.biography("a bio")
				.idOfCategory(null);
		
		IllegalArgumentException e = 
			assertThrows(IllegalArgumentException.class, () -> 
					builder.build());
			
		assertEquals("category.should.not.be.null", e.getMessage());
	}
	
	@Test
	public void should_throw_when_category_is_empty() {
		
		NewPet.NewPetBuilder builder = NewPet.builder()
				.name("a name")
				.birthdate(LocalDate.now())
				.biography("a bio")
				.idOfCategory(" ");
		
		IllegalArgumentException e = 
			assertThrows(IllegalArgumentException.class, () -> 
					builder.build());
			
		assertEquals("category.should.not.be.empty", e.getMessage());
	}
}
