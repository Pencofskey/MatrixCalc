package 行列簡約化;

public class Matrix {
	private String[][] matrix; //内部的には0行0列から始まっています 普通の行列式のように1行1列から始まってると考えて引数を渡すと配列エラーになります
	private int row; //行数
	private int column; //列数

	public int getRow() {
		return this.row;
	}

	public int getColumn() {
		return this.column;
	}

	public String[][] getMatrix() {
		return this.matrix;
	}

	Matrix() {	//行列初期化
		System.out.println("行列の型を指定してください");
		System.out.print("行数：");
		this.row = new java.util.Scanner(System.in).nextInt();
		System.out.print("列数：");
		this.column = new java.util.Scanner(System.in).nextInt();
		this.matrix = new String[row][column];
		System.out.println(this.row + "x" + this.column + "型行列");
	}
	Matrix(int row, int column, int randomRange){		//デバッグ用自動行列生成
		this.row = row;
		this.column = column;
		this.matrix = new String[row][column];
		for (int r = 0; r < this.row; r++) {
			for (int c = 0; c < this.column; c++) {
				this.matrix[r][c] = "" + (new java.util.Random().nextInt(randomRange) - randomRange / 2);
			}
		}
	}			

	//行列出力
	public void print() {
		int charCount = 0;
		for (int r = 0; r < this.row; r++) {	//行列内の最長文字数を走査
			for(int c = 0; c < this.column; c++) {
				if (charCount < this.matrix[r][c].length()) {
					charCount = this.matrix[r][c].length();		//最大を上書き
				}
			}
		}
		for (int row = 0; row < this.getRow(); row++) {
			for (int column = 0; column < this.getColumn(); column++) {
				System.out.print(this.getMatrix()[row][column]);
				for (int i = 0; i < charCount + 2 - this.getMatrix()[row][column].length(); i++) { //文字数に合わせてスペースを挿入し、列をそろえる
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}

	//データ入力
	public void inputData() {
		for (int row = 0; row < this.getRow(); row++) {
			for (int column = 0; column < this.getColumn(); column++) {
				System.out.print((row + 1) + "行" + (column + 1) + "列のデータを入力してください : "); //内部的にはrow行column列ですが、数学的に違和感がないように+1して表示
				this.getMatrix()[row][column] = new java.util.Scanner(System.in).nextLine();
			}
		}
	}

	//row行をratio倍する
	public void rowBasicTransformation1_k(int row, String ratio) {
		outSideErrorCheck(row);
		for (int c = 0; c < this.column; c++) {
			this.matrix[row][c] = Fraction.kakeru(this.matrix[row][c], ratio);
		}
	}

	//row行を1/ratio倍する
	public void rowBasicTransformation1_w(int row, String ratio) {
		outSideErrorCheck(row);
		for (int c = 0; c < this.column; c++) {
			this.matrix[row][c] = Fraction.waru(this.matrix[row][c], ratio);
		}
	}

	//row1行をratio倍し、row2に加える
	public void rowBasicTransformation2_a(int row1, String ratio, int row2) {
		outSideErrorCheck(row1, row2);
		for (int c = 0; c < this.column; c++) {
			this.matrix[row2][c] = Fraction.tasu(this.matrix[row2][c], Fraction.kakeru(this.matrix[row1][c], ratio));
		}
	}

	//row1行をratio倍し、row2から引く
	public void rowBasicTransformation2_d(int row1, String ratio, int row2) {
		outSideErrorCheck(row1, row2);
		for (int c = 0; c < this.column; c++) {
			this.matrix[row2][c] = Fraction.hiku(this.matrix[row2][c], Fraction.kakeru(this.matrix[row1][c], ratio));
		}
	}

	//row1とrow2を入れ替える
	public void rowBasicTransformation3(int row1, int row2) {
		outSideErrorCheck(row1, row2);
		String[] temp = new String[this.column];
		for (int c = 0; c < this.column; c++) {
			temp[c] = this.matrix[row1][c]; //row1行をtempにコピー
		}
		for (int c = 0; c < this.column; c++) {
			this.matrix[row1][c] = this.matrix[row2][c]; //row2行をrow1行に上書きコピー
		}
		for (int c = 0; c < this.column; c++) {
			this.matrix[row2][c] = temp[c]; //tempをrow1行にコピー
		}
	}

	//行列簡約化-掃き出し法
	public void hakidashi() {

		//左下に0の坂を作る
		for (int r = 0; r < this.row; r++) {
			for (int c = 0; c < this.column; c++) {
				boolean allZero = true;
				boolean validColumnOrder = true;

				//現在のc列がすべて0かどうかを確かめる
				for (int i = 0; i < this.row; i++) {
					if (!this.matrix[i][c].equals("0")) {
						allZero = false;
					}
				}

				//現在のc列におけるr行以下において、0が非0要素よりも下の行になっているかどうか確かめる
				boolean isZero = false;
				for (int i = r; i < this.row; i++) {
					if (isZero == true) {
						if (this.matrix[i][c].equals("0")) { //文字列判定には""が必要なことを忘れていた
							isZero = true;
						} else {
							isZero = false;
							validColumnOrder = false; //isZeroがtrueからfalseに移り変わると、その列は正しく並んでいない ex)上から 4, 0, 2, 1
							i = this.row; //判定終了
						}
					} else {
						//						System.out.println("isZeroはfalseです");
						if (this.matrix[i][c].equals("0")) {
							isZero = true;
						} else {
							isZero = false;
						}
					}
				}

				//c列の一番上の行が0の時はその下の0でない行と入れ替える
				if (allZero == false && validColumnOrder == false) { //c列の下の行がすべて0でない場合 かつ 列が正しく並んでいない場合(0より下の行に非0がある)
					for (int i = r; i < this.row; i++) {
						if (this.matrix[i][c].equals("0") == false) {
							rowBasicTransformation3(0, i); //i行c列が0でない数の時にi行とr行を入れ替える
							i = this.row; //入れ替え終了
						}
					}
				}

				//r行目の先頭の値が1になるようにr行目全体をr行目の先頭の値で割り、r行目より下の行の先頭が0になるように引く
				if (!this.matrix[r][c].equals("0") && r < this.row - 1) { //現在の要素が0でない かつ 最後の行でない場合のみ実行
					for (int i = 1; r + i < this.row; i++) {
						rowBasicTransformation1_w(r, this.matrix[r][c]);
						rowBasicTransformation2_d(r, this.matrix[r + i][c], r + i);
						//						rowBasicTransformation2_d(r, Fraction.waru(this.matrix[r + i][c], this.matrix[r][c]), r + i);
					}

					//デバッグ用
					//					System.out.println();
					//					System.out.println("現在は " + r + "行" + c + "列 です");
					//					this.print();

					c = this.column; //次の行に進む
				} else if (!this.matrix[r][c].equals("0") && r == this.row - 1) {
					rowBasicTransformation1_w(r, this.matrix[r][c]);
					c = this.column; //次の行に進む
				}
			}
		}

		//主成分ある列の上の成分をすべて0にする
		for (int r = this.row - 1; r > 0; r--) {
			for (int c = 0; c < this.column; c++) {
				if (!this.matrix[r][c].equals("0")) {
					for (int i = 1; r - i >= 0; i++) {
						rowBasicTransformation2_d(r, this.matrix[r - i][c], r - i);
					}
					c = this.column; //この行の捜査終了
				}
				//デバッグ用
				//				System.out.println();
				//				System.out.println("現在は " + r + "行" + c + "列 です");
				//				this.print();
			}
		}
	}

	//行列外の行を指定していないかどうかチェックします
	public void outSideErrorCheck(int row1, int row2) {
		if (row1 < 0 || row1 >= this.row || row2 < 0 || row2 >= this.row) {
			throw new IllegalArgumentException("行列のサイズの外の行または列を指定してます"); //内部的には0行0列から始まるので1行1列から始まるとして引数を渡すとこのコンパイルエラーが出ます
		}
	}

	public void outSideErrorCheck(int row) {
		this.outSideErrorCheck(row, 0);
	}
}
