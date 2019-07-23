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