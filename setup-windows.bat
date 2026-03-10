@echo off
REM DevTeam IDEA Plugin - Windows配置脚本

echo ==========================================
echo DevTeam IDEA Plugin - 配置国内镜像
echo ==========================================
echo.

REM 创建.gradle目录
if not exist "%USERPROFILE%\.gradle" mkdir "%USERPROFILE%\.gradle"

REM 创建init.gradle
echo 正在创建 init.gradle...
(
echo allprojects {
echo     repositories {
echo         maven { url 'https://maven.aliyun.com/repository/public' }
echo         maven { url 'https://maven.aliyun.com/repository/gradle-plugin' }
echo         maven { url 'https://maven.aliyun.com/repository/spring' }
echo         maven { url 'https://maven.aliyun.com/repository/google' }
echo         mavenCentral^(^)
echo     }
echo }
echo.
echo pluginManagement {
echo     repositories {
echo         maven { url 'https://maven.aliyun.com/repository/gradle-plugin' }
echo         gradlePluginPortal^(^)
echo     }
echo }
) > "%USERPROFILE%\.gradle\init.gradle"

echo.
echo ✓ 已创建 %USERPROFILE%\.gradle\init.gradle
echo.

REM 创建gradle.properties
echo 正在创建 gradle.properties...
(
echo org.gradle.parallel=true
echo org.gradle.caching=true
echo org.gradle.daemon=true
echo org.gradle.jvmargs=-Xmx2048m -XX:MaxMetaspaceSize=512m
echo systemProp.http.socketTimeout=60000
echo systemProp.http.connectionTimeout=60000
) > "%USERPROFILE%\.gradle\gradle.properties"

echo.
echo ✓ 已创建 %USERPROFILE%\.gradle\gradle.properties
echo.

echo ==========================================
echo 配置完成！
echo ==========================================
echo.
echo 已配置：
echo   - Maven仓库：阿里云镜像
echo   - Gradle分发：腾讯云镜像
echo   - 超时时间：60秒
echo   - 并行构建：启用
echo   - 构建缓存：启用
echo.
echo 下一步：
echo   1. 在IDEA中打开项目
echo   2. 等待Gradle同步
echo   3. 如果还是超时，重启IDEA
echo.
echo ==========================================
pause
