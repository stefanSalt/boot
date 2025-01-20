# 高校讲座预约系统

## 项目介绍
高校讲座预约系统是一个用于管理讲座预约、资讯查看等功能的Web应用。系统支持讲座的发布、预约、签到、评价等功能，并提供详细的用户管理功能。

## 技术栈
- **后端**: Java, Spring Boot, MyBatis Plus, MySQL, Druid, JWT, Hutool, EasyExcel, Knife4j
- **前端**: Swagger UI, Knife4j UI (通过后端集成)

## 运行环境
- Java 17
- MySQL 8.0

## 安装步骤
1. **克隆项目**
   ```bash
   git clone https://github.com/your-repo/lecture.git
   cd lecture
   ```

2. **配置数据库**
   - 修改 `src/main/resources/application-dev.yml` 文件中的数据库连接信息。
   ```yaml
   spring:
     datasource:
       url: jdbc:mysql://localhost:3306/lecture?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai
       username: root
       password: '1234'
   ```

3. **导入数据库脚本**
   - 执行 `src/main/resources/db/lecture.sql` 文件中的SQL脚本以初始化数据库表结构和数据。
   ```bash
   mysql -u root -p lecture < src/main/resources/db/lecture.sql
   ```

4. **构建项目**
   ```bash
   mvn clean install
   ```

5. **运行项目**
   ```bash
   mvn spring-boot:run
   ```

## 使用方法
- **访问API文档**
  - 打开浏览器，访问 `http://localhost:9001/swagger-ui.html` 查看API文档。
- **管理讲座**
  - 通过API接口进行讲座的增删改查操作。
- **用户认证**
  - 使用JWT进行用户认证，通过登录接口获取JWT令牌。

## 贡献
欢迎贡献代码，提交Issue或Pull Request。

## 许可证
本项目采用MIT许可证。