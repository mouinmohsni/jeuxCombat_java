package com.combatgame.view;

import com.combatgame.command.AttackCommand;
import com.combatgame.command.DefendCommand;
import com.combatgame.controller.GameController;
import com.combatgame.model.Character;
import com.combatgame.command.UseItemCommand;
import com.combatgame.model.items.Item;
import com.combatgame.model.items.potions.StrengthPotion;

import javax.swing.*;
import java.awt.*;

/**
 * Panneau qui affiche l'écran de combat principal.
 */
public class CombatPanel extends JPanel {

    private GameController controller;

    // Composants pour le joueur
    private JLabel playerNameLabel;
    private JProgressBar playerHealthBar;
    private JLabel playerImageLabel;

    // Composants pour l'ennemi
    private JLabel enemyNameLabel;
    private JProgressBar enemyHealthBar;
    private JLabel enemyImageLabel;

    // Boutons d'action
    private JButton attackButton;
    private JButton defendButton;
    private JButton itemButton;

    public CombatPanel(GameController controller) {
        this.controller = controller;
        setLayout(new BorderLayout(10, 10)); // Layout principal

        // --- Panneau CENTRAL : Les images des personnages ---
        JPanel centerPanel = new JPanel(new BorderLayout());

        // Image du joueur à gauche
        playerImageLabel = new JLabel("", SwingConstants.CENTER);
        playerImageLabel.setPreferredSize(new Dimension(200, 300)); // Taille fixe pour l'image
        centerPanel.add(playerImageLabel, BorderLayout.WEST);

        // Image de l'ennemi à droite
        enemyImageLabel = new JLabel("", SwingConstants.CENTER);
        enemyImageLabel.setPreferredSize(new Dimension(200, 300)); // Taille fixe pour l'image
        centerPanel.add(enemyImageLabel, BorderLayout.EAST);

        add(centerPanel, BorderLayout.CENTER);

        // --- Panneau de l'Ennemi (en haut) : Nom + Barre de vie ---
        JPanel enemyPanel = new JPanel(new GridLayout(2, 1));
        enemyNameLabel = new JLabel("Ennemi", SwingConstants.CENTER);
        enemyNameLabel.setFont(new Font("Serif", Font.BOLD, 18));
        enemyHealthBar = new JProgressBar();
        enemyHealthBar.setForeground(Color.RED);
        enemyPanel.add(enemyNameLabel);
        enemyPanel.add(enemyHealthBar);
        add(enemyPanel, BorderLayout.NORTH);

        // --- Panneau du Joueur (en bas) : Nom + Barre de vie ---
        JPanel playerPanel = new JPanel(new GridLayout(2, 1));
        playerNameLabel = new JLabel("Joueur", SwingConstants.CENTER);
        playerNameLabel.setFont(new Font("Serif", Font.BOLD, 18));
        playerHealthBar = new JProgressBar();
        playerHealthBar.setForeground(Color.GREEN);
        playerPanel.add(playerNameLabel);
        playerPanel.add(playerHealthBar);
        add(playerPanel, BorderLayout.SOUTH);

        // --- Panneau des Actions (à droite) : Les boutons ---
        JPanel actionPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        attackButton = new JButton("Attaquer");
        defendButton = new JButton("Défendre");
        itemButton = new JButton("Inventaire");
        actionPanel.add(attackButton);
        actionPanel.add(defendButton);
        actionPanel.add(itemButton);
        add(actionPanel, BorderLayout.EAST);

        // --- Connexion des boutons aux actions ---
        attackButton.addActionListener(e -> {
            AttackCommand command = new AttackCommand(
                    controller.getEffectivePlayer(),
                    controller.getEffectiveEnemy()
            );
            controller.executePlayerCommand(command);
        });

        defendButton.addActionListener(e -> {
            DefendCommand command = new DefendCommand(controller.getPlayer());
            controller.executePlayerCommand(command);
        });

        itemButton.addActionListener(e -> {
            // On cherche la première Potion de Force dans l'inventaire.
            Item itemToUse = null;
            for (Item item : controller.getPlayer().getInventory()) {
                if (item instanceof StrengthPotion) {
                    itemToUse = item;
                    break;
                }
            }

            if (itemToUse != null) {
                UseItemCommand command = new UseItemCommand(controller.getPlayer(), itemToUse, controller);
                controller.executePlayerCommand(command);
            } else {
                JOptionPane.showMessageDialog(this, "Vous n'avez pas de Potion de Force !");
            }
        });
    }

    /**
     * Met à jour tous les composants graphiques avec les données actuelles du jeu.
     */
    public void updateView() {
        // Mettre à jour les infos du joueur
        Character player = controller.getPlayer();
        playerNameLabel.setText(player.getName());
        playerHealthBar.setMaximum(player.getMaxHealth());
        playerHealthBar.setValue(player.getHealth());
        playerHealthBar.setString(player.getHealth() + " / " + player.getMaxHealth());
        playerHealthBar.setStringPainted(true);

        // Mettre à jour l'image du joueur
        if (player.getImagePath() != null) {
            ImageIcon icon = new ImageIcon(player.getImagePath());
            // Redimensionner l'image pour qu'elle s'adapte bien
            Image img = icon.getImage().getScaledInstance(180, 280, Image.SCALE_SMOOTH);
            playerImageLabel.setIcon(new ImageIcon(img));
        }

        // Mettre à jour les infos de l'ennemi
        Character enemy = controller.getCurrentEnemy();
        if (enemy != null) {
            enemyNameLabel.setText(enemy.getName());
            enemyHealthBar.setMaximum(enemy.getMaxHealth());
            enemyHealthBar.setValue(enemy.getHealth());
            enemyHealthBar.setString(enemy.getHealth() + " / " + enemy.getMaxHealth());
            enemyHealthBar.setStringPainted(true);

            // Mettre à jour l'image de l'ennemi
            if (enemy.getImagePath() != null) {
                ImageIcon icon = new ImageIcon(enemy.getImagePath());
                // Redimensionner l'image pour qu'elle s'adapte bien
                Image img = icon.getImage().getScaledInstance(180, 280, Image.SCALE_SMOOTH);
                enemyImageLabel.setIcon(new ImageIcon(img));
            }
        }
    }
}
