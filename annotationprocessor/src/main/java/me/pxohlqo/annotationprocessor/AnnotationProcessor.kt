package me.pxohlqo.annotationprocessor;

import com.google.auto.service.AutoService
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement

@AutoService(Processor::class)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedOptions("kapt.kotlin.generated")
class AnnotationProcessor: AbstractProcessor() {
    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        return mutableSetOf(Solu)
    }
    override fun process(p0: MutableSet<out TypeElement>?, p1: RoundEnvironment?): Boolean {

    }
}
