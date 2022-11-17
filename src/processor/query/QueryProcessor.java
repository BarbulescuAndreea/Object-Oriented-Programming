/**query processor
 *
 */
package processor.query;

import processor.ActionProcessor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public abstract class QueryProcessor extends ActionProcessor {
  protected final <T> List<T> sortAndLimitResults(
          final List<T> inputList, final Comparator<T> comparator, final int limit) {
    List<T> newInputList = new ArrayList<>(inputList);
    newInputList.sort(comparator);
    if (limit > newInputList.size() || limit == 0) {
      return newInputList;
    }

    List<T> resultsList = new LinkedList<>();
    for (int i = 0; i < limit; ++i) {
      resultsList.add(newInputList.get(i));
    }

    return resultsList;
  }

  /**
   * metoda getCompareFactorForSort e folosita pentru a sti daca trebuie sortat rezultatul
   * crescator (asc) sau descrescator(desc)
   * @param sort
   * @return
   */
  protected final int getCompareFactorForSort(final String sort) {
    return sort.equals("asc") ? 1 : -1;
  }
}
