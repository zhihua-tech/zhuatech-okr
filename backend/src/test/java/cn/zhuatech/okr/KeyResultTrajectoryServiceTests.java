/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.okr;

import cn.zhuatech.okr.service.KeyResultTrajectoryService;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

class KeyResultTrajectoryServiceTests {
    private final KeyResultTrajectoryService service = new KeyResultTrajectoryService();

    @Test void flagsBlockedObjectiveBehindTrajectory() {
        var result = service.forecast(new KeyResultTrajectoryService.Request("O-2026-Q3", 45, 90, List.of(
            new KeyResultTrajectoryService.KeyResult("收入增长", 25, 50, true),
            new KeyResultTrajectoryService.KeyResult("客户增长", 30, 55, true))));
        assertEquals(27.5, result.averageProgress());
        assertEquals("OFF_TRACK", result.status());
    }

    @Test void keepsHealthyObjectiveOnTrack() {
        var result = service.forecast(new KeyResultTrajectoryService.Request("O-2026-Q4", 30, 90, List.of(
            new KeyResultTrajectoryService.KeyResult("交付效率", 40, 80, false),
            new KeyResultTrajectoryService.KeyResult("质量提升", 38, 82, false))));
        assertEquals("ON_TRACK", result.status());
    }
}
