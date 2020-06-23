
package vision;

import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import vision.Calculos.*;
import static vision.Calculos.calcJurosPriceSac;
import static vision.Calculos.calcPMT;
import static vision.Calculos.calcSF;
import static vision.FormulaPrice.calcAmort;
import static vision.FormulaSac.CalcAmortSac;
import static vision.FormulaSac.calcFV;
import static vision.FormulaSac.calcPMTSac;
public class Tabelas {
     public void montaTabelaPrice(double pv, double i, double n) {
        i = i / 100;
        double pmt = calcPMT(pv, i, n);
        double si = 0, juros = 0, amort = 0, sf = 0;
        
        for (int c = 1; c <= n; c++) {
            if (c == 1) {
                si = pv;
                juros = calcJurosPriceSac(si, i);
                amort = calcAmort(pmt, juros);
                sf = calcSF(si, amort);
                //System.out.println("num: " + c + " si: " + si + " juros: " + juros + " amort: " + amort + " pmp: " + pmt + " sf: " + sf);
                salvarDados(c, si, juros, amort, pmt, sf);
            } else {
                si = sf;
                juros = calcJurosPriceSac(si, i);
                amort = calcAmort(pmt, juros);
                sf = calcSF(si, amort);
                salvarDados(c, si, juros, amort, pmt, sf);
            }
        }
    }
    
    public void montaTabelaSac(double pv, double i, double n) {
        i = i / 100;
        
        double fv = calcFV(pv, i);
        double amort = CalcAmortSac(fv, n);
        double si = 0, juros = 0, pmt = 0, sf = 0;
        
        for (int c = 1; c <= n; c++) {
            if (c == 1) {
                si = fv;
                juros = calcJurosPriceSac(si, i);
                pmt = calcPMTSac(juros, amort);
                sf = calcSF(si, amort);
                salvarDados(c, si, juros, amort, pmt, sf);
            } else {
                si = sf;
                juros = calcJurosPriceSac(si, i);
                pmt = calcPMTSac(juros, amort);
                sf = calcSF(si, amort);
                salvarDados(c, si, juros, amort, pmt, sf);
            }
        }
       
    }
    
    ArrayList<DadosTabela> linhas = new ArrayList<>();
    
    public void salvarDados(int num, double si, double juros, double amort, double pmt, double sf) {
        
        DadosTabela tab = new DadosTabela();
        
        tab.setNum(num);
        tab.setSi(si);
        tab.setJuros(juros);
        tab.setAmort(amort);
        tab.setPmt(pmt);
        tab.setSf(sf);
        
        linhas.add(tab);
    }
    
    public double[] imprimirTabela(JTable Tabela) {
        
        DefaultTableModel tabela = (DefaultTableModel) Tabela.getModel();
        tabela.setNumRows(0);
        
        
        
       double totalJuros = 0.0;
       double totalPmt = 0.0;
        double totalJP = 0.0;
       double totalPmtP = 0.0;
        
        for (int i = 0; i < linhas.size(); i++) {
            DadosTabela tab = linhas.get(i);
            totalJuros = totalJuros + tab.getJuros();
            totalPmt = totalPmt + tab.getPmt();
            totalJP = totalJP + tab.getJuros();
            totalPmtP = totalPmtP + tab.getPmt();
            tabela.addRow(new String[]{Integer.toString(tab.getNum()), String.format("%1.2f", tab.getSi()), String.format("%1.2f", tab.getJuros()),
                String.format("%1.2f", tab.getAmort()), String.format("%1.2f", tab.getPmt()), String.format("%1.2f", tab.getSf())});
        }
        
        return new double[]{totalJuros, totalPmt, totalJP, totalPmtP};
   
      
    }
}
