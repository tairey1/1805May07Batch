window.onload = function(){
    loadGenres();
    $('#add')
}

function validateISBN(){
    var input = document.getElementById("isbn").value;
    var numRegEx = /^[0-9]+$/;
    if(! (numRegEx.test(input) & input.length == 10)){
        document.getElementById("isbnwarning").hidden = false;
        document.getElementById("add").setAttribute("disabled", true);
        document.getElementById("isbnwarning").innerHTML = "Not valid input. Please enter a 10 digit number";
    }
    else{
        document.getElementById("isbnwarning").hidden = true;
        document.getElementById("add").removeAttribute("disabled");
    }

}

var count = 100;

/*
This function takes input from the user, and formats it in 
a table by appending a row to the preexisting students table
*/
function addBookRow(book, genre){
    //getting our input values 
    var id = book.id;
    var isbn = book.isbn;
    var title = book.title;
    var price = book.price;
    var genre = genre;

    //generate new elements
    var row = document.createElement("tr");
    var cell1 = document.createElement("td");
    var cell2 = document.createElement("td");
    var cell3 = document.createElement("td");
    var cell4 = document.createElement("td");
    var cell5 = document.createElement("td");

    //assign our cells appropriate data
    cell1.innerHTML = id; // <td> </td>
    cell2.innerHTML = isbn;
    cell3.innerHTML = title;
    cell4.innerHTML = price;
    cell5.innerHTML = genre;

    // append each element to its parent

    row.appendChild(cell1);
    row.appendChild(cell2);
    row.appendChild(cell3);
    row.appendChild(cell4);
    row.appendChild(cell5);

    document.getElementById("tablebody").appendChild(row);
}

function addNewBook(){
    var isbn = document.getElementById("isbn").value;
    var title = document.getElementById("title").value;
    var price = document.getElementById("price").value;
    var e = document.getElementById("genres");
    var genre = e.options[e.selectedIndex].value;
    var newBook = {
        "isbn": isbn,
        "title": title,
        "price": price,
        "genre": genre
    }

    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if(xhr.readyState == 4 && xhr.status == 201){
            console.log("Hello World");
            addBookRow(newBook, e.options[e.selectedIndex].innerHTML);
        }
    }

    xhr.open("POST", "http://localhost:3000/books",true);
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.send(JSON.stringify(newBook));
}

function loadGenres(){
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if(xhr.readyState == 4 && xhr.status == 200){
            var genres = JSON.parse(xhr.responseText);
            for(let i = 0; i < genres.length; i++)
            {
                var elem = document.createElement("option");
                elem.value = genres[i].id;
                elem.innerHTML = genres[i].name;
                $('#genres').append(elem);          
            }
            loadBooks(genres);
        }
    }
    xhr.open("GET", "http://localhost:3000/genres", true);
    xhr.send();
}

function loadBooks(genres){
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if(xhr.readyState == 4 && xhr.status == 200)
        {
            var books = JSON.parse(xhr.responseText);
            for(let i = 0; i < books.length; i++)
            {
                addBookRow(books[i], genres[(books[i].genre)-1].name);
            }
            
        }
    }
    xhr.open("GET", "http://localhost:3000/books", true);
    xhr.send();
}