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

    @Test
    void systemLowersValues() {
        Item item = createAndUpdateItem("foo", 15, 25);
        assertEquals(14, item.sellIn);
        assertEquals(24, item.quality);
    }

    @Test
    void qualityDegradesTwiceAsFast() {
        Item item = createAndUpdateItem("foo", 0, 17);
        assertEquals(15, item.quality);
    }

    @Test
    void qualityIsNeverNegative() {
        Item item = createAndUpdateItem("foo", 0, 0);
        assertEquals(0, item.quality);
    }

    @Test
    void agedBrieIncreasesInQuality() {
        Item item = createAndUpdateItem("Aged Brie", 15, 25);
        assertEquals(26, item.quality);
    }

    @Test
    void qualityNeverMoreThan50() {
        Item item = createAndUpdateItem("foo", 15, 52);
        assertEquals(51, item.quality); // can exceed 50 if input exceeds 50

        item = createAndUpdateItem("Aged Brie", 15, 50);
        assertEquals(50, item.quality);
    }

    @Test
    void sulfurasNeverHasToBeSoldOrDecreasesInQuality() {
        Item item = createAndUpdateItem("Sulfuras, Hand of Ragnaros", 1, 42);
        assertEquals(1, item.sellIn); // sellIn doesn't change
        assertEquals(42, item.quality); // quality doesn't change
    }

    @Test
    void backstagePassesIncreaseInQuality() {
        Item item = createAndUpdateItem("Backstage passes to a TAFKAL80ETC concert", 15, 25);
        assertEquals(26, item.quality);
    }

    @Test
    void backstagePassesIncreaseBy2() {
        Item item = createAndUpdateItem("Backstage passes to a TAFKAL80ETC concert", 10, 25);
        assertEquals(27, item.quality);
    }

    @Test
    void backstagePassesIncreaseBy3() {
        Item item = createAndUpdateItem("Backstage passes to a TAFKAL80ETC concert", 5, 25);
        assertEquals(28, item.quality);
    }

    @Test
    void backstagePassesQualityDropTo0() {
        Item item = createAndUpdateItem("Backstage passes to a TAFKAL80ETC concert", 0, 25);
        assertEquals(0, item.quality);
    }

    @Test
    void agedBrieNeverExpires() {
        Item item = createAndUpdateItem("Aged Brie", 0, 42);
        assertEquals(-1, item.sellIn);
        assertEquals(44, item.quality);
    }

    @Test
    void backstagePassMaximumQuality() {
        Item item = createAndUpdateItem("Backstage passes to a TAFKAL80ETC concert", 10, 48);
        assertEquals(50, item.quality);

        item = createAndUpdateItem("Backstage passes to a TAFKAL80ETC concert", 10, 49);
        assertEquals(50, item.quality);
    }

    @Test
    void degradeInQualityUnlessSulfuras() {
        Item item = createAndUpdateItem("foo", -1, 1);
        assertEquals(0, item.quality);

        item = createAndUpdateItem("Sulfuras, Hand of Ragnaros", -1, 1);
        assertEquals(1, item.quality);
    }

    @Test
    void agedBrieMaximumQuality() {
        Item item = createAndUpdateItem("Aged Brie", -1, 49);
        assertEquals(50, item.quality);
    }

    private Item createAndUpdateItem(String name, int sellIn, int quality) {
        Item[] items = new Item[] {new Item(name, sellIn, quality)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        return app.items[0];
    }
}
