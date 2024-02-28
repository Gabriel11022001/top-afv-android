package com.app.afv_top_android.utils;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;

public class SpinnerUtils<T> {

    private Context contexto;

    public SpinnerUtils(Context contexto) {
        this.contexto = contexto;
    }

    public void configurarSpinner(Spinner spinner, List<T> elementos) {
        ArrayAdapter<T> adapterSpinner = new ArrayAdapter<>(
                contexto,
                android.R.layout.simple_spinner_dropdown_item,
                elementos
        );
        spinner.setAdapter(adapterSpinner);
    }
}
