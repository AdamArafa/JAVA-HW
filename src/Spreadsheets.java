import java.util.Scanner;


public class Spreadsheets {

	Scanner input = new Scanner(System.in);
	private SheetCell[][] cells;
	
	public Spreadsheets(int col, int row) {
		// TODO Auto-generated constructor stub
		cells = new SheetCell[row][col];
		for (int r = 0; r < cells.length; ++r) {
			for (int c = 0; c < cells[c].length; ++c) {
				String value = input.next();
				if (value.charAt(0) == '=') {
					cells[r][c] = new SheetCell(r, c, null, value);
					
				}
				else {
					cells[r][c] = new SheetCell(r, c, value, null);
					
				}
				
			}
			
		}
		
	}
	
	public void printSpreadsheets() {
		for (SheetCell[] r : cells) {
			for (SheetCell c : r) {
				System.out.print(c.value + " ");
				
			}
			System.out.println();
			
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		int col = input.nextInt();
		int row = input.nextInt();
		Spreadsheets spreadsheets = new Spreadsheets(col, row);
		spreadsheets.printSpreadsheets();
		
	}

}
