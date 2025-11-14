# AI News Spring Boot Backend

This Spring Boot service exposes a simple API that returns the latest AI news items. It is built with Java 17 and Maven.

## Project setup

```bash
mvn spring-boot:run
```

Once the application is running, access the news endpoint:

```
GET http://localhost:8080/api/news
```

## Render 免费部署步骤

1. 在 Render 控制台点击 **New → Web Service**。
2. 选择 **Build and deploy from a Git repository** 并连接当前仓库，Render 会读取根目录的 `render.yaml`。
3. 确认使用免费计划（Free），不要勾选任何付费选项，然后创建服务。
4. 部署完成后即可获得形如 `https://your-service.onrender.com` 的域名，后端 API 为 `https://your-service.onrender.com/api/news`。

> 应用会自动识别 Render 提供的 `PORT` 环境变量，无需额外配置。
