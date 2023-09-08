package pl.ayeo.steps

import shapeless.{::, HList, HNil}

object ToHListMapper {

  trait Converters[A] {
    type Out <: HList
    def toHList(a: A): Out
  }

  private type Aux[A, Out0 <: HList] = Converters[A] {type Out = Out0}

  def apply[A](implicit ev: Converters[A]): Aux[A, ev.Out] = ev

  implicit val nameAndAnimalToHList: Aux[NameAndAnimal, String :: Empty.type :: Boolean :: HNil] =
    new Converters[NameAndAnimal] {
      type Out = String :: Empty.type :: Boolean :: HNil

      def toHList(na: NameAndAnimal): Out = na.name :: Empty :: na.animal :: HNil
    }

  implicit val ageAndAnimalToHList: Aux[AgeAndAnimal, Empty.type :: Int :: Boolean :: HNil] =
    new Converters[AgeAndAnimal] {
      type Out = Empty.type :: Int :: Boolean :: HNil

      def toHList(na: AgeAndAnimal): Out = Empty :: na.age :: na.animal :: HNil
    }

  implicit val nameAndAnimalToHList2: Aux[NameAndAge, String :: Int :: Empty.type :: HNil] =
    new Converters[NameAndAge] {
      type Out = String :: Int :: Empty.type :: HNil

      def toHList(na: NameAndAge): Out = na.name :: na.age :: Empty :: HNil
    }

  implicit val nameAndAgeAndAnimalToHList: Aux[NameAndAgeAndAnimal, String :: Int :: Boolean :: HNil] =
    new Converters[NameAndAgeAndAnimal] {
      type Out = String :: Int :: Boolean :: HNil

      def toHList(na: NameAndAgeAndAnimal): Out = na.name :: na.age :: na.animal :: HNil
    }

  private def convertToHList[A <: Subject](input: A)(implicit ev: Converters[A]): ev.Out = ev.toHList(input)

  def process(s: Subject): Serializable :: Any :: Any :: HNil = s match {
    case n: NameAndAnimal => convertToHList(n)
    case n: NameAndAge => convertToHList(n)
    case n: NameAndAgeAndAnimal => convertToHList(n)
    case n: AgeAndAnimal => convertToHList(n)
  }
}
