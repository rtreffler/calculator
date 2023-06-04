package calculator.input.command

import calculator.input.Handler

interface Command: Handler {
    fun name(): String
}
