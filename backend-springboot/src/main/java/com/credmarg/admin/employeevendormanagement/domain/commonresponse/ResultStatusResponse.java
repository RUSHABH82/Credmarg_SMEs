package com.credmarg.admin.employeevendormanagement.domain.commonresponse;

public class ResultStatusResponse {
    private ResultStatus resultStatus;

    public ResultStatusResponse(ResultStatus resultStatus) {
        this.resultStatus = resultStatus;
    }

    public ResultStatus getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(ResultStatus resultStatus) {
        this.resultStatus = resultStatus;
    }


}
