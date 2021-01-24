package 行列簡約化;

public class test2 {

	public static void test() {

		Fraction f = new Fraction(-12, 4);
		Fraction f1 = new Fraction(-1, 5);
		Fraction f2 = new Fraction(-1, 5);
		Fraction f3 = Fraction.div(f, f1);
//		System.out.println(Fraction.saidaikouyakusu(12, 4));
//		f3.print();
//		f.Print();
//		f1.Print();
//		f.yakubun();
//		f.div(f);	//自分自身で割った時計算が違う
//		f.reverse();
//		f.add(f1);


//		f.print();

		Term t = new Term(f, f1);
		Term t1, t2, t3, t4;
		t1 = new Term(-2, 1, 1, 1);
		t2 = new Term(3, 1, 0, 1);
		t3 = new Term(-4, 1, 1, 1);
		t4 = new Term(6, 3, 0, 1);
		
		Term t5 = new Term(5, 1, 1, 1);
//		t5.print();
//		t1.print();
//		t2.print();


		Polynomial p1 = new Polynomial();
		Polynomial p2 = new Polynomial();
		Polynomial p3 = new Polynomial();
		p3.setCoefficient(t5);
		p1.setCoefficient(t1);
		p1.setCoefficient(t2);
		p2.setCoefficient(t3);
		p2.setCoefficient(t4);		//隣り合ってる時はOK、離れてて片方が一番最後にある時NG
//		System.out.println("p1多項式の次数 : " + p1.length());
//		p1.print();
//		p2.print();
//		p1.multiply(p2);
//		p1.print();
//		p2.add(p1);
//		p1.print();
//		p1.div(t1);
//		p2.print();

		Polynomial p4 = p1.copy();
//		p1.print();
//		p4.print();
//		System.out.println(p1.equals(p4));
		
		
		Value v = new Value(p1, p2);
		Value v2 = v.copy();
		v2.setBunbo(p3);
		
		v.print();
		v2.print();
//		v.delta(v2);
		v.div(v2);
		
		v.print();
		v2.print();
		
//		v.yakubun();
		
//		v.print();
//		v2.print();
		
		
		
	}

}
