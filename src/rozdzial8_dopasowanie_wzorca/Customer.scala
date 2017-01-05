package rozdzial8_dopasowanie_wzorca

import java.util.Calendar

import rozdzial7_instrukcje.{Contact, Contract}

/**
  * Created by gprawdzik on 2017-01-05.
  */
object Customer {
  def createCustomer(name : String, state : String, domain : String) : Customer = {
    name match {
      case "" => {
        println("Pole name nie może być puste")
        null
      }
      case _ => state match {
        case "" => {
          println("Pole state nie może być puste")
          null
        }
        case _ => domain match {
          case "" => {
            println("Pole domain nie może być puste")
            null
          }
          case _ => new Customer(
            0,
            name,
            state,
            domain,
            true,
            new Contract(Calendar.getInstance, true),
            List()
          )
        }
      }
    }
  }

  //jednopoziomowa struktura dopasowan
  def createCustomer2(name : String, state : String, domain : String) : Customer = {
    (name, state, domain) match {
      case ("", _, _) => {
        println("Pole name nie może być puste")
        null
      }
      case (_, "", _) => {
        println("Pole state nie może być puste")
        null
      }
      case (_, _, "") => {
        println("Pole domain nie może być puste")
        null
      }
      case _ => new Customer(
        0,
        name,
        state,
        domain,
        true,
        new Contract(Calendar.getInstance, true),
        List()
      )
    }
  }


}

class Customer(val customer_id : Integer,
               val name : String,
               val state : String,
               val domain : String,
               val enabled : Boolean,
               val contract : Contract,
               val contacts : List[Contact]) {
}
