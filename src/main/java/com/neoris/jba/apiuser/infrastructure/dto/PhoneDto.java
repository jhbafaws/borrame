package com.neoris.jba.apiuser.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PhoneDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @JsonIgnore
    @JsonProperty("id")
    private String id;
    @NotBlank()
    @JsonProperty("number")
    private String number;
    @NotBlank()
    @JsonProperty("citycode")
    private String cityCode;
    @NotBlank()
    @JsonProperty("countrycode")
    private String countryCode;

    public PhoneDto(String s, String s1, String s2) {
    }
}
