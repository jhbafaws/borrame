package com.neoris.jba.apiuser.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @JsonProperty("id")
    private String id;
    @JsonProperty("created")
    private Date create;
    @JsonProperty("modified")
    private Date modified;
    @JsonProperty("last_login")
    private Date lastLogin;
    @JsonProperty("token")
    private String token;
    @JsonProperty("isActive")
    private Boolean isActive;

}
