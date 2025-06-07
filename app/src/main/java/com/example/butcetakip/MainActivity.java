package com.example.butcetakip;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText editTutar, editKategori, editAra, editTarih;
    Button btnGelir, btnGider, btnTarihSec, btnAnaliz;
    TextView textBakiye;
    Spinner spinnerKategori;

    ArrayList<String> kayitlar = new ArrayList<>();
    ArrayList<String> kategoriler = new ArrayList<>();
    ArrayAdapter<String> kategoriAdapter;

    double toplamGelir = 0;
    double toplamGider = 0;

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(this);
        db = FirebaseFirestore.getInstance();


        editTutar = findViewById(R.id.editTutar);
        editKategori = findViewById(R.id.editKategori);
        editAra = findViewById(R.id.editAra);
        editTarih = findViewById(R.id.editTarih);
        btnGelir = findViewById(R.id.btnGelir);
        btnGider = findViewById(R.id.btnGider);
        btnTarihSec = findViewById(R.id.btnTarihSec);
        btnAnaliz = findViewById(R.id.btnAnaliz);
        textBakiye = findViewById(R.id.textBakiye);
        spinnerKategori = findViewById(R.id.spinnerKategori);


        kategoriler.add("Tümü");
        kategoriler.add("Gıda");
        kategoriler.add("Ulaşım");
        kategoriler.add("Eğlence");

        kategoriAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, kategoriler);
        kategoriAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerKategori.setAdapter(kategoriAdapter);

        spinnerKategori.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filtrele();
            }

            public void onNothingSelected(AdapterView<?> parent) {}
        });

        editAra.addTextChangedListener(new SimpleWatcher(() -> filtrele()));
        editTarih.addTextChangedListener(new SimpleWatcher(() -> filtrele()));

        btnGelir.setOnClickListener(v -> gelirEkle());
        btnGider.setOnClickListener(v -> giderEkle());

        btnTarihSec.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            new DatePickerDialog(this, (view, y, m, d) -> {
                String secilen = y + "-" + String.format("%02d", (m + 1));
                editTarih.setText(secilen);
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
        });

        btnAnaliz.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AnalizActivity.class);
            intent.putStringArrayListExtra("kayitlar", kayitlar);
            startActivity(intent);
        });
    }

    void gelirEkle() {
        String tutarStr = editTutar.getText().toString();
        String kategori = editKategori.getText().toString();
        String tarih = editTarih.getText().toString();

        if (!tutarStr.isEmpty()) {
            double tutar = Double.parseDouble(tutarStr);
            toplamGelir += tutar;
            String kayit = tarih + " [Gelir] +" + tutar + " TL, " + kategori;
            kayitlar.add(kayit);
            guncelleBakiye();


            Map<String, Object> veri = new HashMap<>();
            veri.put("tarih", tarih);
            veri.put("tip", "Gelir");
            veri.put("tutar", tutar);
            veri.put("kategori", kategori);

            db.collection("kayitlar")
                    .add(veri)
                    .addOnSuccessListener(documentReference -> Toast.makeText(this, "Gelir Firebase'e kaydedildi", Toast.LENGTH_SHORT).show())
                    .addOnFailureListener(e -> Toast.makeText(this, "Firebase'e kaydedilemedi", Toast.LENGTH_SHORT).show());
        }
    }

    void giderEkle() {
        String tutarStr = editTutar.getText().toString();
        String kategori = editKategori.getText().toString();
        String tarih = editTarih.getText().toString();

        if (!tutarStr.isEmpty()) {
            double tutar = Double.parseDouble(tutarStr);
            toplamGider += tutar;
            String kayit = tarih + " [Gider] -" + tutar + " TL, " + kategori;
            kayitlar.add(kayit);
            guncelleBakiye();


            Map<String, Object> veri = new HashMap<>();
            veri.put("tarih", tarih);
            veri.put("tip", "Gider");
            veri.put("tutar", tutar);
            veri.put("kategori", kategori);

            db.collection("kayitlar")
                    .add(veri)
                    .addOnSuccessListener(documentReference -> Toast.makeText(this, "Gider Firebase'e kaydedildi", Toast.LENGTH_SHORT).show())
                    .addOnFailureListener(e -> Toast.makeText(this, "Firebase'e kaydedilemedi", Toast.LENGTH_SHORT).show());
        }
    }

    void guncelleBakiye() {
        double bakiye = toplamGelir - toplamGider;
        textBakiye.setText("Toplam Bakiye: " + bakiye + " TL");
    }

    void filtrele() {
        String arananKategori = spinnerKategori.getSelectedItem().toString();
        String aramaKelimesi = editAra.getText().toString().toLowerCase();
        String tarih = editTarih.getText().toString();

        ArrayList<String> filtreliKayitlar = new ArrayList<>();

        for (String kayit : kayitlar) {
            if ((arananKategori.equals("Tümü") || kayit.contains(arananKategori))
                    && kayit.toLowerCase().contains(aramaKelimesi)
                    && kayit.contains(tarih)) {
                filtreliKayitlar.add(kayit);
            }
        }


        Toast.makeText(this, "Filtrelenen kayıt sayısı: " + filtreliKayitlar.size(), Toast.LENGTH_SHORT).show();
    }


    class SimpleWatcher implements TextWatcher {
        Runnable onChange;
        SimpleWatcher(Runnable r) { this.onChange = r; }
        public void beforeTextChanged(CharSequence s, int st, int c, int a) {}
        public void onTextChanged(CharSequence s, int st, int b, int c) { onChange.run(); }
        public void afterTextChanged(Editable s) {}
    }
}
