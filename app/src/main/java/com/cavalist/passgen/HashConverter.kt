package com.cavalist.passgen

import java.util.*

class HashConverter {
    private lateinit var charset: CharArray

    fun setCharset(charset: CharArray) {
        // 외부에서 bytesToString 을 쓰기 전에 항상 먼저 호출해야한다.
        // 그럼에도 bytesToString 안에 넣지 못한 것은,
        // charset 을 입력 받아야 하기 때문인데
        // SelectedCharManager를 멤버로 가지면 꼬이게 되고,
        // byteToString에 매개변수로 받으면 지저분해진다.
        this.charset = charset
    }

    fun bytesToString(hash: ByteArray): String {
        println(Arrays.toString(hash))
        var resString = ""
        for (byte in hash)
            resString += ByteToChar(byte).toString()

        return resString
    }

    private fun ByteToChar(byte: Byte): Char{
        val scaleFactor = (charset.size / 256f)
        val unsignedByte = signedByteToUnsignedInt(byte)
        val index = (unsignedByte * scaleFactor).toInt()
        return charset[index]
    }

    private fun signedByteToUnsignedInt(byte: Byte): Int{
        // bitwise and 연산을 위해선, 자릿수가 같아야 한다.
        // 0xff 도 Int다.
        // 둘다 4바이트 32비트 Int로 맞춰진 상태에서 and 연산.
        // .toInt() 만 한 상태에선 여전히 음수이다.
        // 0xff = 0000 0000 1111 1111 를 and 연산하면
        // 앞의 부호 비트를 다 날려버려서  나머지 8비트는 어떤 숫자가 와도 양수가 된다.
        return byte.toInt() and 0xff
    }



}
