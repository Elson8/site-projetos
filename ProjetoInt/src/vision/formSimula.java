
package vision;

import dal.ConectaBd;
import java.awt.Dimension;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import static jdk.nashorn.internal.objects.NativeFunction.function;
import net.proteanit.sql.DbUtils;
import vision.Calculos.*;
import vision.CalcCons.*;
import static vision.CalcCons.calcCons;
/**
 *
 * @author elson
 */
public class formSimula extends javax.swing.JInternalFrame {
   
    Connection conecta;
    PreparedStatement pst;
    ResultSet rs;
    int Pv,A,I,J,PMT,Sd;
    

    public formSimula() throws ClassNotFoundException {
       initComponents();
        this.setLocation(20, 20);
       
        conecta = ConectaBd.conectabd();
        listarCliente();
    }
    
    public void listarCliente(){
        String sql = "SELECT *From cliente";
        try{
           pst = conecta.prepareStatement(sql);
           rs = pst.executeQuery();
           tblCalc.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(SQLException error){
            
            JOptionPane.showMessageDialog(null, error);
        }
    }
    public void buscarCliente(){
         String sql = "select *from cliente where Nome like ? ";
         
         try{
              pst = conecta.prepareStatement(sql);
              pst.setString(1, txtNomeBCalc.getText()+"%");
              rs = pst.executeQuery();
              tblCalc.setModel(DbUtils.resultSetToTableModel(rs));
             
         }
         catch(SQLException error){
            
            JOptionPane.showMessageDialog(null, error);
        }
    
    
    }
    
    
     public void mostrarDados(){
       
         int seleciona = tblCalc.getSelectedRow();
         txtIdCalc.setText(tblCalc.getModel().getValueAt(seleciona, 0).toString());
         txtNomeCalc.setText(tblCalc.getModel().getValueAt(seleciona, 1).toString());
         txtCpfCalc.setText(tblCalc.getModel().getValueAt(seleciona, 2).toString());
        
         txtSalarioCalc.setText(tblCalc.getModel().getValueAt(seleciona, 4).toString());
         txtValorOutCalc.setText(tblCalc.getModel().getValueAt(seleciona, 5).toString());
         txtIdcliente.setText(tblCalc.getModel().getValueAt(seleciona, 0).toString());
         
     }
    
   
   
     public void limparCampos(){
        txtIdCalc.setText("");
        txtNomeCalc.setText("");
        txtCpfCalc.setText("");
        txtSalarioCalc.setText("");
        txtValorOutCalc.setText("");
        txtValorMaxParc.setText("");
    
     }
     
      public void TelaSac () {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(formPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        initComponents();
        
        ((DefaultTableCellRenderer)tblSac.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        DefaultTableCellRenderer rendererDireita = new DefaultTableCellRenderer();
        rendererDireita.setHorizontalAlignment(SwingConstants.RIGHT);
        DefaultTableCellRenderer rendererEsquerda = new DefaultTableCellRenderer();
        rendererEsquerda.setHorizontalAlignment(SwingConstants.LEFT);

        DefaultTableCellRenderer rendererCentro = new DefaultTableCellRenderer();
        rendererCentro.setHorizontalAlignment(SwingConstants.CENTER);

        JTableHeader header = tblSac.getTableHeader();
        header.setPreferredSize(new Dimension(0, 20));   // define a largura do cabeçalho
        TableColumnModel modeloDaColuna = tblSac.getColumnModel();

        modeloDaColuna.getColumn(0).setCellRenderer(rendererCentro);
        modeloDaColuna.getColumn(1).setCellRenderer(rendererCentro);
        modeloDaColuna.getColumn(2).setCellRenderer(rendererCentro);
        modeloDaColuna.getColumn(3).setCellRenderer(rendererCentro);
        modeloDaColuna.getColumn(4).setCellRenderer(rendererCentro);
        modeloDaColuna.getColumn(5).setCellRenderer(rendererCentro);

        modeloDaColuna.getColumn(0).setMaxWidth(50);
        
     
    }
      
       public void TelaPrice() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(formPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        initComponents();
        
        ((DefaultTableCellRenderer)tblPrice.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        DefaultTableCellRenderer rendererDireita = new DefaultTableCellRenderer();
        rendererDireita.setHorizontalAlignment(SwingConstants.RIGHT);
        DefaultTableCellRenderer rendererEsquerda = new DefaultTableCellRenderer();
        rendererEsquerda.setHorizontalAlignment(SwingConstants.LEFT);

        DefaultTableCellRenderer rendererCentro = new DefaultTableCellRenderer();
        rendererCentro.setHorizontalAlignment(SwingConstants.CENTER);

        JTableHeader header = tblPrice.getTableHeader();
        header.setPreferredSize(new Dimension(0, 20));   // define a largura do cabeçalho
        TableColumnModel modeloDaColuna = tblPrice.getColumnModel();

        modeloDaColuna.getColumn(0).setCellRenderer(rendererCentro);
        modeloDaColuna.getColumn(1).setCellRenderer(rendererCentro);
        modeloDaColuna.getColumn(2).setCellRenderer(rendererCentro);
        modeloDaColuna.getColumn(3).setCellRenderer(rendererCentro);
        modeloDaColuna.getColumn(4).setCellRenderer(rendererCentro);
        modeloDaColuna.getColumn(5).setCellRenderer(rendererCentro);

        modeloDaColuna.getColumn(0).setMaxWidth(50);
    }
       
        public void cadastrarEmprestimo(){
        String sql = "Insert into emprestimo (idcliente, valoremprestimo, taxajuros, prazos, valormaximoparcela, valorpmt, sistemaamortizacao ) values (?, ?, ?, ?, ?, ?, ?) ";
        try{
            pst = conecta.prepareStatement(sql);
            pst.setString(1, txtIdcliente.getText());
            pst.setString(2, txtValorEmpContr.getText());
            pst.setString(3, txtTaxaCont.getText());
            pst.setString(4, txtPrazoCont.getText());
            pst.setString(5, txtValorMaxCont.getText());
            pst.setString(6, txtValorPmtCont.getText());
             pst.setString(7, ""+comboAmort.getSelectedItem());
            
            pst.execute();
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!","Castrado com sucesso!",JOptionPane.INFORMATION_MESSAGE);
            listarCliente();
        }
        catch(SQLException error){
            
            JOptionPane.showMessageDialog(null, error);
        }
     
        }
        
         public void limparTudo(){
            txtIdcliente.setText("");
            txtValorEmpContr.setText("");
            txtTaxaCont.setText("");
            txtPrazo.setText("");
            txtPmtAPrice.setText("");
            txtPmtASac.setText("");
            txtValorMaxCont.setText("");
            txtValorPmtCont.setText("");
            txtValorIni.setText("");
            txttaxa.setText("");
            txtPrazo.setText("");
            txtJAPrice.setText("");
            txtJASac.setText("");
            
            
         }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        butaobuscarCalc = new javax.swing.JButton();
        txtNomeBCalc = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCalc = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtIdCalc = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNomeCalc = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtCpfCalc = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtSalarioCalc = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtValorOutCalc = new javax.swing.JTextField();
        butaoCalcVMP = new javax.swing.JButton();
        butaoLimpa = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtValorMaxParc = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtValorIni = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txttaxa = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtPrazo = new javax.swing.JTextField();
        butaoSimulacao = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSac = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblPrice = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        txtJASac = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtPmtASac = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtJAPrice = new javax.swing.JTextField();
        txtPmtAPrice = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        txtIdcliente = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtValorEmpContr = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtTaxaCont = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtPrazoCont = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtValorMaxCont = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtValorPmtCont = new javax.swing.JTextField();
        comboAmort = new javax.swing.JComboBox();
        jLabel23 = new javax.swing.JLabel();
        butaoContrata = new javax.swing.JButton();
        butaoLimpartudo = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        setClosable(true);
        setIconifiable(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        butaobuscarCalc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/imagem/Icones/magnifier.png"))); // NOI18N
        butaobuscarCalc.setText("BUSCAR");
        butaobuscarCalc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butaobuscarCalcActionPerformed(evt);
            }
        });
        butaobuscarCalc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                butaobuscarCalcKeyReleased(evt);
            }
        });

        txtNomeBCalc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeBCalcActionPerformed(evt);
            }
        });
        txtNomeBCalc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNomeBCalcKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(butaobuscarCalc)
                .addGap(18, 18, 18)
                .addComponent(txtNomeBCalc, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(butaobuscarCalc)
                    .addComponent(txtNomeBCalc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblCalc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Nome", "CPF", "Situação", "Salário", "Valor Outras Mensalidades"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblCalc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCalcMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCalc);

        jLabel1.setText("Cliente");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 4, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setText("Id");

        txtIdCalc.setForeground(new java.awt.Color(255, 255, 255));
        txtIdCalc.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtIdCalc.setEnabled(false);

        jLabel3.setText("Nome");

        txtNomeCalc.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtNomeCalc.setEnabled(false);

        jLabel4.setText("CPF");

        txtCpfCalc.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtCpfCalc.setEnabled(false);

        jLabel6.setText("Salário");

        txtSalarioCalc.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtSalarioCalc.setEnabled(false);
        txtSalarioCalc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSalarioCalcActionPerformed(evt);
            }
        });

        jLabel7.setText("Valor Outras Mensal.");

        txtValorOutCalc.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtValorOutCalc.setEnabled(false);

        butaoCalcVMP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/imagem/Icones/calculator.png"))); // NOI18N
        butaoCalcVMP.setText("CALCULAR");
        butaoCalcVMP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butaoCalcVMPActionPerformed(evt);
            }
        });

        butaoLimpa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/imagem/Icones/application.png"))); // NOI18N
        butaoLimpa.setText("LIMPAR");
        butaoLimpa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butaoLimpaActionPerformed(evt);
            }
        });

        jLabel9.setText("VALOR MÁXIMO DA PARCELA");

        txtValorMaxParc.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtValorMaxParc.setEnabled(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(txtIdCalc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNomeCalc, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(60, 60, 60)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtValorOutCalc, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(txtCpfCalc, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel6))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtSalarioCalc, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabel9))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtValorMaxParc, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(butaoCalcVMP))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(butaoLimpa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIdCalc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNomeCalc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtValorOutCalc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(butaoCalcVMP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCpfCalc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSalarioCalc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(butaoLimpa)
                    .addComponent(txtValorMaxParc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel8.setText("VALOR DO EMPRESTIMO");

        txtValorIni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtValorIniActionPerformed(evt);
            }
        });

        jLabel10.setText("TAXA JUROS");

        jLabel11.setText("PRAZOS");

        txtPrazo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrazoActionPerformed(evt);
            }
        });

        butaoSimulacao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/imagem/Icones/arrow_rotate_anticlockwise.png"))); // NOI18N
        butaoSimulacao.setText("SIMULAÇÃO");
        butaoSimulacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butaoSimulacaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtValorIni, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(58, 58, 58)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txttaxa, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(52, 52, 52)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtPrazo, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(butaoSimulacao))
                    .addComponent(jLabel11))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel10)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(butaoSimulacao)
                    .addComponent(txtPrazo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txttaxa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtValorIni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel5.setText("TABELA SAC");

        tblSac.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Período", "Saldo Inicial", "Juros", "Amortização", "PMT", "Saldo Devedor"
            }
        ));
        jScrollPane3.setViewportView(tblSac);

        jLabel12.setText("TABELA PRICE");

        tblPrice.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Período", "Saldo Inicial", "Juros", "Amortização", "PMT", "Saldo Devedor"
            }
        ));
        jScrollPane4.setViewportView(tblPrice);

        jLabel13.setText("JUROS ACUMULADOS");

        jLabel14.setText("PMT ACUMULADO");

        jLabel15.setText("JUROS ACUMULADOS");

        jLabel16.setText("PMT ACUMULADO");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 623, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane3)
                        .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(txtJAPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(51, 51, 51)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(txtPmtAPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(txtJASac, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(48, 48, 48)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(txtPmtASac, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addGap(4, 4, 4)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtJASac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPmtASac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtJAPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPmtAPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        txtPmtASac.getAccessibleContext().setAccessibleName("");
        txtPmtAPrice.getAccessibleContext().setAccessibleName("");

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel17.setText("Id CLIENTE");

        txtIdcliente.setEditable(false);
        txtIdcliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtIdclienteMouseClicked(evt);
            }
        });
        txtIdcliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdclienteActionPerformed(evt);
            }
        });

        jLabel18.setText("VALOR DO EMPRESTIMO");

        jLabel19.setText("TAXA JUROS");

        jLabel20.setText("PRAZOS");

        jLabel21.setText("VALOR MÁXINO DA PARCELA");

        txtValorMaxCont.setEditable(false);

        jLabel22.setText("VALOR DA PMT");

        comboAmort.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SAC", "PRICE" }));

        jLabel23.setText("SISTEMA DE AMORTIZAÇÃO");

        butaoContrata.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/imagem/Icones/accept.png"))); // NOI18N
        butaoContrata.setText("CONTRTAR EMPRESTIMO");
        butaoContrata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butaoContrataActionPerformed(evt);
            }
        });

        butaoLimpartudo.setText("LIMPAR");
        butaoLimpartudo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butaoLimpartudoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(txtIdcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtValorEmpContr, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTaxaCont, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(109, 109, 109)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel20)
                            .addComponent(txtPrazoCont, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(105, 105, 105)
                                .addComponent(jLabel21)
                                .addGap(77, 77, 77))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtValorMaxCont, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(88, 88, 88)))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtValorPmtCont, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(116, 116, 116)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboAmort, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(butaoContrata, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(butaoLimpartudo)))
                .addGap(61, 61, 61))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21)
                    .addComponent(jLabel23)
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIdcliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtValorEmpContr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTaxaCont, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrazoCont, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtValorMaxCont, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtValorPmtCont, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboAmort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(butaoContrata, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(butaoLimpartudo, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel24.setText("CONTRATO DE EMPRESTIMOS");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel24)
                .addGap(552, 552, 552))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .addGap(1, 1, 1)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNomeBCalcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeBCalcActionPerformed
     
    }//GEN-LAST:event_txtNomeBCalcActionPerformed
    
    private void butaobuscarCalcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butaobuscarCalcActionPerformed
      
    }//GEN-LAST:event_butaobuscarCalcActionPerformed

    private void tblCalcMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCalcMouseClicked
        mostrarDados();
    }//GEN-LAST:event_tblCalcMouseClicked

    private void butaoLimpaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butaoLimpaActionPerformed
        limparCampos();
    }//GEN-LAST:event_butaoLimpaActionPerformed

    private void butaobuscarCalcKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_butaobuscarCalcKeyReleased
         buscarCliente();
    }//GEN-LAST:event_butaobuscarCalcKeyReleased

    private void butaoSimulacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butaoSimulacaoActionPerformed
      if (!txtValorIni.getText().equals("") && !txttaxa.getText().equals("") && !txtPrazo.getText().equals("")) {
            Tabelas ac = new Tabelas();
            ac.montaTabelaSac(Double.parseDouble(txtValorIni.getText().replace(',', '.')),Double.parseDouble(txttaxa.getText().replace(',', '.')), Double.parseDouble(txtPrazo.getText().replace(',', '.')));
          double[] valoresImpressao = ac.imprimirTabela(tblSac);
          double[] valoresImpre = ac.imprimirTabela(tblSac);
          this.txtJASac.setText(String.format("%1.2f", valoresImpressao[0]));
          this.txtPmtASac.setText(String.format("%1.2f",valoresImpre[1]));
         
        }
        else{
            JOptionPane.showMessageDialog(this, "Digite todos os valores", "Alerta!", 2);
        }
      
       if (!txtValorIni.getText().equals("") && !txttaxa.getText().equals("") && !txtPrazo.getText().equals("")) {
            Tabelas ac = new Tabelas();       
            ac.montaTabelaPrice(Double.parseDouble(txtValorIni.getText().replace(',', '.')),Double.parseDouble(txttaxa.getText().replace(',', '.')), Double.parseDouble(txtPrazo.getText().replace(',', '.')));      
            double[] valoresImpressao =  ac.imprimirTabela(tblPrice); 
            double[] valoresImpre =  ac.imprimirTabela(tblPrice); 
            this.txtJAPrice.setText(String.format("%1.2f", valoresImpressao[2]));
            this.txtPmtAPrice.setText(String.format("%1.2f",valoresImpre[3]));
        }
        else{
            JOptionPane.showMessageDialog(this, "Digite todos os valores", "Alerta!", 2);
        }
      
    }//GEN-LAST:event_butaoSimulacaoActionPerformed

    private void txtPrazoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrazoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrazoActionPerformed

    private void txtSalarioCalcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSalarioCalcActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSalarioCalcActionPerformed

    private void txtValorIniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValorIniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorIniActionPerformed

    private void txtNomeBCalcKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomeBCalcKeyReleased
          buscarCliente();
    }//GEN-LAST:event_txtNomeBCalcKeyReleased

    private void butaoCalcVMPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butaoCalcVMPActionPerformed
        
        String textoS = txtSalarioCalc.getText();
        String textoV = txtValorOutCalc.getText();
     if(txtValorOutCalc.getText()==null || txtValorOutCalc.getText().trim().equals("")){
        double valorParcela = Double.parseDouble(textoS) * 0.30; //(Double.parseDouble(textoV)) ;
         txtValorMaxParc.setText(String.valueOf(valorParcela));
         txtValorMaxCont.setText(String.valueOf(valorParcela));
      }else{
           double valorParcela = Double.parseDouble(textoS) * 0.30 - (Double.parseDouble(textoV)) ;
      
        txtValorMaxParc.setText(String.valueOf(valorParcela));
        txtValorMaxCont.setText(String.valueOf(valorParcela));
      } 
    }//GEN-LAST:event_butaoCalcVMPActionPerformed

    private void txtIdclienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdclienteActionPerformed
       
    }//GEN-LAST:event_txtIdclienteActionPerformed

    private void txtIdclienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtIdclienteMouseClicked
         buscarCliente();
    }//GEN-LAST:event_txtIdclienteMouseClicked

    private void butaoContrataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butaoContrataActionPerformed
        cadastrarEmprestimo();
    }//GEN-LAST:event_butaoContrataActionPerformed

    private void butaoLimpartudoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butaoLimpartudoActionPerformed
       limparTudo();
    }//GEN-LAST:event_butaoLimpartudoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton butaoCalcVMP;
    private javax.swing.JButton butaoContrata;
    private javax.swing.JButton butaoLimpa;
    private javax.swing.JButton butaoLimpartudo;
    private javax.swing.JButton butaoSimulacao;
    private javax.swing.JButton butaobuscarCalc;
    private javax.swing.JComboBox comboAmort;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable tblCalc;
    private javax.swing.JTable tblPrice;
    private javax.swing.JTable tblSac;
    private javax.swing.JTextField txtCpfCalc;
    private javax.swing.JTextField txtIdCalc;
    private javax.swing.JTextField txtIdcliente;
    private javax.swing.JTextField txtJAPrice;
    private javax.swing.JTextField txtJASac;
    private javax.swing.JTextField txtNomeBCalc;
    private javax.swing.JTextField txtNomeCalc;
    private javax.swing.JTextField txtPmtAPrice;
    private javax.swing.JTextField txtPmtASac;
    private javax.swing.JTextField txtPrazo;
    private javax.swing.JTextField txtPrazoCont;
    private javax.swing.JTextField txtSalarioCalc;
    private javax.swing.JTextField txtTaxaCont;
    private javax.swing.JTextField txtValorEmpContr;
    private javax.swing.JTextField txtValorIni;
    private javax.swing.JTextField txtValorMaxCont;
    private javax.swing.JTextField txtValorMaxParc;
    private javax.swing.JTextField txtValorOutCalc;
    private javax.swing.JTextField txtValorPmtCont;
    private javax.swing.JTextField txttaxa;
    // End of variables declaration//GEN-END:variables


}
