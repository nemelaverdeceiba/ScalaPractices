package actions

object Main {


def metodo():String={
val a ="aaaaaaa"
a
}                                                 //> metodo: ()String

  var resultado=metodo()                          //> resultado  : String = aaaaaaa
  print(resultado)                                //> aaaaaaa
  1+1                                             //> res0: Int(2) = 2
  
  for{
     resultado2 <- metodo()
  }
  yield{
  }                                               //> res1: scala.collection.immutable.IndexedSeq[Unit] = Vector((), (), (), (), (
                                                  //| ), (), ())

}