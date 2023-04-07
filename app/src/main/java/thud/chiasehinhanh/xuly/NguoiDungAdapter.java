package thud.chiasehinhanh.xuly;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import thud.chiasehinhanh.dulieu.DbHelper;
import thud.chiasehinhanh.dulieu.NguoiDung;


public class NguoiDungAdapter {
    private DbHelper myDbHelper;
    private SQLiteDatabase db;
    private String[] allColumns = { DbHelper.ND_TENDANGNHAP,
            DbHelper.ND_MATKHAU };
    public NguoiDungAdapter(Context context) {
        myDbHelper = new DbHelper(context);
        db = myDbHelper.getWritableDatabase();
    }
    public int getIdNguoiDung(String tenDangNhap) {
        String query = "SELECT " + DbHelper.ND_ID + " FROM " + DbHelper.TABLE_NGUOIDUNG +
                " WHERE " + DbHelper.ND_TENDANGNHAP + " = ?";
        Cursor cursor = db.rawQuery(query, new String[] {tenDangNhap});
        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(0);
            cursor.close();
            return id;
        }
        return -1;
    }
    public String getTenNguoiDungById(int id) {
        String query = "SELECT " + DbHelper.ND_HOTEN + " FROM " + DbHelper.TABLE_NGUOIDUNG +
                " WHERE " + DbHelper.ND_ID + " = ?";
        Cursor cursor = db.rawQuery(query, new String[] {String.valueOf(id)});
        if (cursor != null && cursor.moveToFirst()) {
            String tenNguoiDung = cursor.getString(0);
            cursor.close();
            return tenNguoiDung;
        }
        return null;
    }



    public long insertNguoiDung(NguoiDung nguoidung) {
        db = myDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbHelper.ND_TENDANGNHAP, nguoidung.getTenDangNhap());
        values.put(DbHelper.ND_HOTEN, nguoidung.getHoTen());
        values.put(DbHelper.ND_MATKHAU, nguoidung.getMatKhau());
        return db.insert(DbHelper.TABLE_NGUOIDUNG,
                null, values);
    }
    public boolean kiemTraDangKy(String tenDangNhap) {
        db = myDbHelper.getReadableDatabase();
        Cursor cursor = db.query(DbHelper.TABLE_NGUOIDUNG,
                allColumns, DbHelper.ND_TENDANGNHAP + " = '" + tenDangNhap + "'",
                null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.close();
            return true;
        }
        return false;
    }
    public boolean kiemTraDangNhap(String tenDangNhap, String matKhau) {
        db = myDbHelper.getReadableDatabase();
        Cursor cursor = db.query(DbHelper.TABLE_NGUOIDUNG,
                allColumns, DbHelper.ND_TENDANGNHAP + " = '" + tenDangNhap + "' AND "
                        + DbHelper.ND_MATKHAU + " = '" + matKhau + "'",
                null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.close();
            return true;
        }
        return false;
    }

}

