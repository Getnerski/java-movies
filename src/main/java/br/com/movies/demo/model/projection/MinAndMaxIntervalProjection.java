package br.com.movies.demo.model.projection;

public interface MinAndMaxIntervalProjection {

    String getProducer();
    int getPreviousAwardYear();
    int getFollowingAwardYear();
    int getDifference();

}

