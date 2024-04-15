package Game.game.Contoroler;

import Game.game.model.characterModel.SquarantineModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.geom.Point2D;
import java.util.Timer;

import static java.lang.Double.NaN;


public class Spawn {
    public Spawn () {
        firstWave.start ();
    }

    enum spawnState {
        first, second, third, finish
    }

    private static Spawn spawn;
    private spawnState spawnstate = spawnState.first;
    private int TOTAL_NUMBER_OF_ENEMY_1 = 25;
    private int TOTAL_NUMBER_OF_ENEMY_2 = 35;
    private int TOTAL_NUMBER_OF_ENEMY_3 = 50;
    private final int NUMBER_OF_ENEMY_1 = 6;
    private final int NUMBER_OF_ENEMY_2 = 9;
    private final int NUMBER_OF_ENEMY_3 = 15;
    private int CurrentEnemy = 0;
    private int SQNumEnemy = 0;
    private int TRNumEnemy = 0;
    private final double sqPercent = 0.6;
    private final double trPercent = 0.4;


    private javax.swing.Timer firstWave = new javax.swing.Timer (1000, new AbstractAction () {
        @Override
        public void actionPerformed (ActionEvent e) {
            if (TOTAL_NUMBER_OF_ENEMY_1 > 0) {
                if (CurrentEnemy < NUMBER_OF_ENEMY_1) {
                    if (SQNumEnemy < NUMBER_OF_ENEMY_1 * sqPercent) {
                        findPlace findPlace = new findPlace ();
                        if (findPlace.getP1 ().getX () == NaN || findPlace.getP1 ().getY () == NaN || findPlace.getP2 ().getX () == NaN || findPlace.getP2 ().getY () == NaN) {
                            findPlace = new findPlace (new Point2D.Double (0, 0), new Point2D.Double (5, 15));
                        }
                        BuilderHelper.squarantineBuilder (findPlace.getP1 (), findPlace.getP2 ());
                        IncreaseSQNumEnemy ();
                        return;
                    }
                    if (TRNumEnemy < TOTAL_NUMBER_OF_ENEMY_1 * trPercent) {
                        findPlace findPlace = new findPlace ();
                        if (findPlace.getP1 ().getX () == NaN || findPlace.getP1 ().getY () == NaN || findPlace.getP2 ().getX () == NaN || findPlace.getP2 ().getY () == NaN) {
                            findPlace = new findPlace (new Point2D.Double (0, 0), new Point2D.Double (5, 15));
                        }
                        BuilderHelper.trigorathBuilder (findPlace.getP1 (), findPlace.getP2 ());
                        IncreaseTRNumEnemy ();
                    }
                }
            } else {
                StopWave1 ();
            }
        }
    });
    private javax.swing.Timer secondWave = new javax.swing.Timer (800, new AbstractAction () {
        @Override
        public void actionPerformed (ActionEvent e) {

        }
    });
    private javax.swing.Timer thirdWave = new javax.swing.Timer (600, new AbstractAction () {
        @Override
        public void actionPerformed (ActionEvent e) {

        }
    });

    private void StopWave1 () {
        firstWave.stop ();
        // TODO: ۱۴/۰۴/۲۰۲۴ efect of end wave and start the another wave
        spawnstate = spawnState.second;
        secondWave.start ();
    }

    private void StopWave2 () {
        secondWave.stop ();
        // TODO: ۱۴/۰۴/۲۰۲۴ efect of end wave and start the another wave
        spawnstate = spawnState.third;
        thirdWave.stop ();
    }

    private void StopWave3 () {
        thirdWave.stop ();
        spawnstate = spawnState.finish;

        // TODO: ۱۴/۰۴/۲۰۲۴ efect of end wave and start the another wave
        // TODO: ۱۴/۰۴/۲۰۲۴ end game
    }

    public static Spawn getSpawn () {
        if (spawn == null) {
            spawn = new Spawn ();
        }
        return spawn;
    }

    public void DecreaseSQNumEnemy () {
        this.SQNumEnemy--;
        CurrentEnemy--;
        switch (spawnstate) {
            case first -> TOTAL_NUMBER_OF_ENEMY_1--;
            case second -> TOTAL_NUMBER_OF_ENEMY_2--;
            case third -> TOTAL_NUMBER_OF_ENEMY_3--;
        }
    }

    public void IncreaseSQNumEnemy () {
        this.SQNumEnemy++;
        CurrentEnemy++;
    }

    public void DecreaseTRNumEnemy () {
        this.TRNumEnemy--;
        CurrentEnemy--;
        switch (spawnstate) {
            case first -> TOTAL_NUMBER_OF_ENEMY_1--;
            case second -> TOTAL_NUMBER_OF_ENEMY_2--;
            case third -> TOTAL_NUMBER_OF_ENEMY_3--;
        }

    }

    public void IncreaseTRNumEnemy () {
        this.TRNumEnemy++;
        CurrentEnemy++;

    }
}