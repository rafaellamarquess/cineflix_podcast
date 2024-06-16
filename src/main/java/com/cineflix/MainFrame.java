package com.cineflix;
import com.cineflix.model.Usuario;
import com.cineflix.model.UsuarioTipo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private JButton btnCadastro;
    private JButton btnListagem;

    private Usuario usuario;

    public MainFrame(Usuario usuario) {
        this.usuario = usuario;

        setTitle("Sistema de Podcasts");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        btnCadastro = new JButton("Cadastro de Podcast");
        btnCadastro.setBounds(50, 50, 150, 25);
        panel.add(btnCadastro);

        btnListagem = new JButton("Listagem de Podcasts");
        btnListagem.setBounds(50, 100, 150, 25);
        panel.add(btnListagem);

        if (usuario.getTipo() != UsuarioTipo.ADMINISTRADOR && usuario.getTipo() != UsuarioTipo.OPERADOR) {
            btnCadastro.setEnabled(false);
        }

        add(panel);

        btnListagem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListagemFrame listagemFrame = new ListagemFrame(usuario);
                listagemFrame.setVisible(true);

                btnCadastro.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        CadastroFrame cadastroFrame = new CadastroFrame(listagemFrame);
                        cadastroFrame.setVisible(true);
                    }
                });
            }
        });
    }
}