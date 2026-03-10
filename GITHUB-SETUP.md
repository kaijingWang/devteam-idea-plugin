# 创建GitHub仓库并推送

## 步骤

### 1. 在GitHub上创建仓库

访问：https://github.com/new

填写信息：
- Repository name: `devteam-idea-plugin`
- Description: `DevTeam IntelliJ IDEA Plugin - AI-Powered Development Team in Your IDE`
- Public/Private: 选择 Public
- 不要勾选 "Initialize this repository with a README"

点击 "Create repository"

### 2. 推送代码

```bash
cd /root/.openclaw/workspace/devteam-idea-plugin

# 添加远程仓库
git remote add origin https://github.com/kaijingWang/devteam-idea-plugin.git

# 推送代码
git push -u origin main
```

### 3. 验证

访问：https://github.com/kaijingWang/devteam-idea-plugin

应该能看到：
- ✅ 18个文件
- ✅ 2个提交
- ✅ README.md
- ✅ 完整的项目结构

## 完成！

现在你的IntelliJ IDEA插件项目已经：
- ✅ 创建完成
- ✅ 功能完善
- ✅ 文档齐全
- ✅ 准备推送到GitHub

下一步可以：
1. 推送到GitHub
2. 构建插件：`./gradlew buildPlugin`
3. 测试插件：`./gradlew runIde`
4. 发布到Marketplace
