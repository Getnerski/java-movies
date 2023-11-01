package br.com.movies.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "awards")
public class Award {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "award_year")
    private int year;

    @OneToOne
    private Movie winner;

    public Award() { }

    public Long getId() {
        return id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Movie getWinner() {
        return winner;
    }

    public void setWinner(Movie winner) {
        this.winner = winner;
    }
}
