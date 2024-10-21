package com.neoris.jba.apiuser.infrastructure.usecase.Impl;

import com.neoris.jba.apiuser.domain.entity.Phone;
import com.neoris.jba.apiuser.domain.entity.User;
import com.neoris.jba.apiuser.domain.service.IUser;
import com.neoris.jba.apiuser.infrastructure.dto.ResponseDto;
import com.neoris.jba.apiuser.infrastructure.dto.UserDto;
import com.neoris.jba.apiuser.infrastructure.usecase.IUserCase;
import com.neoris.jba.apiuser.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserCaseImpl implements IUserCase {


    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    IUser iUser;

    @Override
    public ResponseDto addUserUseCase(UserDto usuarioDto) {
        String token = jwtUtils.generateAccessToken(usuarioDto.getEmail());
        Timestamp sqlTimestamp = Timestamp.from(Instant.now());

        User userDb = User.builder()
                .name(usuarioDto.getName())
                .email(usuarioDto.getEmail())
                .password(usuarioDto.getPassword())
                .isActive(usuarioDto.getIsActive())
                .create(sqlTimestamp)
                .modified(sqlTimestamp)
                .lastLogin(sqlTimestamp)
                .isActive(true)
                .token(token)
                .build();

        List<Phone> phoneList = usuarioDto.getPhones().stream()
                .map(phoneDto -> Phone.builder()
                        .number(phoneDto.getNumber())
                        .cityCode(phoneDto.getCityCode())
                        .countryCode(phoneDto.getCountryCode())
                        .user(userDb)
                        .build())
                .collect(Collectors.toList());

        userDb.setPhones(phoneList);
        User saveUser = iUser.saveUser(userDb);

        ResponseDto resp = new ResponseDto();
        resp.setId(saveUser.getId());
        resp.setCreate(saveUser.getCreate());
        resp.setModified(saveUser.getModified());
        resp.setLastLogin(saveUser.getLastLogin());
        resp.setToken(saveUser.getToken());
        resp.setIsActive(saveUser.getIsActive());
        return resp;
    }

    @Override
    public UserDto getByEmailUseCase(String email) {

        Optional<User> userOpt = iUser.getUserByEmail(email);

        if (userOpt.isEmpty()) return null;

        User user = userOpt.get();
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .isActive(user.getIsActive())
                .build();
    }
}

