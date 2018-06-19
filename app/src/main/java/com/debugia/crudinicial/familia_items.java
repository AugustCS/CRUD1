package com.debugia.crudinicial;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class familia_items extends Fragment {
    Activity activity;
    LinearLayout layout_cointairner;
    ArrayList<List<String>> articulos = new ArrayList<>();

    public familia_items() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity=getActivity();
        View view =inflater.inflate(R.layout.fragment_familia_items, container, false);
        layout_cointairner = view.findViewById(R.id.ly_contenedor);
        articulos=FamiliaBD.getListFamiliaArticulos();
        GenerarFilas();
        return view;


    }

    public void GenerarFilas() {
        int contador=0;
        layout_cointairner.removeAllViews();
        LayoutParams lpimage = new LayoutParams(200, 200);
        LayoutParams lpimage2 = new LayoutParams(LayoutParams.WRAP_CONTENT, 200);
        LinearLayout.LayoutParams lpLayout = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f);

        for (int i = 0; i < articulos.size(); i++) {


            LinearLayout linearLayouth = new LinearLayout(activity);
            linearLayouth.setOrientation(LinearLayout.HORIZONTAL);
            linearLayouth.setMinimumWidth(LayoutParams.MATCH_PARENT);
            linearLayouth.setMinimumHeight(LayoutParams.MATCH_PARENT);

            layout_cointairner.addView(linearLayouth);
            for (int a = 0; a < 2; a++) {

                LinearLayout linearLayoutv = new LinearLayout(activity);
                linearLayoutv.setOrientation(LinearLayout.VERTICAL);
                linearLayoutv.setLayoutParams(lpLayout);
                linearLayoutv.setGravity(Gravity.CENTER_HORIZONTAL);
                linearLayouth.addView(linearLayoutv);

                ImageView imageView = new ImageView(activity);
                imageView.setImageResource(getResources().getIdentifier("polo", "drawable", activity.getPackageName()));
                imageView.setLayoutParams(lpimage);
                linearLayoutv.addView(imageView);
                final int finalI = i;
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Fragment fragment;
                        Log.d("Codigo",articulos.get(finalI).get(1));
                        Log.d("Codigo",articulos.get(finalI).get(3));
                        fragment = new familia_item_descripcion();
                        CambiarFragment(fragment);
                    }
                });


                TextView nombre=new TextView(activity);
                nombre.setText(articulos.get(i).get(2));
                nombre.setGravity(Gravity.CENTER_HORIZONTAL);
                linearLayoutv.addView(nombre);

                TextView tamaño=new TextView(activity);
                tamaño.setText(articulos.get(i).get(3));
                tamaño.setGravity(Gravity.CENTER_HORIZONTAL);
                linearLayoutv.addView(tamaño);

                TextView precio=new TextView(activity);
                precio.setText(articulos.get(i).get(4));
                precio.setGravity(Gravity.CENTER_HORIZONTAL);
                linearLayoutv.addView(precio);
            }
        }

    }
    public void CambiarFragment(Fragment fragment){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frag_contenedor, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
