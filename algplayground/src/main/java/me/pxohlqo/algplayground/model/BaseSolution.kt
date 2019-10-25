package me.pxohlqo.algplayground.model

abstract class BaseSolution {

    var input: String? = ""

//    abstract var name: String
//    abstract var desc: String
    // 使用注解动态加载标题和简介 https://blog.csdn.net/feint123/article/details/77861740

    open fun input(inputString: String?): BaseSolution {
        this.input = inputString
        return this
    }

    abstract fun solve(): String
}