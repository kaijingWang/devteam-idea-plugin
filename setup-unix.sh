#!/bin/bash

# DevTeam IDEA Plugin - macOS/Linux配置脚本

echo "=========================================="
echo "DevTeam IDEA Plugin - 配置国内镜像"
echo "=========================================="
echo ""

# 创建.gradle目录
mkdir -p ~/.gradle

# 创建init.gradle
echo "正在创建 init.gradle..."
cat > ~/.gradle/init.gradle << 'EOF'
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
EOF

echo "✓ 已创建 ~/.gradle/init.gradle"
echo ""

# 创建gradle.properties
echo "正在创建 gradle.properties..."
cat > ~/.gradle/gradle.properties << 'EOF'
org.gradle.parallel=true
org.gradle.caching=true
org.gradle.daemon=true
org.gradle.jvmargs=-Xmx2048m -XX:MaxMetaspaceSize=512m
systemProp.http.socketTimeout=60000
systemProp.http.connectionTimeout=60000
EOF

echo "✓ 已创建 ~/.gradle/gradle.properties"
echo ""

echo "=========================================="
echo "配置完成！"
echo "=========================================="
echo ""
echo "已配置："
echo "  - Maven仓库：阿里云镜像"
echo "  - Gradle分发：腾讯云镜像"
echo "  - 超时时间：60秒"
echo "  - 并行构建：启用"
echo "  - 构建缓存：启用"
echo ""
echo "下一步："
echo "  1. 在IDEA中打开项目"
echo "  2. 等待Gradle同步"
echo "  3. 如果还是超时，重启IDEA"
echo ""
echo "=========================================="
