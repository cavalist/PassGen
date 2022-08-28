package com.cavalist.passgen.CharTypes

class SpecialCustomChars(): CharType {

    private var charString: String = ""

    override fun toString(): String {
        return "[" + this.charString + "]"
    }

    override fun getCharString(): String {
        return charString
    }

    override fun setCharString(charString: String){
        this.charString = charString
    }

}
