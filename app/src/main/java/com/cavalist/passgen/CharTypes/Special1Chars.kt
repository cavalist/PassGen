package com.cavalist.passgen.CharTypes

class Special1Chars: CharType {
    override fun toString(): String {
        return "[!@#$%^&*]"
    }

    override fun getCharString(): String {
        return "!@#\$%^&*"
    }

    override fun setCharString(charString: String) {
        TODO("Not yet implemented")
    }

}
