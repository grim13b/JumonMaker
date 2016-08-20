package org.grim3lt.tool.jumonmaker

import org.junit.Test

import org.junit.Assert.*

class JumonMakerTest {
    fun assert(expended: String, actual: String) {
        assertNotNull(expended)
        assertEquals(expended, actual)
    }


    @Test
    fun successfulCreationHero() {
        val fukkatsuNoJumon = JumonMaker().make(
                name = "さいこう",
                exp = 65535,
                gold = 0,
                weapon = 0,
                armor = 0,
                shield = 0,
                herb = 0,
                key = 0,
                items = arrayOf(0, 0, 0, 0, 0, 0, 0, 0),
                hasDragonsScale = false,
                hasFightersRing = false,
                hasDeathNecklace = false,
                slashedDragon = false,
                slashedGolem = false,
                cryptoCode = 1)
        assert(fukkatsuNoJumon, "せたてひまもらべうだうきさそてぬはづぶい")
    }

    @Test
    fun successfulCreationRichHero() {
        val fukkatsuNoJumon = JumonMaker().make(
                name = "かねもち",
                gold = 65535)
        assert(fukkatsuNoJumon, "ぎざぞおけけふぐじまわげじでぶいかつはほ")
    }

    @Test
    fun successfulCreationSuperHero() {
        val fukkatsuNoJumon = JumonMaker().make(
                name = "さいつよ",
                exp = 65535,
                gold = 65535,
                weapon = 7,
                armor = 7,
                shield = 3,
                herb = 6,
                key = 6)
        assert(fukkatsuNoJumon, "ぬねひゆるるばとねねにのふどおけしろげず")
    }

    @Test
    fun successfulCreationFullItem() {
        val fukkatsuNoJumon = JumonMaker().make(
                name = "たおした",
                exp = 65535,
                gold = 65535,
                weapon = 7,
                armor = 7,
                shield = 3,
                herb = 6,
                key = 6,
                items = arrayOf(6, 7, 8, 9, 14, 4, 5, 6),
                hasDragonsScale = true,
                hasFightersRing = true,
                hasDeathNecklace = true,
                slashedDragon = true,
                slashedGolem = true,
                cryptoCode = 1)
        assert(fukkatsuNoJumon, "ははびにざずらえがにぬはへばよびぼどりけ")
    }

    @Test
    fun successfulCreationNewbie() {
        val fukkatsuNoJumon = JumonMaker().make(
                name = "しんじん")
        assert(fukkatsuNoJumon, "ちなるざぞでぶぬひそほめよれぎざぞざばぼ")
    }

    @Test
    fun successfulCreationShortName() {
        val fukkatsuNoJumon = JumonMaker().make(
                name = "あ")
        assert(fukkatsuNoJumon, "かこぶちなのへろぐぐぶいかこせつにつへむ")
    }
}