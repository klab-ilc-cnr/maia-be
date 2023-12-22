package it.cnr.ilc.maia.errors;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ApiError<T extends ApiSubError> {

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private final LocalDateTime timestamp;

    private final HttpStatus status;

    private String code;

    private String message;

    private String debugMessage;

    private List<T> subErrors = new ArrayList<>();

    public ApiError(HttpStatus status) {
        this.timestamp = LocalDateTime.now();
        this.code = String.valueOf(status.value());
        this.status = status;
    }

    public void addSubError(T error) {
        this.subErrors.add(error);
    }

    public List<T> getSubErrors() {
        return subErrors;
    }
}
