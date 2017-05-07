package com.rt.shop

import org.scalatest.FunSuite
import org.scalatest.BeforeAndAfter

class TestShop extends FunSuite with BeforeAndAfter {

  var shop: Shop = _

  before {
    val items = Map("Apple" -> BigDecimal(.60), "Orange" -> BigDecimal(.25))
    shop = Shop(items)
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
}
