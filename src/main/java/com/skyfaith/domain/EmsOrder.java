package com.skyfaith.domain;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.math.BigDecimal;
import java.util.Date;

public class EmsOrder {
    private StringProperty eorderno;

    private StringProperty orderno;

    private StringProperty sender;

    private StringProperty senderphone;

    private StringProperty senderaddress;

    private StringProperty receiver;

    private StringProperty receiverphone;

    private StringProperty receiveraddress;

    private StringProperty brandname;

    private StringProperty weight;

    private BigDecimal declaredvalue;

    private StringProperty posttalcode;

    private StringProperty clearanceport;

    private StringProperty senderland;

    private String printdate;

    public EmsOrder(){
        this.eorderno = new SimpleStringProperty();
        this.orderno = new SimpleStringProperty();
        this.sender = new SimpleStringProperty();
        this.senderphone = new SimpleStringProperty();
        this.senderaddress = new SimpleStringProperty();
        this.receiver = new SimpleStringProperty();
        this.receiverphone = new SimpleStringProperty();
        this.receiveraddress = new SimpleStringProperty();
        this.brandname = new SimpleStringProperty();
        this.weight = new SimpleStringProperty();
        this.posttalcode = new SimpleStringProperty();
        this.clearanceport = new SimpleStringProperty();
        this.senderland = new SimpleStringProperty();
    }


    public StringProperty ordernoProperty() {
        return orderno;
    }

    public String getEorderno() {
        return eorderno.get();
    }

    public void setEorderno(String eorderno) {
        this.eorderno.set(eorderno);
    }

    public StringProperty eordernoProperty() {
        return eorderno;
    }

    public String getOrderno() {
        return orderno.get();
    }

    public void setOrderno(String orderno) {
        this.orderno.set(orderno);
    }

    public StringProperty senderProperty() {
        return sender;
    }

    public String getSender() {
        return sender.get();
    }

    public void setSender(String sender) {
        this.sender.set(sender);
    }

    public StringProperty senderphoneProperty() {
        return senderphone;
    }

    public String getSenderphone() {
        return senderphone.get();
    }

    public void setSenderphone(String senderphone) {
        this.senderphone.set(senderphone);
    }

    public StringProperty senderAddressProperty() {
        return senderaddress;
    }

    public String getSenderaddress() {
        return senderaddress.get();
    }

    public void setSenderaddress(String senderaddress) {
        this.senderaddress.set(senderaddress);
    }

    public StringProperty receiverProperty() {
        return receiver;
    }

    public String getReceiver() {
        return receiver.get();
    }

    public void setReceiver(String receiver) {
        this.receiver.set(receiver);
    }

    public StringProperty receiverphoneProperty() {
        return receiverphone;
    }

    public String getReceiverphone() {
        return receiverphone.get();
    }

    public void setReceiverphone(String receiverphone) {
        this.receiverphone.set(receiverphone);
    }

    public StringProperty receiveraddressProperty() {
        return receiveraddress;
    }

    public String getReceiveraddress() {
        return receiveraddress.get();
    }

    public void setReceiveraddress(String receiveraddress) {
        this.receiveraddress.set(receiveraddress);
    }

    public StringProperty brandnameProperty() {
        return brandname;
    }

    public String getBrandname() {
        return brandname.get();
    }

    public void setBrandname(String brandname) {
        this.brandname.set(brandname);
    }

    public StringProperty weightProperty() {
        return weight;
    }

    public String getWeight() {
        return weight.get();
    }

    public void setWeight(String weight) {
        this.weight.set(weight);
    }

    public BigDecimal getDeclaredvalue() {
        return declaredvalue;
    }

    public void setDeclaredvalue(BigDecimal declaredvalue) {
        this.declaredvalue = declaredvalue;
    }

    public StringProperty posttalcodeProperty() {
        return posttalcode;
    }

    public String getPosttalcode() {
        return posttalcode.get();
    }

    public void setPosttalcode(String posttalcode) {
        this.posttalcode.set(posttalcode);
    }

    public StringProperty clearanceportProperty() {
        return clearanceport;
    }

    public String getClearanceport() {
        return clearanceport.get();
    }

    public void setClearanceport(String clearanceport) {
        this.clearanceport.set(clearanceport);
    }

    public StringProperty senderlandProperty() {
        return senderland;
    }

    public String getSenderland() {
        return senderland.get();
    }

    public void setSenderland(String senderland) {
        this.senderland.set(senderland);
    }
    public String getPrintdate() {
        return printdate;
    }

    public void setPrintdate(String printdate) {
        this.printdate = printdate;
    }
}