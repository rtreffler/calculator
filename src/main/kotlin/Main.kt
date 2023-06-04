package calculator

import calculator.calculator.*
import calculator.calculator.exception.InvalidExpressionException
import calculator.input.Handler
import calculator.input.InputStrategy
import calculator.input.command.CommandCollection
import calculator.input.command.CommandNotFoundException
import calculator.input.command.ExitCommand
import calculator.input.command.HelpCommand

fun main() {
    val commands = CommandCollection(listOf(ExitCommand(), HelpCommand()))
    val strategy = InputStrategy(commands, Calculator(Normalizer(), Parser(), Interpreter()))
    var command: Handler? = null

    do {
        try {
            command = strategy.decide(readln().trim())
            command?.handle()
        } catch (e: CommandNotFoundException) {
            println(e.message)
        } catch (e: InvalidExpressionException) {
            println(e.message)
        } catch (e: Exception) {
            println("Invalid expression")
        }
    } while (command !is ExitCommand)
}
