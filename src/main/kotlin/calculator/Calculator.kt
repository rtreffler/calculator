package calculator.calculator

class Calculator(private val normalizer: Normalizer, private val parser: Parser, private val interpreter: Interpreter) {
    fun calculate(line: String): String? {
        val normalized = normalizer.normalize(line)
        val tokens = parser.parse(normalized)

        return interpreter.calculate(tokens)
    }
}
