package top.alvinsite.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Alvin
 */
public class LevelFactorNotFoundException extends AbstractHaloException {
    public LevelFactorNotFoundException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
