/*
 * Java (TM) Pet Store Modernized Edition - 2020
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
package petstore.pet.http.spring.category;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Adapter to get categories by id, using a http rest endpoint.
 *
 * @author fabiojose
 */
@FeignClient(
    name = "CategoryGetByIdHTTPAdapter",
    url = "${categories.http.endpoint.url}",
    fallbackFactory = GetByIdRestClientFallback.class
)
public interface GetByIdRestClient {

    @GetMapping(
        value = "/categories/{id}",
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public CategoryDTO getById(@PathVariable("id") String id);

}
