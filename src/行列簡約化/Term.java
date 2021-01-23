package 行列簡約化;

public class Term {

	private Fraction ratio;
	private Fraction power;

	public Term(Fraction ratio, Fraction power) {
		this.ratio = ratio;
		this.power = power;
	}
	public Term() {
		this(new Fraction(), new Fraction());
	}
	public Term(int bunshi, int bunbo, int pbunshi, int pbunbo) {
		this(new Fraction(bunshi, bunbo), new Fraction(pbunshi, pbunbo));
	}

	public void setRatio(Fraction f) {
		this.ratio = f;
	}

	public Fraction getRatio() {
		return this.ratio;
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
		String s = this.ratio + "x^(" + this.power + ")";
		return s;
	}
	
	//xの単項式を掛けます
	public void multiply(Term t) {
		this.ratio.multiply(t.ratio);
		this.power.add(t.power);
	}
	//単項式t1*t2の答えを返します
	public static Term multiply(Term t1, Term t2) {
		Term ans = new Term();
		ans.ratio = Fraction.multiply(t1.ratio, t2.ratio);
		ans.power = Fraction.add(t1.power, t2.power);
		return ans;
	}
	
	//この単項式を引数の単項式で割ります
	public void div(Term t) {
		this.ratio.div(t.ratio);
		this.power.delta(t.power);
	}
	//単項式t1/t2の値を返します
	public Term div(Term t1, Term t2) {
		Term ans = new Term();
		ans.ratio = Fraction.div(t1.ratio, t2.ratio);
		ans.power = Fraction.delta(t1.power, t2.power);
		return ans;
	}

}
