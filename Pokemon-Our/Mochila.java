public class Mochila {
    private int Bayas;
    private int Pociones;
    private int Medallas;
    Mochila(){
        this.Bayas=0;
        this.Pociones=0;
        this.Medallas=0;
    }
    public int getBayas(){
        return this.Bayas;
    }
    public int getPosiones(){
        return this.Pociones;
    }
    public int getMedallas(){
        return this.Medallas;
    }
    public void setBayas(int Bayas){
        this.Bayas =Bayas;
    }
    public void setPosiones(int pociones){
        this.Pociones =Pociones;
    }
    public void setMedallas(int medallas) {
        this.Medallas =Medallas;
        
    }
}
