package com.bryanrr.homework3.util;

import android.content.Context;
import android.widget.Toast;

public class Metodos {

    public void mensaje(String mensaje, Context context) {

        Toast toast1 =
                Toast.makeText(context.getApplicationContext(),
                        mensaje, Toast.LENGTH_SHORT);

        toast1.show();


    }
}
