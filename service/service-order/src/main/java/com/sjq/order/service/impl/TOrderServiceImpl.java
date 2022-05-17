package com.sjq.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sjq.commonutils.vo.CourseWebVo;
import com.sjq.commonutils.vo.UcenterMemberVo;
import com.sjq.order.client.eduClient;
import com.sjq.order.client.userClient;
import com.sjq.order.entity.EduCourse;
import com.sjq.order.entity.TOrder;
import com.sjq.order.mapper.TOrderMapper;
import com.sjq.order.service.ITOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sjq.order.utils.OrderNoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author kemp
 * @since 2022-04-16
 */
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements ITOrderService {

    @Autowired
    private eduClient eduClient;

    @Autowired
    private userClient userClient;

    //创建订单
    @Override
    public String createOrder(String courseId, String memberid) {
        //根据courseid 和 memberid查询订单，查看是否已经生成
        QueryWrapper<TOrder> qw = new QueryWrapper<>();
        qw.eq("course_id",courseId);
        qw.eq("member_id",memberid);
        TOrder tOrder = baseMapper.selectOne(qw);
        if(tOrder != null){
            if(tOrder.getStatus() == 1){
                //已经购买
                return "ALREADYBUY";
            }
            return tOrder.getOrderNo();
        }

        //创建
        UcenterMemberVo memberinfoOrder = userClient.getInfoUcccc(memberid);
        CourseWebVo courseInfoOrder = eduClient.getCourseInfoOrder(courseId);
        TOrder order = new TOrder();
        order.setOrderNo(OrderNoUtil.getOrderNo());
        order.setCourseId(courseId);
        order.setMemberId(memberid);
        order.setGmtCreate(LocalDateTime.now());
        order.setGmtModified(LocalDateTime.now());
        order.setStatus(0);  //订单状态（0：未支付 1：已支付）
        order.setPayType(1);  //支付类型 ，微信1
        if(memberinfoOrder!=null){
            order.setMobile(memberinfoOrder.getMobile());
            order.setNickname(memberinfoOrder.getNickname());
        }
        if(courseInfoOrder!=null){
            order.setCourseCover(courseInfoOrder.getCover());
            order.setCourseTitle(courseInfoOrder.getTitle());
            order.setTeacherName(courseInfoOrder.getTeacherName());
            order.setTotalFee(courseInfoOrder.getPrice());
        }
        baseMapper.insert(order);
        return order.getOrderNo();
    }

    @Override
    public List<EduCourse> gethasBuyCourse(String memberid) {
        QueryWrapper<TOrder> qw = new QueryWrapper<>();
        qw.eq("member_id",memberid);
        qw.eq("is_deleted",0);
        qw.eq("status",1);
        List<EduCourse> res = baseMapper.selecthasbuylist(memberid);
        return res;
    }
}
