package com.debugia.crudinicial;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MenuPrincipal extends Fragment implements View.OnClickListener {
    Button b_familia, b_sub_familia;
    LinearLayout ly_menu;
    public MenuPrincipal() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu_principal, container, false);
        b_familia = view.findViewById(R.id.b_familia);
        b_sub_familia = view.findViewById(R.id.b_sub_familia);
        ly_menu=view.findViewById(R.id.ly_menu);

        b_familia.setOnClickListener(this);
        b_sub_familia.setOnClickListener(this);

        try {
            ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        } catch (Exception e) {
            Log.d("getSupportActionBar", e.getMessage());
        }
        ArrayList arrayList=ConceptosBD.getListConceptos();
        Integer conceptos = arrayList.size()/3;
        int nombre=0;
        for (int i=0; i<conceptos;i++){
            Button btnTag = new Button(getContext());
            btnTag.setLayoutParams( b_familia.getLayoutParams());

            //btnTag.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.polo));
            btnTag.setGravity(b_familia.getGravity());
            btnTag.setBackgroundColor(getResources().getColor(R.color.colorBlack));
            btnTag.setTextColor(getResources().getColor(R.color.colorWhite));
            btnTag.setText(arrayList.get(1+nombre).toString());
            btnTag.setId(i +1);
            btnTag.setOnClickListener(this);
            ly_menu.addView(btnTag);
            nombre+=3;
        }

        return view;
    }

    @Override
    public void onClick(View v) {
        Fragment fragment;
        switch (v.getId()){
            case (R.id.b_familia):
                fragment= new familia();
                CambiarFragment(fragment);
                break;
            case (R.id.b_sub_familia):
                fragment= new sub_familia();
                CambiarFragment(fragment);
                break;
            case(1):
                ConceptosBD.concepto=1;
                fragment= new Conceptos();
                CambiarFragment(fragment);
                break;
            case(2):
                ConceptosBD.concepto=2;
                fragment= new Conceptos();
                CambiarFragment(fragment);
                break;
            case(3):
                ConceptosBD.concepto=3;
                fragment= new Conceptos();
                CambiarFragment(fragment);
                break;
            case(4):
                ConceptosBD.concepto=4;
                fragment= new Conceptos();
                CambiarFragment(fragment);
                break;
            case(5):
                ConceptosBD.concepto=5;
                fragment= new Conceptos();
                CambiarFragment(fragment);
                break;
            case(6):
                ConceptosBD.concepto=6;
                fragment= new Conceptos();
                CambiarFragment(fragment);
                break;
            case(7):
                ConceptosBD.concepto=7;
                fragment= new Conceptos();
                CambiarFragment(fragment);
                break;
        }
    }

    public void CambiarFragment(Fragment fragment){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frag_contenedor, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
