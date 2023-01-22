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

    private final Random random = new Random(0xdeadbeef);

    private static Color getOpponentColor(Color col) {
        if (Color.PLAYER1 == col)
            return Color.PLAYER1;
        else
            return Color.PLAYER2;
    }

    @Override
    public String getName() {
        return "Mikołaj Felczyński 147400";
    }

    @Override
    public Move nextMove(Board b) {
        List<Move> moves = b.getMovesFor(getColor());

        // skopiowanie tablicy

        // wybranie losowego ruchu
        // stworzenie tablicy przejrzanych ruchów
        Map<Move, Integer> calculatedMoves = new HashMap<>();
        for (Move m : moves) {
            Board bClone = b.clone();
            bClone.doMove(m);
            calculatedMoves.put(m, bClone.getMovesFor(MFNaivePlayer.getOpponent(getOpponentColor(getColor()))).size());
        }
        Integer minVal = Collections.min(calculatedMoves.values());

        for (Map.Entry<Move, Integer> entry : calculatedMoves.entrySet()) {
            if (entry.getValue() == minVal) {
                return entry.getKey();
            }
        }
        return null;
    /*

                 // wyszukiwanie ruchu w tablicy przejrzanych ruchów
        for (Move m : moves) {
            if (calculatedMoves.containsKey(m)) {
        List<Move> usedMoves = new ArrayList<>();
        // zastosowanie ruchu na zapasowej tablicy
        bClone.doMove(initMove);
        if (bClone.getMovesFor(MFNaivePlayer.getOpponent(getOpponentColor(getColor()))).size() < b.getMovesFor(MFNaivePlayer.getOpponent(getOpponentColor(getColor()))).size()) {
            return initMove;
        } else {
            usedMoves.add(initMove);
            Move newMove;
            do {
                newMove = bCloneMoves.get(random.nextInt(bCloneMoves.size()));
            } while (usedMoves.contains(newMove));

        }


        return moves.get(random.nextInt(moves.size()));
    }*/
    }
}