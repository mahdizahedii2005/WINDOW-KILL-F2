package Game.game.Contoroler.building;


import javax.swing.*;
import java.awt.event.ActionEvent;


public class Spawn {
    public static boolean Start = false;

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
                firstWave.start ();
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


    private javax.swing.Timer firstWave = new javax.swing.Timer(1000, new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (TOTAL_NUMBER_OF_ENEMY_1 > 0) {
                if (CurrentEnemy < NUMBER_OF_ENEMY_1) {
                    if (SQNumEnemy < NUMBER_OF_ENEMY_1 * sqPercent) {
                        findPlace findPlace = new findPlace();
                        BuilderHelper.squarantineBuilder(findPlace.getP1(), findPlace.getP2());
                        IncreaseSQNumEnemy();
                        return;
                    }
                    if (TRNumEnemy < TOTAL_NUMBER_OF_ENEMY_1 * trPercent) {
                        findPlace findPlace = new findPlace();
                        BuilderHelper.trigorathBuilder(findPlace.getP1(), findPlace.getP2());
                        IncreaseTRNumEnemy();
                    }
                }
            } else {
                if (CurrentEnemy == 0) {
                    StopWave1();
                }
            }
        }
    });
    private javax.swing.Timer secondWave = new javax.swing.Timer(800, new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (TOTAL_NUMBER_OF_ENEMY_2 > 0) {
                if (CurrentEnemy < NUMBER_OF_ENEMY_2) {
                    if (SQNumEnemy < NUMBER_OF_ENEMY_2 * sqPercent) {
                        findPlace findPlace = new findPlace();
                        BuilderHelper.squarantineBuilder(findPlace.getP1(), findPlace.getP2());
                        IncreaseSQNumEnemy();
                        return;
                    }
                    if (TRNumEnemy < TOTAL_NUMBER_OF_ENEMY_2 * trPercent) {
                        findPlace findPlace = new findPlace();
                        BuilderHelper.trigorathBuilder(findPlace.getP1(), findPlace.getP2());
                        IncreaseTRNumEnemy();
                    }
                }
            } else if (CurrentEnemy == 0) {
                StopWave2();
            }
        }
    });
    private javax.swing.Timer thirdWave = new javax.swing.Timer(600, new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (TOTAL_NUMBER_OF_ENEMY_3 > 0) {
                if (CurrentEnemy < NUMBER_OF_ENEMY_3) {
                    if (SQNumEnemy < NUMBER_OF_ENEMY_3 * sqPercent) {
                        findPlace findPlace = new findPlace();
                        BuilderHelper.squarantineBuilder(findPlace.getP1(), findPlace.getP2());
                        IncreaseSQNumEnemy();
                        return;
                    }
                    if (TRNumEnemy < TOTAL_NUMBER_OF_ENEMY_3 * trPercent) {
                        findPlace findPlace = new findPlace();
                        BuilderHelper.trigorathBuilder(findPlace.getP1(), findPlace.getP2());
                        IncreaseTRNumEnemy();
                    }
                }
            } else if (CurrentEnemy == 0) {
                StopWave3();
            }
        }
    });

    private void StopWave1() {
        System.out.println("stop wave 1");
        firstWave.stop();
//        new StartWave2();
        while (true) {
            if (Start) {
                spawnstate = spawnState.second;
                secondWave.start();
                Start = false;
                break;
            }
        }
    }

    private void StopWave2() {
        System.out.println("stop wave 2");
        secondWave.stop();
//        new startWave3();
        while (true) {
            if (Start) {
                spawnstate = spawnState.third;
                thirdWave.start();
                Start = false;
                break;
            }
        }
    }

    private void StopWave3() {
//        new win();
        thirdWave.stop();
        spawnstate = spawnState.finish;

        // TODO: ۱۴/۰۴/۲۰۲۴ efect of end wave and start the another wave
        // TODO: ۱۴/۰۴/۲۰۲۴ end game
    }

    public static Spawn getSpawn() {
        if (spawn == null) {
            spawn = new Spawn();
        }
        return spawn;
    }

    public void DecreaseSQNumEnemy() {
        this.SQNumEnemy--;
        CurrentEnemy--;
        switch (spawnstate) {
            case first -> TOTAL_NUMBER_OF_ENEMY_1--;
            case second -> TOTAL_NUMBER_OF_ENEMY_2--;
            case third -> TOTAL_NUMBER_OF_ENEMY_3--;
        }
    }

    public void IncreaseSQNumEnemy() {
        this.SQNumEnemy++;
        CurrentEnemy++;
    }

    public void DecreaseTRNumEnemy() {
        this.TRNumEnemy--;
        CurrentEnemy--;
        switch (spawnstate) {
            case first -> TOTAL_NUMBER_OF_ENEMY_1--;
            case second -> TOTAL_NUMBER_OF_ENEMY_2--;
            case third -> TOTAL_NUMBER_OF_ENEMY_3--;
        }

    }

    public void IncreaseTRNumEnemy() {
        this.TRNumEnemy++;
        CurrentEnemy++;

    }
}