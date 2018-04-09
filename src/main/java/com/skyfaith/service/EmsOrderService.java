package com.skyfaith.service;

import com.skyfaith.domain.EmsOrder;

import java.util.List;

public interface EmsOrderService {
    List<EmsOrder> getOrderList();
    boolean updateOrderList(List<EmsOrder> orders);
    boolean updateEorderList(List<EmsOrder> emsOrders);
    long getAvaliableEmsOrderCount();
    EmsOrder searchEmsOrderByOrderNo(String orderNo);
}
