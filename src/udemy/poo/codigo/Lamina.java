/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udemy.poo.codigo;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Sou Akiyama
 */
public class Lamina extends JPanel implements ActionListener {
    
    private JPanel panel = new JPanel();
    private JButton botonGuardar = new JButton("Guardar");
    private JButton botonLeer = new JButton("Leer");
    private JLabel nombre = new JLabel("Nombre:");
    private JTextField cajaNombre = new JTextField(20);
    private JLabel apellidoPaterno = new JLabel("Apellido Paterno:");
    private JTextField cajaApellidoP = new JTextField(20);
    private JLabel apellidoMaterno = new JLabel("Apellido Materno:");
    private JTextField cajaApellidoM = new JTextField(20);
    private JLabel edad = new JLabel("Edad:");
    private JTextField cajaEdad = new JTextField(20);
    private JTextArea areaTexto = new JTextArea(10, 40);
    private JScrollPane scroll;

    public Lamina() {
        // Añadir el Scroll al área de Texto
        this.scroll = new JScrollPane(this.areaTexto);
        
        // Agregar el evento al oyente del evento botón
        this.botonGuardar.addActionListener(this);
        this.botonLeer.addActionListener(this);
        
        JPanel bordePan = new JPanel(new GridLayout(5, 2));
        
        // Agregar los componentes al Panel
        nombre.setToolTipText("Ej. Luan Emmanuel");
        bordePan.add(nombre);
        bordePan.add(cajaNombre);
        
        apellidoPaterno.setToolTipText("Ej. Cruz");
        bordePan.add(apellidoPaterno);
        bordePan.add(cajaApellidoP);
        
        apellidoMaterno.setToolTipText("Ej. Monrroy");
        bordePan.add(apellidoMaterno);
        bordePan.add(cajaApellidoM);
        
        edad.setToolTipText("Ej. 25");
        bordePan.add(edad);
        bordePan.add(cajaEdad);
        
        bordePan.add(botonGuardar);
        bordePan.add(botonLeer);
        panel.add(bordePan);
        
        // Creando el BorderLayout
        JPanel bordePanel = new JPanel(new BorderLayout());
        bordePanel.setBorder(new TitledBorder("Datos personales"));
        bordePanel.add(panel, BorderLayout.CENTER);
        
        this.add(bordePanel);
        this.add(scroll);
    }
    
    public void escribirArchivo() {
        FileWriter fw = null;
        try {
            File f = new File(System.getProperty("user.dir") + "/Archivo");
            fw = new FileWriter(f, true);
            PrintWriter pw = new PrintWriter(fw);
            pw.append(this.cajaNombre.getText() + " " + this.cajaApellidoP.getText()
                    + " " + this.cajaApellidoM.getText() + " " + this.cajaEdad.getText() + "\n");
            pw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        
    }
    
    public void leerArchivo() {
        FileReader fr = null;
        try {
            File f = new File(System.getProperty("user.dir") + "/Archivo");
            fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String cad = "";

            while ((cad = br.readLine()) != null) {
                this.areaTexto.append(cad + "\n");
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                fr.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object origen = e.getSource();
        
        // Si el botón generó el evento...
        if (origen == this.botonGuardar) {
            // Escribir en el archivo
            escribirArchivo();
            
            // Limpiar cajas de texto
            this.cajaNombre.setText("");
            this.cajaApellidoP.setText("");
            this.cajaApellidoM.setText("");
            this.cajaEdad.setText("");
            
            // Transferir el el focus a la caja de texto
            this.botonGuardar.transferFocus();
        } else if (origen == this.botonLeer) {
            // Leer el archivo
            leerArchivo();
            
            // Transferir el focus
            this.botonLeer.transferFocus();
        }
    }
    
}
