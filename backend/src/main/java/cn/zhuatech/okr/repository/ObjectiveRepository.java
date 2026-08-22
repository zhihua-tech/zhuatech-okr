/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. · https://www.zhuatech.cn/ */
package cn.zhuatech.okr.repository; import cn.zhuatech.okr.model.Objective; import org.springframework.data.jpa.repository.JpaRepository; import java.util.List;
public interface ObjectiveRepository extends JpaRepository<Objective,Long>{List<Objective> findAllByOrderByDueDateAsc();List<Objective> findByDepartmentCodeOrderByDueDateAsc(String code);long countByStatus(Objective.Status status);}
