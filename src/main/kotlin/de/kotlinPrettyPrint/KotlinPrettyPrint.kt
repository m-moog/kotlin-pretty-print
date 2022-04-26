package de.kotlinPrettyPrint

/**
 * Allows for easy and readable printing of complex data structures
 *
 * Inside the lambda expression, the [InsidePrettyPrint.printForEach] function becomes available.
 * This function will apply a template to each Element of an Iterable<>.
 *
 * ### Example function call
 * ```kotlin
 * prettyPrint {
 *     """
 *         List of Programmers:
 *         ${employees.printForEach {
 *         """
 *             $firstName $lastName
 *             Age: $age
 *             Programming Languages: $programmingLanguages
 *         """
 *         }}
 *     """
 * }
 * ```
 * ### Example Output:
 * ```text
 * List of Programmers:
 *
 *  Hans Günther
 *  Age: 42
 *  Programming Languages: [Kotlin, java]
 *
 *  Jane Smith
 *  Age: 27
 *  Programming Languages: [Kotlin, Javascript]
 * ```
 *
 * For further explanation and examples see [GitHub](https://github.com/m-moog/kotlin-pretty-print)
 *
 * @author M. Moog - 26.04.2022
 */
fun prettyPrint(func: InsidePrettyPrint.() -> String): String{
    return func.invoke(InsidePrettyPrintImpl()).trimIndent()
}

interface InsidePrettyPrint{
    infix fun <T> Iterable<T>.printForEach(template: T.() -> String): String
}

private class InsidePrettyPrintImpl: InsidePrettyPrint{
    override fun <T> Iterable<T>.printForEach(template: T.() -> String): String {
        return joinToString(separator = "") { template.invoke(it) }
    }
}