/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appgaraje;

import java.util.List;


/**
 *
 * @author ley
 */
public class AppGaraje {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }

}

/**
 Clase Garaje
 */
class Garaje {
    public List<Trabajo> todosLosTrabajos;
    //public static int cantidadDeTrabajos;

    public List<Trabajo> getTodosLosTrabajos() {
        return todosLosTrabajos;
    }

}

/**
 Clase Trabajo
 */
abstract class Trabajo {

    protected int identificador;
    protected float horas;
    protected String descripcion;
    protected boolean finalizado;
    protected final int plazo;
    private static int proximoIdentificador;

    public Trabajo(int plazo) {
        this.plazo = plazo;
        this.identificador = proximoIdentificador;
        proximoIdentificador++;
        /*if(this.identificador > 0)
        this.identificador --; // this.identificador -= 1;*/
        
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }


    public float getHoras() {
        return horas;
    }

    public void setHoras(float horas) {
        this.horas = horas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean getFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }
    
    public void aumentarHoras(float aumento){
        this.horas += aumento;
    }
    
    public float calcularCostoFijo(){
        return this.horas * 30;
    }

    public int getPlazo() {
        return plazo;
    }
    
    
    
    public abstract float calcularCostoTotal();

}

/**
 Clase Revision
 */
class Revision extends Trabajo {
        
    public Revision() {
        super(7);
    }

    @Override
    public float calcularCostoTotal() {
        return this.calcularCostoFijo() + 20;
    }   
    
    
}

/**
 Clase Reparacion
 */
abstract class Reparacion extends Trabajo {

    protected float costoMateriales;
    
    public Reparacion(int plazo) {
        super(plazo);
    }
    
    public void aumentarCostoMateriales(float aumento){
        this.costoMateriales += aumento;
    }
    
    
    @Override
    public float calcularCostoTotal() {
        return this.calcularCostoFijo()+ this.calcularCostoEspecifico();
    }
    
    abstract public float calcularCostoEspecifico();
    
}

/**
 Clase Reparacion Mecanica
 */
class Mecanica extends Reparacion {

    public Mecanica() {
        super(14);
    }

    @Override
    public float calcularCostoEspecifico() {
        return this.costoMateriales * 1.1f;
    }

    
}

/**
 Clase Reparacion de Chapa y Pintura
 */
class ChapaPintura extends Reparacion {

    public ChapaPintura(int plazo) {
        super(21);
    }

    @Override
    public float calcularCostoEspecifico() {
        return this.costoMateriales * 1.3f;
    }
    
    
}
