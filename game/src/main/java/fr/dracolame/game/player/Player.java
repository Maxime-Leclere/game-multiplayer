package fr.dracolame.game.player;

import fr.dracolame.game.player.element.Element;

import java.io.Serializable;

public abstract
class Player implements Serializable {
    private String name;
    private double life = 100;
    private double attack = 10;
    private Element element;

    public void reset() {
        life = 100;
        attack = 10;
    }

    public String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    public double getLife() {
        return life;
    }

    private void setLife(double life) {
        this.life = life;
    }

    private double getAttack() {
        return attack;
    }

    public void setAttack(double attack) {
        this.attack = attack;
    }

    private Element getElement() {
        return element;
    }

    protected void setElement(Element element) {
        this.element = element;
    }

    /*
     si val = 1 l'attaque a la faiblesse de l'adverssaire et augmente l'attaque de 20% => 12
     si val = -1 l'attaque a l'avantage de l'adverssaire et diminue l'attaque de 20% => 8
     sinon on prend l'attaque de base(10)
     */
    private double calculAttack(int val) {
        if (val == 1)return getAttack()*(1.0 + getElement().getPourcentage());
        else if (val == -1)return getAttack()*(1.0 - getElement().getPourcentage());
        return getAttack();
    }

    public double getDamageFor(Player player){
        if (player.getElement().equals(this.getElement().getFaiblesse()))return calculAttack(-1);
        else if (player.getElement().equals(this.getElement().getAvantage())) return calculAttack(1);
        else return calculAttack(0);
    }

    public void strikeTo(Player player) {
       player.setLife(player.getLife() - this.getDamageFor(player));
    }

    @Override
    public String toString() {
        return "Nom : "+ getName() + "\n" +
                "Vie : " + getLife() + "\n" +
                "Attaque : "+ getAttack() + "\n" +
                "Element : "+ getElement().getName() + "\n";
    }
}
