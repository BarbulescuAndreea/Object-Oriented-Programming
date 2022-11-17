package processor.query;

import fileio.ActionInputData;
import inputdata.InputOutputUtils;
import store.RatingStore;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.LinkedList;
import java.util.ArrayList;


public final class UsersQueryProcessor extends QueryProcessor {
    @Override
    public void processAction(final ActionInputData actionInputData) {
        Map<String, Map<String, Double>> movieRatingMap
                = RatingStore.getInstance().getMovieRatingsMap();
        Map<String, Map<Integer, Map<String, Double>>>  serialRatingMap
                = RatingStore.getInstance().getSerialRatingsMap();

        // users -> number of ratings
        Map<String, Integer> usersRatingsMap = new HashMap<>();
        for (Map<String, Double> ratingMap : movieRatingMap.values()) {
            for (String username : ratingMap.keySet()) {
                usersRatingsMap.put(username, usersRatingsMap.getOrDefault(username, 0) + 1);
            }
        }

        for (Map<Integer, Map<String, Double>> seasonMap : serialRatingMap.values()) {
            for (Map<String, Double> ratingMap : seasonMap.values()) {
                for (String username : ratingMap.keySet()) {
                    usersRatingsMap.put(username, usersRatingsMap.getOrDefault(username, 0) + 1);
                }
            }
        }

        // il luam pe cel cu rating cel mai mare
        List<String> usersList = new ArrayList<>(usersRatingsMap.keySet());

        int orderFactor = actionInputData.getSortType().equals("asc") ? 1 : -1;
        usersList.sort(new Comparator<String>() {
            @Override
            public int compare(final String o1, final String o2) {
                int compareResult = Integer.compare(usersRatingsMap.get(o1),
                        usersRatingsMap.get(o2)) * orderFactor;
                if (compareResult == 0) {
                    return o1.compareTo(o2) * orderFactor;
                }

                return compareResult;
            }
        });


        List<String> resultList = new LinkedList<>();
        for (int i = 0; i < Math.min(usersList.size(), actionInputData.getNumber()); i++) {
            resultList.add(usersList.get(i));
        }

        String message = "Query result: [" + String.join(", ", resultList) + "]";

        InputOutputUtils.getShared().writeFile(actionInputData.getActionId(), message);

    }
}
