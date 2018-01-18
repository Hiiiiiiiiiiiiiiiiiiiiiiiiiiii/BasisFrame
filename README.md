SSM框架搭建
=
索引
-
1. [基本配置](#1)
2. *[Spring](#2)*
3. *[Mybatis](#3)*
4. *[SpringMVC](#4)*
5. [完整配置](#5)

***
<span id="1"></span>
#　基本配置
-

 1. <font face="STCAIYUN" size="4">项目结构</font>

    -- main  
    　-- java    
    　　-- 具体项目    
    　　　-- controller   
    　　　-- entity   
    　　　-- mapper    
    　　　-- service      
    　-- resources   
      -- webapp  
      　-- WEB-INF
 2. <font face="STCAIYUN" size="4">pom.xml</font>   
    ```xml
        <?xml version="1.0" encoding="UTF-8"?>
    <project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.kaishengit</groupId>
    <artifactId>spring_mybatis</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>war</packaging>

    <dependencies>
        <!--junit-->
        <!-- 单元测试 -->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
        </dependency>
        <!-- servlet jstl -->
        <!-- 用来把java文件编译成.class文件 -->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <version>3.1.0</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>jstl</groupId>
            <artifactId>jstl</artifactId>
            <version>1.2</version>
        </dependency>
        <!--spring-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>4.3.9.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
            <version>4.3.9.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-tx</artifactId>
            <version>4.3.9.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-test</artifactId>
            <version>4.3.9.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-web</artifactId>
            <version>4.3.9.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>4.3.9.RELEASE</version>
        </dependency>
        <!--db-->
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-dbcp2</artifactId>
            <version>2.1.1</version>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.40</version>
        </dependency>
        <!--mybatis-->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.4.4</version>
        </dependency>
        <!--Mybatis Spring-->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis-spring</artifactId>
            <version>1.3.1</version>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <!-- 编译插件 -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.1</version>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                    <encoding>UTF-8</encoding>
                </configuration>
            </plugin>
            <!--tomcat插件-->
            <plugin>
                <groupId>org.apache.tomcat.maven</groupId>
                <artifactId>tomcat7-maven-plugin</artifactId>
                <version>2.2</version>
                <configuration>
                    <port>8080</port>
                    <path>/</path>
                    <uriEncoding>UTF-8</uriEncoding>
                </configuration>
            </plugin>
        </plugins>
    </build>
    </project>
    ```
 3. <font face="STCAIYUN" size="4">web.xml</font>       
    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
            version="3.1">

        <!--字符集过滤器-->
        <filter>
            <filter-name>encodingFilter</filter-name>
            <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
            <init-param>
                <param-name>encoding</param-name>
                <param-value>UTF-8</param-value>
            </init-param>
            <init-param>
                <param-name>forceEncoding</param-name>
                <param-value>true</param-value>
            </init-param>
        </filter>
        <filter-mapping>
            <filter-name>encodingFilter</filter-name>
            <url-pattern>/*</url-pattern>
        </filter-mapping>
    </web-app>
    ```

***
<span id="2"></span>
#　Spring
-
1. 添加mven依赖
2. 新建 applicationContext.xml 配置文件
    1. 添加自动扫描(controller)
    2. 加载 properties 文件
    3. 数据库连接池
    4. 添加事务管理器

*** 
<span id="3"></span>
#　Mybatis 
-
1. 添加maven依赖
2. 添加Mybatis的gui生成的实体类、Mapper接口和XML文件到对应的项目目录中.(**链接**: http://www.mybatis.org/generator/index.html )
3. configs.properties文件
    1. jdbc.dirver
    2. jdbc.url
    3. jdbc.username
    4. jdbc.password
4. applicationContext.xml   
    1. 配置 sqlSessionFactory
    2. 将mybatis中的mapper接口自动放入spring容器中
5. 如果需要分页可以使用分页插件( **链接**: https://github.com/pagehelper/Mybatis-PageHelper/blob/master/wikis/zh/HowToUse.md )

***
<span id="4"></span>
#　SpringMVC
-
1. 添加maven依赖
2. web.xml
    1. 配置中央(前端)控制器
3. 创建 mvc-servlet.xml 配置文件
    1. 自动扫描(controller)
    2. 配置  `<mvc:annotation-driven/>` 标签    
    标签作用: Spring容器为@Controller分发请求所必须的.
    3. 配置  `<mvc:default-servlet-handler/>` 标签  
    标签作用: 当servlet查找页面找不到的时候会报出404,但是加上这个标签之后,找不到页面时会去静态资源中查找.
    4. 配置视图解析器

***
<span id="5"></span>
#　完整项目
-
1. <font face="STCAIYUN" size="4">web.xml</font>
```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
         version="3.1">

    <!--字符集过滤器-->
    <filter>
        <filter-name>encodingFilter</filter-name>
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>UTF-8</param-value>
        </init-param>
        <init-param>
            <param-name>forceEncoding</param-name>
            <param-value>true</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>encodingFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>


    <!--SpringMVC的中央控制器-->
    <servlet>
        <servlet-name>mvc</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>mvc</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>

    <!--在web容器启动时创建Spring容器 加载配置文件-->
    <listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:applicationContext*.xml</param-value>
    </context-param>

</web-app>
```
2. <font face="STCAIYUN" size="4">mvc-servlet.xml</font>
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="
http://www.springframework.org/schema/beans
http://www.springframework.org/schema/beans/spring-beans.xsd
http://www.springframework.org/schema/context
http://www.springframework.org/schema/context/spring-context.xsd
http://www.springframework.org/schema/mvc
http://www.springframework.org/schema/mvc/spring-mvc-3.0.xsd">
    <!--自动扫描-->
    <context:component-scan base-package="com.kaishengit.controller">
        <context:include-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
    </context:component-scan>
    <!--spring为@Controller分发请求所必须的-->
    <mvc:annotation-driven/>
    <!--当servlet查找页面找不到的时候会报出404 但是加上这个标签之后 找不到页面时 会去静态资源中查找-->
    <mvc:default-servlet-handler/>

    <!--视图解析器-->
    <bean id="viewResolver" class="org.springframework.web.servlet.view.UrlBasedViewResolver">
        <property name="viewClass" value="org.springframework.web.servlet.view.JstlView"/>
        <property name="prefix" value="/WEB-INF/views/"/>
        <property name="suffix" value=".jsp"/>
    </bean>
</beans>
```
3. <font face="STCAIYUN" size="4">applicationContext.xml</font>
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd
        http://www.springframework.org/schema/tx
        http://www.springframework.org/schema/tx/spring-tx.xsd">

    <!--自动扫描controller注解-->
    <context:component-scan base-package="com.kaishengit">
        <context:exclude-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
    </context:component-scan>
    <!--加载properties文件-->
    <context:property-placeholder location="classpath:configs.properties"/>
    <!--数据库连接池-->
    <!--导入maven项目 dbcp2 (数据库连接池)-->
    <bean id="dataSource" class="org.apache.commons.dbcp2.BasicDataSource" destroy-method="close">
        <property name="driverClassName" value="${jdbc.driver}"/>
        <property name="url" value="${jdbc.url}"/>
        <property name="username" value="${jdbc.username}"/>
        <property name="password" value="${jdbc.password}"/>
    </bean>
    <!--事务管理器-->
    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource"/>
    </bean>
    <!--开启基于注解的事务-->
    <tx:annotation-driven transaction-manager="transactionManager"/>
    <!--mybaits sqlSessionFactory-->
    <bean id="sessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource"/>
        <!--别名包所在位置-->
        <property name="typeAliasesPackage" value="com.kaishengit.entity"/>
        <!--Mapper文件所在位置-->
        <property name="mapperLocations" value="classpath:mapper/*.xml"/>
        <!--其他配置-->
        <!--驼峰命名法-->
        <property name="configuration">
            <bean class="org.apache.ibatis.session.Configuration">
                <property name="mapUnderscoreToCamelCase" value="true"/>
            </bean>
        </property>
    </bean>
    <!--扫描Mybatis中的Mapper接口,并自动放入spring容器中-->
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <property name="basePackage" value="com.kaishengit.mapper"/>
    </bean>

</beans>
```
4. <font face="STCAIYUN" size="4">config.properties</font>
```
jdbc.driver=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql:///test?useSSL=false
jdbc.username=root
jdbc.password=rootroot
```

