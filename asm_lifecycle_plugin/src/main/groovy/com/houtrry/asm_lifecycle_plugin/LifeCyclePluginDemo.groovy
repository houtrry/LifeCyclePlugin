package com.houtrry.asm_lifecycle_plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class LifeCyclePluginDemo implements Plugin<Project> {

    @Override
    void apply(Project project) {
        println "===${project.name}===="
    }
}