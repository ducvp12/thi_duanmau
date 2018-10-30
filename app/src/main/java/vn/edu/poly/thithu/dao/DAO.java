package vn.edu.poly.thithu.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import vn.edu.poly.thithu.Constant;
import vn.edu.poly.thithu.database.DatabaseHelper;
import vn.edu.poly.thithu.model.Model;

    public class DAO implements Constant {
        private final DatabaseHelper databaseHelper;
        public DAO(DatabaseHelper databaseHelper){ this.databaseHelper= databaseHelper; }


        public void insert(Model a){
           SQLiteDatabase sqLiteDatabase=databaseHelper.getWritableDatabase();

            ContentValues values=new ContentValues();
            values.put(COLUMN_1, a.getA());
            values.put(COLUMN_2, a.getB());
            values.put(COLUMN_3, a.getC());

            // tao cau lenh insert

            long id=sqLiteDatabase.insert(TABLE_NAME, null,values);

            Log.e("insert","insert : "+ id);

            sqLiteDatabase.close();
        }

        public Model getXbya(String y){
            Model x=null;
            SQLiteDatabase sqLiteDatabase=databaseHelper.getWritableDatabase();

            Cursor cursor=sqLiteDatabase.query(TABLE_NAME,new String[]{COLUMN_1,COLUMN_2,COLUMN_3}, COLUMN_1 +"?", new String[]{y},null,null,null);
            if (cursor!= null &&cursor.moveToFirst()){
                String a=cursor.getString(cursor.getColumnIndex(COLUMN_1));
                String b=cursor.getString(cursor.getColumnIndex(COLUMN_2));
                String c=cursor.getString(cursor.getColumnIndex(COLUMN_3));

                x=new Model();
                x.setA(a);
                x.setB(b);
                x.setC(c);
            }
            return x;
        }

        public List<Model> getAll(){
            List<Model> xList=new ArrayList<>();

            String SELECT_ALL= "SELECT * FROM "+TABLE_NAME;

            Log.e("getAll", SELECT_ALL);

            SQLiteDatabase sqLiteDatabase=databaseHelper.getWritableDatabase();

            Cursor cursor=sqLiteDatabase.rawQuery(SELECT_ALL, null);

            cursor.moveToFirst();

            do {
                String a=cursor.getString(cursor.getColumnIndex(COLUMN_1));
                String b=cursor.getString(cursor.getColumnIndex(COLUMN_2));
                String c=cursor.getString(cursor.getColumnIndex(COLUMN_3));

                Model  x=new Model();
                x.setA(a);
                x.setB(b);
                x.setC(c);

                xList.add(x);
            } while (cursor.moveToNext());
            cursor.close();

            sqLiteDatabase.close();

            return  xList;
        }


        public void delete(String id){
            SQLiteDatabase sqLiteDatabase=databaseHelper.getWritableDatabase();

            sqLiteDatabase.delete(TABLE_NAME,COLUMN_1 +"=?", new String[]{id});
        }

        public void update(Model x){

            SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(COLUMN_2,x.getB());
            values.put(COLUMN_3,x.getC());

            sqLiteDatabase.update(TABLE_NAME, values,COLUMN_1 +"=?", new String[]{ x.getA()});
        }

        }

