package com.kaishengit.tms.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class Customer implements Serializable {
    private Long id;

    private String customerName;

    /**
     * 身份证号
     */
    private String customerIdCard;

    /**
     * 身份证正面照片
     */
    private String customerIdCardPhoto;

    /**
     * 身份证反面照片
     */
    private String customerIdCardPhotoBack;

    private Date createTime;

    private Date updateTime;

    /**
     * 一寸照片
     */
    private String customerPhoto;

    private String customerTel;

    private String cutomerAddress;

    private Integer ticketId;

    private Integer customerAge;

    /**
     * 性别
     */
    private String customerGender;


    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerIdCard() {
        return customerIdCard;
    }

    public void setCustomerIdCard(String customerIdCard) {
        this.customerIdCard = customerIdCard;
    }

    public String getCustomerIdCardPhoto() {
        return customerIdCardPhoto;
    }

    public void setCustomerIdCardPhoto(String customerIdCardPhoto) {
        this.customerIdCardPhoto = customerIdCardPhoto;
    }

    public String getCustomerIdCardPhotoBack() {
        return customerIdCardPhotoBack;
    }

    public void setCustomerIdCardPhotoBack(String customerIdCardPhotoBack) {
        this.customerIdCardPhotoBack = customerIdCardPhotoBack;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCustomerPhoto() {
        return customerPhoto;
    }

    public void setCustomerPhoto(String customerPhoto) {
        this.customerPhoto = customerPhoto;
    }

    public String getCustomerTel() {
        return customerTel;
    }

    public void setCustomerTel(String customerTel) {
        this.customerTel = customerTel;
    }

    public String getCutomerAddress() {
        return cutomerAddress;
    }

    public void setCutomerAddress(String cutomerAddress) {
        this.cutomerAddress = cutomerAddress;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public Integer getCustomerAge() {
        return customerAge;
    }

    public void setCustomerAge(Integer customerAge) {
        this.customerAge = customerAge;
    }

    public String getCustomerGender() {
        return customerGender;
    }

    public void setCustomerGender(String customerGender) {
        this.customerGender = customerGender;
    }
}