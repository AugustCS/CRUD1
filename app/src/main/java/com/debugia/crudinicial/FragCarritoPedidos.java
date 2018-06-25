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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragCarritoPedidos extends Fragment implements View.OnClickListener {

    LinearLayout ly_contenedor;
    ArrayList<List<String>> CodArticulos = new ArrayList<>();
    ArrayList<List<String>> DescArticulos = new ArrayList<>();
    Activity activity;
    Button b_aceptar;

    public FragCarritoPedidos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_carrito_pedidos, container, false);
        ly_contenedor = view.findViewById(R.id.ly_contenedor);
        b_aceptar=view.findViewById(R.id.b_aceptar);
        activity = getActivity();
        GenerarFilas();
        b_aceptar.setOnClickListener(this);
        return view;
    }




    public void GenerarFilas() {
        ly_contenedor.removeAllViews();
        CodArticulos = BDCarritoPedidos.getListCodProductos();
        DescArticulos = BDCarritoPedidos.getArraylistPedidos(CodArticulos);
        RelativeLayout.LayoutParams lpimage = new RelativeLayout.LayoutParams(200, 200);
        LinearLayout.LayoutParams lpLayout = new LinearLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT, 1f);
        try {
            for (int i = 0; i < DescArticulos.size(); i++) {
                LinearLayout linearLayouth = new LinearLayout(activity);
                linearLayouth.setOrientation(LinearLayout.HORIZONTAL);
                linearLayouth.setMinimumWidth(RelativeLayout.LayoutParams.MATCH_PARENT);
                linearLayouth.setMinimumHeight(RelativeLayout.LayoutParams.MATCH_PARENT);
                ly_contenedor.addView(linearLayouth);

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
                        CodigosGenerales.Cod_Articulo = DescArticulos.get(finalI).get(1);
                        fragment = new FragListArtiDescripcion();
                        CambiarFragment(fragment);
                    }
                });

                TextView nombre = new TextView(activity);
                nombre.setText(DescArticulos.get(i).get(2));
                nombre.setGravity(Gravity.CENTER_HORIZONTAL);
                linearLayoutv.addView(nombre);

                TextView tamaño = new TextView(activity);
                tamaño.setText(DescArticulos.get(i).get(3));
                tamaño.setGravity(Gravity.CENTER_HORIZONTAL);
                linearLayoutv.addView(tamaño);

                TextView Cantidad = new TextView(activity);
                Cantidad.setText("Cantidad:" + CodArticulos.get(i).get(1));
                Cantidad.setGravity(Gravity.CENTER_HORIZONTAL);
                linearLayoutv.addView(Cantidad);

                TextView precio = new TextView(activity);
                precio.setText(DescArticulos.get(i).get(4));
                precio.setGravity(Gravity.CENTER_HORIZONTAL);
                linearLayoutv.addView(precio);

            }
        } catch (Exception e) {
            Log.d("GenerarFilas", e.getMessage());
        }
    }











/*
    public void GenerarFilas() {
        ly_contenedor.removeAllViews();
        CodArticulos = BDCarritoPedidos.getListCodProductos();
        DescArticulos = BDCarritoPedidos.getArraylistPedidos(CodArticulos);
        RelativeLayout.LayoutParams lpimage = new RelativeLayout.LayoutParams(200, 200);
        LinearLayout.LayoutParams lpLayout = new LinearLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT, 1f);
        try {
            for (int i = 0; i < DescArticulos.size(); i++) {
                LinearLayout linearLayouth = new LinearLayout(activity);
                linearLayouth.setOrientation(LinearLayout.HORIZONTAL);
                linearLayouth.setMinimumWidth(RelativeLayout.LayoutParams.MATCH_PARENT);
                linearLayouth.setMinimumHeight(RelativeLayout.LayoutParams.MATCH_PARENT);
                ly_contenedor.addView(linearLayouth);
                for (int a = 0; a < 2; a++) {
                    i = i + a;
                    if (!(i < DescArticulos.size()))
                        break;
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
                            CodigosGenerales.Cod_Articulo = DescArticulos.get(finalI).get(1);
                            fragment = new FragListArtiDescripcion();
                            CambiarFragment(fragment);
                        }
                    });

                    TextView nombre = new TextView(activity);
                    nombre.setText(DescArticulos.get(i).get(2));
                    nombre.setGravity(Gravity.CENTER_HORIZONTAL);
                    linearLayoutv.addView(nombre);

                    TextView tamaño = new TextView(activity);
                    tamaño.setText(DescArticulos.get(i).get(3));
                    tamaño.setGravity(Gravity.CENTER_HORIZONTAL);
                    linearLayoutv.addView(tamaño);

                    TextView Cantidad = new TextView(activity);
                    Cantidad.setText("Cantidad:"+CodArticulos.get(i).get(1));
                    Cantidad.setGravity(Gravity.CENTER_HORIZONTAL);
                    linearLayoutv.addView(Cantidad);

                    TextView precio = new TextView(activity);
                    precio.setText(DescArticulos.get(i).get(4));
                    precio.setGravity(Gravity.CENTER_HORIZONTAL);
                    linearLayoutv.addView(precio);
                }
            }
        } catch (Exception e) {
            Log.d("GenerarFilas", e.getMessage());
        }
    }
*/



    public void CambiarFragment(Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frag_contenedor, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case(R.id.b_aceptar):
                if(BDCarritoPedidos.EnviarPedidos())
                    Toast.makeText(getContext(),"Se ha enviado el pedido",Toast.LENGTH_SHORT);
                else
                    Toast.makeText(getContext(),"No se ha podido enviar",Toast.LENGTH_SHORT);
                break;
        }
    }
}