/**
 * processor for query actors
 * implementation of the methods for actors
 */
package processor.query;

import actor.ActorsAwards;
import fileio.ActionInputData;
import fileio.ActorInputData;
import inputdata.InputOutputUtils;
import store.RatingStore;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Locale;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.stream.Collectors;

public final class ActorsQueryProcessor extends QueryProcessor {
  public static final int TREI = 3;

  /**
   * * proceseaza actiunea ce trebuie efectuala pentru un actor, aplica filtrele necesare
   *(sortare) scrie rezultatul in message apoi afiseaza mesajul la output.
   * Am folosit String.join pentru a pune "," intre nume
   * @param actionInputData - actiunea ce trebuie procesata/efectuata
   */
  public void processAction(final ActionInputData actionInputData) {
    String criteria = actionInputData.getCriteria();
    // Luam datele filtrate
    List<ActorInputData> actorInputDataList =
            getActorsBasedOnCriteria(criteria, actionInputData.getFilters());
    // Sortam + limitam rezultatele
    List<ActorInputData> results = sortAndLimitResults(
            actorInputDataList,
            getComparatorForCriteria(criteria, actionInputData.getSortType()),
            actionInputData.getNumber());

    Map<String, Double> debugMapAvg =
            getActors().stream().collect(Collectors.toMap(ActorInputData::getName,
                    this::getAverageForActor));
    Map<String, List<String>> debugMapMovies =
            getActors().stream().collect(Collectors.toMap(ActorInputData::getName,
                    ActorInputData::getFilmography));

    // se face scrierea
    List<String> nameList = new LinkedList<>();
    for (ActorInputData entry : results) {
      nameList.add(entry.getName());
    }
    String message = "Query result: [" + String.join(", ", nameList) + "]";
    InputOutputUtils.getShared().writeFile(actionInputData.getActionId(), message);
  }

  /**
   * functia preia o lista de actori pe care aplica un anumit criteriu dat
   * e.g "awards" "filter_description", face filtratrea necesara si intoarce
   * doar actorii care indeplinesc criteriile respective, in ordinea alfabetica dupa nume
   * @param criteria
   * @param filters
   * @return
   */
  public List<ActorInputData> getActorsBasedOnCriteria(
          final String criteria, final List<List<String>> filters) {
    if (criteria.equals("awards")) {
      return filterBasedOnAwards(filters.get(TREI));
    } else if (criteria.equals("filter_description")) {
      return filterBasedOnWords(filters.get(2));
    } else if (criteria.equals("average")) {
      return filterWithAverage();
    }
    return List.of();
  }

  private List<ActorInputData> filterWithAverage() {
    return getActors().stream().filter(actor -> getAverageForActor(actor) != 0)
            .collect(Collectors.toList());
  }

  private List<ActorInputData> filterBasedOnWords(final List<String> wordsFilter) {
    List<ActorInputData> actorInputDataList = getActors();
    List<ActorInputData> results = new LinkedList<>();

    for (ActorInputData actor : actorInputDataList) {
      boolean found = true;
      for (String word : wordsFilter) {
        String description = actor.getCareerDescription().toLowerCase(Locale.ROOT);
        //am folosit split deoarece se luau gresit anumiti actori fiindca cuvantul award
        // continea "war" in el ii si punea implicit si pe ei in query results
        Set<String> splitWords = new HashSet<>(Arrays.asList(description.split("\\W+")));

        if (!splitWords.contains(word)) {
          found = false;
          break;
        }
      }

      if (found) {
        results.add(actor);
      }
    }

    return results;
  }

  private List<ActorInputData> filterBasedOnAwards(final List<String> awardsFilter) {
    List<ActorInputData> actorInputDataList = getActors();
    List<ActorInputData> results = new LinkedList<>();
    // Filtrez in functie de daca au sau nu awards

    for (ActorInputData actor : actorInputDataList) {
      boolean found = true;
      for (String awardString : awardsFilter) {
        ActorsAwards award = ActorsAwards.valueOf(awardString); // Din string => enum
        if (!actor.getAwards().containsKey(award)) {
          found = false;
          break;
        }
      }

      if (found) {
        results.add(actor);
      }
    }

    return results;
  }

  private List<ActorInputData> getActors() {
    return InputOutputUtils.getShared().getInput().getActors();
  }

  private Comparator<ActorInputData> getNameComparator(final String sort) {
    return new Comparator<ActorInputData>() {
      @Override
      public int compare(final ActorInputData o1, final ActorInputData o2) {
        String o1Name = o1.getName();
        String o2Name = o2.getName();
        return o1Name.compareTo(o2Name) * getCompareFactorForSort(sort);
      }
    };
  }

  private Comparator<ActorInputData> getIdentityComparator(final String sort) {
    return new Comparator<ActorInputData>() {
      @Override
      public int compare(final ActorInputData o1, final ActorInputData o2) {
        return 0;
      }
    };
  }

  private double getAverageForActor(final ActorInputData actorInputData) {
    RatingStore ratingStore = RatingStore.getInstance();
    List<String> filmography = actorInputData.getFilmography();
    if (filmography.isEmpty()) {
      return 0;
    }

    double total = 0;
    int count = 0;
    for (String video : filmography) {
      double rating = ratingStore.getRatingForTitle(video);
      if (rating > 0) {
        total += rating;
        count++;
      }
    }

    if (count == 0) {
      return 0;
    }

    return total / count;
  }

  private Comparator<ActorInputData> getAverageComparator(final String sort) {
    return new Comparator<ActorInputData>() {
      @Override
      public int compare(final ActorInputData o1, final ActorInputData o2) {
        int compareResult =  Double.compare(getAverageForActor(o1), getAverageForActor(o2))
                * getCompareFactorForSort(sort);
        if (compareResult == 0) {
          return o1.getName().compareTo(o2.getName()) * getCompareFactorForSort(sort);
        }
        return compareResult;
      }
    };
  }

  private Comparator<ActorInputData> getAwardsComparator(final String sort) {
    return new Comparator<ActorInputData>() {
      @Override
      public int compare(final ActorInputData o1, final ActorInputData o2) {
        int o1Sum = o1.getAwards().values().stream().mapToInt(x -> x).sum();
        // x -> x functie identitate
        int o2Sum = o2.getAwards().values().stream().mapToInt(x -> x).sum();
        // x -> x functie identitate

        if (o1Sum == o2Sum) {
          return o1.getName().compareTo(o2.getName()) * getCompareFactorForSort(sort);
        }

        return Integer.compare(o1Sum, o2Sum) * getCompareFactorForSort(sort);
      }
    };
  }

  private Comparator<ActorInputData> getComparatorForCriteria(
          final String criteria, final String sortType) {
    if (criteria.equals("awards")) {
      return getAwardsComparator(sortType);
    } else if (criteria.equals("filter_description")) {
      return getNameComparator(sortType);
    } else {
      return getAverageComparator(sortType);
    }
  }
}
