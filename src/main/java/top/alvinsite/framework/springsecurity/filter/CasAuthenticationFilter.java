package top.alvinsite.framework.springsecurity.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;
import top.alvinsite.framework.springsecurity.token.CasAuthenticationToken;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
public class CasAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter;

    // ~ Static fields/initializers
    // =====================================================================================

    public static final String SPRING_SECURITY_FORM_TOKEN_KEY = "token";
    public static final String SPRING_SECURITY_FORM_STATE_KEY = "state";

    private String tokenParameter = SPRING_SECURITY_FORM_TOKEN_KEY;
    private String stateParameter = SPRING_SECURITY_FORM_STATE_KEY;

    private boolean postOnly = false;

    // ~ Constructors
    // ===================================================================================================

    public CasAuthenticationFilter() {
        // 短信登录的请求 post 方式的 /sms/login
        super(new AntPathRequestMatcher("/cas/login", "GET"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

        log.info("running attemptAuthentication");

        if (postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }

        String token = obtainToken(request);
        String state = obtainState(request);

        if (token == null) {
            token = "";
        }

        if (state == null) {
            state = "";
        }

        token = token.trim();

        CasAuthenticationToken authRequest = new CasAuthenticationToken(
                token);

        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }

    // ~ Methods
    // ========================================================================================================

    @Nullable
    protected String obtainToken(HttpServletRequest request) {
        return request.getParameter(tokenParameter);
    }


    @Nullable
    protected String obtainState(HttpServletRequest request) {
        return request.getParameter(stateParameter);
    }

    /**
     * Provided so that subclasses may configure what is put into the authentication
     * request's details property.
     *
     * @param request that an authentication request is being created for
     * @param authRequest the authentication request object that should have its details
     * set
     */
    protected void setDetails(HttpServletRequest request,
                              CasAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    /**
     * Sets the parameter name which will be used to obtain the username from the login
     * request.
     *
     * @param tokenParameter the parameter name. Defaults to "username".
     */
    public void setUsernameParameter(String tokenParameter) {
        Assert.hasText(tokenParameter, "token parameter must not be empty or null");
        this.tokenParameter = tokenParameter;
    }

    /**
     * Sets the parameter name which will be used to obtain the password from the login
     * request..
     *
     * @param stateParameter the parameter name. Defaults to "password".
     */
    public void setPasswordParameter(String stateParameter) {
        // Assert.hasText(passwordParameter, "Password parameter must not be empty or null");
        this.stateParameter = stateParameter;
    }

    /**
     * Defines whether only HTTP POST requests will be allowed by this filter. If set to
     * true, and an authentication request is received which is not a POST request, an
     * exception will be raised immediately and authentication will not be attempted. The
     * <tt>unsuccessfulAuthentication()</tt> method will be called as if handling a failed
     * authentication.
     * <p>
     * Defaults to <tt>true</tt> but may be overridden by subclasses.
     */
    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    public final String getTokenParameter() {
        return tokenParameter;
    }

    public final String getStateParameter() {
        return stateParameter;
    }
}
