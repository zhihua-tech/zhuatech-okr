/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. */
package cn.zhuatech.okr.model;
import jakarta.persistence.*; import java.time.LocalDateTime;
@Entity @Table(name="okr_check_in") public class CheckIn extends BaseEntity {
    public enum Result { PENDING, PASSED, FAILED }
    @Column(nullable=false,unique=true,length=32) private String checkInNo; @ManyToOne(optional=false,fetch=FetchType.LAZY) private Objective objective;
    @Column(nullable=false,length=30) private String checkInType; @Column(nullable=false) private int sampleQty; @Column(nullable=false) private int defectQty; @Enumerated(EnumType.STRING) @Column(nullable=false,length=20) private Result result;
    @Column(length=50) private String inspector; @Column(nullable=false) private LocalDateTime createdAt;
    protected CheckIn(){} public CheckIn(String checkInNo,Objective objective,String checkInType,int sampleQty,int defectQty,Result result,String inspector){this.checkInNo=checkInNo;this.objective=objective;this.checkInType=checkInType;this.sampleQty=sampleQty;this.defectQty=defectQty;this.result=result;this.inspector=inspector;this.createdAt=LocalDateTime.now();}
    public String getCheckInNo(){return checkInNo;} public Objective getObjective(){return objective;} public String getCheckInType(){return checkInType;} public int getSampleQty(){return sampleQty;} public int getDefectQty(){return defectQty;} public Result getResult(){return result;} public String getInspector(){return inspector;}
}
