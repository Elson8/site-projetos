
package vision;

/**
 *
 * @author elson
 */
public class FormulaSac {
      public static double calcFV(double pv, double i) {
        double fv = pv * (Math.pow((1 + i), 2));
        return fv;
    }

    public static double calcPMTSac(double juros, double amort) {
        double PMT = juros + amort;
        return PMT;
    }

    public static double CalcAmortSac(double fv, double n) {
        double amort = fv / n;
        return amort;
    }
}
