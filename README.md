# Spring_exp_3 商品管理

实验三：使用 Spring MVC + JdbcTemplate 完成商品信息的录入与查询。

## 一、技术栈

| 项目 | 版本 / 说明 |
| --- | --- |
| JDK | 17 及以上（本机 IDEA 用的是 26，编译目标为 17） |
| Spring Framework | 6.1.14（Jakarta 命名空间） |
| Web 容器 | Tomcat 10.1.x（Servlet 6.0） |
| 数据库 | MySQL 8，库名 `exp_3` |
| 视图 | JSP + JSTL 3.0 |
| 构建 | Maven，打包方式 `war` |

> 注意：Tomcat 10 使用 `jakarta.servlet.*`，不能再配 Spring 5（`javax.servlet.*`）。
> 如果命令行执行 `mvn` 时默认 JDK 是 1.8，需要先把 `JAVA_HOME` 指向 JDK 17+。

## 二、目录结构

```
Spring_exp_3
├─ pom.xml
├─ sql/exp_3.sql                         建库建表脚本
├─ src/main/java
│  ├─ com/pojo/Product.java              实体类
│  ├─ com/dao/ProductDao.java            Dao 接口
│  ├─ com/dao/ProductDaoImpl.java        JdbcTemplate 实现
│  ├─ com/service/ProductService.java    Service 接口
│  ├─ com/service/ProductServiceImpl.java依赖注入 Dao
│  └─ com/controller
│     ├─ IndexController.java            首页
│     └─ ProductController.java          录入 / 保存 / 列表
├─ src/main/resources
│  ├─ applicationContext.xml             数据源、JdbcTemplate、Service/Dao 扫描
│  └─ springmvc-servlet.xml              Controller 扫描、注解驱动、视图解析器
└─ web
   ├─ WEB-INF/web.xml                    Spring 容器 + DispatcherServlet
   ├─ WEB-INF/index.jsp                  首页
   ├─ WEB-INF/addProduct.jsp             录入页
   ├─ WEB-INF/productList.jsp            列表页
   └─ WEB-INF/lib/*.jar                  运行依赖（保证 IDEA 部署时能加载）
```

`index.jsp`、`addProduct.jsp`、`productList.jsp` 放在 `WEB-INF` 下，浏览器不能直接访问，
统一由 `InternalResourceViewResolver` 转发，视图前缀 `/WEB-INF/`、后缀 `.jsp`。

## 三、数据库准备

1. 执行 `sql/exp_3.sql`（Navicat 或命令行均可），会创建 `exp_3` 库和 `product` 表。
2. 打开 `src/main/resources/applicationContext.xml`，把数据源的用户名、密码改成自己的：

```xml
<property name="username" value="root"/>
<property name="password" value="123456"/>
```

表结构以实验提供的为准，有两个容易踩坑的地方：

- `id` **不是自增列**，由 `ProductDaoImpl` 用 `MAX(id)+1` 生成；
- “商品介绍”列名是 `decription`（实验表里的拼写），实体类属性仍叫 `description`。

```sql
CREATE TABLE product (
    id         INT           NOT NULL,
    name       VARCHAR(100)  NOT NULL,
    price      DECIMAL(16,2) DEFAULT NULL,
    code       VARCHAR(32)   DEFAULT NULL,
    saleDate   DATE          DEFAULT NULL,
    decription VARCHAR(100)  DEFAULT NULL,
    PRIMARY KEY (id)
);
```

## 四、请求流程

| 请求 | 处理方法 | 结果 |
| --- | --- | --- |
| `GET /` 或 `/index` | `IndexController.index()` | 打开 `index.jsp` |
| `GET /addProduct` | `ProductController.addProductPage()` | 打开 `addProduct.jsp` |
| `POST /saveProduct` | `ProductController.saveProduct()` | 调用 Service 写入，重定向到 `/productList` |
| `GET /productList` | `ProductController.productList()` | 查询全部商品，打开 `productList.jsp` |

`index.jsp` 中的两个超链接分别指向 `/addProduct` 和 `/productList`，由 Controller 转发到对应 JSP。

## 五、运行方式

### 方式一：IDEA + Tomcat（推荐，和实验环境一致）

1. 用 IDEA 打开项目，等待 Maven 依赖导入完成。
2. 确认 Tomcat 10.1.x 的 Deployment 里是 `Spring_exp_3:Web exploded`。
3. 启动 Tomcat，浏览器访问：
   `http://localhost:8080/Spring_exp_3_Web_exploded/`

如果启动时报：

```
java.lang.ClassNotFoundException: org.springframework.web.context.ContextLoaderListener
```

说明部署目录的 `WEB-INF/lib` 是空的。本工程的 `web/WEB-INF/lib` 已经放好全部运行 jar，
在 IDEA 里执行 **Build → Build Artifacts → Rebuild**，或重新运行 Tomcat 即可。

### 方式二：Maven 打 WAR 包

```bash
mvn clean package
```

把 `target/Spring_exp_3.war` 放进 Tomcat 的 `webapps` 目录，访问
`http://localhost:8080/Spring_exp_3/`。

## 六、说明

- `web/WEB-INF/lib` 中的 jar 是为了让 IDEA 的 exploded 工件直接带上依赖；
  `pom.xml` 里同样声明了这些依赖，`mvn package` 也会复制一份（同名文件会覆盖，不影响运行）。
  以后如果升级 Spring 或 MySQL 驱动，记得同步替换这里的 jar。
- 页面编码统一为 UTF-8，`web.xml` 中配置了 `CharacterEncodingFilter`。
- 表单日期使用 `<input type="date">`，提交格式为 `yyyy-MM-dd`，
  实体类用 `@DateTimeFormat(pattern = "yyyy-MM-dd")` 接收。
