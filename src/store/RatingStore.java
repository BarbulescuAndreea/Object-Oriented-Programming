package store;

import fileio.SerialInputData;
import inputdata.InputOutputUtils;

import java.util.HashMap;
import java.util.Map;

public final class RatingStore {
  private static RatingStore instance;

  /**
   * Clasa de tip singleton-folosim getInstance pentru a accesa instanta unica a clasei
   * @return
   */
  public static synchronized RatingStore getInstance() {
   if (instance == null) {
     instance = new RatingStore();
   }

   return instance;
  }

  // titlu -> Map username -> rating acordat
  private Map<String, Map<String, Double>> movieRatingsMap;
  //titlu => Map serial -> map username -> rating acordat
  private Map<String, Map<Integer, Map<String, Double>>> serialRatingsMap;


  public Map<String, Map<String, Double>> getMovieRatingsMap() {
    return movieRatingsMap;
  }

  public Map<String, Map<Integer, Map<String, Double>>> getSerialRatingsMap() {
    return serialRatingsMap;
  }

  /**
   * addRating- functie care da permite unui utilizator sa dea Rating unui film o singura data.
   * @param title
   * @param username
   * @param rating
   * @throws RatingException
   */
  public void addRating(
          final String title, final String username, final double rating)
          throws RatingException {
    Map<String, Double> ratingMap = movieRatingsMap.computeIfAbsent(title, (k) -> new HashMap<>());
    if (ratingMap.containsKey(username)) {
      throw new RatingException();
    }

    ratingMap.put(username, rating);
  }

  /**
   * addRating- functie care da permite unui utilizator sa dea Rating unui sezon
   * al unui serial o singura data.
   * @param title
   * @param season
   * @param username
   * @param rating
   * @throws RatingException
   */
  public void addRating(
          final String title, final int season, final String username, final double rating)
          throws RatingException {
    Map<Integer, Map<String, Double>> seasonMap
            = serialRatingsMap.computeIfAbsent(title, (k) -> new HashMap<>());
    Map<String, Double> ratingMap = seasonMap.computeIfAbsent(season, (k) -> new HashMap<>());

    if (ratingMap.containsKey(username)) {
      throw new RatingException();
    }

    ratingMap.put(username, rating);
  }

  private double getRatingForMovie(final String title) {
    Map<String, Double> ratingMap = movieRatingsMap.get(title);
    if (ratingMap == null || ratingMap.size() == 0) {
      return 0;
    }

    double total = 0;
    for (double rating : ratingMap.values()) {
      total += rating;
    }

    return total / ratingMap.size();
  }

  private double getRatingForSerial(final String title) {
    Map<Integer, Map<String, Double>> seasonMap = serialRatingsMap.get(title);
    if (seasonMap == null || seasonMap.size() == 0) {
      return 0;
    }

    double total = 0;
    for (Map<String, Double> ratingMap : seasonMap.values()) {
      double seasonTotal = 0;
      if (ratingMap.size() == 0) {
        continue;
      }

      for (double rating : ratingMap.values()) {
        seasonTotal += rating;
      }

      total += (seasonTotal / ratingMap.size());
    }

    for (SerialInputData serialInputData : InputOutputUtils.getShared().getInput().getSerials()) {
      if (serialInputData.getTitle().equals(title)) {
        return total / serialInputData.getNumberSeason();
      }
    }

    return total / seasonMap.size();
  }


  /**
   * Intoarce rating-ul unui film/serial in functie de titlul acestuia
   * @param title
   * @return
   */
  public double getRatingForTitle(final String title) {
    if (movieRatingsMap.containsKey(title)) {
      return getRatingForMovie(title);
    } else if (serialRatingsMap.containsKey(title)) {
      return getRatingForSerial(title);
    }
    return 0;
  }

  /**
   * Dupa fiecare test dam clear ca sa nu influenteze testul viitor
   */
  public void clear() {
    movieRatingsMap.clear();
    serialRatingsMap.clear();
  }

  private RatingStore() {
    movieRatingsMap = new HashMap<>();
    serialRatingsMap = new HashMap<>();
  }
}
