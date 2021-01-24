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
		//分母と分子が一致する場合分母分子共に1にする
		if(this.bunshi.equals(this.bunbo)) {
			this.bunshi = new Polynomial(1);
			this.bunbo = new Polynomial(1);
		}
		//分子に共通因数がある場合は分母分子に同じ値をかけて消去
		if(!this.bunbo.nonFraction()) {
			this.bunshi.div(Polynomial.saidaikouyakusu(this.bunbo));
			this.bunbo.div(Polynomial.saidaikouyakusu(this.bunbo));
		}
		//分母に共通因数がある場合は分母分子に同じ値をかけて消去
		if(!this.bunshi.nonFraction()) {
			this.bunshi.div(Polynomial.saidaikouyakusu(this.bunshi));
			this.bunbo.div(Polynomial.saidaikouyakusu(this.bunshi));
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
		this.yakubun();
	}
	
	public void multiply(Value v) {
		if(this.bunshi.equals(v.bunbo)) {
			this.bunshi = v.bunshi.copy();
		}else if(this.bunbo.equals(v.bunshi)) {
			this.bunbo = v.bunbo.copy();
		}else {			
			this.bunshi.multiply(v.bunshi);
			this.bunbo.multiply(v.bunbo);
		}
//		this.yakubun();
	}
	
	public void gyakusu() {
		Polynomial temp = new Polynomial();
		temp = this.bunshi.copy();
		this.bunshi = this.bunbo;
		this.bunbo = temp;
	}

	//vで割る
	public void div(Value v) {
		Value temp = v.copy();
		temp.gyakusu();
		this.multiply(temp);
//		this.yakubun();
	}
	//単項式で割ります
	public void div(Term t) {
		this.bunshi.div(t);
	}

	public Value copy() {
		Value copy = new Value();
		copy.bunshi = this.bunshi.copy();
		copy.bunbo = this.bunbo.copy();
		return copy;
	}
}
