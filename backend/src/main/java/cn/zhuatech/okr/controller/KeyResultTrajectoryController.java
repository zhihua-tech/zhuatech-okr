/* Copyright 2026 上海如静知华信息科技有限公司 */
package cn.zhuatech.okr.controller;

import cn.zhuatech.okr.common.ApiResponse;
import cn.zhuatech.okr.service.KeyResultTrajectoryService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/okr/insights")
public class KeyResultTrajectoryController {
    private final KeyResultTrajectoryService service;
    public KeyResultTrajectoryController(KeyResultTrajectoryService service) { this.service = service; }

    @PostMapping("/key-result-trajectory")
    public ApiResponse<KeyResultTrajectoryService.Result> forecast(
        @Valid @RequestBody KeyResultTrajectoryService.Request request) {
        return ApiResponse.ok(service.forecast(request));
    }
}
