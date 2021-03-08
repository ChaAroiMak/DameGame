package dame;

public class DameBoardPosition {
    private final String sCoordinate;
    private final int iCoordinate;

    DameBoardPosition(String sCoordinate, int iCoordinate) {
        this.sCoordinate = sCoordinate;
        this.iCoordinate = iCoordinate;
    }

    String getSCoordinate(){
        return this.sCoordinate;
    }

    int getICoordinate() {
        return this.iCoordinate;
    }
}
