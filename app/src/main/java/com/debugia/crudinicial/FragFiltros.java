package com.debugia.crudinicial;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.charset.CharacterCodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragFiltros extends Fragment implements View.OnClickListener {

    ExpandableListAdapter listAdapter;
    ExpListViewAdapterWithCheckbox listAdapterCheckbox;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    BDFamilia bdFamilia = new BDFamilia();
    BDSubFamilia bdSubFamilia = new BDSubFamilia();
    BDConcepto bdConcepto = new BDConcepto();
    BDFiltros bdFiltros = new BDFiltros();

    Button b_borrar, b_listo;

    Toolbar toolbar_filtro, toolbar;


    public FragFiltros() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filtros, container, false);

        expListView = view.findViewById(R.id.lvExp);

        prepareListData();

        listAdapter = new ExpandableListAdapter(getContext(), listDataHeader, listDataChild);
        listAdapterCheckbox = new ExpListViewAdapterWithCheckbox(getContext(), listDataHeader, listDataChild);

        toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar_filtro = getActivity().findViewById(R.id.toolbar_filtro);
        b_borrar = getActivity().findViewById(R.id.b_borrar);
        b_listo = getActivity().findViewById(R.id.b_listo);

        expListView.setAdapter(listAdapterCheckbox);

        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {

                return false;
            }
        });

        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {

            }
        });

        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {


            }
        });

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub

                return false;
            }
        });
        b_borrar.setOnClickListener(this);
        b_listo.setOnClickListener(this);
        return view;
    }


    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding Header data
        listDataHeader.add("Familia");
        listDataHeader.add("Sub Familia");
        ArrayList<List<String>> Conceptos = BDConcepto.getListaConceptos();
        Integer conceptos = Conceptos.size();
        for (int i = 0; i < conceptos; i++) {
            listDataHeader.add(Conceptos.get(i).get(1));
        }
        List<String> list_familia = new ArrayList<>();
        List<String> list_subFamilia = new ArrayList<>();
        List<String> list_concepto1 = new ArrayList<>();
        List<String> list_concepto2 = new ArrayList<>();
        List<String> list_concepto3 = new ArrayList<>();
        List<String> list_concepto4 = new ArrayList<>();
        List<String> list_concepto5 = new ArrayList<>();
        List<String> list_concepto6 = new ArrayList<>();
        List<String> list_concepto7 = new ArrayList<>();
        // Adding child data
        try {
            list_familia = bdFamilia.getListaNombres("");
            if (listDataHeader.size() > 0)
                listDataChild.put(listDataHeader.get(0), list_familia);

            list_subFamilia = bdSubFamilia.getListaNombres("");
            if (listDataHeader.size() > 1)
                listDataChild.put(listDataHeader.get(1), list_subFamilia);

            CodigosGenerales.ConceptoElegido = 1;
            list_concepto1 = bdConcepto.getListaNombres("");
            if (listDataHeader.size() > 2)
                listDataChild.put(listDataHeader.get(2), list_concepto1);

            CodigosGenerales.ConceptoElegido = 2;
            list_concepto2 = bdConcepto.getListaNombres("");
            if (listDataHeader.size() > 3)
                listDataChild.put(listDataHeader.get(3), list_concepto2);

            CodigosGenerales.ConceptoElegido = 3;
            list_concepto3 = bdConcepto.getListaNombres("");
            if (listDataHeader.size() > 4)
                listDataChild.put(listDataHeader.get(4), list_concepto3);

            CodigosGenerales.ConceptoElegido = 4;
            list_concepto4 = bdConcepto.getListaNombres("");
            if (listDataHeader.size() > 5)
                listDataChild.put(listDataHeader.get(5), list_concepto4);

            CodigosGenerales.ConceptoElegido = 5;
            list_concepto5 = bdConcepto.getListaNombres("");
            if (listDataHeader.size() > 6)
                listDataChild.put(listDataHeader.get(6), list_concepto5);

            CodigosGenerales.ConceptoElegido = 6;
            list_concepto6 = bdConcepto.getListaNombres("");
            if (listDataHeader.size() > 7)
                listDataChild.put(listDataHeader.get(7), list_concepto6);

            CodigosGenerales.ConceptoElegido = 7;
            list_concepto7 = bdConcepto.getListaNombres("");
            if (listDataHeader.size() > 8)
                listDataChild.put(listDataHeader.get(8), list_concepto7);

        } catch (Exception e) {
            Log.d("Concentps", e.getMessage());

        }
    }

    public void CambiarFragment(Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frag_contenedor, fragment);
        transaction.commit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case (R.id.b_borrar):
                listAdapterCheckbox.ClearAllCheckBoxes();
                for (int i = 0; i < listAdapterCheckbox.getGroupCount(); i++) {
                    expListView.collapseGroup(i);
                }
                break;
            case (R.id.b_listo):
                List<String> Familia = new ArrayList<>();
                List<String> SubFamilia = new ArrayList<>();
                List<String> Concepto1 = new ArrayList<>();
                List<String> Concepto2 = new ArrayList<>();
                List<String> Concepto3 = new ArrayList<>();
                List<String> Concepto4 = new ArrayList<>();
                List<String> Concepto5 = new ArrayList<>();
                List<String> Concepto6 = new ArrayList<>();
                List<String> Concepto7 = new ArrayList<>();
                Boolean Filtrar = false;
                ArrayList<List<Integer>> CheckedList = listAdapterCheckbox.getCheckedList();
                Integer j = 0;
                try {
                    for (int i = 0; i < CheckedList.size(); i++) {
                        if (CheckedList.get(i).get(0) == 0) {
                            if (Familia.size() < 1)
                                j = 0;
                            Familia.add(bdFamilia.arrayLista.get(j).get(1));
                            Filtrar = true;
                        }
                        if (CheckedList.get(i).get(0) == 1) {
                            if (SubFamilia.size() < 1)
                                j = 0;
                            SubFamilia.add(bdSubFamilia.arrayLista.get(j).get(1));
                            Filtrar = true;
                        }
                        if (CheckedList.get(i).get(0) == 2) {
                            if (Concepto1.size() < 1)
                                j = 0;
                            CodigosGenerales.ConceptoElegido = 1;
                            bdConcepto.getListaNombres("");
                            Concepto1.add(bdConcepto.arrayLista.get(j).get(1));
                            Filtrar = true;
                        }
                        if (CheckedList.get(i).get(0) == 3) {
                            if (Concepto2.size() < 1)
                                j = 0;
                            CodigosGenerales.ConceptoElegido = 2;
                            bdConcepto.getListaNombres("");
                            Concepto2.add(bdConcepto.arrayLista.get(j).get(1));
                            Filtrar = true;
                        }
                        if (CheckedList.get(i).get(0) == 4) {
                            if (Concepto3.size() < 1)
                                j = 0;
                            CodigosGenerales.ConceptoElegido = 3;
                            bdConcepto.getListaNombres("");
                            Concepto3.add(bdConcepto.arrayLista.get(j).get(1));
                            Filtrar = true;
                        }
                        if (CheckedList.get(i).get(0) == 5) {
                            if (Concepto4.size() < 1)
                                j = 0;
                            CodigosGenerales.ConceptoElegido = 4;
                            bdConcepto.getListaNombres("");
                            Concepto4.add(bdConcepto.arrayLista.get(j).get(1));
                            Filtrar = true;
                        }
                        if (CheckedList.get(i).get(0) == 6) {
                            if (Concepto5.size() < 1)
                                j = 0;
                            CodigosGenerales.ConceptoElegido = 5;
                            bdConcepto.getListaNombres("");
                            Concepto5.add(bdConcepto.arrayLista.get(j).get(1));
                            Filtrar = true;
                        }
                        if (CheckedList.get(i).get(0) == 7) {
                            if (Concepto6.size() < 1)
                                j = 0;
                            CodigosGenerales.ConceptoElegido = 6;
                            bdConcepto.getListaNombres("");
                            Concepto6.add(bdConcepto.arrayLista.get(j).get(1));
                            Filtrar = true;
                        }
                        if (CheckedList.get(i).get(0) == 8) {
                            if (Concepto7.size() < 1)
                                j = 0;
                            CodigosGenerales.ConceptoElegido = 7;
                            bdConcepto.getListaNombres("");
                            Concepto7.add(bdConcepto.arrayLista.get(j).get(1));
                            Filtrar = true;
                        }
                        j++;
                    }
                    CodigosGenerales.Tipo = "Filtro";
                    if (Filtrar) {
                        CodigosGenerales.arrayArticulosFiltrados = bdFiltros.getListaArticulos(Familia, SubFamilia, Concepto1, Concepto2, Concepto3, Concepto4, Concepto5, Concepto6, Concepto7);
                        Fragment fragment = new FragListArticulos();
                        CambiarFragment(fragment);
                    }
                } catch (Exception e) {
                    Log.d("b_listo", e.getMessage());
                }

                break;
        }
    }



    @Override
    public void onDetach() {
        try {
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
            toolbar_filtro.setVisibility(View.GONE);
            toolbar.setVisibility(View.VISIBLE);
        } catch (Exception e) {
            Log.d("getSupportActionBar", e.getMessage());
        }
        super.onDetach();
    }

}