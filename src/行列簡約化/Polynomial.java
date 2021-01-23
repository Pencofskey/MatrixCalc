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

	public Term getCoefficient(int digree) {
		return this.x[digree];
	}

	//引数の単項式が多項式内に存在する場合はその項に加え、存在しない場合は配列の最後にくっつける、その後字数の高い順に並べ替える
	public void setCoefficient(Term coefficient) {
		if(exist(coefficient)) {
			this.x[findSamePower(coefficient)].getRatio().add(coefficient.getRatio());
		}else {
			this.x[findSamePower(coefficient)] = coefficient;
		}
	}
	public void setCoefficient(int i, Term coefficient) {
		this.x[i] = coefficient;
	}
	
	public void reset() {
		for(int i = 0; i < this.digreeMax; i++) {
			setCoefficient(i, new Term());
		}
	}

	//次数の高い順に並べ替える
	public void sort() {
		Term temp;
		for(int i = 0; i < this.length(); i++) {
			for(int j = 0; j < this.length() - 1 - i; j++) {
				if(getCoefficient(i + 1).getPower().compare(getCoefficient(i).getPower())) {
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
			if(t.getPower().equals(this.getCoefficient(i).getPower())) {
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
			if(t.getPower().equals(this.getCoefficient(i).getPower()) ) {
				num = i;
			}
		}
		return num;
	}

	//多項式の配列に実際はいっている単項式の数を整数で返します
	public int length() {
		int digree = 0;
		for(int i = 0; i < this.digreeMax; i++) {
			if(getCoefficient(i).getRatio().getBunshi() == 0) {
				digree = i;
				break;
			}
		}
		return digree;
	}

	//多項式を描画します
	public void print() {
//		this.sort();
		for(int i = 0; i < this.length(); i++) {
			System.out.print(x[i].toString() + " + ");
		}
		System.out.println();
	}

	//多項式をかけます
	public void multiply(Fraction f) {
		for(int i = 0; i < this.length(); i++) {
			getCoefficient(i).getRatio().multiply(f);
		}
	}
	public void multiply(Polynomial p) {	//途中
		Polynomial temp1, temp2;
		temp1 = this.copy();
		temp2 = p.copy();
		this.reset();
		for(int i = 0; i < temp1.length(); i++) {
			for(int j = 0; j < temp2.length(); j++) {
				this.setCoefficient(Term.multiply(temp1.getCoefficient(i), temp2.getCoefficient(j)));
			}
		}
	}
	
	public void add(Polynomial p) {
		for(int i = 0; i < p.length(); i++) {
			this.setCoefficient(p.getCoefficient(i));
		}
	}
	
	//多項式をコピーし返します
	public Polynomial copy() {
		Polynomial copied = new Polynomial();
		for(int i = 0; i < this.length(); i++) {
			copied.setCoefficient(this.getCoefficient(i));
		}
		return copied;
	}
	//この多項式を引数の多項式にコピーします
	public void copy(Polynomial p) {
		p.reset();
		for(int i = 0; i < this.length(); i++) {
			p.setCoefficient(this.getCoefficient(i));
		}
	}

}
