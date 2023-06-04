package calculator.input

import calculator.calculator.Calculator

class ExpressionHandler(
    private val calculator: Calculator,
    private val line: String
): Handler {
    override fun handle() {
        calculator.calculate(line).also {
            if (it !== null) {
                println(it)
            }
        }
    }
}
