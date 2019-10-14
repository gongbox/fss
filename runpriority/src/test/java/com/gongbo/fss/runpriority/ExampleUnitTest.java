package com.gongbo.fss.runpriority;

import com.gongbo.fss.runpriority.annotation.RunPriority;
import com.gongbo.fss.runpriority.model.RunPriorityInfo;

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

                RunPriorityUtils.callAll(runPriorityInfo);
            }

            @RunPriority(priority = Priority.LOW)
            private void initView() {
                System.out.println("initView");
            }

            @RunPriority(priority = Priority.HIGH)
            private void initData(Integer value) {
                System.out.println("initData:" + value);
            }

            private void initListener(Long value, String value2) {
                System.out.println("initListener:" + value + ":" + value2);
            }
        }

        new Test();
    }
}