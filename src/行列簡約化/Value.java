package 行列簡約化;

public class Value {

	//2-x, 2x, x^(-12), x/2, 12/x

	private Polynomial bunshi;	//添字は文字数の次数
	private Polynomial bunbo;

	public Value() {
		bunshi = new Polynomial();
		bunbo = new Polynomial();
	}
	public Value(Polynomial bunshi, Polynomial bunbo) {
		this.bunshi = bunshi;
		this.bunbo = bunbo;//分母ゼロの可能性
	}

	public Polynomial getBunshi() {
		return this.bunshi;
	}

	public Polynomial getBunbo() {
		return this.bunbo;
	}

	public void setBunshi(Polynomial p) {
		this.bunshi = p;
	}

	public void setBunbo(Polynomial p) {
		this.bunbo = p;
	}

	public void print() {
		System.out.println(this.toString());
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		if(bunshi.length() == 1) {
			sb.append(this.bunshi.toString());
		}else if(bunshi.length() == 0) {
			sb.append("0");
		}else {
			sb.append("(");
			sb.append(this.bunshi.toString());
			sb.append(")");
		}
		sb.append("/");
		if(bunbo.length() == 1) {
			sb.append(this.bunbo.toString());
		}else if(this.bunbo.length() == 0) {
			throw new IllegalArgumentException("div by zero");
		}else {
			sb.append("(");
			sb.append(this.bunbo.toString());
			sb.append(")");
		}
		return sb.toString();
	}

	public void yakubun() {
		if(this.bunshi.equals(this.bunbo)) {
			this.bunshi = new Polynomial(1);
			this.bunbo = new Polynomial(1);
		}
	}

	public void tsubun(Value v) {
		if(this.bunbo.equals(v.bunbo)) {
			;
		}else {
			this.bunshi.multiply(v.bunbo);
			this.bunbo.multiply(v.bunbo);
		}
	}

	public void add(Value v) {
		Value temp = v.copy();
		temp.tsubun(this);
		this.tsubun(v);
		this.bunshi.add(temp.bunshi);
		temp.yakubun();
	}
	
	public void delta(Value v) {
		Value temp = v.copy();
		temp.tsubun(this);
		this.tsubun(v);
		this.bunshi.delta(temp.bunshi);
		temp.yakubun();
	}
	
	//単項式で割ります
	public void div(Term t) {
		this.bunshi.div(t);
		Fraction power = new Fraction();
		//分子の多項式内で最小の次数の項を探す
		for(int i = 0; i < this.bunshi.length(); i++) {
			if(power.compare(this.bunshi.getTerm(i).getPower())) {
				power = this.bunshi.getTerm(i).getPower();
			}
		}
		//最小の次数が0未満の場合、分母分子に最小次数項の逆数をかける
		if(power.compare(new Fraction())) {
			this.bunshi.div(new Term(new Fraction(1, 1), power));
		}
	}

	public Value copy() {
		Value copy = new Value();
		copy.bunshi = this.bunshi.copy();
		copy.bunbo = this.bunbo.copy();
		return copy;
	}
}
