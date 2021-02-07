package lib.sRAD.logic

fun Int.toCOP():String {
    var str = "$"
    var number = this.toString()

    for (i in number) {
        if(number.length % 3 == 0 && str.length!=1) {
            str += ",$i"
        }
        else {
            str += i
        }
        number = number.substring(1,number.length)
    }

    return str
}
