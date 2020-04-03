package main;

public class Knight extends Hero {
    private int lvl = 0;
    private final float roguemod1 = (float) 1.15, pyromod1 = (float) 1.1,
            wizardmod1 = (float) 0.8, landmod1 = (float) 1.15,
            roguemod2 = (float) 0.8, knightmod2 = (float) 1.2,
            pyromod2 = (float) 0.9, landmod2 = (float) 1.15,
            wizardmod2 = (float) 1.05, patruzeci = (float) 40,
            osuta = (float) 100, treizeci = (float) 30, douasute = (float) 200,
            nouasute = (float) 900, optzeci = (float) 80;
    private float damage, basedamage1, basedamage2;
    public Knight() {
    }
    /* Verifica daca este Knight */
    public String hero() {
        return "K";
    }
    /* Returneaza damage-ul de baza al primului atac */
    public float basedamage1() {
        return this.basedamage1;
    }
    /* Returneaza damage-ul de baza al celui de-al 2-lea atac */
    public float basedamage2() {
        return this.basedamage2;
    }
    /* Returneaza HP-ul full al jucatorului la nivelul curent */
    @Override
    public int fullHP() {
        int hp = (int) nouasute;
        for (int i = 1; i <= this.lvl; i++) {
            hp += optzeci;
        }
        return hp;
    }
    /* Execute */
    public float attack1(final Hero opponent, final String land) {
        this.damage = douasute;
        for (int i = 1; i <= this.lvl; i++) {
            this.damage += treizeci;
        }
        this.basedamage1 = this.damage;
        if (opponent.hero().equals("R")) {
            this.damage = (float) (roguemod1 * this.damage);
        }
        if (opponent.hero().equals("K")) {
            this.damage = (float) (this.damage);
        }
        if (opponent.hero().equals("P")) {
            this.damage = (float) (pyromod1 * this.damage);
        }
        if (opponent.hero().equals("W")) {
            this.damage = (float) (wizardmod1 * this.damage);
        }
        if (land.equals("L")) {
            this.damage = (float) (landmod1 * this.damage);
        }
        return this.damage;
    }
    /* Slam */
    @Override
    public float attack2(final Hero opponent, final String land) {
        this.damage = osuta;
        for (int i = 1; i <= this.lvl; i++) {
            this.damage += patruzeci;
        }
        this.basedamage2 = this.damage;
        if (opponent.hero().equals("R")) {
            this.damage = (float) (roguemod2 * this.damage);
        }
        if (opponent.hero().equals("K")) {
            this.damage = (float) (knightmod2 * this.damage);
        }
        if (opponent.hero().equals("P")) {
            this.damage = (float) (pyromod2 * this.damage);
        }
        if (opponent.hero().equals("W")) {
            this.damage = (float) (wizardmod2 * this.damage);
        }
        if (land.equals("L")) {
            this.damage = (float) (landmod2 * this.damage);
        }
        return damage;
    }
}
