package calculator.calculator.exception

class UnknownVariableException(message: String, cause: Throwable? = null): InvalidExpressionException(message, cause)
