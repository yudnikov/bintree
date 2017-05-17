import ru.yudnikov.bintree._

val x = new NonEmpty(12) include(2) include(45) include(3)
val y = new NonEmpty(6) include(13) include(0)
val z = x.union(y.asInstanceOf[NonEmpty])
z
