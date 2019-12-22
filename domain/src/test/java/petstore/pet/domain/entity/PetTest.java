package petstore.pet.domain.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import petstore.pet.domain.exception.NullOrEmptyArgumentException;

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
			.category(new Category("cid", "cname", "cdesc"));
		
		// assert
		NullOrEmptyArgumentException e = 
			assertThrows(NullOrEmptyArgumentException.class, () -> {
				
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
			.category(new Category("cid", "cname", "cdesc"));
		
		// assert
		NullOrEmptyArgumentException e = 
			assertThrows(NullOrEmptyArgumentException.class, () -> {
				
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
				.category(new Category("cid", "cname", "cdesc"));
		
		// assert
		NullOrEmptyArgumentException e = 
			assertThrows(NullOrEmptyArgumentException.class, () -> {
				
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
				.category(new Category("cid", "cname", "cdesc"));
		
		// assert
		NullOrEmptyArgumentException e = 
			assertThrows(NullOrEmptyArgumentException.class, () -> {
				
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
				.category(new Category("cid", "cname", "cdesc"));
		
		// assert
		NullOrEmptyArgumentException e = 
			assertThrows(NullOrEmptyArgumentException.class, () -> {
				
				// act
				builder.build();
				
			});
		
		// assert
		assertEquals("bio.should.not.be.empty", e.getMessage());
	}
	
	@Test
	public void should_throw_on_invalid_pet_category() {
		
		// setup
		Pet.PetBuilder builder = Pet.builder()
				.id("id")
				.name("a pet")
				.birth(LocalDate.now())
				.bio("a bio")
				.category(null);
		
		// assert
		NullOrEmptyArgumentException e = 
			assertThrows(NullOrEmptyArgumentException.class, () -> {
				
				// act
				builder.build();
				
			});
		
		// assert
		assertEquals("category.should.not.be.null", e.getMessage());
	}
}
