package com.codingshuffle.springbootwebtutorial.springbootwebtutorial.advices;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

public class ApiError {

    private final String message;
    private final HttpStatus status;
    private final List<String> subErrors;
    private final LocalDateTime timestamp;

    // ✅ Private constructor used by Builder
    private ApiError(Builder builder) {
        this.message = builder.message;
        this.status = builder.status;
        this.subErrors = builder.subErrors;
        this.timestamp = builder.timestamp;
    }

    // ✅ Static inner Builder class
    public static class Builder {
        private String message;
        private HttpStatus status;
        private List<String> subErrors;
        private LocalDateTime timestamp;

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder status(HttpStatus status) {
            this.status = status;
            return this;
        }

        public Builder subErrors(List<String> subErrors) {
            this.subErrors = subErrors;
            return this;
        }

        public Builder timestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public ApiError build() {
            return new ApiError(this);
        }
    }

    // ✅ Static method to start builder
    public static Builder builder() {
        return new Builder();
    }

    // ✅ Getters
    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public List<String> getSubErrors() {
        return subErrors;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
