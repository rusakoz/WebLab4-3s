package org.lab4.wed.weblab4;



import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.lab4.wed.weblab4.model.CheckHit;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = CheckHitTest.class)
class CheckHitTest {

	@Test
    public void hitCircle() {
        assertTrue(CheckHit.checkHit(-1, -1, 2));
    }

    // @Test
    // public void hitCircleLowBorder() {
    //     assertTrue(CheckHit.checkHit(-1, -1, 2));
    // }

    // @Test
    // public void hitCircleArchBorder() {
    //     assertTrue(CheckHit.checkHit(-1, -1, 2));
    // }

    @Test
    public void missCircle() {
        assertFalse(CheckHit.checkHit(-2, -2, 2));
    }

    // @Test
    // public void missCircleLowBorder() {
    //     assertFalse(CheckHit.checkHit(-2, -2, 2));
    // }

    // @Test
    // public void missCircleArchBorder() {
    //     assertFalse(CheckHit.checkHit(-2, -2, 2));
    // }

    // @Test
    // public void hitTriangle() {
    //     assertTrue(CheckHit.checkHit(-1, -1, 2));
    // }

    // @Test
    // public void hitTriangleLowBorder() {
    //     assertTrue(CheckHit.checkHit(-1, -1, 2));
    // }

    // @Test
    // public void hitTriangleTopBorder() {
    //     assertTrue(CheckHit.checkHit(-1, -1, 2));
    // }

    // @Test
    // public void hitTriangleHBorder() {
    //     assertTrue(CheckHit.checkHit(-1, -1, 2));
    // }

    // @Test
    // public void missTriangle() {
    //     assertFalse(CheckHit.checkHit(-2, -2, 2));
    // }

    // @Test
    // public void missTriangleHBorder() {
    //     assertFalse(CheckHit.checkHit(-2, -2, 2));
    // }

    // @Test
    // public void missTriangleTopBorder() {
    //     assertFalse(CheckHit.checkHit(-2, -2, 2));
    // }

    // @Test
    // public void hitRectangle() {
    //     assertTrue(CheckHit.checkHit(-1, -1, 2));
    // }

    // @Test
    // public void hitRectangleLowBorder() {
    //     assertTrue(CheckHit.checkHit(-1, -1, 2));
    // }

    // @Test
    // public void hitRectangleLeftBorder() {
    //     assertTrue(CheckHit.checkHit(-1, -1, 2));
    // }

    // @Test
    // public void hitRectangleRightBorder() {
    //     assertTrue(CheckHit.checkHit(-1, -1, 2));
    // }

    // @Test
    // public void missRectangle() {
    //     assertFalse(CheckHit.checkHit(-2, -2, 2));
    // }

    // @Test
    // public void missRectangleLowBorder() {
    //     assertFalse(CheckHit.checkHit(-2, -2, 2));
    // }

    // @Test
    // public void missRectangleLeftBorder() {
    //     assertFalse(CheckHit.checkHit(-2, -2, 2));
    // }

    // @Test
    // public void missRectangleRightBorder() {
    //     assertFalse(CheckHit.checkHit(-2, -2, 2));
    // }

    // @Test
    // public void hitEmptySector() {
    //     assertFalse(CheckHit.checkHit(-2, -2, 2));
    // }

    // @Test
    // public void hitZero() {
    //     assertTrue(CheckHit.checkHit(-1, -1, 2));
    // }

}
