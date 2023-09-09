package pl.ayeo.steps

import shapeless.{::, HNil}

object ToHListMapper {
  def process(s: Subject): Serializable :: Any :: Any :: HNil = s match {
    case na: NameAndAnimal =>  na.name :: Empty :: na.animal :: HNil
    case na: NameAndAge => na.name :: na.age :: Empty :: HNil
    case na: NameAndAgeAndAnimal => na.name :: na.age :: na.animal :: HNil
    case na: AgeAndAnimal => Empty :: na.age :: na.animal :: HNil
  }
}
