package com.example.butcetakip;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.*;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.util.ArrayList;
import android.widget.Button;

public class PieChartActivity extends AppCompatActivity {

    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);


        pieChart = findViewById(R.id.pieChart);
        Button btnGeri = findViewById(R.id.btnGeri);


        double gelir = getIntent().getDoubleExtra("gelir", 0);
        double gider = getIntent().getDoubleExtra("gider", 0);

        if (gelir == 0 && gider == 0) {
            pieChart.setNoDataText("Gösterilecek veri yok.");
            return;
        }

        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry((float) gelir, "Gelir"));
        entries.add(new PieEntry((float) gider, "Gider"));

        PieDataSet dataSet = new PieDataSet(entries, "Gelir/Gider Dağılımı");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        dataSet.setValueTextColor(android.graphics.Color.WHITE);
        dataSet.setValueTextSize(14f);
        dataSet.setSliceSpace(3f);

        PieData data = new PieData(dataSet);

        pieChart.setData(data);
        pieChart.setCenterText("Bütçe Grafiği");
        pieChart.setCenterTextSize(18f);
        pieChart.setHoleRadius(50f);
        pieChart.setTransparentCircleRadius(55f);
        pieChart.setEntryLabelColor(android.graphics.Color.DKGRAY);
        pieChart.setEntryLabelTextSize(12f);
        pieChart.setUsePercentValues(true);

        pieChart.animateY(1000);
        pieChart.invalidate();


        btnGeri.setOnClickListener(v -> finish());



    }
}
