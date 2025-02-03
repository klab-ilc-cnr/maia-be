package it.cnr.ilc.maia.dto.lexo;

import java.util.Arrays;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LexicographicTree {

    private String id;
    private String referredEntity;
    private String[] type;
    private String label;
    private String[] prefix;
    private String[] suffix;
    private List<LexicographicTree> children;

    public LexicographicTree() {
    }

    public LexicographicTree(LexicographicComponent component) {
        id = component.getComponent();
        referredEntity = component.getReferredEntity();
        type = component.getType();
        label = component.getLabel();
        prefix = purgeLexicalConcepts(component.getLexicalConcepts());
        suffix = component.getPos();
    }

    private String[] purgeLexicalConcepts(String[] lexicalConcepts) {
        Arrays.sort(lexicalConcepts);
        for (int i = 0; i < lexicalConcepts.length; i++) {
            lexicalConcepts[i] = lexicalConcepts[i].substring(lexicalConcepts[i].indexOf(":") + 1);
        }
        return lexicalConcepts;
    }

}
