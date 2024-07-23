package Game.game.Contoroler.building;


import Game.game.model.characterModel.Enemy.boss.boss;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.geom.Point2D;

import static Game.game.model.collision.Collidable.side.left;
import static Game.game.model.collision.Collidable.side.right;


public class Spawn {
    public static boolean Start = false;
    public static int NUMBER_OF_ARCHMIRE_ENEMY = 0;
    public static int NUMBER_OF_BARRICOD_ENEMY = 0;
    public static int NUMBER_OF_BALCKORB_ENEMY = 0;
    public static int NUMBER_OF_NEPRO_ENEMY = 0;
    public static int NUMBER_OF_OMENICT_ENEMY = 0;
    public static int NUMBER_OF_SQUR_ENEMY = 0;
    public static int NUMBER_OF_TRIG_ENEMY = 0;
    public static int NUMBER_OF_WYRM_ENEMY = 0;

    public Spawn() {
        spawn = this;
        spawnstate = spawnState.first;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    synchronized (this) {
                        wait(5000);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                firstWave.start();
            }
        }).start();
    }

    public enum spawnState {
        first, second, third, finish
    }

    public static void setSpawnNull() {
        getSpawn().firstWave.stop();
        getSpawn().secondWave.stop();
        getSpawn().thirdWave.stop();
        getSpawn().feethWave.stop();
        getSpawn().fourthWave.stop();
        Spawn.spawn = null;
    }

    public static int zarib = 10;
    public static int zaribSpawn = 2;

    private static Spawn spawn;
    public static spawnState spawnstate = spawnState.first;
    private int TOTAL_NUMBER_OF_ENEMY_1 = zarib * 3;
    private int TOTAL_NUMBER_OF_ENEMY_2 = zarib * 4;
    private int TOTAL_NUMBER_OF_ENEMY_3 = zarib * 5;
    private final int NUMBER_OF_ENEMY_1 = zaribSpawn * 2;
    private final int NUMBER_OF_ENEMY_2 = (zaribSpawn + 1) * 2;
    private final int NUMBER_OF_ENEMY_3 = (zaribSpawn + 2) * 2;
    private int CurrentEnemy = 0;
    private int SQNumEnemy = 0;
    private int TRNumEnemy = 0;
    private final double sqPercent = 0.6;
    private final double trPercent = 0.4;

    private boolean finish(int total) {
        return total == 0 &&
                NUMBER_OF_ARCHMIRE_ENEMY == 0 &&
                NUMBER_OF_BALCKORB_ENEMY == 0 &&
                NUMBER_OF_BARRICOD_ENEMY == 0 &&
                NUMBER_OF_OMENICT_ENEMY == 0 &&
                NUMBER_OF_SQUR_ENEMY == 0 &&
                NUMBER_OF_TRIG_ENEMY == 0 &&
                NUMBER_OF_NEPRO_ENEMY == 0 &&
                NUMBER_OF_WYRM_ENEMY == 0;
    }

    private javax.swing.Timer firstWave = new javax.swing.Timer(900, new AbstractAction() {
        int totalEnemy = 20;
        final int getTotalEnemy = totalEnemy;
        int state = 0;
        @Override
        public void actionPerformed(ActionEvent e) {
            findPlace findPlace = new findPlace();
            switch (state % 3) {
                case 0 -> {
                    if (NUMBER_OF_TRIG_ENEMY < getTotalEnemy / 5) {
                        BuilderHelper.trigorathBuilder(findPlace.getP1(), findPlace.getP2());
                        totalEnemy--;
                        state++;
                    }
                }
                case 1 -> {
                    if (NUMBER_OF_SQUR_ENEMY < getTotalEnemy / 5) {
                        BuilderHelper.squarantineBuilder(findPlace.getP1(), findPlace.getP2());
                        totalEnemy--;
                        state++;

                    }
                    }
                case 2 -> {
                    if (NUMBER_OF_NEPRO_ENEMY < 1) {
                        BuilderHelper.NecropickBuilder(findPlace.getP2());
                        totalEnemy--;
                        state++;
                    } else {
                        state++;
                    }
                }
            }
            state++;
            if (finish(totalEnemy)) {
                    StopWave1();
            }
        }
    });
    private javax.swing.Timer secondWave = new javax.swing.Timer(650, new AbstractAction() {
        int totalEnemy = 30;
        final int getTotalEnemy = totalEnemy;
        int state = 0;
        @Override
        public void actionPerformed(ActionEvent e) {
            findPlace findPlace = new findPlace();
            switch (state % 4) {
                case 0 -> {
                    if (NUMBER_OF_TRIG_ENEMY < getTotalEnemy / 7) {
                        BuilderHelper.trigorathBuilder(findPlace.getP1(), findPlace.getP2());
                        totalEnemy--;
                        state++;

                    }
                }
                case 1 -> {
                    if (NUMBER_OF_SQUR_ENEMY < getTotalEnemy / 8) {
                        BuilderHelper.squarantineBuilder(findPlace.getP1(), findPlace.getP2());
                        totalEnemy--;
                        state++;

                    }
                }
                case 2 -> {
                    if (NUMBER_OF_NEPRO_ENEMY < 2) {
                        BuilderHelper.NecropickBuilder(findPlace.getP2());
                        totalEnemy--;
                        state++;
                    } else {
                        state++;
                    }
                }
                case 3 -> {
                    if (NUMBER_OF_OMENICT_ENEMY < 1) {
                        BuilderHelper.OmenoctBuilder(findPlace.getP2(), right);
                        totalEnemy--;
                        state++;
                    } else {
                        state++;
                    }
                }
            }
            state++;
            if (finish(totalEnemy)) {
                StopWave2();
            }
        }
    });
    private javax.swing.Timer thirdWave = new javax.swing.Timer(600, new AbstractAction() {
        int totalEnemy = 35;
        final int getTotalEnemy = totalEnemy;
        int state = 0;

        @Override
        public void actionPerformed(ActionEvent e) {
            findPlace findPlace = new findPlace();
            switch (state % 6) {
                case 0 -> {
                    if (NUMBER_OF_TRIG_ENEMY < getTotalEnemy / 10) {
                        BuilderHelper.trigorathBuilder(findPlace.getP1(), findPlace.getP2());
                        totalEnemy--;
                        state++;

                    }
                }
                case 1 -> {
                    if (NUMBER_OF_SQUR_ENEMY < getTotalEnemy / 10) {
                        BuilderHelper.squarantineBuilder(findPlace.getP1(), findPlace.getP2());
                        totalEnemy--;
                        state++;

                    }
                }
                case 2 -> {
                    if (NUMBER_OF_NEPRO_ENEMY < 2) {
                        BuilderHelper.NecropickBuilder(findPlace.getP2());
                        totalEnemy--;
                        state++;
                    } else {
                        state++;
                    }
                }
                case 3 -> {
                    if (NUMBER_OF_OMENICT_ENEMY < 1) {
                        BuilderHelper.OmenoctBuilder(findPlace.getP2(), right);
                        totalEnemy--;
                        state++;
                    } else {
                        state++;
                    }
                }
                case 4 -> {
                    if (NUMBER_OF_BARRICOD_ENEMY < 1) {
                        BuilderHelper.OmenoctBuilder(new Point2D.Double(findPlace.getP2().getX() + 100, findPlace.getP2().getY()), right);
                        totalEnemy--;
                        state++;
                    } else {
                        state++;
                    }
                }
                case 5 -> {
                    if (NUMBER_OF_WYRM_ENEMY < 2) {
                        BuilderHelper.wyrmBuilder(new Point2D.Double(findPlace.getP2().getX(), findPlace.getP2().getY()));
                        totalEnemy--;
                        state++;

                    } else {
                        state++;
                    }
                }
            }
            state++;
            if (finish(totalEnemy)) {
                StopWave3();
            }
        }
    });
    private javax.swing.Timer fourthWave = new javax.swing.Timer(500, new AbstractAction() {
        int totalEnemy = 30;
        final int getTotalEnemy = totalEnemy;
        int state = 0;

        @Override
        public void actionPerformed(ActionEvent e) {
            findPlace findPlace = new findPlace();
            switch (state % 4) {
                case 0 -> {
                    if (NUMBER_OF_TRIG_ENEMY < getTotalEnemy / 12) {
                        BuilderHelper.trigorathBuilder(findPlace.getP1(), findPlace.getP2());
                        totalEnemy--;
                        state++;

                    }
                }
                case 1 -> {
                    if (NUMBER_OF_SQUR_ENEMY < getTotalEnemy / 12) {
                        BuilderHelper.squarantineBuilder(findPlace.getP1(), findPlace.getP2());
                        totalEnemy--;
                        state++;

                    }
                }
                case 2 -> {
                    if (NUMBER_OF_NEPRO_ENEMY < 2) {
                        BuilderHelper.NecropickBuilder(findPlace.getP2());
                        totalEnemy--;
                        state++;

                    }
                }
                case 3 -> {
                    if (NUMBER_OF_OMENICT_ENEMY < 2) {
                        if (NUMBER_OF_OMENICT_ENEMY == 1) BuilderHelper.OmenoctBuilder(findPlace.getP2(), right);
                        else BuilderHelper.OmenoctBuilder(findPlace.getP2(), left);
                        totalEnemy--;
                        state++;

                    } else {
                        state++;
                    }
                }
                case 4 -> {
                    if (NUMBER_OF_BARRICOD_ENEMY < 1) {
                        BuilderHelper.OmenoctBuilder(new Point2D.Double(findPlace.getP2().getX() + 100, findPlace.getP2().getY()), right);
                        totalEnemy--;
                        state++;

                    } else {
                        state++;
                    }
                }
                case 5 -> {
                    if (NUMBER_OF_WYRM_ENEMY < 2) {
                        BuilderHelper.wyrmBuilder(new Point2D.Double(findPlace.getP2().getX(), findPlace.getP2().getY()));
                        totalEnemy--;
                        state++;

                    } else {
                        state++;
                    }
                }
                case 6 -> {
                    if (NUMBER_OF_ARCHMIRE_ENEMY < 1) {
                        BuilderHelper.ArchmireBuilder(new Point2D.Double(findPlace.getP2().getX(), findPlace.getP2().getY()), false);
                        totalEnemy--;
                        state++;

                    } else {
                        state++;
                    }
                }
            }
            state++;
            if (finish(totalEnemy)) {
                StopWave4();
            }
        }
    });
    private javax.swing.Timer feethWave = new javax.swing.Timer(400, new AbstractAction() {
        int totalEnemy = 40;
        final int getTotalEnemy = totalEnemy;
        int state = 0;

        @Override
        public void actionPerformed(ActionEvent e) {
            findPlace findPlace = new findPlace();
            switch (state % 4) {
                case 0 -> {
                    if (NUMBER_OF_TRIG_ENEMY < getTotalEnemy / 15) {
                        BuilderHelper.trigorathBuilder(findPlace.getP1(), findPlace.getP2());
                        totalEnemy--;
                        state++;

                    }
                }
                case 1 -> {
                    if (NUMBER_OF_SQUR_ENEMY < getTotalEnemy / 15) {
                        BuilderHelper.squarantineBuilder(findPlace.getP1(), findPlace.getP2());
                        totalEnemy--;
                        state++;

                    }
                }
                case 2 -> {
                    if (NUMBER_OF_NEPRO_ENEMY < 2) {
                        BuilderHelper.NecropickBuilder(findPlace.getP2());
                        totalEnemy--;
                        state++;

                    } else {
                        state++;
                    }
                }
                case 3 -> {
                    if (NUMBER_OF_OMENICT_ENEMY < 2) {
                        if (NUMBER_OF_OMENICT_ENEMY == 1) BuilderHelper.OmenoctBuilder(findPlace.getP2(), right);
                        else BuilderHelper.OmenoctBuilder(findPlace.getP2(), left);
                        totalEnemy--;
                        state++;

                    } else {
                        state++;
                    }
                }
                case 4 -> {
                    if (NUMBER_OF_BARRICOD_ENEMY < 1) {
                        BuilderHelper.OmenoctBuilder(new Point2D.Double(findPlace.getP2().getX() + 100, findPlace.getP2().getY()), right);
                        totalEnemy--;
                        state++;

                    } else {
                        state++;
                    }
                }
                case 5 -> {
                    if (NUMBER_OF_WYRM_ENEMY < 2) {
                        BuilderHelper.wyrmBuilder(new Point2D.Double(findPlace.getP2().getX(), findPlace.getP2().getY()));
                        totalEnemy--;
                        state++;

                    } else {
                        state++;
                    }
                }
                case 6 -> {
                    if (NUMBER_OF_ARCHMIRE_ENEMY < 2) {
                        BuilderHelper.ArchmireBuilder(new Point2D.Double(findPlace.getP2().getX(), findPlace.getP2().getY()), false);
                        totalEnemy--;
                        state++;

                    } else {
                        state++;
                    }
                }
                case 7 -> {
                    if (NUMBER_OF_BALCKORB_ENEMY < 1) {
                        BuilderHelper.startBlackOrb();
                        totalEnemy--;
                        state++;

                    } else {
                        state++;
                    }
                }
            }
            state++;
            if (finish(totalEnemy)) {
                StopWave5();
            }
        }
    });

    private void StopWave1() {
        System.out.println("stop wave 1");
        firstWave.stop();
        JOptionPane.showMessageDialog(Game.game.view.PanelInGame.frameInGame.getFrame(), "wave 1 is finished", "finish wave", JOptionPane.WARNING_MESSAGE);
        secondWave.start();
    }

    private void StopWave2() {
        System.out.println("stop wave 2");
        secondWave.stop();
        JOptionPane.showMessageDialog(Game.game.view.PanelInGame.frameInGame.getFrame(), "wave 2 is finished", "finish wave", JOptionPane.WARNING_MESSAGE);
        thirdWave.start();

    }

    private void StopWave3() {
        System.out.println("stop wave 2");
        thirdWave.stop();
        JOptionPane.showMessageDialog(Game.game.view.PanelInGame.frameInGame.getFrame(), "wave 3 is finished", "finish wave", JOptionPane.WARNING_MESSAGE);
        fourthWave.start();
    }


    private void StopWave4() {
        System.out.println("stop wave 2");
        fourthWave.stop();
        JOptionPane.showMessageDialog(Game.game.view.PanelInGame.frameInGame.getFrame(), "wave 4 is finished", "finish wave", JOptionPane.WARNING_MESSAGE);
        feethWave.start();
    }

    private void StopWave5() {
        System.out.println("stop wave 2");
        feethWave.stop();
        JOptionPane.showMessageDialog(Game.game.view.PanelInGame.frameInGame.getFrame(), "wave 5 is finished", "finish wave", JOptionPane.WARNING_MESSAGE);
        new boss();
    }

    public static Spawn getSpawn() {
        if (spawn == null) {
            spawn = new Spawn();
        }
        return spawn;
    }
}