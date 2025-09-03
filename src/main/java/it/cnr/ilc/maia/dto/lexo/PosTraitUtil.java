package it.cnr.ilc.maia.dto.lexo;

import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author oakgen
 */
public class PosTraitUtil {

    private static final Properties uposs = new Properties();
    private static final Properties poss = new Properties();
    private static final Properties traits = new Properties();

    static {
        try (InputStream input = PosTraitUtil.class.getResourceAsStream("/pos-lexo.properties")) {
            poss.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (InputStream input = PosTraitUtil.class.getResourceAsStream("/trait-lexo.properties")) {
            traits.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (InputStream input = PosTraitUtil.class.getResourceAsStream("/upos-texto.properties")) {
            uposs.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getLabel(String value) {
        return value.substring(value.indexOf("#") + 1);
    }

    public static boolean containsPos(String key) {
        return poss.containsKey(key);
    }

    public static String getPos(String key) {
        return poss.getProperty(key);
    }

    public static String getTrait(String key) {
        return traits.getProperty(key);
    }

    public static String getUPos(String key) {
        return uposs.getProperty(key);
    }
}
