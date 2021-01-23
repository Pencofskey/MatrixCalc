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
		this.bunbo = bunbo;
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
		sb.append("(");
		sb.append(this.bunshi.toString());
		sb.append(")/(");
		sb.append(this.bunbo.toString());
		sb.append(")");
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

	public Value copy() {
		Value copy = new Value();
		copy.bunshi = this.bunshi.copy();
		copy.bunbo = this.bunbo.copy();
		return copy;
	}
}
