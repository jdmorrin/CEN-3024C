/*
If I understand this class correctly
    This class takes two strings, the `start` and the `end` of a section
    The Consumer determines what action will be taken with these two strings
    We have to override the `accept()` method from Consumer interface
*/
package cen_mod2_003;

import java.util.function.Consumer;

/**
 *
 * @author Admin
 */
public class SectionAction {
    final String start;
    final String end;
    final Consumer<String> action;
    
    public SectionAction(String start, String end, Consumer <String> action){
        this.start = start;
        this.end = end;
        this.action = action;
        
    }
}
