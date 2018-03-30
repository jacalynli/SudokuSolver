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
		board.setUpList();
		for (Square[] rows : board.sudokuBoard) {
			for (Square s : rows) {
				assertEquals(s.list, "1234");
			}
		}
			
	}
	
	@Test
	public void testListRows() {
		String[] array = {
				"1020", 
				"0000",
				"0000",
				"0000"
				};
		SudokuBoard board = new SudokuBoard(array);
		board.setUpList();
		board.removeValues();
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
	
	@Test
	public void testListRowsAndColumns() {
		String[] array = {
				"1020", 
				"0000",
				"0000",
				"0000"
				};
		SudokuBoard board = new SudokuBoard(array);
		board.setUpList();
		board.removeValues();
		for(int i = 0; i < board.sudokuBoard.length; i++) {
			for(int j = 0; j < board.sudokuBoard[i].length; j++) {
				Square s = board.sudokuBoard[i][j];
				if (i == 0 && (j == 1 || j == 3)) {
					assertEquals(s.list, "34");
				} else if (i == 0) {
					assertEquals(s.list, "");
				} else if (j == 0) {
					assertEquals(s.list, "234");
				} else if (j == 2) {
					assertEquals(s.list, "134");
				} 
			}
		}		
	}
	
	@Test
	public void testListBoxes() {
		String[] array = {
				"4000", 
				"0000",
				"0030",
				"0000"
				};
		SudokuBoard board = new SudokuBoard(array);
		board.setUpList();
		board.removeValues();
		for(int i = 0; i < board.sudokuBoard.length; i++) {
			for(int j = 0; j < board.sudokuBoard[i].length; j++) {
				Square s = board.sudokuBoard[i][j];
				if (i == 0 && j == 1) {
					assertEquals(s.list, "123");
				} else if (i == 1 && (j == 0 || j == 1)) {
					assertEquals(s.list, "123");
				} else if (i == 2 && j == 3) {
					assertEquals(s.list, "124");
				} else if (i == 3 && (j == 2 || j == 3)) {
					assertEquals(s.list, "124");
				} 
			}
		}		
	}
	
	@Test
	public void testListWholeBoard() {
		String[] array = {
				"1000", 
				"0020",
				"0300",
				"0000"
				};
		SudokuBoard board = new SudokuBoard(array);
		board.setUpList();
		board.removeValues();
		for(int i = 0; i < board.sudokuBoard.length; i++) {
			for(int j = 0; j < board.sudokuBoard[i].length; j++) {
				Square s = board.sudokuBoard[i][j];
				if (i == 0) {
					if (j == 1) {assertEquals(s.list, "24");}
					else if (j == 2 || j == 3) {assertEquals(s.list, "34");}
				} else if (i == 1) {
					if (j == 0) {assertEquals(s.list, "34");}
					else if (j == 1) {assertEquals(s.list, "4");}
					else if (j == 3) {assertEquals(s.list, "134");}
				} else if (i == 2) {
					if (j == 0) {assertEquals(s.list, "24");}
					else if (j == 2) {assertEquals(s.list, "14");}
					else if (j == 3) {assertEquals(s.list, "124");}
				} else if (i == 3) {
					if (j == 0) {assertEquals(s.list, "24");}
					else if (j == 1) {assertEquals(s.list, "124");}
					else if (j == 2) {assertEquals(s.list, "134");}
					else if (j == 3) {assertEquals(s.list, "1234");}
				} 
			}
		}		
	}
	
	@Test
	public void testListFillOne() {
		String[] array = {
				"1000", 
				"0020",
				"0300",
				"0000"
				};
		String[] shouldReturn = {
				"1___",
				"_42_",
				"_3__",
				"____"
				};
		SudokuBoard board = new SudokuBoard(array);
		board.setUpList();
		board.removeValues();
		board.fillOne();
		assertArrayEquals(board.returnBoard(), shouldReturn);
	}
	
	@Test
	public void testSolveableByFillOne() {
		String[] array = {
				"1000", 
				"0020",
				"2300",
				"0000"
				};
		String[] shouldReturn = {
				"1243",
				"3421",
				"2314",
				"4132"
				};
		SudokuBoard board = new SudokuBoard(array);
		board.solve();
		assertArrayEquals(board.returnBoard(), shouldReturn);
	}
	
	@Test
	public void testNotSolveableByFillOne() {
		String[] array = {
				"1000", 
				"0020",
				"0300",
				"0000"
				};
		String[] shouldReturn = {
				"12__",
				"3421",
				"_3__",
				"_1__"
				};
		SudokuBoard board = new SudokuBoard(array);
		board.setUpList();
		board.removeValues();
		board.fillOne();
		board.removeValues();
		board.fillOne();
		board.removeValues();
		board.fillOne();
		assertArrayEquals(board.returnBoard(), shouldReturn);
	}
	
	@Test
	public void testSolveableFlash1() {
		String[] array = {
				"000000901", 
				"049260583",
				"378100420",
				"215403607",
				"800071235",
				"037529000",
				"901748362",
				"000006000",
				"060950814"
				};
		String[] shouldReturn = {
				"526834971",
				"149267583",
				"378195426",
				"215483697",
				"894671235",
				"637529148",
				"951748362",
				"482316759",
				"763952814",
				};
		SudokuBoard board = new SudokuBoard(array);
		board.solve();
		assertArrayEquals(board.returnBoard(), shouldReturn);
	}
	
	@Test
	public void testSolveableFlash2() {
		String[] array = {
				"000104890", 
				"900007521",
				"018005643",
				"002916375",
				"070450206",
				"306782009",
				"860020134",
				"091300702",
				"043000058"
				};
		String[] shouldReturn = {
				"625134897",
				"934867521",
				"718295643",
				"482916375",
				"179453286",
				"356782419",
				"867529134",
				"591348762",
				"243671958",
				};
		SudokuBoard board = new SudokuBoard(array);
		board.solve();
		assertArrayEquals(board.returnBoard(), shouldReturn);
	}
	
	@Test
	public void testSolveableExpert1() {
		String[] array = {
				"000000090", 
				"000004200",
				"079000400",
				"900240806",
				"800060000",
				"000000050",
				"056803970",
				"000706004",
				"007050300"
				};
		String[] shouldReturn = {
				"625134897",
				"934867521",
				"718295643",
				"482916375",
				"179453286",
				"356782419",
				"867529134",
				"591348762",
				"243671958",
				};
		SudokuBoard board = new SudokuBoard(array);
		board.solve();
		board.printBoard();
		board.printNotes();
		assertArrayEquals(board.returnBoard(), shouldReturn);
	}
	
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
		assertArrayEquals(board.returnBoard(), array);
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
		assertArrayEquals(board.returnBoard(), shouldPrint);
	}
	
}
