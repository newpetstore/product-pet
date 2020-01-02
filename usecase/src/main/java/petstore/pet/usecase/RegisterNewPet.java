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

import org.mapstruct.factory.Mappers;

import petstore.pet.domain.entity.Category;
import petstore.pet.domain.entity.Pet;
import petstore.pet.usecase.exception.PetAlreadyExistsException;
import petstore.pet.usecase.model.NewPet;
import petstore.pet.usecase.model.NewPet.NewPetMapper;
import petstore.pet.usecase.model.PetCreated;
import petstore.pet.usecase.model.PetCreated.PetCreatedMapper;
import petstore.pet.domain.exception.PetValidationException;
import petstore.pet.usecase.port.CategoryDatastore;
import petstore.pet.usecase.port.PetIdGenerator;
import petstore.pet.usecase.port.PetDatastore;

/**
 * 
 * @author fabiojose
 *
 */
public class RegisterNewPet {
	
	private final PetDatastore pets;
	
	private final CategoryDatastore categories;
	
	private final NewPetMapper mapper;
	
	/**
	 * 
	 * @param pets
	 * @param petIdGenerator
	 * @param categories
	 * @throws NullPointerException When any argument is <code>null</code>
	 */
	public RegisterNewPet(PetDatastore pets, PetIdGenerator petIdGenerator,
			CategoryDatastore categories) {	
		this.pets = requireNonNull(pets);
		this.categories = requireNonNull(categories);
		
		this.mapper = Mappers.getMapper(NewPetMapper.class);
		this.mapper.setGenerator(requireNonNull(petIdGenerator));
	}
	
	/**
	 * 
	 * @param newPet An instance to create
	 * @throws PetAlreadyExistsException When the same entity already exists
	 * inside domain
	 * @throws PetValidationException When the entity instance is invalid
	 * @throws NullPointerException When the argument is <code>null</code>
	 */
	public PetCreated create(NewPet newPet) {
		
		// Validate
		NewPet _newPet = requireNonNull(newPet);
		
		// Get category by id
		Category category = 
			categories.get(_newPet.getIdOfCategory())
				.orElseThrow(() -> 
					new PetValidationException("category.not.found"));
		
		// Mapping to domain entity
		// - identity creation: see NewPetMapper
		Pet _pet = mapper.map(_newPet, category);
		
		// Already exists?
		pets.get(_pet.getId())
			.ifPresent((exists) -> {
				throw new PetAlreadyExistsException(exists.getId());
			});
		
		// Create using the data store
		pets.put(_pet);
		
		//TODO Fire event about pet creation?
		
		return PetCreatedMapper.INSTANCE.map(_pet);
	}	
}
