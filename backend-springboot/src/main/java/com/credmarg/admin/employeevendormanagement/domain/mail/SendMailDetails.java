package com.credmarg.admin.employeevendormanagement.domain.mail;


import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SendMailDetails extends EmailDetails {

    private String sentAt;

    public SendMailDetails() {
        super();
    }

    public String getSentAt() {
        return sentAt;
    }

    public void setSentAt(String sentAt) {
        this.sentAt = sentAt;
    }
}
