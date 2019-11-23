# fss

###
  FSS框架是为了更快速，更简单，更规范进行Android开发，他包含多个子模块，开发者可根据需要添加所需的依赖。 
+ [fss-bind](https://www.jianshu.com/p/ec05e3e7d319)
绑定框架，实现了多种绑定，简化Activity/Fragment的开发
+ [fss-router](https://www.jianshu.com/p/ab618a57adc3)
路由框架，使用注解的方式声明路由，使用接口管理路由方法，方便进行路由管理
+ [fss-adapter](https://www.jianshu.com/p/0dfc654324d4)
适配器，提供ListView以及RecyclerView的多种适配器，简化适配器开发
+ [fss-runpriority](https://www.jianshu.com/p/1606421edc7a)
运行优先级，使用它可以实现在子类中自定义继承的方法的调用顺序

## 接入
在根项目的build.gradle文件中添加仓库地址
```
	allprojects {
		repositories {
			...
			maven { url 'https://www.jitpack.io' }
		}
	}
```
对应模块添加依赖
``` 
    def fss_version = '1.0.31'
    implementation "com.github.gongbox.fss:adapter:$fss_version"              //适配器模块
    implementation "com.github.gongbox.fss:bind:$fss_version"                 //绑定
    implementation "com.github.gongbox.fss:runpriority:$fss_version"          //运行优先级
    implementation "com.github.gongbox.fss:base:$fss_version"                 //包含adapter，bind，runpriority模块，同时还有Activity和Fragment的基类
    implementation "com.github.gongbox.fss:router-api:$fss_version"           //路由
    annotationProcessor "com.github.gongbox.fss:router-compiler:$fss_version" //路由注解处理器
``` 
      
### 详细介绍：
- [Android开发-FSS开源框架之绑定](https://www.jianshu.com/p/ec05e3e7d319)
- [Android开发-FSS开源框架路由](https://www.jianshu.com/p/ab618a57adc3)
- [Android开发-FSS开源框架之方法运行优先级](https://www.jianshu.com/p/1606421edc7a)
- [Android开发-FSS开源框架之fss_common](https://www.jianshu.com/p/c861716d1421)
- [Android开发-FSS开源框架之ListView，RecyclerView适配器](https://www.jianshu.com/p/0dfc654324d4)

如果各位觉得有什么不足，反应反馈，如果觉得还不错，请给颗星。
