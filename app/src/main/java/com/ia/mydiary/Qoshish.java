package com.ia.mydiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Qoshish extends AppCompatActivity {
    String saqlakun, saqlaoy,saqlasoat, ID;
    private EditText boshliqkirit, ochiqlamakirit;
    SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qoshish);

        Spinner spinner = (Spinner) findViewById(R.id.planets_spinner);
        Spinner spinnerkun = (Spinner) findViewById(R.id.planets_spinnerkun);
        Spinner spinnersoat = (Spinner) findViewById(R.id.planets_spinnersoat);
        ochiqlamakirit=findViewById(R.id.ochiqlamakirit);
        boshliqkirit=findViewById(R.id.boshliqkirit);





        spinnerkun.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                saqlakun = spinnerkun.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                saqlakun= "01";
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                saqlaoy=spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                saqlaoy="yanvar";
            }
        });
        spinnersoat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                saqlasoat=spinnersoat.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                saqlasoat="00:00";
            }
        });





        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.oylar,
               R.layout.spinneritem
        );
        adapter.setDropDownViewResource(R.layout.spinneritem);
        ArrayAdapter<CharSequence> adapterkun = ArrayAdapter.createFromResource(
                this,
                R.array.sanalar,
                R.layout.spinneritem
        );
        adapterkun.setDropDownViewResource(R.layout.spinneritem);
        ArrayAdapter<CharSequence> adaptersoat = ArrayAdapter.createFromResource(
                this,
                R.array.soatlar,
                R.layout.spinneritem
        );
        adaptersoat.setDropDownViewResource(R.layout.spinneritem);
        spinner.setAdapter(adapter);
        spinnersoat.setAdapter(adaptersoat);
        spinnerkun.setAdapter(adapterkun);

    }



    public void saqla_chiq(View v){
        if (!saqlakun.isEmpty() &&!saqlaoy.isEmpty() &&!saqlasoat.isEmpty()){
            ID = saqlasoat+"."+saqlakun+"."+saqlaoy;
            String bshlq=boshliqkirit.getText().toString();
            String ochql=ochiqlamakirit.getText().toString();
            prefs = this.getSharedPreferences(
                    "com.ia.mydiary", Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = prefs.edit();
            editor.putString(ID+"Bosh",bshlq);
            editor.putString(ID+"ochiq",ochql);
            editor.apply();

            Toast.makeText(this, "Muaffaqiyatli amalga oshirildi", Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(this, "Iltimos, yuqorida qismda berilgan kun, oy, soatni tanlang !!", Toast.LENGTH_SHORT).show();
        }

    }
    public void home(View v){
        Intent i = new Intent(Qoshish.this,OnaSahifa.class);
        startActivity(i);
    }

}

