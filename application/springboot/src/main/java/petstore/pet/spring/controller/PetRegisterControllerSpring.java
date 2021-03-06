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
package petstore.pet.spring.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import petstore.pet.adapter.PetRegisterController;
import petstore.pet.adapter.model.PetRequest;
import petstore.pet.adapter.model.PetResponse;
import reactor.core.publisher.Mono;

/**
 * 
 * @author fabiojose
 *
 */
@RestController
public class PetRegisterControllerSpring {
	
	@Autowired
	PetRegisterController adapter;

	@PostMapping(
		path = "/pets",
		consumes = "application/json",
		produces = "application/json"
	)
	public Mono<ResponseEntity<PetResponse>> post(
			@Valid
			@RequestBody(required = true)
			PetRequest req) {
		
		return Mono
				.fromFuture(adapter.register(() -> req))
				.onErrorMap(e -> 
					new ResponseStatusException(HttpStatus.BAD_REQUEST,
							e.getMessage(), e))
				.map(body -> ResponseEntity.ok(body));
	}
	
}
