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
package petstore.pet.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import petstore.pet.adapter.PetRegisterController;
import petstore.pet.usecase.RegisterNewPet;
import petstore.pet.usecase.port.Categories;
import petstore.pet.usecase.port.PetDatastore;
import petstore.pet.usecase.port.PetIdGenerator;

/**
 * 
 * @author fabiojose
 *
 */

//To enable Spring Boot Application resources
@ComponentScan()

//To enable the Data Store
@ComponentScan("petstore.pet.datastore.mongodb.spring")

//To enable Mongo DB Repository
@EnableMongoRepositories("petstore.pet.datastore.mongodb.spring")

@Configuration
public class Config {

	@Bean
	public static RegisterNewPet registerNewPetUseCase(
			PetDatastore pets,
			PetIdGenerator petIdGenerator,
			Categories categories) {
		
		return new RegisterNewPet(pets, petIdGenerator, categories);
	}
	
	@Bean
	public static PetRegisterController petRegisterController(
			RegisterNewPet usecase) {
		
		return new PetRegisterController(usecase);
	}
}
