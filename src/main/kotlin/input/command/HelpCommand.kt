package calculator.input.command

class HelpCommand: Command {
    override fun name(): String = "/help"

    override fun handle() {
        println("""
            Simple integer calculator.
            It normalizes, tokenizes, converts infix notation to RPN and calculates the result.

            Normalization performs following replacements:
            - whitespace removal
            - multiple `+` reduce to single one
            - odd number of `-` reduce to single one
            - even number of `-` reduce to `+`
            - `-+` and `+-` reduce to `-`
            
            Infix to RPN notation conversion is handled by modified Dijkstra's Shunting Yard Algorithm.
            Standard described algorithm doesn't handle unary minus operator.

            Handles following operations:
            - addition `+`
                > 2+2
                4
            - subtraction `-`
                > 2-2
                0
            - multiplication `*`
                > 2*2
                4
            - integer division `/`
                > 2/2
                1
            - exponentiation `^`
                > 2^3
                8
            - parentheses `(`, `)`
                > (2+2)*2
                8
            - negation (unary operator) `-`
                > -5+1
                -4
            - neutral plus (unary operator) `+`
                > +5
                5
            - variable assignments of numbers and expressions `=`
                > n=5
                > n
                5
                > b=n+5
                > b
                10
        """.trimIndent())
    }
}
