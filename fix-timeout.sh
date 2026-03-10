#!/bin/bash

# DevTeam IDEA Plugin - 解决依赖下载超时问题

echo "=========================================="
echo "DevTeam IDEA Plugin - 配置国内镜像"
echo "=========================================="
echo ""

PROJECT_DIR="/root/.openclaw/workspace/devteam-idea-plugin"
cd "$PROJECT_DIR" || exit 1

echo "✅ 已配置以下优化："
echo ""
echo "1. build.gradle.kts - 使用阿里云Maven镜像"
echo "2. gradle-wrapper.properties - 使用腾讯云Gradle镜像"
echo "3. ~/.gradle/init.gradle - 全局Gradle配置"
echo "4. gradle.properties - 项目Gradle配置"
echo ""

echo "📊 配置详情："
echo ""
echo "Maven仓库："
echo "  - https://maven.aliyun.com/repository/public"
echo "  - https://maven.aliyun.com/repository/gradle-plugin"
echo "  - https://maven.aliyun.com/repository/spring"
echo "  - https://maven.aliyun.com/repository/google"
echo ""
echo "Gradle分发："
echo "  - https://mirrors.cloud.tencent.com/gradle/"
echo ""
echo "超时设置："
echo "  - 连接超时：60秒"
echo "  - Socket超时：60秒"
echo ""
echo "性能优化："
echo "  - 并行构建：启用"
echo "  - 构建缓存：启用"
echo "  - JVM内存：2GB"
echo ""

echo "=========================================="
echo "现在可以在IDEA中重新同步项目"
echo "=========================================="
echo ""
echo "步骤："
echo "1. 在IDEA中打开项目"
echo "2. 点击右上角的 🔄 (Reload Gradle Project)"
echo "3. 或者：File → Invalidate Caches → Invalidate and Restart"
echo ""
echo "如果还是超时，可以尝试："
echo "1. 关闭IDEA"
echo "2. 删除 ~/.gradle/caches"
echo "3. 重新打开IDEA"
echo ""

# 测试Gradle配置
echo "🧪 测试Gradle配置..."
echo ""
./gradlew --version

if [ $? -eq 0 ]; then
    echo ""
    echo "✅ Gradle配置正常！"
else
    echo ""
    echo "⚠️  Gradle配置可能有问题，请检查"
fi

echo ""
echo "=========================================="
echo "完成！"
echo "=========================================="
