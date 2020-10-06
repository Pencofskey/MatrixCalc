package 行列簡約化;

public class Main {
	public static void main(String[] args) {
//		test.test();
		Matrix m = new Matrix();
		m.inputData();
		System.out.println("\n入力された行列");
		m.hakidashi();
		System.out.println("\n簡約化された行列");
		m.print();
	}

}
