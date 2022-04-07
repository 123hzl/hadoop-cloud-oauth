package com.hzl.hadoop.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.hzl.hadoop.app.entity.TenantManageEntity;

/**
 * 租户管理
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-11-19 16:18:12
 */
public interface TenantManageService extends IService<TenantManageEntity> {

	PageInfo queryPage(TenantManageEntity params, int start, int pageSize);
}

