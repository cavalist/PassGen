package com.cavalist.passgen.CharTypes

class NumberChars: CharType {
    override fun toString(): String {
        return "[0-9]"
    }

    override fun getCharString(): String {
        var resString = ""
        for (i in 48..57)
            resString += i.toChar().toString()

        return resString
    }

    override fun setCharString(charString: String) {
        TODO("Not yet implemented")
    }
}
