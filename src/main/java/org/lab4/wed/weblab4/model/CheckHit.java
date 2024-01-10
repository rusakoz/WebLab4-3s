package org.lab4.wed.weblab4.model;

public class CheckHit {
    public static boolean checkHit(double X, double Y, double R){
        return (Math.abs(X) <= R/2 && Math.abs(Y) <= R && X >= 0 &&  Y <= 0) ||
                (Math.pow(X, 2) + Math.pow(Y, 2) <= Math.pow(R, 2) && X <= 0 && Y <= 0) ||
                (Math.abs(Y) + Math.abs(X) <= R && X >= 0 && Y >= 0);
    }
}
