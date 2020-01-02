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

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import petstore.pet.domain.entity.Category;
import petstore.pet.domain.entity.Pet;
import petstore.pet.usecase.port.PetIdGenerator;

/**
 * 
 * @author fabiojose
 *
 */
@Getter
@ToString
@EqualsAndHashCode
public class NewPet {

	private final String name;
	private final LocalDate birthdate;
	private final String biography;
	private final String idOfCategory;
	
	/**
	 * @throws IllegalArgumentException When some argument is non-valid
	 */
	@Builder(toBuilder = true)
	NewPet(String name, LocalDate birthdate, String biography,
			String idOfCategory) {
		
		this.name = requireNonEmpty(name, "name");
		this.birthdate = requireNonEmpty(birthdate, "birthdate");
		this.biography = requireNonEmpty(biography, "biography");
		this.idOfCategory = requireNonEmpty(idOfCategory, "category");
	}
	
	static <T> T requireNonEmpty(T value, String argName) {
		
		Optional.ofNullable(value)
			.orElseThrow(() -> 
				new IllegalArgumentException(argName 
						+ ".should.not.be.null"));
		
		if(value instanceof String) {
			if (((String)value).trim().isEmpty()) {
				throw new IllegalArgumentException(argName 
						+ ".should.not.be.empty");
			}
		}
		
		return value;
	}
	
	@Mapper
	public static abstract class NewPetMapper {
		
		protected PetIdGenerator generator;
		
		/**
		 * @throws NullPointerException When argument is {@code null}
		 */
		public void setGenerator(PetIdGenerator generator) {
			this.generator = Objects.requireNonNull(generator);
		}

		@Mapping(target = "id", expression = "java(generator.nextId())")
		@Mapping(source = "pet.name", target = "name")
		@Mapping(source = "pet.birthdate", target = "birth")
		@Mapping(source = "pet.biography", target = "bio")
		@Mapping(source = "category.id", target = "category.id")
		@Mapping(source = "category.name", target = "category.name")
		@Mapping(source = "category.description", target = "category.description")
		public abstract Pet map(NewPet pet, Category category);
		
	}
}
