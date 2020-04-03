package main;

public class Duel {
    private Hero hero1 = new Hero();
    private Hero hero2 = new Hero();
    private String land;
    private final float roguemod1 = (float) 0.8, knightmod1 = (float) 1.2,
            pyromod1 = (float) 0.9, wizardmod1 = (float) 1.05,
            landmod1 = (float) 1.25, roguemod2 = (float) 0.8,
            knightmod2 = (float) 1.2, pyromod2 = (float) 0.9,
            landmod2 = (float) 1.25, wizardmod2 = (float) 1.05,
            osuta = (float) 100, douasute = (float) 200,
            patruzeci = (float) 40, douasute50 = (float) 250,
            cincizeci = (float) 50, treizeci = (float) 30,
            douazeci = (float) 20;
    private final int mort = 1;
    private final int trei = 3;
    public Duel(final Hero hero1, final Hero hero2, final String land) {
        this.hero1 = hero1;
        this.hero2 = hero2;
        this.land = land;
    }
    /* Calculez HP-ul primului jucator la inceputul rundei
       scazand damage-ul primit in continuare in cazul in care
       s-a luptat cu un Pyromancer */
    public float overtime1() {
        float hp1, dam = cincizeci;
        int i, v;
        hp1 = hero1.currentHP();
        i = hero1.pDoT(hero2);
        if (i == 1) {
            if (this.land.equals("V")) {
                v = 1;
            } else {
                v = 0;
            }
            for (int j = 1; j <= hero2.lvl(); j++) {
                dam += treizeci;
            }
            if (hero1.hero().equals("R")) {
                dam = (float) (roguemod1 * dam);
            }
            if (hero1.hero().equals("K")) {
                dam = (float) (knightmod1 * dam);
            }
            if (hero1.hero().equals("P")) {
                dam = (float) (pyromod1 * dam);
            }
            if (hero1.hero().equals("W")) {
                dam = (float) (wizardmod1 * dam);
            }
            if (v == 1) {
                dam = (float) (landmod1 * dam);
            }
            hero1.damDoT(dam);
        }
        if (i == 2 || i == trei) {
            hp1 -= hero1.returndamDoT();
        }
        return hp1;
    }
    /* Calculez HP-ul celui de-al doilea jucator la inceputul rundei
       scazand damage-ul primit in continuare in cazul in care
       s-a luptat cu un Pyromancer */
    public float overtime2() {
        float hp2, dam = cincizeci;
        int i, v;
        hp2 = hero2.currentHP();
        i = hero2.pDoT(hero1);
        if (i == 1) {
            if (this.land.equals("V")) {
                v = 1;
            } else {
                v = 0;
            }
            for (int j = 1; j <= hero1.lvl(); j++) {
                dam += treizeci;
            }
            if (hero2.hero().equals("R")) {
                dam = (float) (roguemod2 * dam);
            }
            if (hero2.hero().equals("K")) {
                dam = (float) (knightmod2 * dam);
            }
            if (hero2.hero().equals("P")) {
                dam = (float) (pyromod2 * dam);
            }
            if (hero2.hero().equals("W")) {
                dam = (float) (wizardmod2 * dam);
            }
            if (v == 1) {
                dam = (float) (landmod2 * dam);
            }
            hero2.damDoT(dam);
            }
        if (i == 2 || i == trei) {
            hp2 -= hero2.returndamDoT();
        }
        return hp2;
    }
    /* Calculez damage-ul dat de primul jucator */
    public int hero1() {
        int damage = 0;
        if (hero1.hero().equals("K")) {
            int limit = (int) douazeci;
            for (int i = 1; i <= hero1.lvl(); i++) {
                limit++;
            }
            if (limit > patruzeci) {
                limit = (int) patruzeci;
            }
            if (((limit / osuta) * (hero2.fullHP())) >= hero2.currentHP()) {
                damage = (int) (hero2.currentHP());
            } else {
                damage = Math.round(hero1.attack1(hero2, land))
                   + Math.round(hero1.attack2(hero2, land));
            }
        } else {
            damage = Math.round(hero1.attack1(hero2, land))
                   + Math.round(hero1.attack2(hero2, land));
        }
        return damage;
    }
    /* Calculez damage-ul dat de al doilea jucator */
    public int hero2() {
        int damage = 0;
        if (hero2.hero().equals("K")) {
            int limit = (int) douazeci;
            for (int i = 1; i <= hero2.lvl(); i++) {
                limit++;
            }
            if (limit > patruzeci) {
                limit = (int) patruzeci;
            }
            if (((limit / osuta) * (hero1.fullHP())) >= hero1.currentHP()) {
                damage = (int) (hero1.currentHP());
            } else {
                damage = Math.round(hero2.attack1(hero1, land))
                        + Math.round(hero2.attack2(hero1, land));
            }
        } else {
            damage = Math.round(hero2.attack1(hero1, land))
                    + Math.round(hero2.attack2(hero1, land));
        }
        return damage;
    }
    /* Verific daca lupta se desfasoara (daca ambii jucatori sunt
       in viata pentru a primi atac din partea adversarului curent) */
    public int battle() {
        int ok = 1;
        float hp1, hp2;
        hp1 = overtime1();
        hp2 = overtime2();
        if (hp1 <= 0) {
            hero1.dead(mort);
            ok = 0;
        }
        if (hp2 <= 0) {
            hero2.dead(mort);
            ok = 0;
        }
        return ok;
    }
    /* In cazul in care unul dintre adversari dau o vraja
       cu efect overtime, daca exista deja o vraja cu efect
       overtime asupra celui atacat, aceea se anuleaza */
    public void reset() {
        if (hero1.hero().equals("P")) {
            hero2.resetDoTR();
        }
        if (hero1.hero().equals("R")) {
            hero2.resetDoTP();
        }
        if (hero1.hero().equals("K")) {
            hero2.resetDoTR();
            hero2.resetDoTP();
        }
        if (hero2.hero().equals("P")) {
            hero1.resetDoTR();
        }
        if (hero2.hero().equals("R")) {
            hero1.resetDoTP();
        }
        if (hero2.hero().equals("K")) {
            hero1.resetDoTR();
            hero1.resetDoTP();
        }
    }
    /* Calculez overtime-ul */
    public void overtime() {
        int hp1 = (int) overtime1();
        int hp2 = (int) overtime2();
        hero1.currentHPupdate(hp1);
        hero2.currentHPupdate(hp2);
    }
    /* Lupta proprie dintre jucatori: */
    public void herosfight() {
        int hp1, hp2, max, xplvlup, atac1, rog1, atac2, rog2;
        hp1 = (int) overtime1();
        hp2 = (int) overtime2();
        if (battle() == 1) {
            hp1 -= hero2();
            hp2 -= hero1();
            rog1 = hero2.rDoT(hero1, land);
            atac1 = hero2.ataca();
            if (atac1 == 1) {
            	hp2 -= hero2.damageparalysis();
            }
            rog2 = hero1.rDoT(hero2, land);
            atac2 = hero1.ataca();
            if (atac2 == 1) {
            	hp1 -= hero1.damageparalysis();
            }
            /* Se face update la viata jucatorilor la finalul meciului: */
            hero1.currentHPupdate(hp1);
            hero2.currentHPupdate(hp2);
            if (hp1 <= 0) {
                hero1.dead(1);
            }
            if (hp2 <= 0) {
                hero2.dead(1);
            }
            /* Daca unul din ei moare, celalalt primeste experienta: */
            if (hero1.deadsts() == 1 && hero2.deadsts() == 0) {
                max = (int) (douasute - (hero2.lvl() - hero1.lvl()) * patruzeci);
                if (max > 0) {
                    hero2.xp(max);
                }
            } else if (hero2.deadsts() == 1 && hero1.deadsts() == 0) {
                max = (int) (douasute - (hero1.lvl() - hero2.lvl()) * patruzeci);
                if (max > 0) {
                    hero1.xp(max);
                }
            }
            // Se verifica daca vreunul din ei trece la urmatorul nivel:
            if (hero1.deadsts() == 0) {
                xplvlup = (int) (douasute50 + hero1.lvl() * cincizeci);
                if (hero1.xpsts() == xplvlup) {
                    hero1.lvlup();
                }
            }
            if (hero2.deadsts() == 0) {
                xplvlup = (int) (douasute50 + hero2.lvl() * cincizeci);
                if (hero2.xpsts() == xplvlup) {
                    hero2.lvlup();
                }
            }
        }
        reset();
    }
}