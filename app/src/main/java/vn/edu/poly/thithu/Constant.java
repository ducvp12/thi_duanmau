package vn.edu.poly.thithu;

public interface Constant {
    String TABLE_NAME = "TableName";

    String COLUMN_1 = "A";
    String COLUMN_2 = "B";
    String COLUMN_3 = "C";
    // Cau lenh tao bang
   String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
            COLUMN_1 + " TEXT PRIMARY KEY," +
            COLUMN_2 + " TEXT," +
            COLUMN_3 + " TEXT" +
            ")";

}
