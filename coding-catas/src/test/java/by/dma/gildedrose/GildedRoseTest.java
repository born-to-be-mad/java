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
        Item item = createAndUpdateItem(15, 25);
        assertEquals(14, item.sellIn);
        assertEquals(24, item.quality);
    }

    @Test
    void qualityDegradesTwiceAsFast() {
        Item item = createAndUpdateItem(0, 17);
        assertEquals(15, item.quality);
    }

    @Test
    void qualityIsNeverNegative() {
        Item item = createAndUpdateItem(0, 0);
        assertEquals(0, item.quality);
    }

    private Item createAndUpdateItem(int i, int i2) {
        Item[] items = new Item[] {new Item("foo", i, i2)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        return app.items[0];
    }
}
