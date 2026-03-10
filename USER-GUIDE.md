# DevTeam IntelliJ IDEA Plugin - 用户手册

## 目录

1. [快速开始](#快速开始)
2. [功能介绍](#功能介绍)
3. [使用指南](#使用指南)
4. [配置说明](#配置说明)
5. [常见问题](#常见问题)
6. [最佳实践](#最佳实践)

## 快速开始

### 安装插件

**方式1：从Marketplace安装（推荐）**

1. 打开IntelliJ IDEA
2. 进入 `Settings` → `Plugins`
3. 搜索 "DevTeam"
4. 点击 `Install`
5. 重启IDE

**方式2：从文件安装**

1. 下载插件zip文件
2. 进入 `Settings` → `Plugins` → ⚙️ → `Install Plugin from Disk...`
3. 选择zip文件
4. 重启IDE

### 配置API

1. 进入 `Settings` → `Tools` → `DevTeam`
2. 输入Claude API Key
3. 点击 `Apply`

### 第一次使用

1. 右键点击项目或文件夹
2. 选择 `DevTeam` → `🚀 Quick Development`
3. 输入需求：`开发一个待办事项应用`
4. 点击 `Start Development`
5. 在底部的DevTeam工具窗口查看进度

## 功能介绍

### 1. 快速开发（Quick Development）

**快捷键：** `Ctrl+Alt+D` (Windows/Linux) 或 `Cmd+Alt+D` (Mac)

**特点：**
- 并行执行（3-5x加速）
- 自动修复常见问题
- 代码质量验证
- 最快速度

**适用场景：**
- 快速原型开发
- 功能验证
- 学习新技术

**使用步骤：**
1. 右键项目 → DevTeam → Quick Development
2. 输入需求
3. 等待完成

### 2. 并行开发（Parallel Development）

**特点：**
- 智能并行执行
- 依赖分析
- 3-5x加速
- 实时进度显示

**适用场景：**
- 中大型项目
- 需要快速交付
- 多模块项目

**执行分组：**
- 第1组：PM Agent
- 第2组：Architect + UI Designer（并行）
- 第3组：API Designer
- 第4组：Backend + Frontend（并行）
- 第5组：QA
- 第6组：Git

### 3. 迭代优化（Iterate Optimization）

**特点：**
- 自动修复Bug
- 持续迭代
- 质量保证
- 最高质量

**适用场景：**
- 生产环境项目
- 高质量要求
- 复杂业务逻辑

**完成标准：**
- ✅ 所有测试通过
- ✅ 构建成功
- ✅ 无TypeScript错误
- ✅ 无ESLint错误

### 4. UI优化（UI Refinement）

**特点：**
- 交互式优化
- 用户反馈驱动
- 持续改进
- 精雕细琢

**适用场景：**
- UI设计优化
- 用户体验改进
- 视觉效果调整

**工作流程：**
1. 生成初始UI
2. 用户提出改进意见
3. AI优化UI
4. 重复2-3直到满意

## 使用指南

### 开发新功能

**场景：** 开发一个用户登录功能

**步骤：**

1. **右键项目**
   - 在项目树中右键点击项目根目录
   - 选择 `DevTeam` → `🚀 Quick Development`

2. **输入需求**
   ```
   开发用户登录功能，支持：
   - 用户名密码登录
   - 手机号验证码登录
   - 记住密码
   - 忘记密码
   使用React + TypeScript + Tailwind CSS
   ```

3. **配置选项**
   - ☑ Auto fix common issues
   - ☑ Validate code quality
   - ☑ Generate tests

4. **开始开发**
   - 点击 `Start Development`
   - 在DevTeam工具窗口查看进度

5. **查看结果**
   - 生成的文件会出现在项目中
   - 自动打开主要文件
   - 查看文档和代码

### 优化现有项目

**场景：** 优化现有项目的UI

**步骤：**

1. **选择UI优化**
   - 右键项目 → DevTeam → 🎨 UI Refinement

2. **输入改进意见**
   ```
   优化登录页面：
   - 使用更现代的设计风格
   - 添加动画效果
   - 改进响应式布局
   - 提升无障碍性
   ```

3. **查看预览**
   - AI生成优化方案
   - 查看设计稿
   - 查看代码变更

4. **应用或继续优化**
   - 满意：应用变更
   - 不满意：继续提出改进意见

### 修复Bug

**场景：** 自动修复项目中的Bug

**步骤：**

1. **选择迭代优化**
   - 右键项目 → DevTeam → 🔄 Iterate Optimization

2. **配置选项**
   - Max iterations: 10
   - ☑ Auto fix
   - ☑ Validate

3. **开始迭代**
   - AI自动分析问题
   - 分配给相应的Agent
   - 自动修复
   - 再次验证

4. **查看结果**
   - 查看修复报告
   - 查看代码变更
   - 运行测试

## 配置说明

### LLM配置

**API Key**
- 获取：https://console.anthropic.com
- 格式：`sk-ant-xxx`
- 权限：需要API访问权限

**Model**
- 推荐：`claude-sonnet-4-6`
- 其他：`claude-3-5-sonnet-20241022`
- 选择：根据需求和预算

**Max Tokens**
- 默认：8192
- 范围：1024-100000
- 说明：单次生成的最大token数

**Temperature**
- 默认：0.7
- 范围：0.0-1.0
- 说明：0=确定性，1=创造性

### 工作空间配置

**Root Directory**
- 默认：`./devteam-workspace`
- 说明：生成代码的根目录
- 建议：使用相对路径

**Docs Directory**
- 默认：`docs`
- 说明：文档目录
- 包含：PRD、架构、API文档

**Src Directory**
- 默认：`src`
- 说明：源代码目录
- 包含：前端、后端代码

**Tests Directory**
- 默认：`tests`
- 说明：测试代码目录
- 包含：单元测试、集成测试

### 选项配置

**Enable Cache**
- 默认：开启
- 说明：缓存API响应
- 效果：减少API调用，提升速度

**Auto Fix**
- 默认：开启
- 说明：自动修复常见问题
- 包含：文件命名、配置文件

**Validate**
- 默认：开启
- 说明：验证代码质量
- 包含：TypeScript、ESLint、构建

## 常见问题

### Q1: 插件无法启动？

**原因：**
- Node.js未安装
- DevTeam CLI未安装
- 版本不兼容

**解决：**
```bash
# 1. 安装Node.js 18+
# 下载：https://nodejs.org

# 2. 安装DevTeam CLI
git clone https://github.com/kaijingWang/DevTeam.git
cd DevTeam
npm install
npm link

# 3. 验证安装
devteam --version
```

### Q2: API调用失败？

**原因：**
- API Key错误
- 网络问题
- 配额不足

**解决：**
1. 检查API Key是否正确
2. 检查网络连接
3. 检查API配额
4. 查看错误日志

### Q3: 生成的代码有错误？

**原因：**
- Agent提示词问题
- 需求描述不清
- 技术栈不支持

**解决：**
1. 使用迭代优化模式
2. 详细描述需求
3. 指定技术栈
4. 手动修复后反馈

### Q4: 执行速度慢？

**原因：**
- 网络延迟
- API响应慢
- 缓存未启用

**解决：**
1. 使用并行模式
2. 启用缓存
3. 使用更快的模型
4. 优化网络

### Q5: 工具窗口不显示？

**原因：**
- 窗口被隐藏
- 插件未正确加载

**解决：**
1. 点击底部的DevTeam标签
2. 或 View → Tool Windows → DevTeam
3. 重启IDE

## 最佳实践

### 1. 需求描述

**好的需求：**
```
开发一个待办事项应用，包含以下功能：

核心功能：
- 添加、编辑、删除待办事项
- 标记完成/未完成
- 设置优先级（高/中/低）
- 设置截止日期

技术要求：
- 使用React + TypeScript
- 使用Tailwind CSS
- 使用localStorage存储
- 响应式设计

UI要求：
- 简洁现代的设计
- 支持深色模式
- 流畅的动画效果
```

**不好的需求：**
```
做一个todo应用
```

### 2. 选择模式

**快速原型：** Quick Development
- 速度最快
- 适合验证想法

**正式项目：** Iterate Optimization
- 质量最高
- 适合生产环境

**UI优化：** UI Refinement
- 交互式优化
- 适合精雕细琢

### 3. 配置优化

**开发环境：**
- Temperature: 0.7（平衡）
- Max Tokens: 8192
- Enable Cache: 开启

**生产环境：**
- Temperature: 0.3（更确定）
- Max Tokens: 4096
- Validate: 开启

### 4. 工作流程

**推荐流程：**
1. 使用Quick Development生成初始版本
2. 使用Iterate Optimization修复问题
3. 使用UI Refinement优化界面
4. 手动测试和调整
5. 提交代码

### 5. 团队协作

**建议：**
- 统一配置（API Key、Model）
- 统一工作空间结构
- 代码审查
- 文档同步

## 快捷键

| 功能 | Windows/Linux | Mac |
|------|---------------|-----|
| Quick Development | Ctrl+Alt+D | Cmd+Alt+D |
| Parallel Development | Ctrl+Alt+P | Cmd+Alt+P |
| Iterate Optimization | Ctrl+Alt+I | Cmd+Alt+I |
| UI Refinement | Ctrl+Alt+R | Cmd+Alt+R |
| Open Settings | Ctrl+Alt+, | Cmd+Alt+, |

## 支持

### 获取帮助

- 文档：https://github.com/kaijingWang/devteam-idea-plugin
- 问题：https://github.com/kaijingWang/devteam-idea-plugin/issues
- 邮箱：wkj11250412@outlook.com

### 反馈建议

欢迎提交：
- Bug报告
- 功能建议
- 使用体验
- 文档改进

---

**版本：** 3.0.0  
**更新：** 2026-03-10
