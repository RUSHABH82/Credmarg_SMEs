package com.credmarg.admin.employeevendormanagement.domain.mail;

public class EmailDetails {

    private String sendTo;
    private String subject;
    private String body;

    public EmailDetails() {
        super();
    }

    public String getSendTo() {
        return sendTo;
    }

    public void setSendTo(String sendTo) {
        this.sendTo = sendTo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
