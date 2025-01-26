package com.personal.crud_pulsar

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

// Custom Exception Class
class UserNotFoundException(message: String) : RuntimeException(message)
class BadRequestException(message: String) : RuntimeException(message)
class ConflictException(message: String) : RuntimeException(message)
class InternalServerErrorException(message: String) : RuntimeException(message)

@ControllerAdvice
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(UserNotFoundException::class)
    fun handleUserNotFound(exception: UserNotFoundException): ResponseEntity<Map<String, String>> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mapOf(
            "errCode" to HttpStatus.NOT_FOUND.value().toString(),
            "description" to exception.message.orEmpty()
        ))
    }

    @ExceptionHandler(BadRequestException::class)
    fun handleBadRequest(exception: BadRequestException): ResponseEntity<Map<String, String>> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapOf(
            "errCode" to HttpStatus.BAD_REQUEST.value().toString(),
            "description" to exception.message.orEmpty()
        ))
    }

    @ExceptionHandler(ConflictException::class)
    fun handleConflict(exception: ConflictException): ResponseEntity<Map<String, String>> {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(mapOf(
            "errCode" to HttpStatus.CONFLICT.value().toString(),
            "description" to exception.message.orEmpty()
        ))
    }

    @ExceptionHandler(InternalServerErrorException::class)
    fun handleInternalServerError(exception: InternalServerErrorException): ResponseEntity<Map<String, String>> {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mapOf(
            "errCode" to HttpStatus.INTERNAL_SERVER_ERROR.value().toString(),
            "description" to exception.message.orEmpty()
        ))
    }
}
