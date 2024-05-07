package ar.edu.unju.fi.ejercicio06.model;

public class FelinoSalvaje {
	private String Nombre;
	private byte edad;
	private float Peso;
	
	
	
	public FelinoSalvaje(String nombre, byte edad, float peso) {
		super();
		Nombre = nombre;
		this.edad = edad;
		Peso = peso;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public byte getEdad() {
		return edad;
	}
	public void setEdad(byte edad) {
		this.edad = edad;
	}
	public float getPeso() {
		return Peso;
	}
	public void setPeso(float peso) {
		Peso = peso;
	}
	@Override
	public String toString() {
		return "FelinoSalvaje [Nombre=" + Nombre + ", edad=" + edad + ", Peso=" + Peso + "]";
	}
	
}
