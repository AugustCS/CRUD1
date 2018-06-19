package com.debugia.crudinicial;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class familia extends Fragment {
    ConexionSQL conexionSQL = new ConexionSQL();
    ArrayList Familia = new ArrayList<String>();
    ArrayAdapter<String> arrayAdapter;
    EditText et_bucar;


    private  String editing=null;

    ListView lv_items;

    public familia() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_familia, container, false);
        lv_items=view.findViewById(R.id.lv_items);
        et_bucar=view.findViewById(R.id.et_buscar);

        Familia= FamiliaBD.getListFamilia("");
        arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, Familia);
        lv_items.setAdapter(arrayAdapter);

        et_bucar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                getData();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        return view;
    }


    private void getData(){
        Familia= FamiliaBD.getListFamilia(et_bucar.getText().toString());
        arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, Familia);
        lv_items.setAdapter(arrayAdapter);
    }

    public void CambiarFragment(Fragment fragment){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.fade_in,R.anim.fade_out,R.anim.fade_in,R.anim.fade_out);
        transaction.replace(R.id.frag_contenedor, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}