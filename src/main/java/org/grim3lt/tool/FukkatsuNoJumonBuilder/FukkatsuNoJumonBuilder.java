package org.grim3lt.tool.FukkatsuNoJumonBuilder;


import java.util.HashMap;


public class FukkatsuNoJumonBuilder {
    // password変換用のHashMap
    private HashMap<Integer, String> jumonMap = new HashMap<Integer, String>() {
        private static final long serialVersionUID = 1L;
        {
            put( 0, "あ"); put( 1, "い"); put( 2, "う"); put( 3, "え"); put( 4, "お");
            put( 5, "か"); put( 6, "き"); put( 7, "く"); put( 8, "け"); put( 9, "こ");
            put(10, "さ"); put(11, "し"); put(12, "す"); put(13, "せ"); put(14, "そ");
            put(15, "た"); put(16, "ち"); put(17, "つ"); put(18, "て"); put(19, "と");
            put(20, "な"); put(21, "に"); put(22, "ぬ"); put(23, "ね"); put(24, "の");
            put(25, "は"); put(26, "ひ"); put(27, "ふ"); put(28, "へ"); put(29, "ほ");
            put(30, "ま"); put(31, "み"); put(32, "む"); put(33, "め"); put(34, "も");
            put(35, "や"); put(36, "ゆ"); put(37, "よ");
            put(38, "ら"); put(39, "り"); put(40, "る"); put(41, "れ"); put(42, "ろ");
            put(43, "わ");
            put(44, "が"); put(45, "ぎ"); put(46, "ぐ"); put(47, "げ"); put(48, "ご");
            put(49, "ざ"); put(50, "じ"); put(51, "ず"); put(52, "ぜ"); put(53, "ぞ");
            put(54, "だ"); put(55, "ぢ"); put(56, "づ"); put(57, "で"); put(58, "ど");
            put(59, "ば"); put(60, "び"); put(61, "ぶ"); put(62, "べ"); put(63, "ぼ");
        }
    };

    // 名前変換用のHashMap
    private HashMap<String, Integer> nameMap = new HashMap<String, Integer>() {
        private static final long serialVersionUID = 2L;
        {
            put("０",  0); put("１",  1); put("２",  2); put("３",  3); put("４",  4);
            put("５",  5); put("６",  6); put("７",  7); put("８",  8); put("９",  9);
            put("あ", 10); put("い", 11); put("う", 12); put("え", 13); put("お", 14);
            put("か", 15); put("き", 16); put("く", 17); put("け", 18); put("こ", 19);
            put("さ", 20); put("し", 21); put("す", 22); put("せ", 23); put("そ", 24);
            put("た", 25); put("ち", 26); put("つ", 27); put("て", 28); put("と", 29);
            put("な", 30); put("に", 31); put("ぬ", 32); put("ね", 33); put("の", 34);
            put("は", 35); put("ひ", 36); put("ふ", 37); put("へ", 38); put("ほ", 39);
            put("ま", 40); put("み", 41); put("む", 42); put("め", 43); put("も", 44);
            put("や", 45); put("ゆ", 46); put("よ", 47);
            put("ら", 48); put("り", 49); put("る", 50); put("れ", 51); put("ろ", 52);
            put("わ", 53); put("を", 54); put("ん", 55);
            put("っ", 56); put("ゃ", 57); put("ゅ", 58); put("ょ", 59);
            put("゛", 60); put("゜", 61); put("−", 62); put("　", 63);
        }
    };

    // マジックコード
    private int[] crcMagic =
            {
                    0x88, 0xc4, 0x62, 0x31, 0x08, 0x84, 0x42, 0x21,
                    0x98, 0xcc, 0xe6, 0x73, 0xa9, 0xc4, 0x62, 0x31,
                    0x5a, 0xad, 0xc6, 0x63, 0xa1, 0xc0, 0x60, 0x30,
                    0x38, 0x9c, 0x4e, 0xa7, 0xc3, 0xf1, 0x68, 0xb4,
                    0xd0, 0x68, 0xb4, 0x5a, 0x2d, 0x06, 0x83, 0x51,
                    0x20, 0x10, 0x08, 0x84, 0x42, 0xa1, 0x40, 0xa0,
                    0xf9, 0xec, 0xf6, 0x7b, 0xad, 0xc6, 0xe3, 0x61,
                    0x81, 0xd0, 0x68, 0xb4, 0xda, 0x6d, 0xa6, 0xd3,
                    0xb2, 0xd9, 0xfc, 0xfe, 0xff, 0xef, 0x67, 0x23,
                    0x34, 0x1a, 0x0d, 0x96, 0x4b, 0x35, 0x8a, 0x45,
                    0xaa, 0xd5, 0x7a, 0x3d, 0x8e, 0x47, 0xb3, 0x49,
                    0xa1, 0x40, 0xa0, 0x50, 0xa8, 0xd4, 0xea, 0x75,
                    0xa0, 0xd0, 0x68, 0xb4, 0x5a, 0xad, 0xc6, 0x63,
                    0x7e, 0xbf, 0xcf, 0xf7, 0x6b, 0xa5, 0xc2, 0x61
            } ;


    // 変換前データの格納用
    private Integer[] data = new Integer[15];

    // 変換後データの格納用
    private Integer[] code = new Integer[20];

    private StringBuffer jumon = new StringBuffer();

    /**
     * コンストラクタ
     */
    private FukkatsuNoJumonBuilder() {
    }

    public static FukkatsuNoJumonBuilder build(DQ1Character character) {
        FukkatsuNoJumonBuilder result = new FukkatsuNoJumonBuilder();

        result.initialize();
        result.doProcess(character);

        return result;
    }

    private void initialize() {
        this.jumon = new StringBuffer();

        // 内部判定用のArray類を初期化
        for(int i = 0; i < data.length; i++) {
            data[i] = 0;
        }

        for(int i = 0; i < code.length; i++) {
            code[i] = 0;
        }

    }

    private void doProcess(DQ1Character character) {

        // 変換前データを作る
        // 名前の変換
        this.setName2Data(character);

        // 経験値の変換
        this.setExp(character);

        // 所持金の変換
        this.setGold(character.getGold());

        // 武器 盾 鎧三点セットの変換
        this.setEquip(character);

        // 鍵 薬草の変換
        this.setKeyHerb(character);

        // アイテムx8の変換
        this.setItem(character);

        // 各種フラグの設定
        this.setFlags(character);

        // チェックコード（0-7）の設定
        this.setCryptCode(character.getCryptCode());

        // マジックコートを計算
        this.setMagicCode();

        // 変換後データを作る
        this.exchangeData2Code();

        // エンコードする
        this.exchangeCode2Jumon();
    }

    public String getJumon() {
        return jumon.toString();
    }

    private void exchangeCode2Jumon() {
        for(Integer element : code) {
            jumon.append(jumonMap.get(element));
        }
    }

    private void exchangeData2Code() {
        int j = 0;
        int work;

        // 8bitコードを6bitコードに変換する。
        for(int i = 14; i >= 0; i -= 3) {
            work = data[i - 2] << 16 | data[i - 1] << 8 | data[i];

            code[j++] =  work        & 0x003f;
            code[j++] = (work >> 6 ) & 0x003f;
            code[j++] = (work >> 12) & 0x003f;
            code[j++] = (work >> 18) & 0x003f;
        }

        // ひらがなの対比表に変換する。
        work = 0;
        for(int i = 0; i < code.length; i ++) {
            work = (code[i] + work + 4) & 0x3f;
            code[i] = work;
        }
    }


    private void setMagicCode()	{
        for (int i = 0; i < 14; i ++) {
            for (int j = 0; j < 8; j ++ ) {
                if (0 != (data[i] & (0x80 >> j))) {
                    data[14] ^= crcMagic[i * 8 + j] ;
                }
            }
        }
    }

    private void setCryptCode(int cryptocode) {
        if((cryptocode & 0x01) == 0x01) {
            data[7] = data[7] | 0x80;
        }

        if((cryptocode & 0x02) == 0x02) {
            data[9] = data[9] | 0x01;
        }

        if((cryptocode & 0x04) == 0x04) {
            data[12] = data[12] |0x80;
        }
    }

    private void setFlags(DQ1Character character) {
        // 竜の鱗装備
        if(character.hasRyuNoUroko()) {
            data[1] = data[1] | 0x80;
        }

        // 戦士の指輪装備
        if(character.hasSenshiNoYubiwa()) {
            data[1] = data[1] | 0x01;
        }

        // 見張りドラゴン退治
        if(character.broughtDownDragon()) {
            data[7] = data[7] | 0x40;
        }

        // メルキドのゴーレム退治
        if(character.broughtDownGolem()) {
            data[9] = data[9] | 0x02;
        }

        // 死の首飾り入手
        if(character.hasShiNoKubikazari()) {
            data[12] = data[12] | 0x40;
        }
    }

    private void setItem(DQ1Character character) {
        int tmpU;
        int tmpL;

        // アイテム1　アイテム2を変換
        tmpL = character.getItem(0);
        tmpU = character.getItem(1);

        data[0] = (tmpU << 4) | tmpL;

        // アイテム3　アイテム4を変換
        tmpL = character.getItem(2);
        tmpU = character.getItem(3);

        data[11] = (tmpU << 4) | tmpL;

        // アイテム5　アイテム6を変換
        tmpL = character.getItem(4);
        tmpU = character.getItem(5);

        data[3] = (tmpU << 4) | tmpL;

        // アイテム7　アイテム8を変換
        tmpL = character.getItem(6);
        tmpU = character.getItem(7);

        data[8] = (tmpU << 4) | tmpL;


    }

    private void setKeyHerb(DQ1Character character) {
        int key = character.getMahonokagi();
        int yakusou = character.getYakusou();

        // 魔法の鍵は上位4ビット、薬草は下位4ビット
        data[4] = (key << 4) | yakusou;
    }

    private void setEquip(DQ1Character character) {
        int w = character.getWeapon();
        int a = character.getArmor();
        int s = character.getShield();

        // 武器上位3ビット　鎧2-5ビット　盾下位2ビット
        data[6] = w << 5 | a << 2 | s;

    }

    private void setGold(int gold) {
        // Goldの上位2バイトを格納
        data[5] = (gold >> 8) & 0xff;

        // Goldの下位2バイトを格納
        data[10] = gold & 0xff;
    }


    private void setExp(DQ1Character character) {
        int exp = character.getExp();

        // 経験値の上位2バイトを格納
        data[2] = (exp >> 8) & 0xff;

        // 経験値の下位2バイトを格納
        data[13]= exp & 0xff;
    }

    private void setName2Data(DQ1Character character) {
        // 名前分解＆変換
        String jrName = character.getName();

        int mojiCode;

        // 一文字ずつ切り出す
        // HashMapから文字コードを引き出す
        switch(jrName.length()) {
            case 4:
                // 4文字目
                mojiCode = nameMap.get(jrName.substring(3, 4));
                data[7] = data[7] | (mojiCode);

            case 3:
                // 3文字目
                mojiCode = nameMap.get(jrName.substring(2, 3));
                data[12] = data[12] | (mojiCode);

            case 2:
                // 2文字目
                mojiCode = nameMap.get(jrName.substring(1, 2));
                data[1] = data[1] | (mojiCode << 1);

            case 1:
                // 1文字目
                mojiCode = nameMap.get(jrName.substring(0, 1));
                data[9] = data[9] | (mojiCode << 2);
        }
    }

}