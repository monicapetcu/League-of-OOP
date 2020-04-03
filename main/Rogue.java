package main;

public class Rogue extends Hero {
    private int lvl = 0, l = 0;
    private final float roguemod1 = (float) 1.2, knightmod1 = (float) 0.9,
            pyromod1 = (float) 1.25, wizardmod1 = (float) 1.25,
            landmod1 = (float) 1.15, roguemod2 = (float) 0.9,
            knightmod2 = (float) 0.8, pyromod2 = (float) 1.2,
            landmod2 = (float) 1.15, wizardmod2 = (float) 1.25,
            critic = (float) 1.5, sasesute = (float) 600,
            patruzeci = 40, douasute = 200, zece = 10,
            douazeci = 20;
    private final int trei = 3, patru = 4;
    private float damage, basedamage1, basedamage2;
    public Rogue() {
    }
    /* Verifica daca este Rogue */
    public String hero() {
        return "R";
    }
    /* Returneaza nivelul curent al jucatorului */
    public int lvl() {
        return this.lvl;
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
        int hp = (int) sasesute;
        for (int i = 1; i <= this.lvl; i++) {
            hp += patruzeci;
        }
        return hp;
    }
    /* Backstab */
    public float attack1(final Hero opponent, final String land) {
        this.damage = douasute;
        int hits = 0;
        hits = this.hitsDoTR(opponent);
        for (int i = 1; i <= this.lvl; i++) {
            this.damage = douazeci;
        }
        this.basedamage1 = this.damage;
        if (opponent.hero().equals("R")) {
            this.damage = (float) (roguemod1 * this.damage);
        }
        if (opponent.hero().equals("K")) {
            this.damage = (float) (knightmod1 * this.damage);
        }
        if (opponent.hero().equals("P")) {
            this.damage = (float) (pyromod1 * this.damage);
        }
        if (opponent.hero().equals("W")) {
            this.damage = (float) (wizardmod1 * this.damage);
        }
        if (land.equals("W")) {
            this.damage = (float) (landmod1 * this.damage);
            if (hits == trei) {
                this.damage = (float) (critic * this.damage);
            }
        }
        return this.damage;
    }
    /* Paralysis */
    @Override
    public float attack2(final Hero opponent, final String land) {
        this.damage = patruzeci;
        for (int i = 1; i <= this.lvl; i++) {
            this.damage += zece;
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
        if (land.equals("W")) {
            this.damage = (float) (landmod2 * this.damage);
        }
        return this.damage;
    }
    /* Numara numarul de hit-uri dat de jucatorul curent ce joaca
      cu caracterul Rogue */
    public int hitsDoTR(final Hero opponent) {
        l++;
        if (l == patru) {
            l = 0;
        }
        return l;
    }
}