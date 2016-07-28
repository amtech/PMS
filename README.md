# PMS 个人管理系统，企业技术整合。(企业工作中所使用的技术与资源)
   @Date: 2016-7-19 17:01:04 @Author: edgar @email:edgar_zhang2014@163.com

    项目介绍：
        1.项目采用分布式架构，实现各模块功能，既相互依赖，又各自独立；
        2.使用maven构建工具管理项目，提升开发效率；
        3.项目有两个web入口：
            home:后台管理系统web入口；
            portal:前台管理系统web入口；

    技术点整理：

        1.开发工具：
           IntelliJ IDEA 15.0.1 （目前最好的java开发工具）
           maven3 构建工具

        2.使用框架技术：
            MVC框架：springMVC（控制层业务处理）
            ORM框架：mybatis（数据访问层处理）
            权限框架：shiro （后台权限控制管理）
            办公框架： poi （对项目中使用的excel、word、pdf处理）

        3.后台管理系统---使用shiro 控制权限；
        4.使用poi 进行excel表格数据、word文档等操作

    说明：
        本仓库会不断更新个人整理的资料，介绍项目使用到的开发工具、框架知识等资料。

    功能添加：
    @Date 2016-7-21 14:34:23 @author:edgar
    1.添加mybatis模块，添加MBG(mybatis-generator 逆向生成pojo工程)demo。
    @Date 2016-7-28 14:53:11 @author:edgar
    2.home 后台初始搭建，使用 spring+springMVC+mybatis,详情参考：home/readme.md文件说明。