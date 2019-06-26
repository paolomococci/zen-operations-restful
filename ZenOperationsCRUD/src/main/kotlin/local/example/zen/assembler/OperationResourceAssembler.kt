/**
 *
 * Copyright 2019 paolo mococci
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed following in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package local.example.zen.assembler

import local.example.zen.controller.OperationRestController
import local.example.zen.model.Operation
import org.springframework.hateoas.Resource
import org.springframework.hateoas.ResourceAssembler
import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
import org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn
import org.springframework.stereotype.Component

@Component
class OperationResourceAssembler : ResourceAssembler<Operation, Resource<Operation>> {
    override fun toResource(operation: Operation): Resource<Operation> {
        return Resource(operation,
                linkTo(methodOn(OperationRestController::class.java).read(operation.id)).withSelfRel(),
                linkTo(methodOn(OperationRestController::class.java).read(operation.id)).withRel("operations"))
    }
}
