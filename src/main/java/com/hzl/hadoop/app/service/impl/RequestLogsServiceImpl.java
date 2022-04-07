package com.hzl.hadoop.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hzl.hadoop.app.entity.RequestLogsEntity;
import com.hzl.hadoop.app.mapper.RequestLogsMapper;
import com.hzl.hadoop.app.service.RequestLogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("requestLogsService")
public class RequestLogsServiceImpl extends ServiceImpl<RequestLogsMapper, RequestLogsEntity> implements RequestLogsService {

	@Autowired
	RequestLogsMapper mapper;

	@Override
	public PageInfo queryPage(RequestLogsEntity params, int start, int pageSize) {
		QueryWrapper<RequestLogsEntity> queryWrapper = new QueryWrapper(params);

		PageInfo<RequestLogsEntity> pageResult = PageHelper.startPage(start, pageSize).doSelectPageInfo(() -> mapper.selectList(queryWrapper));

		return pageResult;
	}

}