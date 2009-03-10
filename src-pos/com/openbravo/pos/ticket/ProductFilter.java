//    Openbravo POS is a point of sales application designed for touch screens.
//    Copyright (C) 2007 Openbravo, S.L.
//    http://sourceforge.net/projects/openbravopos
//
//    This program is free software; you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation; either version 2 of the License, or
//    (at your option) any later version.
//
//    This program is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with this program; if not, write to the Free Software
//    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

package com.openbravo.pos.ticket;

import com.openbravo.data.loader.SerializerWrite;
import com.openbravo.pos.forms.AppLocal;

import com.openbravo.pos.forms.AppView;
import java.awt.Component;
import java.util.List;
import com.openbravo.data.loader.QBFCompareEnum;
import com.openbravo.format.Formats;
import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ListQBFModelNumber;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.SentenceList;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.reports.ReportEditorCreator;

public class ProductFilter extends javax.swing.JPanel implements ReportEditorCreator {
    
    private SentenceList m_sentcat;
    private ComboBoxValModel m_CategoryModel;

    /** Creates new form JQBFProduct */
    public ProductFilter() {

        initComponents();
    }
    
    public void init(AppView app) {
         
        DataLogicSales dlSales = (DataLogicSales) app.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
       
        // El modelo de categorias
        m_sentcat = dlSales.getCategoriesList();
        m_CategoryModel = new ComboBoxValModel();          
         
        m_jCboName.setModel(new ListQBFModelNumber());
        m_jCboPriceBuy.setModel(new ListQBFModelNumber());
        m_jCboPriceSell.setModel(new ListQBFModelNumber());
    }
    
    public void activate() throws BasicException {

        List catlist = m_sentcat.list();
        catlist.add(0, null);
        m_CategoryModel = new ComboBoxValModel(catlist);
        m_jCategory.setModel(m_CategoryModel);
    }
    
    public SerializerWrite getSerializerWrite() {
        return new SerializerWriteBasic(
                new Datas[] {Datas.OBJECT, Datas.STRING, Datas.OBJECT, Datas.DOUBLE, Datas.OBJECT, Datas.DOUBLE, Datas.OBJECT, Datas.STRING, Datas.OBJECT, Datas.STRING});
    }

    public Component getComponent() {
        return this;
    }
   
    public Object createValue() throws BasicException {
        
        if (m_jBarcode.getText() == null || m_jBarcode.getText().equals("")) {
            // Filtro por formulario
            return new Object[] {
                m_jCboName.getSelectedItem(), m_jName.getText(),
                m_jCboPriceBuy.getSelectedItem(), Formats.CURRENCY.parseValue(m_jPriceBuy.getText()),           
                m_jCboPriceSell.getSelectedItem(), Formats.CURRENCY.parseValue(m_jPriceSell.getText()),
                m_CategoryModel.getSelectedKey() == null ? QBFCompareEnum.COMP_NONE : QBFCompareEnum.COMP_EQUALS, m_CategoryModel.getSelectedKey(),
                QBFCompareEnum.COMP_NONE, null         
            };
        } else {            
            // Filtro por codigo de barras.
            return new Object[] {
                QBFCompareEnum.COMP_NONE, null,
                QBFCompareEnum.COMP_NONE, null,
                QBFCompareEnum.COMP_NONE, null,
                QBFCompareEnum.COMP_NONE, null,
                QBFCompareEnum.COMP_RE, "%" + m_jBarcode.getText() + "%"
            };
        }
    } 
 
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        m_jBarcode = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        m_jCboName = new javax.swing.JComboBox();
        m_jName = new javax.swing.JTextField();
        m_jPriceBuy = new javax.swing.JTextField();
        m_jCboPriceBuy = new javax.swing.JComboBox();
        m_jCboPriceSell = new javax.swing.JComboBox();
        m_jPriceSell = new javax.swing.JTextField();
        m_jCategory = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS));

        setPreferredSize(new java.awt.Dimension(500, 200));
        jPanel2.setLayout(null);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(AppLocal.getIntString("label.bybarcode")));
        jPanel2.setPreferredSize(new java.awt.Dimension(500, 60));
        jLabel5.setText(AppLocal.getIntString("label.prodbarcode"));
        jPanel2.add(jLabel5);
        jLabel5.setBounds(20, 20, 130, 14);

        jPanel2.add(m_jBarcode);
        m_jBarcode.setBounds(150, 20, 150, 19);

        add(jPanel2);

        jPanel1.setLayout(null);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(AppLocal.getIntString("label.byform")));
        jPanel1.setPreferredSize(new java.awt.Dimension(500, 140));
        jLabel1.setText(AppLocal.getIntString("label.prodcategory"));
        jPanel1.add(jLabel1);
        jLabel1.setBounds(20, 110, 130, 14);

        jLabel3.setText(AppLocal.getIntString("label.prodpricesell"));
        jPanel1.add(jLabel3);
        jLabel3.setBounds(20, 80, 130, 14);

        jLabel4.setText(AppLocal.getIntString("label.prodpricebuy"));
        jPanel1.add(jLabel4);
        jLabel4.setBounds(20, 50, 130, 14);

        jPanel1.add(m_jCboName);
        m_jCboName.setBounds(150, 20, 150, 20);

        jPanel1.add(m_jName);
        m_jName.setBounds(310, 20, 180, 19);

        jPanel1.add(m_jPriceBuy);
        m_jPriceBuy.setBounds(310, 50, 60, 19);

        jPanel1.add(m_jCboPriceBuy);
        m_jCboPriceBuy.setBounds(150, 50, 150, 20);

        jPanel1.add(m_jCboPriceSell);
        m_jCboPriceSell.setBounds(150, 80, 150, 20);

        jPanel1.add(m_jPriceSell);
        m_jPriceSell.setBounds(310, 80, 60, 19);

        jPanel1.add(m_jCategory);
        m_jCategory.setBounds(150, 110, 220, 20);

        jLabel2.setText(AppLocal.getIntString("label.prodname"));
        jPanel1.add(jLabel2);
        jLabel2.setBounds(20, 20, 130, 14);

        add(jPanel1);

    }// </editor-fold>//GEN-END:initComponents
   
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField m_jBarcode;
    private javax.swing.JComboBox m_jCategory;
    private javax.swing.JComboBox m_jCboName;
    private javax.swing.JComboBox m_jCboPriceBuy;
    private javax.swing.JComboBox m_jCboPriceSell;
    private javax.swing.JTextField m_jName;
    private javax.swing.JTextField m_jPriceBuy;
    private javax.swing.JTextField m_jPriceSell;
    // End of variables declaration//GEN-END:variables
    
}