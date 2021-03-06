package com.debugia.crudinicial;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragListArticulos extends Fragment {
    Activity activity;
    LinearLayout layout_cointairner;
    ArrayList<List<String>> articulos = new ArrayList<>();
    Button b_filtro;
    EditText et_bucar;

    Toolbar toolbar_filtro, toolbar;

    public FragListArticulos() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity = getActivity();
        CodigosGenerales.Filtro = false;
        View view = inflater.inflate(R.layout.fragment_lista_articulos, container, false);
        layout_cointairner = view.findViewById(R.id.ly_contenedor);
        b_filtro = view.findViewById(R.id.b_filtro);
        et_bucar=view.findViewById(R.id.et_buscar);
        toolbar=getActivity().findViewById(R.id.toolbar);
        toolbar_filtro=getActivity().findViewById(R.id.toolbar_filtro);
        articulos = CodigosGenerales.getListArticulos("");
        b_filtro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar_filtro);
                toolbar.setVisibility(View.GONE);
                toolbar_filtro.setVisibility(View.VISIBLE);
                Fragment fragment = new FragFiltros();
                CambiarFragment(fragment);
            }
        });

        et_bucar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                articulos = CodigosGenerales.getListArticulos(et_bucar.getText().toString());
                GenerarFilas();;
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        GenerarFilas();
        return view;
    }

    public void GenerarFilas() {
        layout_cointairner.removeAllViews();
        RelativeLayout.LayoutParams lpimage = new RelativeLayout.LayoutParams(200, 200);
        LinearLayout.LayoutParams lpLayout = new LinearLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT, 1f);
        try {
            for (int i = 0; i < articulos.size(); i++) {
                LinearLayout linearLayouth = new LinearLayout(activity);
                linearLayouth.setOrientation(LinearLayout.HORIZONTAL);
                linearLayouth.setMinimumWidth(RelativeLayout.LayoutParams.MATCH_PARENT);
                linearLayouth.setMinimumHeight(RelativeLayout.LayoutParams.MATCH_PARENT);
                layout_cointairner.addView(linearLayouth);
                for (int a = 0; a < 2; a++) {
                    i = i + a;
                    if (!(i < articulos.size()))
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
                            CodigosGenerales.Cod_Articulo = articulos.get(finalI).get(1);
                            fragment = new FragListArtiDescripcion();
                            CambiarFragment(fragment);
                        }
                    });

                    TextView nombre = new TextView(activity);
                    nombre.setText(articulos.get(i).get(2));
                    nombre.setGravity(Gravity.CENTER_HORIZONTAL);
                    linearLayoutv.addView(nombre);

                    TextView tamaño = new TextView(activity);
                    tamaño.setText(articulos.get(i).get(3));
                    tamaño.setGravity(Gravity.CENTER_HORIZONTAL);
                    linearLayoutv.addView(tamaño);

                    TextView precio = new TextView(activity);
                    precio.setText(articulos.get(i).get(4));
                    precio.setGravity(Gravity.CENTER_HORIZONTAL);
                    linearLayoutv.addView(precio);
                }
            }
        } catch (Exception e) {
            Log.d("GenerarFilas", e.getMessage());
        }
    }

    public void CambiarFragment(Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frag_contenedor, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}