package com.va181.tresna;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class DatabaseHandler extends SQLiteOpenHelper {

    private final static int DATABASE_VERSION = 2;
    private final static String DATABASE_NAME = "db_perpustakaan";
    private final static String TABLE_BUKU = "t_buku";
    private final static String KEY_ID_BUKU = "ID_buku";
    private final static String KEY_JUDUL = "Judul";
    private final static String KEY_TGL = "Tanggal";
    private final static String KEY_GAMBAR = "Gambar";
    private final static String KEY_PENULIS = "Penulis";
    private final static String KEY_ISI_BUKU = "Isi_Buku";
    private final static String KEY_LINK = "Link";
    private SimpleDateFormat sdFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm", Locale.getDefault());
    private Context context;

    public DatabaseHandler(Context ctx) {
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = ctx;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_BUKU = "CREATE TABLE " + TABLE_BUKU
                + "(" + KEY_ID_BUKU + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_JUDUL + " TEXT, " + KEY_TGL + " DATE, "
                + KEY_GAMBAR + " TEXT, " + " TEXT, "
                + KEY_PENULIS + " TEXT, " + KEY_ISI_BUKU + " TEXT, "
                + KEY_LINK + " TEXT);";

        db.execSQL(CREATE_TABLE_BUKU);
        inisialisasiBukuAwal(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_BUKU;
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public void tambahBuku(Buku dataBuku) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_JUDUL, dataBuku.getjudul());
        cv.put(KEY_TGL, sdFormat.format(dataBuku.gettanggal()));
        cv.put(KEY_GAMBAR, dataBuku.getgambar());
        cv.put(KEY_PENULIS, dataBuku.getpenulis());
        cv.put(KEY_ISI_BUKU, dataBuku.getisiBuku());
        cv.put(KEY_LINK, dataBuku.getLink());

        db.insert(TABLE_BUKU, null, cv);
        db.close();
    }

    public void tambahBuku(Buku dataBuku, SQLiteDatabase db) {
        ContentValues cv = new ContentValues();

        cv.put(KEY_JUDUL, dataBuku.getjudul());
        cv.put(KEY_TGL, sdFormat.format(dataBuku.gettanggal()));
        cv.put(KEY_GAMBAR, dataBuku.getgambar());
        cv.put(KEY_PENULIS, dataBuku.getpenulis());
        cv.put(KEY_ISI_BUKU, dataBuku.getisiBuku());
        cv.put(KEY_LINK, dataBuku.getLink());

        db.insert(TABLE_BUKU, null, cv);
    }

    public void editBuku(Buku dataBuku) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_JUDUL, dataBuku.getjudul());
        cv.put(KEY_TGL, sdFormat.format(dataBuku.gettanggal()));
        cv.put(KEY_GAMBAR, dataBuku.getgambar());
        cv.put(KEY_PENULIS, dataBuku.getpenulis());
        cv.put(KEY_ISI_BUKU, dataBuku.getisiBuku());
        cv.put(KEY_LINK, dataBuku.getLink());

        db.update(TABLE_BUKU, cv, KEY_ID_BUKU + "=?", new String[]{String.valueOf(dataBuku.getidBuku())});

        db.close();
    }

    public void hapusBuku(int idBuku ) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_BUKU, KEY_ID_BUKU + "=?", new String[]{String.valueOf(idBuku)});
        db.close();
    }

    public ArrayList<Buku>getAllBuku() {
        ArrayList<Buku> dataBuku = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_BUKU;
        SQLiteDatabase db = getReadableDatabase();
        Cursor csr = db.rawQuery(query, null);
        if(csr.moveToFirst()) {
            do {
                Date tempDate = new Date();
                try {
                    tempDate = sdFormat.parse(csr.getString(2));
                } catch (ParseException er) {
                    er.printStackTrace();
                }

                Buku tempBuku = new Buku(
                        csr.getInt(0),
                        csr.getString(1),
                        tempDate,
                        csr.getString(3),
                        csr.getString(4),
                        csr.getString(6),
                        csr.getString(7)
                );

                dataBuku.add(tempBuku);
            }while (csr.moveToNext());
        }

        return dataBuku;
    }

    private String storeIamgeFile(int id){
        String location;
        Bitmap image = BitmapFactory.decodeResource(context.getResources(), id);
        location = InputActivity.saveImageToInternalStorage(image, context);
        return location;
    }

    private void inisialisasiBukuAwal(SQLiteDatabase db) {
        int idBuku = 0;
        Date tempDate = new Date();

        //Menambah data buku ke-1
        try {
            tempDate = sdFormat.parse("13/03/2020 06:22");
        }catch (ParseException er) {
            er.printStackTrace();
        }

        Buku buku1 = new Buku(
                idBuku, "Absolute Beginner's Guide to C",
                tempDate,
                storeIamgeFile(R.drawable.buku1),
                "AGreg Perry",
                "Book Description\n" +
                        "                        \"For beginning programmers, this updated edition answers all C programming questions.\n" +
                        "                        \"This bestseller talks to readers at their level, explaining every aspect of how to get started and learn the C language quickly.\n" +
                        "                        \" Readers also find out where to learn more about C. This book includes tear-out reference card of C functions and statements, a hierarchy chart, and other valuable information.\n" +
                        "                        \"It uses special icons, notes, clues, warnings, and rewards to make understanding easier. And the clear and friendly style presumes no programming knowledge.\"\n",
                "https://www.amazon.com/Absolute-Beginners-Guide-Portable-Documents-ebook-dp-B003J2RFEK/dp/B003J2RFEK/ref=mt_kindle?_encoding=UTF8&me=&qid="

        );

        tambahBuku(buku1, db);

        }
    }

