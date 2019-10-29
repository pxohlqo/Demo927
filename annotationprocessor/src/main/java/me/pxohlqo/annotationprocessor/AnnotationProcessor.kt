package me.pxohlqo.annotationprocessor

import com.google.auto.service.AutoService
import me.pxohlqo.annotation.SolutionInfo
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement

@AutoService(Processor::class)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedOptions("kapt.kotlin.generated")
class AnnotationProcessor : AbstractProcessor() {
    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        return mutableSetOf(SolutionInfo::class.java.name)
    }

    override fun getSupportedSourceVersion(): SourceVersion {
        return super.getSupportedSourceVersion()
    }

    override fun process(
        annotation: MutableSet<out TypeElement>?,
        roundEnv: RoundEnvironment?
    ): Boolean {
        roundEnv?.getElementsAnnotatedWith(SolutionInfo::class.java)?.forEach {
            println("get elements ======================")
            println(it.simpleName)
        }
        return true
    }

    private fun writeFile() {

    }
}
