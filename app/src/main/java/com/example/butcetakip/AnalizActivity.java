package com.example.butcetakip;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AnalizActivity extends AppCompatActivity {

    TextView textAnaliz;
    ListView listAnaliz;
    Button btnGeriDon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analiz);

        // View'lar
        textAnaliz = findViewById(R.id.textAnaliz);
        listAnaliz = findViewById(R.id.listAnaliz);
        btnGeriDon = findViewById(R.id.btnGeriDon);

        // Geri Dön Butonu
        btnGeriDon.setOnClickListener(v -> finish());

        // Gelen verileri işle
        ArrayList<String> kayitlar = getIntent().getStringArrayListExtra("kayitlar");
        if (kayitlar == null) kayitlar = new ArrayList<>();

        double toplamGelir = 0;
        double toplamGider = 0;
        ArrayList<String> duzenliKayitlar = new ArrayList<>();

        for (String satir : kayitlar) {
            if (satir.contains("[Gelir]")) {
                String[] parca = satir.split("\\+");
                toplamGelir += Double.parseDouble(parca[1].split(" ")[0]);
            } else if (satir.contains("[Gider]")) {
                String[] parca = satir.split("-");
                toplamGider += Double.parseDouble(parca[1].split(" ")[0]);
            }
            duzenliKayitlar.add("• " + satir);
        }

        textAnaliz.setText("📊 İstatistik\n\n" +
                "Toplam Gelir: " + toplamGelir + " TL\n" +
                "Toplam Gider: " + toplamGider + " TL\n" +
                "Bakiye: " + (toplamGelir - toplamGider) + " TL");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, duzenliKayitlar);
        listAnaliz.setAdapter(adapter);
    }
}
