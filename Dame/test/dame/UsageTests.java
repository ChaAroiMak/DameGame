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

    /**
     * given: describes the state of the world before you begin the behavior
     * you're specifying in this scenario.You can think of it as the pre-conditions to the test.
     * when: behavior that you're specifying.
     * then: describes the changes you expect due to the specified behavior.
     * @throws GameException
     * @throws StatusException
     */

    @Test
    public void goodPickColor1() throws GameException, StatusException {
        //given a new game gets started
        Dame ddd = this.getDame();
        //when one player chooses a color
        DamePiece aliceColor = ddd.pick(ALICE, DamePiece.WHITE);
        //then the person should get the chosen color
        Assert.assertEquals(DamePiece.WHITE, aliceColor);
    }

    @Test
    public void goodPickColor2() throws GameException, StatusException {
        //given a new game gets started
        Dame ddd = this.getDame();
        //and one player picks a color
        DamePiece aliceColor = ddd.pick(ALICE, DamePiece.WHITE);
        //and another player picks a different color
        DamePiece bobColor = ddd.pick(BOB, DamePiece.BLACK);
        //then both players should get the wanted color
        Assert.assertEquals(DamePiece.WHITE, aliceColor);
        Assert.assertEquals(DamePiece.BLACK, bobColor);
    }

    @Test
    public void goodPickColor3() throws GameException, StatusException {
        //given a new game gets started
        Dame ddd = this.getDame();
        //and one player chooses a color
        DamePiece aliceColor = ddd.pick(ALICE, DamePiece.WHITE);
        //and another player chooses the same color
        DamePiece bobColor = ddd.pick(BOB, DamePiece.WHITE);
        //then the first player will get the chosen color and the second player will get
        //a different one
        Assert.assertEquals(DamePiece.WHITE, aliceColor);
        Assert.assertEquals(DamePiece.BLACK, bobColor);
    }

    @Test
    public void goodPickColor4() throws GameException, StatusException {
        //given a new game gets started
        Dame ddd = this.getDame();
        //and one player chooses a color
        DamePiece aliceColor = ddd.pick(BOB, DamePiece.WHITE);
        //and another player chooses the same color
        DamePiece bobColor = ddd.pick(ALICE, DamePiece.WHITE);
        //then the first player will get the chosen color and the second player will get
        //a different one
        Assert.assertEquals(DamePiece.WHITE, aliceColor);
        Assert.assertEquals(DamePiece.BLACK, bobColor);
    }

    @Test
    public void goodPickColor5() throws GameException, StatusException {
        //given a new game gets started
        Dame ddd = this.getDame();
        //and one player chooses a color
        DamePiece aliceColor = ddd.pick(ALICE, DamePiece.WHITE);
        //reconsidered
        //and the first player wants another color
        aliceColor = ddd.pick(ALICE, DamePiece.BLACK);
        //then the first player will get the wanted color as long as nobody else already picked it
        DamePiece bobColor = ddd.pick(BOB, DamePiece.WHITE);
        Assert.assertEquals(DamePiece.BLACK, aliceColor);
        Assert.assertEquals(DamePiece.WHITE, bobColor);
    }

    @Test(expected = GameException.class)
    public void failurePickColor3time() throws GameException, StatusException{
        //given a new game gets started
        Dame ddd = this.getDame();
        //and three different players try to log in and play
        ddd.pick(ALICE, DamePiece.WHITE);
        ddd.pick(BOB, DamePiece.WHITE);
        ddd.pick(CLARA, DamePiece.WHITE);
        //then the game will throw an exception
    }

    @Test
    public void goodSet1() throws GameException, StatusException {
        //given a new game gets started
        Dame ddd = this.getDame();
        //and 2 players choose the colors
        DamePiece aliceSymbol = ddd.pick(ALICE, DamePiece.WHITE);
        DamePiece bobSymbol = ddd.pick(BOB, DamePiece.WHITE);

        //and player one will make a valid move
        DameBoardPosition position = new DameBoardPosition("A", 2);
        ddd.set(DamePiece.WHITE, position);

        //then the stone will be set to the chosen position
        Assert.assertFalse(ddd.set(DamePiece.WHITE, position));
    }

    @Test
    public void goodSet2() throws GameException, StatusException {
        //given a new game gets started
        Dame ddd = this.getDame();
        //and two players select their colors
        DamePiece aliceSymbol = ddd.pick(ALICE, DamePiece.WHITE);
        DamePiece bobSymbol = ddd.pick(BOB, DamePiece.WHITE);

        //and both players will make valid moves
        DameBoardPosition position = new DameBoardPosition("B", 4);
        ddd.set(DamePiece.WHITE, position);
        position = new DameBoardPosition("A", 5);
        ddd.set(DamePiece.BLACK, position);
        position = new DameBoardPosition("H", 4);
        ddd.set(DamePiece.WHITE, position);
        position = new DameBoardPosition("C", 3);
        ddd.set(DamePiece.BLACK, position);

        //then the stones will be set to the accortding positions
        Assert.assertFalse(ddd.set(DamePiece.WHITE, position));
        Assert.assertFalse(ddd.set(DamePiece.BLACK, position));

    }

    @Test(expected = GameException.class)
    public void failureSetOutside1() throws GameException, StatusException {
        //given a new game gets started
        Dame ddd = this.getDame();
        //and two players select their colors
        DamePiece aliceColor = ddd.pick(ALICE, DamePiece.WHITE);
        DamePiece bobColor = ddd.pick(BOB, DamePiece.WHITE);

        //and the stone will be placed on a string position outside the board
        DameBoardPosition position = new DameBoardPosition("X", 2);
        ddd.set(DamePiece.WHITE, position);
        //then there will be an exception
    }

    @Test(expected = GameException.class)
    public void failureSetOutside2() throws GameException, StatusException {
        //given a new game gets started
        Dame ddd = this.getDame();
        //and two players select their colors
        DamePiece aliceColor = ddd.pick(ALICE, DamePiece.WHITE);
        DamePiece bobColor = ddd.pick(BOB, DamePiece.WHITE);
        //and the stone will be placed on a int position outside the board
        DameBoardPosition position = new DameBoardPosition("B", 12);
        ddd.set(DamePiece.WHITE, position);
        //then there will be an exception
    }

    @Test
    public void marginSet1() throws GameException, StatusException {
        //given a new game gets started
        Dame ddd = this.getDame();
        //and two players select their colors
        DamePiece aliceColor = ddd.pick(ALICE, DamePiece.WHITE);
        DamePiece bobColor = ddd.pick(BOB, DamePiece.WHITE);
        //and the player makes a valid move to the margin of the board
        DameBoardPosition position = new DameBoardPosition("A", 1);
        ddd.set(DamePiece.WHITE, position);
        //then the piece will be placed on the position
        Assert.assertFalse(ddd.set(DamePiece.WHITE, position));
    }

    @Test
    public void marginSet2() throws GameException, StatusException {
        //given a new game gets started
        Dame ddd = this.getDame();
        //and two players select their colors
        DamePiece aliceColor = ddd.pick(ALICE, DamePiece.WHITE);
        DamePiece bobColor = ddd.pick(BOB, DamePiece.WHITE);

        //and  players make valid moves to the margin of the board
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
        //then the pieces will be placed on the position
        Assert.assertFalse(ddd.set(DamePiece.WHITE, position));
    }

    @Test(expected = StatusException.class)
    public void failureStatus1() throws GameException, StatusException {
        //given a new game gets started
        Dame ddd = this.getDame();
        //and a piece gets moved to a position without players being logged in
        DameBoardPosition position = new DameBoardPosition("B", 2);
        ddd.set(DamePiece.WHITE, position);
        //then there will be and exception
    }

    @Test(expected = StatusException.class)
    public void failureStatus2() throws GameException, StatusException {
        //given a new game gets started

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

    @Test
    public void goodJump1() throws GameException, StatusException{
        Dame ddd = this.getDame();

        DamePiece aliceColor = ddd.pick(ALICE, DamePiece.WHITE);
        DamePiece bobColor = ddd.pick(BOB, DamePiece.BLACK);

        DameBoardStartPositions startPositions = new DameBoardStartPositions();
        DameBoardPosition position = new DameBoardPosition("D", 4);
        ddd.set(DamePiece.w2, position);

        position = new DameBoardPosition("E", 5);
        ddd.set(DamePiece.b2, position);

        position = new DameBoardPosition("B", 4);
        ddd.set(DamePiece.w1, position);

        position = new DameBoardPosition("C", 3);
        ddd.set(DamePiece.b2, position);

        Assert.assertFalse(ddd.set(DamePiece.w1, position));
        Assert.assertFalse(ddd.set(DamePiece.w2, position));
        Assert.assertFalse(ddd.set(DamePiece.b2, position));
    }

    @Test(expected = GameException.class)
    public void badJump1() throws GameException, StatusException {
        Dame ddd = this.getDame();

        DamePiece aliceColor = ddd.pick(ALICE, DamePiece.WHITE);
        DamePiece bobColor = ddd.pick(BOB, DamePiece.BLACK);

        DameBoardStartPositions startPositions = new DameBoardStartPositions();
        DameBoardPosition position = new DameBoardPosition("D", 4);
        ddd.set(DamePiece.w2, position);

        position = new DameBoardPosition("E", 5);
        ddd.set(DamePiece.b2, position);

        position = new DameBoardPosition("C", 3);
        ddd.set(DamePiece.w1, position);

        position = new DameBoardPosition("C", 3);
        ddd.set(DamePiece.b2, position);
    }

}
