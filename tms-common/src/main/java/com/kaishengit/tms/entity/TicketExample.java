package com.kaishengit.tms.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TicketExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TicketExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andTicketNumIsNull() {
            addCriterion("ticket_num is null");
            return (Criteria) this;
        }

        public Criteria andTicketNumIsNotNull() {
            addCriterion("ticket_num is not null");
            return (Criteria) this;
        }

        public Criteria andTicketNumEqualTo(Integer value) {
            addCriterion("ticket_num =", value, "ticketNum");
            return (Criteria) this;
        }

        public Criteria andTicketNumNotEqualTo(Integer value) {
            addCriterion("ticket_num <>", value, "ticketNum");
            return (Criteria) this;
        }

        public Criteria andTicketNumGreaterThan(Integer value) {
            addCriterion("ticket_num >", value, "ticketNum");
            return (Criteria) this;
        }

        public Criteria andTicketNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("ticket_num >=", value, "ticketNum");
            return (Criteria) this;
        }

        public Criteria andTicketNumLessThan(Integer value) {
            addCriterion("ticket_num <", value, "ticketNum");
            return (Criteria) this;
        }

        public Criteria andTicketNumLessThanOrEqualTo(Integer value) {
            addCriterion("ticket_num <=", value, "ticketNum");
            return (Criteria) this;
        }

        public Criteria andTicketNumIn(List<Integer> values) {
            addCriterion("ticket_num in", values, "ticketNum");
            return (Criteria) this;
        }

        public Criteria andTicketNumNotIn(List<Integer> values) {
            addCriterion("ticket_num not in", values, "ticketNum");
            return (Criteria) this;
        }

        public Criteria andTicketNumBetween(Integer value1, Integer value2) {
            addCriterion("ticket_num between", value1, value2, "ticketNum");
            return (Criteria) this;
        }

        public Criteria andTicketNumNotBetween(Integer value1, Integer value2) {
            addCriterion("ticket_num not between", value1, value2, "ticketNum");
            return (Criteria) this;
        }

        public Criteria andTicketInTimeIsNull() {
            addCriterion("ticket_in_time is null");
            return (Criteria) this;
        }

        public Criteria andTicketInTimeIsNotNull() {
            addCriterion("ticket_in_time is not null");
            return (Criteria) this;
        }

        public Criteria andTicketInTimeEqualTo(Date value) {
            addCriterion("ticket_in_time =", value, "ticketInTime");
            return (Criteria) this;
        }

        public Criteria andTicketInTimeNotEqualTo(Date value) {
            addCriterion("ticket_in_time <>", value, "ticketInTime");
            return (Criteria) this;
        }

        public Criteria andTicketInTimeGreaterThan(Date value) {
            addCriterion("ticket_in_time >", value, "ticketInTime");
            return (Criteria) this;
        }

        public Criteria andTicketInTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("ticket_in_time >=", value, "ticketInTime");
            return (Criteria) this;
        }

        public Criteria andTicketInTimeLessThan(Date value) {
            addCriterion("ticket_in_time <", value, "ticketInTime");
            return (Criteria) this;
        }

        public Criteria andTicketInTimeLessThanOrEqualTo(Date value) {
            addCriterion("ticket_in_time <=", value, "ticketInTime");
            return (Criteria) this;
        }

        public Criteria andTicketInTimeIn(List<Date> values) {
            addCriterion("ticket_in_time in", values, "ticketInTime");
            return (Criteria) this;
        }

        public Criteria andTicketInTimeNotIn(List<Date> values) {
            addCriterion("ticket_in_time not in", values, "ticketInTime");
            return (Criteria) this;
        }

        public Criteria andTicketInTimeBetween(Date value1, Date value2) {
            addCriterion("ticket_in_time between", value1, value2, "ticketInTime");
            return (Criteria) this;
        }

        public Criteria andTicketInTimeNotBetween(Date value1, Date value2) {
            addCriterion("ticket_in_time not between", value1, value2, "ticketInTime");
            return (Criteria) this;
        }

        public Criteria andTicketOutTimeIsNull() {
            addCriterion("ticket_out_time is null");
            return (Criteria) this;
        }

        public Criteria andTicketOutTimeIsNotNull() {
            addCriterion("ticket_out_time is not null");
            return (Criteria) this;
        }

        public Criteria andTicketOutTimeEqualTo(Date value) {
            addCriterion("ticket_out_time =", value, "ticketOutTime");
            return (Criteria) this;
        }

        public Criteria andTicketOutTimeNotEqualTo(Date value) {
            addCriterion("ticket_out_time <>", value, "ticketOutTime");
            return (Criteria) this;
        }

        public Criteria andTicketOutTimeGreaterThan(Date value) {
            addCriterion("ticket_out_time >", value, "ticketOutTime");
            return (Criteria) this;
        }

        public Criteria andTicketOutTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("ticket_out_time >=", value, "ticketOutTime");
            return (Criteria) this;
        }

        public Criteria andTicketOutTimeLessThan(Date value) {
            addCriterion("ticket_out_time <", value, "ticketOutTime");
            return (Criteria) this;
        }

        public Criteria andTicketOutTimeLessThanOrEqualTo(Date value) {
            addCriterion("ticket_out_time <=", value, "ticketOutTime");
            return (Criteria) this;
        }

        public Criteria andTicketOutTimeIn(List<Date> values) {
            addCriterion("ticket_out_time in", values, "ticketOutTime");
            return (Criteria) this;
        }

        public Criteria andTicketOutTimeNotIn(List<Date> values) {
            addCriterion("ticket_out_time not in", values, "ticketOutTime");
            return (Criteria) this;
        }

        public Criteria andTicketOutTimeBetween(Date value1, Date value2) {
            addCriterion("ticket_out_time between", value1, value2, "ticketOutTime");
            return (Criteria) this;
        }

        public Criteria andTicketOutTimeNotBetween(Date value1, Date value2) {
            addCriterion("ticket_out_time not between", value1, value2, "ticketOutTime");
            return (Criteria) this;
        }

        public Criteria andTicketStateIsNull() {
            addCriterion("ticket_state is null");
            return (Criteria) this;
        }

        public Criteria andTicketStateIsNotNull() {
            addCriterion("ticket_state is not null");
            return (Criteria) this;
        }

        public Criteria andTicketStateEqualTo(String value) {
            addCriterion("ticket_state =", value, "ticketState");
            return (Criteria) this;
        }

        public Criteria andTicketStateNotEqualTo(String value) {
            addCriterion("ticket_state <>", value, "ticketState");
            return (Criteria) this;
        }

        public Criteria andTicketStateGreaterThan(String value) {
            addCriterion("ticket_state >", value, "ticketState");
            return (Criteria) this;
        }

        public Criteria andTicketStateGreaterThanOrEqualTo(String value) {
            addCriterion("ticket_state >=", value, "ticketState");
            return (Criteria) this;
        }

        public Criteria andTicketStateLessThan(String value) {
            addCriterion("ticket_state <", value, "ticketState");
            return (Criteria) this;
        }

        public Criteria andTicketStateLessThanOrEqualTo(String value) {
            addCriterion("ticket_state <=", value, "ticketState");
            return (Criteria) this;
        }

        public Criteria andTicketStateLike(String value) {
            addCriterion("ticket_state like", value, "ticketState");
            return (Criteria) this;
        }

        public Criteria andTicketStateNotLike(String value) {
            addCriterion("ticket_state not like", value, "ticketState");
            return (Criteria) this;
        }

        public Criteria andTicketStateIn(List<String> values) {
            addCriterion("ticket_state in", values, "ticketState");
            return (Criteria) this;
        }

        public Criteria andTicketStateNotIn(List<String> values) {
            addCriterion("ticket_state not in", values, "ticketState");
            return (Criteria) this;
        }

        public Criteria andTicketStateBetween(String value1, String value2) {
            addCriterion("ticket_state between", value1, value2, "ticketState");
            return (Criteria) this;
        }

        public Criteria andTicketStateNotBetween(String value1, String value2) {
            addCriterion("ticket_state not between", value1, value2, "ticketState");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andStoreAccountIdIsNull() {
            addCriterion("store_account_id is null");
            return (Criteria) this;
        }

        public Criteria andStoreAccountIdIsNotNull() {
            addCriterion("store_account_id is not null");
            return (Criteria) this;
        }

        public Criteria andStoreAccountIdEqualTo(Integer value) {
            addCriterion("store_account_id =", value, "storeAccountId");
            return (Criteria) this;
        }

        public Criteria andStoreAccountIdNotEqualTo(Integer value) {
            addCriterion("store_account_id <>", value, "storeAccountId");
            return (Criteria) this;
        }

        public Criteria andStoreAccountIdGreaterThan(Integer value) {
            addCriterion("store_account_id >", value, "storeAccountId");
            return (Criteria) this;
        }

        public Criteria andStoreAccountIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("store_account_id >=", value, "storeAccountId");
            return (Criteria) this;
        }

        public Criteria andStoreAccountIdLessThan(Integer value) {
            addCriterion("store_account_id <", value, "storeAccountId");
            return (Criteria) this;
        }

        public Criteria andStoreAccountIdLessThanOrEqualTo(Integer value) {
            addCriterion("store_account_id <=", value, "storeAccountId");
            return (Criteria) this;
        }

        public Criteria andStoreAccountIdIn(List<Integer> values) {
            addCriterion("store_account_id in", values, "storeAccountId");
            return (Criteria) this;
        }

        public Criteria andStoreAccountIdNotIn(List<Integer> values) {
            addCriterion("store_account_id not in", values, "storeAccountId");
            return (Criteria) this;
        }

        public Criteria andStoreAccountIdBetween(Integer value1, Integer value2) {
            addCriterion("store_account_id between", value1, value2, "storeAccountId");
            return (Criteria) this;
        }

        public Criteria andStoreAccountIdNotBetween(Integer value1, Integer value2) {
            addCriterion("store_account_id not between", value1, value2, "storeAccountId");
            return (Criteria) this;
        }

        public Criteria andTicketValidatyStartIsNull() {
            addCriterion("ticket_validaty_start is null");
            return (Criteria) this;
        }

        public Criteria andTicketValidatyStartIsNotNull() {
            addCriterion("ticket_validaty_start is not null");
            return (Criteria) this;
        }

        public Criteria andTicketValidatyStartEqualTo(Date value) {
            addCriterion("ticket_validaty_start =", value, "ticketValidatyStart");
            return (Criteria) this;
        }

        public Criteria andTicketValidatyStartNotEqualTo(Date value) {
            addCriterion("ticket_validaty_start <>", value, "ticketValidatyStart");
            return (Criteria) this;
        }

        public Criteria andTicketValidatyStartGreaterThan(Date value) {
            addCriterion("ticket_validaty_start >", value, "ticketValidatyStart");
            return (Criteria) this;
        }

        public Criteria andTicketValidatyStartGreaterThanOrEqualTo(Date value) {
            addCriterion("ticket_validaty_start >=", value, "ticketValidatyStart");
            return (Criteria) this;
        }

        public Criteria andTicketValidatyStartLessThan(Date value) {
            addCriterion("ticket_validaty_start <", value, "ticketValidatyStart");
            return (Criteria) this;
        }

        public Criteria andTicketValidatyStartLessThanOrEqualTo(Date value) {
            addCriterion("ticket_validaty_start <=", value, "ticketValidatyStart");
            return (Criteria) this;
        }

        public Criteria andTicketValidatyStartIn(List<Date> values) {
            addCriterion("ticket_validaty_start in", values, "ticketValidatyStart");
            return (Criteria) this;
        }

        public Criteria andTicketValidatyStartNotIn(List<Date> values) {
            addCriterion("ticket_validaty_start not in", values, "ticketValidatyStart");
            return (Criteria) this;
        }

        public Criteria andTicketValidatyStartBetween(Date value1, Date value2) {
            addCriterion("ticket_validaty_start between", value1, value2, "ticketValidatyStart");
            return (Criteria) this;
        }

        public Criteria andTicketValidatyStartNotBetween(Date value1, Date value2) {
            addCriterion("ticket_validaty_start not between", value1, value2, "ticketValidatyStart");
            return (Criteria) this;
        }

        public Criteria andTicketValidatyEndIsNull() {
            addCriterion("ticket_validaty_end is null");
            return (Criteria) this;
        }

        public Criteria andTicketValidatyEndIsNotNull() {
            addCriterion("ticket_validaty_end is not null");
            return (Criteria) this;
        }

        public Criteria andTicketValidatyEndEqualTo(Date value) {
            addCriterion("ticket_validaty_end =", value, "ticketValidatyEnd");
            return (Criteria) this;
        }

        public Criteria andTicketValidatyEndNotEqualTo(Date value) {
            addCriterion("ticket_validaty_end <>", value, "ticketValidatyEnd");
            return (Criteria) this;
        }

        public Criteria andTicketValidatyEndGreaterThan(Date value) {
            addCriterion("ticket_validaty_end >", value, "ticketValidatyEnd");
            return (Criteria) this;
        }

        public Criteria andTicketValidatyEndGreaterThanOrEqualTo(Date value) {
            addCriterion("ticket_validaty_end >=", value, "ticketValidatyEnd");
            return (Criteria) this;
        }

        public Criteria andTicketValidatyEndLessThan(Date value) {
            addCriterion("ticket_validaty_end <", value, "ticketValidatyEnd");
            return (Criteria) this;
        }

        public Criteria andTicketValidatyEndLessThanOrEqualTo(Date value) {
            addCriterion("ticket_validaty_end <=", value, "ticketValidatyEnd");
            return (Criteria) this;
        }

        public Criteria andTicketValidatyEndIn(List<Date> values) {
            addCriterion("ticket_validaty_end in", values, "ticketValidatyEnd");
            return (Criteria) this;
        }

        public Criteria andTicketValidatyEndNotIn(List<Date> values) {
            addCriterion("ticket_validaty_end not in", values, "ticketValidatyEnd");
            return (Criteria) this;
        }

        public Criteria andTicketValidatyEndBetween(Date value1, Date value2) {
            addCriterion("ticket_validaty_end between", value1, value2, "ticketValidatyEnd");
            return (Criteria) this;
        }

        public Criteria andTicketValidatyEndNotBetween(Date value1, Date value2) {
            addCriterion("ticket_validaty_end not between", value1, value2, "ticketValidatyEnd");
            return (Criteria) this;
        }

        public Criteria andCustomerIdIsNull() {
            addCriterion("customer_id is null");
            return (Criteria) this;
        }

        public Criteria andCustomerIdIsNotNull() {
            addCriterion("customer_id is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerIdEqualTo(Long value) {
            addCriterion("customer_id =", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdNotEqualTo(Long value) {
            addCriterion("customer_id <>", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdGreaterThan(Long value) {
            addCriterion("customer_id >", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdGreaterThanOrEqualTo(Long value) {
            addCriterion("customer_id >=", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdLessThan(Long value) {
            addCriterion("customer_id <", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdLessThanOrEqualTo(Long value) {
            addCriterion("customer_id <=", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdIn(List<Long> values) {
            addCriterion("customer_id in", values, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdNotIn(List<Long> values) {
            addCriterion("customer_id not in", values, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdBetween(Long value1, Long value2) {
            addCriterion("customer_id between", value1, value2, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdNotBetween(Long value1, Long value2) {
            addCriterion("customer_id not between", value1, value2, "customerId");
            return (Criteria) this;
        }
    }

    /**
     */
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