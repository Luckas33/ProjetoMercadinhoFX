package com.programa.projetomercadofx.controllerUtil;

public class IsCharacter {
    public static boolean isChar(String str) {//Método para certificar que na String só existem caracteres
        if (str == null || str.isEmpty()) {
            return false;
        }

        // Verifica se todos os caracteres não são dígitos
        for (char c : str.toCharArray()) {
            if (!Character.isAlphabetic(c)) {
                return false;
            }
        }

        return true;
    }
}
