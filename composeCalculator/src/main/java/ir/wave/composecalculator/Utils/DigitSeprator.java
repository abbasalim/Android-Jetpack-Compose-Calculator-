package ir.wave.composecalculator.Utils;

public class DigitSeprator {


    public static String seRaghmBandi(String Orgprice) {
        return NRaghmBandi(Orgprice, true, 3);
    }

    //if after Number is Persian Text manfiInEnd must true
    public static String seRaghmBandi(String Orgprice, boolean manfiInEnd) {
        return NRaghmBandi(Orgprice, manfiInEnd, 3);
    }

    public static String NRaghmBandi(String Orgprice, boolean manfiInEnd, int nRaghami) {
        String price;
        String Ashar = "";
        boolean cntManfi = false;
        if (Orgprice.contains("-")) {
            cntManfi = true;
            Orgprice = Orgprice.replace("-", "");
        }
        if (Orgprice.contains(".")) {
            price = Orgprice.substring(0, Orgprice.indexOf("."));
            Ashar = Orgprice.substring(Orgprice.indexOf("."));
        } else price = Orgprice;

        StringBuilder s = new StringBuilder(price);
        for (int i = s.length() - nRaghami; i > 0; i -= nRaghami) {
            s.insert(i, ",");
        }
        if (cntManfi)
            return manfiInEnd ? (s + Ashar + "-") : ("-" + s + Ashar);
        else
            return s + Ashar;
    }

    public static String AsharDasti(String mS) {
        String f = mS;
        if (mS.length() == 0 || !mS.contains(".") || (mS.contains(".") && mS.indexOf(".") == mS.length() - 1))
            return mS;
        else if (mS.length() - 1 - mS.indexOf(".") > 3)
            f = mS.substring(0, mS.indexOf(".") + 4);
        return f;
    }

}
