/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. · https://www.zhuatech.cn/ */
package cn.zhuatech.okr.repository; import cn.zhuatech.okr.model.Department; import org.springframework.data.jpa.repository.JpaRepository; import java.util.Optional;
public interface DepartmentRepository extends JpaRepository<Department,Long>{Optional<Department> findByCode(String code);}
