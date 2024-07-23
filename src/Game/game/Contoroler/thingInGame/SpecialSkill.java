package Game.game.Contoroler.thingInGame;

import Game.Data.constants;
import Game.game.Contoroler.control.Update;
import Game.game.model.characterModel.Enemy.enemy;
import Game.game.model.characterModel.epsilonFriend.Epsilon;
import Game.game.view.inputListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Random;

import static Game.Data.constants.*;

public abstract class SpecialSkill implements Runnable {
    private static SpecialSkill currentSpecialSkill = new def1();
    private static boolean coolDown = false;
    static Timer coolDownTimer = new Timer (300000, new AbstractAction () {
        private boolean first = true;

        @Override
        public void actionPerformed (ActionEvent e) {
            if (first) {
                first = false;
                coolDown = true;
            } else {
                coolDown = false;
                ((Timer) e.getSource ()).stop ();
            }
        }
    });

    public static class atack1 extends SpecialSkill {

        Timer ActionTimer = new Timer (TIME_OF_INCREASE_DAMAGE, new AbstractAction () {
            private boolean first = true;

            @Override
            public void actionPerformed (ActionEvent e) {
                if (!coolDown) {
                    if (first) {
                        constants.ZaribOfEpsilonDamage = 2;
                        first = false;
                    } else {
                        constants.ZaribOfEpsilonDamage = 1;
                        coolDown = true;
                        coolDownTimer.start ();
                        ((Timer) e.getSource ()).stop ();
                    }
                } else {
                    System.out.println ("coolDown");
                    ((Timer) e.getSource ()).stop ();
                }
            }
        });

        @Override
        public void run () {
            ActionTimer.start ();
        }
    }

    public static class def1 extends SpecialSkill {
        Timer heel;
        private boolean first = true;

        public def1() {
            heel = new Timer (HeelTIME, new AbstractAction () {
                @Override
                public void actionPerformed (ActionEvent e) {
                    Epsilon.getEpsilon ().increaseHp (HeelRange);
                    System.out.println ("hi");
                }
            });
        }


        Timer ActionTimer = new Timer (60000, new AbstractAction () {
            @Override
            public void actionPerformed (ActionEvent e) {
                if (!coolDown) {
                    if (first) {
                        heel.start ();
                        first = false;
                    } else {
                        heel.stop ();
                        coolDown = true;
                        coolDownTimer.start ();
                        ((Timer) e.getSource ()).stop ();
                    }
                } else {
                    System.out.println ("coolDown");
                    ((Timer) e.getSource ()).stop ();
                }
            }
        });

        @Override
        public void run () {
            ActionTimer.start ();
        }
    }

    public static class atack2 extends SpecialSkill {
        Thread ActionTimer = new Thread(new Runnable() {
            @Override
            public void run() {
                Update.enemyTakeDamage = true;
                inputListener.coolDownn = true;
                try {
                    Thread.sleep(15000);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                Update.enemyTakeDamage = false;
                try {
                    Thread.sleep(75000);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                coolDown = false;
            }
        });

        @Override
        public void run() {
            ActionTimer.start();
        }
    }

    public static class def2 extends SpecialSkill {
        Thread ActionEvent = new Thread(new Runnable() {
            @Override
            public void run() {
                Epsilon.getEpsilon().probeblbyDontTakeDamage = true;
                inputListener.coolDownn = true;
                try {
                    Thread.sleep(15000);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                Epsilon.getEpsilon().probeblbyDontTakeDamage = false;
                try {
                    Thread.sleep(75000);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                coolDown = false;
            }
        });

        @Override
        public void run() {
            ActionEvent.start();
        }
    }

    public static class def3 extends SpecialSkill {
        Thread ActionEvent = new Thread(new Runnable() {
            @Override
            public void run() {
                enemy.HEEL_THE_EPSILON = true;
                inputListener.coolDownn = true;
                try {
                    Thread.sleep(15000);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                enemy.HEEL_THE_EPSILON = false;
                try {
                    Thread.sleep(75000);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                coolDown = false;
            }
        });

        @Override
        public void run() {
            ActionEvent.start();
        }
    }

    public static class tagir2 extends SpecialSkill {
        Thread ActionEvent = new Thread(new Runnable() {
            @Override
            public void run() {
                var saveR = Epsilon.getEpsilon().getRadius();
                Epsilon.getEpsilon().setRadius(saveR * (9d / 10));
                inputListener.coolDownn = true;
                try {
                    Thread.sleep(15000);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                Epsilon.getEpsilon().setRadius((double) saveR);
                try {
                    Thread.sleep(75000);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                coolDown = false;
            }
        });

        @Override
        public void run() {
            ActionEvent.start();
        }
    }

    public static class tagir3 extends SpecialSkill {
        SpecialSkill specialSkill1 = null;
        SpecialSkill specialSkill2 = null;

        public tagir3() {
            switch (new Random().nextInt(6)) {
                case 0:
                    specialSkill1 = new atack1();
                case 1:
                    specialSkill1 = new def1();
                case 2:
                    specialSkill1 = new atack2();
                case 3:
                    specialSkill1 = new def2();
                case 4:
                    specialSkill1 = new def3();
                case 5:
                    specialSkill1 = new tagir2();
            }
            switch (new Random().nextInt(6)) {
                case 0:
                    specialSkill2 = new atack1();
                case 1:
                    specialSkill2 = new def1();
                case 2:
                    specialSkill2 = new atack2();
                case 3:
                    specialSkill2 = new def2();
                case 4:
                    specialSkill2 = new def3();
                case 5:
                    specialSkill2 = new tagir2();
            }
        }

        @Override
        public void run() {
            if (new Random().nextBoolean()) {
                specialSkill2.run();
            } else {
                specialSkill1.run();
            }
        }
    }


//    public static class skill3 extends SpecialSkill {
//        private boolean first;
//        Timer ActionTimer;
//
//        public skill3 () {
//            first = true;
//            ActionTimer = new Timer (30000, new AbstractAction () {
//                @Override
//                public void actionPerformed (ActionEvent e) {
//                    System.out.println ("iii");
//                    if (!coolDown) {
//                        if (first) {
//                            new rasEpsilon (NUMBER_OF_RAS);
//                            first = false;
//                        } else {
//                            rasEpsilon.clear ();
//                            coolDown = true;
//                            coolDownTimer.start ();
//                            ((Timer) e.getSource ()).stop ();
//                        }
//                    } else {
//                        System.out.println ("coolDown");
//                        ((Timer) e.getSource ()).stop ();
//                    }
//                }
//            });
//        }
//
//        @Override
//        public void run () {
//            ActionTimer.start ();
//            if (!coolDown) {
//                if (first) {
//                    new rasEpsilon (NUMBER_OF_RAS);
//                    first = false;
//                }
//            }
//        }
//    }

    public static SpecialSkill getCurrentSpecialSkill () {
        return currentSpecialSkill;
    }

    public static void setCurrentSpecialSkill (SpecialSkill currentSpecialSkilll) {
        currentSpecialSkill = currentSpecialSkilll;
    }
}
