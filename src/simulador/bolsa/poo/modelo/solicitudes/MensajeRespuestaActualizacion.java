package simulador.bolsa.poo.modelo.solicitudes;

public class MensajeRespuestaActualizacion extends MensajeActualizacion {

    private String enterpriseName;
    private float stockPrice;

    public MensajeRespuestaActualizacion (int codeId,String enterpriseName,float stockPrice){
        super(codeId);
        this.setEnterpriseName(enterpriseName);
        this.setStockPrice(stockPrice);
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public float getStockPrice() {
        return stockPrice;
    }

    public void setStockPrice(float stockPrice) {
        this.stockPrice = stockPrice;
    }

    public String codificar(){

        return (this.getCodigoId()+"|"+this.getEnterpriseName()+"|"+String.valueOf(this.getStockPrice()));
    }

}
