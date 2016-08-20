JumonMaker
======================
ドラゴンクエスト（以下DQ1)のふっかつのじゅもんを作ります。  
拙作 Java 版 FukkatsuNoJumonBuilder の kotlin 移植です。  
このプログラムはSATOH_Yoshiyuki氏が公開されている「復活の呪文」資料室の情報を元に作成されました。

ドラゴンクエストはスクウェア・エニックス社の登録商標です。  

使い方
------
+ JumonMaker の make を実行します。

------

### 実装例 ###
    fun hoge() {
        val jumon = JumonMaker().make(
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
    }

すべての引数は省略可能です。  

    fun hoge() {
        // ゴールドカンストでスタート状態
        val jumon = JumonMaker().make(
                name = "かねもち",
                gold = 65535)
    }
    
    fun hani() {
        // 最強装備、EXP、Goldカンストでスタート状態
        val jumon = JumonMaker().make(
                name = "さいつよ",
                exp = 65535,
                gold = 65535,
                weapon = 7, // ロトのつるぎ
                armor = 7,  // ロトのよろい
                shield = 3, // みかがみのたて
                herb = 6,
                key = 6)
    }


パラメータの解説
----------------
実装例で使われているメソッドのパラメータを解説します。

主人公のステータスです。  
+ `name` : 主人公の名前をセットします。濁点半濁点を含む場合は２文字としてカウントされます。
+ `exp` : 主人公の経験値をセットします。 ６５５３５ までの数字で入力してください。
+ `gold` : 主人公の所持金をセットします。 ６５５３５ までの数字で入力してください。
+ `cryptoCode` : ふっかつのじゅもんに一様性を出さないために生成時にランダムな数字を用いていたようです。 ７ までの数字を入力してください。

----
主人公の装備品を数値でセットします。

+ `weapon` : 武器をセットします。次の何れかから選んでください。
  + 0 : なし
  + 1 : たけざお
  + 2 : こんぼう
  + 3 : どうのつるぎ
  + 4 : てつのつるぎ
  + 5 : はがねのつるぎ
  + 6 : ほのおのつるぎ
  + 7 : ロトのつるぎ

+ `armor` : よろいをセットします。次の何れかから選んでください。
  + 0 : なし
  + 1 : ぬののふく
  + 2 : かわのふく
  + 3 : くさりかたびら
  + 4 : てつのよろい
  + 5 : はがねのよろい
  + 6 : まほうのよろい
  + 7 : ロトのよろい

+ `shield` : 盾をセットします。次の何れかから選んでください。  
  + 0 : なし
  + 1 : かわのたて
  + 2 : てつのたて
  + 3 : みかがみのたて

----
やくそうとまほうのかぎをセットします。これらは固定で枠があり所有数だけを指定します。  
+ `herb` : やくそうの所有数をセットします。
+ `key` : まほうのかぎの所有数をセットします。

----
ストーリー上のフラグをセットします。 true が進行済み、 false が未進行です。
+ `hasDragonsScale` : りゅうのうろこを装備したかどうかをセットします。
+ `hasFightersRing` : せんしのゆびわを入手したかどうかをセットします。
+ `hasDeathNecklace` : しのくびかざりを入手したかどうかをセットします。
+ `slashedDragon` : ドラゴンを倒したかどうかをセットします。
+ `slashedGolem` : ゴーレムを倒したかどうかをセットします。

----
どうぐをは数値を Array でセットします。必ず 8 つでセットしてください。

+ `items:Array<Int>`
  + 0 : なし
  + 1 : たいまつ
  + 2 : せいすい
  + 3 : キメラのつばさ
  + 4 : りゅうのうろこ
  + 5 : ようせいのふえ
  + 6 : せんしのゆびわ
  + 7 : ロトのしるし
  + 8 : おうじょのあい
  + 9 : のろいのベルト
  + 10 : ぎんのたてごと
  + 11 : しのくびかざり
  + 12 : たいようのいし
  + 13 : あまぐものつえ
  + 14 : にじのしずく
  + 15 : （不明）

関連情報
--------
###
1. [SATOH_Yoshiyuki氏 「復活の呪文」資料室](http://www.imasy.or.jp/~yotti/dq-passwd.html "SATOH_Yoshiyuki氏 「復活の呪文」資料室")

ライセンス
----------
MIT License  
