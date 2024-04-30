package ar.edu.unju.fi.ejercicio03.constantes;

public enum Provincia {
    JUJUY(672260, 53219), SALTA(1333365, 155488), TUCUMAN(1687305, 22524), CATAMARCA(367820, 102606), LA_RIOJA(331847, 89680), SANTIAGO_DEL_ESTERO(874006, 136351);

    private int cantidadPoblacion;
    private double superficie;

    Provincia(int cantidadPoblacion, double superficie) {
        this.cantidadPoblacion = cantidadPoblacion;
        this.superficie = superficie;
    }

    public int getCantidadPoblacion() {
        return cantidadPoblacion;
    }

    public void setCantidadPoblacion(int cantidadPoblacion) {
        this.cantidadPoblacion = cantidadPoblacion;
    }

    public double getSuperficie() {
        return superficie;
    }

    public void setSuperficie(double superficie) {
        this.superficie = superficie;
    }

    public double calcularDensidadPoblacional() {
        return cantidadPoblacion / superficie;
    }
}