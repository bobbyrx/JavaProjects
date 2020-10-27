public class Main{
    public static void main(String[] args) {
        Animal animal=new Animal("Dog",1,1,5,5);
        Dog dog=new Dog(animal,2,4,1,10,"Black");
        Dog dog1=dog.copy();
        dog1.setCoat("Grey");
        dog.printAnimal();
        dog1.printAnimal();
        //Dog dog1=new Dog();
        //dog1.printDog();
        //Dog dog2=new Dog(1,3,0,3,"Grey");
        //dog2.printDog();
        //dog.eat();
        //dog.move();
    }
}

