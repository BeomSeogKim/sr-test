package sr.backend.test.common;

import org.springframework.http.HttpStatus;

public record ApiResponse<T>(
    int code,
    T body
) {

    public static ApiResponse<String> created(String message) {
        return new ApiResponse<>(201, message);
    }

    public static ApiResponse<Object> of(HttpStatus httpStatus, String defaultMessage) {
        return new ApiResponse<>(httpStatus.value(), defaultMessage);
    }
}

