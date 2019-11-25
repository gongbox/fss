package com.fss.router.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class RouterPlugin implements Plugin<Project>{

    @Override
    void apply(Project project) {
        println('this is a router plugin...')
    }
}