package calculator.calculator

enum class TokenType {
    Number,
    Operator,
    Variable,
    LeftParen,
    RightParen,
    Unknown,
}

data class Token(val type: TokenType, val value: String) {
    override fun toString(): String {
        return "Token(type: $type, value: $value)"
    }
}

val tokens = mapOf(
    TokenType.LeftParen to '(',
    TokenType.RightParen to ')',
)
