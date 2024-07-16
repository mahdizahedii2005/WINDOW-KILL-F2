import Game.application;

import java.util.ArrayList;

import static Game.game.Contoroler.control.Controller.swich;

public class Main {
    public static void main(String[] args) {
        new Thread(new application ()).start();
//        ArrayList<String> a = new ArrayList<>();
//        a.add("first");
//        a.add("seccond");
//        System.out.println(a);
//        swich(a, 0, 1);
//        System.out.println(a);
    }
}