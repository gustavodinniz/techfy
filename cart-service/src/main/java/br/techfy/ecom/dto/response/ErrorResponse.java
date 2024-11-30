package br.techfy.ecom.dto.response;

import br.techfy.ecom.dto.FieldErrorDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.util.List;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorResponse(String code, String message, List<FieldErrorDTO> errors) {

    public static ErrorResponse defaultResponse(String code, String message){
        return ErrorResponse.builder()
                .code(code)
                .message(message)
                .build();
    }

    public static ErrorResponse conflict(String message){
        return ErrorResponse.builder()
                .message(message)
                .build();
    }

    public static ErrorResponse notFound(String message){
        return ErrorResponse.builder()
                .message(message)
                .build();
    }

}
