package org.grim3lt.tool.jumonmaker

class JumonMaker {
    private val GOLD_LIMIT = 0xffff
    private val EXP_LIMIT = 0xffff

    private val NAME_MAP = mapOf(
            "０" to  0, "１" to  1, "２" to  2, "３" to  3, "４" to  4,
            "５" to  5, "６" to  6, "７" to  7, "８" to  8, "９" to  9,
            "あ" to 10, "い" to 11, "う" to 12, "え" to 13, "お" to 14,
            "か" to 15, "き" to 16, "く" to 17, "け" to 18, "こ" to 19,
            "さ" to 20, "し" to 21, "す" to 22, "せ" to 23, "そ" to 24,
            "た" to 25, "ち" to 26, "つ" to 27, "て" to 28, "と" to 29,
            "な" to 30, "に" to 31, "ぬ" to 32, "ね" to 33, "の" to 34,
            "は" to 35, "ひ" to 36, "ふ" to 37, "へ" to 38, "ほ" to 39,
            "ま" to 40, "み" to 41, "む" to 42, "め" to 43, "も" to 44,
            "や" to 45, "ゆ" to 46, "よ" to 47, "ら" to 48, "り" to 49,
            "る" to 50, "れ" to 51, "ろ" to 52, "わ" to 53, "を" to 54,
            "ん" to 55, "っ" to 56, "ゃ" to 57, "ゅ" to 58, "ょ" to 59,
            "゛" to 60, "゜" to 61, "−" to 62, "　" to 63)

    private val JUMON_MAP = mapOf(
             0 to "あ",  1 to "い",  2 to "う",  3 to "え",  4 to "お",
             5 to "か",  6 to "き",  7 to "く",  8 to "け",  9 to "こ",
            10 to "さ", 11 to "し", 12 to "す", 13 to "せ", 14 to "そ",
            15 to "た", 16 to "ち", 17 to "つ", 18 to "て", 19 to "と",
            20 to "な", 21 to "に", 22 to "ぬ", 23 to "ね", 24 to "の",
            25 to "は", 26 to "ひ", 27 to "ふ", 28 to "へ", 29 to "ほ",
            30 to "ま", 31 to "み", 32 to "む", 33 to "め", 34 to "も",
            35 to "や", 36 to "ゆ", 37 to "よ",
            38 to "ら", 39 to "り", 40 to "る", 41 to "れ", 42 to "ろ",
            43 to "わ",
            44 to "が", 45 to "ぎ", 46 to "ぐ", 47 to "げ", 48 to "ご",
            49 to "ざ", 50 to "じ", 51 to "ず", 52 to "ぜ", 53 to "ぞ",
            54 to "だ", 55 to "ぢ", 56 to "づ", 57 to "で", 58 to "ど",
            59 to "ば", 60 to "び", 61 to "ぶ", 62 to "べ", 63 to "ぼ")

    private val CRC_CODE = intArrayOf(
            0x88, 0xc4, 0x62, 0x31, 0x08, 0x84, 0x42, 0x21, 0x98, 0xcc, 0xe6, 0x73, 0xa9, 0xc4, 0x62, 0x31,
            0x5a, 0xad, 0xc6, 0x63, 0xa1, 0xc0, 0x60, 0x30, 0x38, 0x9c, 0x4e, 0xa7, 0xc3, 0xf1, 0x68, 0xb4,
            0xd0, 0x68, 0xb4, 0x5a, 0x2d, 0x06, 0x83, 0x51, 0x20, 0x10, 0x08, 0x84, 0x42, 0xa1, 0x40, 0xa0,
            0xf9, 0xec, 0xf6, 0x7b, 0xad, 0xc6, 0xe3, 0x61, 0x81, 0xd0, 0x68, 0xb4, 0xda, 0x6d, 0xa6, 0xd3,
            0xb2, 0xd9, 0xfc, 0xfe, 0xff, 0xef, 0x67, 0x23, 0x34, 0x1a, 0x0d, 0x96, 0x4b, 0x35, 0x8a, 0x45,
            0xaa, 0xd5, 0x7a, 0x3d, 0x8e, 0x47, 0xb3, 0x49, 0xa1, 0x40, 0xa0, 0x50, 0xa8, 0xd4, 0xea, 0x75,
            0xa0, 0xd0, 0x68, 0xb4, 0x5a, 0xad, 0xc6, 0x63, 0x7e, 0xbf, 0xcf, 0xf7, 0x6b, 0xa5, 0xc2, 0x61)

    // 変換前データの格納用
    private val data = IntArray(15)

    // 変換後データの格納用
    private val code = IntArray(20)
    
    fun make(name: String = "もょもと",
             exp: Int = 0,
             gold: Int = 0,
             weapon: Int = 0,
             armor: Int = 0,
             shield: Int = 0,
             herb: Int = 0,
             key: Int = 0,
             items: Array<Int> = arrayOf(0, 0, 0, 0, 0, 0, 0, 0),
             hasDragonsScale: Boolean = false,
             hasDeathNecklace: Boolean = false,
             hasFightersRing: Boolean = false,
             slashedDragon: Boolean = false,
             slashedGolem: Boolean = false,
             cryptoCode: Int = 1): String {
        // 変換前データを作る
        setName(name)
        setExp(exp)
        setGold(gold)
        setEquipment(weapon, armor, shield)
        setHerbsAndKeys(herb, key)
        setItems(items)
        setStoryFlags(hasDragonsScale,
                hasFightersRing,
                hasDeathNecklace,
                slashedDragon,
                slashedGolem)
        setCryptoCode(cryptoCode)
        setCRC()
        doExchange()

        return doEncode()
    }

    private fun setName(name: String) {
        when (name.length) {
            4 -> {
                data[7] = data[7] or NAME_MAP[name.substring(3, 4)]!!
                data[12] = data[12] or NAME_MAP[name.substring(2, 3)]!!
                data[1] = data[1] or (NAME_MAP[name.substring(1, 2)]!! shl 1)
                data[9] = data[9] or (NAME_MAP[name.substring(0, 1)]!! shl 2)
            }

            3 -> {
                data[12] = data[12] or NAME_MAP[name.substring(2, 3)]!!
                data[1] = data[1] or (NAME_MAP[name.substring(1, 2)]!! shl 1)
                data[9] = data[9] or (NAME_MAP[name.substring(0, 1)]!! shl 2)
            }

            2 -> {
                data[1] = data[1] or (NAME_MAP[name.substring(1, 2)]!! shl 1)
                data[9] = data[9] or (NAME_MAP[name.substring(0, 1)]!! shl 2)
            }

            1 -> {
                data[9] = data[9] or (NAME_MAP[name.substring(0, 1)]!! shl 2)
            }

            else -> throw UnsupportedOperationException()
        }
    }

    private fun setStoryFlags(hasDragonsScale: Boolean,
                         hasFightersRing: Boolean,
                         hasDeathNecklace: Boolean,
                         slashedDragon: Boolean,
                         slashedGolem: Boolean) {
        // 竜の鱗装備
        if (hasDragonsScale) {
            data[1] = data[1] or 0x80
        }

        // 戦士の指輪装備
        if (hasFightersRing) {
            data[1] = data[1] or 0x01
        }

        // 見張りドラゴン退治
        if (slashedDragon) {
            data[7] = data[7] or 0x40
        }

        // メルキドのゴーレム退治
        if (slashedGolem) {
            data[9] = data[9] or 0x02
        }

        // 死の首飾り入手
        if (hasDeathNecklace) {
            data[12] = data[12] or 0x40
        }
    }

    private fun setItems(items: Array<Int>) {
        fun Array<Int>.doConvert(ix1: Int, ix2: Int): Int {
            return (this[ix1] shl 4) or this[ix2]
        }

        if (items.isEmpty() || items.size != 8) {
            throw UnsupportedOperationException()
        }
        data[0] = items.doConvert(1, 0)
        data[11] = items.doConvert(3, 2)
        data[3] = items.doConvert(5, 4)
        data[8] = items.doConvert(7, 6)
    }

    private fun setHerbsAndKeys(herb: Int, key: Int) {
        // 魔法の鍵は上位4ビット、薬草は下位4ビット
        data[4] = (key shl 4) or herb
    }

    private fun setEquipment(weapon: Int, armor: Int, shield: Int) {
        // 武器上位3ビット　鎧2-5ビット　盾下位2ビット
        data[6] = (weapon shl 5) or (armor shl 2) or shield
    }

    private fun setGold(gold: Int) {
        val goldVal: Int = gold and GOLD_LIMIT

        // Goldの上位2バイトを格納
        data[5] = (goldVal shr 8) and 0xff

        // Goldの下位2バイトを格納
        data[10] = goldVal and 0xff
    }

    private fun setExp(exp: Int) {
        val expVal: Int = exp and EXP_LIMIT

        // 経験値の上位2バイトを格納
        data[2] = (expVal shr 8) and 0xff

        // 経験値の下位2バイトを格納
        data[13] = expVal and 0xff
    }

    private fun setCRC() {
        for (i in 0..13) {
            for (j in 0..7) {
                if (0 != data[i] and (0x80 shr j)) {
                    data[14] = data[14] xor CRC_CODE[i * 8 + j]
                }
            }
        }
    }

    private fun setCryptoCode(cryptoCode: Int) {
        fun Int.maskEqual(expression:Int): Boolean {
            return this and expression == expression
        }

        if (cryptoCode.maskEqual(0x01)) {
            data[7] = data[7] or 0x80
        } else if (cryptoCode.maskEqual(0x02)) {
            data[9] = data[9] or 0x01
        } else if (cryptoCode.maskEqual(0x04)) {
            data[12] = data[12] or 0x80
        } else {
            throw UnsupportedOperationException()
        }
    }

    private fun doExchange() {
        var j:Int = 0
        for(i in 14 downTo 0 step 3) {
            // 8bitコードを6bitコードに変換する。
            val work:Int = (data[i - 2] shl 16) or (data[i - 1] shl 8) or data[i]
            code[j++] =  work         and 0x3f
            code[j++] = (work shr 6 ) and 0x3f
            code[j++] = (work shr 12) and 0x3f
            code[j++] = (work shr 18) and 0x3f
        }

        // ひらがなの対比表に変換する。
        var work:Int = 0
        for (i in 0..code.size - 1) {
            work = (code[i] + work + 4) and 0x3f
            code[i] = work
        }
    }

    private fun doEncode(): String {
        var result: String = ""
        for (element in code) {
            result += JUMON_MAP[element]!!
        }
        return result
    }
}