package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MyDB extends SQLiteOpenHelper {
    private static final String TABLE_NAME="MYDB";
    private static final String MA="MA";
    private static final String NAME="NAME";
    private static final String SOPHONG="SOPHONG";
    private static final String DONGIA="DONGIA";
    private static final String SONGAY="SONGAY";

    public MyDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreate="Create table if not exists "+TABLE_NAME+" ("
                +MA+ " INTEGER Primary key AUTOINCREMENT, "
                +NAME+" Text, "
                +SOPHONG+" INTERGER, "
                +DONGIA+" INTERGER, "
                +SONGAY+" INTERGER)";
        db.execSQL(sqlCreate);

    }
    public List<HoaDon> getAll(){
        List<HoaDon> hoaDons=new ArrayList<>();
        String sql="Select * from "+TABLE_NAME;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(sql,null);
        if(cursor!=null){
            while (cursor.moveToNext()){
                HoaDon c=new HoaDon();
                c.setMa(cursor.getInt(0));
                c.setTen(cursor.getString(1));
                c.setSoPhong(cursor.getInt(2));
                c.setDonGia(cursor.getInt(3));
                c.setSoNgay(cursor.getInt(4));

                hoaDons.add(c);
            }


        }
        return hoaDons;
    }




    public Long create(HoaDon h){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(NAME,h.getTen());
        contentValues.put(SOPHONG,h.getSoPhong());
        contentValues.put(DONGIA,h.getDonGia());
        contentValues.put(SONGAY,h.getSoNgay());
        Long id= db.insert(TABLE_NAME,null,contentValues);
        db.close();
        return id;
    }
    /*public int updateTaxi(Taxi taxi) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SOXE, taxi.getSoXe());
        values.put(QUANGDUONG, taxi.getQuangDuong());
        values.put(GIA, taxi.getGia());
        values.put(KHUYENMAI, taxi.getKhuyenMai());


        // Updating row
        int result = db.update(TABLE_NAME, values, MA + " = ?", new String[]{String.valueOf(taxi.getMa())});
        db.close();
        return result;
    }*/

    public void delete(int ma) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, MA + " = ?", new String[]{ma+""});
        db.close();
    }

    public void initData(){
        for (int i=0;i<6;i++){
            HoaDon hoaDon=new HoaDon(10+i,300*(i+1),i+2,"User"+i);
            create(hoaDon);
        }




    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists "+TABLE_NAME);
        onCreate(db);

    }
}
