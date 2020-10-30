package top.alvinsite.framework.mail.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.alvinsite.framework.mail.Mail;
import top.alvinsite.framework.mail.service.MailService;

/**
 * @author Alvin
 */
@Slf4j
@RequestMapping("test/mail")
@RestController
public class MailController {

    @Autowired
    private MailService mailService;

    @GetMapping
    public Mail index() {
        Mail mail = new Mail();
        mail.setId("123456");
        mail.setSubject("测试");
        mail.setText("这是一封测试邮件");
        mail.setTo("543046534@qq.com");

        return mailService.sendMail(mail);
    }
}
