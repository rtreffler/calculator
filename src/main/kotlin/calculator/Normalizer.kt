package calculator.calculator

class Normalizer {
    fun normalize(line: String): String {
        val regex = Regex("[-+]+")
        var tmpLine = line
            .replace(" ", "")

        while (tmpLine.matches(regex)) {
            tmpLine = reduce(line)
        }

        return tmpLine
    }

    private fun reduce(line: String): String =
        line
            .replace(Regex("(--)+"), "+")
            .replace(Regex("\\++"), "+")
            .replace(Regex("(\\+-)+"), "-")
            .replace(Regex("(-\\+)+"), "-")
}
