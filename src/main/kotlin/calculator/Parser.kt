package calculator.calculator

import calculator.calculator.exception.UnknownTokenException
import java.util.*

class Parser {
    fun parse(line: String): List<Token> {
        val tokens = tokenize(line)
        val result = mutableListOf<Token>()
        val stack = Stack<Token>()

        for (token in tokens) {
            when (token.type) {
                TokenType.Number -> result.add(token)
                TokenType.Variable -> result.add(token)
                TokenType.Operator -> {
                    val o1 = operators[token.value] as Operator

                    while (shouldAddToResult(o1, stack)) {
                        result.add(stack.pop())
                    }
                    stack.push(token)
                }
                TokenType.LeftParen -> {
                    stack.push(token)
                }
                TokenType.RightParen -> {
                    while (stack.peek().type != TokenType.LeftParen) {
                        result.add(stack.pop())
                    }
                    stack.pop()
                }
                else -> throw UnknownTokenException("Invalid identifier")
            }
        }
        while (stack.isNotEmpty()) {
            result.add(stack.pop())
        }

        return result
    }

    private fun shouldAddToResult(o1: Operator, stack: Stack<Token>): Boolean {
        if (stack.isEmpty()) {
            return false
        }
        val token = stack.peek()
        if (token.value !in operators) {
            return false
        }
        val o2 = operators[token.value] as Operator

        return o1.associativity == Associativity.Left && o1.priority <= o2.priority ||
               o1.associativity == Associativity.Right && o1.priority < o2.priority
    }

    private fun tokenize(line: String): List<Token> {
        val result = mutableListOf<Token>()
        val token = mutableListOf<Char>()

        for (chr in line) {
            when (true) {
                chr.isDigit() -> token.add(chr)
                chr.isLetter() -> token.add(chr)
                operators.containsKey(chr.toString()) -> {
                    addTokenToResult(token, result)
                    val operator = if (result.isEmpty() || result.last().type == TokenType.Operator || result.last().type == TokenType.LeftParen) ".$chr" else chr.toString()
                    result.add(Token(TokenType.Operator, operator))
                }
                (chr == tokens[TokenType.LeftParen]) -> {
                    addTokenToResult(token, result)
                    result.add(Token(TokenType.LeftParen, chr.toString()))
                }
                (chr == tokens[TokenType.RightParen]) -> {
                    addTokenToResult(token, result)
                    result.add(Token(TokenType.RightParen, chr.toString()))
                }
                else -> {
                    addTokenToResult(token, result)
                    result.add(Token(TokenType.Unknown, chr.toString()))
                }
            }
        }
        addTokenToResult(token, result)

        return result
    }

    private fun addTokenToResult(token: MutableList<Char>, result: MutableList<Token>) {
        if (token.isNotEmpty()) {
            val tokenStr = token.joinToString("")

            result.add(Token(getTokenType(tokenStr), tokenStr))
            token.clear()
        }
    }

    private fun getTokenType(token: String): TokenType {
        return if (token.all { it.isLetter() } && token.matches(Regex("^[a-zA-Z]+$"))) {
            TokenType.Variable
        } else if (token.all { it.isDigit() }) {
            TokenType.Number
        } else if (operators.containsKey(token)) {
            TokenType.Operator
        } else if (token == tokens[TokenType.LeftParen].toString()) {
            TokenType.LeftParen
        } else if (token == tokens[TokenType.RightParen].toString()) {
            TokenType.RightParen
        } else {
            TokenType.Unknown
        }
    }
}
