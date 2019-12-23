package petstore.pet.domain.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import petstore.pet.domain.exception.CategoryValidationException;

/**
 * 
 * @author fabiojose
 *
 */
public class CategoryTest {

	@Test
	public void should_throw_on_null_id() {
		
		Category.CategoryBuilder builder = Category.builder()
				.id(null)
				.name("a name")
				.description("a desc");
		
		CategoryValidationException e =
			assertThrows(CategoryValidationException.class, () -> 
					builder.build());
		
		assertEquals("id.should.not.be.null", e.getMessage());
	}
	
	@Test
	public void should_throw_on_empty_id() {
		
		Category.CategoryBuilder builder = Category.builder()
				.id("  ")
				.name("a name")
				.description("a desc");
		
		CategoryValidationException e =
			assertThrows(CategoryValidationException.class, () -> 
					builder.build());
		
		assertEquals("id.should.not.be.empty", e.getMessage());
	}
	
	@Test
	public void should_throw_on_null_name() {
		
		Category.CategoryBuilder builder = Category.builder()
				.id("an id")
				.name(null)
				.description("a desc");
		
		CategoryValidationException e =
			assertThrows(CategoryValidationException.class, () -> 
					builder.build());
		
		assertEquals("name.should.not.be.null", e.getMessage());
	}
	
	@Test
	public void should_throw_on_empty_name() {
		
		Category.CategoryBuilder builder = Category.builder()
				.id("an id")
				.name("  ")
				.description("a desc");
		
		CategoryValidationException e =
			assertThrows(CategoryValidationException.class, () -> 
					builder.build());
		
		assertEquals("name.should.not.be.empty", e.getMessage());
	}
	
	@Test
	public void should_throw_on_null_description() {
		
		Category.CategoryBuilder builder = Category.builder()
				.id("an id")
				.name("a name")
				.description(null);
		
		CategoryValidationException e =
			assertThrows(CategoryValidationException.class, () -> 
					builder.build());
		
		assertEquals("description.should.not.be.null", e.getMessage());
	}
	
	@Test
	public void should_throw_on_empty_description() {
		
		Category.CategoryBuilder builder = Category.builder()
				.id("an id")
				.name("a name")
				.description("  ");
		
		CategoryValidationException e =
			assertThrows(CategoryValidationException.class, () -> 
					builder.build());
		
		assertEquals("description.should.not.be.empty", e.getMessage());
	}
}
