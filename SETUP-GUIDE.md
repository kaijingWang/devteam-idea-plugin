# 在任何电脑上配置DevTeam IDEA插件

## 问题：依赖下载超时

如果你在其他电脑上克隆项目后，IDEA下载依赖超时，按照以下步骤配置。

## 解决方案

### 方式1：使用项目自带配置（推荐）

项目已经配置好了国内镜像，只需要：

1. **克隆项目**
```bash
git clone https://github.com/kaijingWang/devteam-idea-plugin.git
cd devteam-idea-plugin
```

2. **在IDEA中打开**
   - File → Open
   - 选择项目目录
   - 等待Gradle同步

3. **如果还是超时**
   - 点击右上角 🔄 (Reload Gradle Project)
   - 或 File → Invalidate Caches → Invalidate and Restart

### 方式2：手动配置全局Gradle镜像

如果方式1不行，手动配置全局Gradle：

#### Windows

1. **创建配置文件**
   - 位置：`C:\Users\你的用户名\.gradle\init.gradle`
   - 如果没有 `.gradle` 文件夹，手动创建

2. **添加以下内容**
```groovy
allprojects {
    repositories {
        maven { url 'https://maven.aliyun.com/repository/public' }
        maven { url 'https://maven.aliyun.com/repository/gradle-plugin' }
        maven { url 'https://maven.aliyun.com/repository/spring' }
        maven { url 'https://maven.aliyun.com/repository/google' }
        mavenCentral()
    }
}

pluginManagement {
    repositories {
        maven { url 'https://maven.aliyun.com/repository/gradle-plugin' }
        gradlePluginPortal()
    }
}
```

3. **重启IDEA**

#### macOS/Linux

1. **创建配置文件**
```bash
mkdir -p ~/.gradle
nano ~/.gradle/init.gradle
```

2. **添加以下内容**
```groovy
allprojects {
    repositories {
        maven { url 'https://maven.aliyun.com/repository/public' }
        maven { url 'https://maven.aliyun.com/repository/gradle-plugin' }
        maven { url 'https://maven.aliyun.com/repository/spring' }
        maven { url 'https://maven.aliyun.com/repository/google' }
        mavenCentral()
    }
}

pluginManagement {
    repositories {
        maven { url 'https://maven.aliyun.com/repository/gradle-plugin' }
        gradlePluginPortal()
    }
}
```

3. **保存并重启IDEA**

### 方式3：在IDEA中配置代理（如果有VPN）

1. **打开设置**
   - File → Settings (Windows/Linux)
   - IntelliJ IDEA → Preferences (macOS)

2. **配置HTTP代理**
   - Appearance & Behavior → System Settings → HTTP Proxy
   - 选择 "Manual proxy configuration"
   - 输入代理地址和端口

3. **应用并重启**

### 方式4：离线安装依赖（最后手段）

如果网络实在太慢，可以：

1. **在网络好的电脑上下载依赖**
```bash
cd devteam-idea-plugin
./gradlew build
```

2. **打包Gradle缓存**
```bash
# Windows
tar -czf gradle-cache.tar.gz %USERPROFILE%\.gradle\caches

# macOS/Linux
tar -czf gradle-cache.tar.gz ~/.gradle/caches
```

3. **复制到目标电脑**
   - 将 `gradle-cache.tar.gz` 复制到目标电脑
   - 解压到 `~/.gradle/` 目录

4. **在IDEA中打开项目**
   - 依赖已经在本地，不需要下载

## 验证配置

### 测试Gradle配置

```bash
cd devteam-idea-plugin
./gradlew --version
```

应该能看到Gradle版本信息。

### 测试依赖下载

```bash
./gradlew dependencies
```

应该能看到依赖树，且下载速度很快。

## 常见问题

### Q1: 还是超时怎么办？

**A:** 检查网络：
```bash
# 测试阿里云镜像
curl -I https://maven.aliyun.com/repository/public

# 测试腾讯云镜像
curl -I https://mirrors.cloud.tencent.com/gradle/
```

如果无法访问，可能是网络问题。

### Q2: IDEA一直卡在"Resolving dependencies"？

**A:** 
1. 关闭IDEA
2. 删除项目的 `.gradle` 文件夹
3. 删除 `~/.gradle/caches`
4. 重新打开IDEA

### Q3: 提示"Could not resolve all dependencies"？

**A:** 
1. 检查 `build.gradle.kts` 中的仓库配置
2. 确保 `~/.gradle/init.gradle` 存在
3. 尝试：`./gradlew build --refresh-dependencies`

### Q4: 在公司内网怎么办？

**A:** 
1. 询问公司是否有内部Maven仓库
2. 在 `build.gradle.kts` 中添加公司仓库
3. 或使用公司提供的代理

## 推荐配置（最佳实践）

### 1. 全局Gradle配置

创建 `~/.gradle/gradle.properties`:

```properties
# 启用并行构建
org.gradle.parallel=true

# 启用构建缓存
org.gradle.caching=true

# 增加JVM内存
org.gradle.jvmargs=-Xmx2048m -XX:MaxMetaspaceSize=512m

# 增加超时时间
systemProp.http.socketTimeout=60000
systemProp.http.connectionTimeout=60000

# 启用守护进程
org.gradle.daemon=true
```

### 2. IDEA配置

**增加IDEA内存：**
1. Help → Edit Custom VM Options
2. 添加：
```
-Xmx2048m
-XX:MaxMetaspaceSize=512m
```

**增加Gradle内存：**
1. Settings → Build, Execution, Deployment → Build Tools → Gradle
2. Gradle JVM: 选择 JDK 17+
3. VM options: `-Xmx2048m`

## 国内镜像列表

### Maven仓库
- 阿里云：https://maven.aliyun.com/repository/public
- 腾讯云：https://mirrors.cloud.tencent.com/maven/
- 华为云：https://repo.huaweicloud.com/repository/maven/

### Gradle分发
- 腾讯云：https://mirrors.cloud.tencent.com/gradle/
- 阿里云：https://mirrors.aliyun.com/gradle/

### 选择建议
- **阿里云**：速度快，稳定性好（推荐）
- **腾讯云**：速度快，适合南方地区
- **华为云**：适合企业用户

## 总结

**最简单的方法：**
1. 克隆项目（已配置好镜像）
2. 在IDEA中打开
3. 等待同步完成

**如果超时：**
1. 创建 `~/.gradle/init.gradle`
2. 添加阿里云镜像配置
3. 重启IDEA

**如果还不行：**
1. 检查网络
2. 使用代理
3. 或离线安装依赖

---

**提示：** 项目已经配置好了所有优化，大多数情况下直接打开就能用。
