package com.cineflix;

import com.cineflix.dao.PodcastDAO;
import com.cineflix.model.Podcast;
import com.cineflix.model.Usuario;
import com.cineflix.model.UsuarioTipo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListagemFrame extends JFrame {
    private JTable table;
    private PodcastDAO podcastDAO;
    private JButton btnExcluir;

    public ListagemFrame(Usuario usuario) {
        podcastDAO = new PodcastDAO();

        setTitle("Listagem de Podcasts");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        panel.add(scrollPane, BorderLayout.CENTER);

        if (usuario.getTipo() == UsuarioTipo.ADMINISTRADOR) {
            btnExcluir = new JButton("Excluir Podcast");
            btnExcluir.addActionListener(e -> {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int podcastId = (int) table.getValueAt(selectedRow, 0);
                    podcastDAO.excluirPodcast(podcastId);
                    JOptionPane.showMessageDialog(null, "Podcast excluído com sucesso!");
                    atualizarTabela();
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um podcast para excluir.");
                }
            });
            panel.add(btnExcluir, BorderLayout.SOUTH);
        }

        add(panel, BorderLayout.CENTER);

        atualizarTabela();
    }

    public void atualizarTabela() {
        List<Podcast> podcasts = podcastDAO.listarPodcasts();

        String[] columnNames = {"ID", "Produtor", "Nome do Episódio", "Número do Episódio", "Duração", "URL"};
        Object[][] data = new Object[podcasts.size()][6];

        for (int i = 0; i < podcasts.size(); i++) {
            Podcast p = podcasts.get(i);
            data[i][0] = p.getId();
            data[i][1] = p.getProdutor();
            data[i][2] = p.getNomeEpisodio();
            data[i][3] = p.getNumeroEpisodio();
            data[i][4] = p.getDuracao();
            data[i][5] = p.getUrl();
        }

        table.setModel(new DefaultTableModel(data, columnNames));
    }
}


