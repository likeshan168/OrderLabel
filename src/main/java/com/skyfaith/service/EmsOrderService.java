package com.skyfaith.service;

import com.skyfaith.domain.EmsOrder;
import com.skyfaith.domain.EmsOrderExample;

import java.util.List;

public interface EmsOrderService {
    List<EmsOrder> getOrderList();
    List<EmsOrder> getOrderList(EmsOrderExample emsOrderExample);
    boolean updateOrderList(List<EmsOrder> orders);
    boolean updateOrder(EmsOrder order);
    boolean updateEorderList(List<EmsOrder> emsOrders);
    long getAvaliableEmsOrderCount();
    EmsOrder searchEmsOrderByOrderNo(String orderNo);
    boolean deleteDataByPrintDate(String printDate);
    boolean clearAllData();
}
