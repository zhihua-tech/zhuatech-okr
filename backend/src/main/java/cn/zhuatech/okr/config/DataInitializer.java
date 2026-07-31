/* Copyright 2026 上海如静知华信息科技有限公司 */
package cn.zhuatech.okr.config;

import cn.zhuatech.okr.model.*;
import cn.zhuatech.okr.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner seed(DepartmentRepository departments, ObjectiveRepository objectives,
                           InitiativeRepository initiatives, CheckInRepository checkIns,
                           UserRepository users, PasswordEncoder encoder) {
        return args -> {
            if (departments.count() > 0) return;
            Department product = departments.save(new Department("DEPT-PROD", "产品与研发中心", "技术体系", 100));
            Department sales = departments.save(new Department("DEPT-SALES", "市场营销中心", "商业体系", 100));
            Department delivery = departments.save(new Department("DEPT-DEL", "客户交付中心", "服务体系", 100));

            Objective o1 = objectives.save(new Objective("O-2026Q3-018", "KR-NPS", "提升重点客户产品体验", product, 100, 72, 4, LocalDate.now().plusDays(45), Objective.Status.RUNNING, "2026-Q3"));
            Objective o2 = objectives.save(new Objective("O-2026Q3-021", "KR-ARR", "建立可复制的行业销售路径", sales, 100, 58, 6, LocalDate.now().plusDays(50), Objective.Status.RUNNING, "2026-Q3"));
            Objective o3 = objectives.save(new Objective("O-2026Q3-006", "KR-OTD", "提高项目准时交付率", delivery, 100, 35, 8, LocalDate.now().plusDays(38), Objective.Status.RELEASED, "2026-Q3"));
            Objective o4 = objectives.save(new Objective("O-2026Q2-015", "KR-PLAT", "完成统一技术平台一期", product, 100, 100, 2, LocalDate.now(), Objective.Status.COMPLETED, "2026-Q2"));

            initiatives.saveAll(List.of(
                new Initiative("INIT-PROD-011", "重点客户共创计划", product, Initiative.Status.RUNNING, 86),
                new Initiative("INIT-SALES-014", "制造行业方案标准化", sales, Initiative.Status.RUNNING, 74),
                new Initiative("INIT-DEL-027", "交付风险周例会", delivery, Initiative.Status.ALARM, 61),
                new Initiative("INIT-PROD-006", "研发效能指标治理", product, Initiative.Status.IDLE, 78)
            ));
            checkIns.saveAll(List.of(
                new CheckIn("CI-260801-032", o1, "周进展更新", 5, 0, CheckIn.Result.PASSED, "林知远"),
                new CheckIn("CI-260801-011", o2, "信心指数复核", 4, 1, CheckIn.Result.FAILED, "周妍"),
                new CheckIn("CI-260731-018", o4, "季度复盘", 6, 0, CheckIn.Result.PASSED, "沈清和"),
                new CheckIn("CI-260802-003", o3, "风险确认", 3, 0, CheckIn.Result.PENDING, "陆承")
            ));
            String demo = encoder.encode("Demo@2026");
            users.saveAll(List.of(
                new UserAccount("operator", demo, "林知远", UserAccount.Role.CONTRIBUTOR, "DEPT-PROD"),
                new UserAccount("planner", demo, "沈清和", UserAccount.Role.OKR_MANAGER, null),
                new UserAccount("quality", demo, "周妍", UserAccount.Role.QUALITY, null),
                new UserAccount("admin", encoder.encode("ZhuaTech@2026"), "系统管理员", UserAccount.Role.ADMIN, null)
            ));
        };
    }
}
