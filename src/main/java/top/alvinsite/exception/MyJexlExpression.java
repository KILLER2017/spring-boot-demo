package top.alvinsite.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Alvin
 */
public class MyJexlExpression extends AbstractHaloException {
    public MyJexlExpression(String message) {
        super(message);
    }

    public MyJexlExpression(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
