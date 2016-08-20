package org.grim3lt.tool.FukkatsuNoJumonBuilder;

import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;

public class DQ1Character
{
    private static final int GOLD_LIMIT = 0xffff;
    private static final int EXP_LIMIT = 0xffff;

    private String name = "";
    private int exp = 0;

    private int gold = 0;

    private int weapon;
    private int armor;
    private int shield;

    private ArrayList<Integer> items = new ArrayList<Integer>();

    private int yakusou;
    private int mahonokagi;

    private boolean ryunouroko;
    private boolean shinokubikazari;
    private boolean senshinoyubiwa;
    private boolean dragon;
    private boolean golem;

    private int cryptoCode;


    // 道具名変換用のHashMap
    private HashMap<String, Integer> itemMap = new HashMap<String, Integer>() {
        private static final long serialVersionUID = 6L;
        {
            put("なし",  0);
            put("たいまつ",  1);
            put("せいすい",  2);
            put("キメラのつばさ",  3);
            put("りゅうのうろこ",  4);
            put("ようせいのふえ",  5);
            put("せんしのゆびわ",  6);
            put("ロトのしるし",  7);
            put("おうじょのあい",  8);
            put("のろいのベルト",  9);
            put("ぎんのたてごと",  10);
            put("しのくびかざり",  11);
            put("たいようのいし",  12);
            put("あまぐものつえ",  13);
            put("にじのしずく",  14);
            put("（不明）",  15);
        }
    };

    // 濁点変換用のHashMap
    private HashMap<String, String> DakutenMap = new HashMap<String, String>() {
        private static final long serialVersionUID = 1L;
        {
            put("が", "か゛"); put("ぎ", "き゛"); put("ぐ", "く゛"); put("げ", "け゛"); put("ご", "こ゛");
            put("ざ", "さ゛"); put("じ", "し゛"); put("ず", "す゛"); put("ぜ", "せ゛"); put("ぞ", "そ゛");
            put("だ", "た゛"); put("ぢ", "ち゛"); put("づ", "つ゛"); put("で", "て゛"); put("ど", "と゛");
            put("ば", "は゛"); put("び", "ひ゛"); put("ぶ", "ふ゛"); put("べ", "へ゛"); put("ぼ", "ほ゛");
            put("ぱ", "は゜"); put("ぴ", "ひ゜"); put("ぷ", "ふ゜"); put("ぺ", "へ゜"); put("ぽ", "ほ゜");
        }
    };


    // 武器名変換用のHashMap
    private HashMap<String, Integer> weaponMap = new HashMap<String, Integer>() {
        private static final long serialVersionUID = 3L;
        {
            put("なし",  0);
            put("たけざお",  1);
            put("こんぼう",  2);
            put("どうのつるぎ",  3);
            put("てつのおの",  4);
            put("はがねのつるぎ",  5);
            put("ほのおのつるぎ",  6);
            put("ロトのつるぎ",  7);
        }
    };

    // 鎧名変換用のHashMap
    private HashMap<String, Integer> armorMap = new HashMap<String, Integer>() {
        private static final long serialVersionUID = 4L;
        {
            put("なし",  0);
            put("ぬののふく",  1);
            put("かわのふく",  2);
            put("くさりかたびら",  3);
            put("てつのよろい",  4);
            put("はがねのよろい",  5);
            put("まほうのよろい",  6);
            put("ロトのよろい",  7);
        }
    };

    // 盾名変換用のHashMap
    private HashMap<String, Integer> shieldMap = new HashMap<String, Integer>() {
        private static final long serialVersionUID = 5L;
        {
            put("なし",  0);
            put("かわのたて",  1);
            put("てつのたて",  2);
            put("みかがみのたて",  3);
        }
    };

//	// level判定用
//	private int[] levelTh =
//	{
//		    0,    7,   23,   47,  110,  220,  450,  800, 1300, 2000,
//		 2900, 4000, 5500, 7500,10000,13000,17000,21000,25000,29000,
//		33000,37000,41000,45000,49000,53000,57000,61000,65000,65535
//	};

    private DQ1Character() {
    }

    public static DQ1Character build(String name) {
        return DQ1Character.build(name, 0, 0, 0);
    }



    public static DQ1Character build(String name, int exp, int gold, int cryptoCode) {
        DQ1Character result = new DQ1Character();

        result.setName(name);
        result.setExp(exp);
        result.setGold(gold);
        result.setCryptoCode(cryptoCode);

        return result;
    }

    /**
     * 装備をコードでセットする。
     *
     * @param weapon 武器コード
     * @param armor 鎧コード
     * @param shield 盾コード
     */
    public void setEquip(int weapon, int armor, int shield) {
        this.weapon = weapon & 0x07;
        this.armor = armor & 0x07;
        this.shield = shield & 0x03;
    }

    /**
     * 装備を名称でセットする。
     *
     * @param weapon 武器の名前
     * @param armor 鎧の名前
     * @param shield 盾の名前
     */
    public void setEquip(String weapon, String armor, String shield) {
        this.setEquip(
                weaponMap.containsKey(weapon) ? weaponMap.get(weapon) : 0,
                armorMap.containsKey(armor) ? armorMap.get(armor) : 0,
                shieldMap.containsKey(shield) ? shieldMap.get(shield) : 0
        );
    }

    /**
     * 物語上のフラグをセットする。
     *
     * @param dragon ドラゴンを倒したか
     * @param golem ゴーレムを倒したか
     * @param senshinoyubiwa せんしのゆびわを持っているか
     * @param shinokubikazari しのくびかざりをもっているか
     * @param ryunouroko りゅうのうろこを装備したか
     */
    public void setFlags(boolean dragon, boolean golem, boolean senshinoyubiwa, boolean shinokubikazari, boolean ryunouroko) {
        this.dragon = dragon;
        this.golem = golem;
        this.senshinoyubiwa = senshinoyubiwa;
        this.shinokubikazari = shinokubikazari;
        this.ryunouroko = ryunouroko;
    }

    /**
     * ドラゴンを倒したかどうか。
     *
     * @return dragon
     */
    public boolean broughtDownDragon() {
        return dragon;
    }

    /**
     * ゴーレムを倒したかどうか。
     *
     * @return golem;
     */
    public boolean broughtDownGolem() {
        return golem;
    }

    /**
     * りゅうのうろこを装備したかどうか。
     *
     * @return the ryunouroko
     */
    public boolean hasRyuNoUroko() {
        return ryunouroko;
    }

    /**
     * @return the shinokubikazari
     */
    public boolean hasShiNoKubikazari() {
        return shinokubikazari;
    }

    /**
     * @return the senshinoyubiwa
     */
    public boolean hasSenshiNoYubiwa() {
        return senshinoyubiwa;
    }

    /**
     * 場所固定で数量だけ指定するやくそうとまほうのかぎをセット
     *
     * @param yakusou やくそうの所有数
     * @param mahounokagi まほうのかぎの所有数
     */
    public void setHarbAndKey(int yakusou, int mahounokagi) {
        this.yakusou = yakusou;
        this.mahonokagi = mahounokagi;
    }

    /**
     * とうぐスロットにどうぐをセットする
     *cryptocode
     * @param items どうぐコード（有効数7）
     */
    public void addItem(int ... items) {
        int count;
        int slot = 8 - this.items.size();
        int itemsLength = items.length > 8 ? 8 : items.length;

        count = slot > itemsLength ? itemsLength : slot;

        for(int i = 0; i < count; i ++) {
            this.items.add(items[i]);
        }
    }

    /**
     * どうぐスロットにどうぐをセットする
     *
     * @param items どうぐ名（有効数7）
     */
    public void addItem(String ... items) {
        for(String item : items) {
            this.addItem(itemMap.containsKey(item) ? itemMap.get(item) : itemMap.get("（不明）"));
        }
    }

    /**
     * どうぐスロットのindexで指定した位置からどうぐコードを取り出す
     *
     * @param index どうぐスロットの番号
     * @return どうぐコード
     */
    public int getItem(int index) {
        if(items.isEmpty()) {
            throw new NullPointerException();
        }
        return items.get(index);
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    protected void setName(String name) {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < name.length(); i ++) {
            // 名前一文字を分解
            String ch = name.substring(i, i + 1);
            String w;

            // 濁点半濁点の文字の場合はMapから変換
            if(DakutenMap.containsKey(ch)) {
                // ゛゜で一文字にカウントする
                w =DakutenMap.get(ch);
            } else {
                // 普通の文字の場合はそのまま
                w = ch;
            }
            sb.append(w);
        }

        // 濁点半濁点含めて４文字を取り出す
        // 場合によっては途切れるが気にしない
        sb.append("　　　　");
        this.name = sb.toString().substring(0, 4);
    }

    /**
     * @return the exp
     */
    public int getExp() {
        return exp;
    }
    /**
     * @param exp the exp to set
     */
    protected void setExp(int exp) {
        this.exp = exp & EXP_LIMIT;
    }

    /**
     * @return gold
     */
    public int getGold() {
        return gold;
    }

    /**
     * @param gold gold to set
     */
    protected void setGold(int gold) {
        this.gold = gold & GOLD_LIMIT;
    }


    /**
     * @return the weapon
     */
    public int getWeapon() {
        return weapon;
    }

    /**
     * @return the armor
     */
    public int getArmor() {
        return armor;
    }

    /**
     * @return the shield
     */
    public int getShield() {
        return shield;
    }

    /**
     * @return the yakusou
     */
    public int getYakusou() {
        return yakusou;
    }

    /**
     * @return the mahonokagi
     */
    public int getMahonokagi() {
        return mahonokagi;
    }

    /**
     * @return CryptCode
     */
    public int getCryptCode() {
        return cryptoCode;
    }

    /**
     * @param cryptoCode cryptoCode to set
     */
    public void setCryptoCode(int cryptoCode) {
        this.cryptoCode = cryptoCode & 0x07;
    }
}