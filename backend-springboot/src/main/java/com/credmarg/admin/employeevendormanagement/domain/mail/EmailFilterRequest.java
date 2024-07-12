package com.credmarg.admin.employeevendormanagement.domain.mail;

import java.util.List;

public class EmailFilterRequest {

    private List<String> sentTo;

    private List<String> subjects;

    public EmailFilterRequest() {
        super();
    }

    public List<String> getSentTo() {
        return sentTo;
    }

    public void setSentTo(List<String> sentTo) {
        this.sentTo = sentTo;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }


}
