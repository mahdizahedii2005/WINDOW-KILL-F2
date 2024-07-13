//package Game.game.view.waveAnimation;
//
//import Game.game.Contoroler.player.soundPlayer;
//import Game.game.model.Move.Direction;
//import Game.game.model.characterModel.epsilonFriend.Epsilon;
//import Game.game.view.PanelInGame.frameInGame;
//import Game.game.view.PanelInGame.panelInGameView;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.geom.Point2D;
//
//public abstract class Animation {
//    private boolean finish = false;
//    protected Dimension size;
//    JLabel Photo;
//
//    public Animation (Dimension size, String PhotoPath, String soundPath, int time) {
//        panelInGameView.DO_I_HAVE_ANIMATION = true;
//        panelInGameView.getPanel ().animation = this;
//        this.size = size;
//        Photo = new JLabel ();
//        Photo.setIcon (new ImageIcon (PhotoPath));
//        Photo.setSize (size);
//        Photo.setLocation ((panelInGameView.getPanel ().getWidth () - Photo.getSize ().width) / 2, (panelInGameView.getPanel ().getHeight () - Photo.getSize ().height) / 2);
//        Photo.setVisible (true);
//        Photo.setOpaque (true);
//        panelInGameView.getPanel ().add (Photo);
//        soundPlayer.play (soundPath);
//        Epsilon.getEpsilon ().setDirection (new Direction (new Point2D.Double (0, 0)));
//        new Timer (time, new AbstractAction () {
//            @Override
//            public void actionPerformed (ActionEvent e) {
//                try {
//                    if (finish) {
//                        panelInGameView.DO_I_HAVE_ANIMATION = false;
//                        panelInGameView.getPanel ().remove (Photo);
//                        panelInGameView.getPanel ().setFocusable (true);
//                        panelInGameView.getPanel ().requestFocus ();
//                        panelInGameView.getPanel ().requestFocusInWindow ();
//                        finish = false;
//                        frameInGame.getFrame ().repaint ();
//                        ((Timer) e.getSource ()).stop ();
//                    }
//                    finish = true;
////                    Update.CLOSE_FRAME.stop ();
////                    Update.MODEL_UPDATE.stop ();
////                    Update.FRAME_UPDATE.stop ();
//                } catch (Exception r) {
//                }
//            }
//        }).start ();
//    }
//
//    public Animation (Dimension size, String PhotoPath, String soundPath) {
//        this (size, PhotoPath, soundPath, 2000);
//    }
//
//    public void update () {
//        Photo.setLocation ((panelInGameView.getPanel ().getWidth () - Photo.getSize ().width) / 2, (panelInGameView.getPanel ().getHeight () - Photo.getSize ().height) / 2);
//    }
//}
