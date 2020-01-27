package fr.dracolame.game.player.element;

public class Element {
    private String name;
    private Element faiblesse;
    private Element avantage;
    private final double pourcentage = 0.2;

    public Element (String name, Element faiblesse, Element avantage) {
        setName(name);
        setFaiblesse(faiblesse);
        setAvantage(avantage);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Element getFaiblesse() {
        return faiblesse;
    }

    public void setFaiblesse(Element faiblesse) {
        this.faiblesse = faiblesse;
    }

    public Element getAvantage() {
        return avantage;
    }

    public void setAvantage(Element avantage) {
        this.avantage = avantage;
    }

    public double getPourcentage() {
        return pourcentage;
    }

    @Override
    public String toString() {
        return getClass().getName();
    }
}
