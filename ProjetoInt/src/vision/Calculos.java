
package vision;

public class Calculos {
    private double PV;
    private double FV;
    private double PMT;
    private double I;
    private double N;
    private double M;
    private double Mj;
    private double Mp;
    public Calculos() {

    }

    public Calculos(double PV, double FV, double PMT, double I, double N, double M, double Mj, double Mp) {
        this.PV = PV;
        this.FV = FV;
        this.PMT = PMT;
        this.I = I;
        this.N = N;
        this.M = M;
        this.Mj = Mj;
        this.Mp = Mp;
    }

    public static double calcPMT(double pv, double i, double n) {
        double pmt = pv * ((i * (Math.pow((1 + i), n))) / ((Math.pow((1 + i), n) - 1)));
        return pmt;
    }

    public static double calcPV(double pmt, double i, double n) {
        double pv = pmt * (((Math.pow((1 + i), n) - 1)) / (i * (Math.pow((1 + i), n))));
       
        return pv;
    }

    public static double calcSF(double si, double amort) {
        double sf = si - amort;
        return sf;
    }

    public static double calcJurosPriceSac(double si, double i) {
        double juros = si * i;
        return juros;
    
    
    }
    
    public static double calcMontJ(double si, double i, double n) {
        double montanteJ = (si *(1/n+i) + (si/n)*(1+i))*(n/2);
        return montanteJ;
    
    
    }
}
