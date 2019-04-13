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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
public final class GUI extends JFrame {

    // Propiedades App
    public static final String PRP_FAVICON_RESOURCE = "favicon_resource";
    public static final String PRP_FONT_LIST_RESOURCE = "font_list_resource";
    public static final String PRP_FONT_BORDER_RESOURCE = "font_border_resource";
    public static final String PRP_FORM_HEIGHT = "form_height";
    public static final String PRP_FORM_TITLE = "form_title";
    public static final String PRP_FORM_WIDTH = "form_width";
    public static final String PRP_LOOK_AND_FEEL_PROFILE = "look_and_feel_profile";

    // Valores por Defecto
    public static final String DEF_FAVICON_RESOURCE = "img/favicon.png";
    public static final String DEF_FONT_LIST_SYSTEM_NAME = UtilesSwing.FONT_LUCIDA_SANS_NAME;
    public static final String DEF_FONT_LIST_FALLBACK_NAME = "Dialog";
    public static final String DEF_FONT_BORDER_SYSTEM_NAME = UtilesSwing.FONT_LUCIDA_BRIGHT_NAME;
    public static final String DEF_FONT_BORDER_FALLBACK_NAME = "Dialog";
    public static final String DEF_FORM_TITLE = "Swing Manual App";
    public static final int DEF_FORM_HEIGHT = 300;
    public static final int DEF_FORM_WIDTH = 500;
    public static final String DEF_LOOK_AND_FEEL_PROFILE = UtilesSwing.LNF_WINDOWS_PROFILE;

    // Lista de Autonomias
    private final String AUTONOMIAS[] = {"Asturias", "Cantabria",
        "Castilla-León", "Castilla-La mancha", "Extremadura",
        "Comunidad Valenciana", "Andalucía", "Galicia",
        "Cataluña", "País Vasco", "Rioja", "Comunidad de Madrid",
        "Aragón", "Murcia", "Baleares", "Canarias"};

    // Referencias
    private Properties prp;

    // Componentes
    private JScrollPane pnlOpciones;
    private JList lstOpciones;
    private JScrollPane pnlSeleccion;
    private JList lstSeleccion;
    private JButton btnDerecha;
    private JButton btnTodoDerecha;
    private JButton btnIzquierda;
    private JButton btnTodoIzquierda;
    private Box pnlControles;
    private JPanel pnlPpal;

    // Fuentes
    private Font fntList;
    private Font fntBorder;

    // Constructor
    public GUI(Properties prp) {
        // Conectar Referencia
        this.prp = prp;

        // Inicialización Anterior
        initBefore();

        // Creación Interfaz
        initComponents();

        // Inicializacion Posterior
        initAfter();
    }

    // Construcción del IGU
    private void initComponents() {
        // Fuentes
        fntList = UtilesSwing.generarFuenteRecurso(
                prp.getProperty(PRP_FONT_LIST_RESOURCE),
                DEF_FONT_LIST_SYSTEM_NAME,
                DEF_FONT_LIST_FALLBACK_NAME);
        fntBorder = UtilesSwing.generarFuenteRecurso(
                prp.getProperty(PRP_FONT_BORDER_RESOURCE),
                DEF_FONT_BORDER_SYSTEM_NAME,
                DEF_FONT_BORDER_FALLBACK_NAME);

        // Bordes
        CompoundBorder brdOpciones = new CompoundBorder(
                new TitledBorder(
                        new EtchedBorder(EtchedBorder.LOWERED),
                        "Opciones",
                        TitledBorder.DEFAULT_JUSTIFICATION,
                        TitledBorder.DEFAULT_POSITION,
                        fntBorder.deriveFont(Font.BOLD, 14f),
                        Color.DARK_GRAY),
                new EmptyBorder(10, 10, 10, 10));
        CompoundBorder brdSeleccion = new CompoundBorder(
                new TitledBorder(
                        new EtchedBorder(EtchedBorder.LOWERED),
                        "Selección",
                        TitledBorder.DEFAULT_JUSTIFICATION,
                        TitledBorder.DEFAULT_POSITION,
                        fntBorder.deriveFont(Font.BOLD, 14f),
                        Color.DARK_GRAY),
                new EmptyBorder(10, 10, 10, 10));
        CompoundBorder brdControles = new CompoundBorder(
                new TitledBorder(
                        new EtchedBorder(EtchedBorder.LOWERED),
                        "Controles",
                        TitledBorder.DEFAULT_JUSTIFICATION,
                        TitledBorder.DEFAULT_POSITION,
                        fntBorder.deriveFont(Font.BOLD, 14f),
                        Color.DARK_GRAY),
                new EmptyBorder(10, 10, 10, 10));

        // Lista de opciones
        lstOpciones = new JList(AUTONOMIAS);
        lstOpciones.setFont(fntList.deriveFont(Font.BOLD, 16f));

        // Panel de opciones
        pnlOpciones = new JScrollPane(lstOpciones);
        pnlOpciones.setPreferredSize(new Dimension(190, 200));
        pnlOpciones.setBorder(brdOpciones);

        // Modelo de la lista de selección
        DefaultListModel dlmSeleccion = new DefaultListModel();

        // Lista de selección
        lstSeleccion = new JList(dlmSeleccion);
        lstSeleccion.setFont(fntList.deriveFont(Font.BOLD, 16f));

        // Panel de selección
        pnlSeleccion = new JScrollPane(lstSeleccion);
        pnlSeleccion.setPreferredSize(new Dimension(190, 200));
        pnlSeleccion.setBorder(brdSeleccion);

        // Botón Derecha
        btnDerecha = new JButton(">");
        btnDerecha.setMaximumSize(new Dimension(150, 30));

        // Botón TodoDerecha
        btnTodoDerecha = new JButton(">>");
        btnTodoDerecha.setMaximumSize(new Dimension(150, 30));

        // Botón Izquierda
        btnIzquierda = new JButton("<");
        btnIzquierda.setMaximumSize(new Dimension(150, 30));

        // Botón TodoIzquierda
        btnTodoIzquierda = new JButton("<<");
        btnTodoIzquierda.setMaximumSize(new Dimension(150, 30));

        // Panel de control
        pnlControles = new Box(BoxLayout.Y_AXIS);
        pnlControles.add(Box.createVerticalGlue());
        pnlControles.add(btnDerecha);
        pnlControles.add(Box.createVerticalStrut(10));
        pnlControles.add(btnTodoDerecha);
        pnlControles.add(Box.createVerticalStrut(10));
        pnlControles.add(btnTodoIzquierda);
        pnlControles.add(Box.createVerticalStrut(10));
        pnlControles.add(btnIzquierda);
        pnlControles.add(Box.createVerticalGlue());
        pnlControles.setBorder(brdControles);

        // Panel principal
        pnlPpal = new JPanel(new BorderLayout());
        pnlPpal.add(pnlOpciones, BorderLayout.WEST);
        pnlPpal.add(pnlControles, BorderLayout.CENTER);
        pnlPpal.add(pnlSeleccion, BorderLayout.EAST);
        pnlPpal.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Ventana principal
        setContentPane(pnlPpal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        try {
            int height = Integer.parseInt(prp.getProperty(PRP_FORM_HEIGHT));
            int width = Integer.parseInt(prp.getProperty(PRP_FORM_WIDTH));
            setSize(width, height);
        } catch (NumberFormatException e) {
            setSize(DEF_FORM_WIDTH, DEF_FORM_HEIGHT);
        }
        setTitle(prp.getProperty(PRP_FORM_TITLE, DEF_FORM_TITLE));
        setLocationRelativeTo(null);
    }

    // Inicialización Anterior    
    private void initBefore() {
        // Establecer LnF
        UtilesSwing.establecerLnFProfile(prp.getProperty(
                PRP_LOOK_AND_FEEL_PROFILE, DEF_LOOK_AND_FEEL_PROFILE));
    }

    // Inicialización Posterior
    private void initAfter() {
        // Establecer Favicon
        UtilesSwing.establecerFavicon(this, prp.getProperty(
                PRP_FAVICON_RESOURCE, DEF_FAVICON_RESOURCE));

        // Registrar Gestores de Eventos
        btnDerecha.addActionListener(new AEM(this));
        btnTodoDerecha.addActionListener(new AEM(this));
        btnIzquierda.addActionListener(new AEM(this));
        btnTodoIzquierda.addActionListener(new AEM(this));
    }

    // Procesar Trasiego de Datos entre Tablas
    public final void procesarAccion(ActionEvent ae) {
        if (ae.getSource().equals(btnDerecha)) {
            ((DefaultListModel) (lstSeleccion.getModel())).removeAllElements();
            Object seleccion[] = lstOpciones.getSelectedValuesList().toArray();
            for (Object seleccion1 : seleccion) {
                ((DefaultListModel) (lstSeleccion.getModel())).addElement(seleccion1);
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
