package top.alvinsite.framework.springsecurity;

import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 自定义 WebAuthenticationDetails，添加验证码逻辑
 */

public class MyWebAuthenticationDetails extends WebAuthenticationDetails {

    private boolean imageCodeIsRight = false;

    public MyWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        String imageCode = request.getParameter("captcha");
        HttpSession session = request.getSession();
        String savedImageCode = (String) session.getAttribute("captcha");
        if (!StringUtils.isEmpty(savedImageCode)) {
            // 清除验证码，不管是成功失败，使用过后的验证码都应该失效
            session.removeAttribute("captcha");

            // 当验证码正确时设置状态
            if (!StringUtils.isEmpty(imageCode) && imageCode.equals(savedImageCode)) {
                this.imageCodeIsRight = true;
            }
        }
    }

    public boolean getImageCodeIsRight() {
        return this.imageCodeIsRight;
    }
}
