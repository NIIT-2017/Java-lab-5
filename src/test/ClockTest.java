package test;

import sample.Clock;

import static org.junit.Assert.*;

public class ClockTest
{
    final String dirImages = "CImages/";
    final String path = "src/" + "sample/" + dirImages;
    final String shHour = "HHour.jpg";
    final String shMin = "HMin.jpg";
    final String shSec = "HSec.jpg";
    final String sdHour = "DHour.jpg";
    final String sdMin = "DMin.jpg";
    final String sdSec = "DSec.jpg";

    @org.junit.Test
    public void getTransparentImage1() {
        assertEquals(null, Clock.Test.getTransparentImage(null));
    }

    @org.junit.Test
    public void getTransparentImage2() {
        assertEquals(true, Clock.Test.getTransparentImage(null) == null);
    }

    @org.junit.Test
    public void loadImage1() {
        assertEquals(null, Clock.Test.loadImage(null));
    }

    @org.junit.Test
    public void loadImage2() {
        assertEquals(null, Clock.Test.loadImage(""));
    }

    @org.junit.Test
    public void loadImage3() {
        assertEquals(true, Clock.Test.loadImage(path + shHour) != null);
    }

    @org.junit.Test
    public void getTransparentImageLoadImage1() {
        assertEquals(null, Clock.Test.getTransparentImage(Clock.Test.loadImage(null)));
    }

    @org.junit.Test
    public void getTransparentImageLoadImage2() {
        assertEquals(null, Clock.Test.getTransparentImage(Clock.Test.loadImage("")));
    }

    @org.junit.Test
    public void getTransparentImageLoadImage3() {
        assertEquals(true, Clock.Test.getTransparentImage(Clock.Test.loadImage(path + sdSec)) != null);
    }

    @org.junit.Test
    public void getDivSec() {
        assertEquals(true, Clock.Test.getDivSec() != null);
    }

    @org.junit.Test
    public void getImage() {
        assertEquals(true, Clock.Test.getImage(shSec) != null);
    }
}
