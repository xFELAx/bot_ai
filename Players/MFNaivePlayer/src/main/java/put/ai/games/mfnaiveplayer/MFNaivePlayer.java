/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package put.ai.games.mfnaiveplayer;

import put.ai.games.game.Board;
import put.ai.games.game.Move;
import put.ai.games.game.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MFNaivePlayer extends Player {

    private Random random = new Random(0xdeadbeef);


    @Override
    public String getName() {
        return "Mikołaj Felczyński 147400";
    }


    @Override
    public Move nextMove(Board b) {
        List<Move> moves = b.getMovesFor(getColor());
        Board bClone = b.clone();
        // skopiowanie tablicy
        List<Move> bCloneMoves = bClone.getMovesFor(getColor());
        // wybranie losowego ruchu
        Move initMove = bCloneMoves.get(random.nextInt(bCloneMoves.size()));
        // stworzenie tablicy przejrzanych ruchów
        List<Move> usedMoves = new ArrayList<>();
        // zastosowanie ruchu na zapasowej tablicy
        bClone.doMove(initMove);
        if (bClone.getMovesFor(MFNaivePlayer.getOpponent()) < moves.size()) {
            return initMove;
        } else {
            usedMoves.add(initMove);
            Move newMove;
            do  {
                newMove = bCloneMoves.get(random.nextInt(bCloneMoves.size()));
            }
            while (usedMoves.contains(newMove));

        }


        return moves.get(random.nextInt(moves.size()));
}
}
