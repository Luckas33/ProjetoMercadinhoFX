package com.programa.projetomercadofx.controllerUtil;

public class IsNumeric {

    public static boolean isNumeric(String str) {//Método para certificar que na String só existem números
        if (str == null || str.isEmpty()) {
            return false;
        }

        // Verifica se todos os caracteres são dígitos
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        // Verifica se o ID não começa com zero, a menos que seja apenas o zero
        if (str.length() > 1 && str.startsWith("0")) {
            return false;
        }

        return true;
    }
}
