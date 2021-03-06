package com.debugia.crudinicial;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragMenuPrincipal extends Fragment implements View.OnClickListener {
    Button b_familia, b_sub_familia;
    LinearLayout ly_menu;

    BDFamilia bdFamilia=new BDFamilia();
    BDSubFamilia bdSubFamilia=new BDSubFamilia();
    BDConcepto bdConcepto= new BDConcepto();
    public FragMenuPrincipal() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu_principal, container, false);
        b_familia = view.findViewById(R.id.b_familia);
        b_sub_familia = view.findViewById(R.id.b_sub_familia);
        ly_menu = view.findViewById(R.id.ly_menu);

        b_familia.setOnClickListener(this);
        b_sub_familia.setOnClickListener(this);

        try {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("");
            ((AppCompatActivity) getActivity()).getSupportActionBar().show();
            Log.d("Titulo", ((AppCompatActivity) getActivity()).getSupportActionBar().getTitle() + "");
        } catch (Exception e) {
            Log.d("getSupportActionBar", e.getMessage());
        }

        //Crear Botones de conceptos

        ArrayList<List<String>> Conceptos =  BDConcepto.getListaConceptos();
        Integer conceptos = Conceptos.size();
        for (int i = 0; i < conceptos; i++) {
            Button btnTag = new Button(getContext());
            btnTag.setLayoutParams(b_familia.getLayoutParams());

            //btnTag.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.polo));
            btnTag.setGravity(b_familia.getGravity());
            btnTag.setBackgroundColor(getResources().getColor(R.color.colorBlack));
            btnTag.setTextColor(getResources().getColor(R.color.colorWhite));
            btnTag.setText(Conceptos.get(i).get(1).toString());
            btnTag.setId(i + 1);
            btnTag.setOnClickListener(this);
            ly_menu.addView(btnTag);
            b_familia.setPadding(btnTag.getPaddingLeft(), btnTag.getPaddingTop(), btnTag.getPaddingRight(), btnTag.getPaddingBottom());
            b_sub_familia.setPadding(btnTag.getPaddingLeft(), btnTag.getPaddingTop(), btnTag.getPaddingRight(), btnTag.getPaddingBottom());
        }


        return view;
    }

    @Override
    public void onClick(View v) {
        Fragment fragment;
        switch (v.getId()) {
            case (R.id.b_familia):
                CodigosGenerales.Tipo = "Familia";
                fragment = new FragLista();
                CambiarFragment(fragment);
                break;
            case (R.id.b_sub_familia):
                CodigosGenerales.Tipo = "SubFamilia";
                fragment = new FragLista();
                CambiarFragment(fragment);
                break;
            case (1):
                CodigosGenerales.ConceptoElegido=1;
                CodigosGenerales.Tipo = "Concepto";
                fragment = new FragLista();
                CambiarFragment(fragment);
                break;
            case (2):
                CodigosGenerales.ConceptoElegido = 2;
                CodigosGenerales.Tipo = "Concepto";
                fragment = new FragLista();
                CambiarFragment(fragment);
                break;
            case (3):
                CodigosGenerales.ConceptoElegido = 3;
                CodigosGenerales.Tipo = "Concepto";
                fragment = new FragLista();
                CambiarFragment(fragment);
                break;
            case (4):
                CodigosGenerales.ConceptoElegido = 4;
                CodigosGenerales.Tipo = "Concepto";
                fragment = new FragLista();
                CambiarFragment(fragment);
                break;
            case (5):
                CodigosGenerales.ConceptoElegido = 5;
                CodigosGenerales.Tipo = "Concepto";
                fragment = new FragLista();
                CambiarFragment(fragment);
                break;
            case (6):
                CodigosGenerales.ConceptoElegido = 6;
                CodigosGenerales.Tipo = "Concepto";
                fragment = new FragLista();
                CambiarFragment(fragment);
                break;
            case (7):
                CodigosGenerales.ConceptoElegido = 7;
                CodigosGenerales.Tipo = "Concepto";
                fragment = new FragLista();
                CambiarFragment(fragment);
                break;
        }
    }

    public void CambiarFragment(Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frag_contenedor, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}