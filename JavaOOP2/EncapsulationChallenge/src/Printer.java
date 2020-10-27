public class Printer {
    private int tonerLevel;
    private int numberOfPagesPrinted;
    private boolean isDuplexPrinter;

    public Printer(int tonerLevel, boolean isDuplexPrinter) {
        if(tonerLevel>=0&&tonerLevel<=0)this.tonerLevel = tonerLevel;
        else this.tonerLevel=-1;
        this.numberOfPagesPrinted = 0;
        this.isDuplexPrinter = isDuplexPrinter;
    }
    public int addToner(int tonerAmount){
        if(tonerAmount>0&&tonerAmount<=100) {
            if(this.tonerLevel+tonerAmount>100)return -1;
            this.tonerLevel+=tonerAmount;
            return this.tonerLevel;
        }
        else return -1;
    }

    public int printPages(int pages) {
        int pagesToPrint=pages;
        if(this.isDuplexPrinter){
            pagesToPrint=pages/2+pages%2;
            System.out.println("Printing in duplex mode");
        }
        this.numberOfPagesPrinted+=pagesToPrint;
        return pagesToPrint;
    }

    public int getNumberOfPagesPrinted() {
        return numberOfPagesPrinted;
    }
}
