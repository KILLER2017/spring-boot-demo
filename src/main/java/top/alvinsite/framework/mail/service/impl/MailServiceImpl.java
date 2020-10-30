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
    public Mail sendMail(Mail mailVo) {
        try {
            checkMail(mailVo); //1.检测邮件
            sendMimeMail(mailVo); //2.发送邮件
            return saveMail(mailVo); //3.保存邮件
        } catch (Exception e) {
            mailVo.setStatus("fail");
            mailVo.setError(e.getMessage());
            return mailVo;
        }
    }

    //检测邮件信息类
    private void checkMail(Mail mailVo) {
        if (StringUtils.isEmpty(mailVo.getTo())) {
            throw new RuntimeException("邮件收信人不能为空");
        }
        if (StringUtils.isEmpty(mailVo.getSubject())) {
            throw new RuntimeException("邮件主题不能为空");
        }
        if (StringUtils.isEmpty(mailVo.getText())) {
            throw new RuntimeException("邮件内容不能为空");
        }
    }

    //构建复杂邮件信息类
    private void sendMimeMail(Mail mailVo) {
        try {
            // true表示支持复杂类型
            MimeMessageHelper messageHelper = new MimeMessageHelper(mailSender.createMimeMessage(), true);

            // 邮件发信人从配置项读取
            // mailVo.setFrom(getMailSendFrom());

            // 邮件发信人
            messageHelper.setFrom(mailVo.getFrom());

            // 邮件收信人
            messageHelper.setTo(mailVo.getTo().split(","));

            // 邮件主题
            messageHelper.setSubject(mailVo.getSubject());

            // 邮件内容
            messageHelper.setText(mailVo.getText());
            // 抄送
            if (!StringUtils.isEmpty(mailVo.getCc())) {
                messageHelper.setCc(mailVo.getCc().split(","));
            }
            // 密送
            if (!StringUtils.isEmpty(mailVo.getBcc())) {
                messageHelper.setCc(mailVo.getBcc().split(","));
            }
            if (mailVo.getMultipartFiles() != null) {//添加邮件附件
                for (MultipartFile multipartFile : mailVo.getMultipartFiles()) {
                    messageHelper.addAttachment(multipartFile.getOriginalFilename(), multipartFile);
                }
            }
            if (mailVo.getSentDate() == null) {//发送时间
                mailVo.setSentDate(new Date());
                messageHelper.setSentDate(mailVo.getSentDate());
            }
            mailSender.send(messageHelper.getMimeMessage());//正式发送邮件
            mailVo.setStatus("ok");
        } catch (Exception e) {
            throw new RuntimeException(e);//发送失败
        }
    }

    //保存邮件
    private Mail saveMail(Mail mailVo) {

        //将邮件保存到数据库..

        return mailVo;
    }

    //获取邮件发信人
    public String getMailSendFrom() {
        return mailSender.getJavaMailProperties().getProperty("from");
    }

}