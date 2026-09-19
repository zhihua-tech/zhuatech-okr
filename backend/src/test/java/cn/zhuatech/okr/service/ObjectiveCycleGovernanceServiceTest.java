/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.okr.service;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
class ObjectiveCycleGovernanceServiceTest {
    private final ObjectiveCycleGovernanceService service = new ObjectiveCycleGovernanceService();

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test void keepsHealthyObjectiveOnTrack() {
        var result = service.assess(new ObjectiveCycleGovernanceService.Request(
            "OBJ-001", "owner-a", true, 3, 4, 7, 80, true, true, true));
        assertThat(result.decision()).isEqualTo(ObjectiveCycleGovernanceService.Decision.ON_TRACK);
    }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test void blocksClosedOrUnmeasurableObjectives() {
        var result = service.assess(new ObjectiveCycleGovernanceService.Request(
            "OBJ-002", "owner-a", false, 0, 2, 7, 80, true, true, false));
        assertThat(result.decision()).isEqualTo(ObjectiveCycleGovernanceService.Decision.BLOCKED);
        assertThat(result.blockers()).hasSize(3);
    }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test void reviewsStaleLowConfidenceExecution() {
        var result = service.assess(new ObjectiveCycleGovernanceService.Request(
            "OBJ-003", "owner-a", true, 3, 14, 7, 40, false, false, true));
        assertThat(result.decision()).isEqualTo(ObjectiveCycleGovernanceService.Decision.REVIEW);
        assertThat(result.actions()).hasSize(4);
    }
}
