
package vision;

/**
 *
 * @author elson
 */
public class CalcCons {
    private double Sl ;
    private double Vpo;
   

    public CalcCons() {
    }

    public CalcCons(double Sl, double Vpo) {
        this.Sl = Sl;
        this.Vpo = Vpo;
      
    }

     public static double calcCons(double Sl, double Vpo) {
        double Cons = (Sl - Vpo) * 0.3;
        return Cons;
    }



}