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
package petstore.pet.usecase;

import static java.util.Objects.requireNonNull;
import static petstore.pet.usecase.PetValidator.requireValid;

import petstore.pet.domain.entity.Category;
import petstore.pet.domain.entity.Pet;
import petstore.pet.usecase.exception.PetAlreadyExistsException;
import petstore.pet.usecase.exception.PetValidationException;
import petstore.pet.usecase.port.CategoryDatastore;
import petstore.pet.usecase.port.PetDatastore;

/**
 * 
 * @author fabiojose
 *
 */
public class CreatePet {
	
	private final PetDatastore pets;
	private final CategoryDatastore categories;
	
	/**
	 * 
	 * @param pets
	 * @param categories
	 * @throws NullPointerException When any argument is <code>null</code>
	 */
	public CreatePet(PetDatastore pets, CategoryDatastore categories) {	
		this.pets = requireNonNull(pets);
		this.categories = requireNonNull(categories);
	}
	
	/**
	 * 
	 * @param pet An instance to create
	 * @throws PetAlreadyExistsException When the same entity already exists
	 * inside domain
	 * @throws PetValidationException When the entity instance is valid
	 * @throws NullPointerException When pet argument is <code>null</code>
	 */
	public void create(Pet pet) {
		
		// Validate
		Pet _pet = requireValid(pet);
		
		// Already exists?
		pets.get(_pet.getId())
			.ifPresent((exists) -> {
				throw new PetAlreadyExistsException("pet already exists: " 
						+ exists.getId());
			});
		
		// Get category by id
		Category category = 
			categories.get(_pet.getCategory().getId())
				.orElseThrow(() -> 
					new PetValidationException("category.not.found"));
		
		// Read category from the data store
		Pet _tosave = _pet
			.toBuilder()
				.category(category)
				.build();
		
		// Create using the data store
		pets.put(_tosave);
		
		//TODO Fire event about pet creation?
		
	}
	
}
