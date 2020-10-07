package 行列簡約化;

public class Main {
	public static void main(String[] args) {
//		test.test();
		Matrix m = new Matrix();
		m.inputData();
		System.out.println("\n入力された行列");
		m.print();
		for(int i = 0; i == 0; ) {
			System.out.println("行列の操作を選択してください\n1:行列修正 2:行列簡約化 0:操作終了");
			int selector = new java.util.Scanner(System.in).nextInt();
			switch(selector){
			case 1:
				m.correctionData();
				m.print();
				break;
			case 2:
				System.out.println("入力された行列を簡約化します\n");
				m.hakidashi();
				System.out.println("\n簡約化された行列");
				m.print();
				break;
		}
		}
	}

}
