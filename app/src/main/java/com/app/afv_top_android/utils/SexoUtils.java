package com.app.afv_top_android.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SexoUtils {

    public static List<String> obterSexos() {
        List<String> sexos = new ArrayList<>();
        sexos.add("Masculino");
        sexos.add("Feminino");
        sexos.add("Outro");
        sexos.add("Não desejo informar");

        return Collections.unmodifiableList(sexos);
    }
}
