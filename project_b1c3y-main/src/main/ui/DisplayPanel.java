package ui;

import model.Event;
import model.EventLog;
import model.Movie;
import model.MovieList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import static java.lang.System.exit;

// creates the features being displayed in the application
// parts of code based on SimpleDrawingPlayer and sample application
// https://github.students.cs.ubc.ca/CPSC210/SimpleDrawingPlayer-Starter.git
// // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class DisplayPanel extends JFrame implements ActionListener {

    private static final int WIDTH = 510;
    private static final int HEIGHT = 500;
    private static final String JSON_STORE = "./data/movielist.json";

    private MovieList movieList;
    private JPanel buttonArea;
    private JPanel textArea;
    private JLabel watchedList;
    private JLabel toWatchList;
    private JButton watchedViewButton;
    private JButton toWatchViewButton;
    private JButton addButton;
    private JButton viewButton;
    private JButton sortButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton quitButton;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: creates a display panel with all the buttons, panels and labels
    public DisplayPanel() {
        super("Movie Tracker");
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            System.out.println("UI error");
        }
        movieList = new MovieList();
        buttonArea = new JPanel();
        textArea = new JPanel();
        watchedViewButton = new JButton("Watched");
        toWatchViewButton = new JButton("To Watch");
        addButton = new JButton("Add Movie");
        viewButton = new JButton("View Movies");
        sortButton = new JButton("Sort Movies");
        saveButton = new JButton("Save Movies");
        loadButton = new JButton("Load Movies");
        quitButton = new JButton("Quit");
        watchedList = new JLabel("", SwingConstants.CENTER);
        toWatchList = new JLabel("", SwingConstants.CENTER);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        initializeGraphics();
    }

    // EFFECTS: creates the buttons, and panels graphically
    private void initializeGraphics() {
        buttonArea.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        buttonArea.setSize(500, 100);
        add(buttonArea);
        textArea.setLayout(new FlowLayout(FlowLayout.CENTER));
        textArea.setSize(500, 200);
        add(textArea);

        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(watchedList);
        add(toWatchList);
        createButtons();
        pack();
        setVisible(true);
    }

    // EFFECTS: creates all the buttons with their functions
    private void createButtons() {

        createAddButton();
        createViewButton();
        createSortButton();
        createSaveButton();
        createLoadButton();
        createViewListButton();
        watchedViewButton.setVisible(false);
        toWatchViewButton.setVisible(false);
        createQuitButton();

        pack();
    }

    // EFFECTS: creates the add button and assigns its function
    private void createAddButton() {
        addButton.setActionCommand("add movie");
        addButton.addActionListener(this);
        buttonArea.add(addButton);
    }

    // EFFECTS: creates the view button and assigns its function
    private void createViewButton() {
        viewButton.setActionCommand("view movies");
        viewButton.addActionListener(this);
        buttonArea.add(viewButton);
    }

    // EFFECTS: creates the sort button and assigns its function
    private void createSortButton() {
        sortButton.setActionCommand("sort movies");
        sortButton.addActionListener(this);
        buttonArea.add(sortButton);
    }

    // EFFECTS: asks for input from user for the list the user wants sorted and sorts it by rating
    private void sortMovies() {
        JOptionPane.showMessageDialog(this, "This will keep the watched list sorted by RATING");
        movieList.sortMovies(movieList.getWatched());
    }

    // EFFECTS: creates the save button and assigns its function
    private void createSaveButton() {
        saveButton.setActionCommand("save movies");
        saveButton.addActionListener(this);
        buttonArea.add(saveButton);
    }

    // EFFECTS: saves both movie lists
    private void saveMovie() {
        try {
            jsonWriter.open();
            jsonWriter.write(movieList);
            jsonWriter.close();
            JOptionPane.showMessageDialog(this, "Movies saved :)");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // EFFECTS: creates the load button and assigns its function
    private void createLoadButton() {
        loadButton.setActionCommand("load movies");
        loadButton.addActionListener(this);
        buttonArea.add(loadButton);
    }

    // EFFECTS: loads the movies from file
    private void loadMovie() {
        try {
            movieList = jsonReader.read();
            JOptionPane.showMessageDialog(this, "Movies loaded :)");
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: creates the quit button and assigns its function
    private void createQuitButton() {
        quitButton.setActionCommand("quit");
        quitButton.addActionListener(this);
        buttonArea.add(quitButton);
    }

    // EFFECTS: quits the whole application
    private void quit() {
        for (Event next : EventLog.getInstance()) {
            System.out.println(next.getDescription() + "\n" + "");
        }
        exit(0);
    }

    // EFFECTS: creates the list buttons and assigns its function
    private void createViewListButton() {
        watchedViewButton.setActionCommand("view watched");
        watchedViewButton.addActionListener(this);
        buttonArea.add(watchedViewButton, BorderLayout.WEST);

        toWatchViewButton.setActionCommand("view to watch");
        toWatchViewButton.addActionListener(this);
        buttonArea.add(toWatchViewButton, BorderLayout.EAST);
        pack();
    }

    // EFFECTS: adds movies to correct list
    private void addMovie() {
        String list = inputMovieList();
        if (list.equals("watched")) {
            movieList.addMovieWatched(inputMovieAndRating());
        } else if (list.equals("to watch")) {
            movieList.addMovieToWatch(inputMovieAndRating());
        }
    }

    // EFFECTS: asks user for input to choose the list they want to add to
    public String inputMovieList() {
        return JOptionPane.showInputDialog("Do you want to add to watched or to watch list?");
    }

    // EFFECTS: asks user for input for movie name and rating then creates and returns that movie
    public Movie inputMovieAndRating() {
        String movie = JOptionPane.showInputDialog("What movie do you want to add?");
        String rating = JOptionPane.showInputDialog("Give it a rating between 1 and 10 or 0 for no rating.");
        JOptionPane.showMessageDialog(this, movie + " added with a " + rating + " rating :)");
        return new Movie(movie, Integer.parseInt(rating));
    }

    // EFFECTS: displays watched or to watch list of movies on the screen
    private void showMovies(String list) {
        if (list.equals("watched")) {
            textArea.add(watchedList);
            add(watchedList);
            watchedList.setText("Watched: " + movieList.toString("watched"));

        } else if (list.equals("to watch")) {
            textArea.add(toWatchList);
            add(toWatchList);
            toWatchList.setText("To watch : " + movieList.toString("to watch"));
        }
    }

    // EFFECTS: performs the actions of each button
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("add movie")) {
            addMovie();
        } else if (e.getActionCommand().equals("view movies")) {
            watchedViewButton.setVisible(true);
            toWatchViewButton.setVisible(true);
        } else if (e.getActionCommand().equals("sort movies")) {
            sortMovies();
        } else if (e.getActionCommand().equals("save movies")) {
            saveMovie();
        } else if (e.getActionCommand().equals("load movies")) {
            loadMovie();
        } else if (e.getActionCommand().equals("quit")) {
            quit();
        } else if (e.getActionCommand().equals("view watched")) {
            showMovies("watched");
            toWatchList.setVisible(false);
            watchedList.setVisible(true);
        } else if (e.getActionCommand().equals("view to watch")) {
            showMovies("to watch");
            watchedList.setVisible(false);
            toWatchList.setVisible(true);
        }
    }
}
