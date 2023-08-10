package sources;

import java.util.ResourceBundle;

/**
 * Represents the settings interface for a game.
 */

public interface Settings {

     /**
      * Displays the game statistics.
      */
     void getGameStat();

     /**
      * Displays all the game settings.
      */

     void getAllSettings();

     /**
      * Retrieves the resource bundle for localization.
      *
      * @return the resource bundle
      */

     ResourceBundle getBundle();
}
