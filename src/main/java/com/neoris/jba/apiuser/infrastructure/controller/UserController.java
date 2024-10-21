package com.neoris.jba.apiuser.infrastructure.controller;

import com.neoris.jba.apiuser.infrastructure.dto.MessageDto;
import com.neoris.jba.apiuser.infrastructure.dto.ResponseDto;
import com.neoris.jba.apiuser.infrastructure.dto.UserDto;
import com.neoris.jba.apiuser.infrastructure.usecase.IUserCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    IUserCase iUserCase;
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "register User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User Created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessageDto.class))}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessageDto.class))})}
    )
    public ResponseEntity<Object> createUser(@Valid @RequestBody UserDto userDto) {
        try {
            ResponseDto response = iUserCase.addUserUseCase(userDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(new MessageDto(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
