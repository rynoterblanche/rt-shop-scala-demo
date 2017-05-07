package com.rt.shop

case class Shop(prices: Map[String, BigDecimal]) {

  def sell(items: Array[String]) = items.map(item => prices.getOrElse(item, BigDecimal(0))).sum

}
