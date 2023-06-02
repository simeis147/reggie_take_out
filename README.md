## 1. 角色分工

| 岗位/角色                           | 职责/分工                                  |
| ----------------------------------- | ------------------------------------------ |
| 项目经理                            | 对整个项目负责，任务分配、把控进度         |
| 产品经理                            | 进行需求调研，输出需求调研文档、产品原型等 |
| UI设计师                            | 根据产品原型输出界面效果图                 |
| 架构师                              | 项目整体架构设计、技术选型等               |
| **开发工程师** | **功能代码实现**     |
| 测试工程师                          | 编写测试用例，输出测试报告                 |
| 运维工程师                          | 软件环境搭建、项目上线                     |

## 2. 瑞吉外卖项目介绍

### 2.1 项目介绍

![ ](https://simeis147.oss-cn-shenzhen.aliyuncs.com/img/20230602172603.png)

瑞吉外卖是专为餐饮企业（餐厅、饭店）定制的一款软件产品，包括 **系统管理后台** 和 **移动端应用** 两部分

**系统管理后台**：主要提供给餐饮企业内部员工使用，可以对餐厅的分类、菜品、套餐、订单、员工等进行管理维护  

**移动端应用**：主要提供给消费者使用，可以在线浏览菜品、添加购物车、下单等

### 2.2 产品原型

> 一款产品成型之前，由产品经理绘制一个简单的框架，将页面的排版布局展现出来，使产品的初步构思有一个可视化的展示

#### **1. 管理端**

餐饮企业内部员工使用   主要功能有:

|   模块      | 描述                                                         |
| --------- | ------------------------------------------------------------ |
| 登录/退出 | 内部员工必须登录后，才可以访问系统管理后台                    |
| 员工管理  | 管理员可以在系统后台对员工信息进行管理，包含查询、新增、编辑、禁用等功能 |
| 分类管理  | 主要对当前餐厅经营的 菜品分类 或 套餐分类 进行管理维护，包含查询、新增、修改、删除等功能 |
| 菜品管理  | 主要维护各个分类下的菜品信息，包含查询、新增、修改、删除、启售、停售等功能 |
| 套餐管理  | 主要维护当前餐厅中的套餐信息，包含查询、新增、修改、删除、启售、停售等功能 |
| 订单明细  | 主要维护用户在移动端下的订单信息，包含查询、取消、派送、完成，以及订单报表下载等功能 |

#### **2. 用户端**

移动端应用主要提供给消费者使用  主要功能有:

| 模块        | 描述                                                         |
| ----------- | ------------------------------------------------------------ |
| 登录/退出   | 在移动端，用户也需要登录后使用APP进行点餐                    |
| 点餐-菜单   | 在点餐界面需要展示出菜品分类/套餐分类，并根据当前选择的分类加载其中的菜品信息，供用户查询选择 |
| 点餐-购物车 | 用户选中的菜品就会加入用户的购物车，主要包含 查询购物车、加入购物车、删除购物车、清空购物车等功能 |
| 订单支付    | 用户选完菜品/套餐后，可以对购物车菜品进行结算支付，这时就需要进行订单的支付 |
| 个人信息    | 在个人中心页面中会展示当前用户的基本信息，用户可以管理收货地址，也可以查询历史订单数据 |

### 2.3 技术选型

![ ](https://simeis147.oss-cn-shenzhen.aliyuncs.com/img/20230602172703.png)

#### 1. 用户层

系统管理后台的前端页面，使用 **H5**、**Vue.js**、**ElementUI** 等技术

#### 2. 网关层

**Nginx**是一个服务器，主要用来作为Http服务器，部署静态资源，访问性能高  

> Nginx中还有两个比较重要的作用： 反向代理和负载均衡， 在进行项目部署时，要实现Tomcat的负载均衡，就可以通过Nginx来实现

#### 3. 应用层

**SpringBoot**： 快速构建Spring项目，采用 "约定优于配置" 的思想，简化Spring项目的配置开发  

**Spring**：统一管理项目中的各种资源(bean)，在web开发的各层中都会用到  

**SpringMVC**：SpringMVC是spring框架的一个模块，springmvc和spring无需通过中间整合层进行整合，可以无缝集成  

**SpringSession**：主要解决在集群环境下的Session共享问题  

**lombok**：能以简单的注解形式来简化java代码，提高开发人员的开发效率，例如开发中经常需要写的javabean，都需要花时间去添加相应的getter/setter，也许还要去写构造器、equals等方法  

**Swagger**： 自动帮助开发人员生成接口文档，并对接口进行测试  

#### 4. 数据层

**MySQL**： 关系型数据库，本项目的核心业务数据都会采用MySQL进行存储  

**MybatisPlus**： 本项目持久层将会使用MybatisPlus来简化开发，基本的单表增删改查直接调用框架提供的方法即可  

**Redis**： 基于key-value格式存储的内存数据库，访问速度快，经常使用它做缓存(降低数据库访问压力，提供访问效率)，在后面的性能优化中会使用  

#### 5. 工具

**git**: 版本控制工具，在团队协作中，使用该工具对项目中的代码进行管理  

**maven**: 项目构建工具  

**junit**：单元测试工具，开发人员功能实现完毕后，需要通过junit对功能进行单元测试  

### 2.4 功能架构

![QQ截图20230506133314.png](https://pic.peo.pw/a/2023/05/06/6455e6ba33731.png)

**1. 移动端前台功能**:

手机号登录 ，微信登录 ，收件人地址管理 ，用户历史订单查询 ，菜品规格查询 ，购物车功能 ，下单 ，分类及菜品浏览  

**2. 系统管理后台功能**:

员工登录/退出 ，员工信息管理 ，分类管理 ，菜品管理 ，套餐管理 ，菜品口味管理 ，订单管理  

### 2.5 角色

1. 后台系统管理员
2. 后台系统普通员工
3. C端(移动端)用户  

| 角色             | 权限操作                                                     |
| ---------------- | ------------------------------------------------------------ |
| 后台系统管理员   | 登录后台管理系统，拥有后台系统中的所有操作权限               |
| 后台系统普通员工 | 登录后台管理系统，对菜品、套餐、订单等进行管理 (不包含员工管理) |
| C端用户          | 登录移动端应用，可以浏览菜品、添加购物车、设置地址、在线下单等 |
