/* Copyright 2026 上海如静知华信息科技有限公司 */
package cn.zhuatech.okr.service;
import jakarta.validation.constraints.*; import org.springframework.stereotype.Service; import java.util.ArrayList; import java.util.List;
@Service public class ObjectiveConfidenceService {
    public Result evaluate(Request r){
        double deliveryIndex=r.elapsedPercent()==0?100:Math.min(120,r.progressPercent()*100.0/r.elapsedPercent());
        double velocityIndex=r.requiredVelocity()==0?100:Math.min(120,r.weeklyVelocity()*100.0/r.requiredVelocity());
        double score=r.confidenceVote()*.35+deliveryIndex*.35+velocityIndex*.20+10-r.keyResultsAtRisk()*5-r.blockers()*8;
        score=Math.max(0,Math.min(100,score)); double variance=r.progressPercent()-r.elapsedPercent();
        String forecast=score<45||variance<-20?"OFF_TRACK":score<70||r.blockers()>0?"AT_RISK":"ON_TRACK";
        List<String> actions=new ArrayList<>(); if(variance<-10)actions.add("缩小目标范围或增加关键资源"); if(r.keyResultsAtRisk()>0)actions.add("逐项明确风险关键结果的恢复计划"); if(r.blockers()>0)actions.add("升级跨团队阻塞事项并设置解决期限"); if(velocityIndex<80)actions.add("提高周度交付速度或调整目标承诺");
        return new Result(round(variance),round(deliveryIndex),round(velocityIndex),round(score),forecast,actions);
    }
    private double round(double v){return Math.round(v*100)/100.0;}
    public record Request(@NotBlank String objectiveId,@DecimalMin("0") @DecimalMax("100") double elapsedPercent,
        @DecimalMin("0") @DecimalMax("100") double progressPercent,@Min(0) int keyResultsAtRisk,@Min(0) int blockers,
        @Min(0) @Max(100) int confidenceVote,@DecimalMin("0") double weeklyVelocity,@DecimalMin("0") double requiredVelocity){}
    public record Result(double scheduleVariance,double deliveryIndex,double velocityIndex,double confidenceScore,String forecast,List<String> actions){}
}

