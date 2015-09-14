
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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}
	
}
