package main;

public class Pyromancer extends Hero {
   private int lvl = 0;
   private final float roguemod1 = (float) 0.8, knightmod1 = (float) 1.2,
           pyromod1 = (float) 0.9, wizardmod1 = (float) 1.05,
           landmod1 = (float) 1.25, roguemod2 = (float) 0.8,
           knightmod2 = (float) 1.2, pyromod2 = (float) 0.9,
           landmod2 = (float) 1.25, wizardmod2 = (float) 1.05,
           cincisute = (float) 500, cincizeci = (float) 50,
           treisute50 = (float) 350, osuta50 = (float) 150,
           douazeci = (float) 20;
   private float damage, basedamage1, basedamage2;
   public Pyromancer() {
   }
   /* Verifica daca este Pyromancer */
   public String hero() {
      return "P";
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
      int hp = (int) cincisute;
      for (int i = 1; i <= this.lvl; i++) {
         hp += cincizeci;
      }
      return hp;
   }
   /* Fireblast */
   @Override
   public float attack1(final Hero opponent, final String land) {
      this.damage = treisute50;
      for (int i = 1; i <= this.lvl; i++) {
         this.damage += cincizeci;
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
      if (land.equals("V")) {
         this.damage = (float) (landmod1 * this.damage);
      }
      return this.damage;
   }
   /* Ignite */
   @Override
   public float attack2(final Hero opponent, final String land) {
      this.damage = osuta50;
      for (int i = 1; i <= this.lvl; i++) {
         this.damage += douazeci;
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
      if (land.equals("V")) {
         this.damage = (float) (landmod2 * this.damage);
      }
      return this.damage;
   }
}
