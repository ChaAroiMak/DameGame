package dame;

public class Queen implements Dame{
    @Override
    public DamePiece pick(String userName, DamePiece wantedColor) throws GameException, StatusException {
        return null;
    }

    @Override
    public boolean set(DamePiece color, DameBoardPosition position) throws GameException, StatusException {
        return false;
    }
}
