/*
 * Copyright 2017 José A. Pacheco Ondoño - joanpaon@gmail.com.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.japo.java.forms;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.Properties;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import org.japo.java.events.AEM;
import org.japo.java.libraries.UtilesSwing;

/**
 *
 * @author José A. Pacheco Ondoño - joanpaon@gmail.com
 */
public class GUI extends JFrame {

    // Propiedades App
    public static final String PRP_LOOK_AND_FEEL = "look_and_feel";
    public static final String PRP_FAVICON = "favicon";

    // Valores por Defecto
    public static final String DEF_LOOK_AND_FEEL = UtilesSwing.LNF_NIMBUS;
    public static final String DEF_FAVICON = "img/favicon.png";

    // Lista de Autonomias
    private final String AUTONOMIAS[] = {"Asturias", "Cantabria",
        "Castilla-León", "Castilla-La mancha", "Extremadura",
        "Comunidad Valenciana", "Andalucía", "Galicia",
        "Cataluña", "País Vasco", "Rioja", "Comunidad de Madrid",
        "Aragón", "Murcia", "Baleares", "Canarias"};

    // Referencias
    private Properties prp;
    private JList lstOpciones;
    private JList lstSeleccion;
    private JButton btnDerecha;
    private JButton btnTodoDerecha;
    private JButton btnIzquierda;
    private JButton btnTodoIzquierda;

    public GUI(Properties prp) {
        // Inicialización Anterior
        initBefore(prp);

        // Creación Interfaz
        initComponents();

        // Inicializacion Posterior
        initAfter();
    }

    // Construcción del IGU
    private void initComponents() {
        // Lista de opciones
        lstOpciones = new JList(AUTONOMIAS);

        // Panel de opciones
        JScrollPane pnlOpciones = new JScrollPane(lstOpciones);
        pnlOpciones.setPreferredSize(new Dimension(190, 200));
        pnlOpciones.setBorder(new CompoundBorder(
                new TitledBorder(
                        new EtchedBorder(EtchedBorder.LOWERED),
                        "Opciones"),
                new EmptyBorder(10, 10, 10, 10)));

        // Modelo de la lista de selección
        DefaultListModel dlmSeleccion = new DefaultListModel();

        // Lista de selección
        lstSeleccion = new JList(dlmSeleccion);

        // Panel de selección
        JScrollPane pnlSeleccion = new JScrollPane(lstSeleccion);
        pnlSeleccion.setPreferredSize(new Dimension(190, 200));
        pnlSeleccion.setBorder(new CompoundBorder(
                new TitledBorder(
                        new EtchedBorder(EtchedBorder.LOWERED),
                        "Selección"),
                new EmptyBorder(10, 10, 10, 10)));

        // Botón Derecha
        btnDerecha = new JButton(">");
        btnDerecha.setMaximumSize(new Dimension(150, 30));
        btnDerecha.addActionListener(new AEM(this));

        // Botón TodoDerecha
        btnTodoDerecha = new JButton(">>");
        btnTodoDerecha.setMaximumSize(new Dimension(150, 30));
        btnTodoDerecha.addActionListener(new AEM(this));

        // Botón Izquierda
        btnIzquierda = new JButton("<");
        btnIzquierda.setMaximumSize(new Dimension(150, 30));
        btnIzquierda.addActionListener(new AEM(this));

        // Botón TodoIzquierda
        btnTodoIzquierda = new JButton("<<");
        btnTodoIzquierda.setMaximumSize(new Dimension(150, 30));
        btnTodoIzquierda.addActionListener(new AEM(this));

        // Panel de controles
        Box pnlControles = new Box(BoxLayout.Y_AXIS);
        pnlControles.add(Box.createVerticalGlue());
        pnlControles.add(btnDerecha);
        pnlControles.add(Box.createVerticalStrut(10));
        pnlControles.add(btnTodoDerecha);
        pnlControles.add(Box.createVerticalStrut(10));
        pnlControles.add(btnTodoIzquierda);
        pnlControles.add(Box.createVerticalStrut(10));
        pnlControles.add(btnIzquierda);
        pnlControles.add(Box.createVerticalGlue());
        pnlControles.setBorder(new CompoundBorder(
                new TitledBorder(
                        new EtchedBorder(EtchedBorder.LOWERED),
                        "Controles"),
                new EmptyBorder(10, 10, 10, 10)));

        // Panel principal
        JPanel pnlPpal = new JPanel(new BorderLayout());
        pnlPpal.add(pnlOpciones, BorderLayout.WEST);
        pnlPpal.add(pnlControles, BorderLayout.CENTER);
        pnlPpal.add(pnlSeleccion, BorderLayout.EAST);
        pnlPpal.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Ventana
        setContentPane(pnlPpal);
        setTitle("Swing Manual #13");
        setResizable(false);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Inicialización Anterior    
    private void initBefore(Properties prp) {
        // Memorizar Referencia
        this.prp = prp;

        // Establecer LnF
        UtilesSwing.establecerLnF(prp.getProperty(PRP_LOOK_AND_FEEL, DEF_LOOK_AND_FEEL));
    }

    // Inicialización Posterior
    private void initAfter() {
        // Establecer Favicon
        UtilesSwing.establecerFavicon(this, prp.getProperty(PRP_FAVICON, DEF_FAVICON));
    }

    public void procesarAccion(ActionEvent ae) {
        if (ae.getSource().equals(btnDerecha)) {
            ((DefaultListModel) (lstSeleccion.getModel())).removeAllElements();
            Object seleccion[] = lstOpciones.getSelectedValuesList().toArray();
            for (int i = 0; i < seleccion.length; i++) {
                ((DefaultListModel) (lstSeleccion.getModel())).addElement(seleccion[i]);
            }
            lstOpciones.clearSelection();
            lstSeleccion.clearSelection();
        } else if (ae.getSource().equals(btnTodoDerecha)) {
            ((DefaultListModel) (lstSeleccion.getModel())).removeAllElements();
            for (int i = 0; i < lstOpciones.getModel().getSize(); i++) {
                ((DefaultListModel) (lstSeleccion.getModel())).addElement(
                        lstOpciones.getModel().getElementAt(i));
            }
            lstOpciones.clearSelection();
            lstSeleccion.clearSelection();
        } else if (ae.getSource().equals(btnTodoIzquierda)) {
            ((DefaultListModel) (lstSeleccion.getModel())).removeAllElements();
            lstOpciones.clearSelection();
            lstSeleccion.clearSelection();
        } else if (ae.getSource().equals(btnIzquierda)) {
            Object seleccion[] = lstSeleccion.getSelectedValuesList().toArray();
            for (int i = 0; i < seleccion.length; i++) {
                ((DefaultListModel) (lstSeleccion.getModel())).removeElementAt(i);
            }
            lstOpciones.clearSelection();
            lstSeleccion.clearSelection();
        }
    }
}
