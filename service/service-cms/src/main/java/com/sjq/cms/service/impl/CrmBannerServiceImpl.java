package com.sjq.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sjq.cms.entity.CrmBanner;
import com.sjq.cms.mapper.CrmBannerMapper;
import com.sjq.cms.service.ICrmBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author kemp
 * @since 2022-04-11
 */
@Service("crmService")
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements ICrmBannerService {

    @Override
    @Cacheable(value = "banner",key = "'selectBannerList'")
    public List<CrmBanner> selectAllBanner() {
        QueryWrapper<CrmBanner> qw = new QueryWrapper<>();
        qw.orderByDesc("id");
        qw.last("limit 2");
        List<CrmBanner> crmBanners = null;
        crmBanners = baseMapper.selectList(null);
        return crmBanners;
    }
}
