package top.alvinsite.demo.core;


import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.jexl3.JexlException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.Assert;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;
import top.alvinsite.demo.config.properties.AppProperties;
import top.alvinsite.demo.model.support.BaseResponse;
import top.alvinsite.framework.mail.Mail;
import top.alvinsite.framework.mail.service.MailService;
import top.alvinsite.framework.springsecurity.entity.User;
import top.alvinsite.utils.ExceptionUtils;
import top.alvinsite.utils.ValidationUtils;
import top.alvinsite.exception.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.Map;


/**
 * Exception handler of controller.
 *
 * @author johnniang
 */
@RestControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

    @Autowired
    private AppProperties appProperties;

    @Autowired
    private MailService mailService;

    @ExceptionHandler(MybatisPlusException.class)
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse handleMybatisPlusException(Exception e) {
        BaseResponse baseResponse = handleBaseException(e);
        HttpStatus status = HttpStatus.BAD_REQUEST;
        baseResponse.setStatus(status.value());
        baseResponse.setMessage(e.getMessage());
        return baseResponse;
    }

    @ExceptionHandler(MyJexlExpression.class)
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse handleMyJexlExpression(Exception e) {
        BaseResponse baseResponse = handleBaseException(e);
        HttpStatus status = HttpStatus.BAD_REQUEST;
        baseResponse.setStatus(status.value());

        String errorMessage = e.getMessage();

        errorMessage = errorMessage.replaceAll("a", "[激励绩效工资]");
        errorMessage = errorMessage.replaceAll("b", "[超课时津贴]");
        errorMessage = errorMessage.replaceAll("c", "[岗位津贴]");
        errorMessage = errorMessage.replaceAll("d", "[级差系数]");
        errorMessage = errorMessage.replaceAll("e", "[年度目标教学工作量]");
        errorMessage = errorMessage.replaceAll("f", "[年度目标科研工作量]");
        errorMessage = errorMessage.replaceAll("g", "[实际教学工作量]");
        errorMessage = errorMessage.replaceAll("h", "[实际科研工作量]");
        errorMessage = errorMessage.replaceAll("i", "[个人实际民主测评值]");

        baseResponse.setMessage(errorMessage);
        return baseResponse;
    }

    @ExceptionHandler(JexlException.class)
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse handleJexlException(Exception e) {
        BaseResponse baseResponse = handleBaseException(e);
        HttpStatus status = HttpStatus.BAD_REQUEST;
        baseResponse.setStatus(status.value());
        baseResponse.setMessage(e.getLocalizedMessage());
        return baseResponse;
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse handleDataIntegrityViolationException(Exception e) {
        BaseResponse baseResponse = handleBaseException(e);
        HttpStatus status = HttpStatus.BAD_REQUEST;
        baseResponse.setStatus(status.value());
        baseResponse.setMessage(e.getMessage());
        return baseResponse;
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse handleHttpRequestMethodNotSupportedException(Exception e) {
        BaseResponse baseResponse = handleBaseException(e);
        HttpStatus status = HttpStatus.METHOD_NOT_ALLOWED;
        baseResponse.setStatus(status.value());
        baseResponse.setMessage(e.getMessage());
        return baseResponse;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse handleConstraintViolationException(ConstraintViolationException e) {
        BaseResponse<Map<String, String>> baseResponse = handleBaseException(e);
        baseResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        baseResponse.setMessage("数据验证错误，请完善后重试！");
        baseResponse.setData(ValidationUtils.mapWithValidError(e.getConstraintViolations()));
        return baseResponse;
    }

    @ExceptionHandler(MultipartException.class)
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse handleMultipartException(Exception e) {
        BaseResponse baseResponse = handleBaseException(e);
        HttpStatus status = HttpStatus.BAD_REQUEST;
        baseResponse.setStatus(status.value());
        baseResponse.setMessage("请上传需要导入的数据文件");
        return baseResponse;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        BaseResponse<?> baseResponse = handleBaseException(e);
        baseResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        baseResponse.setMessage("缺失请求主体");
        return baseResponse;
    }


    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse handleIllegalArgumentException(Exception e) {
        BaseResponse baseResponse = handleBaseException(e);
        HttpStatus status = HttpStatus.BAD_REQUEST;
        baseResponse.setStatus(status.value());
        baseResponse.setMessage(e.getMessage());
        return baseResponse;
    }

    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse handleForbiddenException(Exception e) {
        BaseResponse baseResponse = handleBaseException(e);
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        baseResponse.setStatus(status.value());
        baseResponse.setMessage(e.getMessage());
        return baseResponse;
    }
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse handleBindException(BindException e) {
        BaseResponse baseResponse = handleBaseException(e);
        HttpStatus status = HttpStatus.BAD_REQUEST;
        baseResponse.setStatus(status.value());
        baseResponse.setMessage("数据验证错误，请完善后重试！");
        Map<String, String> errMap = ValidationUtils.mapWithFieldError(e.getBindingResult().getFieldErrors());
        baseResponse.setData(errMap);
        return baseResponse;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        BaseResponse<Map<String, String>> baseResponse = handleBaseException(e);
        baseResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        baseResponse.setMessage("数据验证错误，请完善后重试！");
        Map<String, String> errMap = ValidationUtils.mapWithFieldError(e.getBindingResult().getFieldErrors());
        baseResponse.setData(errMap);
        return baseResponse;
    }



    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse handleGlobalException(Exception e, @AuthenticationPrincipal User user, HttpServletRequest request) {

        // 发送未知异常告警邮件
        if (appProperties.isEnableUnknownExceptionMailAlert()) {
            Mail mail = ExceptionUtils.buildMail(e, user, request);
            mailService.sendMail(mail);
        }

        BaseResponse baseResponse = handleBaseException(e);
        log.info(e.getMessage());
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        baseResponse.setStatus(status.value());
        baseResponse.setMessage(status.getReasonPhrase());
        return baseResponse;
    }

    private <T> BaseResponse<T> handleBaseException(Throwable t) {
        Assert.notNull(t, "Throwable must not be null");

        // log.error("Captured an exception", t);

        BaseResponse<T> baseResponse = new BaseResponse<>();
        baseResponse.setMessage(t.getMessage());
        t.printStackTrace();
        if (log.isDebugEnabled()) {
            baseResponse.setDevMessage(ExceptionUtils.getStackTrace(t));
        }

        return baseResponse;
    }

}

