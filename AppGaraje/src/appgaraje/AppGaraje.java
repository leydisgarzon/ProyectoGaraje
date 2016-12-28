/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appgaraje;

import java.util.ArrayList;
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
        Garaje g = new Garaje();
        Trabajo t = new Mecanica("Arreglo mecanico");
        g.registrarTrabajo(t);
        ChapaPintura t1 = new ChapaPintura("Arreglo pintura");
        g.registrarTrabajo(t1);
        
        System.out.println(g.mostrarTodosLosTrabajo());
        System.out.println(g.mostrarTrabajo(1));
        
        g.getTodosLosTrabajos().get(1).aumentarHoras(11.5f);
        ((ChapaPintura)g.getTodosLosTrabajos().get(1)).aumentarCostoMateriales(3.5f);
        System.out.println(g.getTodosLosTrabajos().get(1).calcularCostoTotal());
        
        g.getTodosLosTrabajos().get(1).finalizarTrabajo();
        g.getTodosLosTrabajos().get(1).aumentarHoras(11.5f);
        System.out.println(g.getTodosLosTrabajos().get(1).getHoras());
    }

}

/**
 Clase Garaje
 */
class Garaje {
    private List<Trabajo> todosLosTrabajos;

    public Garaje() {
        this.todosLosTrabajos = new ArrayList<>();
    }
    

   public List<Trabajo> getTodosLosTrabajos() {
        return todosLosTrabajos;
    }
    
    public void registrarTrabajo(Trabajo trabajo){
        todosLosTrabajos.add(trabajo);
    }
    
    public String mostrarTrabajo(int identificador){
        String resultado="";
        for(int i=0; i< todosLosTrabajos.size();i++){
            if(todosLosTrabajos.get(i).getIdentificador()==identificador)
                resultado=todosLosTrabajos.get(i).toString();
            else
                resultado="No existe trabajo con este identificador";
        }  
        return resultado;
    }
    
    public String mostrarTodosLosTrabajo(){
        String resultado= "Los trabajos registrados son: ";
        for(int i=0; i< todosLosTrabajos.size();i++){
            //System.out.println(todosLosTrabajos.get(i).toString());
            resultado = resultado + todosLosTrabajos.get(i).toString() + "\n";           
        }
        return resultado;
    }
    
}

/**
 Clase Trabajo
 */
abstract class Trabajo {

    protected int identificador;
    protected String descripcion;
    protected float horas;
    protected boolean finalizado;
    protected final int plazo;
    private static int proximoIdentificador;

    public Trabajo(int plazo, String descrip) {
        this.plazo = plazo;
        this.descripcion = descrip;
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
        if(!this.finalizado)
            this.horas += aumento;
        else
            System.out.println("No se pudo aumentar las horas porque el trabajo ya está finalizado");
    }
    
    public float calcularCostoFijo(){
        return this.horas * 30;
    }

    public int getPlazo() {
        return plazo;
    }
    
    public void finalizarTrabajo(){
        this.finalizado = true;
    }
    
    public abstract float calcularCostoTotal();

    /*@Override
    public String toString() {
        return "Trabajo{" + "identificador=" + identificador + ", descripcion=" + descripcion + ", horas=" + horas + ", finalizado=" + finalizado + '}';
    }*/
    
    

}

/**
 Clase Revision
 */
class Revision extends Trabajo {
        
    public Revision(String descrip) {
        super(7,descrip);
    }

    @Override
    public float calcularCostoTotal() {
        return this.calcularCostoFijo() + 20;
    }   
    
    @Override
    public String toString() {
        return "Trabajo{" + "identificador=" + identificador + ", tipo= Revisión" + ", descripcion=" + descripcion + ", horas=" + horas + ", finalizado=" + finalizado + '}';
    }
}

/**
 Clase Reparacion
 */
abstract class Reparacion extends Trabajo {

    protected float costoMateriales;
    
    public Reparacion(int plazo,String descrip) {
        super(plazo,descrip);
    }
    
    public void aumentarCostoMateriales(float aumento){
         if(!this.finalizado)
            this.costoMateriales += aumento;
         else
            System.out.println("No se pudo aumentar el costo de materiar porque el trabajo ya está finalizado");
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

    public Mecanica(String descrip) {
        super(14,descrip);
    }

    @Override
    public float calcularCostoEspecifico() {
        return this.costoMateriales * 1.1f;
    }

    @Override
    public String toString() {
        return "Trabajo{" + "identificador=" + identificador + ", tipo= Reparación Mecánica" + ", descripcion=" + descripcion + ", horas=" + horas + ", costo de materiales=" + costoMateriales + ", finalizado=" + finalizado + '}';
    }
}

/**
 Clase Reparacion de Chapa y Pintura
 */
class ChapaPintura extends Reparacion {

    public ChapaPintura(String descrip) {
        super(21,descrip);
    }

    @Override
    public float calcularCostoEspecifico() {
        return this.costoMateriales * 1.3f;
    }
    
    @Override
    public String toString() {
        return "Trabajo{" + "identificador=" + identificador + ", tipo= Reparación de Chapa/Pintura" + ", descripcion=" + descripcion + ", horas=" + horas +  ", costo de materiales=" + costoMateriales + ", finalizado=" + finalizado + '}';
    }
}
