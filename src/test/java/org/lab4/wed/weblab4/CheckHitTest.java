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

    @Test
    public void hitCircleBottomBorder() {
        assertTrue(CheckHit.checkHit(0, -2, 2));
    }

    @Test
    public void hitCircleTopBorder() {
        assertTrue(CheckHit.checkHit(-2, 0, 2));
    }

    @Test
    public void hitCircleArchBorder() {
        assertTrue(CheckHit.checkHit(-1.58, -1.22, 2));
    }

    @Test
    public void missCircle() {
        assertFalse(CheckHit.checkHit(-2, -2, 2));
    }

    @Test
    public void missCircleBottomBorder() {
        assertFalse(CheckHit.checkHit(0, -2.01, 2));
    }

    @Test
    public void missCircleTopBorder() {
        assertFalse(CheckHit.checkHit(-2.01, 0, 2));
    }

    @Test
    public void missCircleArchBorder() {
        assertFalse(CheckHit.checkHit(-1.58, -1.24, 2));
    }

    @Test
    public void hitTriangle() {
        assertTrue(CheckHit.checkHit(1, -1, 3));
    }

    @Test
    public void hitTriangleLowBorder() {
        assertTrue(CheckHit.checkHit(1, -3, 3));
    }

    @Test
    public void hitTriangleTopBorder() {
        assertTrue(CheckHit.checkHit(1, 0, 3));
    }

    @Test
    public void hitTriangleRightBorder() {
        assertTrue(CheckHit.checkHit(1.5, -1.5, 3));
    }

    @Test
    public void missTriangle() {
        assertFalse(CheckHit.checkHit(2, 2, 3));
    }

    @Test
    public void missTriangleRightBorder() {
        assertFalse(CheckHit.checkHit(1.501, -1.5, 3));
    }

    @Test
    public void hitRectangle() {
        assertTrue(CheckHit.checkHit(0.9, 0.9, 3));
    }

    @Test
    public void hitRectangleLowRightBorder() {
        assertTrue(CheckHit.checkHit(3, 0, 3));
    }

    @Test
    public void hitRectangleLeftTopBorder() {
        assertTrue(CheckHit.checkHit(0, 3, 3));
    }

    @Test
    public void hitRectangleRightBorder() {
        assertTrue(CheckHit.checkHit(1.5, 1.5, 3));
    }

    @Test
    public void missRectangle() {
        assertFalse(CheckHit.checkHit(1.7, 1.7, 3));
    }

    @Test
    public void missRectangleLowTopBorder() {
        assertFalse(CheckHit.checkHit(3, -0.001, 3));
    }

    @Test
    public void missRectangleLeftTopBorder() {
        assertFalse(CheckHit.checkHit(-0.001, 3, 3));
    }

    @Test
    public void missRectangleRightBorder() {
        assertFalse(CheckHit.checkHit(1.5001, 1.5001, 3));
    }
    
}
