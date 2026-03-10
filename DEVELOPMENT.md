# DevTeam IntelliJ IDEA Plugin - 开发文档

## 项目概述

DevTeam IntelliJ IDEA插件是一个将DevTeam CLI集成到IntelliJ IDEA的插件，让开发者在IDE内直接使用AI开发团队。

## 技术架构

### 核心组件

```
devteam-idea-plugin/
├── actions/              # 用户操作
│   ├── QuickDevAction    # 快速开发
│   ├── ParallelDevAction # 并行开发
│   ├── IterateAction     # 迭代优化
│   ├── RefineAction      # UI优化
│   └── OpenSettingsAction# 打开设置
├── services/             # 后台服务
│   ├── DevTeamService    # DevTeam CLI调用
│   └── ConfigService     # 配置管理
├── ui/                   # 用户界面
│   ├── DevDialog         # 开发对话框
│   ├── DevTeamToolWindow # 工具窗口
│   ├── ProgressPanel     # 进度面板
│   └── DevTeamToolWindowFactory
├── settings/             # 设置页面
│   ├── DevTeamSettingsConfigurable
│   └── DevTeamSettingsPanel
└── utils/                # 工具类
    └── NodeChecker       # Node.js检查
```

### 数据流

```
用户操作（Action）
    ↓
开发对话框（DevDialog）
    ↓
DevTeam服务（DevTeamService）
    ↓
调用CLI（Process）
    ↓
实时输出（ToolWindow）
    ↓
完成通知
```

## 核心类说明

### 1. DevTeamService

**职责：** 调用DevTeam CLI并处理输出

**核心方法：**
```java
// 执行DevTeam命令
void execute(String command, String requirement, Consumer<String> outputCallback)

// 停止当前执行
void stop()

// 检查DevTeam CLI是否安装
boolean isDevTeamInstalled()

// 获取DevTeam CLI版本
String getDevTeamVersion()
```

**实现细节：**
- 使用ProcessBuilder创建子进程
- 重定向标准输出和错误输出
- 实时读取输出并回调
- 支持停止执行

### 2. ConfigService

**职责：** 管理插件配置

**配置项：**
- API Key
- API URL
- Model
- Max Tokens
- Temperature
- Workspace Root
- Enable Cache
- Auto Fix
- Validate

**持久化：**
- 使用IntelliJ Platform的PersistentStateComponent
- 配置保存在项目的.idea/devteam.xml

### 3. DevDialog

**职责：** 显示开发对话框，收集用户输入

**功能：**
- 需求输入（多行文本框）
- 选项配置（复选框）
- 系统检查（Node.js/npm）
- 执行按钮

**交互流程：**
1. 显示对话框
2. 用户输入需求
3. 用户选择选项
4. 点击确定
5. 调用DevTeamService
6. 显示ToolWindow
7. 实时输出日志

### 4. DevTeamToolWindow

**职责：** 显示实时日志和进度

**组件：**
- 标题栏
- 进度面板（ProgressPanel）
- 日志区域（JTextArea）
- 工具栏（清除、停止按钮）

**功能：**
- 实时追加日志
- 显示进度条
- 显示当前Agent
- 清除日志
- 停止执行

### 5. ProgressPanel

**职责：** 显示执行进度

**组件：**
- 状态标签（Ready/Running/Completed）
- Agent标签（当前执行的Agent）
- 进度条（0-100%）

**状态：**
- Ready - 准备就绪
- Running - 正在执行
- Completed - 执行完成
- Failed - 执行失败

### 6. NodeChecker

**职责：** 检查系统环境

**检查项：**
- Node.js是否安装
- Node.js版本
- npm是否安装
- npm版本

**返回：**
- SystemRequirements对象
- isValid() - 是否满足要求
- getMessage() - 提示信息

## 开发指南

### 环境要求

- IntelliJ IDEA 2023.2+
- JDK 17+
- Gradle 8.0+
- Node.js 18+（运行时）

### 构建项目

```bash
# 克隆项目
git clone https://github.com/kaijingWang/devteam-idea-plugin.git
cd devteam-idea-plugin

# 构建插件
./gradlew buildPlugin

# 输出：build/distributions/devteam-idea-plugin-3.0.0.zip
```

### 运行测试

```bash
# 在IDE中运行
./gradlew runIde

# 这会启动一个新的IntelliJ IDEA实例，插件已安装
```

### 调试

1. 在IntelliJ IDEA中打开项目
2. 运行Gradle任务：`runIde`
3. 在新启动的IDE中测试插件
4. 在原IDE中设置断点调试

### 发布

```bash
# 构建发布版本
./gradlew buildPlugin

# 签名插件（需要证书）
./gradlew signPlugin

# 发布到Marketplace（需要token）
./gradlew publishPlugin
```

## 扩展开发

### 添加新的Action

1. 创建Action类：
```java
public class MyAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        // 实现逻辑
    }
}
```

2. 在plugin.xml中注册：
```xml
<action 
    id="DevTeam.MyAction" 
    class="com.devteam.idea.actions.MyAction"
    text="My Action"
    description="My action description"/>
```

### 添加新的配置项

1. 在ConfigService.State中添加字段：
```java
public class State {
    public String myConfig = "default";
}
```

2. 添加getter/setter：
```java
public String getMyConfig() { return state.myConfig; }
public void setMyConfig(String value) { state.myConfig = value; }
```

3. 在DevTeamSettingsPanel中添加UI组件

### 添加新的工具类

1. 在utils包中创建类
2. 实现静态方法
3. 在需要的地方调用

## 测试指南

### 单元测试

```java
@Test
public void testDevTeamService() {
    DevTeamService service = new DevTeamService(project);
    assertTrue(service.isDevTeamInstalled());
}
```

### 集成测试

1. 启动测试IDE
2. 创建测试项目
3. 右键选择DevTeam → Quick Development
4. 输入测试需求
5. 验证输出

### 性能测试

- 启动时间：< 1秒
- 响应时间：< 100ms
- 内存占用：< 100MB
- CPU占用：< 5%

## 常见问题

### Q: 插件无法加载？

A: 检查：
1. IntelliJ IDEA版本是否 >= 2023.2
2. JDK版本是否 >= 17
3. plugin.xml配置是否正确

### Q: DevTeam CLI未找到？

A: 检查：
1. Node.js是否安装
2. DevTeam CLI是否全局安装
3. PATH环境变量是否正确

### Q: 执行失败？

A: 检查：
1. API Key是否配置
2. 网络是否正常
3. DevTeam CLI版本是否 >= 3.0

### Q: 日志不显示？

A: 检查：
1. ToolWindow是否打开
2. 输出回调是否正确
3. SwingUtilities.invokeLater是否使用

## 贡献指南

### 提交代码

1. Fork项目
2. 创建分支：`git checkout -b feature/my-feature`
3. 提交代码：`git commit -am 'Add my feature'`
4. 推送分支：`git push origin feature/my-feature`
5. 创建Pull Request

### 代码规范

- 使用Java 17特性
- 遵循IntelliJ Platform SDK规范
- 添加JavaDoc注释
- 编写单元测试
- 保持代码简洁

### 提交信息

格式：`<type>: <subject>`

类型：
- feat: 新功能
- fix: 修复Bug
- docs: 文档
- style: 格式
- refactor: 重构
- test: 测试
- chore: 构建

示例：
```
feat: 添加进度面板
fix: 修复日志不显示的问题
docs: 更新开发文档
```

## 路线图

### v3.0.0（当前版本）
- [x] 基础框架
- [x] 5个Action
- [x] Tool Window
- [x] 设置页面
- [x] 进度面板
- [x] 系统检查

### v3.1.0（计划中）
- [ ] 文件自动打开
- [ ] 语法高亮
- [ ] 代码导航
- [ ] 错误提示
- [ ] 重试机制

### v3.2.0（计划中）
- [ ] 历史记录
- [ ] 模板管理
- [ ] 快捷键自定义
- [ ] 主题支持

### v4.0.0（计划中）
- [ ] 多项目支持
- [ ] 团队协作
- [ ] 云端同步
- [ ] Web界面

## 许可证

MIT License

## 联系方式

- 作者：王凯景
- 邮箱：wkj11250412@outlook.com
- GitHub：https://github.com/kaijingWang/devteam-idea-plugin
- 问题反馈：https://github.com/kaijingWang/devteam-idea-plugin/issues

---

**最后更新：** 2026-03-10
**版本：** 3.0.0
