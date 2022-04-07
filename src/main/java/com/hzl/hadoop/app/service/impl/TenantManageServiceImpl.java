package com.hzl.hadoop.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hzl.hadoop.app.entity.TenantManageEntity;
import com.hzl.hadoop.app.mapper.TenantManageMapper;
import com.hzl.hadoop.app.service.TenantManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("tenantManageService")
public class TenantManageServiceImpl extends ServiceImpl<TenantManageMapper, TenantManageEntity> implements TenantManageService {

	@Autowired
	TenantManageMapper mapper;

	@Override
	public PageInfo queryPage(TenantManageEntity params, int start, int pageSize) {
		QueryWrapper<TenantManageEntity> queryWrapper = new QueryWrapper(params);

		PageInfo<TenantManageEntity> pageResult = PageHelper.startPage(start, pageSize).doSelectPageInfo(() -> mapper.selectList(queryWrapper));

		return pageResult;
	}

}