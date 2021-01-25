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
		if(bunbo.equals(new Polynomial())) {
			
		}
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
			return sb.toString();
		}else if(bunbo.equals(new Polynomial(1))){
			sb.append(this.bunshi.toString());
			return sb.toString();
		}else {
			sb.append("(");
			sb.append(this.bunshi.toString());
			sb.append(")");
		}
		sb.append("/");
		if(bunbo.length() == 1) {
			sb.append(this.bunbo.toString());
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


		Fraction saidaikouyakusu;
		//分子に共通の分数が含まれる場合、分母分子に共通の分数の分母の値をかけ、多項式内の分数を消去
		if(!this.bunbo.nonFraction()) {
			saidaikouyakusu = Polynomial.saidaikouyakusu(this.bunbo);
			this.bunshi.multiply(new Fraction(saidaikouyakusu.bunbo, 1));
			this.bunbo.multiply(new Fraction(saidaikouyakusu.bunbo, 1));
		}
		//分母に共通の分数が含まれる場合、分母分子に共通の分数の分母の値をかけ、多項式内の分数を消去
		if(!this.bunshi.nonFraction()) {
			saidaikouyakusu = Polynomial.saidaikouyakusu(this.bunshi);
			this.bunshi.multiply(new Fraction(saidaikouyakusu.bunbo, 1));
			this.bunbo.multiply(new Fraction(saidaikouyakusu.bunbo, 1));
		}


		//分子内の最大公約数
		Fraction bunshiS = Polynomial.saidaikouyakusu(this.bunshi);
		//分母ないの最大公約数
		Fraction bunboS = Polynomial.saidaikouyakusu(this.bunbo);
		//上記2つの分数の分子同士の最大公約数
		int s1 = Fraction.saidaikouyakusu(bunshiS.getBunshi(), bunboS.getBunshi());
		//上記二つの分数の分母同士の最大公約数
		int s2 = Fraction.saidaikouyakusu(bunshiS.getBunbo(), bunboS.getBunbo());
		//分母分子をs1で割る
		this.bunshi.div(new Fraction(s1, 1));
		this.bunbo.div(new Fraction(s1, 1));
		//分母分子にsを掛ける
		this.bunshi.multiply(new Fraction(s2, 1));
		this.bunshi.multiply(new Fraction(s2, 1));
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

	//thisにvを掛けます
	public void multiply(Value v) {
		Polynomial temp1 = new Polynomial();
		Polynomial temp2 = new Polynomial();

		Polynomial thisBunshi = this.bunshi.copy();
		Polynomial thisBunbo = this.bunbo.copy();
		Polynomial vBunshi = v.bunshi.copy();
		Polynomial vBunbo = v.bunbo.copy();

		//自分の分子内の最大公約数
		Fraction thisBunshiS = Polynomial.saidaikouyakusu(this.bunshi);
		//自分の分母の最大公約数
		Fraction thisBunboS = Polynomial.saidaikouyakusu(this.bunbo);
		//掛ける相手の分子内の最大公約数
		Fraction vBunshiS = Polynomial.saidaikouyakusu(v.bunshi);
		//掛ける相手の分母の最大公約数
		Fraction vBunboS = Polynomial.saidaikouyakusu(v.bunbo);

		//それぞれの多項式を共通因数で割る
		thisBunshi.div(thisBunshiS);
		thisBunbo.div(thisBunboS);
		vBunshi.div(vBunshiS);
		vBunbo.div(vBunboS);
		
		if(thisBunshi.equals(vBunbo) || thisBunbo.equals(vBunshi)) {
			//自分の分子と相手の分母が約分できる場合
			if(thisBunshi.equals(vBunbo)) {
				temp1 = v.bunshi.copy();
				temp1.multiply(thisBunshiS);
				temp2 = this.bunbo.copy();
				temp2.multiply(vBunboS);
				this.bunshi = temp1;
				this.bunbo = temp2;
			}
			//自分の分母と相手の分子が約分できる場合
			if(thisBunbo.equals(vBunshi)) {
				temp1 = this.bunshi.copy();
				temp1.multiply(vBunshiS);
				temp2 = v.bunbo.copy();
				temp2.multiply(thisBunboS);
				this.bunshi = temp1;
				this.bunbo = temp2;
			}
		}else {
			this.bunshi.multiply(v.bunshi);
			this.bunbo.multiply(v.bunbo);
		}
		this.yakubun();
	}
	public static Value multiply(Value v1, Value v2) {
		Value temp = v1.copy();
		temp.multiply(v2);
		return temp;
	}
	public void multiply(Term t) {
		this.bunshi.multiply(t);
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
	}
	// v1/v2
	public static Value div(Value v1, Value v2) {
		Value temp1 = v1.copy();
		Value temp2 = v2.copy();
		temp2.gyakusu();
		temp1.multiply(temp2);
		return temp1;
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
