package 行列簡約化;

public class Value {

	//2-x, 2x, x^(-12), x/2, 12/x
	
	private Polynomial bunshi;	//添字は文字数の次数
	private Polynomial bunbo;
	int digreeMax;
	
	public Value() {
		digreeMax = 100;
		bunshi = new Polynomial();
		bunbo = new Polynomial();
	}
	
	public Polynomial getBunshi() {
		return this.bunshi;
	}
	
	public Polynomial getBunbo() {
		return this.bunbo;
	}
	
	public void setBunshi(int digree, int coefficient) {
		this.bunshi.setCoefficient(digree, coefficient);
	}
	
	public void setBunbo(int digree, int coefficient) {
		this.bunbo.setCoefficient(digree, coefficient);
	}
		
	public int[] polynomialMultiply() {
		
	}
	
	//掛け算
	//(1+2x+x^2)(12+3x+2x^2+4x^4)
	public Value multiply(Value v) {
		int bunshi[] = new int[digreeMax];
		int digree = 0;
		for(int i = 0; i < this.countBunshiDigree(); i++) {
			for(int j = 0; j < v.countBunshiDigree(); i++) {
				bunshi[digree] = this.bunshi[j] * v.bunshi[i];
				digree++;
			}
		}
	}
}
