package processor.query;

import entertainment.Season;
import fileio.ActionInputData;
import fileio.SerialInputData;
import fileio.ShowInput;
import fileio.UserInputData;
import inputdata.InputOutputUtils;
import store.RatingStore;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
//procesorul pentru show-uri

public final class ShowQueryProcessor extends QueryProcessor {
    public static final int TREI = 3;

    /**
     * proceseaza actiunea ce trebuie efectuala pentru un show, aplica filtrele necesare
     * (sortare) scrie rezultatul in message apoi afiseaza mesajul la output.
     * Am folosit String.join pentru a pune "," intre nume
     * @param actionInputData
     */
    public void processAction(final ActionInputData actionInputData) {
        String criteria = actionInputData.getCriteria();

        List<SerialInputData> showInputDataList =
              getShowsBasedOnCriteria(criteria, actionInputData.getFilters());

        List<SerialInputData> results = sortAndLimitResults(
                showInputDataList,
                getComparatorForCriteria(criteria, actionInputData.getSortType()),
                actionInputData.getNumber());

        List<String> nameList = new LinkedList<>();
        for (ShowInput entry : results) {
            nameList.add(entry.getTitle());
        }
        //scriere rezultat
        String message = "Query result: [" + String.join(", ", nameList) + "]";
        //afisare
        InputOutputUtils.getShared().writeFile(actionInputData.getActionId(), message);
    }

    List<SerialInputData> getShowsBasedOnCriteria(
            final String criteria, final List<List<String>> filters) {
        String year = filters.get(0).get(0);
        String genre = filters.get(1).get(0);
        List<String> words = filters.get(2);
        List<String> awards = filters.get(TREI);

        List<SerialInputData> result = InputOutputUtils.getShared().getInput().getSerials();
        if (year != null) {
            result = filterByYear(result, Integer.parseInt(year));
        }
        if (genre != null) {
            result = filterByGenre(result, genre);
        }

        if (criteria.equals("favorite")) {
            result.removeIf(
                (movieInputData -> getFavoriteCountForShowTitle(movieInputData.getTitle()) == 0));
        } else if (criteria.equals("most_viewed")) {
            result.removeIf(
                (movieInputData -> getViewCountForShowTitle(movieInputData.getTitle()) == 0));
        } else if (criteria.equals("ratings")) { // il scoatem daca are rating 0 ( adica nu exista )
            result.removeIf(
                    (movieInputData -> RatingStore.getInstance().
                            getRatingForTitle(movieInputData.getTitle()) == 0));
        }

        return result;
    }

    //alegerea comparatorului-longest, favorite, most_viewed, etc
    private Comparator<SerialInputData> getComparatorForCriteria(
            final String criteria, final String sort) {
        if (criteria.equals("longest")) {
            return getComparatorForLongest(sort);
        } else if (criteria.equals("favorite")) {
            return getComparatorForFavorite(sort);
        } else if (criteria.equals("most_viewed")) {
            return getComparatorForMostViewed(sort);
        } else if (criteria.equals("ratings")) {
            return getComparatorForRating(sort);
        }

        return getComparatorForLongest(sort); // todo: remove when all implemented
    }

    private Comparator<SerialInputData> getComparatorForRating(final String sort) {
        return new Comparator<SerialInputData>() {
            @Override
            public int compare(final SerialInputData o1, final SerialInputData o2) {
                return Double.compare(
                        RatingStore.getInstance().getRatingForTitle(o1.getTitle()),
                        RatingStore.getInstance().getRatingForTitle(o2.getTitle())
                );
            }
        };
    }

    //pentru serial
    private static int getDurationForSerial(final SerialInputData serialInputData) {
        int totalDuration = 0;

        for (Season season : serialInputData.getSeasons()) {
            totalDuration += season.getDuration();
        }

        return totalDuration;
    }

    //get comparator for longest show
    private Comparator<SerialInputData> getComparatorForLongest(final String sort) {
        return new Comparator<SerialInputData>() {
            @Override
            public int compare(final SerialInputData o1, final SerialInputData o2) {
                return Integer.compare(
                    getDurationForSerial(o1),
                    getDurationForSerial(o2)
                ) * getCompareFactorForSort(sort);
            }
        };
    }

    //filtru dupa anul show-ului
    private List<SerialInputData> filterByYear(
            final List<SerialInputData> serialInputDataList, final int year) {
        List<SerialInputData> result = new LinkedList<>();
        for (SerialInputData show : serialInputDataList) {
            if (show.getYear() == year) {
                result.add(show);
            }
        }

        return result;
    }

    //filtru dupa genul show-ului
    private List<SerialInputData> filterByGenre(
            final List<SerialInputData> serialInputDataList, final String genre) {
        List<SerialInputData> result = new LinkedList<>();
        for (SerialInputData serial : serialInputDataList) {
            if (serial.getGenres().contains(genre)) {
                result.add(serial);
            }
        }
        return result;
    }

    //nr de vizualizari pe intreg show-ul, episoadele sunt nesemnificative
    private static int getViewCountForShowTitle(final String title) {
        int count = 0;
        List<UserInputData> users = InputOutputUtils.getShared().getInput().getUsers();
        for (UserInputData user : users) {
            // iau cate vizualizari are userul pentru show, daca nu exista adaug 0 ca default
            count += user.getHistory().getOrDefault(title, 0);
        }
        return count;
    }

    //favorite count pentru un show cu mai multe episoade - cati users s au uitat
    private static int getFavoriteCountForShowTitle(final String title) {
        int count = 0;
        List<UserInputData> users = InputOutputUtils.getShared().getInput().getUsers();
        for (UserInputData user : users) {
            if (user.getFavoriteMovies().contains(title)) {
                count++;
            }
        }
        return count;
    }

    //comparator pt cel mai vizualizat show
    // În cazul sezoanelor se ia întregul serial
    // ca și număr de vizualizări, nu independent pe sezoane.
    private Comparator<SerialInputData> getComparatorForMostViewed(final String sort) {
        return new Comparator<SerialInputData>() {
            //sort-descrescator sau crescator
            @Override
            public int compare(final SerialInputData o1, final SerialInputData o2) {
                String t1 = o1.getTitle();
                String t2 = o2.getTitle();

                int compareResult = Integer.compare(
                  getViewCountForShowTitle(t1), getViewCountForShowTitle(t2))
                        * getCompareFactorForSort(sort);
                if (compareResult == 0) {
                    return t1.compareTo(t2) * getCompareFactorForSort(sort);
                }

                return compareResult;
            }
        };
    }

    //comparator pt show-ul favorit in fct de nr de vizualizari
    private Comparator<SerialInputData> getComparatorForFavorite(final String sort) {
        return new Comparator<SerialInputData>() {
            @Override
            public int compare(final SerialInputData o1, final SerialInputData o2) {
                String t1 = o1.getTitle();
                String t2 = o2.getTitle();

                return Integer.compare(getFavoriteCountForShowTitle(t1),
                      getFavoriteCountForShowTitle(t2)) * getCompareFactorForSort(sort);
            }
        };
    }
}
