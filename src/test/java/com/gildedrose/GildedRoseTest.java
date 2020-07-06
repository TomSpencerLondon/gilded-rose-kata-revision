package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void conjured_items_reduce_quality_twice_as_fast_as_normal_items() {
        Item[] items = new Item[] { new Item("Conjured Mana Cake", 3, 6) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Conjured Mana Cake", app.items[0].name);
        assertEquals(4, app.items[0].quality);
        assertEquals(2, app.items[0].sellIn);
    }

    @Test
    void sulfuras_hand_of_ragnaros_does_not_change() {
        Item[] items = new Item[] {
            new Item("Sulfuras, Hand of Ragnaros", 0, 80),
            new Item("Sulfuras, Hand of Ragnaros", -1, 80)
        };

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals("Sulfuras, Hand of Ragnaros", app.items[0].name);
        assertEquals("Sulfuras, Hand of Ragnaros", app.items[1].name);
        assertEquals(80, app.items[0].quality);
        assertEquals(80, app.items[1].quality);
        assertEquals(0, app.items[0].sellIn);
        assertEquals(-1, app.items[1].sellIn);
    }
}

