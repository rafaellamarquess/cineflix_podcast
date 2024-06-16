package com.cineflix.dao;

import com.cineflix.model.Podcast;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PodcastDAO {
    public void salvarPodcast(Podcast podcast) {
        String sql = "INSERT INTO podcasts (produtor, nomeEpisodio, numeroEpisodio, duracao, url) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, podcast.getProdutor());
            stmt.setString(2, podcast.getNomeEpisodio());
            stmt.setInt(3, podcast.getNumeroEpisodio());
            stmt.setInt(4, podcast.getDuracao());
            stmt.setString(5, podcast.getUrl());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Podcast> listarPodcasts() {
        List<Podcast> podcasts = new ArrayList<>();
        String sql = "SELECT * FROM podcasts";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Podcast podcast = new Podcast();
                podcast.setId(rs.getInt("id"));
                podcast.setProdutor(rs.getString("produtor"));
                podcast.setNomeEpisodio(rs.getString("nomeEpisodio"));
                podcast.setNumeroEpisodio(rs.getInt("numeroEpisodio"));
                podcast.setDuracao(rs.getInt("duracao"));
                podcast.setUrl(rs.getString("url"));
                podcasts.add(podcast);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return podcasts;
    }

    public void excluirPodcast(int id) {
        String sql = "DELETE FROM podcasts WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

