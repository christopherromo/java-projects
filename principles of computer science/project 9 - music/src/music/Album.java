
/**
 * Album.java
 * 
 * This file defines the Album class, which creates an album object.
 * 
 * @author Christopher Romo
 * @since 07/24/2022
 * @version 1.0
 */

package src.music;

/**
 * The Album class creates an album object.
 */
public class Album {
    // instance variables
    private String title;
    private String artist;
    private int year;

    /**
     * constructor that creates an album object
     * 
     * @param title
     * @param artist
     * @param year
     */
    public Album(String title, String artist, int year) {
        this.title = title;
        this.artist = artist;
        this.year = year;
    }

    // getters
    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public int getYear() {
        return year;
    }

    // mutators
    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setYear(int year) {
        this.year = year;
    }

    /**
     * prints the album information
     */
    public void print() {
        System.out.println("Title:  " + title);
        System.out.println("Artist: " + artist);
        System.out.println("Year:   " + year);
        
    }
} // Album