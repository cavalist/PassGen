package com.cavalist.passgen

import com.cavalist.passgen.CharTypes.CharType
import com.cavalist.passgen.CharTypes.SpecialCustomChars


class SelectedCharManager {
    var charsFlags = IntArray(128){0}
    var charTypesMap = HashMap<String, CharType>()

    fun addCharType(charType: CharType) {
        val typeName = charType::class.simpleName.toString()
        charTypesMap.put(typeName, charType)
    }

    fun clear() {
        charTypesMap.clear()
    }

    fun getCharsetsViewString(): String {
        var resString = ""
        var specialString = ""
        for (entry in charTypesMap){
            if (entry.key.contains("Special")){
                specialString += entry.value.toString()
            }else{
                resString += entry.value.toString()
            }
        }

        resString += specialString

        return  resString
    }

    fun getCharset(): CharArray {
        syncCharFlagsToCharTypes()

        return charFlagsToCharArray()
    }

    private fun charFlagsToCharArray():CharArray{
        val list = arrayListOf<Char>()
        for (index in 0 until charsFlags.size)
            if (charsFlags[index] == 1)
                list.add(index.toChar())

        return list.toCharArray()
    }

    private fun syncCharFlagsToCharTypes() {
        clearCharFlags()
        for (entry in charTypesMap){
            addToCharFlags(entry.value.getCharString())
        }
    }

    private fun addToCharFlags(str: String) {
        for (ch in str.iterator()){
            charsFlags[ch.code] = 1
        }
    }

    private fun clearCharFlags() {
        charsFlags = IntArray(128){0}
    }

    fun getType(typeName: String): CharType {
        return charTypesMap[typeName]!!
    }

}
