package com.example.llanamoslamochila;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private List<CheckBox> listaCheck;
    private TextView lblPesoTotal;
    private Map<String, Integer> articulos;
    private int defaultTextColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaCheck = new ArrayList<CheckBox>();
        listaCheck.add(findViewById(R.id.chkGorras));
        listaCheck.add(findViewById(R.id.chkBotines));
        listaCheck.add(findViewById(R.id.chkChanclas));
        listaCheck.add(findViewById(R.id.chkCamisas));
        listaCheck.add(findViewById(R.id.chkPantalones));
        listaCheck.add(findViewById(R.id.chkBanyadores));
        lblPesoTotal = findViewById(R.id.lblPesoTotal);
        defaultTextColor = lblPesoTotal.getTextColors().getDefaultColor();

        articulos = new HashMap<String, Integer>();
        articulos.put("Gorras", 4);
        articulos.put("Bañadores", 7);
        articulos.put("Camisas", 5);
        articulos.put("Pantalones", 8);
        articulos.put("Chanclas", 3);
        articulos.put("Botines", 6);

        for (CheckBox chk : listaCheck) {
            chk.setOnCheckedChangeListener(
                    (buttonView, isChecked) -> {
                        actualizarPesoTotal();
                    }
            );
        }

    }

    private void actualizarPesoTotal() {
        int total = 0;
        for (CheckBox chk : listaCheck) {
            if (chk.isChecked()) {
                String articulo = chk.getText().toString();
                Log.d("Contenido", articulo + ", " + articulos.get(articulo));
                total += articulos.get(articulo);
            }
        }
        lblPesoTotal.setText(total + " kg");
        if (total > 20) {
            lblPesoTotal.setTextColor(getColor(R.color.rojo));
        } else {
            lblPesoTotal.setTextColor(defaultTextColor);
        }
    }

    public void borrar(View v) {
        for (CheckBox chk : listaCheck) {
            chk.setChecked(false);
        }
        Toast.makeText(this, "Mochila vaciada.", Toast.LENGTH_SHORT).show();
    }
}