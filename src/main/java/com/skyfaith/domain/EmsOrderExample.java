package com.skyfaith.domain;

import java.util.ArrayList;
import java.util.List;

public class EmsOrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EmsOrderExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andEordernoIsNull() {
            addCriterion("EorderNo is null");
            return (Criteria) this;
        }

        public Criteria andEordernoIsNotNull() {
            addCriterion("EorderNo is not null");
            return (Criteria) this;
        }

        public Criteria andEordernoEqualTo(String value) {
            addCriterion("EorderNo =", value, "eorderno");
            return (Criteria) this;
        }

        public Criteria andEordernoNotEqualTo(String value) {
            addCriterion("EorderNo <>", value, "eorderno");
            return (Criteria) this;
        }

        public Criteria andEordernoGreaterThan(String value) {
            addCriterion("EorderNo >", value, "eorderno");
            return (Criteria) this;
        }

        public Criteria andEordernoGreaterThanOrEqualTo(String value) {
            addCriterion("EorderNo >=", value, "eorderno");
            return (Criteria) this;
        }

        public Criteria andEordernoLessThan(String value) {
            addCriterion("EorderNo <", value, "eorderno");
            return (Criteria) this;
        }

        public Criteria andEordernoLessThanOrEqualTo(String value) {
            addCriterion("EorderNo <=", value, "eorderno");
            return (Criteria) this;
        }

        public Criteria andEordernoLike(String value) {
            addCriterion("EorderNo like", value, "eorderno");
            return (Criteria) this;
        }

        public Criteria andEordernoNotLike(String value) {
            addCriterion("EorderNo not like", value, "eorderno");
            return (Criteria) this;
        }

        public Criteria andEordernoIn(List<String> values) {
            addCriterion("EorderNo in", values, "eorderno");
            return (Criteria) this;
        }

        public Criteria andEordernoNotIn(List<String> values) {
            addCriterion("EorderNo not in", values, "eorderno");
            return (Criteria) this;
        }

        public Criteria andEordernoBetween(String value1, String value2) {
            addCriterion("EorderNo between", value1, value2, "eorderno");
            return (Criteria) this;
        }

        public Criteria andEordernoNotBetween(String value1, String value2) {
            addCriterion("EorderNo not between", value1, value2, "eorderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoIsNull() {
            addCriterion("OrderNo is null");
            return (Criteria) this;
        }

        public Criteria andOrdernoIsNotNull() {
            addCriterion("OrderNo is not null");
            return (Criteria) this;
        }

        public Criteria andOrdernoEqualTo(String value) {
            addCriterion("OrderNo =", value, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoNotEqualTo(String value) {
            addCriterion("OrderNo <>", value, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoGreaterThan(String value) {
            addCriterion("OrderNo >", value, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoGreaterThanOrEqualTo(String value) {
            addCriterion("OrderNo >=", value, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoLessThan(String value) {
            addCriterion("OrderNo <", value, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoLessThanOrEqualTo(String value) {
            addCriterion("OrderNo <=", value, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoLike(String value) {
            addCriterion("OrderNo like", value, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoNotLike(String value) {
            addCriterion("OrderNo not like", value, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoIn(List<String> values) {
            addCriterion("OrderNo in", values, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoNotIn(List<String> values) {
            addCriterion("OrderNo not in", values, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoBetween(String value1, String value2) {
            addCriterion("OrderNo between", value1, value2, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoNotBetween(String value1, String value2) {
            addCriterion("OrderNo not between", value1, value2, "orderno");
            return (Criteria) this;
        }

        public Criteria andSenderIsNull() {
            addCriterion("Sender is null");
            return (Criteria) this;
        }

        public Criteria andSenderIsNotNull() {
            addCriterion("Sender is not null");
            return (Criteria) this;
        }

        public Criteria andSenderEqualTo(String value) {
            addCriterion("Sender =", value, "sender");
            return (Criteria) this;
        }

        public Criteria andSenderNotEqualTo(String value) {
            addCriterion("Sender <>", value, "sender");
            return (Criteria) this;
        }

        public Criteria andSenderGreaterThan(String value) {
            addCriterion("Sender >", value, "sender");
            return (Criteria) this;
        }

        public Criteria andSenderGreaterThanOrEqualTo(String value) {
            addCriterion("Sender >=", value, "sender");
            return (Criteria) this;
        }

        public Criteria andSenderLessThan(String value) {
            addCriterion("Sender <", value, "sender");
            return (Criteria) this;
        }

        public Criteria andSenderLessThanOrEqualTo(String value) {
            addCriterion("Sender <=", value, "sender");
            return (Criteria) this;
        }

        public Criteria andSenderLike(String value) {
            addCriterion("Sender like", value, "sender");
            return (Criteria) this;
        }

        public Criteria andSenderNotLike(String value) {
            addCriterion("Sender not like", value, "sender");
            return (Criteria) this;
        }

        public Criteria andSenderIn(List<String> values) {
            addCriterion("Sender in", values, "sender");
            return (Criteria) this;
        }

        public Criteria andSenderNotIn(List<String> values) {
            addCriterion("Sender not in", values, "sender");
            return (Criteria) this;
        }

        public Criteria andSenderBetween(String value1, String value2) {
            addCriterion("Sender between", value1, value2, "sender");
            return (Criteria) this;
        }

        public Criteria andSenderNotBetween(String value1, String value2) {
            addCriterion("Sender not between", value1, value2, "sender");
            return (Criteria) this;
        }

        public Criteria andSenderphoneIsNull() {
            addCriterion("SenderPhone is null");
            return (Criteria) this;
        }

        public Criteria andSenderphoneIsNotNull() {
            addCriterion("SenderPhone is not null");
            return (Criteria) this;
        }

        public Criteria andSenderphoneEqualTo(String value) {
            addCriterion("SenderPhone =", value, "senderphone");
            return (Criteria) this;
        }

        public Criteria andSenderphoneNotEqualTo(String value) {
            addCriterion("SenderPhone <>", value, "senderphone");
            return (Criteria) this;
        }

        public Criteria andSenderphoneGreaterThan(String value) {
            addCriterion("SenderPhone >", value, "senderphone");
            return (Criteria) this;
        }

        public Criteria andSenderphoneGreaterThanOrEqualTo(String value) {
            addCriterion("SenderPhone >=", value, "senderphone");
            return (Criteria) this;
        }

        public Criteria andSenderphoneLessThan(String value) {
            addCriterion("SenderPhone <", value, "senderphone");
            return (Criteria) this;
        }

        public Criteria andSenderphoneLessThanOrEqualTo(String value) {
            addCriterion("SenderPhone <=", value, "senderphone");
            return (Criteria) this;
        }

        public Criteria andSenderphoneLike(String value) {
            addCriterion("SenderPhone like", value, "senderphone");
            return (Criteria) this;
        }

        public Criteria andSenderphoneNotLike(String value) {
            addCriterion("SenderPhone not like", value, "senderphone");
            return (Criteria) this;
        }

        public Criteria andSenderphoneIn(List<String> values) {
            addCriterion("SenderPhone in", values, "senderphone");
            return (Criteria) this;
        }

        public Criteria andSenderphoneNotIn(List<String> values) {
            addCriterion("SenderPhone not in", values, "senderphone");
            return (Criteria) this;
        }

        public Criteria andSenderphoneBetween(String value1, String value2) {
            addCriterion("SenderPhone between", value1, value2, "senderphone");
            return (Criteria) this;
        }

        public Criteria andSenderphoneNotBetween(String value1, String value2) {
            addCriterion("SenderPhone not between", value1, value2, "senderphone");
            return (Criteria) this;
        }

        public Criteria andSenderaddressIsNull() {
            addCriterion("SenderAddress is null");
            return (Criteria) this;
        }

        public Criteria andSenderaddressIsNotNull() {
            addCriterion("SenderAddress is not null");
            return (Criteria) this;
        }

        public Criteria andSenderaddressEqualTo(String value) {
            addCriterion("SenderAddress =", value, "senderaddress");
            return (Criteria) this;
        }

        public Criteria andSenderaddressNotEqualTo(String value) {
            addCriterion("SenderAddress <>", value, "senderaddress");
            return (Criteria) this;
        }

        public Criteria andSenderaddressGreaterThan(String value) {
            addCriterion("SenderAddress >", value, "senderaddress");
            return (Criteria) this;
        }

        public Criteria andSenderaddressGreaterThanOrEqualTo(String value) {
            addCriterion("SenderAddress >=", value, "senderaddress");
            return (Criteria) this;
        }

        public Criteria andSenderaddressLessThan(String value) {
            addCriterion("SenderAddress <", value, "senderaddress");
            return (Criteria) this;
        }

        public Criteria andSenderaddressLessThanOrEqualTo(String value) {
            addCriterion("SenderAddress <=", value, "senderaddress");
            return (Criteria) this;
        }

        public Criteria andSenderaddressLike(String value) {
            addCriterion("SenderAddress like", value, "senderaddress");
            return (Criteria) this;
        }

        public Criteria andSenderaddressNotLike(String value) {
            addCriterion("SenderAddress not like", value, "senderaddress");
            return (Criteria) this;
        }

        public Criteria andSenderaddressIn(List<String> values) {
            addCriterion("SenderAddress in", values, "senderaddress");
            return (Criteria) this;
        }

        public Criteria andSenderaddressNotIn(List<String> values) {
            addCriterion("SenderAddress not in", values, "senderaddress");
            return (Criteria) this;
        }

        public Criteria andSenderaddressBetween(String value1, String value2) {
            addCriterion("SenderAddress between", value1, value2, "senderaddress");
            return (Criteria) this;
        }

        public Criteria andSenderaddressNotBetween(String value1, String value2) {
            addCriterion("SenderAddress not between", value1, value2, "senderaddress");
            return (Criteria) this;
        }

        public Criteria andReceiverIsNull() {
            addCriterion("Receiver is null");
            return (Criteria) this;
        }

        public Criteria andReceiverIsNotNull() {
            addCriterion("Receiver is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverEqualTo(String value) {
            addCriterion("Receiver =", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverNotEqualTo(String value) {
            addCriterion("Receiver <>", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverGreaterThan(String value) {
            addCriterion("Receiver >", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverGreaterThanOrEqualTo(String value) {
            addCriterion("Receiver >=", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverLessThan(String value) {
            addCriterion("Receiver <", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverLessThanOrEqualTo(String value) {
            addCriterion("Receiver <=", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverLike(String value) {
            addCriterion("Receiver like", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverNotLike(String value) {
            addCriterion("Receiver not like", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverIn(List<String> values) {
            addCriterion("Receiver in", values, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverNotIn(List<String> values) {
            addCriterion("Receiver not in", values, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverBetween(String value1, String value2) {
            addCriterion("Receiver between", value1, value2, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverNotBetween(String value1, String value2) {
            addCriterion("Receiver not between", value1, value2, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverphoneIsNull() {
            addCriterion("ReceiverPhone is null");
            return (Criteria) this;
        }

        public Criteria andReceiverphoneIsNotNull() {
            addCriterion("ReceiverPhone is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverphoneEqualTo(String value) {
            addCriterion("ReceiverPhone =", value, "receiverphone");
            return (Criteria) this;
        }

        public Criteria andReceiverphoneNotEqualTo(String value) {
            addCriterion("ReceiverPhone <>", value, "receiverphone");
            return (Criteria) this;
        }

        public Criteria andReceiverphoneGreaterThan(String value) {
            addCriterion("ReceiverPhone >", value, "receiverphone");
            return (Criteria) this;
        }

        public Criteria andReceiverphoneGreaterThanOrEqualTo(String value) {
            addCriterion("ReceiverPhone >=", value, "receiverphone");
            return (Criteria) this;
        }

        public Criteria andReceiverphoneLessThan(String value) {
            addCriterion("ReceiverPhone <", value, "receiverphone");
            return (Criteria) this;
        }

        public Criteria andReceiverphoneLessThanOrEqualTo(String value) {
            addCriterion("ReceiverPhone <=", value, "receiverphone");
            return (Criteria) this;
        }

        public Criteria andReceiverphoneLike(String value) {
            addCriterion("ReceiverPhone like", value, "receiverphone");
            return (Criteria) this;
        }

        public Criteria andReceiverphoneNotLike(String value) {
            addCriterion("ReceiverPhone not like", value, "receiverphone");
            return (Criteria) this;
        }

        public Criteria andReceiverphoneIn(List<String> values) {
            addCriterion("ReceiverPhone in", values, "receiverphone");
            return (Criteria) this;
        }

        public Criteria andReceiverphoneNotIn(List<String> values) {
            addCriterion("ReceiverPhone not in", values, "receiverphone");
            return (Criteria) this;
        }

        public Criteria andReceiverphoneBetween(String value1, String value2) {
            addCriterion("ReceiverPhone between", value1, value2, "receiverphone");
            return (Criteria) this;
        }

        public Criteria andReceiverphoneNotBetween(String value1, String value2) {
            addCriterion("ReceiverPhone not between", value1, value2, "receiverphone");
            return (Criteria) this;
        }

        public Criteria andReceiveraddressIsNull() {
            addCriterion("ReceiverAddress is null");
            return (Criteria) this;
        }

        public Criteria andReceiveraddressIsNotNull() {
            addCriterion("ReceiverAddress is not null");
            return (Criteria) this;
        }

        public Criteria andReceiveraddressEqualTo(String value) {
            addCriterion("ReceiverAddress =", value, "receiveraddress");
            return (Criteria) this;
        }

        public Criteria andReceiveraddressNotEqualTo(String value) {
            addCriterion("ReceiverAddress <>", value, "receiveraddress");
            return (Criteria) this;
        }

        public Criteria andReceiveraddressGreaterThan(String value) {
            addCriterion("ReceiverAddress >", value, "receiveraddress");
            return (Criteria) this;
        }

        public Criteria andReceiveraddressGreaterThanOrEqualTo(String value) {
            addCriterion("ReceiverAddress >=", value, "receiveraddress");
            return (Criteria) this;
        }

        public Criteria andReceiveraddressLessThan(String value) {
            addCriterion("ReceiverAddress <", value, "receiveraddress");
            return (Criteria) this;
        }

        public Criteria andReceiveraddressLessThanOrEqualTo(String value) {
            addCriterion("ReceiverAddress <=", value, "receiveraddress");
            return (Criteria) this;
        }

        public Criteria andReceiveraddressLike(String value) {
            addCriterion("ReceiverAddress like", value, "receiveraddress");
            return (Criteria) this;
        }

        public Criteria andReceiveraddressNotLike(String value) {
            addCriterion("ReceiverAddress not like", value, "receiveraddress");
            return (Criteria) this;
        }

        public Criteria andReceiveraddressIn(List<String> values) {
            addCriterion("ReceiverAddress in", values, "receiveraddress");
            return (Criteria) this;
        }

        public Criteria andReceiveraddressNotIn(List<String> values) {
            addCriterion("ReceiverAddress not in", values, "receiveraddress");
            return (Criteria) this;
        }

        public Criteria andReceiveraddressBetween(String value1, String value2) {
            addCriterion("ReceiverAddress between", value1, value2, "receiveraddress");
            return (Criteria) this;
        }

        public Criteria andReceiveraddressNotBetween(String value1, String value2) {
            addCriterion("ReceiverAddress not between", value1, value2, "receiveraddress");
            return (Criteria) this;
        }

        public Criteria andBrandnameIsNull() {
            addCriterion("BrandName is null");
            return (Criteria) this;
        }

        public Criteria andBrandnameIsNotNull() {
            addCriterion("BrandName is not null");
            return (Criteria) this;
        }

        public Criteria andBrandnameEqualTo(String value) {
            addCriterion("BrandName =", value, "brandname");
            return (Criteria) this;
        }

        public Criteria andBrandnameNotEqualTo(String value) {
            addCriterion("BrandName <>", value, "brandname");
            return (Criteria) this;
        }

        public Criteria andBrandnameGreaterThan(String value) {
            addCriterion("BrandName >", value, "brandname");
            return (Criteria) this;
        }

        public Criteria andBrandnameGreaterThanOrEqualTo(String value) {
            addCriterion("BrandName >=", value, "brandname");
            return (Criteria) this;
        }

        public Criteria andBrandnameLessThan(String value) {
            addCriterion("BrandName <", value, "brandname");
            return (Criteria) this;
        }

        public Criteria andBrandnameLessThanOrEqualTo(String value) {
            addCriterion("BrandName <=", value, "brandname");
            return (Criteria) this;
        }

        public Criteria andBrandnameLike(String value) {
            addCriterion("BrandName like", value, "brandname");
            return (Criteria) this;
        }

        public Criteria andBrandnameNotLike(String value) {
            addCriterion("BrandName not like", value, "brandname");
            return (Criteria) this;
        }

        public Criteria andBrandnameIn(List<String> values) {
            addCriterion("BrandName in", values, "brandname");
            return (Criteria) this;
        }

        public Criteria andBrandnameNotIn(List<String> values) {
            addCriterion("BrandName not in", values, "brandname");
            return (Criteria) this;
        }

        public Criteria andBrandnameBetween(String value1, String value2) {
            addCriterion("BrandName between", value1, value2, "brandname");
            return (Criteria) this;
        }

        public Criteria andBrandnameNotBetween(String value1, String value2) {
            addCriterion("BrandName not between", value1, value2, "brandname");
            return (Criteria) this;
        }

        public Criteria andWeightIsNull() {
            addCriterion("Weight is null");
            return (Criteria) this;
        }

        public Criteria andWeightIsNotNull() {
            addCriterion("Weight is not null");
            return (Criteria) this;
        }

        public Criteria andWeightEqualTo(String value) {
            addCriterion("Weight =", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotEqualTo(String value) {
            addCriterion("Weight <>", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightGreaterThan(String value) {
            addCriterion("Weight >", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightGreaterThanOrEqualTo(String value) {
            addCriterion("Weight >=", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightLessThan(String value) {
            addCriterion("Weight <", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightLessThanOrEqualTo(String value) {
            addCriterion("Weight <=", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightLike(String value) {
            addCriterion("Weight like", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotLike(String value) {
            addCriterion("Weight not like", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightIn(List<String> values) {
            addCriterion("Weight in", values, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotIn(List<String> values) {
            addCriterion("Weight not in", values, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightBetween(String value1, String value2) {
            addCriterion("Weight between", value1, value2, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotBetween(String value1, String value2) {
            addCriterion("Weight not between", value1, value2, "weight");
            return (Criteria) this;
        }

        public Criteria andDeclaredvalueIsNull() {
            addCriterion("DeclaredValue is null");
            return (Criteria) this;
        }

        public Criteria andDeclaredvalueIsNotNull() {
            addCriterion("DeclaredValue is not null");
            return (Criteria) this;
        }

        public Criteria andDeclaredvalueEqualTo(Double value) {
            addCriterion("DeclaredValue =", value, "declaredvalue");
            return (Criteria) this;
        }

        public Criteria andDeclaredvalueNotEqualTo(Double value) {
            addCriterion("DeclaredValue <>", value, "declaredvalue");
            return (Criteria) this;
        }

        public Criteria andDeclaredvalueGreaterThan(Double value) {
            addCriterion("DeclaredValue >", value, "declaredvalue");
            return (Criteria) this;
        }

        public Criteria andDeclaredvalueGreaterThanOrEqualTo(Double value) {
            addCriterion("DeclaredValue >=", value, "declaredvalue");
            return (Criteria) this;
        }

        public Criteria andDeclaredvalueLessThan(Double value) {
            addCriterion("DeclaredValue <", value, "declaredvalue");
            return (Criteria) this;
        }

        public Criteria andDeclaredvalueLessThanOrEqualTo(Double value) {
            addCriterion("DeclaredValue <=", value, "declaredvalue");
            return (Criteria) this;
        }

        public Criteria andDeclaredvalueIn(List<Double> values) {
            addCriterion("DeclaredValue in", values, "declaredvalue");
            return (Criteria) this;
        }

        public Criteria andDeclaredvalueNotIn(List<Double> values) {
            addCriterion("DeclaredValue not in", values, "declaredvalue");
            return (Criteria) this;
        }

        public Criteria andDeclaredvalueBetween(Double value1, Double value2) {
            addCriterion("DeclaredValue between", value1, value2, "declaredvalue");
            return (Criteria) this;
        }

        public Criteria andDeclaredvalueNotBetween(Double value1, Double value2) {
            addCriterion("DeclaredValue not between", value1, value2, "declaredvalue");
            return (Criteria) this;
        }

        public Criteria andPosttalcodeIsNull() {
            addCriterion("PosttalCode is null");
            return (Criteria) this;
        }

        public Criteria andPosttalcodeIsNotNull() {
            addCriterion("PosttalCode is not null");
            return (Criteria) this;
        }

        public Criteria andPosttalcodeEqualTo(String value) {
            addCriterion("PosttalCode =", value, "posttalcode");
            return (Criteria) this;
        }

        public Criteria andPosttalcodeNotEqualTo(String value) {
            addCriterion("PosttalCode <>", value, "posttalcode");
            return (Criteria) this;
        }

        public Criteria andPosttalcodeGreaterThan(String value) {
            addCriterion("PosttalCode >", value, "posttalcode");
            return (Criteria) this;
        }

        public Criteria andPosttalcodeGreaterThanOrEqualTo(String value) {
            addCriterion("PosttalCode >=", value, "posttalcode");
            return (Criteria) this;
        }

        public Criteria andPosttalcodeLessThan(String value) {
            addCriterion("PosttalCode <", value, "posttalcode");
            return (Criteria) this;
        }

        public Criteria andPosttalcodeLessThanOrEqualTo(String value) {
            addCriterion("PosttalCode <=", value, "posttalcode");
            return (Criteria) this;
        }

        public Criteria andPosttalcodeLike(String value) {
            addCriterion("PosttalCode like", value, "posttalcode");
            return (Criteria) this;
        }

        public Criteria andPosttalcodeNotLike(String value) {
            addCriterion("PosttalCode not like", value, "posttalcode");
            return (Criteria) this;
        }

        public Criteria andPosttalcodeIn(List<String> values) {
            addCriterion("PosttalCode in", values, "posttalcode");
            return (Criteria) this;
        }

        public Criteria andPosttalcodeNotIn(List<String> values) {
            addCriterion("PosttalCode not in", values, "posttalcode");
            return (Criteria) this;
        }

        public Criteria andPosttalcodeBetween(String value1, String value2) {
            addCriterion("PosttalCode between", value1, value2, "posttalcode");
            return (Criteria) this;
        }

        public Criteria andPosttalcodeNotBetween(String value1, String value2) {
            addCriterion("PosttalCode not between", value1, value2, "posttalcode");
            return (Criteria) this;
        }

        public Criteria andClearanceportIsNull() {
            addCriterion("ClearancePort is null");
            return (Criteria) this;
        }

        public Criteria andClearanceportIsNotNull() {
            addCriterion("ClearancePort is not null");
            return (Criteria) this;
        }

        public Criteria andClearanceportEqualTo(String value) {
            addCriterion("ClearancePort =", value, "clearanceport");
            return (Criteria) this;
        }

        public Criteria andClearanceportNotEqualTo(String value) {
            addCriterion("ClearancePort <>", value, "clearanceport");
            return (Criteria) this;
        }

        public Criteria andClearanceportGreaterThan(String value) {
            addCriterion("ClearancePort >", value, "clearanceport");
            return (Criteria) this;
        }

        public Criteria andClearanceportGreaterThanOrEqualTo(String value) {
            addCriterion("ClearancePort >=", value, "clearanceport");
            return (Criteria) this;
        }

        public Criteria andClearanceportLessThan(String value) {
            addCriterion("ClearancePort <", value, "clearanceport");
            return (Criteria) this;
        }

        public Criteria andClearanceportLessThanOrEqualTo(String value) {
            addCriterion("ClearancePort <=", value, "clearanceport");
            return (Criteria) this;
        }

        public Criteria andClearanceportLike(String value) {
            addCriterion("ClearancePort like", value, "clearanceport");
            return (Criteria) this;
        }

        public Criteria andClearanceportNotLike(String value) {
            addCriterion("ClearancePort not like", value, "clearanceport");
            return (Criteria) this;
        }

        public Criteria andClearanceportIn(List<String> values) {
            addCriterion("ClearancePort in", values, "clearanceport");
            return (Criteria) this;
        }

        public Criteria andClearanceportNotIn(List<String> values) {
            addCriterion("ClearancePort not in", values, "clearanceport");
            return (Criteria) this;
        }

        public Criteria andClearanceportBetween(String value1, String value2) {
            addCriterion("ClearancePort between", value1, value2, "clearanceport");
            return (Criteria) this;
        }

        public Criteria andClearanceportNotBetween(String value1, String value2) {
            addCriterion("ClearancePort not between", value1, value2, "clearanceport");
            return (Criteria) this;
        }

        public Criteria andSenderlandIsNull() {
            addCriterion("SenderLand is null");
            return (Criteria) this;
        }

        public Criteria andSenderlandIsNotNull() {
            addCriterion("SenderLand is not null");
            return (Criteria) this;
        }

        public Criteria andSenderlandEqualTo(String value) {
            addCriterion("SenderLand =", value, "senderland");
            return (Criteria) this;
        }

        public Criteria andSenderlandNotEqualTo(String value) {
            addCriterion("SenderLand <>", value, "senderland");
            return (Criteria) this;
        }

        public Criteria andSenderlandGreaterThan(String value) {
            addCriterion("SenderLand >", value, "senderland");
            return (Criteria) this;
        }

        public Criteria andSenderlandGreaterThanOrEqualTo(String value) {
            addCriterion("SenderLand >=", value, "senderland");
            return (Criteria) this;
        }

        public Criteria andSenderlandLessThan(String value) {
            addCriterion("SenderLand <", value, "senderland");
            return (Criteria) this;
        }

        public Criteria andSenderlandLessThanOrEqualTo(String value) {
            addCriterion("SenderLand <=", value, "senderland");
            return (Criteria) this;
        }

        public Criteria andSenderlandLike(String value) {
            addCriterion("SenderLand like", value, "senderland");
            return (Criteria) this;
        }

        public Criteria andSenderlandNotLike(String value) {
            addCriterion("SenderLand not like", value, "senderland");
            return (Criteria) this;
        }

        public Criteria andSenderlandIn(List<String> values) {
            addCriterion("SenderLand in", values, "senderland");
            return (Criteria) this;
        }

        public Criteria andSenderlandNotIn(List<String> values) {
            addCriterion("SenderLand not in", values, "senderland");
            return (Criteria) this;
        }

        public Criteria andSenderlandBetween(String value1, String value2) {
            addCriterion("SenderLand between", value1, value2, "senderland");
            return (Criteria) this;
        }

        public Criteria andSenderlandNotBetween(String value1, String value2) {
            addCriterion("SenderLand not between", value1, value2, "senderland");
            return (Criteria) this;
        }

        public Criteria andPrintdateIsNull() {
            addCriterion("PrintDate is null");
            return (Criteria) this;
        }

        public Criteria andPrintdateIsNotNull() {
            addCriterion("PrintDate is not null");
            return (Criteria) this;
        }

        public Criteria andPrintdateEqualTo(String value) {
            addCriterion("PrintDate =", value, "printdate");
            return (Criteria) this;
        }

        public Criteria andPrintdateNotEqualTo(String value) {
            addCriterion("PrintDate <>", value, "printdate");
            return (Criteria) this;
        }

        public Criteria andPrintdateGreaterThan(String value) {
            addCriterion("PrintDate >", value, "printdate");
            return (Criteria) this;
        }

        public Criteria andPrintdateGreaterThanOrEqualTo(String value) {
            addCriterion("PrintDate >=", value, "printdate");
            return (Criteria) this;
        }

        public Criteria andPrintdateLessThan(String value) {
            addCriterion("PrintDate <", value, "printdate");
            return (Criteria) this;
        }

        public Criteria andPrintdateLessThanOrEqualTo(String value) {
            addCriterion("PrintDate <=", value, "printdate");
            return (Criteria) this;
        }

        public Criteria andPrintdateLike(String value) {
            addCriterion("PrintDate like", value, "printdate");
            return (Criteria) this;
        }

        public Criteria andPrintdateNotLike(String value) {
            addCriterion("PrintDate not like", value, "printdate");
            return (Criteria) this;
        }

        public Criteria andPrintdateIn(List<String> values) {
            addCriterion("PrintDate in", values, "printdate");
            return (Criteria) this;
        }

        public Criteria andPrintdateNotIn(List<String> values) {
            addCriterion("PrintDate not in", values, "printdate");
            return (Criteria) this;
        }

        public Criteria andPrintdateBetween(String value1, String value2) {
            addCriterion("PrintDate between", value1, value2, "printdate");
            return (Criteria) this;
        }

        public Criteria andPrintdateNotBetween(String value1, String value2) {
            addCriterion("PrintDate not between", value1, value2, "printdate");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}