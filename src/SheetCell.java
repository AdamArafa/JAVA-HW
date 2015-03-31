

public class SheetCell {
	
	String value;
	String formula;
	int    row;
	int    column;

	SheetCell(int r, int c) {
		row        = r;
		column     = c;
		value      = null;
		formula    = null;
	}

	public SheetCell(int r, int c, String value, String formula) {
		this(r,c);
	    this.value   = value;
	    this.formula = formula;
	}
	
}
