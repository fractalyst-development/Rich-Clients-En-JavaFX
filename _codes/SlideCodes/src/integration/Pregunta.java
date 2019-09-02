package integration;


public class Pregunta {
    private String quien;
    private String que;
    private long cuando;

    public Pregunta() {
    }

    public Pregunta(String quien, String que, long cuando) {
        this.quien = quien;
        this.que = que;
        this.cuando = cuando;
    }

    public String getQuien() {
        return quien;
    }

    public void setQuien(String quien) {
        this.quien = quien;
    }

    public String getQue() {
        return que;
    }

    public void setQue(String que) {
        this.que = que;
    }

    public long getCuando() {
        return cuando;
    }

    public void setCuando(long cuando) {
        this.cuando = cuando;
    }
    
}
