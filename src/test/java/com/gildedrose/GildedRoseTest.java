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
    void aged_brie_increases_in_quality_and_decreases_in_sellin() {
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

    @Test
    void backstage_passes_increases_by_one_if_more_than_6_days_before() {
        Item[] items = new Item[] {
            new Item("Backstage passes to a TAFKAL80ETC concert", 10, 10)
        };

        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.items[0].name);
        assertEquals(9, app.items[0].sellIn);
        assertEquals(11, app.items[0].quality);
    }

    @Test
    void backstage_passes_increases_by_two_if_less_than_6_days_before() {
        Item[] items = new Item[] {
            new Item("Backstage passes to a TAFKAL80ETC concert", 5, 10)
        };

        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.items[0].name);
        assertEquals(4, app.items[0].sellIn);
        assertEquals(12, app.items[0].quality);
    }


    @Test
    void backstage_passes_is_worth_nothing_after_concert() {
        Item[] items = new Item[] {
            new Item("Backstage passes to a TAFKAL80ETC concert", 0, 10)
        };

        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.items[0].name);
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void normal_item_reduces_quality_before_sellin() {
        Item[] items = new Item[] {
            new Item("Elixir of the Mongoose", 5, 7),
            new Item("+5 Dexterity Vest", 10, 20)
        };

        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Elixir of the Mongoose", app.items[0].name);
        assertEquals(4, app.items[0].sellIn);
        assertEquals(6, app.items[0].quality);

        assertEquals("+5 Dexterity Vest", app.items[1].name);
        assertEquals(9, app.items[1].sellIn);
        assertEquals(19, app.items[1].quality);
    }

    @Test
    void normal_item_doubles_quality_reduction_after_sellin() {
        Item[] items = new Item[] {
            new Item("Elixir of the Mongoose", -1, 7),
            new Item("+5 Dexterity Vest", -1, 20)
        };

        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Elixir of the Mongoose", app.items[0].name);
        assertEquals(-2, app.items[0].sellIn);
        assertEquals(5, app.items[0].quality);

        assertEquals("+5 Dexterity Vest", app.items[1].name);
        assertEquals(-2, app.items[1].sellIn);
        assertEquals(18, app.items[1].quality);
    }
}

