public class Main {
    public static void main(String[] args) {
        ClassWow[] classArray=new ClassWow[9];
        Druid druid=new Druid();
        Hunter hunter=new Hunter("Spot");
        Mage mage=new Mage();
        Paladin paladin=new Paladin();
        Shaman shaman=new Shaman("Fire");
        Priest priest=new Priest(false);
        Rogue rogue=new Rogue();
        Warlock warlock=new Warlock();
        Warrior warrior=new Warrior("ThunderFury");
        classArray[0]=druid;
        classArray[0].print();
        druid.transformIntoBeast("Bear");
        classArray[1]=hunter;
        classArray[1].print();
        classArray[2]=mage;
        classArray[2].print();
        mage.conjureWaterAndFood(10);
        classArray[3]=shaman;
        classArray[3].print();
        classArray[4]=priest;
        classArray[4].print();
        priest.activateShadowForm();
        classArray[4].print();
        classArray[5]=rogue;
        classArray[5].print();
        rogue.getIntoStealth();
        classArray[6]=warlock;
        classArray[6].print();
        warlock.summonDemon("DoomGard");
        classArray[7]=paladin;
        classArray[7].print();
        paladin.buffParty("Blessing of kings");
        classArray[8]=warrior;
        classArray[8].print();
        int[10] array;
    }
}
