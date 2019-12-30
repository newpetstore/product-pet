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
package petstore.pet.adapter;

import static java.util.Objects.requireNonNull;
import static java.util.concurrent.CompletableFuture.supplyAsync;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import petstore.pet.adapter.model.PetRequest;
import petstore.pet.adapter.model.PetRequestMapper;
import petstore.pet.adapter.model.PetResponse;
import petstore.pet.adapter.model.PetResponseMapper;
import petstore.pet.usecase.RegisterNewPet;
import petstore.pet.usecase.model.NewPet;

/**
 * 
 * @author fabiojose
 *
 */
public class PetRegisterController {

	private final RegisterNewPet register;
	
	/**
	 * 
	 * @param registerNewPet
	 * @throws NullPointerException When any argument is {@code null}
	 */
	public PetRegisterController(RegisterNewPet registerNewPet) {		
		this.register = requireNonNull(registerNewPet);
	}
	
	/**
	 * 
	 * @param req {@link Supplier} to supplies the {@link PetRequest} instance
	 * @throws NullPointerException When {@code req} argument is null 
	 */
	public CompletableFuture<PetResponse> register(Supplier<PetRequest> req) {
		
		final NewPet newPet = 
				PetRequestMapper.INSTANCE.map(req.get());
		
		Objects.requireNonNull(newPet);
		
		return supplyAsync(() -> register.create(newPet))
					.thenApply(PetResponseMapper.INSTANCE::map);
	}
	
}
