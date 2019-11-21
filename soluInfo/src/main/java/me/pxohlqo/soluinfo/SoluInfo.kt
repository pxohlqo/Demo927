package me.pxohlqo.soluinfo

import java.io.File
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic

@Target(AnnotationTarget.CLASS)
annotation class SolutionInfo(val title: String, val description: String)

@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes("me.pxohlqo.soluinfo.SolutionInfo")
@SupportedOptions(SoluInfoProcessor.CONTENT)
class SoluInfoProcessor : AbstractProcessor() {
    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        return mutableSetOf(SolutionInfo::class.java.name)
    }

    override fun getSupportedSourceVersion(): SourceVersion {
        return super.getSupportedSourceVersion()
    }

    override fun init(p0: ProcessingEnvironment?) {
        super.init(p0)
    }

    override fun process(
        annotation: MutableSet<out TypeElement>?,
        roundEnv: RoundEnvironment?
    ): Boolean {
        processingEnv.messager.printMessage(
            Diagnostic.Kind.WARNING,
            "=-=-=-=-=-=Start processing SoluInfo"
        )
        val soluInfoAry = mutableListOf<ArrayList<String>>()
        roundEnv!!.getElementsAnnotatedWith(SolutionInfo::class.java).forEach {
            val elemAnno = it.getAnnotation(SolutionInfo::class.java)
            val pakName = processingEnv.elementUtils.getPackageOf(it).qualifiedName
            val elemInfo = arrayListOf(elemAnno.title, elemAnno.description, "$pakName.${it.simpleName.toString()}")
            soluInfoAry.add(elemInfo)
        }

        writeFile(soluInfoAry)

        return true
    }

    private fun writeFile(list: List<ArrayList<String>>) {
        val kaptKotlinGeneratedDir =
            processingEnv.options[KAPT_KOTLIN_GENERATED_OPTION_NAME] ?: run {
                processingEnv.messager.printMessage(Diagnostic.Kind.ERROR, "Can't find the target directory for generated Kotlin files.")
                return
            }
        val contentDir = kaptKotlinGeneratedDir.replaceAfter("algplayground/", "src/main/assets/solutionsContent/solutions.CONTENT")
        if (list.isNotEmpty()) {
            val f = File(contentDir)
            f.writeText("")
            list.forEach {
                f.appendText("SOLUTION=${it[0]}\n")
                f.appendText("DESCRIPTION=${it[1]}\n")
                f.appendText("PATH=${it[2]}\n")
                f.appendText("\n")
            }
        }
    }

    companion object {
        const val KAPT_KOTLIN_GENERATED_OPTION_NAME = "kapt.kotlin.generated"
        const val CONTENT = "content"
    }
}
