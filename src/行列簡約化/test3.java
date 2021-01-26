package 行列簡約化;

public class test3 {

	public static void test() {

		Fraction f1 = Fraction.convert("2");
		Fraction f2 = Fraction.convert("3");
//		f2.multiply(f1);
//		f2.print();
//		f1.print();


		Term t1, t2, t3, t4, t5, t6;
		t1 = new Term(-2, 3, -2, 5);
		t2 = new Term(3, 1, 0, 1);
		t3 = new Term(-4, 3, 5, 1);
		t4 = new Term(2, 1, -1, 1);
		t5 = new Term(5, 1, 1, 1);
		t6 = new Term(2, 1, 0, 1);


		Polynomial p1 = new Polynomial();
		Polynomial p2 = new Polynomial();
		Polynomial p3 = new Polynomial();
		p3.setTerm(t5);
		p1.setTerm(t1);
		p1.setTerm(t2);
		p2.setTerm(t3);
		p2.setTerm(t4);
		p2.setTerm(t5);
		p2.setTerm(t2);

//		p2.print();

		Value v = new Value(p1, p2);
		Value v2 = v.copy();
		v2.setBunbo(p3);
		v2.multiply(t6);

//		Fraction f = Fraction.convert("5");
//		f.print();

		Term t = Term.convert("3");
		Term T = Term.convert("2");
//		t.multiply(T);
//		t.print();
//		T.print();
//		t.print();


//		String s = "-4/3x^(5) + 5x + 3 + 2x^(-1)";
		String s = "-4/3x^(5)";
//		System.out.println(s);

//		Polynomial p = Polynomial.convert("3");
//		Polynomial P = Polynomial.convert("2");
//		p.multiply(P);
//		p.print();

//		Polynomial p4 = new Polynomial("-4/3x^(-5)-1");
//		p4.print();
		
		// {-23x + x^(3) - 2}/{-2/2x + x^(-1/2)}
		Value v3 = Value.convert("2x");
		Value v4 = Value.convert("5x+3x2");		//3*2=12になるバグ
		v3.print();
		v4.print();
		v4.multiply(v3);
		v3.print();
		v4.print();
		
		Matrix m = new Matrix(2, 2);
		m.getMatrix()[0][0] = Value.convert("2x");
		m.getMatrix()[0][1] = Value.convert("3");
		m.getMatrix()[1][0] = Value.convert("x^2");
		m.getMatrix()[1][1] = Value.convert("1");
		m.copyToString();
		m.print();
		
		m.rowBasicTransformation2_d(0, Value.convert("2"), 1);
//		m.hakidashi();
		m.print();
	}

}
