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

	Matrix() { //行列初期化
		System.out.println("行列の型を指定してください");
		System.out.print("行数：");
		this.row = new java.util.Scanner(System.in).nextInt();
		System.out.print("列数：");
		this.column = new java.util.Scanner(System.in).nextInt();
		this.matrix = new String[row][column];
		//行列初期化
		for(int r = 0; r < this.row; r++) {
			for(int c = 0; c < this.column; c++) {
				this.matrix[r][c] = " ";
			}
		}
		System.out.println(this.row + "x" + this.column + "型行列");
	}

	Matrix(int row, int column, int randomRange) { //デバッグ用自動行列生成
		this.row = row;
		this.column = column;
		this.matrix = new String[row][column];
		for (int r = 0; r < this.row; r++) {
			for (int c = 0; c < this.column; c++) {
				int bunshi = new java.util.Random().nextInt(randomRange) - randomRange / 2;
				int bunbo = new java.util.Random().nextInt(randomRange) - randomRange / 2;
				if (bunbo == 0) {
					bunbo = 1;
				}
				this.matrix[r][c] = "" + Fraction.yakubun(Fraction.buildFraction(bunshi, bunbo));
			}
		}
	}

	//行列出力
	public void print() {
		//行列内の最長文字数を走査
		int charCount = 0;
		for (int r = 0; r < this.row; r++) {
			for (int c = 0; c < this.column; c++) {
				if (charCount < this.matrix[r][c].length()) {
					charCount = this.matrix[r][c].length(); //最大を上書き
				}
			}
		}

		//列数描画
		System.out.print("      ");
		for (int column = 0; column < this.column; column++) {
			String columnS = "" + (column + 1);
			System.out.print(column+1);
			for(int i = 0; i < charCount + 4 - columnS.length(); i++) {
				System.out.print(" ");
			}
		}
		System.out.println();

		//1番上の横棒描画
		System.out.print("    +");
		for (int column = 0; column < this.getColumn(); column++) {
			for (int i = 0; i < charCount + 3; i++) { //文字数に合わせてスペースを挿入し、列をそろえる
				System.out.print("-");
			}
			System.out.print("+");
		}
		System.out.println();

		//行列本体描画
		for (int row = 0; row < this.getRow(); row++) {

			//列数描画
			String rowS = "" + (row + 1);
			for (int i = 0; i < 3 - rowS.length(); i++) {
				System.out.print(" ");
			}
			System.out.print(row + 1 + " ");

			//要素と縦棒描画
			System.out.print("| ");		//1番左上の縦棒描画
			for (int column = 0; column < this.getColumn(); column++) {
				System.out.print(this.getMatrix()[row][column]);
				for (int i = 0; i < charCount + 2 - this.getMatrix()[row][column].length(); i++) { //(最大文字数+2-現在の要素の文字数)分スペースを挿入し、列をそろえる
					System.out.print(" ");
				}
				System.out.print("| ");		//左端の縦棒描画
			}

			System.out.println();

			//横棒描画
			System.out.print("    +");	//1番左上の交点描画
			for (int column = 0; column < this.getColumn(); column++) {
				for (int i = 0; i < charCount + 3; i++) { //文字数に合わせてスペースを挿入し、交点を縦棒の位置にそろえる
					System.out.print("-");
				}
				System.out.print("+");	//交点描画
			}

			System.out.println();
		}
	}

	//データ入力
	public void inputData() {
		for (int row = 0; row < this.getRow(); row++) {
			for (int column = 0; column < this.getColumn(); column++) {
				System.out.println();
				this.matrix[row][column] = "_";	//現在入力要素をマーキング
				this.print();
				System.out.print((row + 1) + "行" + (column + 1) + "列のデータを入力してください : "); //内部的にはrow行column列ですが、数学的に違和感がないように+1して表示
				this.getMatrix()[row][column] = new java.util.Scanner(System.in).nextLine();
			}
		}
	}

	//データ修正
	public void correctionData() {
		System.out.println("修正するデータの行と列を指定してください");
		int fRow;
		int fColumn;
		//修正箇所指定
		do {
			System.out.print("行 : ");
			fRow = new java.util.Scanner(System.in).nextInt() - 1;
			System.out.print("列 : ");
			fColumn = new java.util.Scanner(System.in).nextInt() - 1;
			if(outSideEroorCheckInterface(fRow + 1, fColumn + 1)) {	//行列外要素指定チェック
				System.out.println("行列のサイズ外の行もしくは列を指定しています");
			}
		}while(outSideEroorCheckInterface(fRow + 1, fColumn + 1));
		this.matrix[fRow][fColumn] = "_";	//修正箇所にマーキング
		this.print();
		System.out.print((fRow + 1) + "行" + (fColumn + 1) + "列のデータを入力してください : ");
		this.getMatrix()[fRow][fColumn] = new java.util.Scanner(System.in).nextLine();
		System.out.println();
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
				if (allZero == false && validColumnOrder == false && this.matrix[r][c].equals("0")) { //c列の下の行がすべて0でない場合 かつ 列が正しく並んでいない場合(0より下の行に非0がある)
					for (int i = r + 1; i < this.row; i++) {
						if (this.matrix[i][c].equals("0") == false) {

							//デバッグ用
							this.print();
							System.out.println("\n0が下に来るように行を入れ替える | " + (r+1) + "行 <-> " + (i+1) + " 行\n");
							//							System.out.println("現在は " + r + "行 " + c + "列 です\n");

							rowBasicTransformation3(r, i); //i行c列が0でない数の時にi行とr行を入れ替える
							i = this.row; //入れ替え終了
						}
					}
				}

				//r行目の先頭の値が1になるようにr行目全体をr行目の先頭の値で割り、r行目より下の行の先頭が0になるように引く
				if (!this.matrix[r][c].equals("0")) { //現在の要素が0でない
					if (!this.matrix[r][c].equals("1")) {

						//デバッグ用
						this.print();
						System.out.println("\n行の主成分を1にする | " + (r+1) + "行 × " + Fraction.gyakusu(this.matrix[r][c]) + "\n");
						//						System.out.println("現在は " + r + "行 " + c + "列 です\n");

						rowBasicTransformation1_w(r, this.matrix[r][c]);
					}
					for (int i = 1; r + i < this.row; i++) { //最後の行でない場合のみ実行	<------ bug
						if (!this.matrix[r + i][c].equals("0")) {

							//デバッグ用
							this.print();
							System.out.println("\n主成分より下の行の成分を0にする | " + (r + 1 + i) + "行 - " + (r+1) + "行 × " + this.matrix[r + i][c] + "\n");
							//							System.out.println("現在は " + r + "行 " + c + "列 です\n");

							rowBasicTransformation2_d(r, this.matrix[r + i][c], r + i);
						}
					}
					c = this.column; //次の行に進む
				}
			}
		}

		//主成分ある列の上の成分をすべて0にする
		for (int r = this.row - 1; r > 0; r--) {
			for (int c = 0; c < this.column; c++) {
				if (!this.matrix[r][c].equals("0")) {
					for (int i = 1; r - i >= 0 && !this.matrix[r - i][c].equals("0"); i++) {

						//デバッグ用
						this.print();
						System.out.println(
								"\n主成分より上にある成分を0にする | " + ((r+1) - i) + "行 - " + (r+1) + "行 × " + this.matrix[r - i][c] + "\n");
						//						System.out.println("現在は " + r + "行 " + c + "列 です\n");

						rowBasicTransformation2_d(r, this.matrix[r - i][c], r - i);
					}
					c = this.column; //この行の捜査終了
				}
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

	//ユーザが入力した行と列が行列のサイズ外を指定していないかどうかチェックします
	public boolean outSideEroorCheckInterface(int row, int column) {
		boolean err;
		if (row <= 0 || row > this.row || column <= 0 || column > this.column) {	//ユーザの入力は1行1列から始まります
			err = true;
		}else {
			err = false;
		}
		return err;		//エラーがあるときtrue, ないときfalseを返します
	}
}
