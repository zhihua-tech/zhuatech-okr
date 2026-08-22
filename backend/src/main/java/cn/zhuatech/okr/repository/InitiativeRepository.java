/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. · https://www.zhuatech.cn/ */
package cn.zhuatech.okr.repository; import cn.zhuatech.okr.model.Initiative; import org.springframework.data.jpa.repository.JpaRepository; import java.util.List;
public interface InitiativeRepository extends JpaRepository<Initiative,Long>{List<Initiative> findAllByOrderByCodeAsc();long countByStatus(Initiative.Status status);}
