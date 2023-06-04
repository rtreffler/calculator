package calculator.calculator

import calculator.calculator.exception.InvalidExpressionException
import calculator.calculator.exception.UnknownTokenException
import calculator.calculator.exception.UnknownVariableException
import java.util.Stack

class Interpreter {
    private val variables = mutableMapOf<String, String>()

    fun calculate(tokens: List<Token>): String? {
        val stack = Stack<Token>()

        for (token in tokens) {
            when (token.type) {
                TokenType.Number,
                TokenType.Variable -> stack.push(token)
                TokenType.Operator -> handleOperatorToken(token, stack)
                else -> throw UnknownTokenException("Invalid expression")
            }
        }

        return if (stack.empty()) {
            null
        } else {
            getValue(stack.pop())
        }
    }

    private fun handleOperatorToken(token: Token, stack: Stack<Token>) {
        when (val operator = operators[token.value]) {
            is BinaryOperator -> {
                val num1 = getValue(stack.pop())
                val num2 = getValue(stack.pop())

                stack.push(Token(TokenType.Number, operator.calculate(num2, num1)))
            }
            is UnaryOperator -> {
                val value = getValue(stack.pop())

                stack.push(Token(TokenType.Number, operator.calculate(value)))
            }
            is AssignmentOperator -> {
                val value = getValue(stack.pop())
                val variable = stack.pop()

                if (variable.type !== TokenType.Variable) {
                    throw InvalidExpressionException("Invalid expression")
                }

                operator.assign(variables, variable.value, value)
            }
        }
    }

    private fun getValue(token: Token): String {
        return when (token.type) {
            TokenType.Number -> token.value
            TokenType.Variable -> {
                if (!variables.containsKey(token.value)) {
                    throw UnknownVariableException("Unknown variable")
                }

                variables[token.value] as String
            }
            else -> throw InvalidExpressionException("Invalid expression")
        }
    }
}
