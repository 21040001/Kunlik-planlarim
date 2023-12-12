package com.ia.mydiary;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class OnaSahifa extends AppCompatActivity {

    private TextView ona_ekran_soat, haftakuni,tarih;
    private ListView list;


    String plan_reja_soat;
    String plan_map_key;
    String plan_map_oy;
    String list_soat;
    String list_boshliq;
    String list_min;
    String list_ochiqlama;
    Map<String,String> list_map;
    List<Map<String,String>> list_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ona_sahifa);

        list =findViewById(R.id.list);
        ona_ekran_soat=findViewById(R.id.soat_ona_ekran);
        haftakuni=findViewById(R.id.haftakuni);
        tarih=findViewById(R.id.tarih);

// vaqt bolimi


        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat  simpledatetime=new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat  simpledatetimesoat=new SimpleDateFormat("hh:mm");
        String soat_ona=simpledatetimesoat.format(date);
        ona_ekran_soat.setText(soat_ona);
        String formattedDate = simpledatetime.format(date);
        tarih.setText(formattedDate);

        Calendar cal=Calendar.getInstance();
        int val = cal.get(Calendar.DAY_OF_WEEK);
        String kunlar = new DateFormatSymbols().getWeekdays()[val];
        haftakuni.setText(kunlar);


//bajaridiganlar qism list
        SharedPreferences prefs = this.getSharedPreferences(
                "com.ia.mydiary", Context.MODE_PRIVATE);

        Map<String, ?> ish_reja_planlar = prefs.getAll();
        System.out.println(ish_reja_planlar);

        SimpleDateFormat plansimpledateformat= new SimpleDateFormat("dd");
        String plandateformat= plansimpledateformat.format(date);

        SimpleDateFormat plansimpledateformatoy= new SimpleDateFormat("MM");
        String plandateformatoy= plansimpledateformatoy.format(date);

    //oyni raqamlishakildan nom shakliga almashtirayabman ==>  01 ==>yanvar kabi

    switch (plandateformatoy){
        case "01":
            plan_map_oy="Yanvar";
            break;
        case "02":
            plan_map_oy="Fevral";
            break;
        case "03":
            plan_map_oy="Mart";
            break;
        case "04":
            plan_map_oy="Aprel";
            break;
        case "05":
            plan_map_oy="May";
            break;
        case "06":
            plan_map_oy="Iyun";
            break;
        case "07":
            plan_map_oy="Iyul";
            break;
        case "08":
            plan_map_oy="Avgust";
            break;
        case "09":
            plan_map_oy="Sentabr";
            break;
        case "10":
            plan_map_oy="Oktabr";
            break;
        case "11":
            plan_map_oy="Noyabr";
            break;
        case "12":
            plan_map_oy="Dekabr";
            break;

    }





        list_list=new ArrayList<Map<String,String>>();
        for( int i =0;  i<13 ;i=i+1){ //==> soat dongusu

            for (int o=0;o<46;o=o+15){//==> minut dongusu

                list_map=new HashMap<String,String>();



                // soatni ikki honali shakilda yozilishishi uchun kontrol
                if(i<10){
                    // minutni ikki honali shakilda yozilishishi uchun kontrol
                    if(o==0){


                        plan_reja_soat="0"+i+":"+o+"0";
                        plan_map_key=plan_reja_soat+"."+plandateformat+"."+plan_map_oy;

                        list_soat= String.valueOf(i);
                        list_min= String.valueOf(o);
                        list_boshliq= prefs.getString(plan_map_key+"Bosh","ochiq");
                        list_ochiqlama= prefs.getString(plan_map_key+"ochiq","");



                        if (!(list_boshliq =="ochiq")){

                            System.out.println("fghjk"+list_boshliq);
                            list_map.put("Boshliqlar",list_boshliq);
                            list_map.put("ochiqlama",list_ochiqlama);
                            list_map.put("soat", String.valueOf(i));
                            list_map.put("minut", String.valueOf(o));
                            list_list.add(list_map);

                        }

                    }else{
                        plan_reja_soat="0"+i+":"+o;
                        plan_map_key=plan_reja_soat+"."+plandateformat+"."+plan_map_oy;
                        list_soat= String.valueOf(i);
                        list_min= String.valueOf(o);
                        list_boshliq= prefs.getString(plan_map_key+"Bosh","ochiq");
                        list_ochiqlama= prefs.getString(plan_map_key+"ochiq","");

                        if (!(list_boshliq.equals("ochiq"))){

                            System.out.println("fghjk"+list_boshliq);
                            list_map.put("Boshliqlar",list_boshliq);
                            list_map.put("ochiqlama",list_ochiqlama);
                            list_map.put("soat", String.valueOf(i));
                            list_map.put("minut", String.valueOf(o));

                            list_list.add(list_map);
                        }

                    }



                }else{
                    // minutni ikki honali shakilda yozilishishi uchun kontrol
                    if(o==0){
                        plan_reja_soat=i+":"+o+"0";
                        plan_map_key=plan_reja_soat+"."+plandateformat+"."+plan_map_oy;
                        list_soat= String.valueOf(i);
                        list_min= String.valueOf(o);
                        list_boshliq= prefs.getString(plan_map_key+"Bosh","ochiq");
                        list_ochiqlama= prefs.getString(plan_map_key+"ochiq","");


                        if (!(list_boshliq =="ochiq")){

                            System.out.println("fghjk"+list_boshliq);
                            list_map.put("Boshliqlar",list_boshliq);
                            list_map.put("ochiqlama",list_ochiqlama);
                            list_map.put("soat", String.valueOf(i));
                            list_map.put("minut", String.valueOf(o));
                            list_list.add(list_map);

                        }

                    }else{
                        plan_reja_soat=i+":"+o;
                        plan_map_key=plan_reja_soat+"."+plandateformat+"."+plan_map_oy;

                        list_soat= String.valueOf(i);
                        list_min= String.valueOf(o);
                        list_boshliq= prefs.getString(plan_map_key+"Bosh","ochiq");
                        list_ochiqlama= prefs.getString(plan_map_key+"ochiq","");

                        if (!(list_boshliq =="ochiq")){

                            System.out.println("fghjk"+list_boshliq);
                            list_map.put("Boshliqlar",list_boshliq);
                            list_map.put("ochiqlama",list_ochiqlama);
                            list_map.put("soat", String.valueOf(i));
                            list_map.put("minut", String.valueOf(o));
                            list_list.add(list_map);


                        }
                    }

                }

            }





        }

        SimpleAdapter ad ;
        String[]f={"Boshliqlar","ochiqlama","soat","minut"};
        int[]t={R.id.boshliq,R.id.ochiqlama,R.id.soat1,R.id.soat2};
        ad= new SimpleAdapter(OnaSahifa.this,list_list,R.layout.list_ornek,f,t);
        list.setAdapter(ad);





    }

    public void qoshish(View v){
        Intent i = new Intent(OnaSahifa.this, Qoshish.class);
        startActivity(i);
    }
    public void share(View v){
        Intent sendIntent = new Intent();


        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Salom aziz foydalanuvchi. Biz sizni oramizda bo'lishingizdan juda ham mamnun bo'lamiz, bu sababdan sizni bizga qo'shilishga taklif qilib qolamiz.\nhttps://play.google.com/store/apps/details?id=com.ia.mydiary ");

        sendIntent.setType("text/plan");
        startActivity(sendIntent);
    }
}