package com.skyfaith.service;

import com.skyfaith.dao.EmsOrderMapper;
import com.skyfaith.domain.EmsOrder;
import com.skyfaith.domain.EmsOrderExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service("emsOrderService")
public class EmsOrderServiceImpl implements EmsOrderService {
    @Resource
    private EmsOrderMapper emsOrderDao;

    @Override
    public List<EmsOrder> getOrderList() {
        EmsOrderExample emsOrderExample = new EmsOrderExample();
        EmsOrderExample.Criteria criteria = emsOrderExample.createCriteria();
        criteria.andOrdernoIsNotNull();
        emsOrderExample.getOredCriteria().add(criteria);
        return emsOrderDao.selectByExample(emsOrderExample);
    }

    @Override
    public boolean updateOrderList(List<EmsOrder> orders) {
        try {
            //获取EMS单号信息
            EmsOrderExample emsOrderExample = new EmsOrderExample();
            EmsOrderExample.Criteria criteria = emsOrderExample.createCriteria();
            criteria.andOrdernoIsNull();
            emsOrderExample.getOredCriteria().add(criteria);

            List<EmsOrder> emsOrders = emsOrderDao.selectByExample(emsOrderExample);

            //判断是否有足够的EMS快递单号可用
            if (orders.size() > emsOrders.size()) {
                return false;
            } else {
                for (int i = 0; i < orders.size(); i++) {
                    orders.get(i).setEorderno(emsOrders.get(i).getEorderno());
                }
                //TODO:放到一个Transaction 中
                EmsOrderExample emsOrderExample2 = new EmsOrderExample();
                for (EmsOrder order : orders) {
                    //更新之前判断清单中的关联单号是否已经存在
                    EmsOrderExample.Criteria criteria2 = emsOrderExample2.createCriteria();
                    criteria2.andOrdernoEqualTo(order.getOrderno());
                    emsOrderExample2.getOredCriteria().clear();
                    emsOrderExample2.getOredCriteria().add(criteria2);
                    List<EmsOrder> emsOrder = emsOrderDao.selectByExample(emsOrderExample2);
                    //导入重复的单据则忽略更新
                    if (emsOrder != null && emsOrder.size() > 0) {
                        continue;
                    }

                    emsOrderDao.updateByPrimaryKey(order);
                }
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateEorderList(List<EmsOrder> emsOrders) {
        try {
            //判断EMS快递单号是否重复插入
            for (EmsOrder order : emsOrders) {
                EmsOrder emsOrder = emsOrderDao.selectByPrimaryKey(order.getEorderno());
                if (emsOrder == null) {
                    //插入
                    emsOrderDao.insertSelective(order);
                }
            }

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public long getAvaliableEmsOrderCount() {
        EmsOrderExample emsOrderExample = new EmsOrderExample();
        EmsOrderExample.Criteria criteria = emsOrderExample.createCriteria();
        criteria.andOrdernoIsNull();
        return emsOrderDao.countByExample(emsOrderExample);
    }

    @Override
    public EmsOrder searchEmsOrderByOrderNo(String orderNo) {
        EmsOrderExample emsOrderExample = new EmsOrderExample();
        EmsOrderExample.Criteria criteria = emsOrderExample.createCriteria();
        criteria.andOrdernoEqualTo(orderNo);

        List<EmsOrder> orders = emsOrderDao.selectByExample(emsOrderExample);
        if (orders != null && orders.size() > 0) {
            return orders.get(0);
        }
        return null;
    }
}
