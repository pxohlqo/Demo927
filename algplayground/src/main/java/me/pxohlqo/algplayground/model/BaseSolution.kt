package me.pxohlqo.algplayground.model

abstract class BaseSolution {

    var input: String? = ""

    open fun input(vararg inputString: String): BaseSolution {
        this.input = inputString.toString()
        return this
    }

    open fun result(): String {
        return solve(input?:"")
    }

    abstract fun solve(vararg input: Any): String

    companion object {
        fun String.toIntArray(s: String): List<Int> {
            return s.removeSurrounding("[", "]").split(",").map { it.toInt() }
        }
    }
}