package it.cnr.ilc.projectx.filter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import it.cnr.ilc.projectx.errors.ApiError;
import it.cnr.ilc.projectx.errors.ApiSubError;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class FilterErrors {

    private FilterErrors() {
    }

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public enum ErrorCode {
        UNEXPECTED_ERROR("Unexpected error"),
        ACCOUNT_NOT_FOUND("Incomplete authentication"),
        ACCOUNT_DISABLED("Account disabled");

        private final String message;

        ErrorCode(String message) {
            this.message = message;
        }
    }

    public static ApiError<ApiSubError> fromErrorCode(HttpStatus status, ErrorCode errorCode, String debugMessage) {
        ApiError<ApiSubError> apiError = new ApiError<>(status);
        apiError.setCode(errorCode.name());
        apiError.setMessage(errorCode.getMessage());
        apiError.setDebugMessage(debugMessage);
        return apiError;
    }


    public static void setError(HttpServletRequest req, HttpServletResponse res, ApiError<?> apiError) throws IOException {
        log.error(String.format("%s - %s", req.getRequestURI(), apiError.getDebugMessage()));

        res.reset();
        res.setStatus(apiError.getStatus().value());
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");

        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
        String body = mapper.writeValueAsString(apiError);
        res.getWriter().write(body);
        res.flushBuffer();
    }
}
