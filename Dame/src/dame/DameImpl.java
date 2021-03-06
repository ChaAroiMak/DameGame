package dame;

import java.util.HashMap;

public class DameImpl implements Dame {
    private Status status = Status.START;
    HashMap<DamePiece, String> player = new HashMap<>();
    HashMap<DamePiece, Integer> pieces = new HashMap<>();
    private boolean queenMove= false;
    private DamePiece localSymbol;

    @Override
    public DamePiece pick(String userName, DamePiece wantedColor) throws GameException, StatusException {
        if (this.status != Status.START && this.status != Status.ONE_PCIKED) {
            throw new StatusException("pick call but wrong status");
        }

        DamePiece takenColor = null;
        //already taken a color?
        takenColor = this.getTakenColor(userName, DamePiece.WHITE);
        if (takenColor == null) {
            takenColor = this.getTakenColor(userName, DamePiece.BLACK);
        }

        //is this user number 2+?
        if (takenColor == null && this.player.values().size() == 2) {
            throw new GameException("both colors taken but from " + userName);
        }

        //user already got a color?
        if (takenColor != null) { //yes, user got a color
            //wanted one?
            if (takenColor == wantedColor) {
                return wantedColor;
            }
            //can color be changed?
            if (this.player.get(wantedColor) == null) { //yes
                this.player.remove(takenColor);
                this.player.put(wantedColor, userName);
                return wantedColor;
            } else { //cannot change
                return takenColor;
            }
        } else { //no, no color taken yet
            //wanted color available
            if (this.player.get(wantedColor) == null) {// yes, color is available
                this.player.put(wantedColor, userName);
                this.changeStatusAfterPickedColor();
                return wantedColor;
            } else { //wanted color already taken
                DamePiece otherColor = wantedColor == DamePiece.WHITE ? DamePiece.BLACK : DamePiece.WHITE;
                this.player.put(otherColor, userName);
                this.changeStatusAfterPickedColor();
                return otherColor;
            }
        }
    }

    private void changeStatusAfterPickedColor() {
        this.status = this.status == Status.START ? Status.ONE_PCIKED : Status.ACTIVE_WHITE;
    }

    private DamePiece getTakenColor(String userName, DamePiece piece) {
        String name = this.player.get(piece);
        if (name != null && name.equalsIgnoreCase(userName)) {
            return piece;
        }
        return null;
    }

    private DamePiece[][] board = new DamePiece[8][8]; //horizontal / vertical

    @Override
    public boolean set(DamePiece color, DameBoardPosition position) throws GameException, StatusException {
        if (this.status != Status.ACTIVE_WHITE && this.status != Status.ACTIVE_BLACK) {
            throw new StatusException("set call but wrong status");
        }

        int horizontal = this.sCoordinate2Int(position.getSCoordinate());
        int vertical = this.checkIntCoordinate(position.getICoordinate());

        if (this.board[horizontal][vertical] != null) {
            throw new GameException("position already occupied");
        }
        this.board[horizontal][vertical] = color;
        return this.hasWon(color);
    }

    private boolean hasWon(DamePiece color) {

        return false;
    }

    public boolean set(DameBoardPosition position) throws GameException, StatusException {
        return this.set(this.localSymbol, position);
    }

    boolean hasWon(DamePiece color, DameBoardPosition position) throws GameException, StatusException {//testen ob wenigstens ein stein sich noch bewegen kann
        if(canMove(color) == true) { //falls ja --> keiner hat gewonnen
            System.out.println("Can make move: " + position);
            set(color, position);
        } else { //falls nein --> gegner hat gewonnen
            System.out.println(color + " has lost the game");
            return false;
        }
        return false;
    }

    private boolean canMove(DamePiece piece) {
        if(canMove(piece) == true){
            if(piece != piece.Queen) {

                return true;
            }
            else {

                return true;
            }
        }
        else{
            hasLost(piece);
            return false;
        }
        //alle steine ausser dame k??nnen einen schritt vorw??rts gehen diagonal
        //steine k??nnen stein des gegners ??berspringen
        //steine k??nnen nicht die eigene farbe ??berspringen
        //steine k??nnen nur auf freies feld
        //dame kann vor und zur??ck
    }

    private boolean hasLost(DamePiece piece) {
        if(pieces.size() > 0) {
            if(canMove(piece) == true) {
                return false;
            }
            else {
                System.out.println(piece + " has lost - no more possible moves");
                System.exit(0);
                return true;
            }
        }
        else {
            System.out.println(piece + " has lost - no more pieces on the board");
            System.exit(0);
            return true;
        }
    }

    private DamePiece becomeQueen(DamePiece color, DameBoardPosition position) throws GameException, StatusException {
        if(set(color,position))
        if(DameBoardPosition.color != DameBoardStartPositions.color) {
            if (currentPosition("A", 8) || currentPosition("B", 8) || currentPosition("C", 8) || currentPosition("D", 8) || currentPosition("E", 8) || currentPosition("F", 8) || currentPosition("G", 8) || currentPosition("H", 8) ||
                    currentPosition("A", 1) || currentPosition("B", 1) || currentPosition("C", 1) || currentPosition("D", 1) || currentPosition("E", 1) || currentPosition("F", 1) || currentPosition("G", 1) || currentPosition("H", 1)) {
                    color = color.Queen;
            }
        }

        return color;
    }

    private DamePiece queen(DamePiece piece) {

        return piece;
    }

    private boolean currentPosition(String a, int i) {
        return false;
    }


    private int sCoordinate2Int(String cCoordinate) throws GameException {
        switch (cCoordinate) {
            case "A":
                return 0;
            case "B":
                return 1;
            case "C":
                return 2;
            case "D":
                return 3;
            case "E":
                return 4;
            case "F":
                return 5;
            case "G":
                return 6;
            case "H":
                return 7;
        }
        throw new GameException("position outside of board");
    }

    private int checkIntCoordinate(int iCoordinate) throws GameException {
        if (iCoordinate < 0 || iCoordinate > 7) {
            throw new GameException("coordinate outside of board");
        }
        return iCoordinate;
    }
}

