package com.gongbo.fss.demo.adapter;

import java.util.ArrayList;
import java.util.List;

public class ListDataModel {
    public static List<String> getDatas() {
        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            datas.add(i + "");
        }
        return datas;
    }
}
