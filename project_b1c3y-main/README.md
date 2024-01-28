# My CPSC 210 Project

## Movie Tracker

This application will be a tracker for the movies that the user has watched. The user will be able to input the movies 
they have watched into their watched list. Users can also include the genre, director and year when they input their 
movie if they wish to do so. These features could help in finding a movie or sorting for a movie. There would also be a 
to-watch list for movies the user plans to watch but has yet to do so. Additionally, the user can leave reviews in the
form of a number out of 10 for the movies that they have watched. These reviews can help sort the movie list from 
spectacular movies to horrible movies. The people who will use this application would be people who enjoy watching 
movies and would like to keep track of the movies they have watched. People such as casual movie reviewers, film 
critics, cinephiles and anyone who likes to keep track of the films they have watched can use this application to do so. 

This project is of interest to me because during the pandemic I watched many movies and used paper to keep track of them
all. I enjoyed keeping track of the plethora of movies I watched, but even if I wrote short ratings for each movie, I
often found it difficult to easily view which movies I thought were the best and which I would never recommend anyone to
watch. Using this application, I would be able to see these statistics while keeping track of all the movies I have
watched. 

## User Stories:
- I want to be able to add a movie to the movie list.
- I want to be able to give a review to a movie in the movie list. 
- I want to be able to view all the movies in the movie list. 
- I want to be able to sort the movies in the movie list.
- I want to be able to save my movies lists when I choose to do so or not.
- I want to be able to load my previously made movie lists if I choose to do so. 

## Instructions for Grader
### To add movies: 
1. Click the button labelled Add Movie
2. Type in "watched" or "to watch" without quotations or spaces around
3. Type the title of the movie you want to add 
4. Type a number from 1-10 to give that movie a rating; if the movie is being added to "to watch" give the rating a 0 to
   indicate no rating
5. The movie should then be added
### To view movies:
1. Click the button labelled View Movies
2. Click one of the list names that you want to see and the list will appear in the middle of the screen
### To sort movies: 
1. Click the button labelled Sort Movies
2. This automatically sorts the "watched" list by rating
3. To view the sorting, click the view button and then the watched button, if the watched button is already visible, 
   click it directly
### To save movies
1. Click the Save Movies button
2. This will give you a message that the movies were saved. They will be saved to a file named "movielist.json" in the data folder
### To load movies
1. Click the Load Movies button
2. This will give you a message that the movies were loaded. They will load from a file named "movielist.json" in the data folder 

The visual component which is the splash screen when starting the application is found in the main folder in a folder 
named images. The name of this image is IMG_1850.gif for reference.

## Phase 4: Task 2
Movie added to watch list

Movie added to watch list

Movie added to watch list

Movie added to to watch list

Movie added to watch list

Movie added to watch list

Movie added to to watch list

Movie added to to watch list

Movies in watched list sorted

## Phase 4: Task 3

If I had more time to work on this project, I would refactor my DisplayPanel class to contain less duplication and 
split up the tasks that it does since has quite a lot of actions that it is handling. I would do this because this would
allow the DisplayPanel class to handle a more single responsibility such as creating the panels/frames that will be 
displayed instead of it creating the whole GUI. I would create one or more classes to handle the button making. Another 
part I would refactor in my project would be to create an abstract class for the two lists since their methods are 
quite similar. For instance, the methods they use to add movies are almost the same except for the lists they are using. 
This would decrease duplication and error in the code and make it simpler and less error-prone. Another part I would 
refactor is to make the MovieList adhere to the Singleton Design Pattern since one user should only have one MovieList 
and thus does not need access to create more than one. This would make my code more organized and less error-prone in 
the case where another MovieList was made causing bugs in the code. Another refactoring that I would have done would be 
to add exceptions in my code. There were places such as the input of movies, lists and rating that were not guarded by 
exceptions when they could have been. Adding these exceptions would have allowed my code to be more robust and able to 
handle any different inputs. 