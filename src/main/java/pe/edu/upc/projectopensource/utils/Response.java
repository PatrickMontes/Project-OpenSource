package pe.edu.upc.projectopensource.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {
    private int status;
    private String message;
    private T data;
    private Map<String, String> errors;
}