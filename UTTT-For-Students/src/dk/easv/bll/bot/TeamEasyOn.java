package dk.easv.bll.bot;

import dk.easv.bll.field.IField;
import dk.easv.bll.game.GameState;
import dk.easv.bll.game.IGameState;
import dk.easv.bll.move.IMove;
import dk.easv.bll.move.Move;

import java.util.*;

public class TeamEasyOn implements IBot {
    private static final int MOVE_TIME_MS = 1000; // 1 sec
    private static final String BOT_NAME = "Team Easy On";
    private static final String PLAYER_X = "0";
    private static final String PLAYER_O = "1";
    private static final String PLAYER_TIE = "-";

    private double explorationStrength = Math.sqrt(2);

    private Random rand;
    private int opponent;

    public TeamEasyOn() {
        rand = new Random();
    }

    public IGameState cloneState(IGameState state) {
        IGameState clonedState = new GameState();
        String[][] board = cloneBoard(state.getField().getBoard());
        String[][] macroBoard = cloneBoard(state.getField().getMacroboard());

        clonedState.getField().setBoard(board);
        clonedState.getField().setMacroboard(macroBoard);

        clonedState.setMoveNumber(state.getMoveNumber());
        clonedState.setRoundNumber(state.getRoundNumber());

        return clonedState;
    }

    public String[][] cloneBoard(String[][] board) {
        String[][] clonedBoard = new String[board.length][];
        for (int i = 0; i < board.length; i++) {
            clonedBoard[i] = new String[board[i].length];
            System.arraycopy(board[i], 0, clonedBoard[i], 0, board[i].length);
        }

        return clonedBoard;
    }

    class Node {
        IGameState state;
        Node parentNode;
        List<Node> childNodes;
        IMove move;

        double wins;
        double losses;
        double draws;
        double visits;

        double raveWins;
        double raveLosses;
        double raveDraws;
        double raveVisits;

        public Node(Node node) {
            this(node.getState());

            if (node.getParentNode() != null) {
                this.parentNode = node.getParentNode();
            }

            for (Node child : node.getChildNodes()) {
                this.childNodes.add(new Node(child));
            }

            wins = node.getWins();
            losses = node.losses;
            draws = node.draws;
            visits = node.getVisits();

            raveWins = node.raveWins;
            raveLosses = node.raveLosses;
            raveDraws = node.raveDraws;
            raveVisits = node.raveDraws;
        }

        public Node(IGameState state) {
            this.state = cloneState(state);

            this.childNodes = new ArrayList<>();

            this.wins = 0.0;
            this.losses = 0.0;
            this.draws = 0.0;
            this.visits = 0.0;

            this.raveWins = 0.0;
            this.raveLosses = 0.0;
            this.raveDraws = 0.0;
            this.raveVisits = 0.0;
        }

        public Node UCTSelectWithRAVE() {
            double maxScore = Double.NEGATIVE_INFINITY;
            Node selectedNode = null;
            for (Node childNode : childNodes) {
                double uctScore = UCT(childNode, visits);
                double raveScore = RAVE(childNode);
                double score = uctScore + raveScore;
                if (score > maxScore) {
                    maxScore = score;
                    selectedNode = childNode;
                }
            }
            return selectedNode;
        }

        private double RAVE(Node node) {
            if (node.raveVisits == 0) return Double.MAX_VALUE;

            double exploitationScore = (node.raveWins - node.raveLosses) / node.raveVisits;
            double explorationScore = explorationStrength * Math.sqrt(Math.log(visits) / node.raveVisits);
            return exploitationScore + explorationScore;
        }

        public void setMove(IMove move) {
            this.move = move;
        }

        public List<Node> getChildNodes() {
            return childNodes;
        }

        public Node getChildWithMaxScore() {
            return Collections.max(this.childNodes, Comparator.comparing(Node::getVisits));
        }

        public Node getRandomChild() {
            if (childNodes.isEmpty()) return this;

            return chooseRandom(childNodes);
        }

        public Node getParentNode() {
            return parentNode;
        }

        public void setParentNode(Node parentNode) {
            this.parentNode = parentNode;
        }

        public IGameState getState() {
            return state;
        }

        public void setWins(double wins) {
            this.wins = wins;
        }

        public double getWins() {
            return wins;
        }

        public double getVisits() {
            return visits;
        }

        public boolean isTerminal() {
            return isGameOver(state);
        }

        public boolean isLeaf() {
            return childNodes.isEmpty();
        }

        public void expand() {
            if (!isLeaf()) return;
            if (isTerminal()) return;

            List<IMove> availableMoves = state.getField().getAvailableMoves();
            for (IMove move : availableMoves) {
                Node childNode = new Node(state);
                childNode.setMove(move);
                childNode.setParentNode(this);
                childNodes.add(childNode);
                performMove(childNode.getState(), move);
            }
        }
    }

    public int currentPlayer(IGameState state) {
        return state.getMoveNumber() % 2;
    }

    public int nextPlayer(IGameState state) {
        return (state.getMoveNumber() + 1) % 2;
    }

    public <T> T chooseRandom(List<T> list) {
        return list.get(rand.nextInt(list.size()));
    }

    private double UCT(Node node, double parentVisits) {
        if (node.visits == 0) return Double.MAX_VALUE;

        double explorationParameter = calculateExplorationParameter(node);

        double raveWeight = calculateRAVEWeight(node);

        double exploitationScore = (node.wins - node.losses) / node.visits;

        double raveScore = (node.raveWins - node.raveLosses) / node.visits;
        double combinedScore = (1 - raveWeight) * exploitationScore + raveWeight * raveScore;

        double explorationScore = explorationParameter * Math.sqrt(Math.log(parentVisits) / node.visits);

        return combinedScore + explorationScore;
    }

    private double calculateExplorationParameter(Node node) {
        double baseExplorationParameter = explorationStrength;
        double scaleFactor = Math.sqrt(node.visits);
        return baseExplorationParameter * scaleFactor;
    }

    private double calculateRAVEWeight(Node node) {
        double maxRAVEWeight = 0.5;
        double minRAVEWeight = 0.1;
        double maxVisits = 10000;

        double normalizedVisits = Math.min(node.visits, maxVisits) / maxVisits; // 0 - 1
        double raveWeight = minRAVEWeight + (maxRAVEWeight - minRAVEWeight) * normalizedVisits;

        return raveWeight;
    }


    @Override
    public IMove doMove(IGameState state) {
        long time = System.currentTimeMillis();

        Node rootNode = new Node(state);
        opponent = nextPlayer(state);

        if (state.getMoveNumber() == 0) {
            if (isValid(state, new Move(4, 4))) {
                return new Move(4, 4);
            }
        }

        int iteration = 0;
        while (System.currentTimeMillis() < (time + MOVE_TIME_MS)) {
            // 1. Select
            Node leafNode = select(rootNode);

            // 2. Expand
            leafNode.expand();
            Node childNode = leafNode.getRandomChild();

            // 3. Rollout
            int outcome = rollout(childNode);

            // 4. Backpropagation
            backpropagationWithRAVE(childNode, outcome);
            iteration++;
        }
//        System.out.println("MCTS Rave Iterations: " + iteration);

        Node winnerNode = rootNode.getChildWithMaxScore();
        return winnerNode.move;
    }

    private Node select(Node rootNode) {
        Node node = rootNode;
        while (!node.getChildNodes().isEmpty()) {
            node = node.UCTSelectWithRAVE();
        }

        return node;
    }

    private int rollout(Node node) {
        Node tempNode = new Node(node);
        IGameState tempState = tempNode.getState();

        if (tempNode.isTerminal() && (nextPlayer(tempState) == opponent)) {
            tempNode.getParentNode().losses = Double.MAX_VALUE;
            tempNode.getParentNode().raveLosses = Double.MAX_VALUE;
            return opponent;
        }

        while (!isGameOver(tempState)) {
            IMove move = chooseRandom(tempState.getField().getAvailableMoves());
            performMove(tempState, move);
        }

        if (isDraw(tempState)) return -1;

        return nextPlayer(tempState);
    }

    public void backpropagationWithRAVE(Node node, int value) {
        Node tempNode = node;

        while (tempNode != null) {
            tempNode.visits += 1.0;

            if (nextPlayer(tempNode.getState()) == value) {
                tempNode.wins += 1.0;
            } else if (value == -1) {
                tempNode.draws += 1.0;
            } else {
                tempNode.losses += 1.0;
            }

            // Update RAVE statistics
            if (tempNode.move != null) {
                if (nextPlayer(tempNode.getState()) == value) {
                    tempNode.raveWins += 1.0;
                } else if (value == -1) {
                    tempNode.raveDraws += 1.0;
                } else {
                    tempNode.raveLosses += 1.0;
                }
                tempNode.raveVisits += 1.0;
            }

            tempNode = tempNode.getParentNode();
        }
    }

    private boolean isValid(IGameState state, IMove tryMove) {
        List<IMove> moves = state.getField().getAvailableMoves();

        for (IMove move : moves) {
            if (move.getY() == tryMove.getY() && move.getX() == tryMove.getX())
                return true;
        }

        return false;
    }


    public String toSymbol(int player) {
        return player + "";
    }

    private void performMove(IGameState state, IMove move) {
        String[][] board = state.getField().getBoard();
        board[move.getX()][move.getY()] = toSymbol(currentPlayer(state));
        state.getField().setBoard(board);
        updateMacroBoard(state, move);
        state.setMoveNumber(state.getMoveNumber() + 1);
    }

    private void updateMacroBoard(IGameState state, IMove move) {
        updateMicroBoardState(state, move);
        updateMicroBoardsAvailability(state, move);
    }

    private void updateMicroBoardState(IGameState state, IMove move) {
        String[][] macroBoard = state.getField().getMacroboard();
        int startingXPosition = (move.getX() / 3) * 3;
        int startingYPosition = (move.getY() / 3) * 3;
        if (isWinOnMicroBoard(state, startingXPosition, startingYPosition)) {
            macroBoard[move.getX() / 3][move.getY() / 3] = toSymbol(currentPlayer(state));
        } else if(isDrawOnMicroBoard(state, startingXPosition, startingYPosition)) {
            macroBoard[move.getX() / 3][move.getY() / 3] = PLAYER_TIE;
        }
        state.getField().setMacroboard(macroBoard);
    }

    private void updateMicroBoardsAvailability(IGameState state, IMove move) {
        int activeMicroBoardX = move.getX() % 3;
        int activeMicroBoardY = move.getY() % 3;
        String[][] macroBoard = state.getField().getMacroboard();
        if (macroBoard[activeMicroBoardX][activeMicroBoardY].equals(IField.AVAILABLE_FIELD) || macroBoard[activeMicroBoardX][activeMicroBoardY].equals(IField.EMPTY_FIELD)) {
            setAvailableMicroBoard(state, activeMicroBoardX, activeMicroBoardY);
        } else {
            setAllMicroBoardsAvailable(state);
        }
    }

    private void setAvailableMicroBoard(IGameState state, int activeMicroboardX, int activeMicroboardY) {
        String[][] macroBoard = state.getField().getMacroboard();
        for (int x = 0; x < macroBoard.length; x++) {
            for (int y = 0; y < macroBoard[x].length; y++) {
                if (x == activeMicroboardX && y == activeMicroboardY) {
                    macroBoard[x][y] = IField.AVAILABLE_FIELD;
                } else if (macroBoard[x][y].equals(IField.AVAILABLE_FIELD)) {
                    macroBoard[x][y] = IField.EMPTY_FIELD;
                }
            }
        }

        state.getField().setMacroboard(macroBoard);
    }

    private void setAllMicroBoardsAvailable(IGameState state) {
        String[][] macroBoard = state.getField().getMacroboard();
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (macroBoard[x][y].equals(IField.EMPTY_FIELD)) {
                    macroBoard[x][y] = IField.AVAILABLE_FIELD;
                }
            }
        }
    }

    private boolean isWinOnMicroBoard(IGameState state, int startingX, int startingY) {
        String[][] board = state.getField().getBoard();
        return isWinOnBoard(board, startingX, startingY);
    }

    private boolean isDrawOnMicroBoard(IGameState state, int startingX, int startingY) {
        boolean isDraw = true;
        String[][] board = state.getField().getBoard();
        for (int x = startingX; x < startingX+3; x++) {
            for (int y = startingY; y < startingY+3; y++) {
                if (board[x][y].equals(IField.EMPTY_FIELD)) {
                    isDraw = false;
                }
            }
        }

        return isDraw;
    }

    private boolean isGameOver(IGameState state) {
        String[][] macroBoard = state.getField().getMacroboard();
        return isWinOnBoard(macroBoard, 0, 0) || isDraw(state);
    }

    private boolean isDraw(IGameState state) {
        String[][] macroboard = state.getField().getMacroboard();
        for (int x = 0; x < macroboard.length; x++) {
            for (int y = 0; y < macroboard[x].length; y++) {
                if (macroboard[x][y].equals(IField.EMPTY_FIELD) || macroboard[x][y].equals(IField.AVAILABLE_FIELD)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean isWinOnBoard(String[][] board, int startingX, int startingY) {
        for (int x = startingX; x < startingX + 3; x++) {
            if (isHorizontalWin(board, x, startingY)) {
                return true;
            }

            for (int y = startingY; y < startingY + 3; y++) {
                if (isVerticalWin(board, startingX, y)) {
                    return true;
                }
            }
        }

        return isDiagonalWin(board, startingX, startingY);
    }

    private boolean isHorizontalWin(String[][] board, int startingX, int startingY) {
        return ((board[startingX][startingY].equals(PLAYER_X) || board[startingX][startingY].equals(PLAYER_O)) &&
                board[startingX][startingY].equals(board[startingX][startingY+1]) &&
                board[startingX][startingY+1].equals(board[startingX][startingY+2]));
    }

    private boolean isVerticalWin(String[][] board, int startingX, int startingY) {
        return ((board[startingX][startingY].equals(PLAYER_X) || board[startingX][startingY].equals(PLAYER_O)) &&
                board[startingX][startingY].equals(board[startingX+1][startingY]) &&
                board[startingX+1][startingY].equals(board[startingX+2][startingY]));
    }

    private boolean isDiagonalWin(String[][] board, int startingX, int startingY) {
        if ((board[startingX][startingY].equals(PLAYER_X) || board[startingX][startingY].equals(PLAYER_O)) &&
                board[startingX][startingY].equals(board[startingX+1][startingY+1]) &&
                board[startingX+1][startingY+1].equals(board[startingX+2][startingY+2])) {
            return true;
        } else if ((board[startingX][startingY+2].equals(PLAYER_X) || board[startingX][startingY+2].equals(PLAYER_O)) &&
                board[startingX][startingY+2].equals(board[startingX+1][startingY+1]) &&
                board[startingX+1][startingY+1].equals(board[startingX+2][startingY])) {
            return true;
        }
        return false;
    }

    @Override
    public String getBotName() {
        return BOT_NAME;
    }

}