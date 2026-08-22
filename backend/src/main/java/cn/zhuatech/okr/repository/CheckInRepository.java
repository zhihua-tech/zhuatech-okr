/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. · https://www.zhuatech.cn/ */
package cn.zhuatech.okr.repository; import cn.zhuatech.okr.model.CheckIn; import org.springframework.data.jpa.repository.JpaRepository; import java.util.List;
public interface CheckInRepository extends JpaRepository<CheckIn,Long>{List<CheckIn> findTop10ByOrderByIdDesc();long countByResult(CheckIn.Result result);}
