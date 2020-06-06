package com.va181.tresna;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;

public class TampilActivity extends AppCompatActivity {

    private ImageView imgBuku;
    private TextView tvJudul, tvTanggal, tvPenulis, tvIsiBuku;
    private String linkBuku;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil);

        imgBuku = findViewById(R.id.iv_buku);
        tvJudul = findViewById(R.id.tv_judul);
        tvTanggal = findViewById(R.id.tv_tanggal);
        tvPenulis = findViewById(R.id.tv_penulis);
        tvIsiBuku = findViewById(R.id.tv_isi_buku);

        Intent terimaData = getIntent();
        tvJudul.setText(terimaData.getStringExtra("JUDUL"));
        tvTanggal.setText(terimaData.getStringExtra("TANGGAL"));
        tvPenulis.setText(terimaData.getStringExtra("PENULIS"));
        tvPenulis.setText(terimaData.getStringExtra("PENULIS"));
        tvIsiBuku.setText(terimaData.getStringExtra("ISI_BUKU"));
        String imgLocation = terimaData.getStringExtra("GAMBAR");

        try {
            File file = new File(imgLocation);
            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
            imgBuku.setImageBitmap(bitmap);
            imgBuku.setContentDescription(imgLocation);
        } catch (FileNotFoundException er) {
            er.printStackTrace();
            Toast.makeText(this, "Gagal Mangambil Gambar Dari Media Penyimpanan", Toast.LENGTH_SHORT).show();
        }


        linkBuku = terimaData.getStringExtra("LINK");
    }
}
