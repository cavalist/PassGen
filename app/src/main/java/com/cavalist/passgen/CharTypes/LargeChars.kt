package com.cavalist.passgen.CharTypes

class LargeChars: CharType{


    override fun toString(): String {
        return "[A-Z]"
    }

    override fun getCharString(): String {
        var resString = ""
        for (i in 65..90)
            resString += i.toChar().toString()

        return resString
    }

    override fun setCharString(charString: String) {
        TODO("Not yet implemented")
    }

}
