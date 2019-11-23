# fss

###
  FSS框架是为了更快速，更简单，更规范进行Android开发，他包含多个子模块，开发者可根据需要引入自己需要的依赖包即可。
 gitHub地址：[https://github.com/gongbox/fss](https://github.com/gongbox/fss)  
+ [fss-bind](https://www.jianshu.com/p/ec05e3e7d319)
绑定框架，实现了多种绑定，大大减少Activity/Fragment代码
+ [fss-router](https://www.jianshu.com/p/ab618a57adc3)
路由框架，更简洁地进行路由，方便进行路由管理
+ [fss-adapter](https://www.jianshu.com/p/0dfc654324d4)
适配器，提供ListView以及RecyclerView的多种适配器，简化适配器开发
+ [fss-runpriority](https://www.jianshu.com/p/1606421edc7a)
运行优先级，使用它可以实现在自类中自定义调用顺序
      
 ##fss-bind 
绑定框架，使用它可以实现Activity或Fragment的绑定，比如layout绑定，finish方法绑定，View或点击事件绑定，参数绑定，参数绑定到databinding变量等等
  * **用法1：绑定布局文件(Activity，Fragmeng等)**
   ```Java
    @BindActivity(R.layout.activity_bind_test)
    public class BindTestActivity extends BaseFssActivity {
    }
   ```
   另外，BindActivity可以绑定退出按钮，如下：
   ```Java
    @BindActivity(value = R.layout.activity_bind_test, finishViewId = R.id.btn_finish)
    public class BindTestActivity extends BaseFssActivity {
    }
   ```
   这样就可以在点击id为btn_finish的控件时直接调用finish方法，退出Activity
  
   * **用法2：绑定视图**
   ```Java
    public class BindTestActivity extends BaseFssActivity {
        @BindView(R.id.list_view)
        private ListView listView;  //支持绑定所有类型的视图
    }
   ```
   * **用法3：绑定点击事件**
   ```Java
    public class BindTestActivity extends BaseFssActivity {
        @BindOnClick(R.id.btn_click)
        private void click() {
            ...
        }
        //或者
        @BindOnClick(R.id.btn_click)
        private void click(View view) {
            ...
        }
        //或者
        @BindOnClick(R.id.btn_click)
        private void click(Button button) {
            ...
        }
    }
   ```
   或者可以声明写在类上
   ```Java
    @BindOnClick(value = R.id.btn_test, onClickMethod = "test")
    public class BindTestActivity extends BaseFssActivity {
     //方法可以无参或者有一个该控件类型或父类型的参数，如private void test(Button button){}，或private void click(View view){}
     private void test(){
        ...
     }
    }
   ```
   * **用法4：绑定路由**
   ```Java
    //当用户点击id为btn_route的控件时，便可以直接跳转到BindDetailActivity页面(支持携带参数，这里先不介绍)
    @BindRoute(viewId = R.id.btn_route, toActivity = BindDetailActivity.class)  
    public class BindTestActivity extends BaseFssActivity {
    }
   ```
   * **用法5:  绑定参数（支持Activity或Fragmeng）**
   ```Java
    public class BindTestActivity extends BaseFssActivity {
        @BindExtra(value = "value")//key值与变量名相同时可以省略为@BindExtra
        private Integer value;
    }
   ```
   * **用法6：绑定databinding变量**
     
   ```Java
    public class BindTestActivity extends BaseFssActivity {
        //BindExtra除了会将A页面传过来的参数绑定到value变量上，也会将该变量绑定到布局文件中databinding变量名value的变量中
        @BindExtra(id = BR.value) 
        private String value;
    }
   ```
   如果你不需要使用value变量的话，你也可以这么写，效果同上
   ```Java
    @BindExtra(value = "value", id = BR.value) 
    public class BindTestActivity extends BaseFssActivity {
    }
   ```

看到这儿，如果你觉得也没多简洁的话，那我们接下来看看实际使用中的几个例子。

然后我们看一个比较完整的例子：
* 例子
```Java
//绑定layout，并且将视图中id为R.id.img_back的点击事件绑定到finish方法,如果是Fragment，注解需要改为BindFragment，并且没有finish参数
@BindActivity(value = R.layout.activity_a, finishViewId = R.id.img_back)
//绑定Route，当用户点击id为R.id.to_activity_b的view后，会跳转到ActivityB
@BindRoute(viewId = R.id.to_activity_b, toActivity = ActivityB.class)
//绑定Route，当用户点击id为R.id.to_activity_c的view后，会跳转到ActivityC，并会传递参数，参数key值为EXTRA_VALUE2，携带参数为"value":value变量值,"value2":"789","value3":[234](这里为int类型数组)
@BindRoute(viewId = R.id.to_activity_c,toActivity = ActivityC.class,
        extras = {":@value", "value2:789", "value3:(int)[234]"}
)
//绑定intent参数到databinding变量
@BindExtra(value = "EXTRA_VALUE4",  id = BR.value4)
//绑定点击事件，将id为R.id.test的view的点击事件绑定到test方法上，要求该方法没有参数或只有一个View类型的参数
@BindOnClick(value = {R.id.test}, onClickMethod = "test")
public class ActivityA extends BaseBindingActivity<ActivityABinding> {
    
    //绑定intent参数，将key值为EXTRA_VALUE的参数绑定达到value变量
    @BindExtra(value = "EXTRA_VALUE")
    private Integer value;
    
    //绑定intent参数，和上面的BindParam不同的时，除了会将变量绑定外，在使用databinding的情况下，还会将该变量值直接绑定到databinding变量
    //另外，该注解可以直接写到类上面，因为有的时候我只需要将intent传递过来的参数绑定到databinding中，而我并不关心它的值
    @BindExtra(value = "EXTRA_VALUE3",  id = BR.value3)
    private String value3;
    
    //路由参数，用于绑定路由时传递参数
    private String value2 = "123456";
    
    //绑定视图
    @BindView(value = R.id.list_view)
    private ListView listView;

    //绑定点击事件，将id为R.id.img_fun1的view的点击事件绑定到opertation方法上，注意该方法没有参数
    @BindOnClick(R.id.img_fun1)
    public void opertation() {
    }
    
    //绑定点击事件，将id为R.id.img_fun2，R.id.img_fun3的view的点击事件绑定到opertation方法上
    //注意该方法有一个参数View，该参数即为对应触发点击事件对的view
    @BindOnClick({R.id.img_fun2，R.id.img_fun3})
    public void opertation(View view) {
    }
    
    private void test(){
    }
}
```
以上是fss-bind的部分介绍，若想详细了解，请参考[Android开发利器-FSS开源框架之绑定](https://www.jianshu.com/p/ec05e3e7d319)
  
###fss-router
#####背景
在Android开发中，如果要从当前Activity跳转到下一个Activity，你可能会直接使用Intent的方式跳转，如下：
```
Intent intent = new Intent(this,XXXActivity.class);
intent.putExtra("name","value");  //传递参数
startActivity(intent);
```
或者你也可能使用阿里的ARouter路由框架，如下：
```
ARouter.getInstance().build("/xxx/xxx")
                    .withString("name","value")   //传递参数
                    .navigation(XXXActivity.class);
```
不管使用哪种方式，你都会发现如下问题：
1，路由要携带的参数有哪些？各个参数的key值是什么？哪些参数是必传？这些参数是什么意思...
2，对应的Activity是显示启动还是隐式启动，如果是隐式启动对应的action是什么，如果有requestCode，category，flags又该设置为多少
3，无法统一管理路由，到处可见的Intent与startActivity等等，路由方式很随意，哪里想跳就在哪儿写，想传什么参数就传什么。
4，代码量多，传的参数越多，要跳转的地方越多，代码量也就越多。  

在这种背景下，笔者便有了灵感，写出了FssRouter路由框架，我们看看FssRouter是怎么解决这些问题的。
#####使用
我们先看一下使用自定义路由API的方式，使用这种方式需要声明一个接口，在接口中声明路由信息。
现在假设我们有两个Activity，分别是MainActivity，DetailActivity，
现在的代码是这样：
```
public class MainActivity extends AppCompatActivity {
    ...
}
```
```
public class DetailActivity extends AppCompatActivity {
    ...
}
```
然后我们声明一个java接口,接口名随意,这里为ITestRouteApi
```
@RouteApi("test") //定义为路由Api，“test”为这个路由api的分组
public interface ITestRouteApi {
  @RouteActivity(DetailActivity.class) //定义路由的目的Activity
  void navigateToDetailActivity(Context context); //第一个参数必须为Context类型
}
```
声明上面的接口api后，然后再编译项目，然后我们就可以使用如下的方式进行路由了。
```
public class MainActivity extends AppCompatActivity {
   ...
   void xxx(){
     //使用下面的方式进行路由
     FssRouteApi.TEST.navigateToDetailActivity(MainActivity.this);
   }
}

```
上面是一个简单的例子，我们在看看其他情况。
### 1，隐式意图启动
```
@RouteApi("test") //定义为路由Api，“test”为这个路由api的分组
public interface ITestRouteApi {
  @RouteActivity(action = "com.gongbo.fss.route.detail")
  void navigateToDetailActivity(Context context);
}
```
### 2，携带路由参数
```
@RouteApi("test") //定义为路由Api，“test”为这个路由api的分组
public interface ITestRouteApi {
  @RouteActivity(action = "com.gongbo.fss.route.detail")
  void navigateToDetailActivity(Context context, @Extra("value") int value); 
}
```
### 3，携带默认路由参数
```
@RouteApi("test") //定义为路由Api，“test”为这个路由api的分组
public interface ITestRouteApi {
  @RouteActivity(DetailActivity.class)
  @DefaultExtra(
      name = "defaultValue",
      defaultValue = "hello",
      type = String.class
  )
  void navigateToDetailActivity(Context context);
}
```
### 4，设置requestCode，category，flags，路由动画等
```
@RouteApi("test") //定义为路由Api，“test”为这个路由api的分组
public interface ITestRouteApi {
  @RouteActivity(
            value = DetailActivity.class,             //设置路由的目标Activity
            requestCode = 1234,                       //设置requestCode
            category = Intent.CATEGORY_DEFAULT,       //设置category
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK,  //设置falgs
            enterAnim = android.R.anim.fade_in,       //设置activity进入动画
            exitAnim = android.R.anim.fade_out        //设置activity退出动画
  )
  void navigateToDetailActivity(Context context);
}
```
### 5,携带Uri,设置type等
```
@RouteApi("test") //定义为路由Api，“test”为这个路由api的分组
public interface ITestRouteApi {
  @RouteActivity(action = Intent.ACTION_VIEW)
  void routeToView(Context context, Uri data);

  @RouteActivity(
            action = Intent.ACTION_GET_CONTENT,
            requestCode = 2,
            category = Intent.CATEGORY_OPENABLE,
            type = "video/*")
  void navigateToGetContent(Context context);
}
```

## 自动创建Api
上面展示了如何自定义一个路由Api，并如何进行路由。接下来我们看一种更简单的方式实现路由。
我们在的DetailActivity上声明一个注解@Route，编译项目
```
@Route
public class DetailActivity extends AppCompatActivity {
    ...
}
```
然后我们在MainActivity中便可以直接这样来跳转到DetailActivity了
```
public class MainActivity extends AppCompatActivity {
   ...
   void xxx(){
     //使用下面的方式进行路由
     FssRouteApi.DEFAULT.navigateToDetailActivity(MainActivity.this);
   }
}
```
这是因为在编译时，FssRouter会帮我们自动生成对应的路由Api，如下：
![图片.png](https://upload-images.jianshu.io/upload_images/18427893-7748821ee13c6697.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
IDefaultRouteApi.java文件的具体内容如下：
```
public interface IDefaultRouteApi {
  @RouteActivity(DetailActivity.class)
  void navigateToDetailActivity(Context context);
}
```
因此，使用@Route注解便可以帮我们自动生成路由Api，这使得我们不必自定义路由API便可以实现路由，这种方式相对更简单，使用@Route注解，你几乎可以完成上面自定义Api的所有工作。笔者也更推荐使用这种方式,以上介绍了部分fss路由框架，若想了解更多，请参考[Android开发利器-FSS开源框架路由](https://www.jianshu.com/p/ab618a57adc3)

###fss-adapter
fss-adapter提供了多种适配器用法，内容较多，本文只介绍CommonAdapter
1,使用 **CommonAdapter**
```Java
CommonAdapter adapter = new CommonAdapter<String>(this, Arrays.asList("1", "2", "3"), R.layout.layout_list_item) {
            @Override
            protected void setView(CommonViewHolder holder, String str, int position) {
                TextView textView = holder.getView(R.id.tv_text);
                textView.setText(str);
                //可以用holder.setText(R.id.tv_text, str);代替
            }
        };
```
相对来说使用fss-adapter大大简化了适配器开发，如果想了解更多，请参考[Android开发利器-FSS开源框架之ListView，RecyclerView适配器](https://www.jianshu.com/p/0dfc654324d4)

### fss-runpriority
###背景
在Android开发中，Activity或Fragment中经常会用initView，initListener，initData三个方法来初始化，那么你可能会将这三个方法声明在父类中，然后子类继承实现即可，如下：
  ```Java
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ...
        initView();
        initData();
        initListener();
    }

    protected void initView() {
    }

    protected void initData() {
    }

    protected void initListener() {
    }
```
但如果这样做，initView，initData，initListener这三个方法的执行顺序固定了，然而有的时候我并不想以initView，initData，initListener 这样的顺序来执行，而是initView，initListener，initData或者其他顺序执行。fss_runpriority的出现便是为了解决这个问题。
然后在父类注册需要调用的方法：
```
//构造运行优先级方法
RunPriorityInfo runPriorityInfo = new RunPriorityInfo.Builder(this)
                .addMethod("initView") //对应方法：initView()
                //可以传任意个参数，但是要求必须存在该方法
                //.addMethod("initData", 12) 对应方法：initData(int xxx);
                //.addMethod("initData", 15L,"hello") 对应方法：initData(long xxx,String xxx);
                .addMethod("initData")
                .addMethod("initListener")
                .build();
//调用运行优先级方法，默认调用顺序为:initView() -> initData() -> initListener(),子类可使用@RunPriority注解自定义调用顺序
RunPriorityUtils.call(runPriorityInfo);
```

下面看一个Android的Activity的例子
```
class BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ...
        //构造运行优先级方法
        RunPriorityInfo runPriorityInfo = new RunPriorityInfo.Builder(this)
                .addMethod("initView")
                .addMethod("initData")
                .addMethod("initListener")
                .build();
        //调用运行优先级方法，默认调用顺序为:initView() -> initData() -> initListener(),子类可使用@RunPriority注解自定义调用顺序
        RunPriorityUtils.call(runPriorityInfo);
    }
    
    protected void initView() {
    }

    protected void initData() {
    }

    protected void initListener() {
    }
}
```
然后子类就可以根据需要自定义优先级，如下：
```
class TestActivity extends BaseActivity {
    //声明为高优先级，会优先调用
    @RunPriority(Priority.HIGH)
    @Override
    protected void initView() {
        super.initView();
        Log.i("RunPriority", "initView");
    }

    //声明为低优先级，会最后调用
    @RunPriority(Priority.LOW)
    @Override
    protected void initData() {
        super.initData();
        Log.i("RunPriority", "initData");
    }

    //声明为普通优先级，这也是默认的优先级，因此也可以不写
    //@RunPriority(Priority.NORMAL)  
    @Override
    protected void initListener() {
        super.initListener();
        Log.i("RunPriority", "initListener");
    }
}
```
上面的代码执行顺序是initView>initListener>initData。
  
- [Android开发利器-FSS开源框架之绑定](https://www.jianshu.com/p/ec05e3e7d319)
- [Android开发利器-FSS开源框架路由](https://www.jianshu.com/p/ab618a57adc3)
- [Android开发利器-FSS开源框架之方法运行优先级](https://www.jianshu.com/p/1606421edc7a)
- [Android开发利器-FSS开源框架之fss_common](https://www.jianshu.com/p/c861716d1421)
- [Android开发利器-FSS开源框架之ListView，RecyclerView适配器](https://www.jianshu.com/p/0dfc654324d4)
