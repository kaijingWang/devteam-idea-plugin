# DevTeam IntelliJ IDEA Plugin - 本地安装指南

## 方式1：直接在IntelliJ IDEA中开发（推荐）

这是最简单的方式，不需要构建插件。

### 步骤

1. **打开IntelliJ IDEA**

2. **打开项目**
   - File → Open
   - 选择：`/root/.openclaw/workspace/devteam-idea-plugin`
   - 点击 OK

3. **等待Gradle同步**
   - IDEA会自动检测到这是一个Gradle项目
   - 等待依赖下载完成（首次可能需要5-10分钟）

4. **运行插件**
   - 在右侧Gradle面板中找到：`Tasks` → `intellij` → `runIde`
   - 双击运行
   - 会启动一个新的IDEA实例，插件已安装

5. **测试插件**
   - 在新启动的IDEA中创建或打开一个项目
   - 右键项目 → 应该能看到 "DevTeam" 菜单
   - 点击测试功能

## 方式2：构建插件zip文件

如果你想构建插件文件并安装到其他IDEA实例。

### 前置要求

1. **安装JDK 17+**
```bash
# Ubuntu/Debian
sudo apt update
sudo apt install -y openjdk-17-jdk

# 验证安装
java -version
```

2. **安装Gradle（可选，项目会自动下载）**
```bash
# 使用项目自带的gradlew即可
cd /root/.openclaw/workspace/devteam-idea-plugin
chmod +x gradlew
```

### 构建步骤

```bash
cd /root/.openclaw/workspace/devteam-idea-plugin

# 构建插件
./gradlew buildPlugin

# 输出位置
# build/distributions/devteam-idea-plugin-3.0.0.zip
```

### 安装步骤

1. 打开IntelliJ IDEA
2. Settings → Plugins
3. 点击 ⚙️ → Install Plugin from Disk...
4. 选择 `build/distributions/devteam-idea-plugin-3.0.0.zip`
5. 重启IDEA

## 方式3：使用现有的IntelliJ IDEA（最简单）

如果你已经安装了IntelliJ IDEA：

### 步骤

1. **打开IDEA**

2. **导入项目**
   - File → Open
   - 选择项目目录
   - 选择 "Import project from external model" → Gradle
   - 点击 Finish

3. **配置Gradle**
   - IDEA会自动配置Gradle
   - 等待依赖下载

4. **运行测试**
   - 右键 `build.gradle.kts`
   - 选择 "Run 'gradle runIde'"
   - 或在Gradle面板中双击 `runIde`

## 常见问题

### Q: Gradle同步失败？

**解决：**
1. 检查网络连接
2. 检查JDK版本（需要17+）
3. 删除 `.gradle` 目录重试
4. 使用国内镜像（修改 `build.gradle.kts`）

### Q: JDK版本不对？

**解决：**
```bash
# 查看当前JDK
java -version

# 安装JDK 17
sudo apt install openjdk-17-jdk

# 设置JAVA_HOME
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
export PATH=$JAVA_HOME/bin:$PATH
```

### Q: 插件无法加载？

**解决：**
1. 检查IDEA版本（需要2023.2+）
2. 检查plugin.xml配置
3. 查看IDEA日志：Help → Show Log in Explorer

### Q: 依赖下载慢？

**解决：**

修改 `build.gradle.kts`，添加国内镜像：

```kotlin
repositories {
    maven { url = uri("https://maven.aliyun.com/repository/public") }
    maven { url = uri("https://maven.aliyun.com/repository/gradle-plugin") }
    mavenCentral()
}
```

## 快速测试（不构建）

如果只是想测试代码，不需要构建插件：

```bash
cd /root/.openclaw/workspace/devteam-idea-plugin

# 直接运行
./gradlew runIde
```

这会：
1. 自动下载依赖
2. 编译代码
3. 启动测试IDEA实例
4. 插件已安装并激活

## 开发调试

### 设置断点

1. 在IDEA中打开插件项目
2. 在代码中设置断点
3. 运行 `runIde`（Debug模式）
4. 在测试IDEA中触发功能
5. 断点会在开发IDEA中命中

### 查看日志

测试IDEA的日志位置：
- Linux: `~/.cache/JetBrains/IdeaIC<version>/log/idea.log`
- Mac: `~/Library/Logs/JetBrains/IdeaIC<version>/idea.log`
- Windows: `%USERPROFILE%\AppData\Local\JetBrains\IdeaIC<version>\log\idea.log`

### 热重载

修改代码后：
1. 停止测试IDEA
2. 重新运行 `runIde`
3. 新代码会生效

## 推荐工作流

**开发阶段：**
1. 在IDEA中打开项目
2. 修改代码
3. 运行 `runIde` 测试
4. 重复2-3

**发布阶段：**
1. 运行 `buildPlugin` 构建
2. 测试zip文件
3. 提交到Marketplace

## 下一步

- [ ] 完善功能
- [ ] 添加测试
- [ ] 优化UI
- [ ] 编写文档
- [ ] 发布到Marketplace

---

**提示：** 如果你只是想快速测试，直接在IDEA中打开项目并运行 `runIde` 即可，不需要手动构建。
