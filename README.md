# spring-cloud-demo
项目为使用spring cloud搭建的demo，包括了`服务发现Eureka`、`WebService客户端Feign`、`hystrix熔断器`、`spring config`、`网关zuul`

## 模块说明
### eureka
服务发现，启动后访问[eureka后台](http://localhost:1111/)

### service
服务提供者，在`application.properties`中配置了eureka的信息，启动后会自动到eureka中注册

### client
* 服务消费者，启动后会从eureka中获取服务提供者，访问[客户端接口(计算两数字相加)](http://localhost:3333/add?a=10&b=1)，会调用service提供的服务。
* 使用了hystrix熔断器，当无法调用服务提供者的时候，会采用降级机制，返回`Integer.MIN_VALUE`
* 读取`configserver`的配置，启动后访问连接[测试配置获取](http://localhost:3333/hello)，会输出`Hello client!`，其中`client`为配置中的`name`属性，当配置中没有此属性时候回输出`Hello unknow!`。当配置发生变更时候可以post方式访问[更新配置](http://localhost:3333/refresh)
，
### configserver
配置服务器，启动之后访问[获取配置信息](http://localhost:7001/compute-consumer/default)，其中配置信息记录在文件`src/main/resources/client/compute-consumer-default.properties`中，另外可以在`application.properties`中修改配置信息

### gateway
网关，demo中配置了匹配到`/api-compute/**`的url都会转发到`compute-consumer`服务中，并且增加了过滤器，需要带上`access_token`参数(demo中access_token随意字符串即可)，启动之后访问[网关-客户端接口(计算两数字相加)](http://localhost:5555/api-compute/add?a=10&b=2&access_token=123)
