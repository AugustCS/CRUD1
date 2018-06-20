package com.debugia.crudinicial;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragListArtiDescripcion extends Fragment {

    ArrayList<List<String>> listArrayList = new ArrayList<>();

    TextView et_nom_family,et_stock,et_precio;
    ImageView iv_imagen;
    public FragListArtiDescripcion() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lista_articulos_desc, container, false);
        listArrayList = CodigosGenerales.getArticulosDescripcion();
        et_nom_family = view.findViewById(R.id.et_nom_family);
        et_stock = view.findViewById(R.id.et_stock);
        et_precio = view.findViewById(R.id.et_precio);
        iv_imagen = view.findViewById(R.id.iv_imagen);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.polo);
        RoundedBitmapDrawable mDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
        mDrawable.setCircular(true);
        iv_imagen.setImageDrawable(mDrawable);

        iv_imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
                View mView = getLayoutInflater().inflate(R.layout.dialog_zoom, null);
                PhotoView photoView = mView.findViewById(R.id.imageView);
                photoView.setImageResource(R.drawable.polo);
                mBuilder.setView(mView);
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });


        try {
            et_nom_family.setText(CodigosGenerales.nom_categoria+" - "+listArrayList.get(0).get(0));
            et_stock.setText(listArrayList.get(0).get(1));
            et_precio.setText(listArrayList.get(0).get(2));
        } catch (Exception e) {
            et_nom_family.setText("");
            Log.d("OnCreateView", e.getMessage());
        }
        return view;
    }

}