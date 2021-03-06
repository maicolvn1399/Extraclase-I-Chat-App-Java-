/*
 * Michael Valverde Navarro
 * Carné: 2020044189
 * Curso: Algoritmos y estructuras de datos I
 * 2 semestre 2020
 *
 */
package serverchat;

import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *Class that handles the GUI of the server, it shows a log of the connections and disconnections 
 * of the clients 
 * it also shows a message when the server is running correctly 
 * @author Michael Valverde 
 */
public class WindowServer extends javax.swing.JFrame {
    private final String DEFAULT_PORT = "10101";
    private final Server server;
    

    /**
     * Creates new form WindowServer
     */
    public WindowServer() {
        initComponents();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String port = getPort();
        server = new Server(port, this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        clients_txt_area = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        clients_txt_area.setColumns(20);
        clients_txt_area.setRows(5);
        clients_txt_area.setText("Server's Log");
        jScrollPane1.setViewportView(clients_txt_area);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(WindowServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WindowServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WindowServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WindowServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WindowServer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea clients_txt_area;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    /**
     * Method that adds a line of text to log 
     * @param text
     */
    void addLog(String text){
        clients_txt_area.append(text);
    }//End addLog()
    
    /**
     * This method opens a new window so the user can enter the port that wants to use 
     * so the server can use the same port 
     */
    private String getPort(){
        String p = DEFAULT_PORT;
        JTextField port = new JTextField(20);
        port.setText(p);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2,1));
        panel.add(new JLabel("Connection's port"));
        panel.add(port);
        int result = JOptionPane.showConfirmDialog(null, panel,"Communication's settings",JOptionPane.OK_CANCEL_OPTION);
        if(result == JOptionPane.OK_OPTION){
            p = port.getText();
        }else{
            System.exit(0);
        }//End else 
        return p;

    }//End getPort()
    
    /**
     * Method that adds a confirmation message to the log 
     * when the server is running correctly
     */
    void addInitializedServer(){
        clients_txt_area.setText("Initializing the server........[OK]..");
    }//End addInitializedServer()
    
}
