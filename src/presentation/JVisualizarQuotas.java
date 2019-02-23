/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import business.AlunoNaoExisteException;
import business.Quota;
import java.util.Observable;
import java.util.Observer;
import javax.swing.DefaultListModel;
import business.QuotaJaEstaPagaException;
import business.QuotaNaoExisteException;
import javax.swing.JOptionPane;

/**
 *
 * @author ricardopetronilho
 */
public class JVisualizarQuotas extends javax.swing.JFrame implements Observer {
    
    private JSGQ parent; // API pública para conseguir alterar o singleton
    private String numero;
        
    /**
     * Creates new form JVisualizarQuotas
     * @param parent API utilizada para modifica o modelo (singleton)
     * @param cod código do Aluno selecionado
     */
    public JVisualizarQuotas(JSGQ parent, String numero) {
        initComponents();
        
        this.parent = parent;
        this.numero = numero;
        this.numeroLabel.setText("Número de Sócio: "+this.numero);
        try {
            this.parent.addObserverToAluno(this, this.numero); // esta janela vai observar o Aluno selecionado
        } catch (AlunoNaoExisteException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro interno!", "Erro", JOptionPane.ERROR_MESSAGE);           
        }
        
        this.updateJList();
    }
    
    /**
     * Atualiza a apresentação da lista de Quotas.
     */
    public void updateJList() {
        DefaultListModel model = new DefaultListModel(); // abstracao visual dos dados
        String estado;
        try {
            for(Quota q: this.parent.getQuotas(this.numero).values()){
                if(q.isPaga())estado = "sim"; else estado = "não";
                model.addElement("Id: "+q.getId()+" | "+"valor: "+q.getValor()+"€"+" | "+"data da quota: "+q.getDataInicio()+" | "+"data limite: "+q.getDataFim()+" | "+"pago: "+estado);
            }
        } catch (AlunoNaoExisteException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro interno!", "Erro", JOptionPane.ERROR_MESSAGE);           
        }
        this.listaQuotasJList.setModel(model);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        listaQuotasScrollPane = new javax.swing.JScrollPane();
        listaQuotasJList = new javax.swing.JList<>();
        registarQuotaButton = new javax.swing.JButton();
        pagarButton = new javax.swing.JButton();
        numeroLabel = new javax.swing.JLabel();

        setTitle("Visualizar Quota");

        listaQuotasScrollPane.setViewportView(listaQuotasJList);

        registarQuotaButton.setText("Registar Quota");
        registarQuotaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registarQuotaButtonActionPerformed(evt);
            }
        });

        pagarButton.setText("Pagar");
        pagarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pagarButtonActionPerformed(evt);
            }
        });

        numeroLabel.setText("Número de Sócio:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(numeroLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                .addComponent(pagarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(registarQuotaButton)
                .addGap(15, 15, 15))
            .addComponent(listaQuotasScrollPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numeroLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pagarButton)
                    .addComponent(registarQuotaButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(listaQuotasScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void registarQuotaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registarQuotaButtonActionPerformed
        new JRegistarQuota(this.parent, this.numero).setVisible(true);
    }//GEN-LAST:event_registarQuotaButtonActionPerformed

    private void pagarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pagarButtonActionPerformed

        for(String info: this.listaQuotasJList.getSelectedValuesList()) {
            String id = info.split(" | ")[1];
            try {
                this.parent.pagarQuota(id, this.numero);
            } catch (QuotaJaEstaPagaException e) {
                JOptionPane.showMessageDialog(this, "A quota - " + id + " - já foi paga!", "Aviso", JOptionPane.INFORMATION_MESSAGE);          
            } catch (AlunoNaoExisteException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erro interno!", "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (QuotaNaoExisteException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erro interno!", "Erro", JOptionPane.ERROR_MESSAGE);           
            }
        }
    }//GEN-LAST:event_pagarButtonActionPerformed
    
    @Override
    public void update(Observable o, Object arg) {
        this.updateJList();
    }    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> listaQuotasJList;
    private javax.swing.JScrollPane listaQuotasScrollPane;
    private javax.swing.JLabel numeroLabel;
    private javax.swing.JButton pagarButton;
    private javax.swing.JButton registarQuotaButton;
    // End of variables declaration//GEN-END:variables
}
