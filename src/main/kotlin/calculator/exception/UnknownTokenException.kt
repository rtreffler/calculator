package calculator.calculator.exception

class UnknownTokenException(message: String, cause: Throwable? = null): InvalidExpressionException(message, cause)
