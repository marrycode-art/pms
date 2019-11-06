package junit.test;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;

public class EmailTest {

    @Test
    public void EmailTest01() throws Exception{
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans-email.xml");
        JavaMailSenderImpl bean = context.getBean(JavaMailSenderImpl.class);

        MimeMessage mimeMessage = bean.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

        helper.setFrom("17805593640@163.com");
        //helper.setTo("2477079238@qq.com");
        helper.setTo("marrycode01@gmail.com");
        helper.setSubject("test");
        helper.setText("这是一封测试邮件");

        bean.send(mimeMessage);
        System.err.println("send email success");
    }
}
