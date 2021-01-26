package 行列簡約化;

public class Polynomial {

	private Term x[];	//項
	private int digreeMax;

	public Polynomial() {
		this.digreeMax = 100;
		this.x = new Term[this.digreeMax];
		for(int i = 0; i < this.digreeMax; i++) {
			x[i] = new Term();
		}
	}
	//整数nの定数項のみの多項式
	public Polynomial(int n) {
		this();
		this.setTerm(new Term(new Fraction(1, 1), new Fraction(0, 1)));
	}
	public Polynomial(String s) {
		this();
		Polynomial.convert(s).copy(this);
	}

	public Term getTerm(int digree) {
		return this.x[digree];
	}

	//引数の単項式が多項式内に存在する場合はその項に加え、存在しない場合は配列の最後にくっつける、その後字数の高い順に並べ替える
	public void setTerm(Term coefficient) {
		if(exist(coefficient)) {
			this.x[findSamePower(coefficient)].getCoefficient().add(coefficient.getCoefficient());
		}else {
			this.x[findSamePower(coefficient)] = coefficient;
		}
	}
	public void setTerm(int i, Term coefficient) {
		this.x[i] = coefficient;
	}

	public void reset() {
		for(int i = 0; i < this.digreeMax; i++) {
			setTerm(i, new Term());
		}
	}

	//次数の高い順に並べ替える
	public void sort() {
		Term temp;
		for(int i = 0; i < this.length(); i++) {
			for(int j = 0; j < this.length() - 1 - i; j++) {
				if(getTerm(i + 1).getPower().compare(getTerm(i).getPower())) {
					temp = x[i + 1];
					x[i + 1] = x[i];
					x[i] = temp;
				}
			}
		}
	}

	//引数の項の次数が既に存在している場合はtrueを返す
	public boolean exist(Term t) {
		boolean exist = false;
		for(int i = 0; i < this.length(); i++) {
			//引数の項の次数とこの多項式のi番目の次数の分母と分子が一致した場合
			if(t.getPower().equals(this.getTerm(i).getPower())) {
				exist = true;
			}
		}
		return exist;
	}

	//引数と同じ次数の項が入っている多項式配列の配列番号を返します
	//存在しない場合は多項式配列の最後の番号を返します
	public int findSamePower(Term t) {
		int num = this.length();
		for(int i = 0; i < this.length(); i++) {
			//引数の項の次数とこの多項式のi番目の次数の分母と分子が一致した場合
			if(t.getPower().equals(this.getTerm(i).getPower()) ) {
				num = i;
			}
		}
		return num;
	}

	//多項式の配列に実際はいっている単項式の数を整数で返します
	public int length() {
		int digree = 0;
		for(int i = 0; i < this.digreeMax; i++) {
			if(getTerm(i).getCoefficient().getBunshi() == 0) {
				digree = i;
				break;
			}
		}
		return digree;
	}

	//多項式を描画します
	public void print() {
		System.out.println(this.toString());
	}

	public String toString() {
		StringBuilder sb = new StringBuilder("");
		this.sort();
		for(int i = 0; i < this.length(); i++) {
			sb.append(x[i].toString());
			if(i != this.length() -1) {
				sb.append(" + ");
			}
		}
		return sb.toString();
	}

	//単項式をかけます
	public void multiply(Term t) {
		for(int i = 0; i < this.length(); i++) {
			getTerm(i).multiply(t);
		}
	}
	public void multiply(Polynomial p) {
		Polynomial temp1, temp2;
		temp1 = this.copy();
		temp2 = p.copy();
		this.reset();
		for(int i = 0; i < temp1.length(); i++) {
			for(int j = 0; j < temp2.length(); j++) {
				this.setTerm(Term.multiply(temp1.getTerm(i), temp2.getTerm(j)));
			}
		}
	}
	//定数項を掛けます
	public void multiply(Fraction f) {
		for(int i = 0; i < this.length(); i++) {
			getTerm(i).multiply(f);
		}
	}

	//単項式で割ります
	public void div(Term t) {
		for(int i = 0; i < this.length(); i++) {
			getTerm(i).div(t);
		}
	}
	public void div(Fraction f) {
		for(int i = 0; i < this.length(); i++) {
			getTerm(i).div(f);
		}
	}

	public void add(Polynomial p) {
		for(int i = 0; i < p.length(); i++) {
			this.setTerm(p.getTerm(i));
		}
	}

	public void delta(Polynomial p) {
		for(int i = 0; i < p.length(); i++) {
			p.getTerm(i).multiply(new Fraction(-1, 1));
			this.setTerm(p.getTerm(i));
		}
	}

	public Polynomial[] factorization() {
		int n = 0;
		Polynomial p[] = new Polynomial[n];

		return p;
	}

	//多項式をコピーし返します
	public Polynomial copy() {
		Polynomial copied = new Polynomial();
		for(int i = 0; i < this.length(); i++) {
			copied.setTerm(this.getTerm(i).copy());
		}
		return copied;
	}
	//この多項式を引数の多項式にコピーします
	public void copy(Polynomial p) {
		p.reset();
		for(int i = 0; i < this.length(); i++) {
			p.setTerm(this.getTerm(i).copy());
		}
	}

	//多項式が等しい場合trueを返します
	public boolean equals(Polynomial p) {
		boolean equal = true;
		if(this.length() != p.length()) {
			equal = false;
			return equal;
		}
		p.sort();
		this.sort();
		for(int i = 0; i < this.length(); i++) {
			if(!this.getTerm(i).equals(p.getTerm(i))) {
				equal = false;
			}
		}
		return equal;
	}

	//多項式全体の最大の共通因数を返します
	public static Fraction saidaikouyakusu(Polynomial p) {
		int bunshiSaidaikouyakusu = p.getTerm(0).getCoefficient().getBunshi();
		for(int i = 0; i < p.length(); i++) {
			bunshiSaidaikouyakusu = Fraction.saidaikouyakusu(bunshiSaidaikouyakusu, p.getTerm(i).getCoefficient().getBunshi());
		}

		int bunboSaidaikouyakusu = p.getTerm(0).getCoefficient().getBunbo();
		for(int i = 0; i < p.length(); i++) {
			bunboSaidaikouyakusu = Fraction.saidaikouyakusu(bunboSaidaikouyakusu, p.getTerm(i).getCoefficient().getBunbo());
		}
//		System.out.println(bunshiSaidaikouyakusu + ", " + bunboSaidaikouyakusu);
		return new Fraction(bunshiSaidaikouyakusu, bunboSaidaikouyakusu);
	}

	//多項式内に分数が含まれていない場合trueを返す
	public boolean nonFraction() {
		boolean b = true;
		Fraction f = new Fraction(1, 1);
		for(int i = 0; i < this.length(); i++) {
			if(f.compare(this.getTerm(i).getCoefficient()));
			b = false;
		}
		return b;
	}

	// 	-4/3x^(5) + 2x^(-1)
	public static Polynomial convert(String s) {
		s = s.replace(" ", "");
		s = s.replace("x^(-", "b");
		s = s.replace("-", "-a");
		Polynomial p = new Polynomial();
		String terms[] = s.split("\\+|-", 0);
		for(int i = 0; i < terms.length; i++) {
			if(!terms[i].matches("")) {
				terms[i] = terms[i].replace("a", "-");
				terms[i] = terms[i].replace("b", "x^(-");
//				System.out.println(i + ":" + terms[i]);
				p.setTerm(Term.convert(terms[i]));
			}
		}
		return p;
	}
}
