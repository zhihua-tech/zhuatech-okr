/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.okr.controller;

import cn.zhuatech.okr.common.ApiResponse;
import cn.zhuatech.okr.service.KeyResultTrajectoryService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController
@RequestMapping("/api/okr/insights")
public class KeyResultTrajectoryController {
    private final KeyResultTrajectoryService service;
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public KeyResultTrajectoryController(KeyResultTrajectoryService service) { this.service = service; }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @PostMapping("/key-result-trajectory")
    public ApiResponse<KeyResultTrajectoryService.Result> forecast(
        @Valid @RequestBody KeyResultTrajectoryService.Request request) {
        return ApiResponse.ok(service.forecast(request));
    }
}
