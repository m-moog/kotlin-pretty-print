package de.kotlinPrettyPrint

fun prettyPrint(func: InsidePrettyPrint.() -> String): String{
    return func.invoke(InsidePrettyPrintImpl()).trimIndent()
}

interface InsidePrettyPrint{
    infix fun <T> Iterable<T>.printForEach(template: T.() -> String): String

    fun prettyPrint(func: InsidePrettyPrint.() -> String): String
}

private class InsidePrettyPrintImpl: InsidePrettyPrint{
    override fun <T> Iterable<T>.printForEach(template: T.() -> String): String {
        return joinToString(separator = "") { template.invoke(it) }
    }

    override fun prettyPrint(func: InsidePrettyPrint.() -> String): String {
        return func.invoke(InsidePrettyPrintImpl())
    }
}