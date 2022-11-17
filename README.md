Homework

# About my code

I thought about this application like it's all a general router who get the action, is received by an action
router and after that depending on the kind of action and operations on that action that needs to be done,
it's sent to them.


                              - QueryProcessor -  specific operations...
GeneralRouter -> ActionRouter - CommandProcessor - specific operations...
                              - RecommendationProcessor - specific operations...

* ActionRouter - common Interface of Action.

* GeneralRouter - class used to implement a RouterMap who contains all the type of actions that can be done : Query,
Command, Recommendation, and decide which one is get from the imput.

* QueryActionRouter, * CommandActionRouter, * RecommendationActionRouter - process the actions that needs to be done
on the basic kind of action(query, command, recommendation) e.g for recommendation- the action can be : best_unseen(movie),
standard(first_unseen_movie), etc.

After I decide the basic kind of Action that needs to be done from the RouterMap, I separated the subAction
who is needed to be done on the json input file, so I decided to make:

* Processor Package - who contains the command,query,recommendation packages, and

* ActionProcessor - abstract common class to process the action

* StorePackage - * RatingStore - I made the class a Singleton because I want to be a global store for all the action
                               - which needs to use the rating. For the rating process I used two maps, one for
                               - movies and one for serials-who needs to be rated for every season.
                 * RatingException - I made that in order to be thrown if an user already gave a rating to a movie
                                   - and this action can't happen twice

* Command(package) - * CommandProcessor - abstract common class who has a function that gets a user and only one
                   * FavoriteCommandProcessor      -
                   * RatingCommandProcessor        - => Classes who contains the methods who does a certain operation
                                                   - in the application - such as what movie/serial was rated by who,
                                                   - how many views has a serial/movie
                   * ViewCommandProcessor          -

* Query(package) - * QueryProcessor - abstract common class - it's common because the type of sort(asc, desc) will
                                  - will need to be done for all operations over a query, and also the sort with
                                  - the limit.
                 * ActorsQueryProcessor -
                 * MoviesQueryProcessor - => Classes who contains the methods who are done over a query and for a
                                        - specific category such as actors - filtered after awards, the words finded
                                        - in their description, medium rating,
                                        - users,  and for movies, shows(serials) -
                                        - who needed to be filtered by year, genre, duration, etc.
                 * ShowQueryProcessor   -
                 * UsersQueryProcessor  -

* Recommendation(package) - * RecommendationProcessor - abstract common class who has the method of returning a
                                                      - certain user
                           * BestUnseenRecommandationProcessor -
                           * StandardRecommandationProcessor   -
                           * SearchRecommendationProcessor     - => Classes who contains the methods who needs
                                                               - to be done for standard & premium users.
                           * FavoriteRecommendationProcessor   -
                           * PopularRecommendationProcessor    -

§ I used stream two times when I needed to sort Maps after cryteria such as rating, in order to avoid long an
repetitive code and also for the readability.

§ I made a Method in InputOutputUtils.java who write a message in a jsonObject and then add it to the jsonArray

§ In order to have a clear and structured README, I decided to keep the structure explanation for this file,
and leave Javadoc or simple comments in my code so anyone who will read it to understand what I did there.


