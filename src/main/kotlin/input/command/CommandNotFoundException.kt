package calculator.input.command

class CommandNotFoundException(message: String, cause: Throwable? = null): IllegalArgumentException(message, cause)
