package top.alvinsite.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Alvin
 */
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
