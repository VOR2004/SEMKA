package org.example.util;

import lombok.experimental.UtilityClass;

import java.util.InputMismatchException;
import java.util.Scanner;

@UtilityClass
public class ExcProcessing {
    public static boolean isNumber(String s) {
        if (s != null) {
            return s.matches("^[0-9]+$");
        }
        else {
            return false;
        }
    }

}
