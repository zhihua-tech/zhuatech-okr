/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.okr.controller;

import cn.zhuatech.okr.common.ApiResponse;
import cn.zhuatech.okr.service.ObjectiveCycleGovernanceService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController
@RequestMapping("/api/enterprise/okr")
public class ObjectiveCycleGovernanceController {
    private final ObjectiveCycleGovernanceService service;
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public ObjectiveCycleGovernanceController(ObjectiveCycleGovernanceService service) { this.service = service; }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @PostMapping("/objective-cycle-governance")
    public ApiResponse<ObjectiveCycleGovernanceService.Assessment> assess(
        @Valid @RequestBody ObjectiveCycleGovernanceService.Request request) {
        return ApiResponse.ok(service.assess(request));
    }
}
