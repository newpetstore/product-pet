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
package petstore.pet.usecase.port;

import java.util.Optional;

import petstore.pet.domain.entity.Pet;

/**
 * 
 * @author fabiojose
 *
 */
public interface PetDatastore {

	/**
	 * Creates or Updates pets
	 * 
	 * @param pet Entity instance to create in the domain
	 */
	void put(Pet pet);
	
	/**
	 * Reads pets by id
	 * 
	 * @param id Identity of Pet
	 * @return An optional found instance of pet
	 */
	Optional<Pet> get(String id);
	
	/**
	 * Deletes pets by id
	 * 
	 * @param id Identity of entity to delete from domain
	 * 
	 */
	void del(String id);
}
