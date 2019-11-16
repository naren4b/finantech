package util;

import java.util.HashMap;
import java.util.Map;

public final class ScripColumns {

    static Map<String, ScripColumn> columnNameMap =new HashMap<String, ScripColumn>();

    static Map<Integer, ScripColumn> columnIndexMap =new HashMap<Integer, ScripColumn>();

    private static ScripColumns scripColumns = new ScripColumns();

    private final boolean isInitialized;

    public static void initialize(String[] columnNames) {
        scripColumns = new ScripColumns();
        int index = 0;
        for (String columnName : columnNames) {
            ScripColumn scripColumn = new ScripColumn(columnName, index,
                    columnName);
            columnNameMap.put(columnName, scripColumn);
            columnIndexMap.put(index, scripColumn);
            index++;
        }
    }

    public static ScripColumns getInstance() {
        return scripColumns;
    }

    private ScripColumns() {
        isInitialized = true;
    }

    public static int getColumnIndex(String columnName) {
        return columnNameMap.get(columnName).getColumnIndex();
    }

    public static String getColumnName(int index) {
        return columnIndexMap.get(index).getColumnName();
    }

    public static Class getColumnType(String columnName) {
        return columnNameMap.get(columnName).getType();
    }
}

class ScripColumn {

    String columnName;

    int columnIndex;

    String columnDisplayName;

    Class type;

    public Class getType() {
        return type;
    }

    public ScripColumn(String columnName, int columnIndex,
            String columnDisplayName) {
        super();
        this.columnName = columnName;
        this.columnIndex = columnIndex;
        this.columnDisplayName = columnDisplayName;
        this.type = FinanTechConstant.ALL_COLUMN_TYPES[columnIndex];
    }

    public String getColumnName() {
        return columnName;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public String getColumnDisplayName() {
        return columnDisplayName;
    }

}