package pl.ayeo.steps

object StepsApp extends App {

  private val data1: NameAndAge = NameAndAge("Adam Mickiewicz", 42)
  private val data2: NameAndAnimal = NameAndAnimal("Bolesław Prus", animal = true)
  private val data3: NameAndAgeAndAnimal = NameAndAgeAndAnimal("Maria Konopnicka", 24, animal = false)
  private val data4: AgeAndAnimal = AgeAndAnimal(58, animal = true)

  println(ToHListMapper.process(data1))
  println(ToHListMapper.process(data2))
  println(ToHListMapper.process(data3))
  println(ToHListMapper.process(data4))

  println(ToSubjectMapper.process(ToHListMapper.process(data1)))
  println(ToSubjectMapper.process(ToHListMapper.process(data2)))
  println(ToSubjectMapper.process(ToHListMapper.process(data3)))
  println(ToSubjectMapper.process(ToHListMapper.process(data4)))
}
