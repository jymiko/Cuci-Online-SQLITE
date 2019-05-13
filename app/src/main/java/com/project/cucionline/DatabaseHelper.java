package com.project.cucionline;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "transaksi.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_LABELS = "user_table";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_TRANSAKSI_DATABASE =
                "CREATE TABLE " + DatabaseContact.TransaksiEntry.TABLE_NAME+" (" +
                        DatabaseContact.TransaksiEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                        DatabaseContact.TransaksiEntry.JAM_BOOKING + " TEXT NOT NULL,"+
                        DatabaseContact.TransaksiEntry.MOTOR+ "TEXT NOT NULL"+
                        ")";
        db.execSQL(SQL_CREATE_TRANSAKSI_DATABASE);

        final String SQL_CREATE_LOGIN_TABLE =
                "CREATE TABLE " + DatabaseLogin.UserEntry.TABLE_NAME+" (" +
                        DatabaseLogin.UserEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                        DatabaseLogin.UserEntry.COLUMN_NAMA_USER + " TEXT NOT NULL,"+
                        DatabaseLogin.UserEntry.COLUMN_ALAMAT_USER+ " TEXT NOT NULL,"+
                        DatabaseLogin.UserEntry.COLUMN_NOTELP_USER+ " TEXT NOT NULL,"+
                        DatabaseLogin.UserEntry.COLUMN_EMAIL_USER+ " TEXT NOT NULL,"+
                        DatabaseLogin.UserEntry.COLUMN_PASSWORD_USER+ " TEXT NOT NULL,"+
                        DatabaseLogin.UserEntry.COLUMN_MOTOR+ " TEXT NOT NULL"+
                        ")";
        db.execSQL(SQL_CREATE_LOGIN_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if( newVersion > oldVersion){
            db.execSQL(" DROP TABLE IF EXISTS " +
                    DatabaseContact.TransaksiEntry.TABLE_NAME);
            onCreate(db);
        }
    }

    public boolean SimpanData(String Email,String Password){

        String[] Coloumn={DatabaseLogin.UserEntry._ID};
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = DatabaseLogin.UserEntry.COLUMN_EMAIL_USER+" = ?"+ " AND "
                +DatabaseLogin.UserEntry.COLUMN_PASSWORD_USER+" = ?";
        String[]selectionargs={Email,Password};
        Cursor cursor = db.query(DatabaseLogin.UserEntry.TABLE_NAME,
                Coloumn,
                selection,
                selectionargs,
                null,
                null,
                null);
        int cursorcount = cursor.getCount();
        cursor.close();
        db.close();
        if(cursorcount > 0 ){
            return true;
        }
        return false;

    }

    public Cursor LihatData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor lihatdata = db.rawQuery("select email_user,password_user from "+DatabaseLogin.UserEntry.TABLE_NAME,null);
        return lihatdata;
    }

    public void deleteData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+DatabaseLogin.UserEntry.TABLE_NAME);
    }

    public List<String> getAllLabels(){
        List<String> labels = new ArrayList<String>();

        String selectQuery = "SELECT "+ DatabaseLogin.UserEntry.COLUMN_MOTOR+ " FROM "+ TABLE_LABELS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                labels.add(cursor.getString(0));
            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return labels;
    }
}
