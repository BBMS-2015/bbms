package database;

public class BloodBankTable {

	public Object[][] getTablerows() {
		return tablerows;
	}
	public void setTablerows(Object[][] tablerows) {
		this.tablerows = tablerows;
	}
	public String[] getAttributeNames() {
		return AttributeNames;
	}
	public void setAttributeNames(String[] attributeNames) {
		AttributeNames = attributeNames;
	}

    public int getNumberOfColumns() {
		return NumberOfColumns;
	}
	public void setNumberOfColumns(int numberOfColumns) {
		NumberOfColumns = numberOfColumns;
	}
	public int getNumberOfRows() {
		return NumberOfRows;
	}
	public void setNumberOfRows(int numberOfRows) {
		NumberOfRows = numberOfRows;
	}
	Object[][] tablerows=null;
    String [] AttributeNames;
	int NumberOfColumns;
    int NumberOfRows;


}
