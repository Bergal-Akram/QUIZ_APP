package com.example.quiz_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database_Quiz extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "quizApp.db"; // اسم قاعدة البيانات
        private static final int DATABASE_VERSION = 1; // رقم الإصدار
        public static final String TABLE_USERS = "users";
        public static final String COLUMN_USERNAME = "username";
        public static final String COLUMN_PASSWORD = "password";
        public Database_Quiz(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }


    // إنشاء الجدول أول مرة
        @Override
        public void onCreate(SQLiteDatabase db) {
            String createTable = "CREATE TABLE " + TABLE_USERS + " (" +
                    COLUMN_USERNAME + " TEXT PRIMARY KEY, " +
                    COLUMN_PASSWORD + " TEXT)";
            db.execSQL(createTable);
        }

        // عندما نغير إصدار القاعدة، يتم إعادة إنشاء الجداول
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
            onCreate(db);
        }

        // إضافة مستخدم جديد
        public boolean addUser(String username, String password) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_USERNAME, username);
            values.put(COLUMN_PASSWORD, password);

            long result = db.insert(TABLE_USERS, null, values);
            db.close();
            return result != -1; // إذا كانت العملية ناجحة ترجع true
        }

        // التحقق من وجود مستخدم
        public boolean checkUser(String username, String password) {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username=? AND password=?", new String[]{username, password});
            boolean exists = (cursor.getCount() > 0);
            cursor.close();
            db.close();
            return exists;
        }
        public boolean checkUsername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_USERNAME + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{username});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return exists;
    }

}

