public class SudokuBoard {
	//create an array of squares
	Square[][] sudokuBoard;
	int sideLength;
	boolean done = false;
	
	public SudokuBoard(String[] input) {
		//Check that the size of board is a perfect square
		sideLength = (int) Math.sqrt(input.length);
		if (Math.pow(sideLength, 2) != input.length) {
			System.out.println(Math.pow(sideLength, 2) + " != " + Math.pow(input.length, 2));
			System.out.println("Input is not a square");
			System.exit(1);
		}
		
		//Initialize the sudokuBoard
		sudokuBoard = new Square[input.length][input.length];
		
		//Take the input and create an array of squares
		for (int i = 0; i < input.length; i++) {
			char[] charArray = input[i].toCharArray();
			for (int j = 0; j < charArray.length; j++) {
				int value = Integer.parseInt(Character.toString(charArray[j]));
				Square square = new Square(value);
				sudokuBoard[i][j] = square;
			}
		}
	}
	
	public void solve() {
		setUpList();
		removeValues();
		
		String[] start = returnBoard();
		fillOne();
		removeValues();
		String[] end = returnBoard();
		
		while (!arrayEquals(start, end)) {
			done = true;
			start = end;
			fillOne();
			removeValues();
			end = returnBoard();
		}
		
		start = end;
		fillDistinct();
		removeValues();
		end = returnBoard();
		
		while (!arrayEquals(start, end)) {
			done = true;
			start = end;
			fillDistinct();
			removeValues();
			end = returnBoard();
		}
		
		if (!done) {
			System.out.println("Error. Cannot be solved.");
		}	
	}
	
	public void setUpList() {
		//set up the list of numbers in each square
		for (Square[] row : sudokuBoard) {
			for (Square s : row) {
				s.list = "";
				for (int i = 1; i <= sudokuBoard.length; i++) {
					if (s.value != 0) {
						break; 
					} else {
						s.list += i;					
					}
				}
			}
		}
	}
	
	public void removeValues() {
		//remove the values if they are in the same row, column, or box.
		for (int i = 0; i < sudokuBoard.length; i++) {
			for (int j = 0; j < sudokuBoard.length; j++) {
				if (sudokuBoard[i][j].value != 0) {
					String v = sudokuBoard[i][j].value + "";
					//same row
					for (int k = 0; k < sudokuBoard.length; k++) {
						String l = sudokuBoard[i][k].list;
						if (l.indexOf(v) == -1) {
							continue;
						} else {
							l = l.replaceFirst(v, "");
						}
						sudokuBoard[i][k].list = l;
					}
					//same column
					for (int k = 0; k < sudokuBoard.length; k++) {
						String l = sudokuBoard[k][j].list;
						if (l.indexOf(v) == -1) {
							continue;
						} else {
							l = l.replaceFirst(v, "");
						}
						sudokuBoard[k][j].list = l;
					}
					//same box
					int startX = (i / sideLength) * sideLength;
					int endX = startX + sideLength;
					int startY = (j / sideLength) * sideLength;
					int endY = startY + sideLength;
					for (int x = startX; x < endX; x++) {
						for (int y = startY; y < endY; y++) {
							String l = sudokuBoard[x][y].list;
							if (l.indexOf(v) == -1) {
								continue;
							} else {
								l = l.replaceFirst(v, "");
							}
							sudokuBoard[x][y].list = l;
						}
					}
				} else {
					done = false;
				}
			}
		}
	}
	
	public void fillOne() {
		for (int i = 0; i < sudokuBoard.length; i++) {
			for (int j = 0; j < sudokuBoard.length; j++) {
				if (sudokuBoard[i][j].list.length() == 1) {
					sudokuBoard[i][j].value = Integer.parseInt(sudokuBoard[i][j].list);
				}
			}
		}
	}
	
	public void fillDistinct() {
		checkRows();
		checkColumns();
		checkBoxes();
	}
	
	public void checkRows() {
		for (int i = 0; i < sudokuBoard.length; i++) {
			String l = "";
			for (int j = 0; j < sudokuBoard.length; j++) {
				l += sudokuBoard[i][j].list;
			}
			while(l.length() > 0) {
				String curr = Character.toString(l.charAt(0));
				if (l.replaceAll(curr, "").length() == l.length() - 1) {
					for(int k = 0; k < sudokuBoard.length; k++) {
						if (sudokuBoard[i][k].value == 0 
							&& sudokuBoard[i][k].list.indexOf(curr) != -1) {
							sudokuBoard[i][k].value = Integer.parseInt(curr);
						}
					}
				} else {
					l = l.replaceAll(curr, "");
					continue;
				}
			}
		}
	}
	
	public void checkColumns() {
		for (int i = 0; i < sudokuBoard.length; i++) {
			String l = "";
			for (int j = 0; j < sudokuBoard.length; j++) {
				l += sudokuBoard[j][i].list;
			}
			while(l.length() > 0) {
				String curr = Character.toString(l.charAt(0));
				if (l.replaceAll(curr, "").length() == l.length() - 1) {
					for(int k = 0; k < sudokuBoard.length; k++) {
						if (sudokuBoard[k][i].value == 0 
							&& sudokuBoard[k][i].list.indexOf(curr) != -1) {
							sudokuBoard[k][i].value = Integer.parseInt(curr);
						}
					}
				} else {
					l = l.replaceAll(curr, "");
					continue;
				}
			}
		}
	}
	
	public void checkBoxes() {
		
		/*
		for (int i = 0; i < sudokuBoard.length; i++) {
			String l = "";
			for (int j = 0; j < sudokuBoard.length; j++) {
				l += sudokuBoard[i][j].list;
			}
			while(l.length() > 0) {
				String curr = Character.toString(l.charAt(0));
				if (l.replaceAll(curr, "").length() == l.length() - 1) {
					for(int k = 0; k < sudokuBoard.length; k++) {
						if (sudokuBoard[i][k].value != 0 
							&& sudokuBoard[i][k].list.indexOf(curr) != -1) {
							sudokuBoard[i][k].value = Integer.parseInt(curr);
						}
					}
				} else {
					l = l.replaceAll(curr, "");
					continue;
				}
			}
		} */
	}
	
	public String[] returnBoard() {
		String[] output = new String[sudokuBoard.length];
		for (int i = 0; i < output.length; i++) {
			output[i] = "";
			for (int j = 0; j < output.length; j++) {
				if (sudokuBoard[i][j].value == 0) {
					output[i] += "_";
				} else {
					output[i] += sudokuBoard[i][j].value;
				}
			}
		}
		return output;
	}
	
	public void printBoard() {
		for(int i = 0; i < returnBoard().length; i++) {
			System.out.println(returnBoard()[i]);
		}
	}
	
	public void printNotes() {
		for (int i = 0; i < sudokuBoard.length; i++) {
			for (int j = 0; j < sudokuBoard.length; j++) {
				System.out.print(sudokuBoard[i][j].list + "\t");
			}
			System.out.println();
		}
	}
	
	public static boolean arrayEquals(String[] l, String[] l2) {
        if (l.length != l2.length) {return false;}
        else {
            for (int i = 0; i < l.length; i++) {
                if (!l[i].equals(l2[i])) {return false;}
            }
            return true;
        }
    }
	
}
