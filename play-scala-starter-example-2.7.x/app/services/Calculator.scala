package services

import javax.inject._

trait Calculator {
  def sum(a: Int, b: Int): Int
  def substract(a: Int, b: Int): Int
  def multiply(a: Int, b: Int): Int
  def divide(a: Int, b: Int): Int
}

@Singleton
class CalculatorImp extends Calculator {

  override def sum(a: Int, b: Int): Int = a + b
  override def substract(a: Int, b: Int): Int = a - b
  override def multiply(a: Int, b: Int): Int = a * b
  override def divide(a: Int, b: Int): Int = a / b

}