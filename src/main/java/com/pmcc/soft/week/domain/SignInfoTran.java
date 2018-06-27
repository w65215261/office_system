package com.pmcc.soft.week.domain;

/**
 * Created by sunyake on 15/10/30.
 */
public class SignInfoTran {
    private String id;
    private String signPersonId;
    private String signPersonName;
    private String signAddress;
    private Double x;
    private Double y;
    private String signState;//0未签到,1已签到


    public String getSignPersonName() {
        return signPersonName;
    }

    public void setSignPersonName(String signPersonName) {
        this.signPersonName = signPersonName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSignPersonId() {
        return signPersonId;
    }

    public void setSignPersonId(String signPersonId) {
        this.signPersonId = signPersonId;
    }

    public String getSignAddress() {
        return signAddress;
    }

    public void setSignAddress(String signAddress) {
        this.signAddress = signAddress;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public String getSignState() {
        return signState;
    }

    public void setSignState(String signState) {
        this.signState = signState;
    }
}
