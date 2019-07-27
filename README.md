# MyIona

个人基于SpringBoot的JavaWeb练习项目

# 从SSM升级到SpringBoot

1. 使用SpringInitializr创建一个空的SpringBoot-Web项目模板

2. 拷贝所有SSM项目业务代码全部复制到根包下，然后修复下IDE的报错内容

3. 将SSM的所有xml配置，依次使用Java配置类去实现（全局事务的配置会难一点，不过已解决）

4. 启动项目查看其他报错，并依次解决

5. vue的东西全部放在static/文件夹下即可

# 部署至云端

过程和MyBelfast的部署一致，请查阅MyBelfast的README.md文档

使用`java -jar`命令启动即可

# 待完成功能

系统监控，记录谁访问了什么，全都要记录

# 后期可能会重构的部分

1. router和首页左侧菜单栏数据源变更，统一从后台获取，前端全部自动生成，而不是硬编码

2. 部分前端配置与后端配置重合，最好想办法让这些重合的部分都统一从后端取，前端在初始化阶段调用http请求去获取这些配置信息

# 静态资源

静态资源的配置见SpringMvcConfig类的addResourceHandlers方法

1. 头像

默认的，头像会被自动存储至avatar/下(和项目目录同级) 

