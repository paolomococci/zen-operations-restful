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

package local.example.zen.controller

import local.example.zen.assembler.OperationResourceAssembler
import local.example.zen.exception.OperationNotFoundException
import local.example.zen.model.Operation
import local.example.zen.repository.OperationRepository
import org.springframework.hateoas.Resource
import org.springframework.hateoas.Resources
import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
import org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.net.URISyntaxException

@RestController
@RequestMapping("/api/operations")
class OperationRestController(
        private val operationRepository: OperationRepository,
        private val operationResourceAssembler: OperationResourceAssembler
) {

    @PostMapping
    @Throws(URISyntaxException::class)
    internal fun create(@RequestBody operation: Operation): ResponseEntity<Resource<Operation>> {
        val resource = operationResourceAssembler.toResource(operationRepository.save(operation))
        return ResponseEntity.created(URI(resource.id.expand().href)).body(resource)
    }

    @GetMapping("/{id}")
    @Throws(URISyntaxException::class)
    internal fun read(@PathVariable id: Long?): Resource<Operation> {
        val operation = operationRepository.findById(id!!).orElseThrow{
            OperationNotFoundException(id)
        }
        return operationResourceAssembler.toResource(operation)
    }

    @GetMapping
    @Throws(URISyntaxException::class)
    internal fun readAll(): Resources<Resource<Operation>> {
        val operations = operationRepository.findAll().asSequence().map(
                operationResourceAssembler::toResource
        ).toList()
        return Resources(operations, linkTo(methodOn(OperationRestController::class.java).readAll()).withSelfRel())
    }

    @PutMapping("/{id}")
    @Throws(URISyntaxException::class)
    internal fun update(@RequestBody update: Operation, @PathVariable id: Long?): ResponseEntity<*> {
        val updated = operationRepository.findById(id!!)
                .map { temp ->
                    if (!update.name.isNullOrBlank()) temp.name = update.name
                    operationRepository.save(temp)
                }.orElseGet {
                    operationRepository.save(update)
                }
        val resource = operationResourceAssembler.toResource(updated)
        return ResponseEntity.created(URI(resource.id.expand().href)).body(resource)
    }

    @DeleteMapping("/{id}")
    @Throws(URISyntaxException::class)
    internal fun delete(@PathVariable id: Long?): ResponseEntity<*> {
        if (id != null) operationRepository.deleteById(id)
        return ResponseEntity.noContent().build<Any>()
    }
}
