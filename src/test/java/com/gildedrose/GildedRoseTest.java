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

    @Test
    void aged_brie_increases_in_quality_and_decreases_sellin() {
        Item[] items = new Item[] {
            new Item("Aged Brie", 4, 1)
        };

        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(3, app.items[0].sellIn);
        assertEquals(2, app.items[0].quality);
    }

    @Test
    void aged_brie_doubles_in_quality_after_zero_sellin() {
        Item[] items = new Item[] {
            new Item("Aged Brie", -1, 1)
        };

        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-2, app.items[0].sellIn);
        assertEquals(3, app.items[0].quality);
    }

    @Test
    void aged_brie_doesnt_increase_over_50_in_quality() {
        Item[] items = new Item[] {
            new Item("Aged Brie", -1, 49)
        };

        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-2, app.items[0].sellIn);
        assertEquals(50, app.items[0].quality);
    }
}

