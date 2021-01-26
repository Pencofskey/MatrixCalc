package 行列簡約化;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class Matrix {
	private Value[][] matrix; //内部的には0行0列から始まっています 普通の行列式のように1行1列から始まってると考えて引数を渡すと配列エラーになります
	private String[][] string;
	private int row; //行数
	private int column; //列数

	public int getRow() {
		return this.row;
	}

	public int getColumn() {
		return this.column;
	}

	public Value[][] getMatrix() {
		return this.matrix;
	}

	Matrix() { //行列初期化
		System.out.println("行列の型を指定してください");
		boolean err;
		do {		//整数以外をはじく
			System.out.print("行数：");
			String rowSize = new java.util.Scanner(System.in).nextLine();
			if(rowSize.matches("[1-9]")) {
				err = false;
				this.row = Integer.parseInt(rowSize);
			}else {
				err = true;
				System.out.println("行数は1桁の自然数で指定");
			}
		}while(err);
		do {		//整数以外をはじく
			System.out.print("列数：");
			String columnSize = new java.util.Scanner(System.in).nextLine();
			if(columnSize.matches("[1-9]")) {
				err = false;
				this.column = Integer.parseInt(columnSize);
			}else {
				err = true;
			}
			if(err) {
				System.out.println("行数は1桁の自然数で指定");
			}
		}while(err);

		//行列初期化
		this.reset();

		System.out.println(this.row + "x" + this.column + "型行列");
	}

	Matrix(int row, int column) { //デバッグ用自動行列生成
		this.row = row;
		this.column = column;
		this.matrix = new Value[row][column];
		this.string = new String[row][column];
		for (int r = 0; r < this.row; r++) {
			for (int c = 0; c < this.column; c++) {
				this.matrix[r][c] = new Value();
				this.string[r][c] = "";
			}
		}
	}

	//行列リセット
	public void reset() {
		this.matrix = new Value[this.row][this.column];
		this.string = new String[row][column];
		for (int r = 0; r < this.row; r++) {
			for (int c = 0; c < this.column; c++) {
				this.matrix[r][c] = new Value();
				this.string[r][c] = "";
			}
		}
	}

	//列内最大文字数走査
	public int detectColumnMax(int c) {	//c列の最大文字数を返します
		int max = 0;
		for(int i = 0; i < this.row; i ++) {
			if(max < this.string[i][c].length()) {
				max = this.string[i][c].length();
			}
		}
		return max;
	}


	//行列出力
	public void print() {
		
		//列数描画
		System.out.print("      ");
		for (int column = 0; column < this.column; column++) {
			String columnS = "" + (column + 1);
			System.out.print(column + 1);
			for (int i = 0; i < detectColumnMax(column) + 4 - columnS.length(); i++) {
				System.out.print(" ");
			}
		}
		System.out.println();

		//1番上の横棒描画
		System.out.print("    +");
		for (int column = 0; column < this.getColumn(); column++) {
			for (int i = 0; i < detectColumnMax(column) + 3; i++) { //文字数に合わせてスペースを挿入し、列をそろえる
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
			System.out.print("| "); //1番左上の縦棒描画
			for (int column = 0; column < this.getColumn(); column++) {
				System.out.print(this.string[row][column]);
				for (int i = 0; i < detectColumnMax(column) + 2 - this.string[row][column].length(); i++) { //(最大文字数+2-現在の要素の文字数)分スペースを挿入し、列をそろえる
					System.out.print(" ");
				}
				System.out.print("| "); //左端の縦棒描画
			}

			System.out.println();

			//横棒描画
			System.out.print("    +"); //1番左上の交点描画
			for (int column = 0; column < this.getColumn(); column++) {
				for (int i = 0; i < detectColumnMax(column) + 3; i++) { //文字数に合わせてスペースを挿入し、交点を縦棒の位置にそろえる
					System.out.print("-");
				}
				System.out.print("+"); //交点描画
			}

			System.out.println();
		}
	}

	//データ入力
	public void inputData() {
		for (int row = 0; row < this.getRow(); row++) {
			for (int column = 0; column < this.getColumn(); column++) {
				System.out.println();
				this.string[row][column] = "_"; //現在入力要素をマーキング
				this.print();
				this.inputData(row, column);
			}
		}
	}
	public void inputData(int row, int column) { //row行column列に数値を代入する
		boolean err;
		do {
			err = false;
			try {
				System.out.print((row + 1) + "行" + (column + 1) + "列のデータを入力してください : "); //内部的にはrow行column列ですが、数学的に違和感がないように+1して表示
				String input = new java.util.Scanner(System.in).nextLine();
				this.getMatrix()[row][column] = Value.convert(input);
				this.string[row][column] = this.matrix[row][column].toString();
			}catch(Exception e) {
				System.out.println("入力のフォーマットが違います");
				err = true;
			}
		} while (err);
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
			if (outSideEroorCheckInterface(fRow + 1, fColumn + 1)) { //行列外要素指定チェック
				System.out.println("行列のサイズ外の行もしくは列を指定しています");
			}
		} while (outSideEroorCheckInterface(fRow + 1, fColumn + 1));
//		this.matrix[fRow][fColumn] = "_"; //修正箇所にマーキング
		this.print();
		this.inputData(fRow, fColumn);
		System.out.println();
	}

	//row行をratio倍する
	public void rowBasicTransformation1_k(int row, Value v) {
		outSideErrorCheck(row);
		for (int c = 0; c < this.column; c++) {
			this.matrix[row][c].multiply(v);
			this.string[row][c] = this.matrix[row][c].toString();
		}
	}

	//row行を1/ratio倍する
	public void rowBasicTransformation1_w(int row, Value v) {
		outSideErrorCheck(row);
		for (int c = 0; c < this.column; c++) {
			this.matrix[row][c].div(v);
			this.string[row][c] = this.matrix[row][c].toString();
		}
	}

	//row1行をratio倍し、row2に加える
	public void rowBasicTransformation2_a(int row1, Value v, int row2) {
		outSideErrorCheck(row1, row2);
		for (int c = 0; c < this.column; c++) {
			this.matrix[row2][c].add(Value.multiply(this.matrix[row1][c], v));
			this.string[row2][c] = this.matrix[row2][c].toString();
		}
	}

	//row1行をratio倍し、row2から引く
	public void rowBasicTransformation2_d(int row1, Value v, int row2) {
		outSideErrorCheck(row1, row2);
		for (int c = 0; c < this.column; c++) {
			this.matrix[row2][c].delta(Value.multiply(this.matrix[row1][c], v));
			this.string[row2][c] = this.matrix[row2][c].toString();
		}
	}

	//row1とrow2を入れ替える
	public void rowBasicTransformation3(int row1, int row2) {
		outSideErrorCheck(row1, row2);
		Value temp = new Value();
		for (int c = 0; c < this.column; c++) {
			temp = this.matrix[row1][c]; //row1行をtempにコピー
			this.matrix[row1][c] = this.matrix[row2][c];
			this.matrix[row2][c] = temp;
			this.string[row2][c] = this.matrix[row2][c].toString();
			this.string[row1][c] = this.matrix[row1][c].toString();
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
					if (!this.matrix[i][c].equals(new Value())) {
						allZero = false;
					}
				}

				//現在のc列におけるr行以下において、0が非0要素よりも下の行になっているかどうか確かめる
				boolean isZero = false;
				for (int i = r; i < this.row; i++) {
					if (isZero == true) {
						if (this.matrix[i][c].equals(new Value())) { //文字列判定には""が必要なことを忘れていた
							isZero = true;
						} else {
							isZero = false;
							validColumnOrder = false; //isZeroがtrueからfalseに移り変わると、その列は正しく並んでいない ex)上から 4, 0, 2, 1
							i = this.row; //判定終了
						}
					} else {
						//						System.out.println("isZeroはfalseです");
						if (this.matrix[i][c].equals(new Value())) {
							isZero = true;
						} else {
							isZero = false;
						}
					}
				}

				//c列の一番上の行が0の時はその下の0でない行と入れ替える
				if (allZero == false && validColumnOrder == false && this.matrix[r][c].equals(new Value())) { //c列の下の行がすべて0でない場合 かつ 列が正しく並んでいない場合(0より下の行に非0がある)
					for (int i = r + 1; i < this.row; i++) {
						if (this.matrix[i][c].equals(new Value()) == false) {

							//デバッグ用
							this.print();
							System.out.println("\n0が下に来るように行を入れ替える\n " + (r + 1) + "行 <-> " + (i + 1) + " 行\n");
							//							System.out.println("現在は " + r + "行 " + c + "列 です\n");

							rowBasicTransformation3(r, i); //i行c列が0でない数の時にi行とr行を入れ替える
							i = this.row; //入れ替え終了
						}
					}
				}

				//r行目の先頭の値が1になるようにr行目全体をr行目の先頭の値で割り、r行目より下の行の先頭が0になるように引く
				if (!this.matrix[r][c].equals(new Value())) { //現在の要素が0でない
					if (!this.matrix[r][c].equals(new Value(new Polynomial(1), new Polynomial(1)))) {

						//デバッグ用
						System.out.println("\n行の主成分を1にする\n " + (r + 1) + "行 × " + "kakerukazu(gyakusu)\n");
						rowBasicTransformation1_w(r, this.matrix[r][c]);
						this.print();
						//						System.out.println("現在は " + r + "行 " + c + "列 です\n");

					}
					if(r != this.row - 1){
						System.out.println("\n主成分より下の行の成分を0にする");
					}
					for (int i = 1; r + i < this.row; i++) { //最後の行でない場合のみ実行
						if (!this.matrix[r + i][c].equals(new Value())) {

							//デバッグ用
							//							System.out.println("現在は " + r + "行 " + c + "列 です\n");
							System.out.println(" " + (r + 1 + i) + "行 - " + (r + 1) + "行 × " + this.matrix[r + i][c]);
							rowBasicTransformation2_d(r, this.matrix[r + i][c], r + i);
						}
					}
					if(r != this.row - 1){
						System.out.println();
						this.print();
					}
					c = this.column; //次の行に進む
				}
			}
		}

		//主成分ある列の上の成分をすべて0にする
		for (int r = this.row - 1; r > 0; r--) {
			for (int c = 0; c < this.column; c++) {
				if (!this.matrix[r][c].equals(new Value())) {
					System.out.println("\n主成分より上にある成分を0にする");
					for (int i = 1; r - i >= 0; i++) {
						if(!this.matrix[r - i][c].equals(new Value())) {

							//デバッグ用
							System.out.println(" " + ((r + 1) - i) + "行 - " + (r + 1) + "行 × " + this.matrix[r - i][c]);

							//						System.out.println("現在は " + r + "行 " + c + "列 です\n");

							rowBasicTransformation2_d(r, this.matrix[r - i][c], r - i);
						}
					}
					System.out.println();
					this.print();
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
		if (row <= 0 || row > this.row || column <= 0 || column > this.column) { //ユーザの入力は1行1列から始まります
			err = true;
		} else {
			err = false;
		}
		return err; //エラーがあるときtrue, ないときfalseを返します
	}

	//行列をクリップボードにコピーします
	public void copyToClipBoard() {
		StringBuilder cb = new StringBuilder();
		for(int row = 0; row < this.row; row++) {
			cb.append("[");
			for(int column = 0; column < this.column; column++) {
				cb.append(this.matrix[row][column]);
				if(column < this.column - 1) {
					cb.append(",");
				}
			}
			cb.append("]");
			if(row < this.row - 1) {
				cb.append(",");
			}
		}
		//debug
//		System.out.println(cb.toString());
	    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	    StringSelection selection = new StringSelection(cb.toString());
	    clipboard.setContents(selection, selection);
	}
	
	public void copyToString() {
		//表示用String配列に代入
		for(int r = 0; r < this.row; r++) {
			for(int c = 0; c < this.column; c++) {
				this.string[r][c] = this.matrix[r][c].toString();
			}
		}
	}
}
