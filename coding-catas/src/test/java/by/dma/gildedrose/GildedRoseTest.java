package by.dma.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Initial test for legacy {@link GildedRose}.
 *
 * @author dzmitry.marudau
 * @since 2020.4
 */
class GildedRoseTest {

    @Test
    void initialTest() {
        Item dummyItem = new Item("dummy", 0, 0);
        var app = new GildedRose(new Item[] {dummyItem});
        app.updateQuality();
        assertEquals("dummy", app.items[0].name);
    }

}
