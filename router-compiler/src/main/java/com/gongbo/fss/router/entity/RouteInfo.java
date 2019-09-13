package com.gongbo.fss.router.entity;

import com.gongbo.fss.router.annotation.Route;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.lang.model.element.TypeElement;

//路由信息解析
public class RouteInfo {
    public TypeElement typeElement;
    public List<Route> routes;

    public RouteInfo(TypeElement typeElement, Route... routes) {
        this.typeElement = typeElement;
        this.routes = new ArrayList<>(Arrays.asList(routes));
    }


}
