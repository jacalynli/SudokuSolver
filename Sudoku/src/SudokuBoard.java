public class SudokuBoard {
	//create an array of squares
	Square[][] sudokuBoard;
	
	public SudokuBoard(String[] input) {
		//Check that the size of board is a perfect square
		if (Math.pow(Math.sqrt(input.length), 2) != input.length) {
			System.out.println(Math.pow(Math.sqrt(input.length), 2) + " != " + Math.pow(input.length, 2));
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
		
		setUpList();
		
	}
	
	private void setUpList() {
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
		
		//remove the values if they are in the same row, column, or box.
		for (int i = 0; i < sudokuBoard.length; i++) {
			for (int j = 0; j < sudokuBoard.length; j++) {
				if (sudokuBoard[i][j].value != 0) {
					for (j = 0; j < sudokuBoard.length; j++) {
						int v = sudokuBoard[i][j].value;
						String l = sudokuBoard[i][j].list;
						if (l.indexOf(v) == -1) {
							break;
						}
						if (l.indexOf(v) + 1 < l.length()) {
							l = l.substring(0, l.indexOf(v)) + l.substring(l.indexOf(v) + 1); 
						} else {
							l = l.substring(0, l.indexOf(v));
						}
					}
				}
			}
		}
	}
	
	public String[] printBoard() {
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
	
	
}
