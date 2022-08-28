package com.cavalist.passgen.CharTypes

class Special2Chars: CharType {
    override fun toString(): String {
        return "[`-=\\[];',./~()_+|{}:\"<>?]"
    }

    override fun getCharString(): String {
        return "`-=\\[];',./~()_+|{}:\"<>?"
    }

    override fun setCharString(charString: String) {
        TODO("Not yet implemented")
    }

}
