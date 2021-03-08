package dame;

public interface Dame {
    /**
     * Pick a color
     * @param userName user name
     * @param wantedColor user asks for this color. it can be a race condition
     * @return selected color
     * @throws GameException both symbols are already taken
     * @throws StatusException can only be called if game hasn't started yet
     */

    DamePiece pick(String userName, DamePiece wantedColor) throws GameException, StatusException;

    /**
     * set a piece pn the board
     * @param color to be placed on the board
     * @param position
     * @return true if won, false otherwise
     * @throws GameException position outside the board or position occupied by same color
     * @throws StatusException not in status play
     */
    boolean set(DamePiece color, DameBoardPosition position) throws GameException, StatusException;
}
