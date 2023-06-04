package calculator.calculator

import kotlin.math.pow

enum class Associativity {
    Left,
    Right,
    None,
}

abstract class Operator(val associativity: Associativity, val priority: Int)
abstract class BinaryOperator(associativity: Associativity, priority: Int): Operator(associativity, priority) {
    abstract fun calculate(a: Int, b: Int): Int
    abstract fun calculate(a: String, b: String): String
}
class PlusOperator: BinaryOperator(Associativity.Left, 10) {
    override fun calculate(a: Int, b: Int): Int = a + b
    override fun calculate(a: String, b: String): String = (a.toBigInteger() + b.toBigInteger()).toString()
}
class MinusOperator: BinaryOperator(Associativity.Left, 10) {
    override fun calculate(a: Int, b: Int): Int = a - b
    override fun calculate(a: String, b: String): String = (a.toBigInteger() - b.toBigInteger()).toString()
}
class MultiplicationOperator: BinaryOperator(Associativity.Left, 14) {
    override fun calculate(a: Int, b: Int): Int = a * b
    override fun calculate(a: String, b: String): String = (a.toBigInteger() * b.toBigInteger()).toString()
}
class DivisionOperator: BinaryOperator(Associativity.Left, 14) {
    override fun calculate(a: Int, b: Int): Int = a / b
    override fun calculate(a: String, b: String): String = (a.toBigInteger() / b.toBigInteger()).toString()
}
class ExponentialOperator: BinaryOperator(Associativity.Right, 18) {
    override fun calculate(a: Int, b: Int): Int = a.toDouble().pow(b.toDouble()).toInt()
    override fun calculate(a: String, b: String): String = (a.toBigInteger().pow(b.toInt())).toString()
}
abstract class UnaryOperator(associativity: Associativity, priority: Int): Operator(associativity, priority) {
    abstract fun calculate(a: Int): Int
    abstract fun calculate(a: String): String
}
class UnaryMinusOperator: UnaryOperator(Associativity.Right, 12) {
    override fun calculate(a: Int): Int = -a
    override fun calculate(a: String): String = (-a.toBigInteger()).toString()
}
class UnaryPlusOperator: UnaryOperator(Associativity.Right, 12) {
    override fun calculate(a: Int): Int = a
    override fun calculate(a: String): String = a
}
abstract class AssignmentOperator(associativity: Associativity, priority: Int): Operator(associativity, priority) {
    abstract fun assign(variables: MutableMap<String, Int>, variable: String, value: Int)
    abstract fun assign(variables: MutableMap<String, String>, variable: String, value: String)
}
class AssignmentOnlyOperator: AssignmentOperator(Associativity.Left, 8) {
    override fun assign(variables: MutableMap<String, Int>, variable: String, value: Int) {
        variables[variable] = value
    }
    override fun assign(variables: MutableMap<String, String>, variable: String, value: String) {
        variables[variable] = value
    }
}

val operators = mapOf(
    "+" to PlusOperator(),
    "-" to MinusOperator(),
    ".-" to UnaryMinusOperator(),
    ".+" to UnaryPlusOperator(),
    "=" to AssignmentOnlyOperator(),
    "*" to MultiplicationOperator(),
    "/" to DivisionOperator(),
    "^" to ExponentialOperator(),
)
