import scala.collection.mutable.Set
import scala.io.StdIn._

case class Book(title: String, author: String, isbn: String)

object Library {
  // Initial set of books
  var books: Set[Book] = Set(
    Book("1984", "George Orwell", "1234567890"),
    Book("To Kill a Mockingbird", "Harper Lee", "2345678901"),
    Book("The Great Gatsby", "F. Scott Fitzgerald", "3456789012")
  )

  // Add a new book to the library
  def addBook(book: Book): Unit = {
    books += book
    println(s"Book added: ${book.title} by ${book.author}")
  }

  // Remove a book from the library by its ISBN
  def removeBook(isbn: String): Unit = {
    val bookToRemove = books.find(_.isbn == isbn)
    bookToRemove match {
      case Some(book) =>
        books -= book
        println(s"Book removed: ${book.title} by ${book.author}")
      case None => println(s"No book found with ISBN: $isbn")
    }
  }

  // Check if a book is already in the library by its ISBN
  def checkBook(isbn: String): Boolean = {
    books.exists(_.isbn == isbn)
  }

  // Display the current library collection
  def displayLibrary(): Unit = {
    if (books.isEmpty) {
      println("The library is empty.")
    } else {
      books.foreach { book =>
        println(s"Title: ${book.title}, Author: ${book.author}, ISBN: ${book.isbn}")
      }
    }
  }

  // Search for a book by its title
  def searchBookByTitle(title: String): Unit = {
    val bookFound = books.find(_.title.equalsIgnoreCase(title))
    bookFound match {
      case Some(book) =>
        println(s"Book found: Title: ${book.title}, Author: ${book.author}, ISBN: ${book.isbn}")
      case None => println(s"No book found with title: $title")
    }
  }

  // Display all books by a specific author
  def displayBooksByAuthor(author: String): Unit = {
    val booksByAuthor = books.filter(_.author.equalsIgnoreCase(author))
    if (booksByAuthor.isEmpty) {
      println(s"No books found by author: $author")
    } else {
      booksByAuthor.foreach { book =>
        println(s"Title: ${book.title}, ISBN: ${book.isbn}")
      }
    }
  }

  // Main function to interact with the library
  def main(args: Array[String]): Unit = {
    var continue = true

    while (continue) {
      println("\nLibrary Management System")
      println("1. Display library collection")
      println("2. Add a book")
      println("3. Remove a book")
      println("4. Check if a book is in the library")
      println("5. Search for a book by title")
      println("6. Display all books by an author")
      println("7. Exit")
      print("Choose an option: ")

      val choice = readInt()
      choice match {
        case 1 => displayLibrary()
        case 2 =>
          print("Enter book title: ")
          val title = readLine()
          print("Enter book author: ")
          val author = readLine()
          print("Enter book ISBN: ")
          val isbn = readLine()
          addBook(Book(title, author, isbn))
        case 3 =>
          print("Enter book ISBN to remove: ")
          val isbn = readLine()
          removeBook(isbn)
        case 4 =>
          print("Enter book ISBN to check: ")
          val isbn = readLine()
          if (checkBook(isbn)) {
            println("The book is in the library.")
          } else {
            println("The book is not in the library.")
          }
        case 5 =>
          print("Enter book title to search: ")
          val title = readLine()
          searchBookByTitle(title)
        case 6 =>
          print("Enter author name to search: ")
          val author = readLine()
          displayBooksByAuthor(author)
        case 7 => continue = false
        case _ => println("Invalid option. Please try again.")
      }
    }
  }
}

// Running the main function to start the library management system
// Library.main(Array.empty)
