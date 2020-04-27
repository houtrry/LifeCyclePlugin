package com.houtrry.asm_lifecycle_plugin


import com.android.build.gradle.BaseExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
/**
 * @author: houtrry
 * @date: 2020/4/27 14:00
 * @version: $
 * @description:
 */
class LifeCyclePlugin implements Plugin<Project>{
    @Override
    void apply(Project project) {
        println "<===${project.name}====>"
        def android = project.extensions.findByType(BaseExtension.class)
        android.registerTransform(new BytecodeFixTransform())
    }
}
