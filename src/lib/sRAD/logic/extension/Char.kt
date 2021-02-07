package lib.sRAD.logic

fun Char.isOperator(): Boolean {
    return this == '+' || this == '-' || this == '*' || this == '^' || this == '/'
}
