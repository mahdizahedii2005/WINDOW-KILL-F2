//package Game.game.model.characterModel;
//
//import Game.game.model.collision.Collidable;
//
//import java.awt.geom.Point2D;
//import java.util.ArrayList;
//import java.util.UUID;
//
//import static Game.game.Contoroler.control.Controller.addEpsilonRas;
////TODO must del this class
//public class rasEpsilon {
//    int number = 0;
//    public static ArrayList<ras> rasArrayList = new ArrayList<> ();
//
//    public rasEpsilon (int number) {
//        this.number = number;
//        if (number > 0) {
//            new ras (new Point2D.Double (Epsilon.getEpsilon ().getCenter ().x, Epsilon.getEpsilon ().getCenter ().y - Epsilon.getEpsilon ().getRadius ()));
//            if (number > 1) {
//                new ras (new Point2D.Double (Epsilon.getEpsilon ().getCenter ().x, Epsilon.getEpsilon ().getCenter ().y + Epsilon.getEpsilon ().getRadius ()));
//                if (number > 3) {
//                    new ras (new Point2D.Double (Epsilon.getEpsilon ().getCenter ().x + Epsilon.getEpsilon ().getRadius (), Epsilon.getEpsilon ().getCenter ().y));
//                    new ras (new Point2D.Double (Epsilon.getEpsilon ().getCenter ().x - Epsilon.getEpsilon ().getRadius (), Epsilon.getEpsilon ().getCenter ().y));
//                }
//            }
//        }
//    }
//
//    public static void clear () {
//        for (int i = 0 ; i < rasArrayList.size () ;) {
//            rasArrayList.get (i).Die ();
//        }
//    }
//
//    public static void UpdateRAS () {
//        int number = rasArrayList.size ();
//        if (number > 0) {
//            rasArrayList.get (0).setCenter (new Point2D.Double (Epsilon.getEpsilon ().getCenter ().x, Epsilon.getEpsilon ().getCenter ().y - Epsilon.getEpsilon ().getRadius ()));
//            if (number > 1) {
//                rasArrayList.get (1).setCenter (new Point2D.Double (Epsilon.getEpsilon ().getCenter ().x, Epsilon.getEpsilon ().getCenter ().y + Epsilon.getEpsilon ().getRadius ()));
//                if (number > 3) {
//                    rasArrayList.get (2).setCenter (new Point2D.Double (Epsilon.getEpsilon ().getCenter ().x + Epsilon.getEpsilon ().getRadius (), Epsilon.getEpsilon ().getCenter ().y));
//                    rasArrayList.get (3).setCenter (new Point2D.Double (Epsilon.getEpsilon ().getCenter ().x - Epsilon.getEpsilon ().getRadius (), Epsilon.getEpsilon ().getCenter ().y));
//                }
//            }
//        }
//    }
//
//    public class ras extends ObjectInGame implements Collidable {
//
//        public ras (Point2D.Double center) {
//            super (center, Epsilon.getEpsilon ().getColor (), UUID.randomUUID ().toString (), 1, 2,true);
//            rasArrayList.add (this);
//            addEpsilonRas (id);
//            number++;
//        }
//
//        @Override
//        public void Die () {
//            super.Die ();
//            rasArrayList.remove (this);
//            number--;
//        }
//    }
//}