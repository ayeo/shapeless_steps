package pl.ayeo.steps

import shapeless._

private object ToSubjectMapper {
  private object :: {
    def unapply[A, L <: HList](l: A :: L): Option[(A, L)] = Some((l.head, l.tail))
  }

  def process(hlist: HList): Subject = hlist match {
    case (name: String) :: (age: Int) :: Empty :: HNil =>
      NameAndAge(name, age)
    case (name: String) :: (age: Int) :: (hasAnimal: Boolean) :: HNil =>
      NameAndAgeAndAnimal(name, age, hasAnimal)
    case (name: String) :: Empty :: (hasAnimal: Boolean) :: HNil =>
      NameAndAnimal(name, hasAnimal)
    case Empty :: (age: Int) :: (hasAnimal: Boolean) :: HNil =>
      AgeAndAnimal(age, hasAnimal)
  }
}
