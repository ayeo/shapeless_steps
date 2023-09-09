package pl.ayeo.steps

import shapeless.{::, HList, HNil}

object StepsApp extends App {

  case object Empty

  sealed trait Subject
  case class NameAndAnimal(name: String, animal: Boolean) extends Subject
  case class NameAndAge(name: String, age: Int) extends Subject
  case class AgeAndAnimal(age: Int, animal: Boolean) extends Subject
  case class NameAndAgeAndAnimal(name: String, age: Int, animal: Boolean) extends Subject

  object Mapper {

    def from(s: Subject): Serializable :: Any :: Any :: HNil = s match {
      case na: NameAndAnimal => na.name :: Empty :: na.animal :: HNil
      case na: NameAndAge => na.name :: na.age :: Empty :: HNil
      case na: NameAndAgeAndAnimal => na.name :: na.age :: na.animal :: HNil
      case na: AgeAndAnimal => Empty :: na.age :: na.animal :: HNil
    }

    def to(hlist: HList): Subject = hlist match {
      case (name: String) :: (age: Int) :: Empty :: HNil => NameAndAge(name, age)
      case (name: String) :: (age: Int) :: (hasAnimal: Boolean) :: HNil => NameAndAgeAndAnimal(name, age, hasAnimal)
      case (name: String) :: Empty :: (hasAnimal: Boolean) :: HNil => NameAndAnimal(name, hasAnimal)
      case Empty :: (age: Int) :: (hasAnimal: Boolean) :: HNil => AgeAndAnimal(age, hasAnimal)
    }
  }

  private val data1: NameAndAge = NameAndAge("Adam Mickiewicz", 42)
  private val data2: NameAndAnimal = NameAndAnimal("Bolesław Prus", animal = true)
  private val data3: NameAndAgeAndAnimal = NameAndAgeAndAnimal("Maria Konopnicka", 24, animal = false)
  private val data4: AgeAndAnimal = AgeAndAnimal(58, animal = true)

  println(Mapper.from(data1))
  println(Mapper.from(data2))
  println(Mapper.from(data3))
  println(Mapper.from(data4))

  println(Mapper.to(Mapper.from(data1)))
  println(Mapper.to(Mapper.from(data2)))
  println(Mapper.to(Mapper.from(data3)))
  println(Mapper.to(Mapper.from(data4)))
}
