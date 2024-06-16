package com.cineflix;
import com.cineflix.dao.PodcastDAO;
import com.cineflix.model.Podcast;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroFrame extends JFrame {
    private JTextField txtProdutor;
    private JTextField txtNomeEpisodio;
    private JTextField txtNumeroEpisodio;
    private JTextField txtDuracao;
    private JTextField txtUrl;
    private JButton btnSalvar;
    private PodcastDAO podcastDAO;
    private ListagemFrame listagemFrame;

    public CadastroFrame(ListagemFrame listagemFrame) {
        this.listagemFrame = listagemFrame;
        podcastDAO = new PodcastDAO();

        setTitle("Cadastro de Podcast");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel lblProdutor = new JLabel("Produtor:");
        lblProdutor.setBounds(10, 20, 80, 25);
        panel.add(lblProdutor);

        txtProdutor = new JTextField(20);
        txtProdutor.setBounds(100, 20, 165, 25);
        panel.add(txtProdutor);

        JLabel lblNomeEpisodio = new JLabel("Nome do Episódio:");
        lblNomeEpisodio.setBounds(10, 50, 80, 25);
        panel.add(lblNomeEpisodio);

        txtNomeEpisodio = new JTextField(20);
        txtNomeEpisodio.setBounds(100, 50, 165, 25);
        panel.add(txtNomeEpisodio);

        JLabel lblNumeroEpisodio = new JLabel("Número do Episódio:");
        lblNumeroEpisodio.setBounds(10, 80, 80, 25);
        panel.add(lblNumeroEpisodio);

        txtNumeroEpisodio = new JTextField(20);
        txtNumeroEpisodio.setBounds(100, 80, 165, 25);
        panel.add(txtNumeroEpisodio);

        JLabel lblDuracao = new JLabel("Duração:");
        lblDuracao.setBounds(10, 110, 80, 25);
        panel.add(lblDuracao);

        txtDuracao = new JTextField(20);
        txtDuracao.setBounds(100, 110, 165, 25);
        panel.add(txtDuracao);

        JLabel lblUrl = new JLabel("URL:");
        lblUrl.setBounds(10, 140, 80, 25);
        panel.add(lblUrl);

        txtUrl = new JTextField(20);
        txtUrl.setBounds(100, 140, 165, 25);
        panel.add(txtUrl);

        btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(10, 180, 255, 25);
        panel.add(btnSalvar);

        add(panel);

        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Podcast podcast = new Podcast();
                podcast.setProdutor(txtProdutor.getText());
                podcast.setNomeEpisodio(txtNomeEpisodio.getText());
                podcast.setNumeroEpisodio(Integer.parseInt(txtNumeroEpisodio.getText()));
                podcast.setDuracao(Integer.parseInt(txtDuracao.getText()));
                podcast.setUrl(txtUrl.getText());

                podcastDAO.salvarPodcast(podcast);
                JOptionPane.showMessageDialog(null, "Podcast salvo com sucesso!");
                if (listagemFrame != null) {
                    listagemFrame.atualizarTabela();
                }
                dispose();
            }
        });
    }
}

