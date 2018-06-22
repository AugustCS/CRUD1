package com.debugia.crudinicial;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragListArtiDescripcion extends Fragment {

    ArrayList<List<String>> listArrayList = new ArrayList<>();
    Button b_cerrar_popup;
    View popupView;
    PopupWindow popupWindow;

    LayoutInflater layoutInflater;
    ImageButton ib_info, b_share;
    TextView et_nom_family, et_stock, et_precio;
    ImageView iv_imagen;


    public FragListArtiDescripcion() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_articulos_desc, container, false);
        listArrayList = CodigosGenerales.getArticulosDescripcion();
        et_nom_family = view.findViewById(R.id.et_nom_family);
        et_stock = view.findViewById(R.id.et_stock);
        et_precio = view.findViewById(R.id.et_precio);
        iv_imagen = view.findViewById(R.id.iv_imagen);
        ib_info = view.findViewById(R.id.ib_info);
        b_share = view.findViewById(R.id.b_share);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.polo);
        RoundedBitmapDrawable mDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
        mDrawable.setCircular(true);
        iv_imagen.setImageDrawable(mDrawable);

        b_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBodyText = "Encia un mensaje";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Titulo");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBodyText);
                startActivity(Intent.createChooser(sharingIntent, "Opciones"));
            }
        });
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
            et_nom_family.setText(CodigosGenerales.nom_categoria + " - " + listArrayList.get(0).get(0));
            et_stock.setText(listArrayList.get(0).get(1));
            et_precio.setText(listArrayList.get(0).get(2));
        } catch (Exception e) {
            et_nom_family.setText("");
            Log.d("OnCreateView", e.getMessage());
        }

        ib_info.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutInflater =(LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                popupView = layoutInflater.inflate((R.layout.popup_info), null);
                popupWindow = new PopupWindow(popupView,RadioGroup.LayoutParams.WRAP_CONTENT,
                        RadioGroup.LayoutParams.WRAP_CONTENT);

                popupWindow.setOutsideTouchable(true);
                popupWindow.setFocusable(true);

                b_cerrar_popup = popupView.findViewById(R.id.id_cerrar);
                b_cerrar_popup.setOnClickListener(new Button.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }});

                popupWindow.showAsDropDown(ib_info, 50, 0);
                popupWindow.showAtLocation(ib_info, Gravity.CENTER, 0, 0);
            }
        });
        return view;
    }
}