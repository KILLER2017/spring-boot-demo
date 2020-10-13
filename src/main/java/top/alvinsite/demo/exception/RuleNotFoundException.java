package top.alvinsite.demo.exception;

import org.springframework.http.HttpStatus;

public class RuleNotFoundException extends AbstractHaloException {
    public RuleNotFoundException(String message) {
        super(message);
    }

    public RuleNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
