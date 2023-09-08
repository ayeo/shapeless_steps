package pl.ayeo

package object steps {

  case object Empty

  sealed trait Subject
  case class NameAndAnimal(name: String, animal: Boolean) extends Subject
  case class NameAndAge(name: String, age: Int) extends Subject
  case class AgeAndAnimal(age: Int, animal: Boolean) extends Subject
  case class NameAndAgeAndAnimal(name: String, age: Int, animal: Boolean) extends Subject
}
