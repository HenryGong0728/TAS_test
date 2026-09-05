# 实验室阶段性考核仓库（Assessment Lab）

本仓库是实验室招新阶段考核的专用练习仓库，技术栈与正式项目（人才评估系统）保持一致，用于考察：

- Git 仓库协作能力（Fork、个人分支、提交、PR、分支同步）
- 项目环境配置能力（`.env` 环境变量）
- 前后端项目运行与联调能力
- 基础问题排查能力

> **说明**：本考核版本**不接入数据库（SQL）**，后端使用内存存储，启动后即可完成表单提交与数据查看，无需安装 MySQL。

---

## 技术栈

### 后端

| 类型 | 技术 |
|---|---|
| 核心框架 | Spring Boot 3.5.4 |
| 编程语言 | Java 17 |
| 构建工具 | Maven |
| Web 组件 | Spring Web |
| 数据存储 | 内存存储（考核版不接入 MySQL / MyBatis） |

### 前端 / 管理端

| 类型 | 技术 |
|---|---|
| 前端框架 | Vue 3 |
| 构建工具 | Vite |
| 编程语言 | TypeScript |
| UI 组件 | Element Plus |
| 状态管理 | Pinia |
| 路由 | Vue Router |
| HTTP 客户端 | Axios |
| 包管理器 | npm |

---

## 项目目录

```text
assessment-lab/
├── backend/          # 后端服务（Spring Boot，内存存储，无数据库）
├── frontend/         # 前端 / 考生端（Vue 3，信息登记表单）
├── admin/            # 管理端（Vue 3，查看提交记录）
├── doc/              # Git 协作考核文件夹（考核时在此新建文件）
├── .env.example      # 环境变量模板说明入口
├── .gitignore        # Git 忽略规则
└── README.md         # 项目入口说明（本文件）
```

---

## 启动步骤

### 1. 环境配置

将各模块的 `.env.example` 复制生成 `.env` 文件，并按考核要求填写：

| 模块 | 目录 | 考核环境 | 说明 |
|---|---|---|---|
| 前端（考生端） | `frontend/` | **remote** | `VITE_API_BASE_URL` 填写考官提供的**云端后端地址** |
| 后端 | `backend/` | **local** | 本地启动，默认端口 8080，无需数据库配置 |
| 管理端 | `admin/` | **local** | `VITE_API_BASE_URL` 填写本地后端 `http://localhost:8080` |

PowerShell 复制示例：

```powershell
copy frontend\.env.example frontend\.env
copy admin\.env.example admin\.env
copy backend\.env.example backend\.env
```

配置要点：

- **前端 `.env`**：将 `VITE_API_BASE_URL` 改为考官现场提供的云端后端地址；若需在本机联调本地后端，改为 `http://localhost:8080`。
- **管理端 `.env`**：保持 `VITE_API_BASE_URL=http://localhost:8080`。
- **后端 `.env`**：保持默认即可（`SERVER_PORT=8080`）。使用 IDEA 启动时，也可在运行配置的 Environment variables 中注入。
- 修改 `.env` 后需要重新执行 `npm run dev` 才会生效。

### 2. 选择启动方式

#### 方式一：命令行启动

**后端**（需本机已安装 JDK 17）：

已安装 Maven 时：

```powershell
cd backend
mvn spring-boot:run
```

未安装 Maven 时，可直接使用项目自带的 Maven Wrapper（首次运行会自动下载 Maven）：

```powershell
cd backend
.\mvnw.cmd spring-boot:run
```

后端默认地址：

```text
http://localhost:8080
```

健康检查接口：

```text
http://localhost:8080/api/health
```

**前端（考生端）**：

```powershell
cd frontend
npm install
npm run dev
```

前端默认地址：

```text
http://localhost:5173
```

**管理端**：

```powershell
cd admin
npm install
npm run dev
```

管理端默认地址：

```text
http://localhost:5174
```

#### 方式二：IDE 启动（IDEA / VS Code）

**后端**：使用 IDEA 打开 `backend` 目录，等待 Maven 依赖加载完成，确认 JDK 为 17，运行后端启动类：

```text
backend/src/main/java/com/zcst/assessment_backend/AssessmentBackendApplication.java
```

**前端 / 管理端**：使用 VS Code 或 IDEA 打开 `frontend`（或 `admin`）目录，在内置终端执行 `npm install` 安装依赖，再执行 `npm run dev` 启动（也可使用 IDE 内置的 npm 脚本面板）。

### 3. 启动验证

1. 浏览器打开考生端 `http://localhost:5173`，按考官要求填写并提交表单。
2. 浏览器打开管理端 `http://localhost:5174`，点击「刷新」，确认刚提交的数据出现在表格中。
3. 浏览器访问 `http://localhost:8080/api/health`，返回 `{"status":"UP"}` 即表示后端运行正常。

---

## Git 协作考核说明

- Fork 本仓库到自己的账号，在 **个人分支**（建议以姓名/昵称命名）上完成操作，**禁止直接在主分支开发**。
- 云端修改：在仓库主分支的 `doc/` 文件夹中新建文件，保存并提交。
- 本地修改：在本地项目的 `doc/` 文件夹中新建文件，填写任意可追踪内容，提交并推送到远程个人分支。
- Commit Message 需清晰描述修改内容，例如：

```text
docs: 新增个人考核测试文件
```

- 完成后发起 Pull Request（PR），并将主分支的最新修改同步到个人分支。

---

## 常见问题

### 后端 Maven 导入报错

确认 IDEA 使用的 JDK 为 Java 17，并在 Maven 面板中重新加载（Reload）依赖。

### 前端依赖安装失败

先确认 Node.js 版本符合 `package.json` 中 `engines` 的要求（Node `^22.18.0` 或 `>=24.12.0`），再重新执行 `npm install`。

### 考生端显示「后端未连接」/ 管理端看不到数据

1. 确认后端已启动（访问 `http://localhost:8080/api/health`）。
2. 检查对应模块 `.env` 中的 `VITE_API_BASE_URL` 是否正确（管理端应为 `http://localhost:8080`）。
3. 修改 `.env` 后需重启 `npm run dev`。

### 需要安装 MySQL 吗？

不需要。考核版本后端使用内存存储，不接入数据库；后端重启后数据会清空，属正常现象。
