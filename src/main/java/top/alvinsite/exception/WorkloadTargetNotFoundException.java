package top.alvinsite.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Alvin
 */
public class WorkloadTargetNotFoundException extends AbstractHaloException {
    public WorkloadTargetNotFoundException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
