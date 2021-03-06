import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import {AppRoutingModule} from './app-routing.module';

import { AppComponent } from './app.component';
import { DemoComponent } from './components/demo/demo.component';
import { SquarerootPipe } from './pipes/squareroot.pipe';
import { NavbarComponent } from './components/navbar/navbar.component';
import { MainComponent } from './components/main/main.component';
import { TodoComponent } from './components/todo/todo.component';
import { UserComponent } from './components/user/user.component';
import { UserService } from './services/user.service';
import { HttpClientModule } from '@angular/common/http';
import { BooksComponent } from './components/books/books.component';
import { BookStoreService } from './services/bookstore.service';


@NgModule({
 declarations: [   /* the classes that are related to views.
             There can be three types of class that contains a view: 
             components, directives, and pipes
                    */
    AppComponent,
    DemoComponent,
    SquarerootPipe,
    NavbarComponent,
    MainComponent,
    TodoComponent,
    UserComponent,
    BooksComponent
  ], // exports - classes that need to be accessible to the components of other modules
  imports: [ // modules whose classes are needed by the components of this module 
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [UserService, BookStoreService], // services
  bootstrap: [AppComponent] // the root component which is the main view of the app
})
/*
Angular provides its own system of organization of code and functionality
 and this is accomplished by containers called modules.

A module is a cohesive block of code with a related set of capabilities
which have a specific application domain or a workflow. 

All Angular applications have at least one module, the root module 
 typically defined in the file app-module.ts. Now this module ties 
 together all of our components and defined where to start to bootstap our application. 

Some applications utilize more than one module and most commonly these 
modules define particular features. For example, the HTTPModule defines 
an API to use to send AJAX requests and the BrowserModule defines functionality
 for running your application in a web browser. 
 
Now all modules are TypeScript classes annotated with the @NgModule decorator 
which takes in an object of properties or metadata that specify how to construct the module. 


BOOTSTRAPPING
	- Bootstrapping is an essential process in Angular where the application is loaded
	- The bootstrap process loads main.ts which is the main entry point of the application
  - This process also starts the dependency injection system in Angular 
  
Dependency Injection 
	-  a core concept that pre-dates Angular
    The purpose of DI is to simplify dependency management in software components.
   By reducing the amount of information a component needs to know about its dependencies. 

*/

export class AppModule { }
