package dk.easv.bll.bot;

import dk.easv.bll.field.IField;
import dk.easv.bll.game.GameState;
import dk.easv.bll.game.IGameState;
import dk.easv.bll.move.IMove;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class AutzenBot implements IBot {
    final int moveTimeMs = 500;
    private String BOT_NAME = getClass().getSimpleName();

    private GameSimulator createSimulator(IGameState state) {
        GameSimulator simulator = new GameSimulator(new GameState());
        simulator.setGameOver(GameOverState.Active);
        simulator.setCurrentPlayer(state.getMoveNumber() % 2);
        simulator.getCurrentState().setRoundNumber(state.getRoundNumber());
        simulator.getCurrentState().setMoveNumber(state.getMoveNumber());
        simulator.getCurrentState().getField().setBoard(state.getField().getBoard());
        simulator.getCurrentState().getField().setMacroboard(state.getField().getMacroboard());
        return simulator;
    }

    @Override
    public IMove doMove(IGameState state) {
        return calculateWinningMove(state, moveTimeMs);
    }
    // Plays single games until it wins and returns the first move for that. If iterations reached with no clear win, just return random valid move
    private IMove calculateWinningMove(IGameState state, int maxTimeMs){
        long time = System.currentTimeMillis();
        Random rand = new Random();

        //variables tracking best move found during sims
        IMove bestMove = null;
        int bestScore = Integer.MIN_VALUE; //init with lowest score.

        while (System.currentTimeMillis() < time + maxTimeMs) { // check how much time has passed, stop if over maxTimeMs
            GameSimulator simulator = createSimulator(state);
            IGameState gs = simulator.getCurrentState();
            List<IMove> moves = gs.getField().getAvailableMoves();
            IMove randomMove = moves.get(rand.nextInt(moves.size()));
            IMove initialMove = selectMoveBasedOnHeuristics(simulator.getCurrentState(), moves);

            while (simulator.getGameOver()==GameOverState.Active){ // Game not ended
                simulator.updateGame(initialMove);

                // Opponent plays randomly
                if (simulator.getGameOver()==GameOverState.Active){ // game still going
                    moves = gs.getField().getAvailableMoves();
                    IMove randomMoveOpponent = moves.get(rand.nextInt(moves.size()));
                    simulator.updateGame(randomMoveOpponent);
                }
                if (simulator.getGameOver()==GameOverState.Active){ // game still going
                    moves = gs.getField().getAvailableMoves();
                    randomMove = moves.get(rand.nextInt(moves.size()));
                }
            }

            if (simulator.getGameOver()==GameOverState.Win){
                int score = scoreWin(simulator.getCurrentState(), simulator.getCurrentState().getMoveNumber(), simulator);
                if (score > bestScore){
                    bestScore = score;
                    bestMove = initialMove;
                }
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        if (bestMove != null){
            return bestMove;
        }

        List<IMove> moves = state.getField().getAvailableMoves();
        IMove randomMovePlayer = moves.get(rand.nextInt(moves.size()));
        return randomMovePlayer; // just play randomly if solution not found
    }

    private IMove selectMoveBasedOnHeuristics(IGameState state, List<IMove> moves) {
        IMove bestMove = null;
        int bestScore = Integer.MIN_VALUE;

        for (IMove move : moves) {
            int score = evaluateMove(state, move);
            if (score > bestScore) {
                bestScore = score;
                bestMove = move;
            }
        }

        return bestMove != null ? bestMove : moves.get(new Random().nextInt(moves.size())); // Fallback to random move
    }

    private int evaluateMove(IGameState state, IMove move) {
        int score = 0;
        // Adjust weights based on importance
        if (formsThreeInALine(state, move)) score += 7;
        if (blocksOpponentThreeInALine(state, move)) score += 5;
        if (isCenterPosition(move)) score += 2;
        //if (createsForkOpportunity(state, move)) score += 3;
        if (isCornerPosition(move)) score += 1;

        // Goes for center
        if (move.getX() == 1) score += 2;

        // Goes for center row
        if (move.getY() == 1) score += 2;

        return score;
    }

    private boolean formsThreeInALine(IGameState state, IMove move) {
        String[][] board = state.getField().getBoard();
        String currentPlayer = (state.getMoveNumber() % 2 == 0) ? "0" : "1";


        int x = move.getX();
        int y = move.getY();

        // Check for vertical alignment
        if (y >= 2 && board[x][y-2].equals(currentPlayer) && board[x][y-1].equals(currentPlayer)) {
            return true;
        }
        if (y <= board.length - 3 && board[x][y+1].equals(currentPlayer) && board[x][y+2].equals(currentPlayer)) {
            return true;
        }
        if (y > 0 && y < board.length - 1 && board[x][y-1].equals(currentPlayer) && board[x][y+1].equals(currentPlayer)) {
            return true;
        }

        // Check for horizontal alignment
        if (x >= 2 && board[x-2][y].equals(currentPlayer) && board[x-1][y].equals(currentPlayer)) {
            return true;
        }
        if (x <= board[0].length - 3 && board[x+1][y].equals(currentPlayer) && board[x+2][y].equals(currentPlayer)) {
            return true;
        }
        if (x > 0 && x < board[0].length - 1 && board[x-1][y].equals(currentPlayer) && board[x+1][y].equals(currentPlayer)) {
            return true;
        }

        // Check for diagonal alignment (top-left to bottom-right)
        if (x >= 2 && y >= 2 && board[x-2][y-2].equals(currentPlayer) && board[x-1][y-1].equals(currentPlayer)) {
            return true;
        }
        if (x <= board.length - 3 && y <= board[0].length - 3 && board[x+1][y+1].equals(currentPlayer) && board[x+2][y+2].equals(currentPlayer)) {
            return true;
        }

        // Check for diagonal alignment (bottom-left to top-right)
        if (x >= 2 && y <= board[0].length - 3 && board[x-2][y+2].equals(currentPlayer) && board[x-1][y+1].equals(currentPlayer)) {
            return true;
        }
        if (x <= board.length - 3 && y >= 2 && board[x+1][y-1].equals(currentPlayer) && board[x+2][y-2].equals(currentPlayer)) {
            return true;
        }

        return false;
    }

    private boolean blocksOpponentThreeInALine(IGameState state, IMove move) {
        String[][] board = state.getField().getBoard();
        String opponent = (state.getMoveNumber() % 2 == 1) ? "0" : "1";
        int x = move.getX();
        int y = move.getY();

        // Check for vertical alignment
        if (y >= 2 && board[x][y-2].equals(opponent) && board[x][y-1].equals(opponent)) {
            return true;
        }
        if (y <= board.length - 3 && board[x][y+1].equals(opponent) && board[x][y+2].equals(opponent)) {
            return true;
        }
        if (y > 0 && y < board.length - 1 && board[x][y-1].equals(opponent) && board[x][y+1].equals(opponent)) {
            return true;
        }

        // Check for horizontal alignment
        if (x >= 2 && board[x-2][y].equals(opponent) && board[x-1][y].equals(opponent)) {
            return true;
        }
        if (x <= board[0].length - 3 && board[x+1][y].equals(opponent) && board[x+2][y].equals(opponent)) {
            return true;
        }
        if (x > 0 && x < board[0].length - 1 && board[x-1][y].equals(opponent) && board[x+1][y].equals(opponent)) {
            return true;
        }

        // Check for diagonal alignment (top-left to bottom-right)
        if (x >= 2 && y >= 2 && board[x-2][y-2].equals(opponent) && board[x-1][y-1].equals(opponent)) {
            return true;
        }
        if (x <= board.length - 3 && y <= board[0].length - 3 && board[x+1][y+1].equals(opponent) && board[x+2][y+2].equals(opponent)) {
            return true;
        }

        // Check for diagonal alignment (bottom-left to top-right)
        if (x >= 2 && y <= board[0].length - 3 && board[x-2][y+2].equals(opponent) && board[x-1][y+1].equals(opponent)) {
            return true;
        }
        if (x <= board.length - 3 && y >= 2 && board[x+1][y-1].equals(opponent) && board[x+2][y-2].equals(opponent)) {
            return true;
        }

        return false;

    }

    private boolean isCenterPosition(IMove move) {
        return move.getX() == 1 && move.getY() == 1; // Assuming 0-indexed, 3x3 board.
    }

    private boolean createsForkOpportunity(IGameState state, IMove move) {
        String[][] board = state.getField().getBoard();
        int playerToMove = state.getMoveNumber() % 2;
        int oppositePlayer = (playerToMove + 1) % 2;

        // Check rows, columns, and diagonals for the player's marks
        for (int i = 0; i < 3; i++) {
            if ((board[move.getX()][i] == String.valueOf(playerToMove) &&
                    hasTwoInARow(board, playerToMove, move.getX(), i)) ||

                    (board[i][move.getY()] == String.valueOf(playerToMove) &&
                            hasTwoInARow(board, playerToMove, i, move.getY()))) {

                // Check if the opponent has an available move that would block the fork
                for (int dx = -1; dx <= 1; dx++) {
                    for (int dy = -1; dy <= 1; dy++) {
                        if ((dx != 0 || dy != 0) &&
                                board[move.getX() + dx][move.getY() + dy].equals(String.valueOf(oppositePlayer))) {
                            return true;
                        }
                    }
                }
            }
        }

        // Check the two main diagonals
        if ((move.getX() == move.getY() || move.getX() == 2 - move.getY()) &&
                hasTwoInARow(board, playerToMove, move.getX(), move.getY())) {

            // Check if the opponent has an available move that would block the fork
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    if ((dx != 0 || dy != 0) &&
                            board[move.getX() + dx][move.getY() + dy].equals(String.valueOf(oppositePlayer))) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean hasTwoInARow(String[][] board, int player, int x, int y) {
        return (board[x][y].equals(String.valueOf(player)) &&
                (board[x + 1][y].equals(String.valueOf(player)) ||
                        board[x - 1][y].equals(String.valueOf(player)) ||
                        board[x][y + 1].equals(String.valueOf(player)) ||
                        board[x][y - 1].equals(String.valueOf(player))));
    }

    private boolean isCornerPosition(IMove move) {
        return (move.getX() % 2 == 0 && move.getY() % 2 == 0); // Assuming 0-indexed, 3x3 board.
    }

    private int scoreWin(IGameState finalState, int moveCount, GameSimulator simulator){
        int score = 0;
        //win speed
        score += (100 - moveCount);



        return score;
    }

    /*
        The code below is a simulator for simulation of gameplay. This is needed for AI.

        It is put here to make the Bot independent of the GameManager and its subclasses/enums

        Now this class is only dependent on a few interfaces: IMove, IField, and IGameState

        You could say it is self-contained. The drawback is that if the game rules change, the simulator must be
        changed accordingly, making the code redundant.

     */

    @Override
    public String getBotName() {
        return BOT_NAME;
    }

    public enum GameOverState {
        Active,
        Win,
        Tie
    }

    public class Move implements IMove {
        int x = 0;
        int y = 0;

        public Move(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int getX() {
            return x;
        }

        @Override
        public int getY() {
            return y;
        }

        @Override
        public String toString() {
            return "(" + x + "," + y + ")";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Move move = (Move) o;
            return x == move.x && y == move.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    class GameSimulator {
        private final IGameState currentState;
        private int currentPlayer = 0; //player0 == 0 && player1 == 1
        private volatile GameOverState gameOver = GameOverState.Active;

        public void setGameOver(GameOverState state) {
            gameOver = state;
        }

        public GameOverState getGameOver() {
            return gameOver;
        }

        public void setCurrentPlayer(int player) {
            currentPlayer = player;
        }

        public IGameState getCurrentState() {
            return currentState;
        }

        public GameSimulator(IGameState currentState) {
            this.currentState = currentState;
        }

        public Boolean updateGame(IMove move) {
            if (!verifyMoveLegality(move))
                return false;

            updateBoard(move);
            currentPlayer = (currentPlayer + 1) % 2;

            return true;
        }

        private Boolean verifyMoveLegality(IMove move) {
            IField field = currentState.getField();
            boolean isValid = field.isInActiveMicroboard(move.getX(), move.getY());

            if (isValid && (move.getX() < 0 || 9 <= move.getX())) isValid = false;
            if (isValid && (move.getY() < 0 || 9 <= move.getY())) isValid = false;

            if (isValid && !field.getBoard()[move.getX()][move.getY()].equals(IField.EMPTY_FIELD))
                isValid = false;

            return isValid;
        }

        private void updateBoard(IMove move) {
            String[][] board = currentState.getField().getBoard();
            board[move.getX()][move.getY()] = currentPlayer + "";
            currentState.setMoveNumber(currentState.getMoveNumber() + 1);
            if (currentState.getMoveNumber() % 2 == 0) {
                currentState.setRoundNumber(currentState.getRoundNumber() + 1);
            }
            checkAndUpdateIfWin(move);
            updateMacroboard(move);

        }

        private void checkAndUpdateIfWin(IMove move) {
            String[][] macroBoard = currentState.getField().getMacroboard();
            int macroX = move.getX() / 3;
            int macroY = move.getY() / 3;

            if (macroBoard[macroX][macroY].equals(IField.EMPTY_FIELD) ||
                    macroBoard[macroX][macroY].equals(IField.AVAILABLE_FIELD)) {

                String[][] board = getCurrentState().getField().getBoard();

                if (isWin(board, move, "" + currentPlayer))
                    macroBoard[macroX][macroY] = currentPlayer + "";
                else if (isTie(board, move))
                    macroBoard[macroX][macroY] = "TIE";

                //Check macro win
                if (isWin(macroBoard, new Move(macroX, macroY), "" + currentPlayer))
                    gameOver = GameOverState.Win;
                else if (isTie(macroBoard, new Move(macroX, macroY)))
                    gameOver = GameOverState.Tie;
            }

        }

        private boolean isTie(String[][] board, IMove move) {
            int localX = move.getX() % 3;
            int localY = move.getY() % 3;
            int startX = move.getX() - (localX);
            int startY = move.getY() - (localY);

            for (int i = startX; i < startX + 3; i++) {
                for (int k = startY; k < startY + 3; k++) {
                    if (board[i][k].equals(IField.AVAILABLE_FIELD) ||
                            board[i][k].equals(IField.EMPTY_FIELD))
                        return false;
                }
            }
            return true;
        }


        public boolean isWin(String[][] board, IMove move, String currentPlayer) {
            int localX = move.getX() % 3;
            int localY = move.getY() % 3;
            int startX = move.getX() - (localX);
            int startY = move.getY() - (localY);

            //check col
            for (int i = startY; i < startY + 3; i++) {
                if (!board[move.getX()][i].equals(currentPlayer))
                    break;
                if (i == startY + 3 - 1) return true;
            }

            //check row
            for (int i = startX; i < startX + 3; i++) {
                if (!board[i][move.getY()].equals(currentPlayer))
                    break;
                if (i == startX + 3 - 1) return true;
            }

            //check diagonal
            if (localX == localY) {
                //we're on a diagonal
                int y = startY;
                for (int i = startX; i < startX + 3; i++) {
                    if (!board[i][y++].equals(currentPlayer))
                        break;
                    if (i == startX + 3 - 1) return true;
                }
            }

            //check anti diagonal
            if (localX + localY == 3 - 1) {
                int less = 0;
                for (int i = startX; i < startX + 3; i++) {
                    if (!board[i][(startY + 2) - less++].equals(currentPlayer))
                        break;
                    if (i == startX + 3 - 1) return true;
                }
            }
            return false;
        }

        private void updateMacroboard(IMove move) {
            String[][] macroBoard = currentState.getField().getMacroboard();
            for (int i = 0; i < macroBoard.length; i++)
                for (int k = 0; k < macroBoard[i].length; k++) {
                    if (macroBoard[i][k].equals(IField.AVAILABLE_FIELD))
                        macroBoard[i][k] = IField.EMPTY_FIELD;
                }

            int xTrans = move.getX() % 3;
            int yTrans = move.getY() % 3;

            if (macroBoard[xTrans][yTrans].equals(IField.EMPTY_FIELD))
                macroBoard[xTrans][yTrans] = IField.AVAILABLE_FIELD;
            else {
                // Field is already won, set all fields not won to avail.
                for (int i = 0; i < macroBoard.length; i++)
                    for (int k = 0; k < macroBoard[i].length; k++) {
                        if (macroBoard[i][k].equals(IField.EMPTY_FIELD))
                            macroBoard[i][k] = IField.AVAILABLE_FIELD;
                    }
            }
        }
    }

}