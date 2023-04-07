package thud.chiasehinhanh.dulieu;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "MyDatabase.db";
    private static final int DB_VERSION = 1;

    // Define table NguoiDung
    public static final String TABLE_NGUOIDUNG = "NguoiDung";
    public static final String ND_ID = "id";
    public static final String ND_TENDANGNHAP = "tendangnhap";
    public static final String ND_HOTEN = "hoten";
    public static final String ND_MATKHAU = "matkhau";
    private static final String CREATE_TABLE_NGUOIDUNG
            = "CREATE TABLE " + TABLE_NGUOIDUNG + "("
            + ND_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ND_TENDANGNHAP + " TEXT NOT NULL UNIQUE, "
            + ND_HOTEN + " TEXT NOT NULL UNIQUE, "
            + ND_MATKHAU + " TEXT NOT NULL);";

    // Define table BaiViet
    public static final String TABLE_BAIVIET = "BaiViet";
    public static final String BV_ID = "id";
    public static final String BV_NOIDUNG = "noidung";
    public static final String BV_IMAGE = "image";
    public static final String BV_ID_NGUOIDUNG = "id_nguoidung";
    private static final String CREATE_TABLE_BAIVIET
            = "CREATE TABLE " + TABLE_BAIVIET + "("
            + BV_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + BV_NOIDUNG + " TEXT NOT NULL, "
            + BV_IMAGE + " TEXT NOT NULL, "
            + BV_ID_NGUOIDUNG + " INTEGER NOT NULL, "
            + "FOREIGN KEY(" + BV_ID_NGUOIDUNG + ") REFERENCES " + TABLE_NGUOIDUNG + "(" + ND_ID + "));";

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create table NguoiDung
        db.execSQL(CREATE_TABLE_NGUOIDUNG);
        // Create table BaiViet
        db.execSQL(CREATE_TABLE_BAIVIET);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BAIVIET);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NGUOIDUNG);
        onCreate(db);
    }
}

