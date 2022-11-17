package router;

import fileio.ActionInputData;
import processor.recommendation.BestUnseenRecommandationProcessor;
import processor.recommendation.PopularRecommendationProcessor;
import processor.recommendation.SearchRecommendationProcessor;
import processor.recommendation.StandardRecommandationProcessor;
import processor.recommendation.FavoriteRecommendationProcessor;


public final class RecommendationActionRouter implements ActionRouter {

    @Override
    public void routeAction(final ActionInputData actionInputData) {
        if (actionInputData.getType().equals("standard")) {
            StandardRecommandationProcessor recommendationProcessor =
                new StandardRecommandationProcessor();
            recommendationProcessor.processAction(actionInputData);
        } else if (actionInputData.getType().equals("best_unseen")) {
            BestUnseenRecommandationProcessor bestUnseenRecommendationProcessor =
                    new BestUnseenRecommandationProcessor();
            bestUnseenRecommendationProcessor.processAction(actionInputData);
        } else if (actionInputData.getType().equals("popular")) {
            PopularRecommendationProcessor popularRecommendationProcessor
                    = new PopularRecommendationProcessor();
            popularRecommendationProcessor.processAction(actionInputData);
        } else if (actionInputData.getType().equals("search")) {
            SearchRecommendationProcessor searchRecommendationProcessor
                    = new SearchRecommendationProcessor();
            searchRecommendationProcessor.processAction(actionInputData);
        } else {
            FavoriteRecommendationProcessor favoriteRecommendationProcessor
                    = new FavoriteRecommendationProcessor();
            favoriteRecommendationProcessor.processAction(actionInputData);
        }
    }
}
