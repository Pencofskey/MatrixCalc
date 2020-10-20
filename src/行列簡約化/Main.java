package 行列簡約化;

public class Main {
	public static void main(String[] args) {
				test.test();	//デバッグ用
		Matrix m = new Matrix();
		m.inputData();
		System.out.println("\n入力された行列");
		m.print();
		boolean conti = true;
		for (; conti;) {
			boolean err;
			int selector;
			String S;
			do {
				System.out.println("行列の操作を選択してください\n1:行列修正 2:行列リセット 3:行列サイズ再指定 4:行列簡約化 0:操作終了");
				S = new java.util.Scanner(System.in).nextLine();
				if (S.matches("[0-4]")) {
					err = false;
				} else {
					err = true;
					System.out.println("0~3の整数を入力してください");
				}
			} while (err);
			selector = Integer.parseInt(S);
			switch (selector) {
			case 0:
				conti = false;
				break;
			case 1:
				m.correctionData();
				m.print();
				conti = true;
				break;
			case 2:
				m.reset();
				m.inputData();
				m.print();
				conti = true;
				break;
			case 4:
				System.out.println("入力された行列を簡約化します\n");
				m.hakidashi();
				System.out.println("簡約化された行列");
				m.print();
				conti = true;
				break;
			case 3:
				m = new Matrix();
				m.inputData();
				m.print();
				conti = true;
			}
		}

	}
}