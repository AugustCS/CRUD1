package com.debugia.crudinicial;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

        Familia= conexionSQL.getListFamilia();
        arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, Familia);
        lv_items.setAdapter(arrayAdapter);

        et_bucar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                Log.d("Editando","beforeTextChanged");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                getData();
                Log.d("Editando","onTextChanged");
            }

            @Override
            public void afterTextChanged(Editable s) {

                Log.d("Editando","onTextChanged");
            }
        });
//        getData();


        return view;
    }


    private void getData(){

        Familia= conexionSQL.getListFamiliaSearchView(et_bucar.getText().toString());
        arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, Familia);
        lv_items.setAdapter(arrayAdapter);


    }


}