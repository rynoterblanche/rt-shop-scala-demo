package com.rt.shop

case class Shop(prices: Map[String, BigDecimal],
                offers: Map[String, (BigDecimal, Int) => BigDecimal] = Map.empty) {

  def sell(items: Array[String]) = items.groupBy(identity)
    .mapValues(_.length)
    .transform((item, qty) => getTotal(item, qty).-(getDiscount(item, qty)))
    .foldLeft(BigDecimal(0.0))(_+_._2)

  def getTotal(item: String, qty: Int): BigDecimal = getPrice(item).*(qty)

  def getPrice(item: String): BigDecimal = prices.getOrElse(item, BigDecimal(0.0))

  def getDiscount(item: String, qty: Int): BigDecimal = {
    if (offers.keys.exists(_ == item))
      offers(item).apply(getPrice(item), qty)
    else
      BigDecimal(0.0)
  }
}

