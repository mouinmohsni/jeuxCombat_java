package com.combatgame.view;


import com.combatgame.controller.GameController;
import javax.swing.*;
import java.awt.*;

/**
 * Le panneau principal du jeu (JPanel).
 * Utilise un CardLayout pour basculer entre les différents écrans (Menu, Combat, Pause).
 */
public class GameView extends JPanel {

    public static final String MENU_PANEL = "Menu";
    public static final String COMBAT_PANEL = "Combat";
    public static final String PAUSE_PANEL = "Pause";
    public static final String GAME_OVER_PANEL = "GameOver";


    private CombatPanel combatPanel;
    private CardLayout cardLayout;
    private GameController controller;
    private GameOverPanel gameOverPanel;

    public GameView(GameController controller) {
        this.controller = controller;
        this.cardLayout = new CardLayout();
        this.setLayout(cardLayout);
        this.combatPanel = new CombatPanel(controller);
        this.add(combatPanel, COMBAT_PANEL);

        // --- Création des Panneaux Spécifiques ---

        // 1. Panneau du Menu
        JPanel menuPanel = createMenuPanel();
        this.add(menuPanel, MENU_PANEL);

        // 2. Panneau de Combat (sera plus complexe)
        //JPanel combatPanel = new CombatPanel(controller); // Nous allons créer cette classe plus tard
        //this.add(combatPanel, COMBAT_PANEL);

        // 3. Panneau de Pause (simple pour l'exemple)
        JPanel pausePanel = new JPanel();
        pausePanel.add(new JLabel("JEU EN PAUSE"));
        this.add(pausePanel, PAUSE_PANEL);

        // 4. Panneau Game Over
        this.gameOverPanel = new GameOverPanel(controller);
        this.add(gameOverPanel, GAME_OVER_PANEL);

        // Afficher le Menu au démarrage
        cardLayout.show(this, MENU_PANEL);
    }

    private JPanel createMenuPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JButton startButton = new JButton("Nouvelle Partie");
        startButton.addActionListener(e -> {
            // On ne passe pas directement en PlayingState.
            // On passe en StartState pour que la partie soit correctement initialisée.
            controller.setState(new com.combatgame.state.StartState());
            controller.handleState();
        });

        panel.add(new JLabel("Bienvenue !"));
        panel.add(startButton);
        return panel;
    }

    /**
     * Méthode clé pour basculer l'affichage en fonction de l'état du jeu.
     */
    public void showPanel(String panelName) {
        cardLayout.show(this, panelName);
    }
    /**
     * Retourne l'instance du panneau de combat.
     * @return Le CombatPanel.
     */
    public CombatPanel getCombatPanel() {
        return this.combatPanel;
    }

    /**
     * Retourne l'instance du panneau de fin de partie.
     * @return Le GameOverPanel.
     */
    public GameOverPanel getGameOverPanel() {
        return this.gameOverPanel;
    }

}
