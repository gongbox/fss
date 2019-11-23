package com.fss.aop;

import org.aspectj.lang.annotation.Aspect;

//@Aspect
//public class AspectjConfig {
//
//    private static final String TAG = "Aspect";

//    @Pointcut("execution(* android.app.Activity+.onCreate(android.os.Bundle))")
//    private void onActivityCreate() {
//    }
//
//    @Pointcut("execution(* androidx.fragment.app.Fragment+.onCreateView(android.view.LayoutInflater,android.view.ViewGroup,android.os.Bundle))")
//    private void onAndroidxFragmentCreateView() {
//    }
//
//    @Pointcut("execution(* android.app.Fragment+.onCreateView(android.view.LayoutInflater,android.view.ViewGroup,android.os.Bundle))")
//    private void onFragmentCreateView() {
//    }
//
//    @After("onActivityCreate()")
//    public void afterOnAppCompatActivityCreate(JoinPoint joinPoint) {
//        Log.i(TAG, "afterOnAppCompatActivityCreate");
//        ViewGroup viewById = ((AppCompatActivity) joinPoint.getThis()).getDelegate().findViewById(android.R.id.content);
//        if (viewById != null && viewById.getChildCount() == 0) {
//            Log.i(TAG, "afterOnAppCompatActivityCreate>>>deal");
//            //获取布局
//            int layoutId = FssBind.getLayoutId(joinPoint.getThis());
//            if (layoutId >= 0) {
//                //设置布局
//                ((AppCompatActivity) joinPoint.getThis()).setContentView(layoutId);
//                //绑定参数（只绑定BaseFssActivity及其子类的参数）
//                FssBind.bind(joinPoint.getThis(), AppCompatActivity.class);
//
//                //构造运行优先级方法
//                RunPriorityInfo runPriorityInfo = new RunPriorityInfo.Builder(joinPoint.getThis())
//                        .addMethod("initView")
//                        .addMethod("initData")
//                        .addMethod("initListener")
//                        .build();
//                //调用运行优先级方法，默认调用顺序为:initView() -> initData() -> initListener(),子类可使用@RunPriority注解自定义调用顺序
//                RunPriorityUtils.call(runPriorityInfo);
//            }
//        }
//    }
//
//    @Around("onAndroidxFragmentCreateView()")
//    public View aroundOnAndroidxFragmentCreateView(ProceedingJoinPoint joinPoint) {
//        Log.i(TAG, "aroundOnAndroidxFragmentCreateView");
//        //获取布局
//        int layoutId = FssBind.getLayoutId(joinPoint.getThis());
//        if (layoutId >= 0) {
//            Log.i(TAG, "aroundOnAndroidxFragmentCreateView>>>deal");
//            LayoutInflater inflater = (LayoutInflater) joinPoint.getArgs()[0];
//            ViewGroup container = (ViewGroup) joinPoint.getArgs()[1];
//            //根据布局填充
//            return inflater.inflate(layoutId, container, false);
//        }
//        try {
//            return (View) joinPoint.proceed();
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
//        return null;
//    }
//
//    @After("onAndroidxFragmentCreateView()")
//    public void afterOnAndroidxFragmentCreateView(JoinPoint joinPoint) {
//        Log.i(TAG, "afterOnAndroidxFragmentCreateView");
//        if (joinPoint.getThis().getClass().getAnnotation(BindFragment.class) != null) {
//            Log.i(TAG, "afterOnAndroidxFragmentCreateView>>>deal");
//            //绑定参数（只绑定BaseFssFragment及其子类的参数）
//            FssBind.bind(joinPoint.getThis(), Fragment.class);
//
//            //构造运行优先级方法
//            RunPriorityInfo runPriorityInfo = new RunPriorityInfo.Builder(joinPoint.getThis())
//                    .addMethod("initView")
//                    .addMethod("initData")
//                    .addMethod("initListener")
//                    .build();
//            //调用运行优先级方法，默认调用顺序为:initView() -> initData() -> initListener(),子类可使用@RunPriority注解自定义调用顺序
//            RunPriorityUtils.call(runPriorityInfo);
//        }
//    }
//
//    @Around("onFragmentCreateView()")
//    public View aroundOnFragmentCreateView(ProceedingJoinPoint joinPoint) {
//        return aroundOnAndroidxFragmentCreateView(joinPoint);
//    }
//
//    @After("onFragmentCreateView()")
//    public void afterOnFragmentCreateView(JoinPoint joinPoint) {
//        afterOnAndroidxFragmentCreateView(joinPoint);
//    }
//}
