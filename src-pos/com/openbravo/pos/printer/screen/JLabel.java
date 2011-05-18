
package com.openbravo.pos.printer.screen;

import java.awt.*;
import com.openbravo.pos.printer.label.BasicLabel;
import java.util.Map;

class JLabel extends javax.swing.JPanel {
    
    private static final int H_GAP = 8;
    private static final int V_GAP = 8;
    private static final int COLUMNS = 42;
    private static final int LINEWIDTH = COLUMNS * 7;    
    
    private BasicLabel basict;
    private Map desktophints;
   
    public JLabel(BasicLabel t) {
        
        basict = t;
        desktophints = (Map) Toolkit.getDefaultToolkit().getDesktopProperty("awt.font.desktophints");
        initComponents();
    }
    
    protected void paintComponent(Graphics g) {
        paintBorder(g);
        
        Graphics2D g2d = (Graphics2D) g;

        if (desktophints != null) {
            g2d.addRenderingHints(desktophints);
        }
        
        Insets i = getInsets();
        g2d.setPaint(new GradientPaint(getWidth() - i.left - i.right - 100, getHeight() - i.top - i.bottom - 100, getBackground()
                                     , getWidth() - i.left - i.right, getHeight() - i.top - i.bottom, new Color(0xf0f0f0), true));
        g2d.fillRect(i.left, i.top, getWidth() - i.left - i.right, getHeight() - i.top - i.bottom);
        
        g.setColor(getForeground());
        basict.draw(g2d, i.left + H_GAP, i.top + V_GAP, LINEWIDTH);   
    }  
    
      
    public Dimension getPreferredSize() {
        Insets ins = getInsets();
        return new Dimension((int) (LINEWIDTH + 2 * H_GAP) + ins.left + ins.right
                           , (int) (basict.getHeight() + 2 * V_GAP) + ins.top + ins.bottom);
    }

    public Dimension getMaximumSize() {
        return getPreferredSize();
    }

    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    
}
