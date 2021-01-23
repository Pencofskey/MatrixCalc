package 行列簡約化;

public class Fraction {

	long bunshi;
	long bunbo;
	long defaultBunbo = 45378209;

	public Fraction(long bunshi, long bunbo) {
		this.bunshi = bunshi;
		this.bunbo = bunbo;	//0の場合例外処理を追加
		if(this.bunbo == 0) {
			throw new IllegalArgumentException("div by zero");
		}
		this.correctFraction();
		defaultBunbo = 45378209;
	}
	public Fraction() {
		this(0, 45378209);
		defaultBunbo = 45378209;
	}

	public long getBunbo() {
		return this.bunbo;
	}

	public long getBunshi() {
		return this.bunshi;
	}

	public String toString() {
		this.correctFraction();
		String f;
		if(this.bunshi == 0) {
			f = "0";
		}else if(this.bunbo == 1) {
			f = this.bunshi + "";
		}else {
			f = (this.bunshi + "/" + this.bunbo) + "";
		}
		return f;
	}

	public void print() {
		System.out.println(this.toString());
	}

	public void Print() {
		System.out.println(this.bunshi + "/" + this.bunbo);
	}

	//分数を約分し、
	//分母が負の場合分母分子両方に-1をかける
	public void correctFraction() {
		this.yakubun();
		if(this.bunbo < 0) {
			this.bunbo *= -1;
			this.bunshi *= -1;
		}
	}

	//約分します
	public void yakubun() {
		if(this.bunbo < 0) {
			this.bunbo *= -1;
			this.bunshi *= -1;
		}

		if (this.bunshi == 0 || this.bunshi == 1) {
			;
		}else {
			//最大公約数 ユークリッド互除法
			long saidaikouyakusuu = 1;
			long d = Math.max(this.bunshi, this.bunbo);
			long s = Math.min(this.bunshi, this.bunbo);
			long r = d % s;
			for (int i = 1; r != 0;) {
				d = s;
				s = r;
				r = d % r;
			}
			saidaikouyakusuu = s;

			this.bunshi /= s;
			this.bunbo /= s;
		}
	}

	//thisをfに通分します どちらかに0が代入された場合はそのまま
	public void tsubun(Fraction f) {
		this.correctFraction();

		if(this.bunshi == 0) {
			this.bunbo = f.bunbo;
		}else if( f.getBunshi() == 0) {
			f.bunbo = this.bunbo;
		}else {
			long saishoukoubaisu = 1; //最小公倍数の初期化 最小公倍数=a*b/最大公約数
			long d = Math.max(this.bunbo, f.getBunbo());
			long s = Math.min(this.bunbo, f.getBunbo());
			long r = d % s;
			for (; r != 0;) {
				d = s;
				s = r;
				r = d % r;
			}
			saishoukoubaisu = this.bunbo * f.getBunbo() / s;

			this.bunshi = this.bunshi * saishoukoubaisu / this.bunbo;
			this.bunbo = this.bunbo * saishoukoubaisu / this.bunbo;
		}
	}

	//引数の分数を掛けます
	public void multiply(Fraction f) {
		this.correctFraction();
		this.bunshi *= f.bunshi;
		this.bunbo *= f.bunbo;
		this.correctFraction();
	}
	public static Fraction multiply(Fraction F1, Fraction F2) {
		Fraction ans = new Fraction();
		Fraction f1 = F1.copy();
		Fraction f2 = F2.copy();
		f1.correctFraction();
		f2.correctFraction();
		ans.bunshi = f1.bunshi * f2.bunshi;
		ans.bunbo = f1.bunbo * f2.bunbo;
		ans.correctFraction();
		return ans;
	}

	//分数同士の割り算
	public void div(Fraction f) {
		if(f.bunshi == 0) {
			throw new IllegalArgumentException("div by zero");
		}
		this.correctFraction();

		//自分自身で割った時、参照先が同じインスタンスを変更してしまうのを防ぐ
		Fraction temp = new Fraction(f.bunshi, f.bunbo);
		temp.reverse();
		this.multiply(temp);
		this.correctFraction();
	}
	//f1/f2を返します
	public static Fraction div(Fraction F1, Fraction F2) {
		Fraction ans = new Fraction();
		Fraction f1 = F1.copy();
		Fraction f2 = F2.copy();
		f1.correctFraction();
		f2.correctFraction();
		f2.reverse();
		ans.bunshi = f1.bunshi * f2.bunshi;
		ans.bunbo = f1.bunbo * f2.bunbo;
		ans.correctFraction();
		return ans;
	}

	//逆数
	public void reverse() {
		this.correctFraction();
		//分母0を回避
		if(this.bunshi == 0) {
			throw new IllegalArgumentException("div by zero");
		}
		long temp = this.bunshi;
		this.bunshi = this.bunbo;
		this.bunbo = temp;
		this.correctFraction();
	}

	//引数の分数をたします
	public void add(Fraction f) {
		this.correctFraction();
		this.tsubun(f);
		f.tsubun(this);
		this.bunshi += f.bunshi;
		this.correctFraction();
	}
	//f1+f2を返します
	public static Fraction add(Fraction F1, Fraction F2) {
		Fraction ans = new Fraction();
		Fraction f1 = F1.copy();
		Fraction f2 = F2.copy();
		f1.correctFraction();
		f2.correctFraction();
		f1.tsubun(f2);
		f2.tsubun(f1);
		ans.bunbo = f1.bunbo;
		ans.bunshi = f1.bunshi + f2.bunshi;
		ans.correctFraction();
		return ans;
	}

	//引数の分数を引きます
	public void delta(Fraction f) {
		this.correctFraction();
		this.tsubun(f);
		f.tsubun(this);
		this.bunshi -= f.bunshi;
		this.correctFraction();
	}
	//f1-f2の分数を返します
	public static Fraction delta(Fraction F1, Fraction F2) {
		Fraction ans = new Fraction();
		Fraction f1 = F1.copy();
		Fraction f2 = F2.copy();
		f1.correctFraction();
		f2.correctFraction();
		f1.tsubun(f2);
		f2.tsubun(f1);
		ans.bunbo = f1.bunbo;
		ans.bunshi = f1.bunshi - f2.bunshi;
		ans.correctFraction();
		return ans;
	}

	//引数の分数より大きければtrueを返す
	public boolean compare(Fraction f) {
		boolean bigger = false;
		Fraction temp = new Fraction(this.bunshi, this.bunbo);
		temp.tsubun(f);
		f.tsubun(temp);
		if(temp.getBunshi() > f.getBunshi()) {
			bigger = true;
		}
		return bigger;
	}
	
	//引数の分数と等しければtrueを返します
	public boolean equals(Fraction f) {
		boolean equal = false;
		if(this.bunshi == f.bunshi && this.bunbo == f.bunbo) {
			equal = true;
		}
		return equal;
	}
	
	public Fraction copy() {
		Fraction copy = new Fraction();
		copy.bunshi = this.bunshi;
		copy.bunbo = this.bunbo;
		return copy;
	}
}
