package inputdata;

import fileio.Input;
import fileio.Writer;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
//input-output-utils
public final class InputOutputUtils {
  private static InputOutputUtils shared;

  private final Input input;
  private final Writer writer;
  private final JSONArray jsonArray;

  public static InputOutputUtils getShared() {
    return shared;
  }

  public static void setShared(final InputOutputUtils inputOutputUtils) {
    InputOutputUtils.shared = inputOutputUtils;
  }

  public InputOutputUtils(final Input input, final Writer writer, final JSONArray jsonArray) {
    this.input = input;
    this.writer = writer;
    this.jsonArray = jsonArray;
  }

  public Input getInput() {
    return input;
  }

  public Writer getWriter() {
    return writer;
  }

  /**
   * Functia face scrierea unui mesaj intr-un obiect JSON
   * campul field este gol - nu are nici o utilitate
   * @param id
   * @param message
   */
  public void writeFile(final int id, final String message) {
    try {
      JSONObject jsonObject = writer.writeFile(id, "", message);
      jsonArray.add(jsonObject);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
