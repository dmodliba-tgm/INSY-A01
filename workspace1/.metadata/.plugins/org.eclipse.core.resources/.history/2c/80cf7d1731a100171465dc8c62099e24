package jdbcdemo;

import java.util.List;

import javax.swing.table.AbstractTableModel;

class FlughaefenTableModel extends AbstractTableModel {

	private static final int AIRPORTCODE_COL = 0;
	private static final int NAME_COL = 1;
	private static final int COUNTRY_COL = 2;
	private static final int CITY_COL = 3;

	private String[] columnNames = { "Airportcode", "Name", "Country",
			"City" };
	private List<Flughaefen> flughaefen;

	public FlughaefenTableModel(List<Flughaefen> theFlughaefen) {
		flughaefen = theFlughaefen;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return flughaefen.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {

		Flughaefen tempFlughaefen = flughaefen.get(row);

		switch (col) {
		case AIRPORTCODE_COL:
			return tempFlughaefen.getAirportcode();
		case NAME_COL:
			return tempFlughaefen.getName();
		case COUNTRY_COL:
			return tempFlughaefen.getCountry();
		case CITY_COL:
			return tempFlughaefen.getCity();
		default:
			return tempFlughaefen.getAirportcode();
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}