package com.hzl.hadoop.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.hzl.hadoop.app.entity.RequestLogsEntity;

/**
 * 请求日志
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-11-19 16:18:12
 */
public interface RequestLogsService extends IService<RequestLogsEntity> {

	PageInfo queryPage(RequestLogsEntity params, int start, int pageSize);
}

