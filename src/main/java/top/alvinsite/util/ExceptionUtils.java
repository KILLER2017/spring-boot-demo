package top.alvinsite.util;

import top.alvinsite.framework.mail.Mail;
import top.alvinsite.framework.springsecurity.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.UUID;

/**
 * Exception utilities.
 * <p>Part from apache commons lang3 project.</p>
 *
 * @author johnniang
 * @see "org.apache.commons.lang3.exception.ExceptionUtils"
 */
public class ExceptionUtils {

    /**
     * <p>Gets the stack trace from a Throwable as a String.</p>
     *
     * <p>The result of this method vary by JDK version as this method
     * uses {@link Throwable#printStackTrace(PrintWriter)}.
     * On JDK1.3 and earlier, the cause exception will not be shown
     * unless the specified throwable alters printStackTrace.</p>
     *
     * @param throwable the <code>Throwable</code> to be examined
     * @return the stack trace as generated by the exception's
     * <code>printStackTrace(PrintWriter)</code> method
     */
    public static String getStackTrace(final Throwable throwable) {
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw, true);
        throwable.printStackTrace(pw);
        return sw.getBuffer().toString();
    }

    public static Mail buildMail(final Throwable throwable, final User user, final HttpServletRequest request) {
        Mail mail = new Mail();
        mail.setId(UUID.randomUUID().toString());
        mail.setTo("543046534@qq.com");
        mail.setSubject("科研绩效 | 未知异常：" + throwable.getMessage());

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("异常类型：").append(throwable).append("\n")
                .append("异常信息：").append(throwable.getMessage()).append("\n");

        stringBuilder.append(getLoginUserInfo(user));
        stringBuilder.append(getRequestInfo(request));

        stringBuilder.append("异常跟踪：\n").append(getStackTrace(throwable)).append("\n");

        mail.setText(stringBuilder.toString());
        return mail;
    }

    private static String getLoginUserInfo(User user) {
        StringBuilder builder = new StringBuilder();
        if (user != null) {
            builder.append("请求用户：").append(user.getUsername()).append("\n")
                    .append("用户权限：").append(user.getAuthorities()).append("\n");
        }
        return builder.toString();
    }

    private static String getRequestInfo(HttpServletRequest request) {
        StringBuilder builder = new StringBuilder();
        builder.append("请求URI：").append(request.getRequestURI()).append("\n")
                .append("请求方式：").append(request.getMethod()).append("\n")
                .append("query参数：").append(request.getQueryString()).append("\n")
                .append("authorization：").append(request.getHeader("authorization")).append("\n")
                .append("GET参数：").append(request.getParameterMap().toString()).append("\n");

        return builder.toString();
    }
}