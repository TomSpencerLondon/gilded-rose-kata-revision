package com.gildedrose;

class GildedRose {

  public static final int MAX_QUALITY = 50;
  Item[] items;

  public GildedRose(Item[] items) {
    this.items = items;
  }

  public void updateQuality() {

    for (int i = 0; i < items.length; i++) {
      if (items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
        break;
      }

      if (items[i].quality < 50) {
        if (items[i].name.contains("Conjured")) {
          addConjured(i);
          break;
        } else if (items[i].name.equals("Aged Brie")) {
          addBrie(i);
          break;
        } else if (items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
          addBackstageConcertPasses(i);
          break;
        }else {
          addNormalItem(i);
        }
      }
    }
  }

  private void addNormalItem(int i) {
    items[i].sellIn = items[i].sellIn - 1;

    if (items[i].quality > 0) {
      items[i].quality -= 1;

      if(items[i].sellIn < 0){
        items[i].quality -= 1;
      }
    }
  }

  private void addBackstageConcertPasses(int i) {
    items[i].sellIn--;
    if (items[i].sellIn < 11) {
      if (items[i].quality < 50) {
        items[i].quality = items[i].quality + 1;
      }
    }

    if (items[i].sellIn < 6) {
      if (items[i].quality < 50) {
        items[i].quality = items[i].quality + 1;
      }
    }

    if (items[i].sellIn < 0) {
      items[i].quality = items[i].quality - items[i].quality;
    }
  }

  private void addBrie(int i) {
    items[i].sellIn--;
    items[i].quality = items[i].quality + 1;

    if (items[i].sellIn < 0 && items[i].quality < 50) {
      items[i].quality = items[i].quality + 1;
    }
  }

  private void addConjured(int i) {
    items[i].sellIn -= 1;
    items[i].quality -= 2;
  }
}