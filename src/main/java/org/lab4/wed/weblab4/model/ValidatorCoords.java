package org.lab4.wed.weblab4.model;

public class ValidatorCoords {
    public static boolean validate(double x, double y, double r){
        return x >= -3 && x <= 5 && y >= -3 && y <= 3 && r >= -3 && r <= 3;
    }
}
