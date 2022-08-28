package com.cavalist.passgen.CharTypes

interface CharType {
    override fun toString(): String
    abstract fun getCharString(): String
    abstract fun setCharString(charString: String)
}