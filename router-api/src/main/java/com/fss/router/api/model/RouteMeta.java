package com.fss.router.api.model;


import android.net.Uri;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 路由信息
 */
public class RouteMeta {
    private int routeType;
    private String group;
    private Class<?> destination;
    private String action;
    private String name;
    private int requestCode;
    private String[] category;
    private int flags = 0;
    private Uri data;
    private String type;
    private Map<String, Object> params;

    public RouteMeta() {
        this.params = new HashMap<String, Object>();
    }

    public RouteMeta(int routeType, String group, Class<?> destination, String action, String name, int requestCode, String[] category, Map<String, Object> params) {
        this.routeType = routeType;
        this.group = group;
        this.destination = destination;
        this.action = action;
        this.name = name;
        this.requestCode = requestCode;
        this.category = category;
        this.params = params;
    }

    public void addParam(String name, Object value) {
        if (params == null) {
            params = new HashMap<String, Object>();
        }
        params.put(name, value);
    }

    public int getRouteType() {
        return routeType;
    }

    public void setRouteType(int routeType) {
        this.routeType = routeType;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Class<?> getDestination() {
        return destination;
    }

    public void setDestination(Class<?> destination) {
        this.destination = destination;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(int requestCode) {
        this.requestCode = requestCode;
    }

    public String[] getCategory() {
        return category;
    }

    public void setCategory(String[] category) {
        this.category = category;
    }

    public int getFlags() {
        return flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    public Uri getData() {
        return data;
    }

    public void setData(Uri data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "RouteMeta{" +
                "routeType=" + routeType +
                ", group='" + group + '\'' +
                ", destination=" + destination +
                ", action='" + action + '\'' +
                ", name='" + name + '\'' +
                ", requestCode=" + requestCode +
                ", category=" + Arrays.toString(category) +
                ", flags=" + flags +
                ", data=" + data +
                ", type='" + type + '\'' +
                ", params=" + params +
                '}';
    }
}
