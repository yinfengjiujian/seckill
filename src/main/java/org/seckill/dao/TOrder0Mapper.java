package org.seckill.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.seckill.entity.TOrder0;
import org.seckill.entity.TOrder0Example;

public interface TOrder0Mapper {
    int countByExample(TOrder0Example example);

    int deleteByExample(TOrder0Example example);

    int deleteByPrimaryKey(Long orderId);

    int insert(TOrder0 record);

    int insertSelective(TOrder0 record);

    List<TOrder0> selectByExample(TOrder0Example example);

    TOrder0 selectByPrimaryKey(Long orderId);

    int updateByExampleSelective(@Param("record") TOrder0 record, @Param("example") TOrder0Example example);

    int updateByExample(@Param("record") TOrder0 record, @Param("example") TOrder0Example example);

    int updateByPrimaryKeySelective(TOrder0 record);

    int updateByPrimaryKey(TOrder0 record);
}