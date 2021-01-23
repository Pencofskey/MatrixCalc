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
		String s;
		if(this.power.getBunshi() == 0) {
			s = this.coefficient.toString();
		}else if(this.power.getBunshi() == 1 && this.power.getBunbo() == 1){
			s = this.coefficient.toString() + "x";
		}else {
			s = this.coefficient + "x^(" + this.power + ")";
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

}
