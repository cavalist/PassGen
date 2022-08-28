package com.cavalist.passgen.CharTypes

class SmallChars: CharType {
    override fun toString(): String {
        return "[a-z]"
    }

    override fun getCharString(): String {
        var resString = ""
        for (i in 97..122)
            resString += i.toChar().toString()

        return resString
    }

    override fun setCharString(charString: String) {
        TODO("Not yet implemented")
    }

}
