package Game.game.view;

import Game.game.model.characterModel.Epsilon;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static Game.game.Contoroler.Controller.moveEpsilon;

public class inputListener {

    InputMap inputMap;
    ActionMap actionMap;

    public inputListener() {
        createKeyBindings();
        createKeyActions();
    }

    private void createKeyBindings() {
        inputMap = frameInGame.getFrame().getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        actionMap = frameInGame.getFrame().getRootPane().getActionMap();
        // Key Press:
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0), "moveUp");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0), "moveDown");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0), "moveLeft");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), "moveRight");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "shop");
        inputMap.put(KeyStroke.getKeyStroke(MouseEvent.BUTTON1, 0), "shoot");
        inputMap.put(KeyStroke.getKeyStroke(MouseEvent.BUTTON3, 0), "aiming");

        // Key Release:

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, true), "moveUpReleased");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, true), "moveDownReleased");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, true), "moveLeftReleased");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, true), "moveRightReleased");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true), "shopReleased");
        inputMap.put(KeyStroke.getKeyStroke(MouseEvent.BUTTON1, 0, true), "shootReleased");
        inputMap.put(KeyStroke.getKeyStroke(MouseEvent.BUTTON3, 0, true), "aimingReleased");
    }

    private void createKeyActions() {

        // Key Press Action:
        actionMap.put("moveUp", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveEpsilon(0, -1);
                System.out.println("up");

            }
        });

        actionMap.put("moveDown", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveEpsilon(0, 1);
                System.out.println("down");

            }
        });
        actionMap.put("moveLeft", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveEpsilon(-1, 0);
            }
        });

        actionMap.put("moveRight", new
                AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        moveEpsilon(1, 0);
                    }
                });
        actionMap.put("shop", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        actionMap.put("shoot", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        actionMap.put("aiming", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        // Key Release Action:
        actionMap.put("moveUpReleased", new

                AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        moveEpsilon(false);
                        System.out.println("upR");

                    }
                });

        actionMap.put("moveDownReleased", new

                AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        moveEpsilon(false);
                        System.out.println("DownR");

                    }
                });

        actionMap.put("moveLeftReleased", new

                AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        moveEpsilon(true);
                        System.out.println("leftR");
                    }
                });

        actionMap.put("moveRightReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveEpsilon(true);
            }
        });
        actionMap.put("shopReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        actionMap.put("shootReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        actionMap.put("aimingReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

    }

}