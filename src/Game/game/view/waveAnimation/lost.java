//package Game.game.view.waveAnimation;
//
//import Game.game.Contoroler.control.Update;
//import Game.game.model.characterModel.epsilonFriend.Epsilon;
//import Game.game.view.characterView.DrawAble;
//import Game.login.SkillTree;
//
//import java.awt.*;
//
//import static Game.game.Contoroler.control.Controller.findObjectView;
//
//public class lost extends Animation {
//    public lost () {
//        super (new Dimension (350, 75), "src\\sources\\photo\\lost.png", "src\\sources\\song\\lost.wav", 800000);
//        Update.FRAME_UPDATE.stop ();
//        Update.MODEL_UPDATE.stop ();
//        SkillTree.increaseTotalExp ((int) Epsilon.getEpsilon ().getTotalExp ());
//        Update.finish = false;
//        DrawAble.DRAW_ABLES.remove (findObjectView (Epsilon.getEpsilon ().getId ()));
//        new lostPanel ().run ();
//    }
//
//}
