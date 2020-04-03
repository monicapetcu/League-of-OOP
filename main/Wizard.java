package main;

public class Wizard extends Hero {
    private int lvl = 0;
    private final float roguemod1 = (float) 0.8, knightmod1 = (float) 1.2,
            pyromod1 = (float) 0.9, wizardmod1 = (float) 1.05,
            landmod1 = (float) 1.10, roguemod2 = (float) 1.2,
            knightmod2 = (float) 1.4, pyromod2 = (float) 1.3,
            landmod2 = (float) 1.1, osuta = (float) 100,
            zerotrei = (float) 0.3, patrusute = 400, treizeci = 30,
            cinci = 5, doi = 2, douazeci = 20, treizeci5 = 35, saptezeci = 70;
    private float damage, basedamage1 = 0, basedamage2 = 0;
    public Wizard() {
    }
    /* Verifica daca este Wizard */
    public String hero() {
        return "W";
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
        int hp = (int) patrusute;
        for (int i = 1; i <= this.lvl; i++) {
            hp += treizeci;
        }
        return hp;
    }
    /* Drain */
    public float attack1(final Hero opponent, final String land) {
        float procent = douazeci, mini;
        for (int i = 1; i <= this.lvl; i++) {
            procent += cinci;
        }
        if (zerotrei * opponent.fullHP() < opponent.currentHP()) {
            mini = (float) (zerotrei * opponent.fullHP());
        } else {
            mini = opponent.currentHP();
            }
        this.basedamage1 = procent * mini / osuta;
        if (opponent.hero().equals("R")) {
            procent = (float) (roguemod1 * procent);
        }
        if (opponent.hero().equals("K")) {
            procent = (float) (knightmod1 * procent);
        }
        if (opponent.hero().equals("P")) {
            procent = (float) (pyromod1 * procent);
        }
        if (opponent.hero().equals("W")) {
            procent = (float) (wizardmod1 * procent);
        }
        this.damage = procent * mini / osuta;
        if (land.equals("D")) {
            this.damage = (float) (landmod1 * this.damage);
        }
        return this.damage;
    }
    /* Deflect */
    @Override
    public float attack2(final Hero opponent, final String land) {
        float procent = treizeci5;
        for (int i = 1; i <= this.lvl; i++) {
            procent += doi;
        }
        if (procent > saptezeci) {
            procent = saptezeci;
        }
        if (opponent.hero().equals("R")) {
            procent = (float) (roguemod2 * procent);
        }
        if (opponent.hero().equals("K")) {
            procent = (float) (knightmod2 * procent);
        }
        if (opponent.hero().equals("P")) {
            procent = (float) (pyromod2 * procent);
        }
        if (opponent.hero().equals("W")) {
            procent = 0;
        }
        this.damage = procent * (opponent.basedamage1() + opponent.basedamage2()) / osuta;
        if (land.equals("D")) {
            this.damage = (float) (landmod2 * this.damage);
        }
        return this.damage;
    }
}
