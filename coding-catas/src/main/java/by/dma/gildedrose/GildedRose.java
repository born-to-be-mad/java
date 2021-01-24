package by.dma.gildedrose;

/**
 * Legacy GildedRose. The idea is to start hacking this code away improving the design.
 *
 * @author dzmitry.marudau
 * @since 2020.4
 */
class GildedRose {

    public static final int MAXIMUM_QUALITY = 50;
    public static final int BACKSTAGE_PASS_THRESHOLD1 = 11;
    public static final int BACKSTAGE_PASS_THRESHOLD2 = 6;
    public static final String AGED_BRIE = "Aged Brie";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            final Item item = items[i];
            if (isNormalItem(item)) {
                handleNormalItem(item);
            } else {
                if (isAgedBrie(item) || isBackstagePasses(item)) {
                    if (item.quality < MAXIMUM_QUALITY) {
                        item.quality++;

                        if (isBackstagePasses(item)) {
                            if (item.sellIn < BACKSTAGE_PASS_THRESHOLD1) {
                                if (item.quality < MAXIMUM_QUALITY) {
                                    item.quality++;
                                }
                            }

                            if (item.sellIn < BACKSTAGE_PASS_THRESHOLD2) {
                                if (item.quality < MAXIMUM_QUALITY) {
                                    item.quality++;
                                }
                            }
                        }
                    }
                } else {
                    if (item.quality > 0) {
                        if (isSulfuras(item)) {
                            continue;
                        } else {
                            item.quality--;
                        }
                    }
                }

                if (isSulfuras(item)) {
                    continue;
                } else {
                    item.sellIn--;
                }

                if (item.sellIn < 0) {
                    if (isAgedBrie(item)) {
                        if (item.quality < MAXIMUM_QUALITY) {
                            item.quality++;
                        }
                    } else {
                        if (isBackstagePasses(item)) {
                            item.quality = 0;
                        } else {
                            if (item.quality > 0) {
                                if (isSulfuras(item)) {
                                    continue;
                                }
                                item.quality--;
                            }
                        }
                    }
                }
            }
        }
    }

    private void handleNormalItem(Item item) {
        item.sellIn--;
        if (item.sellIn <= 0) {
            item.quality -= 2;
        } else {
            item.quality--;
        }
        if (item.quality < 0) { item.quality = 0; }
    }

    private boolean isNormalItem(Item item) {
        return !(isAgedBrie(item) || isBackstagePasses(item) || isSulfuras(item));
    }

    private boolean isAgedBrie(Item item) {
        return item.name.equals(AGED_BRIE);
    }

    private boolean isSulfuras(Item item) {
        return item.name.equals(SULFURAS);
    }

    private boolean isBackstagePasses(Item item) {
        return item.name.equals(BACKSTAGE_PASSES);
    }
}
