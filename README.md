# DevTeam IntelliJ IDEA Plugin

> AI-Powered Development Team in Your IDE

## Features

- 🎯 **8 Professional AI Agents** - PM, Architect, UI Designer, API Designer, Backend, Frontend, QA, Git
- 🚀 **Parallel Execution** - 3-5x faster with intelligent parallel processing
- 🔄 **Iteration Optimization** - Auto-fix bugs until complete
- 🎨 **UI Refinement** - Interactive UI optimization
- 🔧 **Auto Code Fixing** - Fix common issues automatically
- ✅ **Code Validation** - TypeScript, ESLint, build checks
- 📝 **Complete Documentation** - PRD, Architecture, API, UI Design

## Requirements

- IntelliJ IDEA 2023.2+
- Node.js 18+
- DevTeam CLI 3.0+ (will be installed automatically)
- Claude API Key

## Installation

### From JetBrains Marketplace (Coming Soon)

1. Open IntelliJ IDEA
2. Go to `Settings` → `Plugins`
3. Search for "DevTeam"
4. Click `Install`
5. Restart IDE

### From Source

```bash
git clone https://github.com/kaijingWang/devteam-idea-plugin.git
cd devteam-idea-plugin
./gradlew buildPlugin
```

The plugin will be in `build/distributions/devteam-idea-plugin-3.0.0.zip`

Install manually:
1. `Settings` → `Plugins` → ⚙️ → `Install Plugin from Disk...`
2. Select the zip file
3. Restart IDE

## Quick Start

### 1. Configure API Key

1. Go to `Settings` → `Tools` → `DevTeam`
2. Enter your Claude API Key
3. Click `Apply`

### 2. Start Development

**Method 1: Right-click Menu**
1. Right-click on project/folder
2. Select `DevTeam` → `Quick Development`
3. Enter your requirement
4. Click `Start Development`

**Method 2: Keyboard Shortcut**
- Press `Ctrl+Alt+D` (Windows/Linux) or `Cmd+Alt+D` (Mac)
- Enter your requirement
- Press Enter

### 3. Watch Progress

- Open `DevTeam` tool window (bottom panel)
- See real-time logs and progress
- Generated files will appear in your project

## Usage

### Quick Development

Fastest way to generate code:

```
Right-click → DevTeam → Quick Development
```

Features:
- Parallel execution
- Auto fix
- Code validation

### Parallel Development

3-5x faster with parallel Agent execution:

```
Right-click → DevTeam → Parallel Development
```

### Iterate Optimization

Auto-fix bugs until complete:

```
Right-click → DevTeam → Iterate Optimization
```

Options:
- Max iterations: 1-20
- Auto fix: Yes/No
- Validate: Yes/No

### UI Refinement

Interactive UI optimization:

```
Right-click → DevTeam → UI Refinement
```

## Settings

### LLM Configuration

- **API Key**: Your Claude API key
- **API URL**: https://api.anthropic.com
- **Model**: claude-sonnet-4-6
- **Max Tokens**: 8192
- **Temperature**: 0.7

### Workspace

- **Root Directory**: ./devteam-workspace
- **Docs Directory**: docs
- **Src Directory**: src
- **Tests Directory**: tests

### Options

- **Enable Cache**: Cache API responses (24h TTL, 100MB limit)
- **Auto Fix**: Automatically fix common issues
- **Validate**: Validate code quality after generation

## Tool Window

The DevTeam tool window shows:

- **Progress**: Real-time Agent execution status
- **Logs**: Detailed output from each Agent
- **Generated Files**: List of created files

## Keyboard Shortcuts

- `Ctrl+Alt+D` - Quick Development
- `Ctrl+Alt+P` - Parallel Development
- `Ctrl+Alt+I` - Iterate Optimization
- `Ctrl+Alt+R` - UI Refinement

## Troubleshooting

### DevTeam CLI not found

Install DevTeam CLI:

```bash
git clone https://github.com/kaijingWang/DevTeam.git
cd DevTeam
npm install
npm link
```

### API Key not working

1. Check your API key in Settings
2. Verify API URL is correct
3. Test with `devteam config list` in terminal

### Plugin not loading

1. Check IntelliJ IDEA version (2023.2+)
2. Check plugin is enabled in Settings → Plugins
3. Restart IDE

## Development

### Build

```bash
./gradlew buildPlugin
```

### Run in IDE

```bash
./gradlew runIde
```

### Test

```bash
./gradlew test
```

## Contributing

Contributions are welcome!

1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License

MIT

## Author

王凯景 <wkj11250412@outlook.com>

## Links

- [DevTeam CLI](https://github.com/kaijingWang/DevTeam)
- [Documentation](https://github.com/kaijingWang/DevTeam/blob/main/README.md)
- [Issues](https://github.com/kaijingWang/devteam-idea-plugin/issues)

---

**Let AI be your development team!** 🚀
