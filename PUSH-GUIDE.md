# 推送到GitHub的简单方法

## 方法1：使用Personal Access Token（最简单）

### 步骤1：获取Token

1. 访问：https://github.com/settings/tokens
2. 点击 "Generate new token (classic)"
3. 勾选 `repo` 权限
4. 点击 "Generate token"
5. 复制生成的token（只显示一次！）

### 步骤2：推送

```bash
cd /root/.openclaw/workspace/devteam-idea-plugin

# 替换 YOUR_TOKEN 为你的token
git remote set-url origin https://kaijingWang:YOUR_TOKEN@github.com/kaijingWang/devteam-idea-plugin.git

# 推送
git push -u origin main
```

## 方法2：使用SSH密钥

### 你的SSH公钥

```
ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAACAQC8LDB/59i7TJDbvQ5MRrCVgtr6mzMuSKiYcA0ySdvlqLX677PKtlNdH3NIGp7TsMfxvLpSNefMT5ExJDAC4iwF2DyL/W7QUvPBbBxIdIOOlBBniE7s5IW5Rfb0PC6vovsHLboB51PkAdKld3NOacmjChljI3VowN8Ax4k0g7ToWbfH8fVbFbNW+qqIUGKWzg4/0odVU1wisfXkd7cmr7ZNk+ipter1mHGMeqlJnxAofJk0KKvgStyLcxuI8U0zQNz1qtDrKoW8n34T9pv5OS0h2vj4GCdmgd7VJt4mCoWDj6QfxB9NYPw8PoxGIupA3JGGio+WbZBzT0uwoAh4viBQLndfVUKDAbc1HJ/xehnoFv7GLr5BjDZC1If1ZRW6pu/v17NOmXZGvuJR+gfg44rVnTdbKJCBzd+Li9/Gn9PcVqkw3TGU4FB3dQrmNA8r3MnNf8K+vRc5Bshh82DT+4tb+VwPCWDCJ3AvF2utrIdHwx3+C+JucSZ2f923WOPjTQVY/v162CyTzQyqt2p732v4/h343K4QguTTeuGVhckGNWqAYTCds32eFedGqsmbrpIEMbfxuSrIMf4RsZHYg2GuBbJlTQbzMoIbouDuRpBmkMSm7+F1rL3ikrSMgcBnDS2+RrwvoBxlFQpNtOuJ31DTqYU0pGsYlU0Xyw3YcDxwaw== wkj11250412@outlook.com
```

### 步骤

1. 访问：https://github.com/settings/keys
2. 点击 "New SSH key"
3. Title: `DevTeam Server`
4. 粘贴上面的公钥
5. 点击 "Add SSH key"
6. 然后运行：

```bash
cd /root/.openclaw/workspace/devteam-idea-plugin
git push -u origin main
```

## 推荐：方法1（Token）

最简单，不需要配置SSH。

---

**提示：** 如果你之前已经添加过SSH密钥到GitHub，可能是用的不同的密钥。建议使用方法1（Token）更简单。
