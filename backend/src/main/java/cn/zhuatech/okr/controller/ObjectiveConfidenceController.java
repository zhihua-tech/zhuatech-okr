/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.okr.controller;
import cn.zhuatech.okr.common.ApiResponse; import cn.zhuatech.okr.service.ObjectiveConfidenceService; import jakarta.validation.Valid; import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/admin") public class ObjectiveConfidenceController {private final ObjectiveConfidenceService service; public ObjectiveConfidenceController(ObjectiveConfidenceService service){this.service=service;} @PostMapping("/objective-confidence") public ApiResponse<ObjectiveConfidenceService.Result> evaluate(@Valid @RequestBody ObjectiveConfidenceService.Request request){return ApiResponse.ok(service.evaluate(request));}}
