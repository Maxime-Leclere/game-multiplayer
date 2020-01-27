package fr.dracolame.game.ia;

import fr.dracolame.game.ia.algoreacttocontext.AlgoReactToContext;
import fr.dracolame.game.player.element.Element;

public class IaBold extends Ia {

    public IaBold(Element element) {
        setName(getClass().getSimpleName());
        setElement(element);
    }

    @Override
    public void reactToContext(AlgoReactToContext algoReactToContext) {
        algoReactToContext.reactToContext(this);
    }
}
