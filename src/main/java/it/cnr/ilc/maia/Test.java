package it.cnr.ilc.maia;

/**
 *
 * @author oakgen
 */
public class Test {
 
    public static void main(String[] args) {
        String text = "Ciao (mammà) come staì 8 l'orso [bello] é {carino} ";
        text = text.replaceAll("[^a-zA-Z0-9'àèéìòù]", " ");
        System.out.println(text);
    }
}
