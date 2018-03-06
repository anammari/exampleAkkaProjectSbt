package akkaquote.actor

import akka.actor.Actor
import scala.collection.mutable.ListBuffer
import akkaquote.message.{ Quote, RequestQuote, AddQuote, QuoteAdded, QuoteRequested }
import scala.util.Random

class QuotesHandlerActor extends Actor {
  val quotes = new ListBuffer[Quote]()

  def receive = {
    case AddQuote(quote) => {
      quotes += quote
      sender ! QuoteAdded
    }

    case RequestQuote(originalSender) => {
      val index = Random.nextInt(quotes.size)
      sender ! QuoteRequested(quotes(index), originalSender)
    }
  } 
}