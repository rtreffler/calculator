package calculator.input.command

class ExitCommand: Command {
    override fun name(): String = "/exit"

    override fun handle() {
        println("Bye!")
    }
}
