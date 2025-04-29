package com.example.quiz_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database_Quiz extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "quizApp.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_USERS = "users";
    public static final String COLUMN_USER_ID = "id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";


    public static final String TABLE_QUESTIONS = "questions";
    public static final String COLUMN_QUESTION_ID = "id";
    public static final String COLUMN_QUESTION_TEXT = "question_text";
    public static final String COLUMN_OPTION_A = "option_a";
    public static final String COLUMN_OPTION_B = "option_b";
    public static final String COLUMN_OPTION_C = "option_c";
    public static final String COLUMN_OPTION_D = "option_d";
    public static final String COLUMN_CORRECT_ANSWER = "correct_answer";



    public static final String TABLE_RESULTS = "results";
    public static final String COLUMN_RESULT_ID = "id";
    public static final String COLUMN_USER_REF_ID = "user_id";
    public static final String COLUMN_SCORE = "score";
    public static final String COLUMN_DATE = "date";

    public Database_Quiz(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUsersTable = "CREATE TABLE " + TABLE_USERS + " (" +
                COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USERNAME + " TEXT UNIQUE, " +
                COLUMN_PASSWORD + " TEXT)";
        db.execSQL(createUsersTable);


        String createQuestionsTable = "CREATE TABLE " + TABLE_QUESTIONS + " (" +
                COLUMN_QUESTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_QUESTION_TEXT + " TEXT, " +
                COLUMN_OPTION_A + " TEXT, " +
                COLUMN_OPTION_B + " TEXT, " +
                COLUMN_OPTION_C + " TEXT, " +
                COLUMN_OPTION_D + " TEXT, " +
                COLUMN_CORRECT_ANSWER + " TEXT)";
        db.execSQL(createQuestionsTable);


        String createResultsTable = "CREATE TABLE " + TABLE_RESULTS + " (" +
                COLUMN_RESULT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USER_REF_ID + " INTEGER, " +
                COLUMN_SCORE + " INTEGER, " +
                COLUMN_DATE + " TEXT, " +
                "FOREIGN KEY(" + COLUMN_USER_REF_ID + ") REFERENCES " + TABLE_USERS + "(" + COLUMN_USER_ID + "))";
        db.execSQL(createResultsTable);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESULTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public boolean addUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_PASSWORD, password);
        long result = db.insert(TABLE_USERS, null, values);
        db.close();
        return result != -1;
    }


    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE " +
                COLUMN_USERNAME + "=? AND " + COLUMN_PASSWORD + "=?", new String[]{username, password});
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        db.close();
        return exists;
    }

    public boolean checkUsername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_USERNAME + "=?", new String[]{username});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return exists;
    }

    public boolean addResult(int userId, int score, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_REF_ID, userId);
        values.put(COLUMN_SCORE, score);
        values.put(COLUMN_DATE, date);
        long result = db.insert(TABLE_RESULTS, null, values);
        db.close();
        return result != -1;
    }

    public boolean addQuestion(String question, String a, String b, String c, String d, String correct) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_QUESTION_TEXT, question);
        values.put(COLUMN_OPTION_A, a);
        values.put(COLUMN_OPTION_B, b);
        values.put(COLUMN_OPTION_C, c);
        values.put(COLUMN_OPTION_D, d);
        values.put(COLUMN_CORRECT_ANSWER, correct);
        long result = db.insert(TABLE_QUESTIONS, null, values);
        db.close();
        return result != -1;
    }

    public Cursor getAllQuestions() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM questions", null);
    }

}


