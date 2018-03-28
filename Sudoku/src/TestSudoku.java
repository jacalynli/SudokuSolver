import static org.junit.Assert.*;

import org.junit.Test;

public class TestSudoku {

	@Test
	public void testListEmptyBoard() {
		String[] array = {
				"0000", 
				"0000",
				"0000",
				"0000"
				};
		SudokuBoard board = new SudokuBoard(array);
		for (Square[] rows : board.sudokuBoard) {
			for (Square s : rows) {
				assertEquals(s.list, "1234");
			}
		}
			
	}
	
	/*
	@Test
	public void testListAlmostEmptyBoard() {
		String[] array = {
				"1000", 
				"0000",
				"0020",
				"0000"
				};
		SudokuBoard board = new SudokuBoard(array);
		for(int i = 0; i < board.sudokuBoard.length; i++) {
			for(int j = 0; j < board.sudokuBoard[i].length; j++) {
				Square s = board.sudokuBoard[i][j];
				if ((i == 0 && j == 0) || (i == 2 && j == 2)) {
					assertEquals(s.list, "");
				} else if ( (i == 0 && j == 2) || (i == 2 && j == 0) ){
					assertEquals(s.list, "34");
				} else if (i == 0 || j == 0) {
					assertEquals(s.list, "234");
				} else if (i == 2 || j == 2) {
					assertEquals(s.list, "134");
				} else if ( (i == 1 || i == 3) && (j == 1 || j == 3) ) {
					assertEquals(s.list, "1234");
				} else {
					System.out.println("Error! Something is wrong with the test!");
				}
			}
		}		
	}
	*/
	
	@Test
	public void testListRows() {
		String[] array = {
				"1020", 
				"0000",
				"0000",
				"0000"
				};
		SudokuBoard board = new SudokuBoard(array);
		for(int i = 0; i < board.sudokuBoard.length; i++) {
			for(int j = 0; j < board.sudokuBoard[i].length; j++) {
				Square s = board.sudokuBoard[i][j];
				if (i == 0 && (j == 1 || j == 3)) {
					assertEquals(s.list, "34");
				} else if (i == 0) {
					assertEquals(s.list, "");
				}
			}
		}		
	}
	
	/*
	@Test
	public void testListRows() {
		String[] array = {
				"1020", 
				"0000",
				"0000",
				"0000"
				};
		SudokuBoard board = new SudokuBoard(array);
		for(int i = 0; i < board.sudokuBoard.length; i++) {
			for(int j = 0; j < board.sudokuBoard[i].length; j++) {
				Square s = board.sudokuBoard[i][j];
				if (i == 0 && (j == 1 || j == 3)) {
					assertEquals(s.list, "34");
				} else if (i == 0) {
					assertEquals(s.list, "");
				} else if (j == 1 || j == 3) {
					assertEquals(s.list, "1234");
				} else if (j == 0) {
					assertEquals(s.list, "234");
				} else if (j == 2) {
					assertEquals(s.list, "134");
				} else {
					System.out.println("Error! Something is wrong with the test!");
				}
			}
		}		
	}
	*/
	
	//Test to make sure that JUnit tests were working
	@Test
	public void test() {
		int sum = 1 + 1;
		assertEquals(sum, 2);
	}

	//Tests if the print method works
	@Test
	public void testPrint() {
		String[] array = {
				"1234", 
				"4213",
				"2431",
				"3142"
				};
		SudokuBoard board = new SudokuBoard(array);
		assertArrayEquals(board.printBoard(), array);
	}
	
	//Tests if the print method replaces 0's with _'s
	@Test
	public void testPrintWithSpaces() {
		String[] array = {
				"1034", 
				"4203",
				"2430",
				"3142"
				};
		String[] shouldPrint = {
				"1_34", 
				"42_3",
				"243_",
				"3142"
				};
		SudokuBoard board = new SudokuBoard(array);
		assertArrayEquals(board.printBoard(), shouldPrint);
	}
	
}
