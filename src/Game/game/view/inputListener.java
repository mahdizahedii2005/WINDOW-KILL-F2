package Game.game.view;

import Game.game.Contoroler.Controller;
import Game.game.model.characterModel.Epsilon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;

import static Game.Data.constants.SPEED_OF_ADDING_BOLT;
import static Game.game.Contoroler.Controller.fire;
import static Game.game.Contoroler.Controller.moveEpsilon;

public class inputListener {
    boolean a = false;
    Timer up = new Timer (200, new AbstractAction () {
        @Override
        public void actionPerformed (ActionEvent e) {
            moveEpsilon (0, -250);
        }
    });
    boolean upp = false;
    Timer down = new Timer (200, new AbstractAction () {
        @Override
        public void actionPerformed (ActionEvent e) {
            moveEpsilon (0, 250);
        }
    });
    boolean downn = false;
    Timer left = new Timer (200, new AbstractAction () {
        @Override
        public void actionPerformed (ActionEvent e) {
            moveEpsilon (-250, 0);
        }
    });
    boolean leftt = false;
    Timer right = new Timer (200, new AbstractAction () {
        @Override
        public void actionPerformed (ActionEvent e) {
            moveEpsilon (250, 0);
        }
    });
    boolean rightt = false;
    boolean start = false;

    private void startBolting (double x, double y) {
        this.x = x;
        this.y = y;
        if (!start) {
            addbolt.start ();
            start = true;
        }
    }

    double x = 0d, y = 0d;
    Timer addbolt = new Timer (SPEED_OF_ADDING_BOLT, new AbstractAction () {
        @Override
        public void actionPerformed (ActionEvent e) {
            fire (new Point2D.Double (x, y));
        }
    });

    InputMap inputMap;
    ActionMap actionMap;

    public inputListener () {
        createKeyBindings ();
        createKeyActions ();
    }

    private void createKeyBindings () {
        inputMap = frameInGame.getFrame ().getRootPane ().getInputMap (JComponent.WHEN_IN_FOCUSED_WINDOW);
        actionMap = frameInGame.getFrame ().getRootPane ().getActionMap ();
        // Key Press:
        inputMap.put (KeyStroke.getKeyStroke (KeyEvent.VK_W, 0), "moveUp");
        inputMap.put (KeyStroke.getKeyStroke (KeyEvent.VK_S, 0), "moveDown");
        inputMap.put (KeyStroke.getKeyStroke (KeyEvent.VK_A, 0), "moveLeft");
        inputMap.put (KeyStroke.getKeyStroke (KeyEvent.VK_D, 0), "moveRight");
        inputMap.put (KeyStroke.getKeyStroke (KeyEvent.VK_SPACE, 0), "shop");
        inputMap.put (KeyStroke.getKeyStroke (MouseEvent.MOUSE_CLICKED, 0), "shoot");

        // Key Release:

        inputMap.put (KeyStroke.getKeyStroke (KeyEvent.VK_W, 0, true), "moveUpReleased");
        inputMap.put (KeyStroke.getKeyStroke (KeyEvent.VK_S, 0, true), "moveDownReleased");
        inputMap.put (KeyStroke.getKeyStroke (KeyEvent.VK_A, 0, true), "moveLeftReleased");
        inputMap.put (KeyStroke.getKeyStroke (KeyEvent.VK_D, 0, true), "moveRightReleased");
        inputMap.put (KeyStroke.getKeyStroke (KeyEvent.VK_SPACE, 0, true), "shopReleased");
        inputMap.put (KeyStroke.getKeyStroke (MouseEvent.MOUSE_CLICKED, 0, true), "shootReleased");
    }

    private void createKeyActions () {
        frameInGame.getFrame ().addMouseMotionListener (new MouseMotionListener () {
            @Override
            public void mouseDragged (MouseEvent e) {
                startBolting (e.getX (), e.getY ());
            }

            @Override
            public void mouseMoved (MouseEvent e) {
            }
        });
        frameInGame.getFrame ().addMouseListener (new MouseAdapter () {
            @Override
            public void mouseClicked (MouseEvent e) {
                startBolting (e.getX (), e.getY ());
            }

            @Override
            public void mouseReleased (MouseEvent e) {
                addbolt.stop ();
                start = false;
            }
        });
        // Key Press Action:
        actionMap.put ("moveUp", new AbstractAction () {
            @Override
            public void actionPerformed (ActionEvent e) {
                if (!upp) {
                    up.start ();
                    upp = true;
                }
            }
        });

        actionMap.put ("moveDown", new AbstractAction () {
            @Override
            public void actionPerformed (ActionEvent e) {
                if (!downn) {
                    down.start ();
                    downn = true;
                }
            }
        });
        actionMap.put ("moveLeft", new AbstractAction () {
            @Override
            public void actionPerformed (ActionEvent e) {
                if (!leftt) {
                    left.start ();
                    leftt = true;
                }
            }
        });
        actionMap.put ("moveRight", new AbstractAction () {
            @Override
            public void actionPerformed (ActionEvent e) {
                if (!rightt) {
                    right.start ();
                    rightt = true;
                }
            }
        });
        actionMap.put ("shop", new AbstractAction () {
            @Override
            public void actionPerformed (ActionEvent e) {

            }
        });
        // Key Release Action:
        actionMap.put ("moveUpReleased", new

                AbstractAction () {
                    @Override
                    public void actionPerformed (ActionEvent e) {
                        moveEpsilon (false);
                        up.stop ();
                        upp = false;
                    }
                });

        actionMap.put ("moveDownReleased", new

                AbstractAction () {
                    @Override
                    public void actionPerformed (ActionEvent e) {
                        moveEpsilon (false);
                        down.stop ();
                        downn = false;
                    }
                });

        actionMap.put ("moveLeftReleased", new

                AbstractAction () {
                    @Override
                    public void actionPerformed (ActionEvent e) {
                        moveEpsilon (true);
                        left.stop ();
                        leftt = false;
                    }
                });

        actionMap.put ("moveRightReleased", new AbstractAction () {
            @Override
            public void actionPerformed (ActionEvent e) {
                moveEpsilon (true);
                right.stop ();
                rightt = false;
            }
        });
        actionMap.put ("shopReleased", new AbstractAction () {
            @Override
            public void actionPerformed (ActionEvent e) {

            }
        });

    }

}