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

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import feign.FeignException;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * Fallback impl. for {@link GetByIdRestClient}
 *
 * @author fabiojose
 */
@Slf4j
@Component
public class GetByIdRestClientFallback
    implements FallbackFactory<GetByIdRestClient> {

    @Override
    public GetByIdRestClient create(Throwable cause) {
        log.error(cause.getMessage(), cause);

        int status =
            Optional.ofNullable(cause)
                .filter(c -> c instanceof FeignException)
                .map(c -> (FeignException)c)
                .map(FeignException::status)
                .orElse(Integer.MIN_VALUE);

        log.error("HTTP status code {}", status);

        // 404, will result in null
        if(status == HttpStatus.NOT_FOUND.value()){
            return new GetByIdRestClient() {
                @Override
                public CategoryDTO getById(String id) {
                    return null;
                }
            };
        } else {
            throw new RuntimeException(cause.getMessage(), cause);
        }
    }
}
