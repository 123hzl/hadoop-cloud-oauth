package com.hzl.hadoop.app.controller;

import com.github.pagehelper.PageInfo;
import com.hzl.hadoop.app.entity.RequestLogsEntity;
import com.hzl.hadoop.app.service.RequestLogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


/**
 * 请求日志
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-11-19 16:18:12
 */
@RestController
@RequestMapping("/requestlogs")
public class RequestLogsController {
	@Autowired
	private RequestLogsService requestLogsService;

	/**
	 * 列表
	 */
	@GetMapping("/list")
	public ResponseEntity<PageInfo<RequestLogsEntity>> list(RequestLogsEntity params, @RequestParam int start, @RequestParam int pageSize) {
		PageInfo<RequestLogsEntity> page = requestLogsService.queryPage(params, start, pageSize);

		return new ResponseEntity(page, HttpStatus.OK);
	}


	/**
	 * 信息
	 */
	@GetMapping("/info/{id}")
	public ResponseEntity<RequestLogsEntity> info(@PathVariable("id") Long id) {
		RequestLogsEntity requestLogs = requestLogsService.getById(id);

		return new ResponseEntity(requestLogs, HttpStatus.OK);
	}

	/**
	 * 保存
	 */
	@PostMapping("/save")
	public ResponseEntity save(@RequestBody RequestLogsEntity requestLogs) {
		requestLogsService.save(requestLogs);

		return new ResponseEntity(HttpStatus.OK);
	}

	/**
	 * 修改
	 */
	@PutMapping("/update")
	public ResponseEntity update(@RequestBody RequestLogsEntity requestLogs) {
		requestLogsService.updateById(requestLogs);

		return new ResponseEntity(HttpStatus.OK);
	}

	/**
	 * 删除
	 */
	@DeleteMapping("/delete")
	public ResponseEntity delete(@RequestBody Long[] ids) {
		requestLogsService.removeByIds(Arrays.asList(ids));

		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

}
