package org.grim3lt.tool.FukkatsuNoJumonBuilder;

import org.junit.Test;

import static org.junit.Assert.*;

public class FukkatsuNoJumonBuilderTest {
    @Test
    public void build() throws Exception {
        DQ1Character dc = DQ1Character.build("さいこう");
        dc.setExp(65535);
        dc.setGold(0);

        dc.setEquip(0, 0, 0);
        dc.setHarbAndKey(0, 0);
        dc.setFlags(false, false, false, false, false);

        dc.addItem(0, 0, 0, 0, 0, 0, 0, 0);
        dc.setCryptoCode(1);

        FukkatsuNoJumonBuilder builder = FukkatsuNoJumonBuilder.build(dc);

        String jumon = builder.getJumon();

        assertNotNull(jumon);
        System.out.print("Java:" + jumon);
    }

}