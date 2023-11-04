# Lottery营销平台

## 基础架构

![img.png](imgs/img.png)
### DDD分层架构
项目结构
![img_2.png](/imgs/img_2.png)

#### 应用层Application

应用服务位于应用层。用来表述应用和用户行为，负责服务的组合、编排和转发，负责处理业务用例的执行顺序以及结果的拼装。
**应用层的服务包括应用服务和领域事件相关服务。**
**应用服务可对微服务内的领域服务以及微服务外的应用服务进行组合和编排，或者对基础层如文件、缓存等数据直接操作形成应用服务，对外提供粗粒度的服务。**
领域事件服务包括两类：领域事件的发布和订阅。通过事件总线和消息队列实现异步数据传输，实现微服务之间的解耦。

#### Domain层

领域服务位于领域层，为完成领域中跨实体或值对象的操作转换而封装的服务，领域服务以与实体和值对象相同的方式参与实施过程。
**领域服务对同一个实体的一个或多个方法进行组合和封装，或对多个不同实体的操作进行组合或编排，对外暴露成领域服务。领域服务封装了核心的业务逻辑。实体自身的行为在实体类内部实现，向上封装成领域服务暴露。**
**为隐藏领域层的业务逻辑实现，所有领域方法和服务等均须通过领域服务对外暴露。**
为实现微服务内聚合之间的解耦，原则上禁止跨聚合的领域服务调用和跨聚合的数据相互关联。

#### Infrastructure层

基础服务位于基础层。为各层提供资源服务（如数据库、缓存等），实现各层的解耦，降低外部资源变化对业务逻辑的影响。

基础服务主要为仓储服务，通过依赖反转的方式为各层提供基础资源服务，领域服务和应用服务调用仓储服务接口，利用仓储实现持久化数据对象或直接访问基础资源。

一般对应一些数据库的实体类都放到这里，还有一些dao的都放到这一层进行处理

#### Interface层

接口服务位于用户接口层，用于处理用户发送的**Restful**请求和解析用户输入的配置文件等，并将信息传递给应用层。

### RPC

对外提供接口描述信息

一般将接口都写在这里面，然后在interface中进行实现，然后在应用层application进行dubboReference进行rpc的调用。这样就完成了一部分的调用工作。

#### Dubbo的配置

一般都是配置zookeeper为注册中心

```yaml
dubbo:
  registry:
    address: zookeeper://ip:2181  #注册中心
  application:
    name: Lottery
    version: 1.0
  protocol:  #使用的协议，默认是使用dubbo进行的，是rpc，处于osi7层模型中的第五层。也可以rmi远程调用，或者http等方式进行调用
    name: dubbo
    port: 20881
  scan:
    base-packages: com.kevin.lottery.rpc  #接口扫描
```

`dubbo的调用过程`
![img_1.png](imgs/img_1.png)

### 活动表设计

因为是一个抽奖系统，首先涉及到活动

因此需要一个活动表

对应的字段应该有：主键id、活动名称、活动id(多张表联合，对应具体的活动细节)、活动开始时间、活动结束时间、活动描述、活动可以参加人数、库存、活动状态(1编辑、2提审、3撤审、4通过、5运行(审核通过后worker扫描状态)、6拒绝、7关闭、8开启)、创建时间、更新时间、创建人、更新人



对应的建表语句如下：

```sql
create table `activity`(
	id bigint(20) not null AUTO_INCREMENT comment '自增id',
	activity_id bigint(20) not null comment '活动id',
	activity_name varchar(64) CHARACTER set utf8mb4 DEFAULT null comment '活动名称',
	activity_desc varchar(128) CHARACTER set utf8mb4 DEFAULT null comment '活动描述',
	begin_date_time datetime DEFAULT null COMMENT '活动开始时间',
	end_date_time datetime DEFAULT null comment '活动结束时间',
	stock_count int(11) DEFAULT null comment '库存',
	take_count int(11) DEFAULT null comment '每次活动可以参加人数',
	state TINYINT(2) DEFAULT null comment '活动状态：1编辑、2提审、3撤审、4通过、5运行(审核通过后worker扫描状态)、6拒绝、7关闭、8开启',
	create_time datetime DEFAULT CURRENT_TIMESTAMP comment '创建时间',
	update_time datetime DEFAULT CURRENT_TIMESTAMP comment '更新时间',
	creator varchar(64) CHARACTER set utf8mb4 comment '创建人',
	PRIMARY KEY(`id`),
	unique key `unique_activity_id`(`activity_id`)
)ENGINE = INNODB auto_increment=2 DEFAULT charset=utf8mb4 collate=utf8mb4_bin comment ='活动配置';
```

这里的utf8mb4_bin 是一种区分大小写和二进制比较的utf-8编码字符集。

#### 对应的具体表
![img.png](imgs/img4.png)

因为涉及到活动，活动的进行规则、策略，奖品的发放，以及奖品发放等信息，以及用户、用户参与信息等表。

