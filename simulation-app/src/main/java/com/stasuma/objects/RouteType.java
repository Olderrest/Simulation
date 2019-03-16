package com.stasuma.objects;

public enum RouteType {
    CIRCULAR, STRAIGHT;

    public static RouteType getType(String type){
        if (type.equals(RouteType.CIRCULAR.name())){
            return RouteType.CIRCULAR;
        }else {
            return RouteType.STRAIGHT;
        }
    }
}
