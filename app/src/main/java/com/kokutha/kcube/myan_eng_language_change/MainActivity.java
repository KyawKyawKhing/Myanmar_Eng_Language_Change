package com.kokutha.kcube.myan_eng_language_change;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView show1, show2;
    Button btnmyan, btneng;
    Locale mylocale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        show1 = (TextView) findViewById(R.id.txtshow1);
        show2 = (TextView) findViewById(R.id.txtshow2);


        btnmyan = (Button) findViewById(R.id.btnmyan);
        btneng = (Button) findViewById(R.id.btneng);

        btnmyan.setOnClickListener(this);
        btneng.setOnClickListener(this);
        loadlocale();
    }

    private void loadlocale() {
        // TODO Auto-generated method stub
        changelang(getSharedPreferences("CommonPrefs", 0).getString("Language", ""));
    }

    private void changelang(String lang) {
        // TODO Auto-generated method stub
        if (!lang.equalsIgnoreCase("")) {
            this.mylocale = new Locale(lang);
            savelocale(lang);
            Locale.setDefault(this.mylocale);
            Configuration config = new Configuration();
            config.locale = this.mylocale;
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
            updatetexts();
        }
    }

    private void updatetexts() {
        // TODO Auto-generated method stub
        this.show1.setText(R.string.eng1);
        this.show2.setText(R.string.eng2);
        this.btneng.setText(R.string.btn1);
        this.btnmyan.setText(R.string.btn2);
        getSupportActionBar().setTitle(R.string.app_name);
    }

    private void savelocale(String lang) {
        // TODO Auto-generated method stub
        SharedPreferences.Editor editor = getSharedPreferences("CommonPrefs", 0).edit();
        editor.putString("Language", lang);
        editor.commit();
    }

    @Override
    public void onClick(View v) {
        String lang = "en";
        switch (v.getId()) {
            case R.id.btneng:
                lang = "en";
                break;

            case R.id.btnmyan:
                lang = "my";
                break;
        }
        changelang(lang);
    }
}
