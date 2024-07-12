package com.credmarg.admin.employeevendormanagement.service;

import com.credmarg.admin.employeevendormanagement.domain.mail.EmailDetails;
import com.credmarg.admin.employeevendormanagement.domain.mail.EmailFilterRequest;
import com.credmarg.admin.employeevendormanagement.domain.mail.SendMailDetails;
import com.credmarg.admin.employeevendormanagement.entity.Vendor;
import com.credmarg.admin.employeevendormanagement.exception.CredmargPortalException;
import io.micrometer.common.util.StringUtils;
import jakarta.mail.*;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import jakarta.mail.search.AndTerm;
import jakarta.mail.search.RecipientStringTerm;
import jakarta.mail.search.SearchTerm;
import jakarta.mail.search.SubjectTerm;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.IOException;
import java.util.*;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;
    private final Folder emailFolder;

    public EmailService(JavaMailSender javaMailSender, TemplateEngine templateEngine) throws MessagingException {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
        final JavaMailSenderImpl mailSenderDetails = (JavaMailSenderImpl) javaMailSender;

        Properties properties = new Properties();
        properties.put("mail.store.protocol", "imaps");
        properties.put("mail.imap.host", mailSenderDetails.getHost());
        properties.put("mail.imap.port", mailSenderDetails.getPort());
        properties.put("mail.imap.ssl.enable", "true");
        Session session = Session.getDefaultInstance(properties);
        Store store = session.getStore("imaps");
        store.connect(mailSenderDetails.getHost(), mailSenderDetails.getUsername(), mailSenderDetails.getPassword());
        this.emailFolder = store.getFolder("[Gmail]/Sent Mail");
        emailFolder.open(Folder.READ_ONLY);

    }

    public void sendPaymentMailToVendors(List<Vendor> vendors) throws CredmargPortalException {
        List<EmailDetails> emailDetailsList = vendors.stream().map(vendor -> {
            EmailDetails emailDetails = new EmailDetails();
            emailDetails.setSubject("Vendor Payment");
            emailDetails.setSendTo(vendor.getEmail().toLowerCase());
            emailDetails.setBody(generateMailHtml(vendor.getName(), vendor.getUpi()));
            return emailDetails;
        }).toList();
        this.sendSimpleMail(emailDetailsList);
    }


    public List<SendMailDetails> readSentEmails(EmailFilterRequest emailFilterRequest) throws CredmargPortalException {
        try {
            List<SearchTerm> searchTermList = new ArrayList<>();
            if (!CollectionUtils.isEmpty(emailFilterRequest.getSentTo())) {
                emailFilterRequest.getSentTo().stream().filter(StringUtils::isNotBlank)
                        .forEach(s -> searchTermList.add(new RecipientStringTerm(Message.RecipientType.TO, s.toLowerCase())));
            }
            if (!CollectionUtils.isEmpty(emailFilterRequest.getSubjects())) {
                emailFilterRequest.getSubjects().stream().filter(StringUtils::isNotBlank)
                        .forEach(s -> searchTermList.add(new SubjectTerm(s)));
            }
            searchTermList.add(new SubjectTerm("Vendor Payment"));

            Message[] messages = emailFolder.search(new AndTerm(searchTermList.toArray(new SearchTerm[0])));

            return Arrays.asList(messages).parallelStream().map(message -> {
                try {
                    return getTextFromMessage(message);
                } catch (MessagingException | IOException e) {
                    throw new RuntimeException(e);
                }
            }).toList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new CredmargPortalException("failed to readSentEmails");
        }

    }

    private SendMailDetails getTextFromMessage(Message message) throws MessagingException, IOException {
        SendMailDetails sendMailDetails = new SendMailDetails();
        sendMailDetails.setSentAt(message.getSentDate().toString());
        sendMailDetails.setSendTo(String.join(", ", message.getRecipients(Message.RecipientType.TO)[0].toString()));
        if (message.isMimeType("text/plain")) {
            sendMailDetails.setBody(message.getContent().toString());
        }
        if (message.isMimeType("multipart/*")) {
            if (message.getContent() instanceof MimeMultipart mimeMultipart) {
                sendMailDetails.setBody(getTextFromMimeMultipart(mimeMultipart));
            }
        }
        return sendMailDetails;
    }

    private String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws MessagingException, IOException {
        StringBuilder result = new StringBuilder();
        int count = mimeMultipart.getCount();
        for (int i = 0; i < count; i++) {
            BodyPart bodyPart = mimeMultipart.getBodyPart(i);
            if (bodyPart.isMimeType("text/plain")) {
                result.append(bodyPart.getContent());
            } else if (bodyPart.isMimeType("text/html")) {
                String html = (String) bodyPart.getContent();
                result.append(org.jsoup.Jsoup.parse(html).text());
            } else if (bodyPart.getContent() instanceof MimeMultipart) {
                result.append(getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent()));
            }
        }
        return result.toString();
    }

    private void sendSimpleMail(List<EmailDetails> emailDetailsList) throws CredmargPortalException {
        try {
            MimeMessage[] mimeMessageList = new MimeMessage[emailDetailsList.size()];
            for (int i = 0; i < emailDetailsList.size(); i++) {
                EmailDetails emailDetails = emailDetailsList.get(i);
                MimeMessage mimeMessage = javaMailSender.createMimeMessage();
                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                mimeMessageHelper.setFrom(Objects.requireNonNull(((JavaMailSenderImpl) javaMailSender).getUsername()));
                mimeMessageHelper.setTo(emailDetails.getSendTo());
                mimeMessageHelper.setSubject(emailDetails.getSubject());
                mimeMessageHelper.setText(emailDetails.getBody(), true);
                mimeMessageList[i] = mimeMessage;
            }
            javaMailSender.send(mimeMessageList);
        } catch (Exception e) {
            throw new CredmargPortalException("Unable to send email! Try again", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private String generateMailHtml(String name, String upi) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("name", name);
        variables.put("upi", upi);

        return this.templateEngine.process("vendor_payment_success", new Context(Locale.getDefault(), variables));
    }
}
