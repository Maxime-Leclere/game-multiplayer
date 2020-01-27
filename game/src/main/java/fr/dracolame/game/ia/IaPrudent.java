package fr.dracolame.game.ia;

import fr.dracolame.game.ia.algoreacttocontext.AlgoReactToContext;
import fr.dracolame.game.player.element.Element;

public class IaPrudent extends Ia {

    public IaPrudent(Element element) {
        setName(getClass().getName());
        setElement(element);
    }

    @Override
    public void reactToContext(AlgoReactToContext algoReactToContext) {
        algoReactToContext.reactToContext(this);
    }
}
