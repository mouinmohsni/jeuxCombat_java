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

    // Composants pour l'ennemi
    private JLabel enemyNameLabel;
    private JProgressBar enemyHealthBar;

    // Boutons d'action
    private JButton attackButton;
    private JButton defendButton;
    private JButton itemButton;
    private JLabel playerImageLabel;
    private JLabel enemyImageLabel;

    public CombatPanel(GameController controller) {
        this.controller = controller;
        setLayout(new BorderLayout(10, 10)); // Layout principal

        // --- Panneau de l'Ennemi (en haut) ---
        JPanel enemyPanel = new JPanel(new GridLayout(2, 1));
        enemyNameLabel = new JLabel("Ennemi", SwingConstants.CENTER);
        enemyHealthBar = new JProgressBar();
        enemyPanel.add(enemyNameLabel);
        enemyPanel.add(enemyHealthBar);
        add(enemyPanel, BorderLayout.NORTH);
        playerImageLabel = new JLabel();
        enemyImageLabel = new JLabel();

        add(playerImageLabel, BorderLayout.WEST);
        add(enemyImageLabel, BorderLayout.EAST);

        // --- Panneau du Joueur (en bas) ---
        JPanel playerPanel = new JPanel(new GridLayout(2, 1));
        playerNameLabel = new JLabel("Joueur", SwingConstants.CENTER);
        playerHealthBar = new JProgressBar();
        playerPanel.add(playerNameLabel);
        playerPanel.add(playerHealthBar);
        add(playerPanel, BorderLayout.SOUTH);

        // --- Panneau des Actions (à droite) ---
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
            // On utilise les versions "effectives" pour prendre en compte les bonus/malus
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
                // Si on a trouvé une potion, on crée et exécute la commande.
                UseItemCommand command = new UseItemCommand(controller.getPlayer(), itemToUse, controller);
                controller.executePlayerCommand(command);
            } else {
                // Sinon, on informe le joueur.
                JOptionPane.showMessageDialog(this, "Vous n'avez pas de Potion de Force !");
            }
        });
    }

    /**
     * Met à jour tous les composants graphiques avec les données actuelles du jeu.
     * C'est le cœur du rafraîchissement de la vue.
     */
    public void updateView() {

        new ImageIcon("../images/foret.jpg");





        // Mettre à jour les infos du joueur
        Character player = controller.getPlayer();
        playerNameLabel.setText(player.getName());
        playerHealthBar.setMaximum(player.getMaxHealth());
        playerHealthBar.setValue(player.getHealth());
        playerHealthBar.setString(player.getHealth() + " / " + player.getMaxHealth());
        playerHealthBar.setStringPainted(true);

        // Mettre à jour les images
        if (player.getImagePath() != null) {
            playerImageLabel.setIcon(new ImageIcon(player.getImagePath()));
        }

        // Mettre à jour les infos de l'ennemi
        Character enemy = controller.getCurrentEnemy();
        if (enemy != null) {
            enemyNameLabel.setText(enemy.getName());
            enemyHealthBar.setMaximum(enemy.getMaxHealth());
            enemyHealthBar.setValue(enemy.getHealth());
            enemyHealthBar.setString(enemy.getHealth() + " / " + enemy.getMaxHealth());
            enemyHealthBar.setStringPainted(true);
            enemyImageLabel.setIcon(new ImageIcon(enemy.getImagePath()));
        }
//        if (enemy != null && enemy.getImagePath() != null) {
//            enemyImageLabel.setIcon(new ImageIcon(enemy.getImagePath()));
//            System.out.println("path" + enemy.getImagePath());
//        }else{
//            System.out.println("Erreur des ennemi image path :" + enemy.getImagePath());
//        }

    }
}
