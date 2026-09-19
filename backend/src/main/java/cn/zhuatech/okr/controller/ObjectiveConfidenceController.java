/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.okr.controller;
import cn.zhuatech.okr.common.ApiResponse; import cn.zhuatech.okr.service.ObjectiveConfidenceService; import jakarta.validation.Valid; import org.springframework.web.bind.annotation.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController @RequestMapping("/api/admin") public class ObjectiveConfidenceController {private final ObjectiveConfidenceService service; /**
                                                                                                                                             * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                             */
public ObjectiveConfidenceController(ObjectiveConfidenceService service){this.service=service;} /**
                                                                                                                                                                                                                                             * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                             */
@PostMapping("/objective-confidence") public ApiResponse<ObjectiveConfidenceService.Result> evaluate(@Valid @RequestBody ObjectiveConfidenceService.Request request){return ApiResponse.ok(service.evaluate(request));}}
