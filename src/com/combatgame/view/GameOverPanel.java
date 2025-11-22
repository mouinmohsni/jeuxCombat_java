package com.combatgame.view;

import com.combatgame.controller.GameController;
import com.combatgame.state.MenuState;

import javax.swing.*;
import java.awt.*;

/**
 * Panneau affiché à la fin de la partie (victoire ou défaite).
 */
public class GameOverPanel extends JPanel {

    private GameController controller;
    private JLabel messageLabel;

    public GameOverPanel(GameController controller) {
        this.controller = controller;
        setLayout(new BorderLayout(10, 10));

        // Message de fin (sera mis à jour)
        messageLabel = new JLabel("", SwingConstants.CENTER);
        messageLabel.setFont(new Font("Serif", Font.BOLD, 24));
        add(messageLabel, BorderLayout.CENTER);

        // Panneau pour les boutons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton restartButton = new JButton("Rejouer");
        JButton quitButton = new JButton("Quitter");
        buttonPanel.add(restartButton);
        buttonPanel.add(quitButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Actions des boutons
        restartButton.addActionListener(e -> {
            // Pour rejouer, on réinitialise le contrôleur et on retourne au menu.
            controller.setState(new MenuState());
            controller.handleState();
        });

        quitButton.addActionListener(e -> {
            // Quitte l'application
            System.exit(0);
        });

        restartButton.addActionListener(e -> {
            // On appelle la méthode de réinitialisation du contrôleur.
            controller.reset();
        });
    }

    /**
     * Met à jour le message affiché sur l'écran de fin.
     * @param message Le message à afficher (ex: "VICTOIRE !" ou "DÉFAITE...").
     */
    public void setMessage(String message) {
        this.messageLabel.setText(message);
    }

    /**
     * C'est la méthode que vous cherchiez.
     * Elle met à jour le message et démarre le compte à rebours pour retourner au menu.
     * @param message Le message à afficher (ex: "VICTOIRE !" ou "DÉFAITE...").
     */
    public void showFinalMessage(String message) {
        this.messageLabel.setText(message);

    }
}
