package Game.game.Contoroler.thingInGame;

import Game.Data.constants;
import Game.game.model.characterModel.epsilonFriend.Epsilon;

import javax.swing.*;
import java.awt.event.ActionEvent;

import static Game.Data.constants.*;

public abstract class SpecialSkill implements Runnable {
    private static SpecialSkill currentSpecialSkill;
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

    public static class skill1 extends SpecialSkill {

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

    public static class skill2 extends SpecialSkill {
        Timer heel;
        private boolean first = true;

        public skill2 () {
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
