package com.houtrry.asm_lifecycle_plugin

import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.Transform
import com.android.build.api.transform.TransformException
import com.android.build.api.transform.TransformInvocation
import com.android.build.gradle.internal.pipeline.TransformManager
import com.android.build.api.transform.Format
import com.android.utils.FileUtils

class BytecodeFixTransform extends Transform {

    BytecodeFixTransform() {

    }

    @Override
    String getName() {
        return "=BytecodeFixTransform="
    }

    /**
     * 该方法表示指定输入类型，这里我们指定CONTENT_RESOURCES类型
     * @return
     */
    @Override
    Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS
    }

    /**
     * 该方法表示当前Transform的作用范围，这里我们指定SCOPE_FULL_PROJECT
     * @return
     */
    @Override
    Set<? super QualifiedContent.Scope> getScopes() {
        return TransformManager.SCOPE_FULL_PROJECT
    }

    /**
     * 该方法表示当前Transform是否支持增量编译
     * @return
     */
    @Override
    boolean isIncremental() {
        return false
    }

    @Override
    void transform(TransformInvocation transformInvocation) throws
            TransformException, InterruptedException, IOException {
        def inputs = transformInvocation.inputs
        def outputProvider = transformInvocation.outputProvider
        inputs.each {
            // jarInputs：各个依赖所编译成的 jar ⽂文件
            it.jarInputs.each {
                // dest:./app/build/intermediates/transforms/hencoderTransform/...
                File dest = outputProvider.getContentLocation(it.name, it.contentTypes, it.scopes, Format.JAR)
                println "============>>>>>>jarInputs, from-> ${it.file} to->${dest}"
                FileUtils.copyFile(it.file, dest)
            }
            // directoryInputs：本地 project 编译成的多个 class ⽂文件存放的⽬目录
            it.directoryInputs.each {
                // dest:./app/build/intermediates/transforms/hencoderTransform/...
                File dest = outputProvider.getContentLocation(it.name, it.contentTypes, it.scopes, Format.DIRECTORY)
                println "============>>>>>>directoryInputs, from-> ${it.file} to->${dest}"
                FileUtils.copyDirectory(it.file, dest)
            }
        }
    }
}