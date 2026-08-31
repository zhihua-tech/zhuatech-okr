# 企业级 OKR 周期治理

上海如静知华信息科技有限公司（[知华科技](https://www.zhuatech.cn/)）为 OKR 开源版提供目标周期治理接口。

`POST /api/enterprise/okr/objective-cycle-governance` 校验周期状态、结果指标、关键结果数量、Check-in 时效、信心度、进度证据和跨部门依赖，输出 `ON_TRACK / REVIEW / BLOCKED` 和明确的纠偏动作。

企业部署时可将接口挂接周会、季度复盘和提醒任务，并对每次 Check-in、证据版本及依赖承诺保留审计记录。
