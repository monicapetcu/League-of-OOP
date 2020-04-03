package main;

public class Hero {
    private float currentHP, xp, dam, damageparalysis = 0;
    private int lvl = 0, dead = 0, k = 0, i = 0, m, ok, x, y, lupta, ataca = 0;
    private final int trei = 3, patru = 4, sapte = 7;
    public Hero() {
    }
    /* Il folosesc pentru a verifica tipul jucatorului
      (Pyromancer, Knight, Rogue sau Wizard) */
    public String hero() {
        return "H";
    }
    /* Coordonatele la care se afla jucatorul */
    public final void location(final int x, final int y) {
        this.x = x;
        this.y = y;
    }
    /* Linia pe care se afla jucatorul */
    public int getx() {
        return x;
    }
    /* Coloana pe care se afla jucatorul */
    public int gety() {
        return y;
    }
    /* Returneaza nivelul curent al jucatorului */
    public int lvl() {
        return this.lvl;
    }
    /* Returneaza damage-ul de baza al primului atac */
    public float basedamage1() {
        return 0;
    }
    /* Returneaza damage-ul de baza al celui de-al 2-lea atac */
    public float basedamage2() {
        return 0;
    }
    /* Returneaza HP-ul full al jucatorului la nivelul curent */
    public int fullHP() {
        return 0;
    }
    /* Returneaza damage-ul primului atac cu modificatori de rasa si teren */
    public float attack1(final Hero opponent, final String land) {
        return 0;
    }
    /* Returneaza damage-ul celui de-al 2-lea atac cu modificatori de rasa si
       teren */
    public float attack2(final Hero opponent, final String land) {
        return 0;
    }
    /* Numara cate lovituri a executat Rogue */
    public int hitsDoTR(final Hero opponent) {
        return 0;
    }
    /* Numar rundele de dupa un atac al Pyromancer-ului si daca
       se ajunge la runda a 3-a de dupa Pyromancer, se reseteaza
       variabila la 0 pana gaseste un nou Pyromancer */
    public int pDoT(final Hero opponent) {
        if (opponent.hero().equals("P")) {
            i = 1;
        } else if (i == 1 || i == 2) {
                    i++;
                } else if (i == trei) {
                        i = 0;
                        }
        if (opponent.deadsts() == 1 && (i == 1 || i == 2)) {
            i++;
        } else if (i == trei) {
                i = 0;
                }
        return i;
    }
    /* Numar rundele de dupa un atac al Knight-ului si daca
       se ajunge la runda a 2-a de dupa Knight, se reseteaza
       variabila la 0 pana gaseste un nou Knight */
    public int kDoT(final Hero opponent) {
        if (opponent.hero().equals("K")) {
            k = 1;
        } else if (k == 1) {
                    k++;
                } else if (k == 2) {
                        k = 0;
                }
        if (opponent.deadsts() == 1 && i == 1) {
            k++;
        } else if (k == 2) {
            k = 0;
        }
        return k;
    }
    /* Numar rundele de dupa un atac al Rogue-ului si daca
       se ajunge la runda a 4-a de dupa Rogue, respectiv a
       7-a in cazul in care lupta se desfasoara pe teren WOODS,
       se reseteaza variabila la 0 pana gaseste un nou Rogue */
    public int rDoT(final Hero opponent, final String land) {
        if (opponent.hero().equals("R")) {
            m = 1;
            damageparalysis = opponent.attack2(this, land);
            lupta++;
        }
        if (m == 1) {
            if (land.equals("W")) {
                ok = 1;
            } else {
               ok = 0;
            }
        }
        if (m == 1) {
        	if(lupta > 1) {
        		ataca = 1;
        	} else {
        		lupta = 1;
        		ataca = 0;
        	}
            if (ok == 1) {
                if (m >= 1 && m < sapte) {
                    m++;
                } else if (m == sapte) {
                    m = 0;
                }
            } else {
                if (m >= 1 && m < patru) {
                    m++;
                } else if (m == patru) {
                    m = 0;
                }
            }
        }
        return m;
    }
    /* Returneaza numarul rundei in care jucatorul este
       sub influenta Paralysis dat de Rogue sau 0 daca
       nu se abla sub influenta acestei vraji: */
    public int stsDoTR() {
        return this.m;
    }
    /* Returneaza numarul rundei in care jucatorul este
       sub influenta Slam dat de Knight sau 0 daca
       nu se abla sub influenta acestei vraji: */
    public int stsDoTK() {
        return this.k;
    }
    /* Anuleaza efectul Ignite dat de Pyromancer: */
    public void resetDoTP() {
        this.i = 0;
    }
    /* Anuleaza efectul Paralysis dat de Rogue: */
    public void resetDoTR() {
        this.m = 0;
    }
    /* Retine damage-ul de DoT */
    public void damDoT(final float dam) {
        this.dam = dam;
    }
    /* Returneaza damage-ul de DoT */
    public float returndamDoT() {
        return this.dam;
    }
    /* Daca un jucator moare, dead devine 1.
       Daca este in viata, dead ramane 0: */
    public void dead(final int dead) {
        this.dead = dead;
    }
    /* Verific daca un jucator este mort sau in viata: */
    public int deadsts() {
        return this.dead;
    }
    /* Se actualizeaza experienta jucatorului */
    public void xp(final float max) {
        xp = xp + max;
    }
    /* Returneaza cata experienta are jucatorul */
    public float xpsts() {
        return this.xp;
    }
    /* Retine HP-ul curent al jucatorului */
    public void currentHPupdate(final float hp) {
        currentHP = hp;
    }
    /* Returneaza HP-ul curent al jucatorului */
    public float currentHP() {
        return currentHP;
    }
    /* Cand trece la urmatorul nivel, HP-ul devine plin */
    public void lvlup() {
        this.lvl++;
        currentHP = fullHP();
    }
    
    public float damageparalysis() {
    	return damageparalysis;
    }
    
    public int ataca() {
    	return ataca;
    }
}