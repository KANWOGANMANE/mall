package com.sjq.cms.service;

import com.sjq.cms.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author kemp
 * @since 2022-04-11
 */
public interface ICrmBannerService extends IService<CrmBanner> {

    List<CrmBanner> selectAllBanner();
}
