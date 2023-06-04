package calculator.input.command

import calculator.input.Handler

class CommandCollection(listOfCommands: List<Command>) {
    private val commands = mutableMapOf<String, Command>()

    init {
        listOfCommands.forEach { add(it) }
    }

    fun add(command: Command) {
        commands[command.name()] = command
    }

    fun get(commandName: String): Handler = commands[commandName] ?: throw CommandNotFoundException("Unknown command")
}
