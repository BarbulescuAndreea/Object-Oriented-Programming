/**
 * processor for query movies
 * implementation of the methods for movies
 */
package processor.query;

import fileio.ActionInputData;
//import fileio.ActorInputData;
import fileio.MovieInputData;
import fileio.UserInputData;
import inputdata.InputOutputUtils;
import store.RatingStore;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public final class MoviesQueryProcessor extends QueryProcessor {
  public static final int TREI = 3;
  @Override
  public void processAction(final ActionInputData actionInputData) {
    String criteria = actionInputData.getCriteria();

    List<MovieInputData> movieInputDataList =
            getMoviesBasedOnCriteria(criteria, actionInputData.getFilters());
    //sortam
    List<MovieInputData> results = sortAndLimitResults(
            movieInputDataList,
            getComparatorForCriteria(criteria, actionInputData.getSortType()),
            actionInputData.getNumber());

    List<String> nameList = new LinkedList<>();
    for (MovieInputData entry : results) {
      nameList.add(entry.getTitle());
    }
    String message = "Query result: [" + String.join(", ", nameList) + "]";
    //afisam
    InputOutputUtils.getShared().writeFile(actionInputData.getActionId(), message);
  }

  private Comparator<MovieInputData> getComparatorForCriteria(
          final String criteria, final String sort) {
    if (criteria.equals("longest")) {
      return getComparatorForLongest(sort);
    } else if (criteria.equals("favorite")) {
      return getComparatorForFavorite(sort);
    } else if (criteria.equals("most_viewed")) {
      return getComparatorForMostViewed(sort);
    }

    return getComparatorForLongest(sort);
  }

  private List<MovieInputData> getMoviesBasedOnCriteria(
          final String criteria, final List<List<String>> filters) {
    String year = filters.get(0).get(0);
    String genre = filters.get(1).get(0);
    List<String> words = filters.get(2);
    List<String> awards = filters.get(TREI);

    List<MovieInputData> result =
            new ArrayList<>(InputOutputUtils.getShared().getInput().getMovies());

    if (year != null) {
      int yearInt = Integer.parseInt(year);
      result = filterByYear(result, yearInt);
    }

    if (genre != null) {
      result = filterByGenre(result, genre);
    }

    if (criteria.equals("most_viewed")) {
      // scoate filmele din lista de rezultate daca sunt 0
      result.removeIf(
              (movieInputData -> getViewCountForMovieTitle(movieInputData.getTitle()) == 0));
    }

    if (criteria.equals("ratings")) {
      result = filterWithRating(result);
    }

    return result;
  }

  private static List<MovieInputData> filterWithRating(final List<MovieInputData> result) {
    RatingStore ratingStore = RatingStore.getInstance();
    return result.stream()
            .filter(movieInputData -> ratingStore.getRatingForTitle(movieInputData.getTitle()) != 0)
            .collect(Collectors.toList());
  }

  private static int getViewCountForMovieTitle(final String title) {
    int count = 0;
    List<UserInputData> users = InputOutputUtils.getShared().getInput().getUsers();
    for (UserInputData user : users) {
      // iau cate vizualizari are userul pentru film, daca nu exista adaug 0 ca default
      // sa nu influenteze rezultatul
      count += user.getHistory().getOrDefault(title, 0);
    }
    return count;
  }

  private Comparator<MovieInputData> getComparatorForMostViewed(final String sort) {
    return new Comparator<MovieInputData>() {
      @Override
      public int compare(final MovieInputData o1, final MovieInputData o2) {
        String t1 = o1.getTitle();
        String t2 = o2.getTitle();

        return Integer.compare(
                getViewCountForMovieTitle(t1), getViewCountForMovieTitle(t2))
                * getCompareFactorForSort(sort);
      }
    };
  }

  private Comparator<MovieInputData> getComparatorForLongest(final String sort) {
    return new Comparator<MovieInputData>() {
      @Override
      public int compare(final MovieInputData o1, final MovieInputData o2) {
        int compareResult = Integer.compare(o1.getDuration(),
                o2.getDuration()) * getCompareFactorForSort(sort);
        if (compareResult == 0) {
          return o1.getTitle().compareTo(o2.getTitle()) * getCompareFactorForSort(sort);
        }

        return compareResult;
      }
    };
  }

  private static int getFavoriteCountForMovieTitle(final String title) {
    int count = 0;
    List<UserInputData> users = InputOutputUtils.getShared().getInput().getUsers();
    for (UserInputData user : users) {
      if (user.getFavoriteMovies().contains(title)) {
        count++;
      }
    }

    return count;
  }

  private Comparator<MovieInputData> getComparatorForFavorite(final String sort) {
    return new Comparator<MovieInputData>() {
      @Override
      public int compare(final MovieInputData o1, final MovieInputData o2) {
        String t1 = o1.getTitle();
        String t2 = o2.getTitle();

        int compareResults = Integer.compare(getFavoriteCountForMovieTitle(t1),
                getFavoriteCountForMovieTitle(t2)) * getCompareFactorForSort(sort);

        if (compareResults == 0) {
          return t1.compareTo(t2) * getCompareFactorForSort(sort);
        }

        return compareResults;
      }
    };
  }

  private List<MovieInputData> filterByYear(
          final List<MovieInputData> movieInputDataList, final int year) {
    List<MovieInputData> result = new LinkedList<>();
    for (MovieInputData movie : movieInputDataList) {
      if (movie.getYear() == year) {
        result.add(movie);
      }
    }

    return result;
  }

  private List<MovieInputData> filterByGenre(
          final List<MovieInputData> movieInputDataList, final String genre) {
    List<MovieInputData> result = new LinkedList<>();
    for (MovieInputData movie : movieInputDataList) {
      if (movie.getGenres().contains(genre)) {
        result.add(movie);
      }
    }

    return result;
  }
}
