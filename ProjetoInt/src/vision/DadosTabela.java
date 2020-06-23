
package vision;

import java.math.RoundingMode;
import java.text.DecimalFormat;
/**
 *
 * @author elson
 */
public class DadosTabela {
    
     private double si, juros, amort, pmt, sf;
    private int num;

    public void setNum(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }
    
    public double getSi() {
        return si;
    }

    public void setSi(double si) {
        this.si = si;
    }

    public double getJuros() {
        return juros;
    }

    public void setJuros(double juros) {
        this.juros = juros;
    }

    public double getAmort() {
        return amort;
    }

    public void setAmort(double amort) {
        this.amort = amort;
    }

    public double getPmt() {
        return pmt;
    }

    public void setPmt(double pmt) {
        this.pmt = pmt;
    }

    public double getSf() {
        return sf;
    }

    public void setSf(double sf) {
        this.sf = sf;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.00"); //limita a 4 casas após a vírgula
        df.setRoundingMode(RoundingMode.UP); //arredonda o valor acima
        return "N: " + df.format(num) +  " - SI: " + df.format(si) + " - Juros: " + df.format(juros) + " - Amort: " + df.format(amort) + " - PMT: " + df.format(pmt) + " - SF: " + df.format(sf);
    }
    
}
