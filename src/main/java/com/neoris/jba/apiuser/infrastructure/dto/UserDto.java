package com.neoris.jba.apiuser.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.neoris.jba.apiuser.domain.validation.anotation.UniqueEmail;
import com.neoris.jba.apiuser.infrastructure.validation.anotations.ValidEmail;
import com.neoris.jba.apiuser.infrastructure.validation.anotations.ValidPassword;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @JsonProperty("id")
    private String id;

    @NotBlank()
    @JsonProperty("name")
    private String name;
    @ValidEmail
    @UniqueEmail
    @NotBlank()
    @JsonProperty("email")
    private String email;
    @ValidPassword
    @NotBlank()
    @JsonProperty("password")
    private String password;
    @JsonProperty("isActive")
    private Boolean isActive;
    @JsonProperty("created")
    private Date create;
    @JsonProperty("modified")
    private Date modified;
    @JsonProperty("last_login")
    private Date lastLogin;
    @JsonProperty("token")
    private String token;
    @Valid
    @JsonProperty("phones")
    private List<PhoneDto> phones;
}
