package actions

object Main {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(75); 


def metodo():String={
val a ="aaaaaaa"
a
};System.out.println("""metodo: ()String""");$skip(26); 

  var resultado=metodo();System.out.println("""resultado  : String = """ + $show(resultado ));$skip(19); 
  print(resultado);$skip(6); val res$0 = 
  1+1;System.out.println("""res0: Int(2) = """ + $show(res$0));$skip(55); val res$1 = 
  
  for{
     resultado2 <- metodo()
  }
  yield{
  };System.out.println("""res1: scala.collection.immutable.IndexedSeq[Unit] = """ + $show(res$1))}

}
