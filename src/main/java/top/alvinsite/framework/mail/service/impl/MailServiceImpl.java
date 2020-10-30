package top.alvinsite.framework.mail.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.alvinsite.framework.mail.Mail;
import top.alvinsite.framework.mail.service.MailService;

import java.util.Date;
import java.util.Objects;

/**
 * @author Alvin
 */
@Service
public  class MailServiceImpl implements MailService {

    /**
     * 注入邮件工具类
     */
    @Autowired
    private JavaMailSenderImpl mailSender;

    @Async
    @Override
    public Mail sendMail(Mail mail) {
        try {
            // 1.检测邮件
            checkMail(mail);
            // 2.发送邮件
            sendMimeMail(mail);
            // 3.保存邮件
            return saveMail(mail);
        } catch (Exception e) {
            mail.setStatus("fail");
            mail.setError(e.getMessage());
            return mail;
        }
    }

    /**
     * // 检测邮件信息类
     * @param mail 邮件
     */
    private void checkMail(Mail mail) {
        if (StringUtils.isEmpty(mail.getTo())) {
            throw new RuntimeException("邮件收信人不能为空");
        }
        if (StringUtils.isEmpty(mail.getSubject())) {
            throw new RuntimeException("邮件主题不能为空");
        }
        if (StringUtils.isEmpty(mail.getText())) {
            throw new RuntimeException("邮件内容不能为空");
        }
    }

    /**
     * //构建复杂邮件信息类
     * @param mail 邮件
     */
    private void sendMimeMail(Mail mail) {
        try {
            // true表示支持复杂类型
            MimeMessageHelper messageHelper = new MimeMessageHelper(mailSender.createMimeMessage(), true);

            // 邮件发信人从配置项读取
            mail.setFrom(getMailSendFrom());

            // 邮件发信人
            messageHelper.setFrom(mail.getFrom());

            // 邮件收信人
            messageHelper.setTo(mail.getTo().split(","));

            // 邮件主题
            messageHelper.setSubject(mail.getSubject());

            // 邮件内容
            messageHelper.setText(mail.getText());
            // 抄送
            if (!StringUtils.isEmpty(mail.getCc())) {
                messageHelper.setCc(mail.getCc().split(","));
            }
            // 密送
            if (!StringUtils.isEmpty(mail.getBcc())) {
                messageHelper.setCc(mail.getBcc().split(","));
            }

            // 添加邮件附件
            if (mail.getMultipartFiles() != null) {
                for (MultipartFile multipartFile : mail.getMultipartFiles()) {
                    messageHelper.addAttachment(Objects.requireNonNull(multipartFile.getOriginalFilename()), multipartFile);
                }
            }

            // 发送时间
            if (mail.getSentDate() == null) {
                mail.setSentDate(new Date());
                messageHelper.setSentDate(mail.getSentDate());
            }

            // 正式发送邮件
            mailSender.send(messageHelper.getMimeMessage());
            mail.setStatus("ok");
        } catch (Exception e) {
            // 发送失败
            throw new RuntimeException(e);
        }
    }

    /**
     * //保存邮件
     * @param mail 被保存的邮件
     * @return 已保存的邮件
     */
    private Mail saveMail(Mail mail) {

        //将邮件保存到数据库..

        return mail;
    }

    /**
     * 获取邮件发信人
     * @return 发信人
     */
    public String getMailSendFrom() {
        return mailSender.getJavaMailProperties().getProperty("from");
    }

}