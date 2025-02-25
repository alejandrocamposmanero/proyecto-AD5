package dam.ad.apirest.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity(name = "habitaciones")
public class Habitacion {
    @Id
    private int id;
    @Column(name = "numero_camas")
    private int numCamas;
    private double precio;
    @ManyToOne
    private Cliente cliente;
    private Date fecha;

    public Habitacion() {
    }

    public Habitacion(int numCamas, double precio) {
        this.numCamas = numCamas;
        this.precio = precio;
        this.fecha = new Date();
    }

    public Habitacion(int id, int numCamas, double precio) {
        this.id = id;
        this.numCamas = numCamas;
        this.precio = precio;
        this.fecha = new Date();
    }

    public Habitacion(int numCamas, double precio, Cliente cliente) {
        this.numCamas = numCamas;
        this.precio = precio;
        this.fecha = new Date();
        setCliente(cliente);
    }

    public Habitacion(int id, int numCamas, double precio, Cliente cliente) {
        this.id = id;
        this.numCamas = numCamas;
        this.precio = precio;
        this.fecha = new Date();
        setCliente(cliente);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumCamas() {
        return numCamas;
    }

    public void setNumCamas(int numCamas) {
        this.numCamas = numCamas;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        cliente.addHabitacion(this);
    }

    public Date getFecha(){
        return fecha;
    }

    public void setFecha(Date fecha){
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Habitacion{" +
                "id=" + id +
                ", numCamas=" + numCamas +
                ", precio=" + precio +"€"+
                '}';
    }
}
