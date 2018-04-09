package com.skyfaith.dao;

import com.skyfaith.domain.EmsOrder;
import com.skyfaith.domain.EmsOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmsOrderMapper {
    long countByExample(EmsOrderExample example);

    int deleteByExample(EmsOrderExample example);

    int deleteByPrimaryKey(String eorderno);

    int insert(EmsOrder record);

    int insertSelective(EmsOrder record);

    List<EmsOrder> selectByExample(EmsOrderExample example);

    EmsOrder selectByPrimaryKey(String eorderno);

    int updateByExampleSelective(@Param("record") EmsOrder record, @Param("example") EmsOrderExample example);

    int updateByExample(@Param("record") EmsOrder record, @Param("example") EmsOrderExample example);

    int updateByPrimaryKeySelective(EmsOrder record);

    int updateByPrimaryKey(EmsOrder record);
}