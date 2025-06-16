package Models;

public class Calendario {
    static int[][] data = new int[12][];
    public Calendario() {

        this.data[0] = new int[31];
        this.data[1] = new int[28];
        this.data[2] = new int[31];
        this.data[3] = new int[30];
        this.data[4] = new int[31];
        this.data[5] = new int[30];
        this.data[6] = new int[31];
        this.data[7] = new int[31];
        this.data[8] = new int[30];
        this.data[9] = new int[31];
        this.data[10] = new int[30];
        this.data[11] = new int[31];


    }
    public static int mesParaNumero(String mes) {
        mes = mes.toLowerCase();

        switch (mes) {
            case "janeiro": return 1;
            case "fevereiro": return 2;
            case "março":
            case "marco": return 3;
            case "abril": return 4;
            case "maio": return 5;
            case "junho": return 6;
            case "julho": return 7;
            case "agosto": return 8;
            case "setembro": return 9;
            case "outubro": return 10;
            case "novembro": return 11;
            case "dezembro": return 12;
            default: return -1;
        }
    }
    public static int getMonthLength(int mes){
        return data[mes-1].length;
    }
}
