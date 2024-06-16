package com.cineflix;

import com.cineflix.dao.UsuarioDAO;
import com.cineflix.model.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private JTextField txtNome;
    private JPasswordField txtSenha;
    private JButton btnLogin;
    private UsuarioDAO usuarioDAO;

    public LoginFrame() {
        usuarioDAO = new UsuarioDAO();

        setTitle("Login");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(10, 20, 80, 25);
        panel.add(lblNome);

        txtNome = new JTextField(20);
        txtNome.setBounds(100, 20, 165, 25);
        panel.add(txtNome);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(10, 50, 80, 25);
        panel.add(lblSenha);

        txtSenha = new JPasswordField(20);
        txtSenha.setBounds(100, 50, 165, 25);
        panel.add(txtSenha);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(10, 80, 255, 25);
        panel.add(btnLogin);

        add(panel);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = txtNome.getText();
                String senha = new String(txtSenha.getPassword());
                Usuario usuario = usuarioDAO.autenticar(nome, senha);
                if (usuario != null) {
                    JOptionPane.showMessageDialog(null, "Olá " + usuario.getNome() + ", sua permissão é de " + usuario.getTipo() + ". Seja bem-vindo!");
                    MainFrame mainFrame = new MainFrame(usuario);
                    mainFrame.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos.");
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginFrame().setVisible(true);
            }
        });
    }
}
