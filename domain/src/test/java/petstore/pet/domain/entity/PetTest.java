package petstore.pet.domain.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import petstore.pet.domain.exception.PetValidationException;

/**
 * 
 * @author fabiojose
 *
 */
public class PetTest {

	@Test
	public void should_throw_on_null_pet_id() {
		
		// setup
		Pet.PetBuilder builder = Pet.builder()
			.id(null)
			.name("a pet")
			.birth(LocalDate.now())
			.bio("a bio")
			.idOfCategory("cid");
		
		// assert
		PetValidationException e = 
			assertThrows(PetValidationException.class, () -> {
				
				// act
				builder.build();
				
			});
		
		// assert
		assertEquals("id.should.not.be.null", e.getMessage());
	}
	
	@Test
	public void should_throw_on_empty_pet_id() {
		
		// setup
		Pet.PetBuilder builder = Pet.builder()
			.id("   ")
			.name("a pet")
			.birth(LocalDate.now())
			.bio("a bio")
			.idOfCategory("cid");
		
		// assert
		PetValidationException e = 
			assertThrows(PetValidationException.class, () -> {
				
				// act
				builder.build();
				
			});
		
		// assert
		assertEquals("id.should.not.be.empty", e.getMessage());
	}
	
	@Test
	public void should_throw_on_invalid_pet_birth() {
		
		// setup
		Pet.PetBuilder builder = Pet.builder()
				.id("id")
				.name("a pet")
				.birth(null)
				.bio("a bio")
			    .idOfCategory("cid");
		
		// assert
		PetValidationException e = 
			assertThrows(PetValidationException.class, () -> {
				
				// act
				builder.build();
				
			});
		
		// assert
		assertEquals("birth.should.not.be.null", e.getMessage());
	}
	
	@Test
	public void should_throw_on_null_pet_bio() {
		
		// setup
		Pet.PetBuilder builder = Pet.builder()
				.id("id")
				.name("a pet")
				.birth(LocalDate.now())
				.bio(null)
			    .idOfCategory("cid");
		
		// assert
		PetValidationException e = 
			assertThrows(PetValidationException.class, () -> {
				
				// act
				builder.build();
				
			});
		
		// assert
		assertEquals("bio.should.not.be.null", e.getMessage());
	}
	
	@Test
	public void should_throw_on_empty_pet_bio() {
		
		// setup
		Pet.PetBuilder builder = Pet.builder()
				.id("id")
				.name("a pet")
				.birth(LocalDate.now())
				.bio("  ")
			    .idOfCategory("cid");
		
		// assert
		PetValidationException e = 
			assertThrows(PetValidationException.class, () -> {
				
				// act
				builder.build();
				
			});
		
		// assert
		assertEquals("bio.should.not.be.empty", e.getMessage());
	}
	
	@Test
	public void should_throw_on_null_pet_category_id() {
		
		// setup
		Pet.PetBuilder builder = Pet.builder()
				.id("id")
				.name("a pet")
				.birth(LocalDate.now())
				.bio("a bio")
		 	    .idOfCategory(null);
		
		// assert
		PetValidationException e = 
			assertThrows(PetValidationException.class, () -> {
				
				// act
				builder.build();
				
			});
		
		// assert
		assertEquals("idOfCategory.should.not.be.null", e.getMessage());
	}

	@Test
	public void should_throw_on_empty_pet_category_id() {
		
		// setup
		Pet.PetBuilder builder = Pet.builder()
				.id("id")
				.name("a pet")
				.birth(LocalDate.now())
				.bio("bio")
			    .idOfCategory("  ");
		
		// assert
		PetValidationException e = 
			assertThrows(PetValidationException.class, () -> {
				
				// act
				builder.build();
				
			});
		
		// assert
		assertEquals("idOfCategory.should.not.be.empty", e.getMessage());
	}
	
}
