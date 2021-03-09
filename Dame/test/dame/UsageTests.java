package dame;

import org.junit.Assert;
import org.junit.Test;

public class UsageTests {
    public static final String ALICE = "Alice";
    public static final String BOB = "Bob";
    private static final String CLARA = "Clara";

    private Dame getDame() {
        return new DameImpl();
    }


    @Test
    public void goodPickColor1() throws GameException, StatusException {
        Dame ddd = this.getDame();

        DamePiece aliceColor = ddd.pick(ALICE, DamePiece.WHITE);

        Assert.assertEquals(DamePiece.WHITE, aliceColor);
    }

    @Test
    public void goodPickColor2() throws GameException, StatusException {
        Dame ddd = this.getDame();
        DamePiece aliceColor = ddd.pick(ALICE, DamePiece.WHITE);
        DamePiece bobColor = ddd.pick(BOB, DamePiece.BLACK);
        Assert.assertEquals(DamePiece.WHITE, aliceColor);
        Assert.assertEquals(DamePiece.BLACK, bobColor);
    }

    @Test
    public void goodPickColor3() throws GameException, StatusException {
        Dame ddd = this.getDame();
        DamePiece aliceColor = ddd.pick(ALICE, DamePiece.WHITE);
        DamePiece bobColor = ddd.pick(BOB, DamePiece.WHITE);
        Assert.assertEquals(DamePiece.WHITE, aliceColor);
        Assert.assertEquals(DamePiece.BLACK, bobColor);
    }

    @Test
    public void goodPickColor4() throws GameException, StatusException {
        Dame ddd = this.getDame();
        DamePiece aliceColor = ddd.pick(BOB, DamePiece.WHITE);
        DamePiece bobColor = ddd.pick(ALICE, DamePiece.WHITE);
        Assert.assertEquals(DamePiece.WHITE, aliceColor);
        Assert.assertEquals(DamePiece.BLACK, bobColor);
    }

    @Test
    public void goodPickColor5() throws GameException, StatusException {
        Dame ddd = this.getDame();
        DamePiece aliceColor = ddd.pick(ALICE, DamePiece.WHITE);
        //reconsidered
        aliceColor = ddd.pick(ALICE, DamePiece.BLACK);
        DamePiece bobColor = ddd.pick(BOB, DamePiece.WHITE);
        Assert.assertEquals(DamePiece.BLACK, aliceColor);
        Assert.assertEquals(DamePiece.WHITE, bobColor);
    }

    @Test(expected = GameException.class)
    public void failurePickColor3time() throws GameException, StatusException{
        Dame ddd = this.getDame();
        ddd.pick(ALICE, DamePiece.WHITE);
        ddd.pick(BOB, DamePiece.WHITE);
        ddd.pick(CLARA, DamePiece.WHITE);
    }

    @Test
    public void goodSet1() throws GameException, StatusException {
        Dame ddd = this.getDame();
        DamePiece aliceSymbol = ddd.pick(ALICE, DamePiece.WHITE);
        DamePiece bobSymbol = ddd.pick(BOB, DamePiece.WHITE);

        DameBoardPosition position = new DameBoardPosition("A", 2);
        ddd.set(DamePiece.WHITE, position);

        Assert.assertFalse(ddd.set(DamePiece.WHITE, position));
    }

    @Test
    public void goodSet2() throws GameException, StatusException {
        Dame ddd = this.getDame();
        DamePiece aliceSymbol = ddd.pick(ALICE, DamePiece.WHITE);
        DamePiece bobSymbol = ddd.pick(BOB, DamePiece.WHITE);

        DameBoardPosition position = new DameBoardPosition("B", 4);
        ddd.set(DamePiece.WHITE, position);
        position = new DameBoardPosition("A", 5);
        ddd.set(DamePiece.BLACK, position);
        position = new DameBoardPosition("H", 4);
        ddd.set(DamePiece.WHITE, position);
        position = new DameBoardPosition("C", 3);
        ddd.set(DamePiece.BLACK, position);

        Assert.assertFalse(ddd.set(DamePiece.WHITE, position));
        Assert.assertFalse(ddd.set(DamePiece.BLACK, position));

    }

    @Test(expected = GameException.class)
    public void failureSetOutside1() throws GameException, StatusException {
        Dame ddd = this.getDame();
        DamePiece aliceColor = ddd.pick(ALICE, DamePiece.WHITE);
        DamePiece bobColor = ddd.pick(BOB, DamePiece.WHITE);

        DameBoardPosition position = new DameBoardPosition("X", 2);
        ddd.set(DamePiece.WHITE, position);
    }

    @Test(expected = GameException.class)
    public void failureSetOutside2() throws GameException, StatusException {
        Dame ddd = this.getDame();
        DamePiece aliceColor = ddd.pick(ALICE, DamePiece.WHITE);
        DamePiece bobColor = ddd.pick(BOB, DamePiece.WHITE);

        DameBoardPosition position = new DameBoardPosition("B", 12);
        ddd.set(DamePiece.WHITE, position);
    }

    @Test
    public void marginSet1() throws GameException, StatusException {
        Dame ddd = this.getDame();
        DamePiece aliceColor = ddd.pick(ALICE, DamePiece.WHITE);
        DamePiece bobColor = ddd.pick(BOB, DamePiece.WHITE);

        DameBoardPosition position = new DameBoardPosition("A", 1);
        ddd.set(DamePiece.WHITE, position);
        Assert.assertFalse(ddd.set(DamePiece.WHITE, position));
    }

    @Test
    public void marginSet2() throws GameException, StatusException {
        Dame ddd = this.getDame();
        DamePiece aliceColor = ddd.pick(ALICE, DamePiece.WHITE);
        DamePiece bobColor = ddd.pick(BOB, DamePiece.WHITE);

        DameBoardPosition position = new DameBoardPosition("H", 8);
        ddd.set(DamePiece.WHITE, position);
        Assert.assertFalse(ddd.set(DamePiece.WHITE, position));

        position = new DameBoardPosition("H", 1);
        ddd.set(DamePiece.WHITE, position);
        Assert.assertFalse(ddd.set(DamePiece.WHITE, position));

        position = new DameBoardPosition("A", 8);
        ddd.set(DamePiece.WHITE, position);
        Assert.assertFalse(ddd.set(DamePiece.WHITE, position));

        position = new DameBoardPosition("A", 1);
        ddd.set(DamePiece.WHITE, position);
        Assert.assertFalse(ddd.set(DamePiece.WHITE, position));
    }

    @Test(expected = StatusException.class)
    public void failureStatus1() throws GameException, StatusException {
        Dame ddd = this.getDame();
        DameBoardPosition position = new DameBoardPosition("B", 2);
        ddd.set(DamePiece.WHITE, position);
    }

    @Test(expected = StatusException.class)
    public void failureStatus2() throws GameException, StatusException {
        Dame ddd = this.getDame();

        DamePiece aliceColor = ddd.pick(ALICE, DamePiece.WHITE);
        DamePiece bobColor = ddd.pick(BOB, DamePiece.WHITE);

        DameBoardPosition position = new DameBoardPosition("B", 2);
        ddd.set(DamePiece.WHITE, position);

        ddd.pick(BOB, DamePiece.WHITE);
    }

    @Test(expected = GameException.class)
    public void failureSetSamePosition1() throws GameException, StatusException {
        Dame ddd = this.getDame();

        DamePiece aliceColor = ddd.pick(ALICE, DamePiece.WHITE);
        DamePiece bobColor = ddd.pick(BOB, DamePiece.BLACK);

        DameBoardPosition position = new DameBoardPosition("B", 4);
        ddd.set(DamePiece.WHITE, position);
        ddd.set(DamePiece.BLACK, (position = new DameBoardPosition("A", 5)));
        ddd.set(DamePiece.WHITE, (position = new DameBoardPosition("B", 4)));
    }

    @Test(expected = GameException.class)
    public void failureSetSamePosition2() throws GameException, StatusException {
        Dame ddd = this.getDame();

        DamePiece aliceColor = ddd.pick(ALICE, DamePiece.WHITE);
        DamePiece bobColor = ddd.pick(BOB, DamePiece.BLACK);

        DameBoardPosition position = new DameBoardPosition("B", 4);
        ddd.set(DamePiece.WHITE, position);
        ddd.set(DamePiece.BLACK, position);
    }

    @Test(expected = GameException.class)
    public void failurePlace2() throws GameException, StatusException {
        Dame ddd = this.getDame();

        DamePiece aliceColor = ddd.pick(ALICE, DamePiece.WHITE);
        DamePiece bobColor = ddd.pick(BOB, DamePiece.BLACK);

        DameBoardPosition position = new DameBoardPosition("B", 4);
        ddd.set(DamePiece.WHITE, position);
        ddd.set(DamePiece.BLACK, (position = new DameBoardPosition("A", 5)));
        ddd.set(DamePiece.WHITE, (position = new DameBoardPosition("F", 4)));
        ddd.set(DamePiece.BLACK, (position = new DameBoardPosition("B", 6)));

    }

}
