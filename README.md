## 部署
Git
JDK
Maven
MySql
## 步骤
- sudo apt update
- sudo apt install git
- sudo apt install maven
- 
- exit  转到个人用户
- pwd   打开目录
- mkdir App 创建目录
- cd App/ 进入目录
- cd App/community
- mvn flyway:migrate  数据库执行语句
- mvn mybatis-generator:generate  Mybatis自动生成
- mvn compile package 打包
- cp src/
- sudo java -jar -Dspring.profiles.active=production target/community-0.0.1-SNAPSHOT.jar
- sudo java -jar target/community-0.0.1-SNAPSHOT.jar
- ps -aux | grep java  查看进程是否存在
- kill -9 id       id为杀掉的进程id  
- nohup java -jar target/community-0.0.1-SNAPSHOT.jar &
## 资料
[Spring 文档](https://spring.io/guides)
[spring boot](https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html#howto)
[maven](https://maven.apache.org/)
[MVC](https://docs.spring.io/spring-framework/docs/5.0.3.RELEASE/spring-framework-reference/web.html#mvc-handlermapping-interceptor)
[mvn仓库](https://mvnrepository.com/)
[Bootstrap](https://v3.bootcss.com/)
[Github OAuth](https://docs.github.com/cn/developers/apps/building-oauth-apps/creating-an-oauth-app)
[OkHttp](https://square.github.io/okhttp/)
[h2 数据库工具](http://www.h2database.com/html/main.html)
[Thymeleaf](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html)
[mybatis generator](http://mybatis.org/generator/)
## 工具
[Git](https://git-scm.com/dowmload)
[Visual Paradigm](https://www.visual-paradigm.com)
[Flyway](https://flywaydb.org/getstarted/firststeps/maven)
[lombok](https://projectlombok.org/)
[Postman](extension://jopnhpooecpfgkolkacdmpehiiffcinf/debug.html)
[json editor online](http://jsoneditoronline.org/)
## 脚本
```bash
mvn flyway:migrate  数据库执行语句
mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate
mvn mybatis-generator:generate  Mybatis自动生成
```