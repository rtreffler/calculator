package calculator.input

import calculator.calculator.Calculator
import calculator.input.command.CommandCollection

class InputStrategy(private val commandCollection: CommandCollection, private val calculator: Calculator) {
    fun decide(input: String): Handler? {
        return if (input.isEmpty()) {
            null
        } else if (input.first() == '/') {
            commandCollection.get(input)
        } else {
            ExpressionHandler(calculator, input)
        }
    }
}
