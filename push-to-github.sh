#!/bin/bash

# DevTeam IDEA Plugin - GitHub推送脚本

echo "=========================================="
echo "DevTeam IDEA Plugin - GitHub推送"
echo "=========================================="
echo ""

# 项目目录
PROJECT_DIR="/root/.openclaw/workspace/devteam-idea-plugin"
cd "$PROJECT_DIR" || exit 1

# 检查Git状态
echo "📊 检查Git状态..."
git status
echo ""

# 显示提交历史
echo "📝 提交历史："
git log --oneline -5
echo ""

# 推送选项
echo "请选择推送方式："
echo ""
echo "1. 使用GitHub Personal Access Token（推荐）"
echo "2. 使用SSH密钥"
echo "3. 生成推送命令（手动执行）"
echo ""
read -p "请输入选项 (1-3): " choice

case $choice in
  1)
    echo ""
    echo "📌 使用Personal Access Token推送"
    echo ""
    echo "步骤："
    echo "1. 访问：https://github.com/settings/tokens"
    echo "2. 点击 'Generate new token (classic)'"
    echo "3. 勾选 'repo' 权限"
    echo "4. 生成并复制token"
    echo ""
    read -p "请输入你的GitHub用户名: " username
    read -sp "请输入你的Personal Access Token: " token
    echo ""
    echo ""
    
    # 设置远程仓库URL（包含token）
    git remote set-url origin "https://${username}:${token}@github.com/kaijingWang/devteam-idea-plugin.git"
    
    # 推送
    echo "🚀 开始推送..."
    git push -u origin main
    
    if [ $? -eq 0 ]; then
      echo ""
      echo "✅ 推送成功！"
      echo ""
      echo "仓库地址：https://github.com/kaijingWang/devteam-idea-plugin"
    else
      echo ""
      echo "❌ 推送失败，请检查token是否正确"
    fi
    ;;
    
  2)
    echo ""
    echo "📌 使用SSH密钥推送"
    echo ""
    
    # 检查SSH密钥
    if [ ! -f ~/.ssh/id_rsa ]; then
      echo "⚠️  未找到SSH密钥，正在生成..."
      ssh-keygen -t rsa -b 4096 -C "wkj11250412@outlook.com" -f ~/.ssh/id_rsa -N ""
      echo ""
      echo "✅ SSH密钥已生成"
    fi
    
    echo "📋 你的SSH公钥："
    echo ""
    cat ~/.ssh/id_rsa.pub
    echo ""
    echo "请将上面的公钥添加到GitHub："
    echo "1. 访问：https://github.com/settings/keys"
    echo "2. 点击 'New SSH key'"
    echo "3. 粘贴上面的公钥"
    echo "4. 点击 'Add SSH key'"
    echo ""
    read -p "添加完成后按Enter继续..."
    
    # 添加GitHub到known_hosts
    ssh-keyscan github.com >> ~/.ssh/known_hosts 2>/dev/null
    
    # 设置远程仓库URL（SSH）
    git remote set-url origin git@github.com:kaijingWang/devteam-idea-plugin.git
    
    # 推送
    echo ""
    echo "🚀 开始推送..."
    git push -u origin main
    
    if [ $? -eq 0 ]; then
      echo ""
      echo "✅ 推送成功！"
      echo ""
      echo "仓库地址：https://github.com/kaijingWang/devteam-idea-plugin"
    else
      echo ""
      echo "❌ 推送失败，请检查SSH密钥是否正确添加"
    fi
    ;;
    
  3)
    echo ""
    echo "📌 生成推送命令"
    echo ""
    echo "请手动执行以下命令："
    echo ""
    echo "# 方式1：使用Personal Access Token"
    echo "cd $PROJECT_DIR"
    echo "git remote set-url origin https://YOUR_USERNAME:YOUR_TOKEN@github.com/kaijingWang/devteam-idea-plugin.git"
    echo "git push -u origin main"
    echo ""
    echo "# 方式2：使用SSH"
    echo "cd $PROJECT_DIR"
    echo "git remote set-url origin git@github.com:kaijingWang/devteam-idea-plugin.git"
    echo "git push -u origin main"
    echo ""
    ;;
    
  *)
    echo ""
    echo "❌ 无效的选项"
    exit 1
    ;;
esac

echo ""
echo "=========================================="
echo "完成！"
echo "=========================================="
