package org.grim3lt.tool.jumonmaker

import org.junit.Test

import org.junit.Assert.*

class JumonMakerTest {
    @Test
    fun makeSuccessful() {
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
        assertNotNull(fukkatsuNoJumon)
        assertEquals(fukkatsuNoJumon, "せたてひまもらべうだうきさそてぬはづぶい")
        print("kotlin:" + fukkatsuNoJumon)
    }

}