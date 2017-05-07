package com.rt.shop

import org.scalatest.FunSuite
import org.scalatest.BeforeAndAfter

class TestShopWithOffers extends FunSuite with BeforeAndAfter {

  var shop: Shop = _
  val apple = "Apple"
  val orange = "Orange"

  def calcDiscount(price: BigDecimal, qty: Int, discountQty: Int): BigDecimal = {
    price.*(qty / discountQty)
  }
  def TWO_FOR_ONE = (price: BigDecimal, qty: Int) => calcDiscount(price, qty, 2)
  def THREE_FOR_TWO = (price: BigDecimal, qty: Int) => calcDiscount(price, qty, 3)

  before {
    val items = Map(apple -> BigDecimal(.60), orange -> BigDecimal(.25))
    val offers = Map(apple -> TWO_FOR_ONE, orange -> THREE_FOR_TWO)
    shop = Shop(items, offers)
  }

  test("sells items at correct total cost") {
    assert(shop.sell(Array("Apple", "Orange")) == .85)
  }

  test("sells empty item list for zero cost") {
    assert(shop.sell(Array.empty) == 0)
  }

  test("ignores unknown items and sells at correct cost") {
    assert(shop.sell(Array("Apple", "UnknownItem")) == .60)
  }

  test("sells two apples for price of one") {
    assert(shop.sell(Array(apple, apple)) == .60)
  }

  test("sells two apples for price of one and one full price") {
    assert(shop.sell(Array(apple, apple, apple)) == 1.20)
  }

  test("sells four apples for price of two") {
    assert(shop.sell(Array(apple, apple, apple, apple)) == 1.20)
  }

  test("sells 3 oranges for price of 2") {
    assert(shop.sell(Array(orange, orange, orange)) == .50)
  }

  test("sells 3 oranges for price of 2 and two apples for price of one") {
    assert(shop.sell(Array(orange, orange, orange, apple, apple)) == 1.10)
  }

}