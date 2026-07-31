/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. */
package cn.zhuatech.okr.dto;
import jakarta.validation.constraints.*; import java.time.*; import java.util.List;
public final class OkrDto { private OkrDto(){}
    public record Metric(String label,String value,String hint,String tone){}
    public record ObjectiveView(Long id,String orderNo,String productCode,String productName,String department,String workshop,int plannedQty,int completedQty,int defectQty,LocalDate dueDate,String status,String batchNo,int progress){}
    public record InitiativeView(String code,String name,String department,String status,int oee,LocalDateTime lastHeartbeat){}
    public record CheckInView(String checkInNo,String orderNo,String productName,String checkInType,int sampleQty,int defectQty,String result,String inspector){}
    public record Dashboard(List<Metric> metrics,List<ObjectiveView> objectives,List<InitiativeView> initiative,List<CheckInView> checkIns){}
    public record ReportRequest(@NotBlank String operationName,@Positive int goodQty,@PositiveOrZero int defectQty,@Size(max=200) String remark){}
    public record ReportResult(String orderNo,int completedQty,int defectQty,int progress,String status){}
}
