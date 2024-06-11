package com.papupupu.train.member.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.papupupu.train.common.context.LoginMemberContext;
import com.papupupu.train.common.resp.PageResp;
import com.papupupu.train.common.util.SnowUtil;
import com.papupupu.train.member.domain.Passenger;
import com.papupupu.train.member.domain.PassengerExample;
import com.papupupu.train.member.mapper.PassengerMapper;
import com.papupupu.train.member.req.PassengerQueryReq;
import com.papupupu.train.member.req.PassengerSaveReq;
import com.papupupu.train.member.resp.PassengerQueryResp;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassageService {
    @Resource
    private PassengerMapper passengerMapper;

    private static final Logger LOG = LoggerFactory.getLogger(PassageService.class);

    /**
     * 保存/编辑用户
     *
     * @param req
     */
    public void save(PassengerSaveReq req) {
        Passenger passenger = BeanUtil.copyProperties(req, Passenger.class);
        DateTime now = DateTime.now();
        if(ObjectUtil.isNull(passenger.getId())) {
            passenger.setId(SnowUtil.getSnowflakeNextId());
            passenger.setMemberId(LoginMemberContext.getId());
            passenger.setCreateTime(now);
            passenger.setUpdateTime(now);
            passengerMapper.insert(passenger);
        } else{
            passenger.setUpdateTime(now);
            passengerMapper.updateByPrimaryKey(passenger);
        }
    }

    /**
     * 分页查询乘车人
     *
     * @param passengerQueryReq
     * @return
     */
    public PageResp<PassengerQueryResp> queryList(PassengerQueryReq passengerQueryReq) {
        PassengerExample passengerExample = new PassengerExample();
        PassengerExample.Criteria criteria = passengerExample.createCriteria();
        if (ObjectUtil.isNotNull(passengerQueryReq) && ObjectUtil.isNotNull(passengerQueryReq.getMemberId())) {
            criteria.andMemberIdEqualTo(passengerQueryReq.getMemberId());
        }


        LOG.info("查询页码：{}", passengerQueryReq.getPage());
        LOG.info("每页条数：{}", passengerQueryReq.getSize());

        PageHelper.startPage(passengerQueryReq.getPage(), passengerQueryReq.getSize());
        List<Passenger> passengerList = passengerMapper.selectByExample(passengerExample);

        PageInfo<Passenger> passengerPageInfo = new PageInfo<>(passengerList);
        LOG.info("总行数：{}", passengerPageInfo.getTotal());
        LOG.info("总页数：{}", passengerPageInfo.getPages());

        PageResp<PassengerQueryResp> pageResp = new PageResp<>();
        List<PassengerQueryResp> passengerQueryResps = BeanUtil.copyToList(passengerList, PassengerQueryResp.class);
        pageResp.setList(passengerQueryResps);
        pageResp.setTotal(passengerPageInfo.getTotal());
        return pageResp;
    }


    public void delete(Long id) {
        passengerMapper.deleteByPrimaryKey(id);
    }
}