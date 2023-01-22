/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package put.ai.games.mfnaiveplayer;

import put.ai.games.game.Board;
import put.ai.games.game.Move;
import put.ai.games.game.Player;

import java.util.*;

public class MFNaivePlayer extends Player {
    private static final int DIFF_TIME = 100;
    private final Random random = new Random(0xdeadbeef);



    @Override
    public String getName() {
        return "Mikołaj Felczyński 147400";
    }

    @Override
    public Move nextMove(Board b) {
        long startTime = System.nanoTime();
        List<Move> moves = b.getMovesFor(getColor());
//System.out.println("GETTIME "+getTime());

        // skopiowanie tablicy

        // wybranie losowego ruchu
        // stworzenie tablicy przejrzanych ruchów
        Map<Move, Integer> calculatedMoves = new HashMap<>();
        for (Move m : moves) {
            Board bClone = b.clone();
            bClone.doMove(m);
            calculatedMoves.put(m, bClone.getMovesFor(MFNaivePlayer.getOpponent(getColor())).size());
            if ((System.nanoTime() - startTime)<(getTime()-DIFF_TIME)) {
          //      System.out.println("OK1 "+getTime());
                return moves.get(random.nextInt(moves.size()));
            }
        }
        Integer minVal = Collections.min(calculatedMoves.values());
        Map.Entry<Move, Integer> minMove = null;
        for (Map.Entry<Move, Integer> entry : calculatedMoves.entrySet()) {
            if (minMove == null) {
                minMove = entry;
            }
            if (entry.getValue().equals(minVal)) {
                //System.out.println("OK2");
                return entry.getKey();
            }
            if (minMove.getValue() > entry.getValue()) {
                minMove = entry;
            }
            if ((System.nanoTime() - startTime)<(getTime()-DIFF_TIME)) {
                //System.out.println("OK3");
                return minMove.getKey();
            }
        }
        //System.out.println("GETTIME2 "+getTime());

        return moves.get(random.nextInt(moves.size()));
    }
}