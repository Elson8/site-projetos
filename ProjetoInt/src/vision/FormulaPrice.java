
package vision;

public class FormulaPrice {
    
     public static double calcAmort(double pmt, double juros) {
        double amort = pmt - juros;
        return amort;
    }
    
}
