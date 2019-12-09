package com.fss.demo.route.api;

import com.fss.router.annotation.Route;

/**
 * 可以通过destination属性配置路由的目的类，
 * 如果这个类在父模块中存在时，可以使用这种方式进行路由名，需要保证要路由的类在父模块存在
 */
@Route(
        destination = "com.fss.demo.route.RouteDetailActivity",
        group = "destination",
        name = "navigateToCurrentModuleActivity",
        desc = "路由到当前模块，这个类在当前模块存在，可以路由"
)
@Route(
        destination = "com.fss.demo.OtherModuleActivity",
        group = "destination",
        name = "navigateToOtherModuleActivity",
        desc = "路由到其他模块的Activity，请保证存在再进行路由"
)
@Route(
        destination = "com.fss.demo.OtherModuleService",
        group = "destination",
        name = "navigateToOtherModuleService",
        desc = "路由到其他模块的Service，请保证存在再进行路由"
)
public class DestinationRouteApi {
}
