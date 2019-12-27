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
package petstore.pet.domain.entity;

import java.time.LocalDate;
import java.util.Optional;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import petstore.pet.domain.exception.PetValidationException;

/**
 * 
 * @author fabiojose
 *
 */
@Getter
@ToString
@EqualsAndHashCode
public class Pet {

	private final String id;
	
	private final String name;
	private final LocalDate birth;
	private final String bio;
	private final Category category;
	
	/**
	 * @throws PetValidationException When some argument is non-valid
	 */
	@Builder(toBuilder = true)
	Pet(String id, String name, LocalDate birth, String bio, Category category) {
		this.id = requireNonEmpty(id, "id");
		this.name = requireNonEmpty(name, "name");
		this.birth = requireNonEmpty(birth, "birth");
		this.bio = requireNonEmpty(bio, "bio");
		this.category = requireNonEmpty(category, "category");
	}
	
	static <T> T requireNonEmpty(T value, String argName) {
		
		Optional.ofNullable(value)
			.orElseThrow(() -> 
				new PetValidationException(argName 
						+ ".should.not.be.null"));
		
		if(value instanceof String) {
			if (((String)value).trim().isEmpty()) {
				throw new PetValidationException(argName 
						+ ".should.not.be.empty");
			}
		}
		
		return value;
	}
	
}
