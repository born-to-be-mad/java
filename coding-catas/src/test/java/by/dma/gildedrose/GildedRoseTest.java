package by.dma.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Initial test for legacy {@link GildedRose}.
 *
 * @author dzmitry.marudau
 * @since 2020.4
 */
class GildedRoseTest {

    private static final String DUMMY_NAME = "foo";

    @Test
    void systemLowersValues() {
        Item item = createAndUpdateItem(DUMMY_NAME, 15, 25);
        assertEquals(14, item.sellIn);
        assertEquals(24, item.quality);
    }

    @Test
    void qualityDegradesTwiceAsFast() {
        Item item = createAndUpdateItem(DUMMY_NAME, 0, 17);
        assertEquals(15, item.quality);
    }

    @Test
    void qualityIsNeverNegative() {
        Item item = createAndUpdateItem(DUMMY_NAME, 0, 0);
        assertEquals(0, item.quality);
    }

    @Test
    void agedBrieIncreasesInQuality() {
        Item item = createAndUpdateItem(GildedRose.AGED_BRIE, 15, 25);
        assertEquals(26, item.quality);
    }

    @Test
    void qualityNeverMoreThan50() {
        Item item = createAndUpdateItem(DUMMY_NAME, 15, 52);
        assertEquals(51, item.quality); // can exceed GildedRose.MAXIMUM_QUALITY if input exceeds GildedRose.MAXIMUM_QUALITY
        item = createAndUpdateItem(GildedRose.AGED_BRIE, 15, GildedRose.MAXIMUM_QUALITY);
        assertEquals(GildedRose.MAXIMUM_QUALITY, item.quality);
    }

    @Test
    void sulfurasNeverHasToBeSoldOrDecreasesInQuality() {
        Item item = createAndUpdateItem(GildedRose.SULFURAS, 1, 42);
        assertEquals(1, item.sellIn); // sellIn doesn't change
        assertEquals(42, item.quality); // quality doesn't change
    }

    @Test
    void backstagePassesIncreaseInQuality() {
        Item item = createAndUpdateItem(GildedRose.BACKSTAGE_PASSES, 15, 25);
        assertEquals(26, item.quality);
    }

    @Test
    void backstagePassesIncreaseBy2() {
        Item item = createAndUpdateItem(GildedRose.BACKSTAGE_PASSES, 10, 25);
        assertEquals(27, item.quality);
    }

    @Test
    void backstagePassesIncreaseBy3() {
        Item item = createAndUpdateItem(GildedRose.BACKSTAGE_PASSES, 5, 25);
        assertEquals(28, item.quality);
    }

    @Test
    void backstagePassesQualityDropTo0() {
        Item item = createAndUpdateItem(GildedRose.BACKSTAGE_PASSES, 0, 25);
        assertEquals(0, item.quality);
    }

    @Test
    void agedBrieNeverExpires() {
        Item item = createAndUpdateItem(GildedRose.AGED_BRIE, 0, 42);
        assertEquals(-1, item.sellIn);
        assertEquals(44, item.quality);
    }

    @Test
    void backstagePassMaximumQuality() {
        Item item = createAndUpdateItem(GildedRose.BACKSTAGE_PASSES, 10, 48);
        assertEquals(GildedRose.MAXIMUM_QUALITY, item.quality);

        item = createAndUpdateItem(GildedRose.BACKSTAGE_PASSES, 10, 49);
        assertEquals(GildedRose.MAXIMUM_QUALITY, item.quality);
    }

    @Test
    void degradeInQualityUnlessSulfuras() {
        Item item = createAndUpdateItem(DUMMY_NAME, -1, 1);
        assertEquals(0, item.quality);

        item = createAndUpdateItem(GildedRose.SULFURAS, -1, 1);
        assertEquals(1, item.quality);
    }

    @Test
    void agedBrieMaximumQuality() {
        Item item = createAndUpdateItem(GildedRose.AGED_BRIE, -1, 49);
        assertEquals(GildedRose.MAXIMUM_QUALITY, item.quality);
    }

    @Test
    void conjuredDegradeTwiceAsFast() {
        Item item = createAndUpdateItem(GildedRose.CONJURED, 15, 25);
        assertEquals(23, item.quality);
    }

    private Item createAndUpdateItem(String name, int sellIn, int quality) {
        Item[] items = new Item[] {new Item(name, sellIn, quality)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        return app.items[0];
    }
}
