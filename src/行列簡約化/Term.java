package 行列簡約化;

public class Term {

	private Fraction coefficient;
	private Fraction power;

	public Term(Fraction coefficient, Fraction power) {
		this.coefficient = coefficient;
		this.power = power;
	}
	public Term() {
		this(new Fraction(), new Fraction());
	}
	public Term(int bunshi, int bunbo, int pbunshi, int pbunbo) {
		this(new Fraction(bunshi, bunbo), new Fraction(pbunshi, pbunbo));
	}

	public void setCoefficient(Fraction f) {
		this.coefficient = f;
	}

	public Fraction getCoefficient() {
		return this.coefficient;
	}

	public void setPower(Fraction f) {
		this.power = f;
	}

	public Fraction getPower() {
		return this.power;
	}

	public void print() {
		System.out.println(this.toString());
	}

	public String toString() {
		String s = this.coefficient.toString();
		if(this.power.getBunshi() == 0) {
			s += "";
		}else if(this.power.getBunshi() == 1 && this.power.getBunbo() == 1){
			s += "x";
		}else {
			s += "x^(" + this.power.toString() + ")";
		}
		return s;
	}

	//xの単項式を掛けます
	public void multiply(Term t) {
		this.coefficient.multiply(t.coefficient);
		this.power.add(t.power);
	}
	//定数項を掛けます
	public void multiply(Fraction f) {
		this.coefficient.multiply(f);
	}
	//単項式t1*t2の答えを返します
	public static Term multiply(Term t1, Term t2) {
		Term ans = new Term();
		ans.coefficient = Fraction.multiply(t1.coefficient, t2.coefficient);
		ans.power = Fraction.add(t1.power, t2.power);
		return ans;
	}

	//この単項式を引数の単項式で割ります
	public void div(Term t) {
		this.coefficient.div(t.coefficient);
		this.power.delta(t.power);
	}
	//定数項で割ります
	public void div(Fraction f) {
		this.coefficient.div(f);
	}
	//単項式t1/t2の値を返します
	public Term div(Term t1, Term t2) {
		Term ans = new Term();
		ans.coefficient = Fraction.div(t1.coefficient, t2.coefficient);
		ans.power = Fraction.delta(t1.power, t2.power);
		return ans;
	}

	public Term copy() {
		Term t = new Term();
		t.setCoefficient(this.getCoefficient().copy());
		t.setPower(this.getPower().copy());
		return t;
	}

	public boolean equals(Term t) {
		boolean equal = true;
		if(!this.coefficient.equals(t.coefficient)) {
			equal = false;
		}
		if(!this.power.equals(t.power)) {
			equal = false;
		}
		return equal;
	}

	// 例   4x^(2), -2/3x^(-2/5), 5x, 23
	public static Term convert(String s) {
		s = s.replaceAll(" ", "");
		s = s.replaceAll("\\(", "");
		s = s.replaceAll("\\)", "");
		s = s.replaceAll("\\^", "");
		String coefficient;
		String power;
		String term[] = s.split("x", 2);
		if(term.length == 1) {
			coefficient = s;
			power = "0";
			return new Term(Fraction.convert(coefficient), Fraction.convert(power));
		}else if(term.length == 0) {
			s = s.replaceAll("x", "");
			coefficient = "1";
			power = "1";
			return new Term(Fraction.convert(coefficient), Fraction.convert(power));
		}
		// x^(3)
		if(term[0].equals("")) {
			coefficient = "1";
		// -2/3x^(-2/5)
		}else {
			coefficient = term[0];
		}
		// 12x
		if(term[1].equals("")) {
			power = "1";
		// -2/3x^(-2/5)
		}else {
			power = term[1];
		}
//		System.out.println(coefficient + ", " + power);
		return new Term(Fraction.convert(coefficient), Fraction.convert(power));
	}
}
