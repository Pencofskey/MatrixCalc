package 行列簡約化;

public class test {
	public static void test() {
		//getBunboテスト
//		System.out.println(Fraction.getBunbo("2/4"));

		//int型にchar型を代入するテスト
//		char a = '0';
//		int s = a;
//		System.out.println(s);

		//getBunshiテスト
//		System.out.println(Fraction.getBunshi("20/4"));
//		System.out.println(Fraction.getBunshi("-14"));
//		System.out.println("afefewa".indexOf("/"));

		//yakubunテスト
//		System.out.println(Fraction.yakubun("24/4"));

		//tubunテスト
//		System.out.println(Fraction.tsubun("1/4", "1/3"));
//		System.out.println(Fraction.tsubun("1/3", "1/4"));

		//分母0代入例外テスト
//		Fraction.buildFraction(23, 0);
		

		//分数テスト
//		System.out.println(Fraction.getBunbo("-12/22"));
//		System.out.println(Fraction.getBunshi("-12/22"));
//		System.out.println(Fraction.yakubun("-12/22"));
//		System.out.println(Fraction.yakubun("12/22"));
//		System.out.println(Fraction.buildFraction(-12, 22));
//		System.out.println(Fraction.kakeru("-12/22", "2/3"));
//		System.out.println(Fraction.kakeru("-12/22", "-2/3"));
//		System.out.println(Fraction.tsubun("-1/3", "1/4"));
//		System.out.println(Fraction.tasu("-1/3", "1/4"));
//		System.out.println(Fraction.tasu("-1/3", "-1/4"));
//		System.out.println(Fraction.tasu("1/3", "1/4"));
//		System.out.println(Fraction.waru("5/12", "9/4"));
//		System.out.println(Fraction.waru("-5/12", "-9/4"));
//		System.out.println(Fraction.waru("-32/24", "16/48"));
//		System.out.println(Fraction.gyakusu("1/2"));
//		System.out.println(Fraction.tasu("3", "-34"));
//		System.out.println(Fraction.hiku("0", "3/7"));	// <-------- bug	修正済み
//		System.out.println(Fraction.tasu("0", "-" + "3/7"));  //これでもダメ 分数の足し算引き算メソッドに問題あり	修正済み
//		System.out.println(Fraction.tsubun("0", "3/7"));	//	0
//		System.out.println(Fraction.tsubun("3/7", "0"));	//  3/7
//		System.out.println(Fraction.tsubun("-2/-3", "-4/7"));
//		System.out.println(Fraction.correctFraction("0003"));	//最初のゼロを削除する		未使用
//		System.out.println(Fraction.getBunshi("-12/4"));		//getBunshi新バージョンテスト
//		System.out.println(Fraction.getBunbo("-3/2"));
//		System.out.println(Fraction.tsubun("1/1343631", "324874/3463787"));		// <-----------処理に時間かかりすぎ 総当たりで最大公約数探すのは遅い ユークリッド互除法を使うことで改善
//		System.out.println(Fraction.getBunshi("324874/3463787"));
//		System.out.println(Fraction.getBunbo("324874/3463787"));
		System.out.println(Fraction.yakubun("-34/466"));
		System.out.println(Fraction.tsubun("-345/523", "384/55"));


		Matrix m = new Matrix(4, 5, 60);	//デバッグ用自動行列生成
//		Matrix m = new Matrix();
//		m.inputData();		//データインプッタ
		m.print();
		System.out.println();
		
		//行基本変形2のテスト
//		m.rowBasicTransformation2_d(0, "1/2", 1);
//		System.out.println("2行 - 1行 * 1/2\n");
		
		
//		m.rowBasicTransformation1_w(0, "2");
//		m.print();
//		m.rowBasicTransformation2_d(0, "1", 2);
//		m.print();
//		m.rowBasicTransformation1_k(2, "5");
//		m.print();
//		m.rowBasicTransformation2_a(2, 5, 1);
//		m.print();
//		m.rowBasicTransformation3(1, 3);
//		m.print();
//		m.rowBasicTransformation1(0, "2");
//		m.rowBasicTransformation3(0, 1);
//		m.rowBasicTransformation2_a(0, "5", 1);
//		m.rowBasicTransformation2_d(2, "5", 3);
//		m.print();
		m.hakidashi();
		m.print();
		

	}

}