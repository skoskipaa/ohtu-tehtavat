package statistics.matcher;

import statistics.Player;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HasFewerThan implements Matcher {
    
    private int value;
    private String fieldName;

    public HasFewerThan(int value, String category) {
        this.value = value;
        this.fieldName = "get"+Character.toUpperCase(category.charAt(0))+category.substring(1, category.length());
    }

    
    
    @Override
    public boolean matches(Player p) {
        try {
            Method method = p.getClass().getMethod(fieldName);
            int returnValue = (int) method.invoke(p);
            return returnValue < this.value;
            
        } catch (Exception ex) {
            System.out.println(ex);
            throw new IllegalStateException("Player does not have field "+fieldName.substring(3, fieldName.length()).toLowerCase());

        }
        
    }
    
}
