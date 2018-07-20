package com.example.shanna.orbital2;

public class AllCollabsReq {

    private String Pay;
    private String Duration;
    private String BufferWait;
    private String MaxChanges;
    private String DateOfRequest;
    private String Partner; //ID
    private String SenderFullName;
    private String Title;

    public AllCollabsReq(){

    }


    public AllCollabsReq(String pay, String duration, String bufferWait,
                         String maxChanges, String dateOfRequest, String partner, String senderFullName, String title) {
        this.Pay = pay;
        this.Duration = duration;
        this.BufferWait = bufferWait;
        this.MaxChanges = maxChanges;
        this.DateOfRequest = dateOfRequest;
        this.Partner = partner;
        this.SenderFullName = senderFullName;
        this.Title = title;
    }

    public String getPay() {
        return Pay;
    }

    public void setPay(String pay) {
        Pay = pay;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    public String getBufferWait() {
        return BufferWait;
    }

    public void setBufferWait(String bufferWait) {
        BufferWait = bufferWait;
    }

    public String getMaxChanges() {
        return MaxChanges;
    }

    public void setMaxChanges(String maxChanges) {
        MaxChanges = maxChanges;
    }

    public String getDateOfRequest() {
        return DateOfRequest;
    }

    public void setDateOfRequest(String dateOfRequest) {
        DateOfRequest = dateOfRequest;
    }

    public String getPartner() {
        return Partner;
    }

    public void setPartner(String partner) {
        Partner = partner;
    }

    public String getSenderFullName() {
        return SenderFullName;
    }

    public void setSenderFullName(String senderFullName) {
        SenderFullName = senderFullName;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}