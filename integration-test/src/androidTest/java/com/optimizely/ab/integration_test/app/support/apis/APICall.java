/****************************************************************************
 * Copyright 2019, Optimizely, Inc. and contributors                        *
 *                                                                          *
 * Licensed under the Apache License, Version 2.0 (the "License");          *
 * you may not use this file except in compliance with the License.         *
 * You may obtain a copy of the License at                                  *
 *                                                                          *
 *    http://www.apache.org/licenses/LICENSE-2.0                            *
 *                                                                          *
 * Unless required by applicable law or agreed to in writing, software      *
 * distributed under the License is distributed on an "AS IS" BASIS,        *
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. *
 * See the License for the specific language governing permissions and      *
 * limitations under the License.                                           *
 ***************************************************************************/

package com.optimizely.ab.integration_test.app.support.apis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.optimizely.ab.integration_test.app.models.responses.BaseResponse;
import com.optimizely.ab.integration_test.app.support.OptimizelyWrapper;
import com.optimizely.ab.integration_test.app.models.responses.ListenerMethodArrayResponse;
import com.optimizely.ab.integration_test.app.models.responses.ListenerMethodResponse;

import java.util.List;
import java.util.Map;

import static com.optimizely.ab.integration_test.app.optlyplugins.OptimizelyUtils.setListenerResponseCollection;

public abstract class APICall<TResponse> {

    protected ObjectMapper mapper;

    protected APICall() {
        mapper = new ObjectMapper();
    }

    public abstract BaseResponse invokeAPI(OptimizelyWrapper optimizelyWrapper, Object desreailizeObject);

    ListenerMethodResponse sendResponse(TResponse result, OptimizelyWrapper optimizelyWrapper) {
        List<Map<String, Object>> responseCollection = setListenerResponseCollection(optimizelyWrapper);
        return new ListenerMethodResponse<TResponse>(result, responseCollection);
    }

    ListenerMethodArrayResponse sendArrayResponse(List<String> result, OptimizelyWrapper optimizelyWrapper) {
        List<Map<String, Object>> responseCollection = setListenerResponseCollection(optimizelyWrapper);
        return new ListenerMethodArrayResponse(result, responseCollection);
    }
}