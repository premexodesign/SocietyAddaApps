package com.example.navigation_drawer.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="SocietyAdda.db";
    private static final String TABLE_NAME2 ="tbl_visitor_list";
    private static final String TABLE_NAME1="tbl_visitor_info_list";

    private static final String[] tblvisitorlistcolumn={"visitorid","referencecode","vehicleno","reasontovisit"
            ,"relationwithperson","wing","flat","checkindate","checkintime","checkoutdate","checkouttime","checkinimg"};
    private static final String[] tblvisitorinfolistcolumn={"name","mobileno","status"};

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Table_Visitor_info_list="CREATE TABLE "+TABLE_NAME1+" ( Id	INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name	TEXT NOT NULL, " +
                "mobileno	NUMERIC(15) NOT NULL, " +
                "status INTEGER(1) NOT NULL)";
        String Table_Visitor_list="CREATE TABLE "+ TABLE_NAME2 +" (Id	INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "visitorid INTEGER NOT NULL, " +
                "referencecode INTEGER NOT NULL, " +
                "vehicleno TEXT, " +
                "reasontovisit TEXT, " +
                "relationwithperson TEXT, " +
                "wing TEXT(3) NOT NULL, " +
                "flat	INTEGER NOT NULL, " +
                "checkindate TEXT, " +
                "checkintime TEXT, " +
                "checkoutdate TEXT, " +
                "checkouttime TEXT," +
                "checkinimg BLOB,"+
                " FOREIGN KEY (visitorid) REFERENCES "+TABLE_NAME1+"(Id))";
        db.execSQL(Table_Visitor_info_list);
        db.execSQL(Table_Visitor_list);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        onCreate(db);
    }

    public boolean RegisterVisitor(String name,long mobileno,int status){

        SQLiteDatabase db=this.getWritableDatabase();
        SQLiteDatabase dbget=this.getReadableDatabase();

        String quary="Select * from "+TABLE_NAME1+" where mobileno="+mobileno;
        Cursor cursor=dbget.rawQuery(quary,null);

        if (!(cursor.moveToFirst() && cursor.getCount() > 0)){
            ContentValues cv=new ContentValues();
            cv.put(tblvisitorinfolistcolumn[0],name);
            cv.put(tblvisitorinfolistcolumn[1],mobileno);
            cv.put(tblvisitorinfolistcolumn[2],status);
            long result=db.insert(TABLE_NAME1,null,cv);
            db.close();
            if (result==-1){
                cursor.close();
                return false;
            }else{
                cursor.close();
                return true;
            }
        }else{
            int visitorid=cursor.getInt(0);
            ContentValues contentValues=new ContentValues();
            contentValues.put("status",1);
            int affected=db.update(TABLE_NAME1,contentValues,"id="+visitorid,null);
            if (affected>0) {
                cursor.close();
                return true;
            }else{
                cursor.close();
                return false;
            }
        }
    }

    public boolean insertvisitor(long mobileno,int referncecode,String vehicleno,String reason,String relation,
                                 String wing,int flat,String date,String time,byte[] img){
        SQLiteDatabase dbget=this.getReadableDatabase();
        SQLiteDatabase dbset=this.getWritableDatabase();
        int visitorid;
        String quary="Select Id from "+TABLE_NAME1+" where mobileno="+mobileno;
        Cursor cursor=dbget.rawQuery(quary,null);
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            visitorid=cursor.getInt(0);
        }else{
            return false;
        }
        ContentValues cv=new ContentValues();
        cv.put(tblvisitorlistcolumn[0],visitorid);
        cv.put(tblvisitorlistcolumn[1],referncecode);
        cv.put(tblvisitorlistcolumn[2],vehicleno);
        cv.put(tblvisitorlistcolumn[3],reason);
        cv.put(tblvisitorlistcolumn[4],relation);
        cv.put(tblvisitorlistcolumn[5],wing);
        cv.put(tblvisitorlistcolumn[6],flat);
        cv.put(tblvisitorlistcolumn[7],date);
        cv.put(tblvisitorlistcolumn[8],time);
        cv.put(tblvisitorlistcolumn[11],img);

        long result=dbset.insert(TABLE_NAME2,null,cv);
        if (result==-1){
            return false;
        }else{
            return true;
        }
    }

    public Cursor getVisitorData(){
        SQLiteDatabase db=getReadableDatabase();
        String query="Select * from "+TABLE_NAME1+" where status="+1;
        Cursor cursor=db.rawQuery(query,null);
        return cursor;

    }

    public void deletealldataofdatabase(){
//        SQLiteDatabase db=getWritableDatabase();
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
//        onCreate(db);
//        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
//        String str = sdf.format(new Date());
//        Log.d("CurrentTime",str);
    }

    public boolean checkout(int vid){
        SQLiteDatabase dbget=this.getReadableDatabase();
        SQLiteDatabase dbset=this.getWritableDatabase();
        int visitortblid;
        String selectquery="Select MAX(id) from "+TABLE_NAME2+" where visitorid="+vid;
        Cursor cursor=dbget.rawQuery(selectquery,null);
        if (cursor.getCount()>0 && cursor.moveToFirst()){
            visitortblid=cursor.getInt(0);
            Date c = Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            String formattedDate = df.format(c);

            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            String currenttime = sdf.format(new Date());

            ContentValues cv=new ContentValues();
            cv.put("checkoutdate",formattedDate);
            cv.put("checkouttime",currenttime);
            int affecteddata=dbset.update(TABLE_NAME2,cv,"id="+visitortblid,null);
            if (affecteddata>0){
                ContentValues cv1=new ContentValues();
                cv1.put("status",0);
                int affecteddata1=dbset.update(TABLE_NAME1,cv1,"id="+vid,null);
                if (affecteddata1>0)
                    return true;
                else
                    return false;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    public Cursor getallcheckoutdata(){
        Cursor cursor;
        SQLiteDatabase db=getReadableDatabase();
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String currentDate = df.format(c);
        Log.d("DateTimeCheck",currentDate);
        String query="Select vd.name,vd.mobileno,vl.id,vl.checkoutdate,vl.checkouttime from tbl_visitor_list AS vl " +
                "INNER JOIN  tbl_visitor_info_list AS vd ON vd.id=vl.visitorid WHERE vl.checkoutdate='"+currentDate+"'";
        cursor=db.rawQuery(query,null);
        return cursor;
    }

    public Cursor getDatabyReferenceCode(int code){
        Cursor cursor;
        SQLiteDatabase db=getReadableDatabase();
        String query="Select vd.name,vd.mobileno,vl.id,vl.reasontovisit,vl.relationwithperson,vl.wing,vl.flat from " +
                "tbl_visitor_list AS vl INNER JOIN  tbl_visitor_info_list AS vd ON vd.id=vl.visitorid " +
                "WHERE vl.referencecode="+code;
        cursor=db.rawQuery(query,null);

        return  cursor;
    }

    public Cursor getDataByVisitorListId(int id){
        SQLiteDatabase db=getReadableDatabase();
        String query="Select vd.name,vd.mobileno,vl.referencecode,vl.vehicleno,vl.reasontovisit,vl.relationwithperson," +
                "vl.wing,vl.flat,vl.checkindate,vl.checkintime,vl.checkoutdate,vl.checkouttime,vl.checkinimg from " +
                "tbl_visitor_list AS vl INNER JOIN  tbl_visitor_info_list AS vd ON vd.id=vl.visitorid " +
                "WHERE vl.id="+id;
        Cursor cursor=db.rawQuery(query,null);
        return cursor;
    }

    public Cursor getCheckinPersonData(int vid){
        SQLiteDatabase db=getReadableDatabase();
        String query="Select vd.name,vd.mobileno,vl.referencecode,vl.vehicleno,vl.reasontovisit,vl.relationwithperson," +
                "vl.wing,vl.flat,vl.checkindate,vl.checkintime,vl.checkinimg from " +
                "tbl_visitor_list AS vl INNER JOIN  tbl_visitor_info_list AS vd ON vd.id=vl.visitorid " +
                "WHERE vl.id=(Select max(id) from tbl_visitor_list where visitorid="+vid+")";

        Cursor cursor=db.rawQuery(query,null);
        return cursor;
    }

}
