package 行列簡約化;

public class Fraction {

	//String型の分数bの分母を取得します
	public static int getBunbo(String b) {
		correctFraction(b);		//分数の形を整える
		int slash = b.indexOf("/");
		int bunbo = 0;
		if (slash == -1) { //スラッシュがない場合
			bunbo = 1;
		} else {
			String bunboS = b.substring(slash + 1, b.length()); //スラッシュから後ろを切り出し
			//				System.out.println(bunboS);
			char[] bunboC = bunboS.toCharArray(); //一文字ずつchar配列に代入
			int[] bunboD = new int[bunboC.length];
			for (int i = 0; i < bunboC.length; i++) {
				bunboD[i] = bunboC[i] - 48; //char配列をint配列に代入
				//						System.out.println(bunboD[i]);
			}
			for (int i = 0; i < bunboD.length; i++) {
				bunbo += bunboD[i] * Math.pow(10, bunboD.length - i - 1); //int配列を最終的にint型としてまとめ分母を算出
			}
		}
		return bunbo;
	}

	//String型の分数bの分子を取得します
	//整数を取得する場合もこのメソッドを使用してください
	public static int getBunshi(String b) {
		correctFraction(b);
		int slash = b.indexOf("/");
		int bunshi = 0;
		int negative = 1;
		if (slash == -1) { //スラッシュがない場合
			char[] bunshiC = b.toCharArray();
			int[] bunshiD = new int[bunshiC.length];
			for (int i = 0; i < bunshiC.length; i++) {
				if (bunshiC[i] == '-') { //"-"があったときにnegativeに-1を代入
					negative = -1;
				} else {
					bunshiD[i] = bunshiC[i] - 48; //char配列をint配列に代入
				}
			}
			for (int i = 0; i < bunshiD.length; i++) {
				//				System.out.println(bunshiD[i]);
				bunshi += bunshiD[i] * Math.pow(10, bunshiD.length - i - 1); //int配列を最終的にint型としてまとめ分母を算出
			}
		} else { //スラッシュがある場合
			String bunshiS = b.substring(0, slash); //スラッシュから前を切り出し
			//				System.out.println(bunshiS);
			char[] bunshiC = bunshiS.toCharArray(); //一文字ずつchar配列に代入
			int[] bunshiD = new int[bunshiC.length];
			for (int i = 0; i < bunshiC.length; i++) {
				if (bunshiC[i] == '-') { //"-"があったときにnegativeに-1を代入
					negative = -1;
				} else {
					bunshiD[i] = bunshiC[i] - 48; //char配列をint配列に代入
					//			System.out.println(bunshiD[i]);
				}
			}
			for (int i = 0; i < bunshiD.length; i++) {
				bunshi += bunshiD[i] * Math.pow(10, bunshiD.length - i - 1); //int配列を最終的にint型としてまとめ分母を算出
			}
		}
		return negative * bunshi;
	}

	//分数の形を整える
	public static String correctFraction(String b) {
		String bunsu;
		bunsu = b.replace(" ", "");	//余分なスペースを削除
		String[] bunshibunbo = b.split("[/]");
		for(int i = 0; i < bunshibunbo.length; i++) {	//先頭のゼロを削除
			bunshibunbo[i] = bunshibunbo[i].replaceFirst("^0*", "");
		}
		if(bunshibunbo.length == 1) {
			bunsu = bunshibunbo[0];
		}else if (bunshibunbo.length == 2){
			bunsu = bunshibunbo[0] + "/" + bunshibunbo[1];
		}
		return bunsu;
	}

//	public static void checkFraction(String b) {
//		if(b.matches("[0-9][/]")) {
//		}else {
//			throw new IllegalArgumentException("分数に文字が混入");
//		}
//	}

	//2つの整数を読み込み、String型の分数を返します
	public static String buildFraction(int bunshi, int bunbo) {
		String bunsu;
		if (bunbo == 1) { //分母が1の時に分子だけを返す
			bunsu = "" + bunshi;
		} else if (bunbo == 0) { //分母ゼロ例外処理
			throw new IllegalArgumentException("分母に0が代入");
		} else if (bunshi == 0) { //分子0の場合整数の0を代入
			bunsu = "0";
		} else if (bunbo < 0){	//分母が負の場合分子分母両方に-1をかける
			bunshi *= -1;
			bunbo *= -1;
			bunsu = bunshi + "/" + bunbo;
		} else {
			bunsu = bunshi + "/" + bunbo;
		}
		correctFraction(bunsu);
		return bunsu;
	}

	//約分されたbを返します
	public static String yakubun(String b) {
		int bunshi = getBunshi(b);
		if (bunshi < 0) {
			bunshi *= -1;
		}
		int bunbo = getBunbo(b);
		int saidaikouyakusuu = 1; //最大公約数
		for (int i = 2; i <= Math.min(bunbo, bunshi); i++) { //分母と分子どちらか小さい方の値まで繰り返し
			if (bunshi % i == 0 && bunbo % i == 0) { //分母と分子どちらでも割り切れるときに最大公約数を代入
				saidaikouyakusuu = i;
			}
		}
		//				System.out.println(saidaikouyakusuu);
		return buildFraction(getBunshi(b) / saidaikouyakusuu, getBunbo(b) / saidaikouyakusuu);
	}

	//b2に通分されたb1を返します どちらかに0が代入された場合はb1をそのまま返します
	public static String tsubun(String b1, String b2) {
		int saishoukoubaisu = 1; //最小公倍数の初期化
		String bunsu;
		if(getBunshi(b1) == 0 || getBunshi(b2) == 0) {
			bunsu = b1;
		}else {
			for (; saishoukoubaisu % getBunbo(b1) != 0 || saishoukoubaisu % getBunbo(b2) != 0; saishoukoubaisu++); //1から順にb1,b2の分母両方で割り切れる整数を探す
			//		System.out.println(saishoukoubaisu);
			bunsu = buildFraction(getBunshi(b1) * saishoukoubaisu / getBunbo(b1), getBunbo(b1) * saishoukoubaisu / getBunbo(b1));
		}
		return bunsu;
	}

	//b1 * b2 を返します
	public static String kakeru(String b1, String b2) {
		return yakubun(buildFraction(getBunshi(b1) * getBunshi(b2), getBunbo(b1) * getBunbo(b2)));
	}

	//b1 * (1/b2) を返します
	public static String waru(String b1, String b2) {
		return yakubun(kakeru(b1, gyakusu(b2)));
	}

	// 1/b を返します
	public static String gyakusu(String b) {
		int pn = 1;
		if (getBunshi(b) < 0) {
			pn = -1;
		}
		return buildFraction(pn * getBunbo(b), pn * getBunshi(b));
	}

	//b1+b2 を返します
	public static String tasu(String b1, String b2) {
		String bunsu;
		if(b1.equals("0") || b2.equals("0")) {	//0から引く場合の処理
			bunsu = buildFraction(getBunshi(b1) + getBunshi(b2), getBunbo(b1) * getBunbo(b2));
		}else {
			bunsu = yakubun(
					buildFraction(
							getBunshi(tsubun(b1, b2)) + getBunshi(tsubun(b2, b1)),
							getBunbo(tsubun(b1, b2))
							)
					);
		}
		return bunsu;
	}

	//b1-b2 を返します
	public static String hiku(String b1, String b2) {
		String bunsu;
		if (b1.equals("0") || b2.equals("0")) {	//0から引く場合の処理
			bunsu = buildFraction(getBunshi(b1) - getBunshi(b2), getBunbo(b1) * getBunbo(b2));
		}else {
			bunsu = yakubun(buildFraction(getBunshi(tsubun(b1, b2)) - getBunshi(tsubun(b2, b1)), getBunbo(tsubun(b1, b2))));
		}
		return bunsu;
	}
}
