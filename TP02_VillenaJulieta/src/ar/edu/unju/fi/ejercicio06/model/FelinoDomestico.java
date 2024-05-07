package ar.edu.unju.fi.ejercicio06.model;

public class FelinoDomestico {
	private String Nombre;
	private byte edad;
	private float Peso;
	
	
	
	public FelinoDomestico(String nombre, byte edad, float peso) {
		super();
		Nombre = nombre;
		this.edad = edad;
		Peso = peso;
	}
	@Override
	public String toString() {
		return "FelinoDomestico [Nombre=" + Nombre + ", edad=" + edad + ", Peso=" + Peso + "]";
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
	
	
}
