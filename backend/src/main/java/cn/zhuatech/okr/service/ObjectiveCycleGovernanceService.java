/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.okr.service;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Service
public class ObjectiveCycleGovernanceService {
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public Assessment assess(Request request) {
        List<String> blockers = new ArrayList<>();
        List<String> actions = new ArrayList<>();
        if (!request.cycleOpen()) blockers.add("目标周期已关闭");
        if (!request.outcomeMetricDefined()) blockers.add("目标未定义可验证的结果指标");
        if (request.keyResultCount() < 1 || request.keyResultCount() > 5) blockers.add("关键结果数量应为 1 至 5 个");
        if (!blockers.isEmpty()) {
            actions.add("由目标负责人修正阻断项后重新进入周期治理");
            return new Assessment(Decision.BLOCKED, blockers, actions);
        }
        if (request.checkInAgeDays() > request.maxCheckInAgeDays()
            || request.confidence() < 60 || !request.progressEvidenceComplete()
            || !request.dependenciesOwned()) {
            if (request.checkInAgeDays() > request.maxCheckInAgeDays()) actions.add("完成逾期 Check-in 并说明变化");
            if (request.confidence() < 60) actions.add("制定低信心关键结果纠偏计划");
            if (!request.progressEvidenceComplete()) actions.add("补充可核验的进度证据");
            if (!request.dependenciesOwned()) actions.add("为跨部门依赖指定责任人和期限");
            return new Assessment(Decision.REVIEW, blockers, actions);
        }
        actions.add("保持周度 Check-in，并将证据快照纳入周期复盘");
        return new Assessment(Decision.ON_TRACK, blockers, actions);
    }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record Request(@NotBlank String objectiveId, @NotBlank String ownerId,
                          boolean cycleOpen, @Min(0) int keyResultCount,
                          @Min(0) int checkInAgeDays, @Min(1) int maxCheckInAgeDays,
                          @Min(0) @Max(100) int confidence,
                          boolean progressEvidenceComplete, boolean dependenciesOwned,
                          boolean outcomeMetricDefined) {}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record Assessment(Decision decision, List<String> blockers, List<String> actions) {}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public enum Decision { ON_TRACK, REVIEW, BLOCKED }
}
