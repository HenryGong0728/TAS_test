# TAS Test

TAS Test 是人才盘点系统的简化测试项目，用于验证前端页面、后端接口、环境配置和基础联调流程。

当前版本不接入数据库，后端使用内存保存测试数据，服务重启后数据会清空。

## 技术栈

### 后端

| 类型 | 技术 |
|---|---|
| 核心框架 | Spring Boot 3.5.4 |
| 编程语言 | Java 17 |
| 构建工具 | Maven / Maven Wrapper |
| Web 组件 | Spring Web |
| 数据存储 | 内存存储 |

### 前端

| 类型 | 技术 |
|---|---|
| 前端框架 | Vue 3 |
| 构建工具 | Vite |
| 编程语言 | TypeScript |
| 状态管理 | Pinia |
| 路由 | Vue Router |
| HTTP 客户端 | Axios |
| 包管理器 | npm |

## 项目结构

```text
TAS_test/
├── backend/          # 后端服务
├── frontend/         # 前端页面
├── doc/              # 项目文档和测试记录
├── .env.example      # 根目录环境说明
├── .gitignore        # Git 忽略规则
└── README.md         # 项目说明
```

## 环境要求

- Git
- Java 17
- Node.js
- npm

后端基于 Spring Boot 3.x，需要使用 Java 17。若使用 Java 8，会出现编译失败。

## 环境配置

项目按模块维护环境变量模板：

| 模块 | 模板文件 | 本地配置文件 | 说明 |
|---|---|---|---|
| 后端 | `backend/.env.example` | `backend/.env` | 配置 `SERVER_PORT` |
| 前端 | `frontend/.env.example` | `frontend/.env` | 配置 `VITE_API_BASE_URL` |

本地运行时，将对应目录下的 `.env.example` 复制为 `.env` 后再修改。

前端的 `VITE_API_BASE_URL` 用于配置后端接口地址。本机联调时可配置为本地后端地址；连接远程后端时配置为远程后端地址。

后端默认端口为 8080，可通过 `SERVER_PORT` 调整。

## 后端启动

进入 `backend` 目录。

如果本机已安装 Maven，可以使用 Maven 启动。

```bash
mvn spring-boot:run
```

如果本机没有安装 Maven，可以使用项目自带的 Maven Wrapper 启动。

```bash
./mvnw spring-boot:run
```

Windows PowerShell 可使用：

```powershell
.\mvnw.cmd spring-boot:run
```

后端默认访问地址：

```text
http://localhost:8080
```

健康检查接口：

```text
GET http://localhost:8080/api/health
```

人员信息测试接口：

```text
POST http://localhost:8080/api/applications
GET  http://localhost:8080/api/applications
```

## 前端启动

进入 `frontend` 目录。

首次运行需要安装前端依赖，然后启动 Vite 开发服务。

```bash
npm install
npm run dev
```

前端默认访问地址：

```text
http://localhost:5173
```

## 功能说明

当前前端提供一个基础运行检查页面，包含：

- 后端连接状态检测
- 人员编号、姓名、岗位方向填写
- 表单提交测试
- 最近提交结果展示

当前后端提供：

- 健康检查接口
- 人员信息提交接口
- 人员信息列表接口

提交的数据仅保存在内存中，不会写入数据库。

## 运行验证

1. 启动后端服务。
2. 配置前端 `.env` 中的 `VITE_API_BASE_URL`。
3. 启动前端服务。
4. 打开前端页面，确认显示“后端连接成功”。
5. 填写人员编号、姓名和岗位方向并提交，页面显示“提交成功”即表示前后端联调正常。

## 常见问题

### 后端启动失败

优先检查 Java 版本是否为 Java 17。

### Maven 不可用

可以使用 `backend` 目录下的 Maven Wrapper。

### 前端页面显示后端未连接

检查后端是否已启动，并确认 `frontend/.env` 中的 `VITE_API_BASE_URL` 是否正确。修改 `.env` 后需要重新启动前端服务。

### 是否需要安装数据库

当前版本不需要安装数据库。
