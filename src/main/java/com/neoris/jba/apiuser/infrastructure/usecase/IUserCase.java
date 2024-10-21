package com.neoris.jba.apiuser.infrastructure.usecase;

import com.neoris.jba.apiuser.infrastructure.dto.ResponseDto;
import com.neoris.jba.apiuser.infrastructure.dto.UserDto;

public interface IUserCase {

    ResponseDto addUserUseCase(UserDto usuarioDto);

    UserDto getByEmailUseCase(String Email);
}
