/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.okr.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KeyResultTrajectoryService {
    public Result forecast(Request request) {
        double averageProgress = request.keyResults().stream().mapToInt(KeyResult::progressPercent).average().orElse(0);
        double expectedProgress = Math.min(100, request.daysElapsed() * 100D / request.cycleDays());
        double forecastProgress = request.daysElapsed() == 0 ? averageProgress
            : Math.min(100, averageProgress / request.daysElapsed() * request.cycleDays());
        long blocked = request.keyResults().stream().filter(KeyResult::blocked).count();
        double gap = expectedProgress - averageProgress;
        String status = gap > 15 || blocked >= 2 ? "OFF_TRACK" : gap > 5 || blocked > 0 ? "WATCH" : "ON_TRACK";
        List<String> actions = new ArrayList<>();
        if (blocked > 0) actions.add("为受阻关键结果明确解除依赖的责任人与期限");
        if (gap > 5) actions.add("调整本周期举措并提高低进展关键结果资源优先级");
        if ("ON_TRACK".equals(status)) actions.add("保持当前节奏并按周更新关键结果证据");
        return new Result(request.objectiveCode(), round(averageProgress), round(expectedProgress),
            round(forecastProgress), (int) blocked, status, actions);
    }

    private double round(double value) { return Math.round(value * 100D) / 100D; }

    public record Request(@NotBlank String objectiveCode, @Min(0) int daysElapsed,
                          @Min(1) int cycleDays, @NotEmpty List<@Valid KeyResult> keyResults) {}
    public record KeyResult(@NotBlank String name, @Min(0) @Max(100) int progressPercent,
                            @Min(0) @Max(100) int confidencePercent, boolean blocked) {}
    public record Result(String objectiveCode, double averageProgress, double expectedProgress,
                         double forecastProgress, int blockedKeyResults, String status,
                         List<String> actions) {}
}
