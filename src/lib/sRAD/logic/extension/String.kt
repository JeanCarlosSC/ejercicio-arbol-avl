package lib.sRAD.logic

import java.lang.NumberFormatException
import java.util.*

fun String.isDouble(): Boolean {
    return try {
        this.toDouble()
        true
    }catch (numberFormat: NumberFormatException){
        false
    }
}

fun String.isInt(): Boolean {
    return try {
        this.toInt()
        true
    }catch (numberFormat: NumberFormatException){
        false
    }
}

fun String.toMutableList(): MutableList<String> {
    val mutableList = mutableListOf<String>()
    val tokens = StringTokenizer(this)
    while(tokens.hasMoreTokens()) {
        mutableList.add(tokens.nextToken())
    }
    return mutableList
}
