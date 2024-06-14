package it.cnr.ilc.maia;

/**
 *
 * @author oakgen
 */
public class Test {
 
    public static void main(String[] args) {
        String text = "{\"pino\":\"gino\"}";
        text = text.replaceAll("\"", "\\\\\"").replaceAll("\\s", " ");
        System.out.println(text);
    }
}
