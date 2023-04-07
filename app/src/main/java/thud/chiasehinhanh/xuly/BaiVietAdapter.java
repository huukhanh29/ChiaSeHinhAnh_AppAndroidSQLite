package thud.chiasehinhanh.xuly;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import thud.chiasehinhanh.dulieu.BaiViet;
import thud.chiasehinhanh.dulieu.DbHelper;

public class BaiVietAdapter {
    private DbHelper myDbHelper;
    private SQLiteDatabase db;
    private String[] allColumns = { DbHelper.BV_ID,
            DbHelper.BV_NOIDUNG,
            DbHelper.BV_IMAGE,
            DbHelper.BV_ID_NGUOIDUNG };

    public BaiVietAdapter(Context context) {
        myDbHelper = new DbHelper(context);
        db = myDbHelper.getWritableDatabase();
    }

    public long insertBaiViet(BaiViet baiviet) {
        db = myDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbHelper.BV_NOIDUNG, baiviet.getNoiDung());
        values.put(DbHelper.BV_IMAGE, baiviet.getImage());
        values.put(DbHelper.BV_ID_NGUOIDUNG, baiviet.getIdNguoiDung());
        return db.insert(DbHelper.TABLE_BAIVIET, null, values);
    }

    public int updateBaiViet(BaiViet baiviet) {
        db = myDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbHelper.BV_NOIDUNG, baiviet.getNoiDung());
        values.put(DbHelper.BV_IMAGE, baiviet.getImage());
        values.put(DbHelper.BV_ID_NGUOIDUNG, baiviet.getIdNguoiDung());
        return db.update(DbHelper.TABLE_BAIVIET, values, DbHelper.BV_ID + " = ?",
                new String[] { String.valueOf(baiviet.getId()) });
    }

    public BaiViet getBaiVietById(int id) {
        db = myDbHelper.getReadableDatabase();
        Cursor cursor = db.query(DbHelper.TABLE_BAIVIET,
                allColumns, DbHelper.BV_ID + " = ?",
                new String[] { String.valueOf(id) }, null, null, null);
        BaiViet baiviet = null;
        if (cursor.moveToFirst()) {
            baiviet = cursorToBaiViet(cursor);
        }
        cursor.close();
        return baiviet;
    }

    public void deleteBaiVietById(int id) {
        db = myDbHelper.getWritableDatabase();
        db.delete(DbHelper.TABLE_BAIVIET, DbHelper.BV_ID + " = ?",
                new String[] { String.valueOf(id) });
    }


    public List<BaiViet> getAllBaiViet() {
        db = myDbHelper.getReadableDatabase();
        List<BaiViet> baivietList = new ArrayList<BaiViet>();
        Cursor cursor = db.query(DbHelper.TABLE_BAIVIET,
                allColumns, null, null, null, null, "id DESC");

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            BaiViet baiviet = cursorToBaiViet(cursor);
            baivietList.add(baiviet);
            cursor.moveToNext();
        }
        cursor.close();
        return baivietList;
    }
    public List<BaiViet> getBaiVietByNguoiDung(int idNguoiDung) {
        db = myDbHelper.getReadableDatabase();
        List<BaiViet> baivietList = new ArrayList<BaiViet>();
        Cursor cursor = db.query(DbHelper.TABLE_BAIVIET,
                allColumns, DbHelper.BV_ID_NGUOIDUNG + " = ?",
                new String[] { String.valueOf(idNguoiDung) }, null, null, "id DESC");

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            BaiViet baiviet = cursorToBaiViet(cursor);
            baivietList.add(baiviet);
            cursor.moveToNext();
        }
        cursor.close();
        return baivietList;
    }


    private BaiViet cursorToBaiViet(Cursor cursor) {
        BaiViet baiviet = new BaiViet();
        baiviet.setId(cursor.getInt(0));
        baiviet.setNoiDung(cursor.getString(1));
        baiviet.setImage(cursor.getString(2));
        baiviet.setIdNguoiDung(cursor.getInt(3));
        return baiviet;
    }
}

