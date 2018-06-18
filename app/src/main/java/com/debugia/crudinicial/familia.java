package com.debugia.crudinicial;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class familia extends Fragment {
    ConexionSQL conexionSQL = new ConexionSQL();
    ArrayList Familia = new ArrayList<String>();
    ArrayAdapter<String> arrayAdapter;

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
        Familia= conexionSQL.getListFamilia();
        arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, Familia);
        lv_items.setAdapter(arrayAdapter);
        return view;
    }
}