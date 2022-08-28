package com.cavalist.passgen

import com.cavalist.passgen.CharTypes.*

class CheckboxAnalyzer {
    fun getCharType(id: Int): CharType {
        when (id) {
            R.id.checkBox_number -> {
                return NumberChars()
            }
            R.id.checkBox_small -> {
                return SmallChars()
            }
            R.id.checkBox_large -> {
                return LargeChars()
            }
            R.id.checkBox_special1 -> {
                return Special1Chars()
            }
            R.id.checkBox_special2 -> {
                return Special2Chars()
            }
            R.id.checkBox_specialCustom -> {
                return SpecialCustomChars()
            }
        }
        return NullCharType()
    }

}
