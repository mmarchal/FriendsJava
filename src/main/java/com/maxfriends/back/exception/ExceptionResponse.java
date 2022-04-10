package com.maxfriends.back.exception;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@ApiModel(value = "ExceptionResponse", description = "The Error Response with detailled informations")
public class ExceptionResponse {

    @ApiModelProperty(position = 1, required = true, value = "HTTP status Error", dataType = "java.lang.Integer", example = "418")
    private Integer status;
    @ApiModelProperty(position = 2, required = true, value = "The detailed Error message", dataType = "java.lang.String", example = "Test")
    private String message;
}
