package com.fss.runpriority;

import com.fss.runpriority.annotation.RunPriority;
import com.fss.runpriority.constant.Priority;
import com.fss.runpriority.model.RunPriorityInfo;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void runPriorityTest() {
        class Test {
            public Test() {
                RunPriorityInfo runPriorityInfo = new RunPriorityInfo.Builder(this)
                        .addMethod("initView")
                        .addMethod("initData", 12)
                        .addMethod("initListener", 15L, "123456")
                        .build();

                RunPriorityUtils.call(runPriorityInfo);
            }

//            @RunPriority(Priority.LOW)
//            private void initView() {
//                System.out.println("initView");
//            }

            @RunPriority(Priority.HIGH)
            private void initData(Integer value) {
                System.out.println("initData:" + value);
                int a=1/0;
            }

            private void initListener(Long value, String value2) {
                System.out.println("initListener:" + value + ":" + value2);
            }
        }

        new Test();
    }
}